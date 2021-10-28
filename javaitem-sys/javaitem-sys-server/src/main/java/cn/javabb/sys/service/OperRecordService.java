package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.repository.dataobject.OperLogDO;
import cn.javabb.sys.repository.mapper.OperLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Service
public class OperRecordService extends ServiceImpl<OperLogMapper, OperLogDO> {

    public PageResult<OperLogDO> listPage(PageParam<OperLogDO> page) {
        List<OperLogDO> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<OperLogDO> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}
