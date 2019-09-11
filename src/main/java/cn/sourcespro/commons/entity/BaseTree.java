package cn.sourcespro.commons.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

/**
 * 左右值结构基类
 *
 * @author zhanghaowei
 * @since 2018/10/31
 */
public class BaseTree<ID extends Serializable, T> {

    /**
     * id
     */
    private ID id;

    /**
     * 父id
     */
    private ID pid;

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

    @TableField(exist = false)
    private List<T> children;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getPid() {
        return pid;
    }

    public void setPid(ID pid) {
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

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
