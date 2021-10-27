package cn.javabb.sys.repository.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.repository.dataobject.RoleDO;
import cn.javabb.sys.repository.dataobject.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 用户角色 Mapper 接口
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
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
    /**
     * 批量查询用户角色
     */
    List<RoleDO> listByUserIds(@Param("userIds") List<String> userIds);
    /**
     * 查询某个用户的角色
     */
    List<RoleDO> listByUserId(@Param("userId") String userId);
}
