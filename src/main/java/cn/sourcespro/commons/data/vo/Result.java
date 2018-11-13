package cn.sourcespro.commons.data.vo;

import java.util.List;

public class Result {

    public static Vo paramError(String errMsg){
        return new Vo(400, errMsg);
    }

    public static Vo ok(){
        return new Vo();
    }

    public static Vo ok(Long id){
        return new IdVo(id);
    }

    public static <T> Vo ok(List<T> list){
        return new ListVo(list);
    }

    public static Vo code(String code) {
        return new StringVo(code);
    }
}
