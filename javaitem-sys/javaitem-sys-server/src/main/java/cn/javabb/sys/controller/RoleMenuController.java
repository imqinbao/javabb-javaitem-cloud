package cn.javabb.sys.controller;

import cn.javabb.common.annotation.ApiPageParam;
import cn.javabb.common.annotation.OperLog;
import cn.javabb.common.web.controller.BaseController;
import cn.javabb.common.web.domain.AjaxResult;
import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.repository.dataobject.RoleMenu;
import cn.javabb.sys.service.RoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * 角色权限 Controller控制器
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Api(tags = "角色权限管理")
@RestController
@RequestMapping("rolePermission")
public class RoleMenuController extends BaseController {
    @Autowired
    private RoleMenuService roleMenuService;

    @OperLog(value = "角色权限管理", desc = "分页查询")
    @ApiOperation("分页查询角色权限")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<RoleMenu> page(HttpServletRequest request) {
        PageParam<RoleMenu> pageParam = new PageParam<>(request);
        return new PageResult<>(roleMenuService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return rolePermissionService.listPage(pageParam);  // 使用关联查询
    }

    @OperLog(value = "角色权限管理", desc = "查询全部")
    @ApiOperation("查询全部角色权限")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<RoleMenu> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(roleMenuService.list(pageParam.getOrderWrapper()));
        //List<RolePermission> records = rolePermissionService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }
    @OperLog(value = "角色权限管理", desc = "根据id查询")
    @ApiOperation("根据id查询角色权限")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.ok().setData(roleMenuService.getById(id));
        // 使用关联查询
        //PageParam<RolePermission> pageParam = new PageParam<>();
        //pageParam.put("id", id);
        //List<RolePermission> records = rolePermissionService.listAll(pageParam.getNoPageParam());
        //return AjaxResult.ok().setData(pageParam.getOne(records));
    }

    @OperLog(value = "角色权限管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加角色权限")
    @PostMapping()
    public AjaxResult save(@RequestBody RoleMenu roleMenu) {
        if (roleMenuService.save(roleMenu)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @OperLog(value = "角色权限管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改角色权限")
    @PutMapping()
    public AjaxResult update(@RequestBody RoleMenu roleMenu) {
        if (roleMenuService.updateById(roleMenu)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @OperLog(value = "角色权限管理", desc = "删除", result = true)
    @ApiOperation("删除角色权限")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (roleMenuService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @OperLog(value = "角色权限管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除角色权限")
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody List<Integer> ids) {
        if (roleMenuService.removeByIds(ids)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

}