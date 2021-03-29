package cn.javabb.job.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
/**
 * 定时任务调度日志表
 *
 * @author Abcd
 * @since 2021-03-09 10:08:18
 */
@Data
@Accessors(chain = true)
@TableName("sys_job_log")
@ApiModel(value="SysJobLog实体类", description="定时任务调度日志表")
public class SysJobLog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "任务日志ID")
    @TableId(value = "job_log_id", type = IdType.AUTO)
    private Long jobLogId;

    @ApiModelProperty(value = "任务名称")
    @TableField("job_name")
    private String jobName;

    @ApiModelProperty(value = "任务组名")
    @TableField("job_group")
    private String jobGroup;

    @ApiModelProperty(value = "调用目标字符串")
    @TableField("invoke_target")
    private String invokeTarget;

    @ApiModelProperty(value = "日志信息")
    @TableField("job_message")
    private String jobMessage;

    @ApiModelProperty(value = "执行状态（0正常 1失败）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "异常信息")
    @TableField("exception_info")
    private String exceptionInfo;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    /** 开始时间 */
    @TableField(exist = false)
    private Date startTime;

    /** 停止时间 */
    @TableField(exist = false)
    private Date stopTime;
    @Override
    public String toString() {
        return "SysJobLog{" +
        "jobLogId=" + jobLogId +
        ", jobName=" + jobName +
        ", jobGroup=" + jobGroup +
        ", invokeTarget=" + invokeTarget +
        ", jobMessage=" + jobMessage +
        ", status=" + status +
        ", exceptionInfo=" + exceptionInfo +
        ", createTime=" + createTime +
        "}";
    }
}
