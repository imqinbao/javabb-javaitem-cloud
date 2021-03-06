package cn.javabb.sys.controller;

import cn.javabb.sys.repository.dataobject.DictDataDO;
import cn.javabb.sys.service.DictDataService;
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
 * 字典项 Controller控制器
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Api(tags = "字典项管理")
@RestController
@RequestMapping("dictionaryData")
public class DictDataController extends BaseController {
    @Autowired
    private DictDataService dictDataService;

    @OperLog(value = "字典项管理", desc = "分页查询")
    @ApiOperation("分页查询字典项")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<DictDataDO> page(HttpServletRequest request) {
        PageParam<DictDataDO> pageParam = new PageParam<>(request);
        return new PageResult<>(dictDataService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return dictionaryDataService.listPage(pageParam);  // 使用关联查询
    }

    @OperLog(value = "字典项管理", desc = "查询全部")
    @ApiOperation("查询全部字典项")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<DictDataDO> pageParam = new PageParam<>(request);
        //return AjaxResult.ok().setData(dictionaryDataService.list(pageParam.getOrderWrapper()));
        List<DictDataDO> records = dictDataService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }
    @OperLog(value = "字典项管理", desc = "根据id查询")
    @ApiOperation("根据id查询字典项")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.ok().setData(dictDataService.getById(id));
        // 使用关联查询
        //PageParam<DictionaryData> pageParam = new PageParam<>();
        //pageParam.put("id", id);
        //List<DictionaryData> records = dictionaryDataService.listAll(pageParam.getNoPageParam());
        //return AjaxResult.ok().setData(pageParam.getOne(records));
    }

    @OperLog(value = "字典项管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加字典项")
    @PostMapping()
    public AjaxResult save(@RequestBody DictDataDO dictDataDO) {
        if (dictDataService.save(dictDataDO)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @OperLog(value = "字典项管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改字典项")
    @PutMapping()
    public AjaxResult update(@RequestBody DictDataDO dictDataDO) {
        if (dictDataService.updateById(dictDataDO)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @OperLog(value = "字典项管理", desc = "删除", result = true)
    @ApiOperation("删除字典项")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (dictDataService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @OperLog(value = "字典项管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除字典项")
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody List<Integer> ids) {
        if (dictDataService.removeByIds(ids)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

}