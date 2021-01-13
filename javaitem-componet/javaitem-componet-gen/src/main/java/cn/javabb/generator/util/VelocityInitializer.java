package cn.javabb.generator.util;

import cn.javabb.generator.config.constants.ConstVal;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Properties;

/**
 * VelocityEngine工厂
 * 
 */
public class VelocityInitializer
{
    /**
     * 初始化vm方法
     */
    public static void initVelocity(String tplPath)
    {
        Properties p = new Properties();
        try
        {
            //设置velocity资源加载方式为file
            //p.setProperty(Velocity.RESOURCE_LOADER, "file");
            // 加载classpath目录下的vm文件
            //p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, tplPath);
            // 定义字符集
            p.setProperty(Velocity.ENCODING_DEFAULT, ConstVal.UTF8);
            p.setProperty(Velocity.OUTPUT_ENCODING, ConstVal.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
           /* Template t = velocityEngine.getTemplate("controller.java.vm");
            System.out.println(t);*/
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        initVelocity("D:/javabb-generator/template/tk-mapper-tpl");
        Template template = Velocity.getTemplate("controller.java.vm", "UTF-8");
        VelocityContext context = new VelocityContext();
        context.put("table", "1");
        StringWriter sw = new StringWriter();
        template.merge(context,sw);

    }
}
