package cn.javabb.sys.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/02/24 21:55
 */
@Data
@Accessors(chain = true)
public class LoginLogDTO implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer id;

    private String username;

    private String os;

    private String device;

    private String browser;

    private String ip;

    private Integer operType;

    private String remark;

    private Date createTime;

    private Date updateTime;

}
