package cn.sourcespro.commons.data.vo;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class NodeVo<ID extends Serializable> {

    private ID id;

    private String name;

    private Boolean leaf;

    private Integer level;

    private List<NodeVo> children = Collections.emptyList();

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<NodeVo> getChildren() {
        return children;
    }

    public void setChildren(List<NodeVo> children) {
        this.children = children;
    }
}
