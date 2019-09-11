package cn.sourcespro.commons.conver;

import cn.sourcespro.commons.data.vo.NodeVo;
import cn.sourcespro.commons.entity.BaseTree;

/**
 * 树形结构节点对象类型转换
 *
 * @author zhanghaowei
 * @since 2018/11/23
 */
public interface AbstractTreeNodeConvert<R, S> {

    /**
     * 树形结构节点对象类型转换
     * @param s
     *      待转换数据
     * @return
     *      R 目标数据
     */
    R convert(S s);

}
