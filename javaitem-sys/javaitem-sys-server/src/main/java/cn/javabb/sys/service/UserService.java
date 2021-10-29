package cn.javabb.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.javabb.common.exception.BizException;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.common.web.page.PageResultUtil;
import cn.javabb.sys.api.dto.UserDTO;
import cn.javabb.sys.model.dto.UserBaseDTO;
import cn.javabb.sys.model.qry.UserQry;
import cn.javabb.sys.repository.dataobject.MenuDO;
import cn.javabb.sys.repository.dataobject.RoleDO;
import cn.javabb.sys.repository.dataobject.UserDO;
import cn.javabb.sys.repository.dataobject.UserRoleDO;
import cn.javabb.sys.repository.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
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

    public PageResult<UserBaseDTO> listPage(UserQry userQry) {
        PageHelper.startPage(userQry.getPageNo(), userQry.getPageSize());
        List<UserBaseDTO> records = baseMapper.listPage(userQry);
        selectUserRoles(records);
        PageResult<UserBaseDTO> pageResult = new PageResult();
        PageResultUtil.setPageResult(records,pageResult);
        //查询用户角色
        return pageResult;
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
    public UserBaseDTO getByUsername(String username) {
        UserDO userDO = baseMapper.selectOne(new QueryWrapper<UserDO>().eq("username", username));
        UserBaseDTO userBase = new UserBaseDTO();
        BeanUtil.copyProperties(userDO, userBase);
        return userBase;
    }
    /**
     * 批量查询用户的角色
     */
    private void selectUserRoles(List<UserBaseDTO> userList) {
        if (CollUtil.isNotEmpty(userList)) {
            List<String> userIds = userList.stream().map(UserDO::getId).collect(Collectors.toList());
            List<RoleDO> userRoles = userRoleService.getUserRole(userIds);
            for (UserBaseDTO user : userList) {
                List<RoleDO> roles = userRoles.stream().filter(d -> user.getId().equals(d.getUserId())).collect(Collectors.toList());
                user.setRoles(roles);
            }
        }
    }

    /**
     * User 转换成 UserDTO
     *
     * @param userBase
     * @return UserDTO
     */
    public UserDTO userToUserDTO(UserBaseDTO userBase) {
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(userBase, userDTO);
        return userDTO;
    }

    /**
     * 获取用户的详细信息
     * @param userId
     * @return
     */
    public UserBaseDTO getUserBaseById(String userId) {
        UserBaseDTO retUser = new UserBaseDTO();
        //获取用户
        UserDO userDO = this.getById(userId);
        if (ObjectUtil.isEmpty(userDO)) {
            return null;
        }
        BeanUtil.copyProperties(userDO,retUser);
        //获取角色
        List<RoleDO> roles = userRoleService.getUserRole(userId);
        //获取权限
        List<MenuDO> menus = menuService.getUserMenu(userId, null);
        List<MenuDO> authorities = menus.stream().filter(m -> StrUtil.isNotBlank(m.getAuthority())).collect(Collectors.toList());

        retUser.setRoles(roles);
        retUser.setAuthorities(authorities);
        return retUser;
    }

    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    @Transactional
    public boolean saveUser(UserBaseDTO userInfo) {
        if (userInfo.getUsername() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("username", userInfo.getUsername())) > 0) {
            throw new BizException("账号已存在");
        }
        if (userInfo.getPhone() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("phone", userInfo.getPhone())) > 0) {
            throw new BizException("手机号已存在");
        }
        if (userInfo.getEmail() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("email", userInfo.getEmail())) > 0) {
            throw new BizException("邮箱已存在");
        }
        UserDO userDO = new UserDO();
        BeanUtil.copyProperties(userInfo, userDO);
        boolean result = baseMapper.insert(userDO) > 0;
        if (result) {
            addUserRoles(userDO.getId(), userInfo.getRoleIds(), false);
        }
        return result;
    }
    /**
     * 修改用户
     * @param userInfo
     * @return
     */
    @Transactional
    @CacheDelete({@CacheDeleteKey("'getUserById'+#args[0].userId")})
    public boolean updateUser(UserBaseDTO userInfo) {
        if (userInfo.getUsername() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("username", userInfo.getUsername()).ne("user_id", userInfo.getId())) > 0) {
            throw new BizException("账号已存在");
        }
        if (userInfo.getPhone() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("phone", userInfo.getPhone()).ne("user_id", userInfo.getId())) > 0) {
            throw new BizException("手机号已存在");
        }
        if (userInfo.getEmail() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("email", userInfo.getEmail()).ne("user_id", userInfo.getId())) > 0) {
            throw new BizException("邮箱已存在");
        }
        UserDO userDO = new UserDO();
        BeanUtil.copyProperties(userInfo,userDO);
        boolean result = baseMapper.updateById(userDO) > 0;
        if (result) {
            addUserRoles(userDO.getId(), userInfo.getRoleIds(), true);
        }
        return result;
    }
    /**
     * 添加用户角色
     */
    private void addUserRoles(String userId, List<String> roleIds, boolean deleteOld) {
        if (deleteOld) {
            userRoleService.remove(new UpdateWrapper<UserRoleDO>().eq("user_id", userId));
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

}
