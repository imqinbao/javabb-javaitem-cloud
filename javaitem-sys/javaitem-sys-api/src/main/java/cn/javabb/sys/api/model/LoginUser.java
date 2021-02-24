package cn.javabb.sys.api.model;

import cn.javabb.sys.api.dto.UserDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/28 23:10
 */
@Data
@Accessors(chain = true)
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户唯一标识
     */
    private String token;
    /**
     * 用户名id
     */
    private Integer userid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 登录时间
     */
    private Long loginTime;
    /**
     * 过期时间
     */
    private Long expireTime;
    /**
     * 登录IP地址
     */
    private String ipaddr;
    /**
     * 权限列表
     */
    private Set<Integer> permissions;
    /**
     * 角色列表
     */
    private Set<Integer> roles;

    private UserDTO userInfo;
}
