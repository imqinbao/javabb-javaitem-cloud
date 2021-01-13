package cn.javabb.generator.core;

import cn.hutool.core.util.StrUtil;
import cn.javabb.generator.config.*;
import cn.javabb.generator.config.constants.ConstVal;
import cn.javabb.generator.config.rules.DbColumnType;
import cn.javabb.generator.config.rules.DbType;
import cn.javabb.generator.exception.GeneratorException;
import cn.javabb.generator.model.TableField;
import cn.javabb.generator.model.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/12 18:10
 */
@Slf4j
public class GeneratorHelper {
    /**
     * SQL连接
     */
     Connection connection;
    /**
     * 数据库配置
     */
    private DataSourceConfig dataSourceConfig;
    private PackageConfig packageConfig;
    private StrategyConfig strategyConfig;

    /**
     * 包配置详情
     */
    private Map<String, String> packageInfo;
    /**
     * 路径配置信息
     */
    private Map<String, String> pathInfo;
    private List<TableInfo> tableInfoList;
    /**
     * 所有包配置信息
     */
    public Map<String, String> getPackageInfo() {
        return packageInfo;
    }
    /**
     * 所有路径配置
     */
    public Map<String, String> getPathInfo() {
        return pathInfo;
    }

    public List<TableInfo> getTableInfoList() {
        return tableInfoList;
    }

    public GeneratorHelper setTableInfoList(List<TableInfo> tableInfoList) {
        this.tableInfoList = tableInfoList;
        return this;
    }

    public GeneratorHelper(DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig) {
        //this.genConfig = Optional.ofNullable(genConfig).orElseGet(GenConfig::new);
        this.strategyConfig = Optional.ofNullable(strategyConfig).orElseGet(StrategyConfig::new);
        this.dataSourceConfig = dataSourceConfig;
        connection = dataSourceConfig.getConn();
        handlerStrategy(strategyConfig);
    }

