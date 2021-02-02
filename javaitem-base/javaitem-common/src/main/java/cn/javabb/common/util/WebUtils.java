package cn.javabb.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/02/02 14:28
 */
public class WebUtils {
    /**
     * 空字符串
     */
    private static final String NULLSTR = "";

    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';

    /**
     * 星号
     */
    private static final String STAR = "*";

    /**
     * 查找指定字符串是否匹配指定字符串列表中的任意一个字符串
     *
     * @param str  指定字符串
     * @param strs 需要检查的字符串数组
     * @return 是否匹配
     */
    public static boolean matches(String str, List<String> strs) {
        if (StrUtil.isEmpty(str) || CollUtil.isEmpty(strs)) {
            return false;
        }
        for (String testStr : strs) {
            if (matches(str, testStr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找指定字符串是否匹配指定字符串数组中的任意一个字符串
     *
     * @param str  指定字符串
     * @param strs 需要检查的字符串数组
     * @return 是否匹配
     */
    public static boolean matches(String str, String... strs) {
        if (StrUtil.isEmpty(str) || StrUtil.isAllEmpty(strs)) {
            return false;
        }
        for (String testStr : strs) {
            if (matches(str, testStr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找指定字符串是否匹配
     *
     * @param str     指定字符串
     * @param pattern 需要检查的字符串
     * @return 是否匹配
     */
    public static boolean matches(String str, String pattern) {
        if (StrUtil.isEmpty(pattern) || StrUtil.isEmpty(str)) {
            return false;
        }

        pattern = pattern.replaceAll("\\s*", ""); // 替换空格
        int beginOffset = 0; // pattern截取开始位置
        int formerStarOffset = -1; // 前星号的偏移位置
        int latterStarOffset = -1; // 后星号的偏移位置

        String remainingURI = str;
        String prefixPattern = "";
        String suffixPattern = "";

        boolean result = false;
        do {
            formerStarOffset = StrUtil.indexOf(pattern, STAR, beginOffset, false);
            prefixPattern = StrUtil.sub(pattern, beginOffset, formerStarOffset > -1 ? formerStarOffset : pattern.length());

            // 匹配前缀Pattern
            result = remainingURI.contains(prefixPattern);
            // 已经没有星号，直接返回
            if (formerStarOffset == -1) {
                return result;
            }

            // 匹配失败，直接返回
            if (!result)
                return false;

            if (StrUtil.isNotEmpty(prefixPattern)) {
                remainingURI = StrUtil.subAfter(str, prefixPattern, false);
            }

            // 匹配后缀Pattern
            latterStarOffset = StrUtil.indexOf(pattern, STAR, formerStarOffset + 1, false);
            suffixPattern = StrUtil.sub(pattern, formerStarOffset + 1, latterStarOffset > -1 ? latterStarOffset : pattern.length());

            result = remainingURI.contains(suffixPattern);
            // 匹配失败，直接返回
            if (!result)
                return false;

            if (StrUtil.isNotEmpty(suffixPattern)) {
                remainingURI = StrUtil.subAfter(str, suffixPattern, false);
            }

            // 移动指针
            beginOffset = latterStarOffset + 1;

        }
        while (StrUtil.isNotEmpty(suffixPattern) && StrUtil.isNotEmpty(remainingURI));

        return true;
    }

}
