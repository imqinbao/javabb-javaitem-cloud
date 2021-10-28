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
 * 字典
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Data
@Accessors(chain = true)
@TableName("sys_dict")
@ApiModel(value="Dict实体类", description="字典")
public class DictDO extends BaseDO {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "字典id")
    @TableId
    private String id;

    @ApiModelProperty(value = "字典标识")
    @TableField("dict_code")
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    @TableField("dict_name")
    private String dictName;

    @ApiModelProperty(value = "排序号")
    @TableField("order_no")
    private Integer orderNo;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @Override
    public String toString() {
        return "DictDO{" +
                "id=" + id +
                ", dictCode='" + dictCode + '\'' +
                ", dictName='" + dictName + '\'' +
                ", orderNo=" + orderNo +
                ", remark='" + remark + '\'' +
                '}';
    }
}
