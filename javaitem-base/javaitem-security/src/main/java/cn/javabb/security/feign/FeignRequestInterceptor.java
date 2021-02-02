package cn.javabb.security.feign;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.javabb.common.constant.ConsVal;
import cn.javabb.common.util.ServletUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @desc: feign 请求拦截器
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/27 21:32
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest httpServletRequest = ServletUtils.getRequest();
        if (ObjectUtil.isNotEmpty(httpServletRequest)) {
            Map<String, String> headers = ServletUtils.getHeaders(httpServletRequest);
            // 传递用户信息请求头，防止丢失
            String userId = headers.get(ConsVal.DETAILS_USER_ID);
            if (StrUtil.isNotEmpty(userId)) {
                requestTemplate.header(ConsVal.DETAILS_USER_ID, userId);
            }
            String userName = headers.get(ConsVal.DETAILS_USERNAME);
            if (StrUtil.isNotEmpty(userName)) {
                requestTemplate.header(ConsVal.DETAILS_USERNAME, userName);
            }
            String authentication = headers.get(ConsVal.AUTHORIZATION_HEADER);
            if (StrUtil.isNotEmpty(authentication)) {
                requestTemplate.header(ConsVal.AUTHORIZATION_HEADER, authentication);
            }
        }
    }
}