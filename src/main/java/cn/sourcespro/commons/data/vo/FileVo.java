package cn.sourcespro.commons.data.vo;

/**
 * file vo
 */
public class FileVo extends Vo {

    /**
     * 相对路径
     */
    private String path;

    public FileVo() {
    }

    public FileVo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
