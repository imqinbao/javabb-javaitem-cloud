package cn.javabb.generator.config.querys;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/12 17:27
 */
public class MySqlQuery extends AbstractDbQuery {

    @Override
    public String tablesSql() {
        return "show table status WHERE 1=1 ";
    }


    @Override
    public String tableFieldsSql() {
        return "select distinct column_name,data_type,collation_name,is_nullable,column_key,extra,column_comment from information_schema.columns where table_name = '%s'";
    }


    @Override
    public String tableName() {
        return "NAME";
    }


    @Override
    public String tableComment() {
        return "COMMENT";
    }


    @Override
    public String fieldName() {
        return "column_name";
    }


    @Override
    public String fieldType() {
        return "data_type";
    }


    @Override
    public String fieldComment() {
        return "column_comment";
    }


    @Override
    public String fieldKey() {
        return "column_key";
    }

    /**
     * 判断是否为自增主键
     * @param results
     * @return
     * @throws SQLException
     */
    @Override
    public boolean isKeyIdentity(ResultSet results) throws SQLException {
        return "auto_increment".equals(results.getString("extra"));
    }
}
