package cn.sourcespro.commons.data.vo;

/**
 * Vo
 *
 * @author 张浩伟
 * @version 1.01 2017年12月01日
 */
public class Vo {

    private int code = 0;

    private String msg = "success";

    public Vo() {
    }

    public Vo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
