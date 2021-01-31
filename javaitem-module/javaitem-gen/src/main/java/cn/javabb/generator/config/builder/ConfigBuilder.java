package cn.javabb.generator.config.builder;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.javabb.generator.config.*;
import cn.javabb.generator.config.rules.DbColumnType;
import cn.javabb.generator.config.rules.DbType;
import cn.javabb.generator.exception.GeneratorException;
import cn.javabb.generator.model.TableField;
import cn.javabb.generator.model.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/12 18:10
 */
@Slf4j
public class ConfigBuilder {
    /**
     * SQL连接
     */
    Connection connection;
    GenConfig genConfig;
    /**
     * 数据库配置
     */
    DataSourceConfig dataSourceConfig;
    PackageConfig packageConfig;

    public GenConfig getGenConfig(){
        return this.genConfig;
    }

    /**
     * 初始化
     */
    public void init(DataSourceConfig dsc) {
        connection = dsc.getConn();
    }

    public ConfigBuilder(DataSourceConfig dataSourceConfig) {
        this.init(dataSourceConfig);
    }

    /**
     * 获取所有的表
     *
     * @return
     */
    public List<TableInfo> getTableInfos() {
        List<TableInfo> tableInfoList = new ArrayList<>();
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
                            tableInfo.setComment(formatComment(tableComment));
                        }
                        tableInfoList.add(tableInfo);
                    }
                }
            }
        } catch (Exception e) {
            throw new GeneratorException("数据库表读取错误:" + e.getMessage());
        }

        if (CollUtil.isNotEmpty(tableInfoList)) {
            tableInfoList.forEach(tableInfo -> convertTableFields(tableInfo, genConfig));
        }
        return processTable(tableInfoList,genConfig);
    }

    /**
     * 处理对应的表名称
     *
     * @param tableList
     * @param config
     * @return
     */
    private List<TableInfo> processTable(List<TableInfo> tableList, GenConfig config) {

        for (TableInfo tableInfo : tableList) {
            //首字母大写
            String entityName = StrUtil.upperFirst(StrUtil.toCamelCase(tableInfo.getName()));
            tableInfo.setEntityName(entityName);
            tableInfo.setMapperName(entityName + "Mapper");
            tableInfo.setServiceName(entityName + "Service");
            tableInfo.setControllerName(entityName + "Controller");
        }

        return tableList;
    }

    /**
     * 获取表字段信息
     * @param tableInfo
     * @param config
     * @return
     */
    private TableInfo convertTableFields(TableInfo tableInfo, GenConfig config) {
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
                    //设置属性
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
     * 格式化数据库注释内容
     */
    public String formatComment(String comment) {
        return StrUtil.isBlank(comment) ? StringPool.EMPTY : comment.replaceAll("\r\n", "\t");
    }

    private void handleDataSource(DataSourceConfig config) {
        connection = config.getConn();
    }

}
