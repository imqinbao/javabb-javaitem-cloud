package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.repository.dataobject.Menu;
import cn.javabb.sys.repository.mapper.MenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jarvis.cache.annotation.Cache;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * 获取用户目录
     *
     * @return
     */
    @Cache(key = "getUserMenu", expire = 30 * 60)
    public List<Menu> getUserMenu(String userId, Integer menuType) {
        return baseMapper.listByUserId(userId, menuType);
    }

    /**
     * 转化为树形菜单
     * @return
     */
    public List<Menu> toMenuTree(List<Menu> menus, Integer parentId) {
        List<Menu> list = new ArrayList<>();
        for (Menu menu : menus) {
            if (parentId.equals(menu.getParentId())) {
                menu.setChildren(toMenuTree(menus, menu.getMenuId()));
                list.add(menu);
            }
        }
        return list;
    }


}
