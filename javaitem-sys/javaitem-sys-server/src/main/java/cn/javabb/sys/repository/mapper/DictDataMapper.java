package cn.javabb.sys.repository.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.repository.dataobject.DictDataDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 字典项 Mapper 接口
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
public interface DictDataMapper extends BaseMapper<DictDataDO> {

    /**
     * 分页查询
     */
    List<DictDataDO> listPage(@Param("page") PageParam<DictDataDO> page);

    /**
     * 查询全部
     */
    List<DictDataDO> listAll(@Param("page") Map<String, Object> page);

}
