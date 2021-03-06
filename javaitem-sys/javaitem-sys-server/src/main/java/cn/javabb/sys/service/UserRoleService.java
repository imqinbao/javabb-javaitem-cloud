package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.repository.dataobject.RoleDO;
import cn.javabb.sys.repository.dataobject.UserRoleDO;
import cn.javabb.sys.repository.mapper.UserRoleMapper;
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
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRoleDO> {

    public PageResult<UserRoleDO> listPage(PageParam<UserRoleDO> page) {
        List<UserRoleDO> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<UserRoleDO> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    public List<RoleDO> getUserRole(String userId) {
        return baseMapper.listByUserId(userId);
    }
    /**
     * 获取用户角色
     * @param userIds
     * @return
     */
    public List<RoleDO> getUserRole(List<String> userIds) {
        return baseMapper.listByUserIds(userIds);
    }
    /**
     * 获取角色ID
     * @param userId
     * @return
     */
    public Set<String> getUserRoleIds(String userId) {
        List<UserRoleDO> list = baseMapper.selectList(new QueryWrapper<>(new UserRoleDO().setUserId(userId)));
        Set<String> roleIds = list.stream()
                .map(UserRoleDO::getRoleId)
                .collect(Collectors.toSet());
        return roleIds;
    }

    /**
     * 批量保存用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    public boolean insertBatch(String userId, List<String> roleIds) {
        List<UserRoleDO> list = new ArrayList<>();
        for (String roleId : roleIds) {
            UserRoleDO userRole = new UserRoleDO();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            list.add(userRole);
        }
        return this.saveBatch(list);

    }
}
