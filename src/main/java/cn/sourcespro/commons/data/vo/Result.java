package cn.sourcespro.commons.data.vo;

import cn.sourcespro.commons.data.dto.PageInfo;
import cn.sourcespro.commons.utils.MessageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public class Result {

    public static Vo paramError(String errMsg){
        String msg = MessageUtil.get(errMsg);
        return new Vo(400, msg != null ? msg : errMsg);
    }

    public static Vo notData(String errMsg){
        String msg = MessageUtil.get(errMsg);
        return new Vo(410, msg != null ? msg : errMsg);
    }

    public static Vo updateError(String errMsg){
        String msg = MessageUtil.get(errMsg);
        return new Vo(409, msg != null ? msg : errMsg);
    }

    public static Vo notFound(String errMsg){
        String msg = MessageUtil.get(errMsg);
        return new Vo(404, msg != null ? msg : errMsg);
    }

    public static Vo forbidden(String errMsg){
        String msg = MessageUtil.get(errMsg);
        return new Vo(403, msg != null ? msg : errMsg);
    }

    public static Vo authError(String errMsg){
        String msg = MessageUtil.get(errMsg);
        return new Vo(401, msg != null ? msg : errMsg);
    }

    public static Vo systemError(String errMsg){
        String msg = MessageUtil.get(errMsg);
        return new Vo(500, msg != null ? msg : errMsg);
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
    public static <T> Vo data(List<T> list){
        return new DataVo(list);
    }

    @SuppressWarnings("unchecked")
    public static <T> Vo ok(T data){
        return new DataVo(data);
    }

    public static Vo code(String code) {
        return new StringVo(code);
    }

    public static Vo success(int code, String errMsg) {
        DataVo dataVo = new DataVo(null);
        dataVo.setCode(code);
        String msg = MessageUtil.get(errMsg);
        dataVo.setMsg(msg != null ? msg : errMsg);
        return dataVo;
    }

    public static Vo error(int code, String errMsg) {
        DataVo dataVo = new DataVo(null);
        dataVo.setCode(code);
        String msg = MessageUtil.get(errMsg);
        dataVo.setMsg(msg != null ? msg : errMsg);
        return dataVo;
    }

    public static <T> PageVo<T> page(IPage<T> iPage){
        return new PageVo<>(iPage.getTotal(), iPage.getRecords());
    }

    public static <T> PageInfoVo pageInfo(PageInfo<T> pageInfo){
        return new PageInfoVo(pageInfo);
    }
}
