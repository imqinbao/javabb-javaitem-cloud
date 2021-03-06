package cn.javabb.sys.controller;

import cn.javabb.common.util.SecurityUtils;
import cn.javabb.sys.repository.dataobject.MenuDO;
import cn.javabb.sys.service.MenuService;
import cn.javabb.common.web.domain.*;
import cn.javabb.common.web.controller.BaseController;
import cn.javabb.common.annotation.ApiPageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.javabb.common.annotation.OperLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * 菜单 Controller控制器
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    @OperLog(value = "菜单管理", desc = "分页查询")
    @ApiOperation("分页查询菜单")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<MenuDO> page(HttpServletRequest request) {
        PageParam<MenuDO> pageParam = new PageParam<>(request);
        return new PageResult<>(menuService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return menuService.listPage(pageParam);  // 使用关联查询
    }

    @OperLog(value = "菜单管理", desc = "查询全部")
    @ApiOperation("查询全部菜单")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<MenuDO> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(menuService.list(pageParam.getOrderWrapper()));
        //List<Menu> records = menuService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }

    @OperLog(value = "菜单管理", desc = "查询树形菜单")
    @ApiOperation("查询树形菜单")
    @GetMapping("/userMenuTree")
    public AjaxResult menuTree(HttpServletRequest request) {
        List<MenuDO> userMenu = menuService.getUserMenu(SecurityUtils.getUserId(), MenuDO.TYPE_MENU);
        return AjaxResult.ok().setData(menuService.toMenuTree(userMenu,0));
    }

    @OperLog(value = "菜单管理", desc = "根据id查询")
    @ApiOperation("根据id查询菜单")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.ok().setData(menuService.getById(id));
        // 使用关联查询
        //PageParam<Menu> pageParam = new PageParam<>();
        //pageParam.put("id", id);
        //List<Menu> records = menuService.listAll(pageParam.getNoPageParam());
        //return AjaxResult.ok().setData(pageParam.getOne(records));
    }

    @OperLog(value = "菜单管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加菜单")
    @PostMapping()
    public AjaxResult save(@RequestBody MenuDO menu) {
        if (menuService.save(menu)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @OperLog(value = "菜单管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改菜单")
    @PutMapping()
    public AjaxResult update(@RequestBody MenuDO menu) {
        if (menuService.updateById(menu)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @OperLog(value = "菜单管理", desc = "删除", result = true)
    @ApiOperation("删除菜单")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (menuService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @OperLog(value = "菜单管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除菜单")
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody List<Integer> ids) {
        if (menuService.removeByIds(ids)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

}