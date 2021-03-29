package cn.javabb.job.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.job.entity.SysJobLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 定时任务调度日志表 Mapper 接口
 *
 * @author Javabb
 * @since 2021-03-09 10:08:18
 */
public interface SysJobLogMapper extends BaseMapper<SysJobLog> {

    /**
     * 分页查询
     */
    List<SysJobLog> listPage(@Param("page") PageParam<SysJobLog> page);

    /**
     * 查询全部
     */
    List<SysJobLog> listAll(@Param("page") Map<String, Object> page);

    /**
     * 清空日志
     */
    void cleanJobLog();
}
