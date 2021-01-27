package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.entity.DictionaryData;
import cn.javabb.sys.mapper.DictionaryDataMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典项 服务实现类
 * </p>
 *
 * @author Javabb generator
 * @since 2021-01-22 21:23:46
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

}
