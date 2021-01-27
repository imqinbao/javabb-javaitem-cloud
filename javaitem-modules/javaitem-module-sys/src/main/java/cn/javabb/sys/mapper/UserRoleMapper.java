package cn.javabb.sys.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 用户角色 Mapper 接口
 *
 * @author Javabb generator
 * @since 2021-01-22 21:23:46
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 分页查询
     */
    List<UserRole> listPage(@Param("page") PageParam<UserRole> page);

    /**
     * 查询全部
     */
    List<UserRole> listAll(@Param("page") Map<String, Object> page);

}
