package cn.javabb.sys.service;

import cn.javabb.sys.repository.dataobject.OrgDO;
import cn.javabb.sys.repository.mapper.OrgMapper;
import cn.javabb.common.web.domain.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Service
public class OrgService extends ServiceImpl<OrgMapper, OrgDO> {

    public PageResult<OrgDO> listPage(PageParam<OrgDO> page) {
        List<OrgDO> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<OrgDO> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}
