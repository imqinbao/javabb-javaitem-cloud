package cn.javabb.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.dto.UserDTO;
import cn.javabb.sys.entity.Menu;
import cn.javabb.sys.entity.Role;
import cn.javabb.sys.entity.User;
import cn.javabb.sys.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private MenuService menuService;

    public PageResult<User> listPage(PageParam<User> page) {
        List<User> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<User> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    public User getByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    /**
     * User 转换成 UserDTO
     *
     * @param user
     * @return UserDTO
     */
    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(user, userDTO);
        return userDTO;
    }

    /**
     * 获取用户的详细信息
     * @param userId
     * @return
     */
    public User getFullUserInfo(Integer userId) {
        //获取用户
        User user = this.getById(userId);
        //获取角色
        List<Role> roles = userRoleService.getUserRole(userId);
        //获取权限
        List<Menu> menus = menuService.getUserMenu(userId, null);
        List<Menu> authorities = menus.stream().filter(m -> StrUtil.isNotBlank(m.getAuthority())).collect(Collectors.toList());

        user.setRoles(roles);
        user.setAuthorities(authorities);
        return user;
    }


    public static void main(String[] args) {
        User user = new User().setUserId(1).setUsername("admin");
        UserService userService = new UserService();
        System.out.println(userService.userToUserDTO(user));
    }
}
