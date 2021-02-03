package cn.javabb.sys.api;

import cn.javabb.common.constant.ServiceNameConstants;
import cn.javabb.common.model.R;
import cn.javabb.sys.fallback.RemoteUserFallback;
import cn.javabb.sys.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/28 22:56
 */
@FeignClient(contextId = "remoteUserService",value = ServiceNameConstants.SERVICE_SYS,fallbackFactory = RemoteUserFallback.class)
public interface RemoteUserService {

    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username);
}
