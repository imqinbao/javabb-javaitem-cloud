package cn.javabb.test;

import cn.javabb.generator.config.GenConfig;
import cn.javabb.generator.model.GenModel;
import cn.javabb.generator.util.GenUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/15 00:17
 */
public class GenTest {

    @Test
    public void test() throws Exception {

        GenConfig genConfig = new GenConfig();
        genConfig.setDebugModel(true);
        genConfig.setProjectName("test")
                .setEntityLombokModel(true)
                .setSwagger(true);
        genConfig.setTplName("mybatisplus-tpl" );
        genConfig.setDbUrl("jdbc:mysql://59.110.236.115:3306/db_portal?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8")
                .setDbUserName("root")
                .setDbPassword("mima123")
                .setDbDriverName("com.mysql.cj.jdbc.Driver");
        genConfig.setAuthor("javabb")
                .setGroupId("cn.javabb");

        GenModel genModel = new GenModel()
                .setModelName("sys")
                //.setPrefix(Arrays.asList("sys_"))
                .setTables(Arrays.asList("sys_user", "sys_role"));
        GenModel genModel2 = new GenModel()
                .setModelName("biz")
                //.setPrefix(Arrays.asList("biz_"))
                .setTables(Arrays.asList("biz_blog", "biz_post"));
        List<GenModel> models = Arrays.asList(genModel,genModel2);

        genConfig.setModels(models);

        new GenUtil(1).gen(genConfig);

    }

}
