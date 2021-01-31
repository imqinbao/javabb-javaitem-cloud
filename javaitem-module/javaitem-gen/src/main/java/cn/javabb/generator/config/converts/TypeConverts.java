package cn.javabb.generator.config.converts;

import cn.javabb.generator.config.ITypeConvert;
import cn.javabb.generator.config.rules.DbType;

/**
 * @desc:   类型转换器
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/15 22:43
 */
public class TypeConverts {

    /**
     * 查询数据库类型对应的类型转换器
     *
     * @param dbType 数据库类型
     * @return 返回转换器
     */
    public static ITypeConvert getTypeConvert(DbType dbType) {
        switch (dbType) {
            case HSQL:
            case H2:
            case DB2:
            case ORACLE:
            case MYSQL:
                return MySqlTypeConvert.INSTANCE;
        }
        return null;
    }

}
