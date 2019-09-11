package cn.sourcespro.commons.data.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public class Result {

    public static Vo paramError(String errMsg){
        return new Vo(400, errMsg);
    }

    public static Vo systemError(String errMsg){
        return new Vo(500, errMsg);
    }

    public static Vo ok(){
        return new Vo();
    }

    public static Vo ok(Long id){
        return new IdVo(id);
    }

    @SuppressWarnings("unchecked")
    public static <T> Vo ok(List<T> list){
        return new ListVo(list);
    }

    @SuppressWarnings("unchecked")
    public static <T> Vo ok(T data){
        return new DataVo(data);
    }

    public static Vo code(String code) {
        return new StringVo(code);
    }

    public static <T> PageVo<T> page(IPage<T> iPage){
        return new PageVo<>(iPage.getTotal(), iPage.getRecords());
    }
}
