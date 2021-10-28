package cn.javabb.common.repository.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/10/27 16:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDO extends Model {

    private static final long serialVersionUID = -4991614285972304618L;

    @ApiModelProperty("逻辑删除：0未删除，1已删除")
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("创建时间")
    @TableField(
            value = "create_time",
            fill = FieldFill.INSERT
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("创建用户")
    @TableField(
            value = "create_user",
            fill = FieldFill.INSERT
    )
    private String createUser;

    @ApiModelProperty("更新时间")
    @TableField(
            value = "update_time",
            fill = FieldFill.UPDATE
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty("更新用户")
    @TableField(
            value = "update_user",
            fill = FieldFill.UPDATE
    )
    private String updateUser;

    @Override
    public String toString() {
        return "BaseDO{" +
                "deleted=" + deleted +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}
