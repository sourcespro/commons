package cn.sourcespro.commons.data.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * pageInfo
 * @author zhang
 */
@JsonIgnoreProperties(value = {"size", "current", "orders", "searchCount"})
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
     * 是否存在上一页
     */
    private boolean hasPrevious;

    /**
     * 是否存在下一页
     */
    private boolean hasNext;

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

    public boolean isHasPrevious() {
        return super.hasPrevious();
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isHasNext() {
        return super.hasNext();
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", field='" + field + '\'' +
                ", order='" + order + '\'' +
                ", condition=" + (condition != null ? condition.toString() : "{}") +
                "} ";
    }
}
