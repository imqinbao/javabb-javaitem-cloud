package cn.javabb.common.util;

import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/02/19 23:05
 */
public class CommonUtil {

    /**
     * 判断excel某列是否有空值
     */
    public static String excelCheckBlank(List<List<Object>> list, int startRow, int... cols) {
        StringBuilder sb = new StringBuilder();
        for (int col : cols) {
            for (int i = 0; i < list.size(); i++) {
                Object value = list.get(i).get(col);
                if (value == null || StrUtil.isBlank(value.toString())) {
                    if (sb.length() != 0) sb.append("\r\n");
                    sb.append("第").append(i + startRow + 1).append("行第");
                    sb.append(col + 1).append("列不能为空.");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 判断excel某列是否有重复值
     */
    public static String excelCheckRepeat(List<List<Object>> list, int startRow, int... cols) {
        StringBuilder sb = new StringBuilder();
        for (int col : cols) {
            for (int i = 0; i < list.size(); i++) {
                Object value = list.get(i).get(col);
                for (int j = 0; j < list.size(); j++) {
                    if (i != j && value != null && value.equals(list.get(j).get(col))) {
                        if (sb.length() != 0) sb.append("\r\n");
                        sb.append("第").append(i + startRow + 1).append("行第").append(col + 1).append("列与第");
                        sb.append(j + startRow + 1).append("行第").append(col + 1).append("列重复.");
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 检查list集合中元素字段是否有重复
     *
     * @param list  集合
     * @param field 字段名称
     * @return 返回重复的元素
     */
    public static <T> T listCheckRepeat(List<T> list, String field) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j) {
                    Object value = getFieldValue(list.get(i), field);
                    if (value != null && value.equals(getFieldValue(list.get(j), field))) {
                        return list.get(j);
                    }
                }
            }
        }
        return null;
    }

    /**
     * 检查list集合中元素字段是否有重复
     *
     * @param list   集合
     * @param field  字段名称
     * @param zhName 字段中文名称，用于错误提示
     * @return 返回错误提示信息
     */
    public static <T> String listCheckRepeat(List<T> list, String field, String zhName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j) {
                    Object value = getFieldValue(list.get(i), field);
                    if (value != null && value.equals(getFieldValue(list.get(j), field))) {
                        if (sb.length() != 0) sb.append("\r\n");
                        sb.append("第").append(i + 1).append("条与第").append(j + 1).append("条数据的").append(zhName).append("重复.");
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 检查list集合中元素字段是否有空值
     *
     * @param list  集合
     * @param field 字段名称
     * @return 返回为空的元素
     */
    public static <T> T listCheckBlank(List<T> list, String field) {
        for (T t : list) {
            Object value = getFieldValue(t, field);
            if (value == null || StrUtil.isBlank(value.toString())) {
                return t;
            }
        }
        return null;
    }

    /**
     * 检查list集合中元素字段是否有空值
     *
     * @param list   集合
     * @param field  字段名称
     * @param zhName 字段中文名称，用于错误提示
     * @return 返回错误提示信息
     */
    public static <T> String listCheckBlank(List<T> list, String field, String zhName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Object value = getFieldValue(list.get(i), field);
            if (value == null || StrUtil.isBlank(value.toString())) {
                if (sb.length() != 0) sb.append("\r\n");
                sb.append("第").append(i + 1).append("条数据的").append(zhName).append("不能为空.");
            }
        }
        return sb.toString();
    }

    /**
     * 获取某个对象的某个字段的值
     */
    public static Object getFieldValue(Object t, String field) {
        if (t == null || field == null) return null;
        try {
            Field clazzField = t.getClass().getDeclaredField(field);
            clazzField.setAccessible(true);
            return clazzField.get(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
