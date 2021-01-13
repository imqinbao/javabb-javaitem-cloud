package cn.javabb.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/13 22:56
 */
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
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
                .title("API文档")
                .description("")
                .version("0.1")
                .termsOfServiceUrl("")
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
