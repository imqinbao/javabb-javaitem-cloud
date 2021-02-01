package cn.javabb.sys.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.entity.DictionaryData;
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
public interface DictionaryDataMapper extends BaseMapper<DictionaryData> {

    /**
     * 分页查询
     */
    List<DictionaryData> listPage(@Param("page") PageParam<DictionaryData> page);

    /**
     * 查询全部
     */
    List<DictionaryData> listAll(@Param("page") Map<String, Object> page);

}
