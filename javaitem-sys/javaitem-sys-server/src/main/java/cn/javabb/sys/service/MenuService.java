package cn.javabb.sys.service;

import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.repository.dataobject.MenuDO;
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
public class MenuService extends ServiceImpl<MenuMapper, MenuDO> {

    public PageResult<MenuDO> listPage(PageParam<MenuDO> page) {
        List<MenuDO> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    public List<MenuDO> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

    /**
     * 获取用户目录
     *
     * @return
     */
    @Cache(key = "getUserMenu", expire = 30 * 60)
    public List<MenuDO> getUserMenu(String userId, Integer menuType) {
        return baseMapper.listByUserId(userId, menuType);
    }

    /**
     * 转化为树形菜单
     * @return
     */
    public List<MenuDO> toMenuTree(List<MenuDO> menus, Integer parentId) {
        List<MenuDO> list = new ArrayList<>();
        for (MenuDO menu : menus) {
            if (parentId.equals(menu.getParentId())) {
                menu.setChildren(toMenuTree(menus, menu.getMenuId()));
                list.add(menu);
            }
        }
        return list;
    }


}
