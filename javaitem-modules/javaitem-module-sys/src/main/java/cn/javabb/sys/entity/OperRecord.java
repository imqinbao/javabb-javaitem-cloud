package cn.javabb.sys.entity;

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
 * @author Javabb generator
 * @since 2021-01-22 21:23:46
 */
@Data
@Accessors(chain = true)
@TableName("sys_oper_record")
@ApiModel(value="OperRecord实体类", description="操作日志")
public class OperRecord implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
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
    private String spendTime;

    @ApiModelProperty(value = "状态,0成功,1异常")
    @TableField("state")
    private String state;

    @ApiModelProperty(value = "操作时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;


    @Override
    public String toString() {
        return "OperRecord{" +
        "id=" + id +
        ", userId=" + userId +
        ", model=" + model +
        ", description=" + description +
        ", url=" + url +
        ", requestMethod=" + requestMethod +
        ", operMethod=" + operMethod +
        ", param=" + param +
        ", result=" + result +
        ", ip=" + ip +
        ", remark=" + remark +
        ", spendTime=" + spendTime +
        ", state=" + state +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
