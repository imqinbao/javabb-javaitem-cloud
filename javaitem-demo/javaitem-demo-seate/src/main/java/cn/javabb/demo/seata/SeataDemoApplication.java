package cn.javabb.demo.seata;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/29 10:00
 */
@Slf4j
@MapperScan("cn.javabb.demo.seata.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SeataDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataDemoApplication.class, args);
        log.info("打开浏览器:http://localhost:9003/doc.html");
    }
}
