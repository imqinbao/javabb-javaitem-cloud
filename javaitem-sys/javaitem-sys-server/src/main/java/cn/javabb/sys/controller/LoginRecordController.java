package cn.javabb.sys.controller;

import cn.javabb.sys.entity.LoginRecord;
import cn.javabb.sys.service.LoginRecordService;
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
 * 登录日志 Controller控制器
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Api(tags = "登录日志管理")
@RestController
@RequestMapping("loginRecord")
public class LoginRecordController extends BaseController {
    @Autowired
    private LoginRecordService loginRecordService;

    @OperLog(value = "登录日志管理", desc = "分页查询")
    @ApiOperation("分页查询登录日志")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<LoginRecord> page(HttpServletRequest request) {
        PageParam<LoginRecord> pageParam = new PageParam<>(request);
        return new PageResult<>(loginRecordService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return loginRecordService.listPage(pageParam);  // 使用关联查询
    }

    @OperLog(value = "登录日志管理", desc = "查询全部")
    @ApiOperation("查询全部登录日志")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<LoginRecord> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(loginRecordService.list(pageParam.getOrderWrapper()));
        //List<LoginRecord> records = loginRecordService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }
    @OperLog(value = "登录日志管理", desc = "根据id查询")
    @ApiOperation("根据id查询登录日志")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.ok().setData(loginRecordService.getById(id));
        // 使用关联查询
        //PageParam<LoginRecord> pageParam = new PageParam<>();
        //pageParam.put("id", id);
        //List<LoginRecord> records = loginRecordService.listAll(pageParam.getNoPageParam());
        //return AjaxResult.ok().setData(pageParam.getOne(records));
    }

    @OperLog(value = "登录日志管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加登录日志")
    @PostMapping()
    public AjaxResult save(@RequestBody LoginRecord loginRecord) {
        if (loginRecordService.save(loginRecord)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @OperLog(value = "登录日志管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改登录日志")
    @PutMapping()
    public AjaxResult update(@RequestBody LoginRecord loginRecord) {
        if (loginRecordService.updateById(loginRecord)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @OperLog(value = "登录日志管理", desc = "删除", result = true)
    @ApiOperation("删除登录日志")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (loginRecordService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @OperLog(value = "登录日志管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除登录日志")
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody List<Integer> ids) {
        if (loginRecordService.removeByIds(ids)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

}