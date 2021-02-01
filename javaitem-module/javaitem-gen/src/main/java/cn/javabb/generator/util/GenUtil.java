package cn.javabb.generator.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ZipUtil;
import cn.javabb.generator.config.*;
import cn.javabb.generator.config.rules.DbType;
import cn.javabb.generator.core.GeneratorHelper;
import cn.javabb.generator.exception.GeneratorException;
import cn.javabb.generator.model.*;
import org.apache.velocity.VelocityContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/11 00:49
 */
public class GenUtil {
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

    private String baseDir; // 项目缓存位置

    // 项目模板路径
    public String getBaseDir() {
        return this.baseDir;
    }

    // 项目模板路径
    public String getTplDir() {
        return this.baseDir + "tpl/";
    }

    // 项目模板解压后的路径
    public String getTemplateDir() {
        return this.baseDir + "template/";
    }

    // 项目生成路径
    public String getTempDir() {
        return this.baseDir + "temp/";
    }

    // 项目生成完打包输出路径
    public String getOutputDir() {
        return this.baseDir + "output/";
    }

    public GenUtil(int index) {
        this.baseDir = File.listRoots()[index] + "/javabb-generator/";
    }
    /**
     * 模版注入属性
     *
     * @param tableInfo
     * @return
     */
    public Map<String, Object> getMapData(TableInfo tableInfo, PackageConfig pc, GenConfig genConfig) {
        Map<String, Object> map = new HashMap<>();
        map.put("table", tableInfo);
        map.put("author", genConfig.getAuthor());
        map.put("now", DateUtil.now());
        map.put("packageName", genConfig.getPackageName());
        map.put("projectName", genConfig.getProjectName());
        map.put("package", pc);
        map.put("config", genConfig);
        //map.put("code", codeConfig);
        return map;
    }
    /**
     * 查询数据库所有表信息
     *
     * @return List<TableInfo>
     */
    public static List<TableInfo> getTableList(DataSourceConfig dataSourceConfig) {

        Connection connection = dataSourceConfig.getConn();
        List<TableInfo> tableInfoList = new ArrayList<>();
        DbType dbType = dataSourceConfig.getDbType();
        IDbQuery dbQuery = dataSourceConfig.getDbQuery();
        try {
            String tablesSql = dataSourceConfig.getDbQuery().tablesSql();
            StringBuilder sql = new StringBuilder(tablesSql);
            TableInfo tableInfo;
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
                    ResultSet results = preparedStatement.executeQuery();
            ) {
                while (results.next()) {
                    String tableName = results.getString(dbQuery.tableName());
                    if (StrUtil.isNotBlank(tableName)) {
                        tableInfo = new TableInfo();
                        tableInfo.setName(tableName);
                        String commentColumn = dbQuery.tableComment();
                        if (StrUtil.isNotBlank(commentColumn)) {
                            String tableComment = results.getString(commentColumn);
                            tableInfo.setComment(formatComment(tableComment));
                        }
                        tableInfoList.add(tableInfo);
                    }
                }
            }
        } catch (Exception e) {
            throw new GeneratorException("数据库表读取错误:" + e.getMessage());
        }
        return tableInfoList;
    }
    /**
     * 代码生成
     * @throws Exception
     */
    public String gen(GenConfig genConfig) throws Exception {
        String projectId = IdUtil.objectId();
        String projectDir = getTempDir() + projectId + "/" + genConfig.getProjectName() + "/";
        //String projectDir = genConfig.getTempDir() + genConfig.getProjectName() + "/";
        if (FileUtil.exist(projectDir)) {
            FileUtil.del(projectDir);
        }
        if (genConfig.isDebugModel()) {
            // 生成crud java类
            // 本地调试
            generatorCurd(projectDir + "src/",genConfig);
            return "生成完成";
        }else{
            // 生成项目
            genProject(projectDir,genConfig);
            // 生成crud java类
            generatorCurd(projectDir + "src/",genConfig);
            // 自定义替换内容
            doReplaces(projectDir,genConfig);
            // 修改模版里的包名
            updatePackages(projectDir,genConfig);
            // 打包项目
            String outputPath = projectId + "/" + genConfig.getProjectName() + ".zip";
            ZipUtil.zip(getTempDir() + projectId + "/", getOutputDir() + outputPath);
            return outputPath;
        }
    }
    /**
     * 生成项目框架
     *
     * @param projectDir 项目生成位置
     */
    private void genProject(String projectDir,GenConfig genConfig) {

        // 初次使用解压模板
        File tplFile = new File(getTemplateDir(), genConfig.getTplName());
        File[] listFiles = tplFile.listFiles();
        if (!tplFile.exists() || listFiles == null || listFiles.length == 0) {
            ZipUtil.unzip(new File(genConfig.getTplDir(), genConfig.getTplName() + ".zip"), tplFile, Charset.forName("GBK"));
        }
        // 读取配置
        //this.tplConfig = TemplateUtil.getTplConfig(genConfig.getTemplateDir() + genConfig.getTplName());
        // 复制模板到项目生成位置中
        FileUtil.copyContent(new File(tplFile, "/project"), new File(projectDir), true);
    }
    /**
     * 自定义文件内容替换,只支持el表达式
     *
     * @param projectDir
     */
    private void doReplaces(String projectDir,GenConfig genConfig) {
        // 对外提供的数据
        Map<String, Object> tplData = new HashMap<>();
        tplData.put("projectName", genConfig.getProjectName());
        tplData.put("groupId", genConfig.getGroupId());
        tplData.put("groupIdPath", genConfig.getGroupId().replace(".", "/"));
        tplData.put("packageName", genConfig.getPackageName());
        tplData.put("packageNamePath", genConfig.getPackageName().replace(".", "/"));
        tplData.put("author", genConfig.getAuthor());
        tplData.put("dbUrl", genConfig.getDbUrl());
        tplData.put("dbUserName", genConfig.getDbUserName());
        tplData.put("dbPassword", genConfig.getDbPassword());
        tplData.put("dbDriverName", genConfig.getDbDriverName());

        TplConfig tplConfig = TemplateUtil.getTplConfig(getTemplateDir() + genConfig.getTplName());
        List<TplReplace> replaces = tplConfig.getReplaces();
        System.out.println("文件配置中...");
        //List<TplReplace> replaces = tplConfig.getReplaces();
        for (TplReplace replace : replaces) {
            if (replace.getFiles().length == 0 ) continue;
            for (String file : replace.getFiles()) {
                TemplateUtil.replaceFileEl(projectDir+file,tplData);
            }

        }
    }
    /**
     * 生产java代码Curd
     * @param srcDir
     * @throws Exception
     */
    public void generatorCurd(String srcDir,GenConfig genConfig) throws Exception {

        VelocityInitializer.initVelocity(getTemplateDir() + genConfig.getTplName());

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
                strategyConfig.setEntityCamelModel(genConfig.isEntityCamelModel());

                GeneratorHelper helper = new GeneratorHelper(genConfig.getDataSourceConfig(), strategyConfig);
                List<TableInfo> tableInfoList = helper.getTableInfoList();
                for (TableInfo table : tableInfoList) {
                    VelocityContext content = VelocityUtil.prepareContext(table, genConfig);
                    // 获取模板
                    List<String> templates = VelocityUtil.getTemplateList(getTemplateDir() + genConfig.getTplName());
                    for (String template : templates) {
                        String fileName = getFileName(srcDir, template, table, pc);
                        if (StrUtil.isNotBlank(fileName)) {
                            //System.out.println("文件已经生成:" + fileName);
                            VelocityUtil.writer(getMapData(table, pc,genConfig), template, fileName);
                        }

                    }
                }

            }
        }
    }
    /**
     * 更新包名
     * @param projectDir
     */
    public void updatePackages(String projectDir,GenConfig genConfig) {
        TplConfig tplConfig = TemplateUtil.getTplConfig(genConfig.getTemplateDir()+genConfig.getTplName());
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
     * 获取生成后的压缩包文件
     *
     * @param path 文件路径
     * @return File
     */
    public File getOutputFile(String path) {
        return new File(getOutputDir(), path);
    }
    /**
     * 获取模板列表
     */
    public List<Map<String, Object>> listTpl() {
        List<Map<String, Object>> list = new ArrayList<>();
        File dir = new File(getTplDir());
        if (!dir.exists() && dir.mkdirs()) return list;
        File[] files = dir.listFiles();
        if (files == null) return list;
        for (File f : files) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", StrUtil.removeSuffix(f.getName(), ".zip"));
            map.put("size", f.length());
            list.add(map);
        }
        return list;
    }
    /**
     * 格式化数据库注释内容
     */
    public static String formatComment(String comment) {
        return StrUtil.isBlank(comment) ? "" : comment.replaceAll("\r\n", "\t");
    }

    /**
     * 上传模板
     */
    public boolean upload(MultipartFile file) {
        String name = file.getOriginalFilename();
        if (name == null) return false;
        File f = new File(getTplDir(), name);
        if (!f.getParentFile().exists() && !f.getParentFile().mkdirs()) return false;
        try {
            file.transferTo(f);
            FileUtil.del(new File(getTemplateDir(), name));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 获取历史生成记录
     */
    public List<Map<String, Object>> history() {
        List<Map<String, Object>> list = new ArrayList<>();
        File dir = new File(getOutputDir());
        if (!dir.exists() && !dir.mkdirs()) return list;
        File[] files = dir.listFiles();
        if (files == null) return list;
        for (File file : files) {
            File[] fs = file.listFiles();
            if (fs != null && fs.length > 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", file.getName());
                map.put("name", StrUtil.removeSuffix(fs[0].getName(), ".zip"));
                map.put("updateTime", fs[0].lastModified());  // 最后修改时间
                list.add(map);
            }
        }
        return list;
    }
    public static <T> T jsonParseObject(String json, Class<T> clazz) {
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
        } else if (template.contains("service.java.vm")) {
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
}
