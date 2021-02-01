package cn.javabb.sys.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 角色 Mapper 接口
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 分页查询
     */
    List<Role> listPage(@Param("page") PageParam<Role> page);

    /**
     * 查询全部
     */
    List<Role> listAll(@Param("page") Map<String, Object> page);

}
