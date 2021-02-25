package cn.javabb.sys.api;

import cn.javabb.common.constant.ServiceNameConstants;
import cn.javabb.common.model.R;
import cn.javabb.sys.api.dto.LoginLogDTO;
import cn.javabb.sys.api.dto.OperLogDTO;
import cn.javabb.sys.api.fallback.RemoteLogFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @desc:   日志
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/02/23 23:00
 */
@FeignClient(contextId = "remoteLogService",value = ServiceNameConstants.SERVICE_SYS,fallbackFactory = RemoteLogFallback.class)
public interface RemoteLogService {

    /**
     * 保存操作日志
     */
    @PostMapping("/operRecord")
    R<Boolean> saveOperLog(@RequestBody OperLogDTO operDTO);

    /**
     * 保存访问记录
     */
    @PostMapping("/loginRecord")
    R<Boolean> saveLoginLog(@RequestBody LoginLogDTO loginDTO);
}
