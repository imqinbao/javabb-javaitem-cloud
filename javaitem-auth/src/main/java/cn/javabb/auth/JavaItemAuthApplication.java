package cn.javabb.auth;

import cn.javabb.security.annotation.EnableCustomFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/02/02 21:16
 */
@EnableCustomFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JavaItemAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaItemAuthApplication.class, args);
        System.out.println("========认证授权中心启动成功");
    }
}
