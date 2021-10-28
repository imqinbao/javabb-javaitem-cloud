package cn.javabb.sys.repository.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.repository.dataobject.RoleMenuDO;
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
public interface RoleMenuMapper extends BaseMapper<RoleMenuDO> {

    /**
     * 分页查询
     */
    List<RoleMenuDO> listPage(@Param("page") PageParam<RoleMenuDO> page);

    /**
     * 查询全部
     */
    List<RoleMenuDO> listAll(@Param("page") Map<String, Object> page);

}
