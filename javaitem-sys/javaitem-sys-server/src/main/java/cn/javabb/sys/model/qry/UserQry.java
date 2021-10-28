package cn.javabb.sys.model.qry;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/10/28 17:23
 */
@Data
@ApiModel("用户查询条件")
public class UserQry implements Serializable {
    private static final long serialVersionUID = 3275669933121439221L;

    private String userId;

    private String username;

    private String orgId;

    private String email;
}
