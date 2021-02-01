package cn.javabb.sys.controller;

import cn.javabb.sys.entity.Organization;
import cn.javabb.sys.service.OrganizationService;
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
 * 组织机构 Controller控制器
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Api(tags = "组织机构管理")
@RestController
@RequestMapping("sys/organization")
public class OrganizationController extends BaseController {
    @Autowired
    private OrganizationService organizationService;

    @OperLog(value = "组织机构管理", desc = "分页查询")
    @ApiOperation("分页查询组织机构")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<Organization> page(HttpServletRequest request) {
        PageParam<Organization> pageParam = new PageParam<>(request);
        return new PageResult<>(organizationService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return organizationService.listPage(pageParam);  // 使用关联查询
    }

    @OperLog(value = "组织机构管理", desc = "查询全部")
    @ApiOperation("查询全部组织机构")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<Organization> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(organizationService.list(pageParam.getOrderWrapper()));
        //List<Organization> records = organizationService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }
    @OperLog(value = "组织机构管理", desc = "根据id查询")
    @ApiOperation("根据id查询组织机构")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.ok().setData(organizationService.getById(id));
        // 使用关联查询
        //PageParam<Organization> pageParam = new PageParam<>();
        //pageParam.put("id", id);
        //List<Organization> records = organizationService.listAll(pageParam.getNoPageParam());
        //return AjaxResult.ok().setData(pageParam.getOne(records));
    }

    @OperLog(value = "组织机构管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加组织机构")
    @PostMapping()
    public AjaxResult save(@RequestBody Organization organization) {
        if (organizationService.save(organization)) {
            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @OperLog(value = "组织机构管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改组织机构")
    @PutMapping()
    public AjaxResult update(@RequestBody Organization organization) {
        if (organizationService.updateById(organization)) {
            return AjaxResult.ok("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @OperLog(value = "组织机构管理", desc = "删除", result = true)
    @ApiOperation("删除组织机构")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Integer id) {
        if (organizationService.removeById(id)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @OperLog(value = "组织机构管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除组织机构")
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody List<Integer> ids) {
        if (organizationService.removeByIds(ids)) {
            return AjaxResult.ok("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

}