package cn.javabb.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/18 12:22
 */
@Data
@Accessors(chain = true)
public class GlobalConfig {
    /**
     * 生成文件的输出目录【默认 D 盘根目录】
     */
    private String outputDir = "D://";

    /**
     * 是否覆盖已有文件
     */
    private boolean fileOverride = false;
    /**
     * 各层文件名称方式，例如： %sAction 生成 UserAction
     * %s 为占位符
     */
    private String entityName;
    private String mapperName;
    private String xmlName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;
}
