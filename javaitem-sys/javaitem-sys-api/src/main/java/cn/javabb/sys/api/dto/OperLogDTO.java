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
 * @create: 2021/02/24 21:49
 */
@Data
@Accessors(chain = true)
public class OperLogDTO implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer id;

    private Integer userId;

    private String model;

    private String description;

    private String url;

    private String requestMethod;

    private String operMethod;

    private String param;

    private String result;

    private String ip;

    private String remark;

    private Integer spendTime;

    private Integer state;

    private Date createTime;

    private Date updateTime;
}
