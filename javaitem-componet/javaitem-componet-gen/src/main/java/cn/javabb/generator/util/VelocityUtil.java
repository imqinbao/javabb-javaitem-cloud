package cn.javabb.generator.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.javabb.generator.config.GenConfig;
import cn.javabb.generator.model.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/11 13:47
 */
@Slf4j
public class VelocityUtil {
    /** 项目空间路径 */
    private static final String PROJECT_PATH = "main/java";

    /** mybatis空间路径 */
    private static final String MYBATIS_PATH = "main/resources/mapper";

    /** html空间路径 */
    private static final String TEMPLATES_PATH = "main/resources/templates";

    /** 解析参数模版 */
    public static VelocityContext prepareContext(TableInfo tableInfo, GenConfig genConfig) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("table", tableInfo);
        velocityContext.put("author", genConfig.getAuthor());
        velocityContext.put("packageName",genConfig.getPackageName());
        velocityContext.put("projectName", genConfig.getProjectName());
        return velocityContext;
    }

    public static void writer(Map<String, Object> objectMap, String templateName, String outputFile) throws Exception {

        if (!StringUtils.isEmpty(templateName)) {
            Template template = Velocity.getTemplate(templateName, ConstVal.UTF8);
            StringWriter writer = null;
            try {
                writer = new StringWriter();
                VelocityContext velocityContext = new VelocityContext(objectMap);
                // 添加全局工具类
                velocityContext.put("Util", new GlobalUtil());
                template.merge(velocityContext, writer);
                FileUtils.writeStringToFile(new File(outputFile), writer.toString(), Constants.UTF_8);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                IoUtil.close(writer);
            }
        }
        log.debug("模板:" + templateName + ";  文件:" + outputFile);
    }
    /**
     * 获取模板列表
     * @param tplPath
     * @return
     */
    public static List<String> getTemplateList(String tplPath){
        List<String> templates = new ArrayList<>();
        List<File> fileList = FileUtil.loopFiles(tplPath);
        //templates.add(tplName+"/entity.java.vm");
        fileList.forEach(t->{
            if (t.getName().endsWith(".vm")) {
                templates.add(t.getName());
            }
        });
        return templates;
    }

    /**
     * 获取项目文件路径
     *
     * @return 路径
     */
    public static String getProjectPath(GenConfig genConfig)
    {
        String packageName = genConfig.getPackageName();
        StringBuffer projectPath = new StringBuffer();
        projectPath.append("main/java/");
        projectPath.append(packageName.replace(".", "/"));
        projectPath.append("/");
        return projectPath.toString();
    }

    public static void main(String[] args) {
        getTemplateList("tkmapper-tpl").forEach(t-> System.out.println(t));
    }
}
