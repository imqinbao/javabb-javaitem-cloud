package cn.javabb.sys.controller;

import cn.javabb.sys.entity.RolePermission;
import cn.javabb.sys.service.RolePermissionService;
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
 * 角色权限 Controller控制器
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Api(tags = "角色权限管理")
@RestController
@RequestMapping("sys/rolePermission")
public class RolePermissionController extends BaseController {
    @Autowired
    private RolePermissionService rolePermissionService;

    @OperLog(value = "角色权限管理", desc = "分页查询")
    @ApiOperation("分页查询角色权限")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<RolePermission> page(HttpServletRequest request) {
        PageParam<RolePermission> pageParam = new PageParam<>(request);
        return new PageResult<>(rolePermissionService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return rolePermissionService.listPage(pageParam);  // 使用关联查询
    }

    @OperLog(value = "角色权限管理", desc = "查询全部")
    @ApiOperation("查询全部角色权限")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<RolePermission> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(rolePermissionService.list(pageParam.getOrderWrapper()));
        //List<RolePermission> records = rolePermissionService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }
    @OperLog(value = "角色权限管理", desc = "根据id查询")
    @ApiOperation("根据id查询角色权限")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.ok().setData(rolePermissionService.getById(id));
        // 使用关联查询
        //PageParam<RolePermission> pageParam = new PageParam<>();
        //pageParam.put("id", id);
        //List<RolePermission> records = rolePermissionService.listAll(pageParam.getNoPageParam());
        //return AjaxResult.ok().setData(pageParam.getOne(records));
    }

    @OperLog(value = "角色权限管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加角色权限")
    @PostMapping()
    public AjaxResult save(@RequestBody RolePermission rolePermission) {
        if (rolePermissionService.save(rolePermission)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @OperLog(value = "角色权限管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改角色权限")
    @PutMapping()
    public AjaxResult update(@RequestBody RolePermission rolePermission) {
        if (rolePermissionService.updateById(rolePermission)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @OperLog(value = "角色权限管理", desc = "删除", result = true)
    @ApiOperation("删除角色权限")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (rolePermissionService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @OperLog(value = "角色权限管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除角色权限")
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody List<Integer> ids) {
        if (rolePermissionService.removeByIds(ids)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

}