    /**
     * 获取所有的表
     *
     * @return
     */
    public List<TableInfo> getTableInfos(StrategyConfig config) {

        boolean isInclude = (null != config.getInclude() && config.getInclude().length > 0);
        boolean isExclude = (null != config.getExclude() && config.getExclude().length > 0);
        if (isInclude && isExclude) {
            throw new RuntimeException("<strategy> 标签中 <include> 与 <exclude> 只能配置一项！");
        }
        //所有表信息
        List<TableInfo> tableInfoList = new ArrayList<>();
        //需要反向生成或排除的表信息
        List<TableInfo> includeTableList = new ArrayList<>();
        List<TableInfo> excludeTableList = new ArrayList<>();

        DbType dbType = this.dataSourceConfig.getDbType();
        IDbQuery dbQuery = this.dataSourceConfig.getDbQuery();
        try {
            String tablesSql = dataSourceConfig.getDbQuery().tablesSql();
            StringBuilder sql = new StringBuilder(tablesSql);
            TableInfo tableInfo;
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
                    ResultSet results = preparedStatement.executeQuery();
            ) {
                while (results.next()) {
                    String tableName = results.getString(dbQuery.tableName());
                    if (StrUtil.isNotBlank(tableName)) {
                        tableInfo = new TableInfo();
                        tableInfo.setName(tableName);
                        String commentColumn = dbQuery.tableComment();
                        if (StrUtil.isNotBlank(commentColumn)) {
                            String tableComment = results.getString(commentColumn);
                            if (config.isSkipView() && "VIEW".equals(tableComment)) {
                                // 跳过视图
                                continue;
                            }
                            tableInfo.setComment(formatComment(tableComment));
                        }
                        //tableInfoList.add(tableInfo);
                        if (isInclude) {
                            for (String includeTable : config.getInclude()) {
                                // 忽略大小写等于
                                if (tableNameMatches(includeTable, tableName)) {
                                    includeTableList.add(tableInfo);
                                }
                            }
                        } else if (isExclude) {
                            for (String excludeTable : config.getExclude()) {
                                // 忽略大小写等于 或 正则 true
                                if (tableNameMatches(excludeTable, tableName)) {
                                    excludeTableList.add(tableInfo);
                                }
                            }
                        }
                        tableInfoList.add(tableInfo);
                    }else{
                        System.out.println("当前数据库为空");
                    }
                }
            }
            // 需要反向生成的表信息
            if (isExclude) {
                tableInfoList.removeAll(excludeTableList);
                includeTableList = tableInfoList;
            }
            if (!isInclude && !isExclude) {
                includeTableList = tableInfoList;
            }
            // 性能优化，只处理需执行表字段
            includeTableList.forEach(ti -> convertTableFields(ti, config));
        } catch (Exception e) {
            throw new GeneratorException("数据库表读取错误:" + e.getMessage());
        }
        return processTable(includeTableList,config);
    }

    /**
     * 处理对应的表名称
     *
     * @param tableList
     * @param config
     * @return
     */
    private List<TableInfo> processTable(List<TableInfo> tableList, StrategyConfig config) {
        String[] tablePrefix = config.getTablePrefix();
        for (TableInfo tableInfo : tableList) {
            //首字母大写,默认驼峰命名,转换为EntityName为首字母大写
            String name = tableInfo.getName();
            for (String pre : tablePrefix) {
                if (tableInfo.getName().startsWith(pre)) {
                    name = name.substring(pre.length());
                    break;
                }
            }
            String entityName = StrUtil.upperFirst(StrUtil.toCamelCase(name));
            tableInfo.setEntityName(entityName);
            tableInfo.setMapperName(entityName + "Mapper");
            tableInfo.setXmlName(StrUtil.format("{}Mapper", entityName));
            tableInfo.setServiceName(entityName + "Service");
            tableInfo.setControllerName(entityName + "Controller");
            tableInfo.setServiceImplName(StrUtil.format("{}ServiceImpl", entityName));
        }
        return tableList;
    }

    /**
     * 获取表字段信息
     * @param tableInfo
     * @param config
     * @return
     */
    private TableInfo convertTableFields(TableInfo tableInfo, StrategyConfig config) {
        boolean havePk = false;
        List<TableField> fieldList = new ArrayList<>();
        List<TableField> commonFieldList = new ArrayList<>();
        DbType dbType = this.dataSourceConfig.getDbType();
        IDbQuery dbQuery = this.dataSourceConfig.getDbQuery();
        String tableName = tableInfo.getName();
        try {
            String tableFieldsSql = dbQuery.tableFieldsSql();
            if (DbType.ORACLE.equals(dbType)) {
                tableName = tableName.toUpperCase();
                tableFieldsSql = String.format(tableFieldsSql.replace("#schema", dataSourceConfig.getSchemaName()), tableName);
            } else if (DbType.H2.equals(dbType)) {

            } else {
                tableFieldsSql = String.format(tableFieldsSql, tableName);
            }

            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(tableFieldsSql);
                    ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    TableField field = new TableField();
                    String columnName = resultSet.getString(dbQuery.fieldName());
                    // 避免多重主键设置，目前只取第一个找到ID，并放到list中的索引为0的位置
                    String key = resultSet.getString(dbQuery.fieldKey());
                    boolean isPk = StrUtil.isNotBlank(key) && "PRI".equals(key.toUpperCase());

                    //处理主键
                    if (isPk && !havePk) {
                        havePk = true;
                        field.setKeyFlag(true);
                        tableInfo.setHavePk(true);
                        field.setKeyIdentityFlag(dbQuery.isKeyIdentity(resultSet));
                    } else {
                        field.setKeyFlag(false);
                    }
                    field.setName(columnName);
                    String newColumnName = columnName;
                    IKeyWordsHandler keyWordsHandler = this.dataSourceConfig.getKeyWordsHandler();
                    if (keyWordsHandler != null) {
                        if (keyWordsHandler.isKeyWords(columnName)) {
                            System.err.println(String.format("当前表[%s]存在字段[%s]为数据库关键字或保留字!", tableName, columnName));
                            field.setKeyWords(true);
                            newColumnName = keyWordsHandler.formatColumn(columnName);
                        }
                    }
                    field.setColumnName(newColumnName);
                    field.setType(resultSet.getString(dbQuery.fieldType()));
                    //设置属性名
                    if (config.isEntityCamelModel()) {
                        field.setPropertyName(StrUtil.toCamelCase(field.getName()));
                    }else{
                        field.setPropertyName(field.getName());
                    }
                    field.setColumnType(DbColumnType.getDbColumnType(field.getType()));
                    String fieldCommentColumn = dbQuery.fieldComment();
                    if (StrUtil.isNotBlank(fieldCommentColumn)) {
                        field.setComment(formatComment(resultSet.getString(fieldCommentColumn)));
                    }
                    fieldList.add(field);
                }
            }
        } catch (SQLException e) {
            log.error("SQL Exception " + e.getMessage());
        }
        tableInfo.setFields(fieldList);
        return tableInfo;
    }

    /**
     * 连接父子包名
     *
     * @param parent     父包名
     * @param subPackage 子包名
     * @return 连接后的包名
     */
    private String joinPackage(String parent, String subPackage) {
        return StrUtil.isBlank(parent) ? subPackage : parent + "." + subPackage;
    }

    private void setPathInfo(Map<String, String> pathInfo, String template, String outputDir, String path, String module) {
        if (StrUtil.isNotBlank(template)) {
            pathInfo.put(path, joinPath(outputDir, packageInfo.get(module)));
        }
    }
    /**
     * 连接路径字符串
     *
     * @param parentDir   路径常量字符串
     * @param packageName 包名
     * @return 连接后的路径
     */
    private String joinPath(String parentDir, String packageName) {
        if (StrUtil.isBlank(parentDir)) {
            parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
        }
        if (!StringUtils.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", "\\" + File.separator);
        return parentDir + packageName;
    }


    private void handlerStrategy(StrategyConfig config) {
        this.tableInfoList = this.getTableInfos(config);
    }
    /**
     * 格式化数据库注释内容
     */
    public String formatComment(String comment) {
        return StrUtil.isBlank(comment) ? StringPool.EMPTY : comment.replaceAll("\r\n", "\t");
    }

    private void handleDataSource(DataSourceConfig config) {
        connection = config.getConn();
    }
    /**
     * 表名匹配
     *
     * @param setTableName 设置表名
     * @param dbTableName  数据库表单
     * @return ignore
     */
    private boolean tableNameMatches(String setTableName, String dbTableName) {
        return setTableName.equalsIgnoreCase(dbTableName)
                || StringUtils.matches(setTableName, dbTableName);
    }
}
