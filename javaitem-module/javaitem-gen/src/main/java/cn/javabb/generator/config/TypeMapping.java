package cn.javabb.generator.config;

import cn.hutool.core.util.ReUtil;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * SQL type和java type映射关系
 *
 */
public class TypeMapping {

    private static final String STRING_TYPE = "java.lang.String";
    private static Map<Integer, String> mapping = new HashMap<>();

    static {
        mapping.put(Types.TINYINT, "java.lang.Boolean");
        mapping.put(Types.SMALLINT, "java.lang.Integer");
        mapping.put(Types.INTEGER, "java.lang.Integer");
        mapping.put(Types.BIGINT, "java.lang.Long");
        mapping.put(Types.REAL, "java.lang.Float");
        mapping.put(Types.FLOAT, "java.lang.Double");
        mapping.put(Types.DOUBLE, "java.lang.Double");
        mapping.put(Types.DECIMAL, "java.math.BigDecimal");
        mapping.put(Types.NUMERIC, "java.math.BigDecimal");
        mapping.put(Types.BIT, "java.lang.Boolean");
        mapping.put(Types.BOOLEAN, "java.lang.Boolean");
        mapping.put(Types.CHAR, "java.lang.String");
        mapping.put(Types.VARCHAR, "java.lang.String");
        mapping.put(Types.LONGVARCHAR, "java.lang.String");
        mapping.put(Types.BINARY, "byte[]");
        mapping.put(Types.VARBINARY, "byte[]");
        mapping.put(Types.LONGVARBINARY, "byte[]");
        mapping.put(Types.DATE, "java.util.Date");
        mapping.put(Types.TIME, "java.util.Time");
        mapping.put(Types.TIMESTAMP, "java.util.Date");
        mapping.put(Types.CLOB, "java.sql.Clob");
        mapping.put(Types.BLOB, "java.sql.Blob");
        mapping.put(Types.ARRAY, "java.sql.Array");
        mapping.put(Types.REF, "java.sql.Ref");
        mapping.put(Types.STRUCT, "java.lang.Object");
        mapping.put(Types.JAVA_OBJECT, "java.lang.Object");

    }

    public static String getJavaType(String sqlType) {
        // 先把sql type中的()去掉
        String regex = "\\([^)]*\\)";
        sqlType = ReUtil.delAll(regex, sqlType);
        if (null == sqlType || sqlType.isEmpty()) {
            return STRING_TYPE;
        }
        JdbcType jdbcType = JdbcType.getJdbcTypeBySqlType(sqlType);
        if (mapping.containsKey(jdbcType.getCode())) {
            return mapping.get(jdbcType.getCode());
        }
        return STRING_TYPE;
    }

    public static void main(String[] args) {
        System.out.println(getJavaType("timestamp"));

    }
}
