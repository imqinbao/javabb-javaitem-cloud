package cn.javabb.sys.service;

import cn.javabb.sys.entity.LoginRecord;
import cn.javabb.sys.mapper.LoginRecordMapper;
import cn.javabb.common.web.domain.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author Javabb Generator
 * @since 2021-01-31 23:52:16
 */
@Service
public class LoginRecordService extends ServiceImpl<LoginRecordMapper, LoginRecord> {

    public PageResult<LoginRecord> listPage(PageParam<LoginRecord> page) {
        List<LoginRecord> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<LoginRecord> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}
