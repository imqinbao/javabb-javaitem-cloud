package cn.javabb.job.controller;

import cn.javabb.common.annotation.ApiPageParam;
import cn.javabb.common.annotation.OperLog;
import cn.javabb.common.web.controller.BaseController;
import cn.javabb.common.web.domain.AjaxResult;
import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.job.entity.SysJob;
import cn.javabb.job.service.SysJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * 定时任务调度表 Controller控制器
 *
 * @author Javabb
 * @since 2021-03-09 10:08:18
 */
@Api(tags = "定时任务调度表管理")
@RestController
@RequestMapping("job/sysJob")
public class SysJobController extends BaseController {
    @Autowired
    private SysJobService sysJobService;

    @OperLog(value = "定时任务调度表管理", desc = "分页查询")
    @ApiOperation("分页查询定时任务调度表")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<SysJob> page(HttpServletRequest request) {
        PageParam<SysJob> pageParam = new PageParam<>(request);
        return new PageResult<>(sysJobService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return sysJobService.listPage(pageParam);  // 使用关联查询
    }

    @OperLog(value = "定时任务调度表管理", desc = "查询全部")
    @ApiOperation("查询全部定时任务调度表")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<SysJob> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(sysJobService.list(pageParam.getOrderWrapper()));
        //List<SysJob> records = sysJobService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }
    @OperLog(value = "定时任务调度表管理", desc = "根据id查询")
    @ApiOperation("根据id查询定时任务调度表")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.ok().setData(sysJobService.getById(id));
        // 使用关联查询
        //PageParam<SysJob> pageParam = new PageParam<>();
        //pageParam.put("id", id);
        //List<SysJob> records = sysJobService.listAll(pageParam.getNoPageParam());
        //return AjaxResult.ok().setData(pageParam.getOne(records));
    }

    @OperLog(value = "定时任务调度表管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加定时任务调度表")
    @PostMapping()
    public AjaxResult save(@RequestBody SysJob sysJob) {
        if (sysJobService.save(sysJob)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @OperLog(value = "定时任务调度表管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改定时任务调度表")
    @PutMapping()
    public AjaxResult update(@RequestBody SysJob sysJob) {
        if (sysJobService.updateById(sysJob)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @OperLog(value = "定时任务调度表管理", desc = "删除", result = true)
    @ApiOperation("删除定时任务调度表")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (sysJobService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @OperLog(value = "定时任务调度表管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除定时任务调度表")
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody List<Integer> ids) {
        if (sysJobService.removeByIds(ids)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

}