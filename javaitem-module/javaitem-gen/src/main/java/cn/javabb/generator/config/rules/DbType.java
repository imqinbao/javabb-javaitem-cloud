package cn.javabb.generator.config.rules;

/**
 * @desc:   数据库类型
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/12 17:14
 */
public enum DbType {
    MYSQL("mysql", "MySql数据库"),
    ORACLE("oracle", "Oracle数据库"),
    DB2("db2", "DB2数据库"),
    H2("h2", "H2数据库"),
    HSQL("hsql", "HSQL数据库"),
    SQLITE("sqlite", "SQLite数据库"),
    POSTGRE_SQL("postgresql", "Postgre数据库"),
    SQL_SERVER("sqlserver", "SQLServer数据库"),
    OTHER("other", "其他数据库");

    private final String db;
    private final String desc;

    public static DbType getDbType(String dbType) {
        DbType[] dts = values();
       DbType[] var2 = dts;
        int var3 = dts.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            DbType dt = var2[var4];
            if (dt.getDb().equalsIgnoreCase(dbType)) {
                return dt;
            }
        }

        return OTHER;
    }

    public String getDb() {
        return this.db;
    }

    public String getDesc() {
        return this.desc;
    }

    private DbType(final String db, final String desc) {
        this.db = db;
        this.desc = desc;
    }
}
