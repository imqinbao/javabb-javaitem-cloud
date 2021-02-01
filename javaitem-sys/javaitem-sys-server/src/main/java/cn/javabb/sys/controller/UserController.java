package cn.javabb.sys.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.javabb.common.annotation.ApiPageParam;
import cn.javabb.common.annotation.OperLog;
import cn.javabb.common.model.R;
import cn.javabb.common.web.controller.BaseController;
import cn.javabb.common.web.domain.AjaxResult;
import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.entity.User;
import cn.javabb.sys.model.LoginUser;
import cn.javabb.sys.service.UserRoleService;
import cn.javabb.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 用户 Controller控制器
 *
 * @author Javabb Generator
 * @since 2021-01-31 23:52:16
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("sys/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @OperLog(value = "用户管理", desc = "分页查询")
    @ApiOperation("分页查询用户")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<User> page(HttpServletRequest request) {
        PageParam<User> pageParam = new PageParam<>(request);
        return new PageResult<>(userService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return userService.listPage(pageParam);  // 使用关联查询
    }

    @OperLog(value = "用户管理", desc = "查询全部")
    @ApiOperation("查询全部用户")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<User> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(userService.list(pageParam.getOrderWrapper()));
        //List<User> records = userService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }
    @OperLog(value = "用户管理", desc = "根据id查询")
    @ApiOperation("根据id查询用户")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.ok().setData(userService.getById(id));
        // 使用关联查询
        //PageParam<User> pageParam = new PageParam<>();
        //pageParam.put("id", id);
        //List<User> records = userService.listAll(pageParam.getNoPageParam());
        //return AjaxResult.ok().setData(pageParam.getOne(records));
    }

    @OperLog(value = "用户管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加用户")
    @PostMapping()
    public AjaxResult save(@RequestBody User user) {
        if (userService.save(user)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @OperLog(value = "用户管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改用户")
    @PutMapping()
    public AjaxResult update(@RequestBody User user) {
        if (userService.updateById(user)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @OperLog(value = "用户管理", desc = "删除", result = true)
    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (userService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @OperLog(value = "用户管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除用户")
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody List<Integer> ids) {
        if (userService.removeByIds(ids)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @GetMapping("/info/{username}")
    public R<LoginUser> userInfo(@PathVariable("username") String username) {
        //QueryWrapper
        User user = userService.getByUsername(username);
        if (ObjectUtil.isEmpty(user)) {
            return R.fail("用户名密码错误");
        }
        Set<String> roleIds = userRoleService.getRoleIds(user.getUserId());
        LoginUser loginUser = new LoginUser();
        loginUser.setUserid(user.getUserId())
        return null;
    }


}