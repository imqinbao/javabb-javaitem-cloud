package cn.javabb.sys.controller;

import cn.javabb.sys.repository.dataobject.OperRecord;
import cn.javabb.sys.service.OperRecordService;
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
 * 操作日志 Controller控制器
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Api(tags = "操作日志管理")
@RestController
@RequestMapping("operRecord")
public class OperRecordController extends BaseController {
    @Autowired
    private OperRecordService operRecordService;

    @OperLog(value = "操作日志管理", desc = "分页查询")
    @ApiOperation("分页查询操作日志")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<OperRecord> page(HttpServletRequest request) {
        PageParam<OperRecord> pageParam = new PageParam<>(request);
        return new PageResult<>(operRecordService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return operRecordService.listPage(pageParam);  // 使用关联查询
    }

    @OperLog(value = "操作日志管理", desc = "查询全部")
    @ApiOperation("查询全部操作日志")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<OperRecord> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(operRecordService.list(pageParam.getOrderWrapper()));
        //List<OperRecord> records = operRecordService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }
    @OperLog(value = "操作日志管理", desc = "根据id查询")
    @ApiOperation("根据id查询操作日志")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.ok().setData(operRecordService.getById(id));
        // 使用关联查询
        //PageParam<OperRecord> pageParam = new PageParam<>();
        //pageParam.put("id", id);
        //List<OperRecord> records = operRecordService.listAll(pageParam.getNoPageParam());
        //return AjaxResult.ok().setData(pageParam.getOne(records));
    }

    @OperLog(value = "操作日志管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加操作日志")
    @PostMapping()
    public AjaxResult save(@RequestBody OperRecord operRecord) {
        if (operRecordService.save(operRecord)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @OperLog(value = "操作日志管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改操作日志")
    @PutMapping()
    public AjaxResult update(@RequestBody OperRecord operRecord) {
        if (operRecordService.updateById(operRecord)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @OperLog(value = "操作日志管理", desc = "删除", result = true)
    @ApiOperation("删除操作日志")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (operRecordService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @OperLog(value = "操作日志管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除操作日志")
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody List<Integer> ids) {
        if (operRecordService.removeByIds(ids)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

}