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
 * 用户角色
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_role")
@ApiModel(value="UserRole实体类", description="用户角色")
public class UserRoleDO extends BaseDO {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    @TableId
    private String id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private String roleId;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
