package cn.javabb.generator.config;

import java.util.List;

/**
 * @desc:   关键词
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/15 21:11
 */
public interface IKeyWordsHandler {

    /**
     * 获取关键字
     *
     * @return 关键字集合
     */
    List<String> getKeyWords();

    /**
     * 格式化关键字格式
     *
     * @return 格式
     */
    String formatStyle();

    /**
     * 是否为关键字
     *
     * @param columnName 字段名称
     * @return 是否为关键字
     */
    boolean isKeyWords(String columnName);

    /**
     * 格式化字段,将格式化字段加上``
     *
     * @param columnName 字段名称
     * @return 格式化字段
     */
    default String formatColumn(String columnName) {
        return String.format(formatStyle(), columnName);
    }
}
