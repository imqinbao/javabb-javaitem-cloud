package cn.javabb.sys.api.fallback;

import cn.javabb.common.model.R;
import cn.javabb.sys.api.RemoteLogService;
import cn.javabb.sys.api.dto.LoginLogDTO;
import cn.javabb.sys.api.dto.OperLogDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/02/23 22:05
 */
@Slf4j
@Component
public class RemoteLogFallback implements FallbackFactory<RemoteLogService> {
    @Override
    public RemoteLogService create(Throwable throwable) {
        return new RemoteLogService() {
            @Override
            public R<Boolean> saveOperLog(OperLogDTO operDTO) {
                return R.fail("日志服务调用失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> saveLoginLog(LoginLogDTO loginDTO) {
                return R.fail("日志服务调用失败:" + throwable.getMessage());
            }
        };
    }
}
