package cn.javabb.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

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

    @ApiModelProperty(value = "状态,0正常,1冻结")
    @TableField("state")
    private Integer state;

    @ApiModelProperty(value = "是否删除,0否,1是")
    @TableField("deleted")
    private Integer deleted;

    @ApiModelProperty(value = "注册时间")
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty("机构名称")
    @TableField(exist = false)
    private String orgName;
    @ApiModelProperty("性别")
    @TableField(exist = false)
    private String sexName;
    @ApiModelProperty("角色id")
    @TableField(exist = false)
    private List<Integer> roleIds;

    @ApiModelProperty("角色列表")
    @TableField(exist = false)
    private List<Role> roles;

    @ApiModelProperty("权限列表")
    @TableField(exist = false)
    private List<Menu> authorities;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username=" + username +
                ", password=" + password +
                ", nickname=" + nickname +
                ", avatar=" + avatar +
                ", sex=" + sex +
                ", phone=" + phone +
                ", email=" + email +
                ", emailVerified=" + emailVerified +
                ", trueName=" + trueName +
                ", idCard=" + idCard +
                ", birthday=" + birthday +
                ", introduction=" + introduction +
                ", orgId=" + orgId +
                ", state=" + state +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
