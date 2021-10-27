package cn.javabb.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.javabb.common.exception.BizException;
import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.api.dto.UserDTO;
import cn.javabb.sys.repository.dataobject.Menu;
import cn.javabb.sys.repository.dataobject.RoleDO;
import cn.javabb.sys.repository.dataobject.UserDO;
import cn.javabb.sys.repository.dataobject.UserRole;
import cn.javabb.sys.repository.mapper.UserMapper;
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
public class UserService extends ServiceImpl<UserMapper, UserDO> {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private MenuService menuService;

    public PageResult<UserDO> listPage(PageParam<UserDO> page) {
        List<UserDO> records = baseMapper.listPage(page);
        //查询用户角色
        selectUserRoles(records);
        return new PageResult<>(records, page.getTotal());
    }

    public List<UserDO> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    public UserDO getByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<UserDO>().eq("username", username));
    }
    /**
     * 批量查询用户的角色
     */
    private void selectUserRoles(List<UserDO> userDOS) {
        if (userDOS != null && userDOS.size() > 0) {
            List<String> userIds = userDOS.stream().map(UserDO::getId).collect(Collectors.toList());
            List<RoleDO> userRoles = userRoleService.getUserRole(userIds);
            for (UserDO userDO : userDOS) {
                List<RoleDO> roles = userRoles.stream().filter(d -> userDO.getId().equals(d.getUserId())).collect(Collectors.toList());
                userDO.setRoles(roles);
            }
        }
    }

    /**
     * User 转换成 UserDTO
     *
     * @param userDO
     * @return UserDTO
     */
    public UserDTO userToUserDTO(UserDO userDO) {
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(userDO, userDTO);
        return userDTO;
    }

    /**
     * 获取用户的详细信息
     * @param userId
     * @return
     */
    public UserDO getFullUserInfo(String userId) {
        //获取用户
        UserDO userDO = this.getById(userId);
        //获取角色
        List<RoleDO> roles = userRoleService.getUserRole(userId);
        //获取权限
        List<Menu> menus = menuService.getUserMenu(userId, null);
        List<Menu> authorities = menus.stream().filter(m -> StrUtil.isNotBlank(m.getAuthority())).collect(Collectors.toList());

        userDO.setRoles(roles);
        userDO.setAuthorities(authorities);
        return userDO;
    }

    /**
     * 添加用户
     * @param userDO
     * @return
     */
    @Transactional
    public boolean saveUser(UserDO userDO) {
        if (userDO.getUsername() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("username", userDO.getUsername())) > 0) {
            throw new BizException("账号已存在");
        }
        if (userDO.getPhone() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("phone", userDO.getPhone())) > 0) {
            throw new BizException("手机号已存在");
        }
        if (userDO.getEmail() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("email", userDO.getEmail())) > 0) {
            throw new BizException("邮箱已存在");
        }
        boolean result = baseMapper.insert(userDO) > 0;
        if (result) {
            addUserRoles(userDO.getId(), userDO.getRoleIds(), false);
        }
        return result;
    }
    /**
     * 修改用户
     * @param userDO
     * @return
     */
    @Transactional
    @CacheDelete({@CacheDeleteKey("'getUserById'+#args[0].userId")})
    public boolean updateUser(UserDO userDO) {
        if (userDO.getUsername() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("username", userDO.getUsername()).ne("user_id", userDO.getId())) > 0) {
            throw new BizException("账号已存在");
        }
        if (userDO.getPhone() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("phone", userDO.getPhone()).ne("user_id", userDO.getId())) > 0) {
            throw new BizException("手机号已存在");
        }
        if (userDO.getEmail() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("email", userDO.getEmail()).ne("user_id", userDO.getId())) > 0) {
            throw new BizException("邮箱已存在");
        }
        boolean result = baseMapper.updateById(userDO) > 0;
        if (result) {
            addUserRoles(userDO.getId(), userDO.getRoleIds(), true);
        }
        return result;
    }
    /**
     * 添加用户角色
     */
    private void addUserRoles(String userId, List<String> roleIds, boolean deleteOld) {
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
    public UserDO getUserById(Integer userId) {
        return baseMapper.selectById(userId);
    }


    public static void main(String[] args) {
        UserDO userDO = new UserDO().setId("1").setUsername("admin");
        UserService userService = new UserService();
        System.out.println(userService.userToUserDTO(userDO));
    }
}
