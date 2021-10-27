package cn.javabb.sys.service;

import cn.javabb.sys.repository.dataobject.Dictionary;
import cn.javabb.sys.repository.mapper.DictionaryMapper;
import cn.javabb.common.web.domain.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典 服务实现类
 * </p>
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Service
public class DictionaryService extends ServiceImpl<DictionaryMapper, Dictionary> {

    public PageResult<Dictionary> listPage(PageParam<Dictionary> page) {
        List<Dictionary> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<Dictionary> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}
