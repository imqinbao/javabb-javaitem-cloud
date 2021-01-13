package cn.javabb.generator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ZipUtil;
import cn.javabb.generator.config.DataSourceConfig;
import cn.javabb.generator.config.GenConfig;
import cn.javabb.generator.config.PackageConfig;
import cn.javabb.generator.config.StrategyConfig;
import cn.javabb.generator.core.GeneratorHelper;
import cn.javabb.generator.model.*;
import cn.javabb.generator.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/16 16:48
 */
@Slf4j
public class Generator {
    /**
     * 项目空间路径
     */
    private static final String JAVA_PATH = "main/java";
    /**
     * mybatis空间路径
     */
    private static final String MYBATIS_PATH = "main/resources/mapper";
    /**
     * html空间路径
     */
    private static final String TEMPLATES_PATH = "main/resources/templates";
    // 生成 配置
    private GenConfig genConfig;
    // 包 配置
    private PackageConfig packageConfig;
    // 模版 配置
    private TplConfig tplConfig;
    // 数据库配置
    private DataSourceConfig dataSourceConfig;

    public Generator() {
    }

    /**
     * 模版注入属性
     *
     * @param tableInfo
     * @return
     */
    public Map<String, Object> getMapData(TableInfo tableInfo, PackageConfig pc) {
        Map<String, Object> map = new HashMap<>();
        map.put("table", tableInfo);
        map.put("author", genConfig.getAuthor());
        map.put("now", DateUtil.now());
        map.put("packageName", genConfig.getPackageName());
        map.put("projectName", genConfig.getProjectName());
        map.put("package", pc);
        //map.put("code", codeConfig);
        return map;
    }

    /**
     * 代码生成
     *
     * @throws Exception
     */
    public void gen() throws Exception {
        String projectId = IdUtil.objectId();
        //String projectDir = getTempDir() + projectId + "/" + genConfig.getProjectName() + "/";
        String projectDir = genConfig.getTempDir() + genConfig.getProjectName() + "/";
        if (FileUtil.exist(projectDir)) {
            FileUtil.del(projectDir);
        }
        if (genConfig.isDebugModel()) {
            // 生成crud java类
            // 本地调试
            generatorCurd(projectDir + "src/");
        } else {
            // 生成项目
            genProject(projectDir);
            // 生成crud java类
            generatorCurd(projectDir + "src/");
            // 自定义替换内容
            doReplaces(projectDir);
            // 修改模版里的包名
            updatePackages(projectDir);
        }

    }

    /**
     * 调试
     *
     * @param srcDir
     * @param table
     * @throws Exception
     */
    public void generatorCode(String srcDir, TableInfo table) throws Exception {

        //VelocityInitializer.initVelocity(getTemplateDir());
        VelocityContext content = VelocityUtil.prepareContext(table, genConfig);
        // 获取模板
        List<String> templates = VelocityUtil.getTemplateList(genConfig.getTplName());
        for (String template : templates) {
            if (template.indexOf("sql.vm") > 0) {
                String fileName = getFileName(srcDir, template, table, new PackageConfig());
                log.info("文件生成:" + fileName);
                // 渲染模板
                VelocityUtil.writer(getMapData(table, null), template, fileName);
            }
        }
    }

