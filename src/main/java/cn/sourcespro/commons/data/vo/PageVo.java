package cn.sourcespro.commons.data.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * layui page vo
 *
 * @author 张浩伟
 * @version 1.01 2017/12/13
 */
public class PageVo<T> extends Vo {

    private long count;

    private List<T> data = new ArrayList<>();

    public PageVo() {
    }

    public PageVo(int code, String msg, long count, List<T> list) {
        super(code, msg);
        this.count = count;
        this.data = list;
    }

    public PageVo(long count, List<T> list) {
        this.count = count;
        this.data = list;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
