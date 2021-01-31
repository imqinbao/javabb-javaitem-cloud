package cn.javabb.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/18 16:22
 */
@Data
@Accessors(chain = true)
public class StrategyConfig {
    private boolean entityCamelModel = true;
    /**
     * 表前缀
     */
    private String[] tablePrefix;
    /**
     * 字段前缀
     */
    private String[] fieldPrefix;
    /**
     * 需要包含的表名，允许正则表达式（与exclude二选一配置）<br/>
     */
    private String[] include = null;
    /**
     * 需要排除的表名，允许正则表达式<br/>
     */
    private String[] exclude = null;
}
