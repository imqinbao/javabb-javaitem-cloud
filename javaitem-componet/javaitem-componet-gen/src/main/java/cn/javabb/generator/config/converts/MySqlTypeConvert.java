package cn.javabb.generator.config.converts;

import cn.javabb.generator.config.ITypeConvert;
import cn.javabb.generator.config.rules.IColumnType;

/**
 * @desc: Mysql 数据库字段类型转换
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/15 22:48
 */
public class MySqlTypeConvert implements ITypeConvert {
    public static final MySqlTypeConvert INSTANCE = new MySqlTypeConvert();

    @Override
    public IColumnType processTypeConvert(String fieldType) {
        return null;
    }

}
