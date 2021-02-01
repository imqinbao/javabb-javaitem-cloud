package cn.javabb.sys.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 菜单 Mapper 接口
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 分页查询
     */
    List<Menu> listPage(@Param("page") PageParam<Menu> page);

    /**
     * 查询全部
     */
    List<Menu> listAll(@Param("page") Map<String, Object> page);

}
