package cn.sourcespro.commons.data.vo;

import cn.sourcespro.commons.data.dto.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * layui page vo
 *
 * @author 张浩伟
 * @version 1.01 2017/12/13
 */
public class PageInfoVo extends Vo {

    private PageInfo data;

    public PageInfoVo() {
    }

    public PageInfoVo(PageInfo data) {
        this.data = data;
    }

    public PageInfoVo(int code, String msg) {
        super(code, msg);
    }

    public PageInfo getData() {
        return data;
    }

    public void setData(PageInfo data) {
        this.data = data;
    }
}
