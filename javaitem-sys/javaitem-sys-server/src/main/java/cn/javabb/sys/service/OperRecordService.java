package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.repository.dataobject.OperRecord;
import cn.javabb.sys.repository.mapper.OperRecordMapper;
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
public class OperRecordService extends ServiceImpl<OperRecordMapper, OperRecord> {

    public PageResult<OperRecord> listPage(PageParam<OperRecord> page) {
        List<OperRecord> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<OperRecord> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}
