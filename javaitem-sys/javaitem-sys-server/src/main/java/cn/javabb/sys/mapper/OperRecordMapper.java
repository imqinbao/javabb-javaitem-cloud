package cn.javabb.sys.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.entity.OperRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 操作日志 Mapper 接口
 *
 * @author Javabb Generator
 * @since 2021-01-31 23:52:16
 */
public interface OperRecordMapper extends BaseMapper<OperRecord> {

    /**
     * 分页查询
     */
    List<OperRecord> listPage(@Param("page") PageParam<OperRecord> page);

    /**
     * 查询全部
     */
    List<OperRecord> listAll(@Param("page") Map<String, Object> page);

}
