package cn.javabb.sys.repository.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.repository.dataobject.OperLogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 操作日志 Mapper 接口
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
public interface OperLogMapper extends BaseMapper<OperLogDO> {

    /**
     * 分页查询
     */
    List<OperLogDO> listPage(@Param("page") PageParam<OperLogDO> page);

    /**
     * 查询全部
     */
    List<OperLogDO> listAll(@Param("page") Map<String, Object> page);

}
