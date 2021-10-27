package cn.javabb.sys.repository.dataobject;

import cn.javabb.common.repository.mybatis.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "User实体类", description = "用户")
public class UserDO extends BaseDO {

    private static final long serialVersionUID = 5153826381674245418L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "账号")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "邮箱是否验证,0否,1是")
    @TableField("email_verified")
    private Integer emailVerified;

    @ApiModelProperty(value = "真实姓名")
    @TableField("true_name")
    private String trueName;

    @ApiModelProperty(value = "身份证号")
    @TableField("id_card")
    private String idCard;

    @ApiModelProperty(value = "出生日期")
    @TableField("birthday")
    private Date birthday;

    @ApiModelProperty(value = "个人简介")
    @TableField("introduction")
    private String introduction;

    @ApiModelProperty(value = "机构id")
    @TableField("org_id")
    private Integer orgId;

    @ApiModelProperty("机构名称")
    @TableField(exist = false)
    private String orgName;
    @ApiModelProperty("性别")
    @TableField(exist = false)
    private String sexName;
    @ApiModelProperty("角色id")
    @TableField(exist = false)
    private List<String> roleIds;

    @ApiModelProperty("角色列表")
    @TableField(exist = false)
    private List<RoleDO> roles;

    @ApiModelProperty("权限列表")
    @TableField(exist = false)
    private List<Menu> authorities;

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", emailVerified=" + emailVerified +
                ", trueName='" + trueName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", birthday=" + birthday +
                ", introduction='" + introduction + '\'' +
                ", orgId=" + orgId +
                ", deleted=" + this.getDeleted() +
                ", orgName='" + orgName + '\'' +
                ", sexName='" + sexName + '\'' +
                ", roleIds=" + roleIds +
                ", roles=" + roles +
                ", authorities=" + authorities +
                '}';
    }
}
