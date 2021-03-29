package cn.javabb.job;

import cn.javabb.security.annotation.EnableCustomConfig;
import cn.javabb.security.annotation.EnableCustomFeignClients;
import cn.javabb.swagger.annotation.EnableCustomSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/09 09:38
 */
@EnableCustomConfig
@EnableCustomSwagger
@EnableCustomFeignClients
@SpringCloudApplication
public class JavaItemJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaItemJobApplication.class, args);
        System.out.println("=====JavaItem-Job应用启动=====");
    }
}
