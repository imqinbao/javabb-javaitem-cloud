package cn.javabb.sys.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 用户 Mapper 接口
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询
     */
    List<User> listPage(@Param("page") PageParam<User> page);

    /**
     * 查询全部
     */
    List<User> listAll(@Param("page") Map<String, Object> page);

}
