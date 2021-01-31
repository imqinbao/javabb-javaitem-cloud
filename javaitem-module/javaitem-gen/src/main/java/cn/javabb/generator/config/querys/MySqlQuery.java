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
        return "show full fields from `%s`";
    }

    public String tableName() {
        return "NAME";
    }

    public String tableComment() {
        return "COMMENT";
    }

    public String fieldName() {
        return "FIELD";
    }

    public String fieldType() {
        return "TYPE";
    }

    public String fieldComment() {
        return "COMMENT";
    }

    public String fieldKey() {
        return "KEY";
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
