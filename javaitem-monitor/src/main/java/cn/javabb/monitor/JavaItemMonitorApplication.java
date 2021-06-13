package cn.javabb.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/07 17:43
 */
@EnableAdminServer
@SpringCloudApplication
public class JavaItemMonitorApplication {

    public static void main(String[] args) {
            SpringApplication.run(JavaItemMonitorApplication.class, args);
            System.out.println("====JavaItem服务监控启动成功====");

    }

}