    /**
     * 生产java代码Curd
     *
     * @param srcDir
     * @throws Exception
     */
    public void generatorCurd(String srcDir) throws Exception {

        VelocityInitializer.initVelocity(genConfig.getTemplateDir() + genConfig.getTplName());

        if (CollUtil.isNotEmpty(genConfig.getModels())) {
            for (GenModel genModel : genConfig.getModels()) {
                // 包配置
                PackageConfig pc = new PackageConfig();
                String mName = StrUtil.isBlank(genModel.getModelName()) ? "" : genModel.getModelName();
                pc.setModuleName(mName);
                //pc.setParent(genConfig.getPackageName() + "." + mName);
                pc.setEntity("entity");
                pc.setMapper("mapper");
                pc.setXml("mapper.xml");
                pc.setService("service");
                pc.setServiceImpl("service.impl");
                // 策略配置
                StrategyConfig strategyConfig = new StrategyConfig();
                strategyConfig.setInclude(genModel.getTablesArray());
                strategyConfig.setTablePrefix(genModel.getPrefixArray());

                GeneratorHelper helper = new GeneratorHelper(dataSourceConfig, strategyConfig);
                List<TableInfo> tableInfoList = helper.getTableInfoList();
                for (TableInfo table : tableInfoList) {
                    VelocityContext content = VelocityUtil.prepareContext(table, genConfig);
                    // 获取模板
                    List<String> templates = VelocityUtil.getTemplateList(genConfig.getTemplateDir() + genConfig.getTplName());
                    for (String template : templates) {
                        String fileName = getFileName(srcDir, template, table, pc);
                        if (StrUtil.isNotBlank(fileName)) {
                            //System.out.println("文件已经生成:" + fileName);
                            VelocityUtil.writer(getMapData(table, pc), template, fileName);
                        }

                    }
                }

            }
        }
    }

    /**
     * 自定义文件内容替换,只支持el表达式
     *
     * @param projectDir
     */
    private void doReplaces(String projectDir) {
        // 对外提供的数据
        Map<String, Object> tplData = new HashMap<>();
        tplData.put("projectName", genConfig.getProjectName());
        tplData.put("groupId", genConfig.getGroupId());
        tplData.put("groupIdPath", genConfig.getGroupId().replace(".", "/"));
        tplData.put("packageName", genConfig.getPackageName());
        tplData.put("packageNamePath", genConfig.getPackageName().replace(".", "/"));
        tplData.put("author", genConfig.getAuthor());
        tplData.put("dbUrl", dataSourceConfig.getUrl());
        tplData.put("dbUserName", dataSourceConfig.getUsername());
        tplData.put("dbPassword", dataSourceConfig.getPassword());
        tplData.put("dbDriverName", dataSourceConfig.getDriverName());

        List<TplReplace> replaces = tplConfig.getReplaces();
        System.out.println("文件配置中...");
        //List<TplReplace> replaces = tplConfig.getReplaces();
        for (TplReplace replace : replaces) {
            if (replace.getFiles().length == 0) continue;
            for (String file : replace.getFiles()) {
                TemplateUtil.replaceFileEl(projectDir + file, tplData);
            }

        }
    }

    /**
     * 生成项目框架
     *
     * @param projectDir 项目生成位置
     */
    private void genProject(String projectDir) {

        // 初次使用解压模板
        File tplFile = new File(genConfig.getTemplateDir(), genConfig.getTplName());
        File[] listFiles = tplFile.listFiles();
        if (!tplFile.exists() || listFiles == null || listFiles.length == 0) {
            ZipUtil.unzip(new File(genConfig.getTplDir(), genConfig.getTplName() + ".zip"), tplFile, Charset.forName("GBK"));
        }
        // 读取配置
        this.tplConfig = TemplateUtil.getTplConfig(genConfig.getTemplateDir() + genConfig.getTplName());
        // 复制模板到项目生成位置中
        FileUtil.copyContent(new File(tplFile, "/project"), new File(projectDir), true);
    }

    /**
     * 更新包名
     *
     * @param projectDir
     */
    public void updatePackages(String projectDir) {
        if (this.tplConfig == null) {
            tplConfig = TemplateUtil.getTplConfig(genConfig.getTemplateDir() + genConfig.getTplName());
        }
        String oldPackage = tplConfig.getPackageName();
        String newPackage = genConfig.getGroupId();
        if (oldPackage.equals(newPackage)) return;
        File oldSrc = new File(projectDir, "src/main/java/" + oldPackage.replace(".", "/"));
        File newSrc = new File(projectDir, "src/main/java/" + newPackage.replace(".", "/"));
        File[] oldFiles = oldSrc.listFiles();
        if (oldFiles == null) return;
        for (File oldFile : oldFiles) {
            FileUtil.move(oldFile, newSrc, true);  // 移动文件
        }
        String[] oldSubPack = StrUtil.split(oldPackage, ".");
        String[] newSubPack = StrUtil.split(newPackage, ".");
        // 获取想同的包名前缀,然后删除old包文件夹,目前我只考虑了包的第一层.
        String delPath = oldPackage.replace(".", "/");
        if (oldSubPack.length >= 2 && newSubPack.length >= 2 && !oldSubPack[0].equals(newSubPack[0])) {
            delPath = oldSubPack[0];
        }
        FileUtil.del(projectDir + "src/main/java/" + delPath);  // 删除旧目录
        updateCodePackages(newSrc, oldPackage, newPackage, null);  // 修改代码包名
    }

