package cn.javabb.generator.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @desc: 全局工具类
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/19 11:27
 */
@SuppressWarnings("unused")
public class GlobalUtil {
    /**
     * 获取当前时间
     */
    public String now(){
        return DateUtil.now();
    }

    /**
     * 首字母大写方法
     */
    public String upperFirst(String name) {
        return StrUtil.upperFirst(name);
    }
    /**
     * 首字母小写方法
     */
    public String lowerFirst(String name) {
        return StrUtil.lowerFirst(name);
    }
    /**
     * 通过java全名获取类名
     */
    public String getClsNameByFullName(String fullName) {
        return fullName.substring(fullName.lastIndexOf('.') + 1);
    }
    /**
     * 任意对象合并工具类
     *
     * @param objects 任意对象
     * @return 合并后的字符串结果
     */
    public String append(Object... objects) {

        if (objects == null || objects.length == 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (Object s : objects) {
            if (s != null) {
                builder.append(s);
            }
        }
        return builder.toString();
    }


}
