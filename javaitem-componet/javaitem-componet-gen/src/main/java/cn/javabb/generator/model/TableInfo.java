package cn.javabb.generator.model;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/12 15:40
 */
@Data
@Accessors(chain = true)
public class TableInfo {
    private final Set<String> importPackages = new HashSet();
    //表名
    private String name;
    //表注释
    private String comment;
    //实体名字
    private String entityName;
    private String mapperName;
    private String xmlName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;
    private List<TableField> fields;
    private boolean havePk;
    /**
     * 公共字段
     */
    private List<TableField> commonFields;
    private String fieldNames;



    public TableInfo setImportPackages(String pkg) {
        if (importPackages.contains(pkg)) {
            return this;
        } else {
            importPackages.add(pkg);
            return this;
        }
    }

    public TableInfo setFields(List<TableField> fields) {
        this.fields = fields;
        if (CollectionUtils.isNotEmpty(fields)) {
            // 收集导入包信息
            for (TableField field : fields) {
                if (null != field.getJavaType() && field.getJavaType().startsWith("java.util.")) {
                    importPackages.add(field.getJavaType());
                }
                if (field.isKeyFlag()) {
                    //主键

                }else{
                    //普通字段
                    //importPackages.add(field.getColumnType().getPkg());
                }
            }
        }

        return this;
    }

    /**
     * 转换filed实体为 xml mapper 中的 base column 字符串信息
     */
    public String getFieldNames() {
        if (StrUtil.isBlank(fieldNames)
                && CollectionUtils.isNotEmpty(fields)) {
            StringBuilder names = new StringBuilder();
            IntStream.range(0, fields.size()).forEach(i -> {
                TableField fd = fields.get(i);
                if (i == fields.size() - 1) {
                    names.append(fd.getColumnName());
                } else {
                    names.append(fd.getColumnName()).append(", ");
                }
            });
            fieldNames = names.toString();
        }
        return fieldNames;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "importPackages=" + importPackages +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", entityName='" + entityName + '\'' +
                ", mapperName='" + mapperName + '\'' +
                ", xmlName='" + xmlName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", serviceImplName='" + serviceImplName + '\'' +
                ", controllerName='" + controllerName + '\'' +
                ", fields=" + fields +
                ", commonFields=" + commonFields +
                ", fieldNames='" + fieldNames + '\'' +
                '}';
    }
}
