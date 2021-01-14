package cn.javabb.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/13 22:56
 */
@Configuration
public class SwaggerConfig {

    @Bean
    @Order(value = 1)
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        return docket
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.javabb"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("JavaItem项目接口文档")
                .description("JavaItem项目接口文档")
                .version("1.0")
                .termsOfServiceUrl("http://www.javaitem.com")
                .contact(new Contact("javabb", "javabb.cn", "imqinbao@javabb.cn"))
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(new ApiKey("Authorization", "Authorization", "header"));
    }

    private List<SecurityContext> securityContexts() {
        AuthorizationScope[] scopes = {new AuthorizationScope("global", "accessEverything")};
        List<SecurityReference> references = Collections.singletonList(new SecurityReference("Authorization", scopes));
        return Collections.singletonList(SecurityContext.builder()
                .securityReferences(references)
                .forPaths(PathSelectors.any())
                .build());
    }
}
