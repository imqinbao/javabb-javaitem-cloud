package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.entity.Dictionary;
import cn.javabb.sys.mapper.DictionaryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典 服务实现类
 * </p>
 *
 * @author Javabb generator
 * @since 2021-01-22 21:23:46
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
