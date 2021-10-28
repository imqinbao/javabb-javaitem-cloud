package cn.javabb.sys.api.dto;

import cn.javabb.sys.api.model.LoginUser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/28 23:11
 */
@Data
@Accessors(chain = true)
public class UserDTO{
    private String userId;

    private String username;

    private String password;

    private String nickname;

    private String avatar;

    private String sex;

    private String phone;

    private String email;

    private String emailVerified;

    private String trueName;

    private String idCard;

    private Date birthday;

    private String introduction;

    private String orgId;

    private String state;

    private String deleted;

    private Date createTime;

    private Date updateTime;

}
