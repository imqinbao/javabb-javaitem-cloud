package cn.javabb.demo.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/29 10:00
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SeataDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataDemoApplication.class, args);
    }
}
