package cn.javabb.sys.repository.dataobject;

import cn.javabb.common.repository.mybatis.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
/**
 * 操作日志
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Data
@Accessors(chain = true)
@TableName("sys_oper_log")
@ApiModel(value="OperLog实体类", description="操作日志")
public class OperLogDO extends BaseDO {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId
    private String id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "操作模块")
    @TableField("model")
    private String model;

    @ApiModelProperty(value = "操作方法")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "请求地址")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "请求方式")
    @TableField("request_method")
    private String requestMethod;

    @ApiModelProperty(value = "调用方法")
    @TableField("oper_method")
    private String operMethod;

    @ApiModelProperty(value = "请求参数")
    @TableField("param")
    private String param;

    @ApiModelProperty(value = "返回结果")
    @TableField("result")
    private String result;

    @ApiModelProperty(value = "ip地址")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "请求耗时,单位毫秒")
    @TableField("spend_time")
    private Integer spendTime;

    @ApiModelProperty(value = "状态,0成功,1异常")
    @TableField("state")
    private Integer state;

}
