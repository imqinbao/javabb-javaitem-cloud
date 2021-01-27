package cn.javabb.sys.controller;

import cn.javabb.sys.entity.Menu;
import cn.javabb.sys.service.MenuService;
import cn.javabb.common.web.domain.*;
import cn.javabb.common.web.controller.BaseController;
import cn.javabb.common.annotation.ApiPageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import cn.javabb.common.annotation.OperLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * 菜单 Controller控制器
 *
 * @author Javabb generator
 * @since 2021-01-22 22:15:02
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("sys/menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    @PreAuthorize("hasAuthority('sys:menu:list')")
    @OperLog(value = "菜单管理", desc = "分页查询")
    @ApiOperation("分页查询菜单")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<Menu> page(HttpServletRequest request) {
        PageParam<Menu> pageParam = new PageParam<>(request);
        return new PageResult<>(menuService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return menuService.listPage(pageParam);  // 使用关联查询
    }

    @PreAuthorize("hasAuthority('sys:menu:list')")
    @OperLog(value = "菜单管理", desc = "查询全部")
    @ApiOperation("查询全部菜单")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<Menu> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(menuService.list(pageParam.getOrderWrapper()));
        //List<Menu> records = menuService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }
    @PreAuthorize("hasAuthority('sys:menu:list')")
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

    @PreAuthorize("hasAuthority('sys:menu:save')")
    @OperLog(value = "菜单管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加菜单")
    @PostMapping()
    public AjaxResult save(@RequestBody Menu menu) {
        if (menuService.save(menu)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:update')")
    @OperLog(value = "菜单管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改菜单")
    @PutMapping()
    public AjaxResult update(@RequestBody Menu menu) {
        if (menuService.updateById(menu)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:remove')")
    @OperLog(value = "菜单管理", desc = "删除", result = true)
    @ApiOperation("删除菜单")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (menuService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:remove')")
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