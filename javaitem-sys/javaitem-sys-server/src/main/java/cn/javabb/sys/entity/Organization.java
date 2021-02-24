package cn.javabb.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
/**
 * 组织机构
 *
 * @author Javabb Generator
 * @since 2021-02-01 20:14:50
 */
@Data
@Accessors(chain = true)
@TableName("sys_organization")
@ApiModel(value="Organization实体类", description="组织机构")
public class Organization implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "机构id")
    @TableId(value = "org_id", type = IdType.AUTO)
    private Integer orgId;

    @ApiModelProperty(value = "上级id,0是顶级")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "机构名称")
    @TableField("org_name")
    private String orgName;

    @ApiModelProperty(value = "机构全称")
    @TableField("org_full_name")
    private String orgFullName;

    @ApiModelProperty(value = "机构代码")
    @TableField("org_code")
    private String orgCode;

    @ApiModelProperty(value = "机构类型")
    @TableField("org_type")
    private Integer orgType;

    @ApiModelProperty(value = "负责人id")
    @TableField("leader_id")
    private Integer leaderId;

    @ApiModelProperty(value = "排序号")
    @TableField("sort_no")
    private Integer sortNo;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "是否删除,0否,1是")
    @TableField("deleted")
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;


    @Override
    public String toString() {
        return "Organization{" +
        "orgId=" + orgId +
        ", parentId=" + parentId +
        ", orgName=" + orgName +
        ", orgFullName=" + orgFullName +
        ", orgCode=" + orgCode +
        ", orgType=" + orgType +
        ", leaderId=" + leaderId +
        ", sortNo=" + sortNo +
        ", remark=" + remark +
        ", deleted=" + deleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
