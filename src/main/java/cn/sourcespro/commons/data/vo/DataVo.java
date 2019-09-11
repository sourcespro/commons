package cn.sourcespro.commons.data.vo;

/**
 * data vo
 */
public class DataVo<T> extends Vo {

    private T data;

    public DataVo() {
    }

    public DataVo(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
