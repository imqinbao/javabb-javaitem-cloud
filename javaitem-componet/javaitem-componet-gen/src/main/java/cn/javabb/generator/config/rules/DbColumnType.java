package cn.javabb.generator.config.rules;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/12 16:14
 */
public enum DbColumnType implements IColumnType {

    BASE_BYTE("byte", (String)null),
    BASE_SHORT("short", (String)null),
    BASE_CHAR("char", (String)null),
    BASE_INT("int", (String)null),
    BASE_LONG("long", (String)null),
    BASE_FLOAT("float", (String)null),
    BASE_DOUBLE("double", (String)null),
    BASE_BOOLEAN("boolean", (String)null),
    BYTE("Byte", (String)null),
    SHORT("Short", (String)null),
    CHARACTER("Character", (String)null),
    INTEGER("Integer", (String)null),
    LONG("Long", (String)null),
    FLOAT("Float", (String)null),
    DOUBLE("Double", (String)null),
    BOOLEAN("Boolean", (String)null),
    STRING("String", (String)null),
    BYTE_ARRAY("byte[]", (String)null),
    OBJECT("Object", (String)null),
    DATE("Date", "java.util.Date"),
    TIMESTAMP("Timestamp", "java.sql.Timestamp"),
    BLOB("Blob", "java.sql.Blob"),
    CLOB("Clob", "java.sql.Clob"),
    LOCAL_DATE("LocalDate", "java.time.LocalDate"),
    LOCAL_TIME("LocalTime", "java.time.LocalTime"),
    YEAR("Year", "java.time.Year"),
    YEAR_MONTH("YearMonth", "java.time.YearMonth"),
    LOCAL_DATE_TIME("LocalDateTime", "java.time.LocalDateTime"),
    INSTANT("Instant", "java.time.Instant"),
    BIG_INTEGER("BigInteger", "java.math.BigInteger"),
    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal")

    ;

    private final String type;
    private final String pkg;

    private DbColumnType(final String type, final String pkg) {
        this.type = type;
        this.pkg = pkg;
    }

    public String getType() {
        return this.type;
    }

    public String getPkg() {
        return this.pkg;
    }

    public static DbColumnType getDbColumnType(String fieldType) {
        for (DbColumnType t : DbColumnType.values()) {
            if (fieldType.contains(t.getType())) {
                return t;
            }
        }
        return STRING;
    }
}
