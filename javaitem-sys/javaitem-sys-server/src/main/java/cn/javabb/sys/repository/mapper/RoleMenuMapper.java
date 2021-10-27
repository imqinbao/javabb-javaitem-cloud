package cn.javabb.sys.repository.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.repository.dataobject.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 角色权限 Mapper 接口
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 分页查询
     */
    List<RoleMenu> listPage(@Param("page") PageParam<RoleMenu> page);

    /**
     * 查询全部
     */
    List<RoleMenu> listAll(@Param("page") Map<String, Object> page);

}
