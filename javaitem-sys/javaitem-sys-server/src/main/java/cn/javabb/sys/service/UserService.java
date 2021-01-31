package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.entity.User;
import cn.javabb.sys.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Javabb Generator
 * @since 2021-01-31 23:52:16
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

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    public User getByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

}
