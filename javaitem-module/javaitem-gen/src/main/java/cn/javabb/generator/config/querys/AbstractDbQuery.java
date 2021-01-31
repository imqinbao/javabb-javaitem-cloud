package cn.javabb.generator.config.querys;

import cn.javabb.generator.config.IDbQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @desc:   表数据查询抽象类
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/12 17:16
 */
public abstract class AbstractDbQuery implements IDbQuery{

   @Override
    public boolean isKeyIdentity(ResultSet results) throws SQLException {
        return false;
    }


    @Override
    public String[] fieldCustom() {
        return null;
    }
}
