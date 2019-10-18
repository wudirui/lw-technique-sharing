import java.util.List;

/**
 * @ClassName Menu
 * @Description TODO
 * @Author ruizhang
 * @Date 2019/10/19 0:42
 */
public class Menu {

    private String id;
    private String parentId;
    private String text;
    private String url;
    private String yxbz;
    private List<Menu> children;
    public Menu(String id,String parentId,String text,String url,String yxbz) {
        this.id=id;
        this.parentId=parentId;
        this.text=text;
        this.url=url;
        this.yxbz=yxbz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}


