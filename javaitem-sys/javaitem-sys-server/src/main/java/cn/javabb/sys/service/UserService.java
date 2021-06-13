package cn.javabb.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.javabb.common.exception.BizException;
import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.api.dto.UserDTO;
import cn.javabb.sys.entity.Menu;
import cn.javabb.sys.entity.Role;
import cn.javabb.sys.entity.User;
import cn.javabb.sys.entity.UserRole;
import cn.javabb.sys.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jarvis.cache.annotation.Cache;
import com.jarvis.cache.annotation.CacheDelete;
import com.jarvis.cache.annotation.CacheDeleteKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //查询用户角色
        selectUserRoles(records);
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
     * 批量查询用户的角色
     */
    private void selectUserRoles(List<User> users) {
        if (users != null && users.size() > 0) {
            List<Integer> userIds = users.stream().map(User::getUserId).collect(Collectors.toList());
            List<Role> userRoles = userRoleService.getUserRole(userIds);
            for (User user : users) {
                List<Role> roles = userRoles.stream().filter(d -> user.getUserId().equals(d.getUserId())).collect(Collectors.toList());
                user.setRoles(roles);
            }
        }
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

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Transactional
    public boolean saveUser(User user) {
        if (user.getUsername() != null && baseMapper.selectCount(new QueryWrapper<User>()
                .eq("username", user.getUsername())) > 0) {
            throw new BizException("账号已存在");
        }
        if (user.getPhone() != null && baseMapper.selectCount(new QueryWrapper<User>()
                .eq("phone", user.getPhone())) > 0) {
            throw new BizException("手机号已存在");
        }
        if (user.getEmail() != null && baseMapper.selectCount(new QueryWrapper<User>()
                .eq("email", user.getEmail())) > 0) {
            throw new BizException("邮箱已存在");
        }
        boolean result = baseMapper.insert(user) > 0;
        if (result) {
            addUserRoles(user.getUserId(), user.getRoleIds(), false);
        }
        return result;
    }
    /**
     * 修改用户
     * @param user
     * @return
     */
    @Transactional
    @CacheDelete({@CacheDeleteKey("'getUserById'+#args[0].userId")})
    public boolean updateUser(User user) {
        if (user.getUsername() != null && baseMapper.selectCount(new QueryWrapper<User>()
                .eq("username", user.getUsername()).ne("user_id", user.getUserId())) > 0) {
            throw new BizException("账号已存在");
        }
        if (user.getPhone() != null && baseMapper.selectCount(new QueryWrapper<User>()
                .eq("phone", user.getPhone()).ne("user_id", user.getUserId())) > 0) {
            throw new BizException("手机号已存在");
        }
        if (user.getEmail() != null && baseMapper.selectCount(new QueryWrapper<User>()
                .eq("email", user.getEmail()).ne("user_id", user.getUserId())) > 0) {
            throw new BizException("邮箱已存在");
        }
        boolean result = baseMapper.updateById(user) > 0;
        if (result) {
            addUserRoles(user.getUserId(), user.getRoleIds(), true);
        }
        return result;
    }
    /**
     * 添加用户角色
     */
    private void addUserRoles(Integer userId, List<Integer> roleIds, boolean deleteOld) {
        if (deleteOld) {
            userRoleService.remove(new UpdateWrapper<UserRole>().eq("user_id", userId));
        }
        if (!userRoleService.insertBatch(userId, roleIds)) {
            throw new BizException("操作失败");
        }
    }

    /**
     * 密码比较
     * @param dbPsw
     * @param inputPsw
     * @return
     */
    public boolean comparePsw(String dbPsw, String inputPsw) {
        return dbPsw != null && new BCryptPasswordEncoder().matches(inputPsw, dbPsw);
    }

    @Cache(key="'getUserById'+#args[0]",expire = 30 * 60)
    public User getUserById(Integer userId) {
        return baseMapper.selectById(userId);
    }


    public static void main(String[] args) {
        User user = new User().setUserId(1).setUsername("admin");
        UserService userService = new UserService();
        System.out.println(userService.userToUserDTO(user));
    }
}
