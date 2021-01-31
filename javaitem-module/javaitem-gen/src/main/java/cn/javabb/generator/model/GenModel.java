package cn.javabb.generator.model;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 项目生成模块划分配置
 */
@Data
@Accessors(chain = true)
public class GenModel implements Serializable {
    /**
     * 模块名称
     */
    private String modelName;
    /**
     * 模块所有表名
     */
    private List<String> tables;
    /**
     * 需要去掉的表前缀名
     */
    private List<String> prefix;

    public String[] getTablesArray() {
        return tables.toArray(new String[0]);
    }

    public String[] getPrefixArray() {
        if(CollUtil.isNotEmpty(prefix)){
           return prefix.toArray(new String[0]);
        }
        return null;
    }
}
