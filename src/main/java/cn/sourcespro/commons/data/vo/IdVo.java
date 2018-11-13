package cn.sourcespro.commons.data.vo;


public class IdVo extends Vo {

    private Long id;

    public IdVo(Long id) {
        this.id = id;
    }

    public IdVo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
