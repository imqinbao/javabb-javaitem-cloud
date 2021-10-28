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
 * 角色
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Data
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value="Role实体类", description="角色")
public class RoleDO extends BaseDO {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色id")
    @TableId
    private String id;

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "角色标识")
    @TableField("role_code")
    private String roleCode;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;


    @ApiModelProperty(value = "用户ID")
    @TableField(exist = false)
    private String userId;

    @Override
    public String toString() {
        return "RoleDO{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", remark='" + remark + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
