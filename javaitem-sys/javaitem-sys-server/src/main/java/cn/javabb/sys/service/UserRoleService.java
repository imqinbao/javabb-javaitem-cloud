package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.entity.Role;
import cn.javabb.sys.entity.UserRole;
import cn.javabb.sys.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {

    public PageResult<UserRole> listPage(PageParam<UserRole> page) {
        List<UserRole> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<UserRole> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    public List<Role> getUserRole(Integer userId) {
        return baseMapper.listByUserId(userId);
    }
    /**
     * 获取用户角色
     * @param userIds
     * @return
     */
    public List<Role> getUserRole(List<Integer> userIds) {
        return baseMapper.listByUserIds(userIds);
    }
    /**
     * 获取角色ID
     * @param userId
     * @return
     */
    public Set<Integer> getUserRoleIds(Integer userId) {
        List<UserRole> list = baseMapper.selectList(new QueryWrapper<>(new UserRole().setUserId(userId)));
        Set<Integer> roleIds = list.stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toSet());
        return roleIds;
    }

    /**
     * 批量保存用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    public boolean insertBatch(Integer userId, List<Integer> roleIds) {
        List<UserRole> list = new ArrayList<>();
        for (Integer roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            list.add(userRole);
        }
        return this.saveBatch(list);

    }
}
