package cn.javabb.sys.controller;

import cn.javabb.common.annotation.ApiPageParam;
import cn.javabb.common.annotation.OperLog;
import cn.javabb.common.web.controller.BaseController;
import cn.javabb.common.web.domain.AjaxResult;
import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.entity.Menu;
import cn.javabb.sys.entity.Role;
import cn.javabb.sys.entity.RoleMenu;
import cn.javabb.sys.service.MenuService;
import cn.javabb.sys.service.RoleMenuService;
import cn.javabb.sys.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * 角色 Controller控制器
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleMenuService roleMenuService;

    @OperLog(value = "角色管理", desc = "分页查询")
    @ApiOperation("分页查询角色")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<Role> page(HttpServletRequest request) {
        PageParam<Role> pageParam = new PageParam<>(request);
        return new PageResult<>(roleService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return roleService.listPage(pageParam);  // 使用关联查询
    }

    @OperLog(value = "角色管理", desc = "查询全部")
    @ApiOperation("查询全部角色")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<Role> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(roleService.list(pageParam.getOrderWrapper()));
        //List<Role> records = roleService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }
    @OperLog(value = "角色管理", desc = "根据id查询")
    @ApiOperation("根据id查询角色")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.ok().setData(roleService.getById(id));
        // 使用关联查询
        //PageParam<Role> pageParam = new PageParam<>();
        //pageParam.put("id", id);
        //List<Role> records = roleService.listAll(pageParam.getNoPageParam());
        //return AjaxResult.ok().setData(pageParam.getOne(records));
    }

    @OperLog(value = "角色管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加角色")
    @PostMapping()
    public AjaxResult save(@RequestBody Role role) {
        if (roleService.save(role)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @OperLog(value = "角色管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改角色")
    @PutMapping()
    public AjaxResult update(@RequestBody Role role) {
        if (roleService.updateById(role)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @OperLog(value = "角色管理", desc = "删除", result = true)
    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (roleService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @OperLog(value = "角色管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除角色")
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody List<Integer> ids) {
        if (roleService.removeByIds(ids)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }
    /**
     * 查询角色菜单
     */
    @OperLog(value = "角色管理", desc = "查询角色菜单")
    @ResponseBody
    @GetMapping("/menu")
    public AjaxResult getRoleMenu(Integer roleId) {
        List<Menu> menus = menuService.list(new QueryWrapper<Menu>().orderByAsc("sort_no"));
        List<RoleMenu> roleMenus = roleMenuService.list(new QueryWrapper<RoleMenu>().eq("role_id", roleId));
        for (Menu menu : menus) {
            menu.setOpen(true);
            menu.setChecked(false);
            for (RoleMenu roleMenu : roleMenus) {
                if (menu.getMenuId().equals(roleMenu.getMenuId())) {
                    menu.setChecked(true);
                    break;
                }
            }
        }
        return AjaxResult.ok().setData(menus);
    }
}