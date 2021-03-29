package cn.javabb.demo.seata.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/29 10:27
 */
@Data
@TableName("account")
public class Account {
    private Long id;
    /**
     * 余额
     */
    private Double balance;

    private Date lastUpdateTime;

}
