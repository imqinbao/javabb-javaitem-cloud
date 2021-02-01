package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.entity.UserRole;
import cn.javabb.sys.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
     * 获取角色ID
     * @param userId
     * @return
     */
    public Set<Integer> getRoleIds(Integer userId) {
        List<UserRole> list = baseMapper.selectList(new QueryWrapper<>(new UserRole().setUserId(userId)));
        Set<Integer> roleIds = list.stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toSet());
        return roleIds;
    }

}
