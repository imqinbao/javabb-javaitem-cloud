package cn.javabb.sys.repository.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.model.dto.UserBaseDTO;
import cn.javabb.sys.model.qry.UserQry;
import cn.javabb.sys.repository.dataobject.UserDO;
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
public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 分页查询
     */
    List<UserBaseDTO> listPage(UserQry userQry);

    /**
     * 查询全部
     */
    List<UserDO> listAll(@Param("page") Map<String, Object> page);

}
