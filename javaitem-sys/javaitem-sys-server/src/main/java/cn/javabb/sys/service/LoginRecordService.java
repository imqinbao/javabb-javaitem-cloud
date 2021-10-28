package cn.javabb.sys.service;

import cn.javabb.sys.repository.dataobject.LoginLogDO;
import cn.javabb.sys.repository.mapper.LoginLogMapper;
import cn.javabb.common.web.domain.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Service
public class LoginRecordService extends ServiceImpl<LoginLogMapper, LoginLogDO> {

    public PageResult<LoginLogDO> listPage(PageParam<LoginLogDO> page) {
        List<LoginLogDO> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<LoginLogDO> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}
