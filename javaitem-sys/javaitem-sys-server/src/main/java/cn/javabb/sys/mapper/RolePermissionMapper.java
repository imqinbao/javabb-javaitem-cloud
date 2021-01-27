package cn.javabb.sys.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 角色权限 Mapper 接口
 *
 * @author Javabb generator
 * @since 2021-01-22 21:23:46
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 分页查询
     */
    List<RolePermission> listPage(@Param("page") PageParam<RolePermission> page);

    /**
     * 查询全部
     */
    List<RolePermission> listAll(@Param("page") Map<String, Object> page);

}
