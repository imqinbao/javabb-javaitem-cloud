package cn.javabb.job.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
/**
 * 定时任务调度表
 *
 * @author Abcd
 * @since 2021-03-09 10:08:18
 */
@Data
@Accessors(chain = true)
@TableName("sys_job")
@ApiModel(value="SysJob实体类", description="定时任务调度表")
public class SysJob implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "任务ID")
    @TableId(value = "job_id", type = IdType.AUTO)
    private Long jobId;

    @ApiModelProperty(value = "任务名称")
    @TableField("job_name")
    private String jobName;

    @ApiModelProperty(value = "任务组名")
    @TableField("job_group")
    private String jobGroup;

    @ApiModelProperty(value = "调用目标字符串")
    @TableField("invoke_target")
    private String invokeTarget;

    @ApiModelProperty(value = "cron执行表达式")
    @TableField("cron_expression")
    private String cronExpression;

    @ApiModelProperty(value = "计划执行错误策略（1立即执行 2执行一次 3放弃执行）")
    @TableField("misfire_policy")
    private String misfirePolicy;

    @ApiModelProperty(value = "是否并发执行（0允许 1禁止）")
    @TableField("concurrent")
    private String concurrent;

    @ApiModelProperty(value = "状态（0正常 1暂停）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "创建者")
    @TableField("create_by")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新者")
    @TableField("update_by")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "备注信息")
    @TableField("remark")
    private String remark;


    @Override
    public String toString() {
        return "SysJob{" +
        "jobId=" + jobId +
        ", jobName=" + jobName +
        ", jobGroup=" + jobGroup +
        ", invokeTarget=" + invokeTarget +
        ", cronExpression=" + cronExpression +
        ", misfirePolicy=" + misfirePolicy +
        ", concurrent=" + concurrent +
        ", status=" + status +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", remark=" + remark +
        "}";
    }
}
