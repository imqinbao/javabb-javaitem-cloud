package cn.javabb.sys.repository.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.repository.dataobject.Organization;
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
public interface OrganizationMapper extends BaseMapper<Organization> {

    /**
     * 分页查询
     */
    List<Organization> listPage(@Param("page") PageParam<Organization> page);

    /**
     * 查询全部
     */
    List<Organization> listAll(@Param("page") Map<String, Object> page);

}