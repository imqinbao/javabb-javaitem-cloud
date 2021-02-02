package cn.javabb.common.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.javabb.common.constant.ConsVal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限获取工具类
 */
public class SecurityUtils {
    /**
     * 获取用户
     */
    public static String getUsername() {
        return ServletUtils.getRequest().getHeader(ConsVal.DETAILS_USERNAME);
    }

    /**
     * 获取用户ID
     */
    public static Integer getUserId() {
        return Convert.toInt(ServletUtils.getRequest().getHeader(ConsVal.DETAILS_USER_ID));
    }

    /**
     * 获取请求token
     */
    public static String getToken() {
        return getToken(ServletUtils.getRequest());
    }

    /**
     * 根据request获取请求token
     */
    public static String getToken(HttpServletRequest request) {
        String token = ServletUtils.getRequest().getHeader(ConsVal.HEADER);
        if (StrUtil.isNotEmpty(token) && token.startsWith(ConsVal.TOKEN_PREFIX)) {
            token = token.replace(ConsVal.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
