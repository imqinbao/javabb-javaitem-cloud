package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.entity.Role;
import cn.javabb.sys.mapper.RoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2021-01-31 23:52:16
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    public PageResult<Role> listPage(PageParam<Role> page) {
        List<Role> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<Role> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

    public List<Role> list(Role role){
        List<Role> list = this.list(new QueryWrapper<>(role));
        return list;
    }

}
