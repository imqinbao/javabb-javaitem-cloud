package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.repository.dataobject.DictDataDO;
import cn.javabb.sys.repository.mapper.DictDataMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典项 服务实现类
 * </p>
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Service
public class DictDataService extends ServiceImpl<DictDataMapper, DictDataDO> {

    public PageResult<DictDataDO> listPage(PageParam<DictDataDO> page) {
        List<DictDataDO> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<DictDataDO> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

    /**
     * 查询字典数据,通过字典code
     * @param dictCode
     * @param dictDataName
     * @return
     */
    public DictDataDO listByDictCodeAndName(String dictCode, String dictDataName) {
        PageParam<DictDataDO> pageParam = new PageParam<>();
        pageParam.put("dictCode", dictCode).put("dictDataName", dictDataName);
        List<DictDataDO> records = baseMapper.listAll(pageParam.getNoPageParam());
        return pageParam.getOne(records);
    }
}
