package cn.javabb.generator.model;

import cn.javabb.generator.config.TypeMapping;
import cn.javabb.generator.config.rules.IColumnType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @desc: 表字段信息
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/12 15:43
 */
@Data
@Accessors(chain = true)
public class TableField {
    /**
     * 是否是主键
     */
    private boolean keyFlag;
    /**
     * 是否自增主键
     */
    private boolean keyIdentityFlag;
    /**
     * 查询出来的列名,会转换成columnName
     */
    private String name;
    /**
     * 数据库列类型
     */
    private String type;
    private String propertyName;
    private IColumnType columnType;
    private String comment;
    private String fill;
    /**
     * 数据库字段
     */
    private String columnName;
    /**
     * 是否是数据库关键字
     */
    private boolean keyWords;
    /**
     * 自定义查询字段
     */
    private Map<String, Object> customMap;

    /**
     * 获取属性类型
     * @return
     */
    public String getPropertyType() {
        if (getJavaType().startsWith("java.lang.") || getJavaType().startsWith("java.util.")) {
            return getJavaType().substring(getJavaType().lastIndexOf(".")+1);
        } else {
            return getJavaType();
        }
    }

    /**
     * 按 JavaBean 规则来生成 get 和 set 方法后面的属性名称
     * 需要处理一下特殊情况：
     * <p>
     * 1、如果只有一位，转换为大写形式
     * 2、如果多于 1 位，只有在第二位是小写的情况下，才会把第一位转为小写
     * <p>
     * 并不建议在数据库对应的对象中使用基本类型，因此这里不会考虑基本类型的情况
     */
    public String getCapitalName() {
        if (propertyName.length() == 1) {
            return propertyName.toUpperCase();
        }
        if (Character.isLowerCase(propertyName.charAt(1))) {
            return Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
        }
        return propertyName;
    }

    public String getJavaType() {
        return TypeMapping.getJavaType(type);
    }

}
