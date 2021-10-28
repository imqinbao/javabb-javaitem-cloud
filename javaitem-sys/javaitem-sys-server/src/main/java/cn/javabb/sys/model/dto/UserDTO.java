package cn.javabb.sys.model.dto;

import cn.javabb.sys.repository.dataobject.MenuDO;
import cn.javabb.sys.repository.dataobject.RoleDO;
import cn.javabb.sys.repository.dataobject.UserDO;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/10/28 17:18
 */
@Data
@ApiModel("用户数据实体")
public class UserDTO extends UserDO {

    @ApiModelProperty("机构名称")
    private String orgName;
    @ApiModelProperty("性别")
    private String sexName;

    @ApiModelProperty("角色id")
    private List<String> roleIds;

    @ApiModelProperty("角色列表")
    private List<RoleDO> roles;

    @ApiModelProperty("权限列表")
    private List<MenuDO> authorities;

}
