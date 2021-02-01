package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.entity.Menu;
import cn.javabb.sys.mapper.MenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> {

    public PageResult<Menu> listPage(PageParam<Menu> page) {
        List<Menu> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<Menu> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}
