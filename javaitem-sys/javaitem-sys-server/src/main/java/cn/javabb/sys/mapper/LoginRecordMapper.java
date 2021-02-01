package cn.javabb.sys.mapper;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.sys.entity.LoginRecord;
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
public interface LoginRecordMapper extends BaseMapper<LoginRecord> {

    /**
     * 分页查询
     */
    List<LoginRecord> listPage(@Param("page") PageParam<LoginRecord> page);

    /**
     * 查询全部
     */
    List<LoginRecord> listAll(@Param("page") Map<String, Object> page);

}
