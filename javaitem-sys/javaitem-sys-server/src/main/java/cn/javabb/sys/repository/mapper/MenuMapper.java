package cn.javabb.sys.repository.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.repository.dataobject.MenuDO;
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
public interface MenuMapper extends BaseMapper<MenuDO> {

    /**
     * 分页查询
     */
    List<MenuDO> listPage(@Param("page") PageParam<MenuDO> page);

    /**
     * 查询全部
     */
    List<MenuDO> listAll(@Param("page") Map<String, Object> page);

    /**
     * 查询用户的权限目录
     */
    List<MenuDO> listByUserId(@Param("userId") String userId, @Param("menuType")Integer menuType);
}
