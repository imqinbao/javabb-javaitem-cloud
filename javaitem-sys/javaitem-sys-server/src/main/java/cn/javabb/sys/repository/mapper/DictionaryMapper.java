package cn.javabb.sys.repository.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.repository.dataobject.Dictionary;
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
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    /**
     * 分页查询
     */
    List<Dictionary> listPage(@Param("page") PageParam<Dictionary> page);

    /**
     * 查询全部
     */
    List<Dictionary> listAll(@Param("page") Map<String, Object> page);

}
