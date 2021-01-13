package cn.javabb.generator.engine;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.StrUtil;
import cn.javabb.generator.model.TplConfig;
import cn.javabb.generator.model.TplPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @desc: 重写Velocity模版引擎
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/11 01:38
 */
public class GenTemplateEngine extends AbstractTemplateEngine {
    private static final String DOT_VM = ".vm";
    private static final String GLOBAL_CONFIG_PATH = "globalConfig";
    private VelocityEngine velocityEngine;
    /**
     * 解压后的模板所存放的位置
     */
    private String tplDir;
    /**
     * 模板名称
     */
    private String tplName;
    /**
     * 模板配置
     */
    private TplConfig tplConfig;
    public GenTemplateEngine() {

    }
    public GenTemplateEngine(String tplDir, String tplName) {
        this.tplDir = tplDir;
        this.tplName = tplName;
    }

    public String getTplDir() {
        return tplDir;
    }
    public void setTplDir(String tplDir) {
        this.tplDir = tplDir;
    }

    public String getTplName() {
        return tplName;
    }

    public void setTplName(String tplName) {
        this.tplName = tplName;
    }

    public void setTplConfig(TplConfig tplConfig) {
        this.tplConfig = tplConfig;
    }

    public AbstractTemplateEngine init(ConfigBuilder configBuilder) {
        super.init(configBuilder);
        if (null == this.velocityEngine) {
            Properties p = new Properties();
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty("file.resource.loader.path", "");
            p.setProperty("UTF-8", ConstVal.UTF8);
            p.setProperty("input.encoding", ConstVal.UTF8);
            p.setProperty("file.resource.loader.unicode", "true");
            //宏配置,配置好像不对
            //p.setProperty("velocimacro.library", "globalConfig/define.vm");
            this.velocityEngine = new VelocityEngine(p);
        }
        return this;
    }
    @Override
    public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
        if (!StringUtils.isEmpty(templatePath)) {
            Template template = this.velocityEngine.getTemplate(templatePath, ConstVal.UTF8);
            FileOutputStream fos = null;
            OutputStreamWriter ow = null;
            BufferedWriter writer = null;
            try {
                fos = new FileOutputStream(outputFile);
                ow = new OutputStreamWriter(fos, ConstVal.UTF8);
                writer = new BufferedWriter(ow);
                template.merge(new VelocityContext(objectMap), writer);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                IoUtil.close(fos);
                IoUtil.close(ow);
                IoUtil.close(writer);
            }
        }
        logger.debug("模板:" + templatePath + ";  文件:" + outputFile);
    }

    @Override
    public String templateFilePath(String filePath) {
        return StrUtil.removePrefix(filePath, "/templates/") + DOT_VM;
    }
    public TplConfig getTplConfig() {
        if (tplConfig == null) {
            try {
                String content = new FileReader(tplDir + tplName + "/config.json").readString();
                tplConfig = jsonParseObject(content, TplConfig.class);
                setTplConfig(tplConfig);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tplConfig == null) {
                throw new RuntimeException("模板" + tplName + "的配置文件读取失败");
            }
        }
        return tplConfig;
    }
    /**
     * 是否生成页面
     */
    public boolean isGenPage() {
        TplConfig tc = getTplConfig();
        return !(tc == null || tc.getPages() == null || tc.getPages().size() == 0);
    }
    /**
     * 获取页面模板配置
     *
     * @param srcDir    src目录
     * @param modelName 模块名
     * @return List<FileOutConfig>
     */
    public List<FileOutConfig> getFocList(String srcDir, String modelName) {
        List<FileOutConfig> focList = new ArrayList<>();
        if (isGenPage()) {
            for (TplPage page : tplConfig.getPages()) {
                focList.add(new FileOutConfig(page.getTpl()) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        String suffix = page.getTpl().substring(4, page.getTpl().lastIndexOf("."));
                        String mName = StrUtil.isBlank(modelName) ? "" : modelName + "/";
                        String nameSuffix = page.getNameSuffix() == null ? "" : page.getNameSuffix();
                        return srcDir + page.getOutput() + mName + tableInfo.getEntityPath() + nameSuffix + suffix;
                    }
                });
            }
        }
        return focList;
    }
    /**
     * 解析json
     */
    public <T> T jsonParseObject(String json, Class<T> clazz) {
        if (json != null && !json.trim().isEmpty()) {
            try {
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                return mapper.readValue(json, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
