package cn.javabb.sys.service;

import cn.javabb.sys.entity.RolePermission;
import cn.javabb.sys.mapper.RolePermissionMapper;
import cn.javabb.common.web.domain.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author Javabb Generator
 * @since 2021-01-31 23:52:16
 */
@Service
public class RolePermissionService extends ServiceImpl<RolePermissionMapper, RolePermission> {

    public PageResult<RolePermission> listPage(PageParam<RolePermission> page) {
        List<RolePermission> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<RolePermission> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}
