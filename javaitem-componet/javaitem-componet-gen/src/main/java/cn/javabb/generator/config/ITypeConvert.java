package cn.javabb.generator.config;

import cn.javabb.generator.config.rules.IColumnType;
import cn.javabb.generator.model.TableField;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/15 22:44
 */
public interface ITypeConvert {
    /**
     * 执行类型转换
     *
     * @param tableField   字段列信息
     * @return ignore
     */
    default IColumnType processTypeConvert(TableField tableField) {
        return processTypeConvert(tableField.getType());
    }

    /**
     * 执行类型转换
     *
     * @param fieldType    字段类型
     * @return ignore
     */
    IColumnType processTypeConvert(String fieldType);

}
