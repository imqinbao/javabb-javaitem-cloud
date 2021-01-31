package cn.javabb.generator.config;

import com.baomidou.mybatisplus.annotation.DbType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/12 17:05
 */
public interface IDbQuery {
    /**
     * 数据库类型
     *
     * @deprecated 3.3.1 {@link DataSourceConfig#setDbType(DbType)}
     */
    @Deprecated
    default DbType dbType() {
        return null;
    }
    /**
     * 表信息查询 SQL
     */
    String tablesSql();


    /**
     * 表字段信息查询 SQL
     */
    String tableFieldsSql();


    /**
     * 表名称
     */
    String tableName();


    /**
     * 表注释
     */
    String tableComment();


    /**
     * 字段名称
     */
    String fieldName();


    /**
     * 字段类型
     */
    String fieldType();


    /**
     * 字段注释
     */
    String fieldComment();


    /**
     * 主键字段
     */
    String fieldKey();


    /**
     * 判断主键是否为identity
     *
     * @param results ResultSet
     * @return 主键是否为identity
     * @throws SQLException ignore
     */
    boolean isKeyIdentity(ResultSet results) throws SQLException;


    /**
     * 自定义字段名称
     */
    String[] fieldCustom();
}
