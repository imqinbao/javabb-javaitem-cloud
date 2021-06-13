package cn.javabb.cache.autoload;

import com.jarvis.cache.ICacheManager;
import com.jarvis.cache.serializer.ISerializer;
import com.jarvis.cache.serializer.JdkSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2019/10/24 22:03
 */
@Configuration
public class AutoLoadCacheConfig {

    @Bean
    public ICacheManager redisCacheManage(RedisConnectionFactory factory){

        return new CustomRedisCacheManager(factory,new JdkSerializer());
    }

    @Bean
    public ISerializer<Object> autoloadCacheSerializer() {
        return new JdkSerializer();
    }
}
