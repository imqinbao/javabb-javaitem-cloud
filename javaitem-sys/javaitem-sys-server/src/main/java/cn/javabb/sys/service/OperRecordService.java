package cn.javabb.sys.service;

import cn.javabb.sys.entity.OperRecord;
import cn.javabb.sys.mapper.OperRecordMapper;
import cn.javabb.common.web.domain.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author Javabb Generator
 * @since 2021-01-31 23:52:16
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
