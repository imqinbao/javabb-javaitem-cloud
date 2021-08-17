package cn.javabb.sys;

import cn.javabb.security.annotation.EnableCustomConfig;
import cn.javabb.security.annotation.EnableCustomFeignClients;
import cn.javabb.swagger.annotation.EnableCustomSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/22 23:31
 */
@EnableCustomConfig
@EnableCustomSwagger
@EnableCustomFeignClients
@SpringBootApplication
public class JavaItemSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaItemSysApplication.class, args);
    }

}
