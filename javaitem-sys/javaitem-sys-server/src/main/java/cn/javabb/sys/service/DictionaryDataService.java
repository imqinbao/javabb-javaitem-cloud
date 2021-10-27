package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.repository.dataobject.DictionaryData;
import cn.javabb.sys.repository.mapper.DictionaryDataMapper;
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
public class DictionaryDataService extends ServiceImpl<DictionaryDataMapper, DictionaryData> {

    public PageResult<DictionaryData> listPage(PageParam<DictionaryData> page) {
        List<DictionaryData> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<DictionaryData> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

    /**
     * 查询字典数据,通过字典code
     * @param dictCode
     * @param dictDataName
     * @return
     */
    public DictionaryData listByDictCodeAndName(String dictCode, String dictDataName) {
        PageParam<DictionaryData> pageParam = new PageParam<>();
        pageParam.put("dictCode", dictCode).put("dictDataName", dictDataName);
        List<DictionaryData> records = baseMapper.listAll(pageParam.getNoPageParam());
        return pageParam.getOne(records);
    }
}
