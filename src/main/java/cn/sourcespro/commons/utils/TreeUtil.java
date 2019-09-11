package cn.sourcespro.commons.utils;

import cn.sourcespro.commons.conver.AbstractTreeNodeConvert;
import cn.sourcespro.commons.data.vo.NodeVo;
import cn.sourcespro.commons.entity.BaseTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 左右值树形结构工具类
 *
 * @author zhanghaowei
 * @since 2018/11/12
 */
public class TreeUtil {

    /**
     * list结构转换成tree结构
     * @param list
     *      待转换集合
     * @param <T>
     *      集合数据类型
     * @return
     *      tree结构
     */
    public static <T extends BaseTree> List<T> converListToTree(List<T> list){
        Integer minLevel = null;
        for (T t : list) {
            //最小级别
            Integer currLevel = t.getLevel();
            if (minLevel == null) {
                minLevel = currLevel;
            } else {
                if (currLevel < minLevel) {
                    minLevel = currLevel;
                }
            }
        }
        List<T> result = new ArrayList<>();
        //第一级
        for (T t : list) {
            if (t.getLevel().equals(minLevel)){
                result.add(t);
            }
        }
        //第二级，第三级……
        for (T t : result) {
            t.setChildren(findByPid(t, list));
        }
        return result;
    }

    /**
     * 级联查询集合中下级
     * @param t
     *      父级
     * @param list
     *      全部集合数据
     * @param <T>
     *      集合数据类型
     * @return
     *      tree结构数据
     */
    private static <T extends BaseTree> List<T> findByPid(T t, List<T> list){
        Serializable pid = t.getId();
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (item.getPid().equals(pid)) {
                item.setChildren(findByPid(item, list));
                result.add(item);
            }
        }
        return result;
    }

    /**
     * list结构转换成tree结构，根据自定义的转换器
     * @param list
     *      待转换集合
     * @param convert
     *      节点转换器
     * @param <S>
     *      待转换类型
     * @param <R>
     *      转换结果类型
     * @return
     *      tree结构数据
     */
    public static <S extends BaseTree, R extends NodeVo> List<R> converListToTree(List<S> list, AbstractTreeNodeConvert convert){
        Integer minLevel = null;
        for (S s : list) {
            //最小级别
            Integer currLevel = s.getLevel();
            if (minLevel == null) {
                minLevel = currLevel;
            } else {
                if (currLevel < minLevel) {
                    minLevel = currLevel;
                }
            }
        }
        List<R> result = new ArrayList<>();
        //第一级
        for (S s : list) {
            if (s.getLevel().equals(minLevel)){
                result.add((R) convert.convert(s));
            }
        }
        //第二级，第三级……
        for (R r : result) {
            r.setChildren(findByPidNode(r, list, convert));
        }
        return result;
    }

    /**
     * 级联查询集合中下级，根据自定义的转换器
     * @param r
     *      目标结果对象
     * @param list
     *      待转换集合
     * @param convert
     *      自定义转换器
     * @param <S>
     *      原数据类型
     * @param <R>
     *      目标数据类型
     * @return
     *      目标数据类型子级
     */
    private static  <S extends BaseTree, R extends NodeVo> List<R> findByPidNode(R r, List<S> list, AbstractTreeNodeConvert convert){
        Serializable pid = r.getId();
        List<R> result = new ArrayList<>();
        for (S item : list) {
            if (item.getPid().equals(pid)) {
                item.setChildren(findByPid(item, list));
                result.add((R) convert.convert(item));
            }
        }
        return result;
    }

}
