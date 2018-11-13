package cn.sourcespro.commons.utils;

import cn.sourcespro.commons.entity.BaseTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 左右值树形结构工具类
 *
 * @author zhanghaowei
 * @date 2018/11/12
 */
public class TreeUtil {

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

    private static  <T extends BaseTree> List<T> findByPid(T t, List<T> list){
        Integer pid = t.getId();
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (item.getPid().equals(pid)) {
                item.setChildren(findByPid(item, list));
                result.add(item);
            }
        }
        return result;
    }

}
