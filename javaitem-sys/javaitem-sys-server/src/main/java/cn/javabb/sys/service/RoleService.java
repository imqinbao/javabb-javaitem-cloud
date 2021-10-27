package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.repository.dataobject.RoleDO;
import cn.javabb.sys.repository.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, RoleDO> {

    public PageResult<RoleDO> listPage(PageParam<RoleDO> page) {
        List<RoleDO> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<RoleDO> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}
