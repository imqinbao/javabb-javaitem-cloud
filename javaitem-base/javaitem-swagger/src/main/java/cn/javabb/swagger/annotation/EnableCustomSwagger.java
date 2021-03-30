package cn.javabb.swagger.annotation;

import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

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
@Import({Swagger2DocumentationConfiguration.class})
public @interface EnableCustomSwagger {
}
