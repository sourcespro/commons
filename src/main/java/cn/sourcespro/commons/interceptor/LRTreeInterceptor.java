package cn.sourcespro.commons.interceptor;


import cn.sourcespro.commons.annotation.LRTreeDelete;
import cn.sourcespro.commons.dao.BaseTreeMapper;
import cn.sourcespro.commons.entity.BaseTree;
import cn.sourcespro.commons.utils.SpringUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 左右值树拦截器
 *
 * @author zhanghaowei
 * @since 2018/10/31
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class LRTreeInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(LRTreeInterceptor.class);

    private BaseTreeMapper treeMapper;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (treeMapper == null) {
            this.treeMapper = SpringUtil.getBean(BaseTreeMapper.class);
            logger.debug("获取base tree mapper：{}", treeMapper);
        }
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String mappedStatementId = mappedStatement.getId();
        //注解中method的值
        String methodName = invocation.getMethod().getName();
        //sql类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        //参数 T extend BaseTree
        Object object = invocation.getArgs()[1];
        //获取表名
        BoundSql boundSql = mappedStatement.getBoundSql(object);
        String tableName = getTableName(boundSql);

        if ("update".equals(methodName)) {
            if (object instanceof BaseTree) {
                logger.debug("左右值树结构，进行拦截更新相关值");
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    logger.debug("插入语句，执行左右值拦截方法");
                    Field pidField = object.getClass().getSuperclass().getDeclaredField("pid");
                    Field lftField = object.getClass().getSuperclass().getDeclaredField("lft");
                    Field rgtField = object.getClass().getSuperclass().getDeclaredField("rgt");
                    Field idsField = object.getClass().getSuperclass().getDeclaredField("ids");
                    Field leafField = object.getClass().getSuperclass().getDeclaredField("leaf");
                    Field levelField = object.getClass().getSuperclass().getDeclaredField("level");

                    pidField.setAccessible(true);
                    lftField.setAccessible(true);
                    rgtField.setAccessible(true);
                    idsField.setAccessible(true);
                    leafField.setAccessible(true);
                    levelField.setAccessible(true);

                    Object pidObj = pidField.get(object);
                    Long pid;
                    if (pidObj instanceof Integer) {
                        pid = Long.valueOf((Integer)pidObj);
                    } else if (pidObj instanceof Long){
                        pid = (Long)pidObj;
                    } else {
                        throw new IllegalArgumentException("左右值树仅支持id类型为Integer和Long类型");
                    }
                    //如果是第一层：
                    if (pid == 0) {
                        //查询第一层最大的右值  maxRight
                        int maxLv1Val = treeMapper.maxLv1Val(tableName);

                        //待添加的数据的左右值为
                        lftField.set(object, maxLv1Val + 1);
                        rgtField.set(object, maxLv1Val + 2);
                        idsField.set(object, "");
                        leafField.set(object, 1);
                        levelField.set(object, 0);

                    }
                    // 如果非第一层：pId != 0
                    else {
                        //更新父节点为非叶子节点
                        treeMapper.updateParentNotLeaf(tableName, pid);

                        Map<String, Object> parent = treeMapper.findById(tableName, pid);

                        Long refId = (Long) parent.get("id");
                        Integer refLft = (Integer) parent.get("lft");
                        Integer refRgt = (Integer) parent.get("rgt");
                        Integer refLevel = (Integer) parent.get("level");
                        String refIds = (String) parent.get("ids");

                        // 获取要添加节点父节点（P）下一级节点最大的右值 maxLvChildVal
                        Integer maxLvChildVal = treeMapper.maxLvChildVal(tableName, refLevel, refLft, refRgt);
                        //maxLvChildVal == 0表示P下还没有子节点
                        if (maxLvChildVal == null || maxLvChildVal == 0) {
                            //>=右节点的所有左右节点+2
                            treeMapper.updateLftVal(tableName, refLft);
                            treeMapper.updateRgtVal(tableName, refLft);
                            lftField.set(object, refRgt);
                            rgtField.set(object, refRgt +1);
                        } else {
                            //>右节点的所有左右节点+2
                            treeMapper.updateLftVal(tableName, maxLvChildVal);
                            treeMapper.updateRgtVal(tableName, maxLvChildVal);
                            lftField.set(object, maxLvChildVal + 1);
                            rgtField.set(object, maxLvChildVal + 2);
                        }
                        leafField.set(object, true);
                        levelField.set(object, refLevel + 1);
                        if (refIds == null || "".equals(refIds)){
                            idsField.set(object, String.valueOf(refId));
                        } else {
                            idsField.set(object, refIds + "," + refId);
                        }
                    }
                }
            }
            if (SqlCommandType.UPDATE.equals(sqlCommandType) && mappedStatementId.contains("deleteBy")) {
                Class<?> classType = Class.forName(mappedStatementId.substring(0,mappedStatementId.lastIndexOf(".")));
                String mName  = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1 ,mappedStatementId.length());
                for (Method method : classType.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(LRTreeDelete.class) && method.getName().equals(mName)) {
                        //方法包含自定义注解 @LRTreeDelete
                        logger.debug("逻辑删除语句：{}，执行左右值拦截方法", mappedStatementId);
                        Map<String, Object> preDelObj;
                        if (object instanceof Long) {
                            preDelObj = treeMapper.findById(tableName, (Long) object);
                        } else if (object instanceof Integer) {
                            preDelObj = treeMapper.findById(tableName, Long.valueOf((Integer)object));
                        } else if (object instanceof String) {
                            preDelObj = treeMapper.findByUuid(tableName, String.valueOf(object));
                        } else {
                            throw new IllegalArgumentException("左右值树仅支持id类型为Integer、Long、String（uuid）类型");
                        }
                        if (preDelObj != null) {
                            Long id = (Long) preDelObj.get("id");
                            Long pid = (Long) preDelObj.get("pid");
                            Integer lft = (Integer) preDelObj.get("lft");
                            Integer rgt = (Integer) preDelObj.get("rgt");
                            List<Integer> childIds = treeMapper.findChildIds(tableName, lft, rgt);
                            if (childIds == null || childIds.size() == 0) {
                                //1、没有子节点
                                treeMapper.updateLftSubVal(tableName, rgt, 2);
                                treeMapper.updateRgtSubVal(tableName, rgt, 2);
                            } else {
                                Map<String, Object> parent = treeMapper.findById(tableName, pid);
                                Integer parentLft = (Integer) parent.get("lft");
                                Integer parentRgt = (Integer) parent.get("rgt");
                                Integer parentLevel = (Integer) parent.get("level");
                                Integer minBrotherLftVal = treeMapper.minBrotherLftVal(tableName, parentLevel, parentLft, parentRgt, id);
                                if (minBrotherLftVal == null || minBrotherLftVal == 0) {
                                    //2、有子节点 && 没有兄弟节点
                                    treeMapper.updateLftSubVal(tableName, rgt, parentRgt - lft);
                                    treeMapper.updateRgtSubVal(tableName, rgt, parentRgt - lft);
                                } else {
                                    //3、有子节点 && 有兄弟节点
                                    treeMapper.updateLftSubVal(tableName, rgt, minBrotherLftVal - lft);
                                    treeMapper.updateRgtSubVal(tableName, rgt, minBrotherLftVal - lft);
                                }
                            }
                        }
                    }
                }

            }
        }


        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private String getTableName(BoundSql boundSql){
        // 0、获取sql
        String sql = boundSql.getSql().trim().toLowerCase();
        String[] sqls = sql.split("\\s+");
        switch (sqls[0]){
            case "select" : {
                // select aa,bb,cc from tableName
                for (int i=0; i<sqls.length; i++){
                    if (sqls[i].equals("from")) {
                        return sqls[i +1];
                    }
                }
                break;
            }
            case "update" : {
                // update tableName
                return sqls[1];
            }
            case "insert" : {
                // insert into tableName
                return sqls[2];
            }
            case "delete" : {
                // delete tableName
                return sqls[1];
            }
            default:
                return null;
        }
        return null;
    }
}
