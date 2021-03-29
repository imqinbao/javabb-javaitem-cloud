package cn.javabb.job.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.job.entity.SysJobLog;
import cn.javabb.job.mapper.SysJobLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务调度日志表 服务实现类
 * </p>
 *
 * @author Javabb
 * @since 2021-03-09 10:08:18
 */
@Service
public class SysJobLogService extends ServiceImpl<SysJobLogMapper, SysJobLog> {

    public PageResult<SysJobLog> listPage(PageParam<SysJobLog> page) {
        List<SysJobLog> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<SysJobLog> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

    /**
     * 清空任务日志
     */
    public void cleanJobLog() {
        baseMapper.cleanJobLog();
    }
}
