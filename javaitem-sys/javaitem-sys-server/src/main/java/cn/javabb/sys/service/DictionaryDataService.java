package cn.javabb.sys.service;

import cn.javabb.sys.entity.DictionaryData;
import cn.javabb.sys.mapper.DictionaryDataMapper;
import cn.javabb.common.web.domain.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

}
