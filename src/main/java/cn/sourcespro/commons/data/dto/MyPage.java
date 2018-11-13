package cn.sourcespro.commons.data.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 自定义分页 dto
 *
 * @author zhanghaowei
 * @version 1.01 2017年11月27日
 */
public class MyPage<T> extends Page<T> {

    /**
     * 页码
     */
    private long page;

    /**
     * 每页记录条数
     */
    private long size;

    public MyPage(long current, long size) {
        super(current, size);
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    @Override
    public long getSize() {
        return size;
    }
}
