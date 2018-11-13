package cn.sourcespro.commons.data.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * page-dto
 *
 * @author zhanghaowei
 * @date 2018/9/4
 */
public class PageDto {

    /**
     * pageNo
     */
    private long pageNo = 1;

    /**
     * pageSize
     */
    private long pageSize = 10;

    private String orderColumn;

    private String orderSort;

    public Page getPage(){
        Page page = new Page();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        return page;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(String orderSort) {
        this.orderSort = orderSort;
    }
}
