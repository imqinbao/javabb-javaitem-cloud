package cn.javabb.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.dto.UserDTO;
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
 * @since 2021-02-01 20:14:50
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

    /**
     * User 转换成 UserDTO
     * @param user
     * @return UserDTO
     */
    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(user,userDTO);
        return userDTO;
    }
}
