package cn.javabb.knife4j.annotation;

import cn.javabb.knife4j.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/27 21:50
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
public @interface EnableCustomSwagger {
}
