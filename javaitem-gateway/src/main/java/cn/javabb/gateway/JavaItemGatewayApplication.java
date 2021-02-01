package cn.javabb.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/26 22:04
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JavaItemGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaItemGatewayApplication.class, args);
        System.out.println("====JavaItem网关启动成功====");

    }
}
