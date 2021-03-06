package cn.javabb.sys.repository.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.repository.dataobject.LoginLogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 登录日志 Mapper 接口
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
public interface LoginLogMapper extends BaseMapper<LoginLogDO> {

    /**
     * 分页查询
     */
    List<LoginLogDO> listPage(@Param("page") PageParam<LoginLogDO> page);

    /**
     * 查询全部
     */
    List<LoginLogDO> listAll(@Param("page") Map<String, Object> page);

}