    /**
     * 修改某个目录下代码的包名
     *
     * @param src        目录
     * @param oldPackage 原始包名
     * @param newPackage 新的包名
     */
    private static void updateCodePackages(File src, String oldPackage, String newPackage, List<String> child) {
        File[] files = src.listFiles();
        if (files == null) return;
        if (child == null) {
            child = new ArrayList<>();
            for (File file : files) {
                int index = file.getName().lastIndexOf(".");
                if (index == -1) {
                    child.add(file.getName());
                } else {
                    child.add(file.getName().substring(0, index));
                }
            }
        }
        for (File file : files) {
            if (file.isDirectory()) {
                updateCodePackages(file, oldPackage, newPackage, child);
                continue;
            }
            List<TplReplaceItem> items = new ArrayList<>();
            items.add(new TplReplaceItem("package " + oldPackage, "package " + newPackage));
            items.add(new TplReplaceItem("namespace=\"" + oldPackage, "namespace=\"" + newPackage));
            items.add(new TplReplaceItem("resultType=\"" + oldPackage, "resultType=\"" + newPackage));
            items.add(new TplReplaceItem("@annotation(" + oldPackage, "@annotation(" + newPackage));
            for (String name : child) {
                items.add(new TplReplaceItem("import " + oldPackage + "." + name, "import " + newPackage + "." + name));
            }
            TemplateUtil.replaceFileStr(new TplReplace(file.getAbsolutePath(), items.toArray(new TplReplaceItem[0])));
        }
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String srcDir, String template, TableInfo tableInfo, PackageConfig pc) {
        String fileName = "";
        //
        String javaPath = srcDir + JAVA_PATH + File.separator;
        if (template.contains("entity.java.vm")) {
            fileName = javaPath + StrUtil.replace(pc.getEntity(), ".", "/") + File.separator + tableInfo.getEntityName() + ".java";
        } else if (template.contains("mapper.java.vm")) {
            fileName = javaPath + StrUtil.replace(pc.getMapper(), ".", "/") + File.separator + tableInfo.getMapperName() + ".java";
        } else if (template.contains("service.java.vn")) {
            fileName = javaPath + StrUtil.replace(pc.getService(), ".", "/") + File.separator + tableInfo.getServiceName() + ".java";
        } else if (template.contains("serviceImpl.java.vm")) {
            fileName = javaPath + StrUtil.replace(pc.getServiceImpl(), ".", "/") + File.separator + tableInfo.getServiceImplName() + ".java";
        } else if (template.contains("mapper.xml.vm")) {
            // 也可以放在resource下面
            fileName = javaPath + StrUtil.replace(pc.getXml(), ".", "/") + File.separator + tableInfo.getXmlName() + ".xml";
        } else if (template.contains("controller.java.vm")) {
            fileName = javaPath + StrUtil.replace(pc.getController(), ".", "/") + File.separator + tableInfo.getControllerName() + ".java";
        }
        return fileName;
    }

    public TplConfig getTplConfig() {
        if (tplConfig == null) {
            try {
                String content = new FileReader(genConfig.getTemplateDir() + genConfig.getTplName() + "/config.json").readString();
                tplConfig = GenUtil.jsonParseObject(content, TplConfig.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tplConfig == null) {
                throw new RuntimeException("模板" + genConfig.getTplName() + "的配置文件读取失败");
            }
        }
        return tplConfig;
    }

}
