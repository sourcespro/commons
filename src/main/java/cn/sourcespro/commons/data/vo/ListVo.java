package cn.sourcespro.commons.data.vo;

import java.util.List;

public class ListVo<T> extends Vo {

    private List<T> list;

    public ListVo(List<T> list) {
        this.list = list;
    }

    public ListVo() {
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
