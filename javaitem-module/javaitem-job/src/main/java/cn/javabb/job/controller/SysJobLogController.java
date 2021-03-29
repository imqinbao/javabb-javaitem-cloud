package cn.javabb.job.controller;

import cn.javabb.common.annotation.ApiPageParam;
import cn.javabb.common.annotation.OperLog;
import cn.javabb.common.web.controller.BaseController;
import cn.javabb.common.web.domain.AjaxResult;
import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.job.entity.SysJobLog;
import cn.javabb.job.service.SysJobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * 定时任务调度日志表 Controller控制器
 *
 * @author Javabb
 * @since 2021-03-09 10:08:18
 */
@Api(tags = "定时任务调度日志表管理")
@RestController
@RequestMapping("job/sysJobLog")
public class SysJobLogController extends BaseController {
    @Autowired
    private SysJobLogService sysJobLogService;

    @OperLog(value = "定时任务调度日志表管理", desc = "分页查询")
    @ApiOperation("分页查询定时任务调度日志表")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<SysJobLog> page(HttpServletRequest request) {
        PageParam<SysJobLog> pageParam = new PageParam<>(request);
        return new PageResult<>(sysJobLogService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return sysJobLogService.listPage(pageParam);  // 使用关联查询
    }

    @OperLog(value = "定时任务调度日志表管理", desc = "查询全部")
    @ApiOperation("查询全部定时任务调度日志表")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<SysJobLog> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(sysJobLogService.list(pageParam.getOrderWrapper()));
        //List<SysJobLog> records = sysJobLogService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }
    @OperLog(value = "定时任务调度日志表管理", desc = "根据id查询")
    @ApiOperation("根据id查询定时任务调度日志表")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.ok().setData(sysJobLogService.getById(id));
        // 使用关联查询
        //PageParam<SysJobLog> pageParam = new PageParam<>();
        //pageParam.put("id", id);
        //List<SysJobLog> records = sysJobLogService.listAll(pageParam.getNoPageParam());
        //return AjaxResult.ok().setData(pageParam.getOne(records));
    }

    @OperLog(value = "定时任务调度日志表管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加定时任务调度日志表")
    @PostMapping()
    public AjaxResult save(@RequestBody SysJobLog sysJobLog) {
        if (sysJobLogService.save(sysJobLog)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @OperLog(value = "定时任务调度日志表管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改定时任务调度日志表")
    @PutMapping()
    public AjaxResult update(@RequestBody SysJobLog sysJobLog) {
        if (sysJobLogService.updateById(sysJobLog)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @OperLog(value = "定时任务调度日志表管理", desc = "删除", result = true)
    @ApiOperation("删除定时任务调度日志表")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (sysJobLogService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @OperLog(value = "定时任务调度日志表管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除定时任务调度日志表")
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody List<Integer> ids) {
        if (sysJobLogService.removeByIds(ids)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

}