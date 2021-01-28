package cn.javabb.sys.controller;

import cn.javabb.sys.entity.Role;
import cn.javabb.sys.service.RoleService;
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
 * 角色 Controller控制器
 *
 * @author Javabb generator
 * @since 2021-01-22 22:15:02
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("sys/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('sys:role:list')")
    @OperLog(value = "角色管理", desc = "分页查询")
    @ApiOperation("分页查询角色")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<Role> page(HttpServletRequest request) {
        PageParam<Role> pageParam = new PageParam<>(request);
        return new PageResult<>(roleService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return roleService.listPage(pageParam);  // 使用关联查询
    }

    @PreAuthorize("hasAuthority('sys:role:list')")
    @OperLog(value = "角色管理", desc = "查询全部")
    @ApiOperation("查询全部角色")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<Role> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(roleService.list(pageParam.getOrderWrapper()));
        //List<Role> records = roleService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }
    @PreAuthorize("hasAuthority('sys:role:list')")
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

    @PreAuthorize("hasAuthority('sys:role:save')")
    @OperLog(value = "角色管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加角色")
    @PostMapping()
    public AjaxResult save(@RequestBody Role role) {
        if (roleService.save(role)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:role:update')")
    @OperLog(value = "角色管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改角色")
    @PutMapping()
    public AjaxResult update(@RequestBody Role role) {
        if (roleService.updateById(role)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:role:remove')")
    @OperLog(value = "角色管理", desc = "删除", result = true)
    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (roleService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:role:remove')")
    @OperLog(value = "角色管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除角色")
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody List<Integer> ids) {
        if (roleService.removeByIds(ids)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

}