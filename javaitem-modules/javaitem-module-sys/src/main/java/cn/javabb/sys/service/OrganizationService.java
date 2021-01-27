package cn.javabb.sys.service;

import cn.javabb.sys.entity.Organization;
import cn.javabb.sys.mapper.OrganizationMapper;
import cn.javabb.common.web.domain.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author Javabb generator
 * @since 2021-01-22 21:23:46
 */
@Service
public class OrganizationService extends ServiceImpl<OrganizationMapper, Organization> {

    public PageResult<Organization> listPage(PageParam<Organization> page) {
        List<Organization> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<Organization> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}
