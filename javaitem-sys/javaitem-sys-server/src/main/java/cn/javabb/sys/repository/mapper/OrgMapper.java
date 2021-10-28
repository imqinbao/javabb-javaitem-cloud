package cn.javabb.sys.repository.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.repository.dataobject.OrgDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 组织机构 Mapper 接口
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
public interface OrgMapper extends BaseMapper<OrgDO> {

    /**
     * 分页查询
     */
    List<OrgDO> listPage(@Param("page") PageParam<OrgDO> page);

    /**
     * 查询全部
     */
    List<OrgDO> listAll(@Param("page") Map<String, Object> page);

}
