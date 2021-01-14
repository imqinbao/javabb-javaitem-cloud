package cn.javabb.test;

import cn.javabb.generator.config.GenConfig;
import cn.javabb.generator.util.GenUtil;
import org.junit.Test;

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
        genConfig.setProjectName("test" );
        genConfig.setTplName("mybatisplus-tpl" );
        new GenUtil(1).gen(genConfig);

    }

}
