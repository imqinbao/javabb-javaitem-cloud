package cn.javabb.sys.repository.dataobject;

import cn.javabb.common.repository.mybatis.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
/**
 * 角色权限
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Data
@Accessors(chain = true)
@TableName("sys_role_menu")
@ApiModel(value="RoleMenu实体类", description="角色权限")
public class RoleMenuDO extends BaseDO {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    @TableId
    private String id;

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private String roleId;

    @ApiModelProperty(value = "菜单id")
    @TableField("menu_id")
    private String menuId;

    @Override
    public String toString() {
        return "RoleMenuDO{" +
                "id='" + id + '\'' +
                ", roleId='" + roleId + '\'' +
                ", menuId='" + menuId + '\'' +
                '}';
    }
}
