package cn.javabb.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.javabb.cache.redis.service.RedisService;
import cn.javabb.common.constant.ConsVal;
import cn.javabb.common.model.R;
import cn.javabb.common.util.WebUtils;
import cn.javabb.gateway.config.properties.IgnoreWhiteProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.function.Consumer;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/02/02 13:55
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;
    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> sops;
    @Autowired
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        // 跳过不需要验证的路径
        if (WebUtils.matches(url, ignoreWhite.getWhites())) {
            return chain.filter(exchange);
        }
        // 先全部放行
        /*if (true) {
            return chain.filter(exchange);
        }*/
        String token = getToken(exchange.getRequest());
        if (StrUtil.isBlank(token)) {
            return setUnauthorizedResponse(exchange, "令牌不能为空");
        }
        String userStr = sops.get(getTokenKey(token));
        if (StrUtil.isEmpty(userStr)) {
            return setUnauthorizedResponse(exchange, "登录状态已过期");
        }
        JSONObject obj = JSONObject.parseObject(userStr);
        String userid = obj.getString("userid");
        String username = obj.getString("username");
        if (StrUtil.isBlank(userid) || StrUtil.isBlank(username)) {
            return setUnauthorizedResponse(exchange, "令牌验证失败");
        }

        // 设置过期时间
        redisService.expire(getTokenKey(token), ConsVal.TOKEN_EXPIRE_TIME);
        // 设置用户信息到请求
        /*ServerHttpRequest mutableReq = exchange.getRequest().mutate().header(ConsVal.DETAILS_USER_ID, userid)
                .header(ConsVal.DETAILS_USERNAME, username).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        */
        /*新方法*/
        Consumer<HttpHeaders> httpHeaders = httpHeader->{
            httpHeader.set(ConsVal.DETAILS_USER_ID, userid);
            httpHeader.set(ConsVal.DETAILS_USERNAME, username);
        };
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(serverHttpRequest).build();
        return chain.filter(mutableExchange);
    }

    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String msg) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);

        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSON.toJSONBytes(R.fail(HttpStatus.UNAUTHORIZED.value(),msg)));
        }));
    }

    private String getTokenKey(String token) {
        return ConsVal.LOGIN_TOKEN_KEY + token;
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(ConsVal.HEADER);
        if (StrUtil.isNotEmpty(token) && token.startsWith(ConsVal.TOKEN_PREFIX)) {
            token = token.replace(ConsVal.TOKEN_PREFIX, "");
        }
        return token;
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
