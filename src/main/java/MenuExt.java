import java.io.Serializable;
import java.util.*;

/**
 * <Description> 菜单扩展<br>
 *
 * @author lu.wei<br>
 * @email 1025742048@qq.com <br>
 * @date 2016年12月19日 <br>
 * @since V1.0<br>
 */
public class MenuExt implements Serializable {

    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单地址
     */
    private String url;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 子菜单
     */
    private List<MenuExt> children = new ArrayList<MenuExt>();

    /**
     * 菜单顺序
     */
    private Integer ordby;

    /**
     * 菜单状态
     */
    private String state;

    public MenuExt() {
    }

    public MenuExt(Long id, String name, String url, Long parentId, Integer ordby) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.parentId = parentId;
        this.ordby = ordby;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrdby() {
        return ordby;
    }

    public void setOrdby(Integer ordby) {
        this.ordby = ordby;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<MenuExt> getChildren() {
        return children;
    }

    public void setChildren(List<MenuExt> children) {
        this.children = children;
    }

    /**
     * <Description> 孩子节点排序<br>
     *
     * @author lu.wei<br>
     * @email 1025742048@qq.com <br>
     * @date 2016年12月22日 下午10:54:26 <br>
     * <br>
     */
    public void sortChildren() {
        Collections.sort(children, new Comparator<MenuExt>() {
            @Override
            public int compare(MenuExt menu1, MenuExt menu2) {
                int result = 0;

                Integer ordby1 = menu1.getOrdby();
                Integer ordby2 = menu2.getOrdby();

                Long id1 = menu1.getId();
                Long id2 = menu2.getId();
                if (null != ordby1 && null != ordby2) {
                    result = (ordby1 < ordby2 ? -1 : (ordby1 == ordby2 ? 0 : 1));
                } else {
                    result = (id1 < id2 ? -1 : (id1 == id2 ? 0 : 1));
                }
                return result;
            }

        });
        // 对每个节点的下一层节点进行排序
        for (Iterator<MenuExt> it = children.iterator(); it.hasNext(); ) {
            it.next().sortChildren();
        }
    }
}