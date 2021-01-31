package cn.javabb.sys.controller;

import cn.javabb.sys.entity.UserRole;
import cn.javabb.sys.service.UserRoleService;
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
 * 用户角色 Controller控制器
 *
 * @author Javabb Generator
 * @since 2021-01-31 23:52:16
 */
@Api(tags = "用户角色管理")
@RestController
@RequestMapping("sys/userRole")
public class UserRoleController extends BaseController {
    @Autowired
    private UserRoleService userRoleService;

    @OperLog(value = "用户角色管理", desc = "分页查询")
    @ApiOperation("分页查询用户角色")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<UserRole> page(HttpServletRequest request) {
        PageParam<UserRole> pageParam = new PageParam<>(request);
        return new PageResult<>(userRoleService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return userRoleService.listPage(pageParam);  // 使用关联查询
    }

    @OperLog(value = "用户角色管理", desc = "查询全部")
    @ApiOperation("查询全部用户角色")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<UserRole> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(userRoleService.list(pageParam.getOrderWrapper()));
        //List<UserRole> records = userRoleService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }
    @OperLog(value = "用户角色管理", desc = "根据id查询")
    @ApiOperation("根据id查询用户角色")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.ok().setData(userRoleService.getById(id));
        // 使用关联查询
        //PageParam<UserRole> pageParam = new PageParam<>();
        //pageParam.put("id", id);
        //List<UserRole> records = userRoleService.listAll(pageParam.getNoPageParam());
        //return AjaxResult.ok().setData(pageParam.getOne(records));
    }

    @OperLog(value = "用户角色管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加用户角色")
    @PostMapping()
    public AjaxResult save(@RequestBody UserRole userRole) {
        if (userRoleService.save(userRole)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @OperLog(value = "用户角色管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改用户角色")
    @PutMapping()
    public AjaxResult update(@RequestBody UserRole userRole) {
        if (userRoleService.updateById(userRole)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @OperLog(value = "用户角色管理", desc = "删除", result = true)
    @ApiOperation("删除用户角色")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (userRoleService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @OperLog(value = "用户角色管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除用户角色")
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody List<Integer> ids) {
        if (userRoleService.removeByIds(ids)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

}