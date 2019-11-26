package cn.sourcespro.commons.data.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * pageInfo
 */
public class PageInfo<T> extends Page {

    /**
     * pageNo
     */
    private long pageNo = 1;

    /**
     * pageSize
     */
    private long pageSize = 10;

    /**
     * 排序列名
     */
    private String field;

    /**
     * 排序方法 asc desc
     */
    private String order;

    /**
     * 查询条件
     */
    private T condition;

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        super.setCurrent(pageNo);
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        super.setSize(pageSize);
        this.pageSize = pageSize;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }
}
