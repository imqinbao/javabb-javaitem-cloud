package cn.javabb.auth.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.javabb.common.enums.UserStatus;
import cn.javabb.common.exception.BaseException;
import cn.javabb.common.model.R;
import cn.javabb.common.util.SecurityUtils;
import cn.javabb.sys.api.RemoteUserService;
import cn.javabb.sys.dto.UserDTO;
import cn.javabb.sys.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/02/02 21:19
 */
@Component
public class LoginService {

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    public LoginUser login(String username, String password) {
        // 用户名或密码为空 错误
        if (StrUtil.hasEmpty(username, password)) {
            throw new BaseException("用户/密码必须填写");
        }
        // 查询用户信息
        R<LoginUser> userResult = remoteUserService.getUserInfo(username);
        if (R.FAIL == userResult.getCode()) {
            throw new BaseException(userResult.getMsg());
        }
        if (ObjectUtil.isEmpty(userResult) || ObjectUtil.isEmpty(userResult.getData())) {
            throw new BaseException("登录用户：" + username + " 不存在");
        }
        LoginUser userInfo = userResult.getData();
        UserDTO user = userResult.getData().getUserInfo();
        if (UserStatus.DELETED.getCode().equals(user.getDeleted())) {
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
        if (UserStatus.DISABLE.getCode().equals(user.getState())) {
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }
        if (!SecurityUtils.matchesPassword(password, user.getPassword())) {
            throw new BaseException("用户不存在/密码错误");
        }
        return userInfo;
    }

    public void logout(String loginName) {

    }

}
