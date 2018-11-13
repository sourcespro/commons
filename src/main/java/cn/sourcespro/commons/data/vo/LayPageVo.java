package cn.sourcespro.commons.data.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * layui page vo
 *
 * @author 张浩伟
 * @version 1.01 2017/12/13
 */
public class LayPageVo<T> extends Vo {

    private long count;

    private List<T> data = new ArrayList<>();

    public LayPageVo() {
    }

    public LayPageVo(int code, String msg, long count, List<T> list) {
        super(code, msg);
        this.count = count;
        this.data = list;
    }

    public LayPageVo(long count, List<T> list) {
        this.count = count;
        this.data = list;
    }
}
