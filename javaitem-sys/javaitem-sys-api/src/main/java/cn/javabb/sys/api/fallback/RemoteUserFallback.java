package cn.javabb.sys.api.fallback;

import cn.javabb.common.model.R;
import cn.javabb.sys.api.RemoteUserService;
import cn.javabb.sys.api.model.LoginUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/28 23:25
 */
@Slf4j
@Component
public class RemoteUserFallback implements FallbackFactory<RemoteUserService> {
    @Override
    public RemoteUserService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService(){
            @Override
            public R<LoginUser> getUserInfo(String username){
                return R.fail("用户服务调用失败:" + throwable.getMessage());
            }
        };
    }
}
