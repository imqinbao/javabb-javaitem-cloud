package cn.javabb.job.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.job.entity.SysJob;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 定时任务调度表 Mapper 接口
 *
 * @author Javabb
 * @since 2021-03-09 10:08:18
 */
public interface SysJobMapper extends BaseMapper<SysJob> {

    /**
     * 分页查询
     */
    List<SysJob> listPage(@Param("page") PageParam<SysJob> page);

    /**
     * 查询全部
     */
    List<SysJob> listAll(@Param("page") Map<String, Object> page);

}
