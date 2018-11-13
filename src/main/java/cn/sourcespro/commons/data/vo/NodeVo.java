package cn.sourcespro.commons.data.vo;

import java.util.List;

public class NodeVo {

    private Long id;

    private String name;

    private String label;

    private Boolean spread;

    private Boolean isLeaf;

    private Integer code;

    private List<NodeVo> children = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<NodeVo> getChildren() {
        return children;
    }

    public void setChildren(List<NodeVo> children) {
        this.children = children;
    }
}
