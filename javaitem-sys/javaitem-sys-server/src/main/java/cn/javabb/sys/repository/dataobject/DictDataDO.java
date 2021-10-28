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
 * 字典项
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Data
@Accessors(chain = true)
@TableName("sys_dict_data")
@ApiModel(value="DictionaryData实体类", description="字典项")
public class DictDataDO extends BaseDO {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "字典项id")
    @TableId
    private String id;

    @ApiModelProperty(value = "字典id")
    @TableField("dict_id")
    private String dictId;

    @ApiModelProperty(value = "字典项标识")
    @TableField("dict_data_code")
    private String dictDataCode;

    @ApiModelProperty(value = "字典项名称")
    @TableField("dict_data_name")
    private String dictDataName;

    @ApiModelProperty(value = "排序号")
    @TableField("sort_no")
    private Integer sortNo;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;


    @ApiModelProperty(value = "字典代码")
    @TableField(exist = false)
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    @TableField(exist = false)
    private String dictName;

    @Override
    public String toString() {
        return "DictDataDO{" +
                "id='" + id + '\'' +
                ", dictId='" + dictId + '\'' +
                ", dictDataCode='" + dictDataCode + '\'' +
                ", dictDataName='" + dictDataName + '\'' +
                ", sortNo=" + sortNo +
                ", remark='" + remark + '\'' +
                ", dictCode='" + dictCode + '\'' +
                ", dictName='" + dictName + '\'' +
                '}';
    }
}
