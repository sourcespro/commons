package cn.sourcespro.commons.annotation;

import cn.sourcespro.commons.dao.BaseTreeMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * lr-tree
 *
 * @author zhanghaowei
 * @since 2018/7/10
 */
//@Component
//@Aspect
public class LRTreeAspect {

//    @Autowired
    private BaseTreeMapper mapper;

//    @Around("@annotation(add)")
    public Object around(ProceedingJoinPoint point, LRTreeAdd add){
        Object[] args = new Object[0];
        try {
            args = point.getArgs();
            String tableName = add.tableName();
            Object clazz = args[0];
            Class<?> param = clazz.getClass();
            Method getPidMethod = param.getDeclaredMethod("getPid");
            Method setLftMethod = param.getDeclaredMethod("setLft", Integer.class);
            Method setRgtMethod = param.getDeclaredMethod("setRgt", Integer.class);
            Method setLevelMethod = param.getDeclaredMethod("setLevel", Integer.class);
            Method setIdsMethod = param.getDeclaredMethod("setIds", String.class);

            Integer pid = (Integer) getPidMethod.invoke(clazz);
            //如果是第一层：
            if (pid == 0) {
                //查询第一层最大的右值  maxRight
                Integer maxLv1Val = mapper.maxLv1Val(tableName);
                if (maxLv1Val == null) {
                    maxLv1Val = 0;
                }
                //新的数据的左右值为
                setLftMethod.invoke(clazz, maxLv1Val + 1);
                setRgtMethod.invoke(clazz, maxLv1Val + 2);
                setLevelMethod.invoke(clazz, 0);
                setIdsMethod.invoke(clazz, "");

            }
            // 如果非第一层：pId！=0
            else {
                Map<String, Object> parent = mapper.findById(tableName, String.valueOf(pid));

                Integer refId = (Integer) parent.get("id");
                Integer refLft = (Integer) parent.get("lft");
                Integer refRgt = (Integer) parent.get("rgt");
                Integer refLevel = (Integer) parent.get("level");
                String refIds = (String) parent.get("ids");

                // 获取要添加节点父节点（P）下一级节点最大的右值 maxRight 最大右值：maxLeft
                Integer maxLvChildVal = mapper.maxLvChildVal(tableName, refLevel, refLft, refRgt);
                //如果maxRight==0表示P下还没有子节点
                if (maxLvChildVal == null || maxLvChildVal == 0) {
                    //>=右节点的所有左右节点+2
                    mapper.updateLftVal(tableName, refLft);
                    mapper.updateRgtVal(tableName, refLft);
                    setLftMethod.invoke(clazz, refRgt);
                    setRgtMethod.invoke(clazz, refRgt +1);
                } else {
                    //>右节点的所有左右节点+2
                    mapper.updateLftVal(tableName, maxLvChildVal);
                    mapper.updateRgtVal(tableName, maxLvChildVal);
                    setLftMethod.invoke(clazz, maxLvChildVal + 1);
                    setRgtMethod.invoke(clazz, maxLvChildVal + 2);
                }
                setLevelMethod.invoke(clazz, refLevel + 1);
                if (StringUtils.isEmpty(refIds)){
                    setIdsMethod.invoke(clazz, String.valueOf(refId));
                } else {
                    setIdsMethod.invoke(clazz, refIds + "," + refId);
                }
            }

            point.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return args;
    }

//    @Around("@annotation(delete)")
    public Object around2(ProceedingJoinPoint point, LRTreeDelete delete){
        Object[] args = new Object[0];
        try {
            args = point.getArgs();
            String tableName = delete.tableName();
            Map<String, Object> tree;
            if (args[0] instanceof Integer) {
                Integer id = (Integer) args[0];
                tree = mapper.findById(tableName, String.valueOf(id));
            } else {
                String uuid = (String) args[0];
                tree = mapper.findByUuid(tableName, uuid);
            }
            if (tree != null) {
                Integer id = (Integer) tree.get("id");
                Integer pid = (Integer) tree.get("pid");
                Integer lft = (Integer) tree.get("lft");
                Integer rgt = (Integer) tree.get("rgt");
                List<Integer> childIds = mapper.findChildIds(tableName, lft, rgt);
                if (CollectionUtils.isEmpty(childIds)) {
                    //1、没有子节点
                    mapper.updateLftSubVal(tableName, rgt, 2);
                    mapper.updateRgtSubVal(tableName, rgt, 2);
                } else {
                    Map<String, Object> parent = mapper.findById(tableName, String.valueOf(pid));
                    Integer parentLft = (Integer) parent.get("lft");
                    Integer parentRgt = (Integer) parent.get("rgt");
                    Integer parentLevel = (Integer) parent.get("level");
                    Integer minBrotherLftVal = mapper.minBrotherLftVal(tableName, parentLevel, parentLft, parentRgt, String.valueOf(id));
                    if (minBrotherLftVal == null || minBrotherLftVal == 0) {
                        //2、有子节点 && 没有兄弟节点
                        mapper.updateLftSubVal(tableName, rgt, parentRgt - lft);
                        mapper.updateRgtSubVal(tableName, rgt, parentRgt - lft);
                    } else {
                        //3、有子节点 && 有兄弟节点
                        mapper.updateLftSubVal(tableName, rgt, minBrotherLftVal - lft);
                        mapper.updateRgtSubVal(tableName, rgt, minBrotherLftVal - lft);
                    }
                }
            }

            point.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return args;
    }

}
