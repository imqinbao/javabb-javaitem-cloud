package cn.javabb.sys.service;

import cn.javabb.sys.entity.User;
import cn.javabb.sys.mapper.UserMapper;
import cn.javabb.common.web.domain.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Javabb generator
 * @since 2021-01-22 21:23:46
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public PageResult<User> listPage(PageParam<User> page) {
        List<User> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<User> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}
