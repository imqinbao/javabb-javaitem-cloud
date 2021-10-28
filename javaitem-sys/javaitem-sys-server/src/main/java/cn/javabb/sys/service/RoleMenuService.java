package cn.javabb.sys.service;

import cn.javabb.sys.repository.dataobject.RoleMenuDO;
import cn.javabb.sys.repository.mapper.RoleMenuMapper;
import cn.javabb.common.web.domain.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenuDO> {

    public PageResult<RoleMenuDO> listPage(PageParam<RoleMenuDO> page) {
        List<RoleMenuDO> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<RoleMenuDO> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}
