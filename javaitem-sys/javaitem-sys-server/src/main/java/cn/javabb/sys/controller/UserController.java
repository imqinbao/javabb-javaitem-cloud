package cn.javabb.sys.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.javabb.common.annotation.ApiPageParam;
import cn.javabb.common.annotation.OperLog;
import cn.javabb.common.model.R;
import cn.javabb.common.util.CommonUtil;
import cn.javabb.common.util.SecurityUtils;
import cn.javabb.common.util.ServletUtils;
import cn.javabb.common.web.controller.BaseController;
import cn.javabb.common.web.domain.AjaxResult;
import cn.javabb.common.web.domain.PageParam;
import cn.javabb.common.web.domain.PageResult;
import cn.javabb.sys.api.model.LoginUser;
import cn.javabb.sys.model.dto.UserBaseDTO;
import cn.javabb.sys.model.qry.UserQry;
import cn.javabb.sys.repository.dataobject.DictDataDO;
import cn.javabb.sys.repository.dataobject.OrgDO;
import cn.javabb.sys.repository.dataobject.RoleDO;
import cn.javabb.sys.repository.dataobject.UserDO;
import cn.javabb.sys.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 用户 Controller控制器
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private DictDataService dictDataService;


    @OperLog(value = "用户管理", desc = "分页查询")
    @ApiOperation("分页查询用户")
    @GetMapping("/page")
    public PageResult<UserBaseDTO> page(UserQry userQry) {

        return userService.listPage(userQry);  // 使用关联查询
    }

    @OperLog(value = "用户管理", desc = "查询全部")
    @ApiOperation("查询全部用户")
    @GetMapping()
    public AjaxResult list(HttpServletRequest request) {
        PageParam<UserDO> pageParam = new PageParam<>(request);
        return AjaxResult.ok().setData(userService.list(pageParam.getOrderWrapper()));
        //List<User> records = userService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return AjaxResult.ok().setData(pageParam.sortRecords(records));
    }

    @OperLog(value = "用户管理", desc = "根据id查询")
    @ApiOperation("根据id查询用户")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.ok().setData(userService.getUserById(id));
        // 使用关联查询
        //PageParam<User> pageParam = new PageParam<>();
        //pageParam.put("id", id);
        //List<User> records = userService.listAll(pageParam.getNoPageParam());
        //return AjaxResult.ok().setData(pageParam.getOne(records));
    }

    @OperLog(value = "用户管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加用户")
    @PostMapping()
    public AjaxResult save(@RequestBody UserBaseDTO user) {
        user.setDeleted(0);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        if (userService.saveUser(user)) {

            return AjaxResult.ok("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @OperLog(value = "用户管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改用户")
    @PutMapping()
    public AjaxResult update(@RequestBody UserBaseDTO user) {
        Assert.isNull(user.getId(),"参数错误");
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        if (userService.updateUser(user)) {
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
        UserBaseDTO user = userService.getByUsername(username);
        if (ObjectUtil.isEmpty(user)) {
            return R.fail("用户不存在");
        }
        Set<String> roleIds = userRoleService.getUserRoleIds(user.getId());
        LoginUser loginUser = new LoginUser();
        loginUser.setUserid(user.getId());
        loginUser.setRoles(roleIds);
        loginUser.setUserInfo(userService.userToUserDTO(user));
        return R.ok(loginUser);
    }
    @ApiOperation("获取当前登录用户详细信息")
    @GetMapping("/fullUser")
    public AjaxResult fullUserInfo() {
        String username = SecurityUtils.getUsername();
        HttpServletRequest request = ServletUtils.getRequest();

        return AjaxResult.ok().setData(userService.getUserBaseById(SecurityUtils.getUserId()));
    }

    /**
     * 重置密码
     * @param id
     * @param password
     * @return
     */
    @OperLog(value = "用户管理", desc = "重置密码", param = false, result = true)
    @ApiOperation("重置密码")
    @PutMapping("/psw/{id}")
    public AjaxResult resetPsw(@PathVariable("id") String id, String password) {
        UserDO userDO = new UserDO();
        userDO.setId(id);
        userDO.setPassword(SecurityUtils.encryptPassword(password));
        if (userService.updateById(userDO)) {
            return AjaxResult.ok("重置成功");
        } else {
            return AjaxResult.error("重置失败");
        }
    }
    /**
     * excel导入用户
     */
    @OperLog(value = "用户管理", desc = "excel导入", param = false, result = true)
    @ApiOperation("excel导入用户")
    @Transactional
    @PostMapping("/import")
    public AjaxResult importBatch(MultipartFile file) {
        StringBuilder sb = new StringBuilder();
        try {
            // 读取excel
            int startRow = 1;
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream(), 0);
            List<List<Object>> list = reader.read(startRow);
            // 进行非空和重复检查
            sb.append(CommonUtil.excelCheckBlank(list, startRow, 0, 1, 2, 3, 4, 7));
            sb.append(CommonUtil.excelCheckRepeat(list, startRow, 0, 5, 6));
            if (!sb.toString().isEmpty()) return AjaxResult.error(sb.toString());
            // 进行数据库层面检查
            List<UserBaseDTO> userList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                List<Object> objects = list.get(i);
                String username = String.valueOf(objects.get(0));  // 账号
                String password = String.valueOf(objects.get(1));  // 密码
                String nickname = String.valueOf(objects.get(2));  // 用户名
                String sexName = String.valueOf(objects.get(3));  // 性别
                String roleName = String.valueOf(objects.get(4));  // 角色名
                String phone = String.valueOf(objects.get(5));  // 手机号
                String email = String.valueOf(objects.get(6));  // 邮箱
                String orgName = String.valueOf(objects.get(7));  // 组织机构
                if (userService.count(new QueryWrapper<UserDO>().eq("username", username)) > 0) {
                    sb.append("第");
                    sb.append(i + startRow + 1);
                    sb.append("行第1");
                    sb.append("列账号已存在;\r\n");
                }
                if (StrUtil.isNotBlank(phone) && userService.count(new QueryWrapper<UserDO>().eq("phone", phone)) > 0) {
                    sb.append("第");
                    sb.append(i + startRow + 1);
                    sb.append("行第6");
                    sb.append("列手机号已存在;\r\n");
                }
                if (StrUtil.isNotBlank(email) && userService.count(new QueryWrapper<UserDO>().eq("email", email)) > 0) {
                    sb.append("第");
                    sb.append(i + startRow + 1);
                    sb.append("行第7");
                    sb.append("列邮箱已存在;\r\n");
                }
                UserBaseDTO userBaseDTO = new UserBaseDTO();
                userBaseDTO.setUsername(username);
                userBaseDTO.setNickname(nickname);
                userBaseDTO.setPassword(SecurityUtils.encryptPassword(password));
                userBaseDTO.setDeleted(0);
                userBaseDTO.setPhone(phone);
                userBaseDTO.setEmail(email);
                DictDataDO sexDictData = dictDataService.listByDictCodeAndName("sex", sexName);
                if (sexDictData == null) {
                    sb.append("第");
                    sb.append(i + startRow + 1);
                    sb.append("行第4");
                    sb.append("列性别不存在;\r\n");
                } else {
                    userBaseDTO.setSex(sexDictData.getId());
                }
                RoleDO role = roleService.getOne(new QueryWrapper<RoleDO>().eq("role_name", roleName), false);
                if (role == null) {
                    sb.append("第");
                    sb.append(i + startRow + 1);
                    sb.append("行第5");
                    sb.append("列角色不存在;\r\n");
                } else {
                    userBaseDTO.setRoleIds(Collections.singletonList(role.getId()));
                }
                OrgDO org = orgService.getOne(new QueryWrapper<OrgDO>().eq("org_full_name", orgName), false);
                if (org == null) {
                    sb.append("第");
                    sb.append(i + startRow + 1);
                    sb.append("行第8");
                    sb.append("列机构不存在;\r\n");
                } else {
                    userBaseDTO.setOrgId(org.getId());
                }
                userList.add(userBaseDTO);
            }
            if (!sb.toString().isEmpty()) return AjaxResult.error(sb.toString());
            // 开始添加用户
            int okNum = 0, errorNum = 0;
            for (UserBaseDTO userDTO : userList) {
                if (userService.saveUser(userDTO)) okNum++;
                else errorNum++;
            }
            return AjaxResult.ok("导入完成，成功" + okNum + "条，失败" + errorNum + "条");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResult.error("导入失败");
    }
}