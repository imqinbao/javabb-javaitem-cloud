package cn.javabb.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
/**
 * 字典
 *
 * @author Javabb Generator
 * @since 2021-01-31 23:52:16
 */
@Data
@Accessors(chain = true)
@TableName("sys_dictionary")
@ApiModel(value="Dictionary实体类", description="字典")
public class Dictionary implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "字典id")
    @TableId(value = "dict_id", type = IdType.AUTO)
    private String dictId;

    @ApiModelProperty(value = "字典标识")
    @TableField("dict_code")
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    @TableField("dict_name")
    private String dictName;

    @ApiModelProperty(value = "排序号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "是否删除,0否,1是")
    @TableField("deleted")
    private String deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;


    @Override
    public String toString() {
        return "Dictionary{" +
        "dictId=" + dictId +
        ", dictCode=" + dictCode +
        ", dictName=" + dictName +
        ", orderNo=" + orderNo +
        ", remark=" + remark +
        ", deleted=" + deleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
