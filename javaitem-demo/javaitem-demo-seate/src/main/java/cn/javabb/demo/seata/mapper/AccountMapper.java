package cn.javabb.demo.seata.mapper;

import cn.javabb.demo.seata.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/30 14:10
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
