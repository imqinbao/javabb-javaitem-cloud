package cn.javabb.sys;

import cn.javabb.knife4j.annotation.EnableCustomSwagger;
import cn.javabb.security.annotation.EnableCustomConfig;
import cn.javabb.security.annotation.EnableCustomFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/22 23:31
 */
@EnableCustomConfig
@EnableCustomSwagger
@EnableCustomFeignClients
@SpringCloudApplication
public class JavaItemSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaItemSysApplication.class, args);
    }

}
