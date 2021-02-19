package cn.javabb.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
/**
 * 角色
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Data
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value="Role实体类", description="角色")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色id")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "角色标识")
    @TableField("role_code")
    private String roleCode;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "是否删除,0否,1是")
    @TableField("deleted")
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "用户ID")
    @TableField(exist = false)
    private Integer userId;

    @Override
    public String toString() {
        return "Role{" +
        "roleId=" + roleId +
        ", roleName=" + roleName +
        ", roleCode=" + roleCode +
        ", remark=" + remark +
        ", deleted=" + deleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
