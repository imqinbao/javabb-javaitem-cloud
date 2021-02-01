package cn.javabb.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
/**
 * 用户角色
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_role")
@ApiModel(value="UserRole实体类", description="用户角色")
public class UserRole implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;


    @Override
    public String toString() {
        return "UserRole{" +
        "id=" + id +
        ", userId=" + userId +
        ", roleId=" + roleId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
