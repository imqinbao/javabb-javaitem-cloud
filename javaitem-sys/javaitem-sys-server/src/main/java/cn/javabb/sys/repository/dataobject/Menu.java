package cn.javabb.sys.repository.dataobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Data
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value="Menu实体类", description="菜单")
public class Menu implements Serializable {

    private static final long serialVersionUID=1L;

    public static final int TYPE_MENU = 0;  // 菜单类型
    public static final int TYPE_BTN = 1;  // 按钮类型

    @ApiModelProperty(value = "菜单id")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @ApiModelProperty(value = "上级id,0是顶级")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "菜单名称")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "菜单路由关键字,目录为空")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "菜单组件地址,目录为空")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "类型,0菜单,1按钮")
    @TableField("menu_type")
    private Integer menuType;

    @ApiModelProperty(value = "排序号")
    @TableField("sort_no")
    private Integer sortNo;

    @ApiModelProperty(value = "权限标识")
    @TableField("authority")
    private String authority;

    @ApiModelProperty(value = "打开位置")
    @TableField("target")
    private String target;

    @ApiModelProperty(value = "图标颜色")
    @TableField("color")
    private String color;

    @ApiModelProperty(value = "嵌套路由左侧选中")
    @TableField("uid")
    private String uid;

    @ApiModelProperty(value = "是否隐藏,0否,1是(仅注册路由不显示左侧菜单)")
    @TableField("hide")
    private Integer hide;

    @ApiModelProperty(value = "是否删除,0否,1是")
    @TableField("deleted")
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty("上级菜单名称")
    @TableField(exist = false)
    private String parentTitle;

    @ApiModelProperty("子菜单")
    @TableField(exist = false)
    private List<Menu> children;

    @ApiModelProperty("权限树回显选中状态,0未选中,1选中")
    @TableField(exist = false)
    private Boolean checked;

    @ApiModelProperty("权限树控制展开")
    @TableField(exist = false)
    private Boolean open;

    @Override
    public String toString() {
        return "Menu{" +
        "menuId=" + menuId +
        ", parentId=" + parentId +
        ", title=" + title +
        ", icon=" + icon +
        ", path=" + path +
        ", component=" + component +
        ", menuType=" + menuType +
        ", sortNo=" + sortNo +
        ", authority=" + authority +
        ", target=" + target +
        ", color=" + color +
        ", uid=" + uid +
        ", hide=" + hide +
        ", deleted=" + deleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
