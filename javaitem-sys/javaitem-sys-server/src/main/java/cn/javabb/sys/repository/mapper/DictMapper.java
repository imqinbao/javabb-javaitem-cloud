package cn.javabb.sys.repository.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.repository.dataobject.DictDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 字典 Mapper 接口
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
public interface DictMapper extends BaseMapper<DictDO> {

    /**
     * 分页查询
     */
    List<DictDO> listPage(@Param("page") PageParam<DictDO> page);

    /**
     * 查询全部
     */
    List<DictDO> listAll(@Param("page") Map<String, Object> page);

}
