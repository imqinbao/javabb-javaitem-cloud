package cn.javabb.generator.util;

import java.util.Arrays;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/16 00:38
 */
public class StrUtil extends cn.hutool.core.util.StrUtil {
    /**
     * 自己写个转驼峰方式,也可以直接用 StrUtil.toCamelCase()
     * @param name
     * @return
     */
    public static String underlineToCamel(String name) {
        if (StrUtil.isEmpty(name)) {
            return StrUtil.EMPTY;
        }
        String tempName = name;
        StringBuilder result = new StringBuilder();
        String[] camels = tempName.split("_");
        Arrays.stream(camels).filter(camel-> StrUtil.isNotBlank(camel)).forEach(camel ->{
            if (result.length() == 0) {
                //第一个驼峰片段
                result.append(camel);
            }else{
                result.append(StrUtil.upperFirst(camel));
            }
        });
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(underlineToCamel("sys_blog_name"));
        System.out.println(StrUtil.toCamelCase("Blog_name"));
    }

}
