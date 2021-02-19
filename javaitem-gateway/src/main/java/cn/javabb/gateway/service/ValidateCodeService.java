package cn.javabb.gateway.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.javabb.cache.redis.service.RedisService;
import cn.javabb.common.constant.ConsVal;
import cn.javabb.common.exception.BaseException;
import cn.javabb.common.web.domain.AjaxResult;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/02/15 15:40
 */
@Component
public class ValidateCodeService {
    @Autowired
    private RedisService redisService;

    /**
     * 创建验证码
     * @return
     */
    public AjaxResult createCode() {
        String uuid = IdUtil.simpleUUID();
        String verifyKey = ConsVal.CAPTCHA_CODE_KEY + uuid;

        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        //代码
        String code = specCaptcha.text().toLowerCase();
        String imageBase64 = specCaptcha.toBase64();
        // 存入缓存
        redisService.setCacheObject(verifyKey, code, ConsVal.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        AjaxResult ajaxResult = AjaxResult.ok();
        ajaxResult.put("uuid", uuid);
        ajaxResult.put("image", imageBase64);
        //这里直接返回给前台,自动填充,实际项目去掉这个
        ajaxResult.put("text", code);
        return ajaxResult;

    }

    /**
     * 校验验证码
     * @param uuid
     * @param code
     */
    public void validateCode(String code,String uuid) {
        if (StrUtil.isBlank(code)) {
            throw new BaseException("验证码不能为空");
        }
        if (StrUtil.isBlank(uuid)) {
            throw new BaseException("验证码已失效");
        }
        String verifyKey = ConsVal.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisService.getCacheObject(verifyKey);
        redisService.deleteObject(verifyKey);
        if (!code.equalsIgnoreCase(captcha)) {
            throw new BaseException("验证码错误");
        }

    }
}
