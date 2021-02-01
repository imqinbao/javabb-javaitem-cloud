package cn.javabb.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
/**
 * 登录日志
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Data
@Accessors(chain = true)
@TableName("sys_login_record")
@ApiModel(value="LoginRecord实体类", description="登录日志")
public class LoginRecord implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户账号")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "操作系统")
    @TableField("os")
    private String os;

    @ApiModelProperty(value = "设备名")
    @TableField("device")
    private String device;

    @ApiModelProperty(value = "浏览器类型")
    @TableField("browser")
    private String browser;

    @ApiModelProperty(value = "ip地址")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "操作类型,0登录成功,1登录失败,2退出登录,3刷新token")
    @TableField("oper_type")
    private Integer operType;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "操作时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;


    @Override
    public String toString() {
        return "LoginRecord{" +
        "id=" + id +
        ", username=" + username +
        ", os=" + os +
        ", device=" + device +
        ", browser=" + browser +
        ", ip=" + ip +
        ", operType=" + operType +
        ", remark=" + remark +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
