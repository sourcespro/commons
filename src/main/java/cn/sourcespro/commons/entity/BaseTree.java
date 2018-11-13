package cn.sourcespro.commons.entity;

import java.util.List;

/**
 * 左右值结构基类
 *
 * @author zhanghaowei
 * @date 2018/10/31
 */
public class BaseTree {

    /**
     * id
     */
    private Integer id;

    /**
     * 父id
     */
    private Integer pid;

    /**
     * 左值
     */
    private Integer lft;

    /**
     * 右值
     */
    private Integer rgt;

    /**
     * 级别
     */
    private Integer level;

    /**
     * id节点路径
     */
    private String ids;

    /**
     * 是否叶子节点（最子节点）
     */
    private Boolean leaf;

    private List<?> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLft() {
        return lft;
    }

    public void setLft(Integer lft) {
        this.lft = lft;
    }

    public Integer getRgt() {
        return rgt;
    }

    public void setRgt(Integer rgt) {
        this.rgt = rgt;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }
}
