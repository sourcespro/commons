package cn.sourcespro.commons.data.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * page-dto
 *
 * @author zhanghaowei
 * @since 2018/9/4
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

    /**
     * 排序列名
     */
    private String field;
    /**
     * 排序方法 asc desc
     */
    private String order;

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
}
