package cn.javabb.generator.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.javabb.generator.exception.GeneratorException;
import cn.javabb.generator.model.Template;
import cn.javabb.generator.model.TplConfig;
import cn.javabb.generator.model.TplReplace;
import cn.javabb.generator.model.TplReplaceItem;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 模版工具
 */
public class TemplateUtil extends cn.hutool.core.io.FileUtil {

    private static String getFileContentByResourcePath(String resourcePath) {
        try {
            InputStream inputStream = TemplateUtil.class.getResourceAsStream(resourcePath);
            if (null == inputStream) {
                throw new GeneratorException("请检查`src/main/resources`下是否存在: " + resourcePath);
            }
            String content = IoUtil.read(inputStream, StandardCharsets.UTF_8);
            if (StringUtils.isEmpty(content)) {
                throw new GeneratorException("模板内容为空：" + resourcePath);
            }
            return content;
        } catch (Exception e) {
            throw new GeneratorException("无法获取模板内容：" + resourcePath);
        }
    }

    private static String getFileContentByFilePath(String filePath) {
        try {
            InputStream inputStream = new FileInputStream(new File(filePath));
            String content = IoUtil.read(inputStream, StandardCharsets.UTF_8);
            if (StringUtils.isEmpty(content)) {
                throw new GeneratorException("模板内容为空：" + filePath);
            }
            return content;
        } catch (Exception e) {
            throw new GeneratorException("无法获取模板内容：" + filePath);
        }
    }

    private static List<File> getFilesFromResource(String resourcePath) {
        List<File> files = loopFiles(resourcePath);
        return files.stream().filter((f) -> !f.getPath().endsWith(".include")).collect(Collectors.toList());
    }

    public static List<Template> listTemplates(String templatePath) {

        URL classPath = Thread.currentThread().getContextClassLoader().getResource(templatePath);
        if (null == classPath) {
            throw new GeneratorException("[" + templatePath + "] 下未获取到模板信息");
        }
        String templateBasePath = file(classPath.getFile()).getAbsolutePath();
        List<Template> templates = new ArrayList<>();
                //ListUtil.newArrayList();
        List<File> templateFiles = TemplateUtil.getFilesFromResource(templateBasePath);

        for (File template : templateFiles) {
            String absolutePath = template.getAbsolutePath();
            templates.add(Template.builder()
                    .filePath(absolutePath.replace(templateBasePath, ""))
                    .fileContent(TemplateUtil.getFileContentByFilePath(template.getPath()))
                    .build());
        }
        return templates;
    }


    /**
     * 删除目录，返回删除的文件数
     *
     * @param rootPath 待删除的目录
     */
    public static void deleteDir(String rootPath) {
        File dir = new File(rootPath);
        if (!dir.exists()) {
            return;
        }
        if (!dir.isDirectory()) {
            String message = dir + " is not a directory";
            throw new IllegalArgumentException(message);
        }
        File[] items = dir.listFiles();
        if (items != null && items.length > 0) {
            for (File item : items) {
                forceDelete(item);
            }
        }
    }

    /**
     * 强制删除文件
     *
     * @param file 待删除的文件
     */
    public static void forceDelete(File file) {
        if (file.isDirectory()) {
            deleteDir(file.getAbsolutePath());
        } else {
            boolean filePresent = file.exists();
            if (!file.delete()) {
                if (!filePresent) {
                    throw new GeneratorException("File does not exist: " + file);
                }
                String message = "Unable to delete file: " + file;
                throw new GeneratorException(message);
            }
        }
    }

    /**
     * 获取模版配置文件
     * @param tplPath
     * @return
     */
    public static TplConfig getTplConfig(String tplPath) {
        TplConfig tConfig = new TplConfig();
        try {
            String content = FileUtil.readString(tplPath + "/config.json","UTF-8");
            //String content = new FileReader(tplPath + "/config.json").readString();
            tConfig = jsonParseObject(content, TplConfig.class);
            return tConfig;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tConfig == null) {
            throw new RuntimeException("模板" + tplPath + "的配置文件读取失败");
        }
        return tConfig;
    }
    /**
     * 获取模板列表
     */
    public List<Map<String, Object>> listTpl(String tplDir) {
        List<Map<String, Object>> list = new ArrayList<>();
        File dir = new File(tplDir);
        if (!dir.exists() && dir.mkdirs()) return list;
        File[] files = dir.listFiles();
        if (files == null) return list;
        for (File f : files) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", cn.hutool.core.util.StrUtil.removeSuffix(f.getName(), ".zip"));
            map.put("size", f.length());
            list.add(map);
        }
        return list;
    }
    /**
     * 获取历史生成记录
     */
    public List<Map<String, Object>> history(String outputDir) {
        List<Map<String, Object>> list = new ArrayList<>();
        File dir = new File(outputDir);
        if (!dir.exists() && !dir.mkdirs()) return list;
        File[] files = dir.listFiles();
        if (files == null) return list;
        for (File file : files) {
            File[] fs = file.listFiles();
            if (fs != null && fs.length > 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", file.getName());
                map.put("name", cn.hutool.core.util.StrUtil.removeSuffix(fs[0].getName(), ".zip"));
                map.put("updateTime", fs[0].lastModified());  // 最后修改时间
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 替换文件内容
     */
    public static void replaceFileStr(TplReplace replace) {
        for (String file : replace.getFiles()) {
            FileOutputStream out = null;
            try {
                String content = FileUtil.readString(file,"UTF-8");
                //String content = new FileReader(file).readString();
                if (content == null || content.trim().isEmpty()) continue;

                for (TplReplaceItem item : replace.getItems()) {
                    content = content.replace(item.getOrgStr(), item.getNewStr());
                }

                out = new FileOutputStream(new File(file));
                out.write(content.getBytes("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IoUtil.close(out);
            }
        }
    }

    /**
     * 替换文件内容
     */
    public static void replaceFileEl(String filePath,Map<String,Object> map) {
            FileOutputStream out = null;
            try {
                String content = FileUtil.readString(filePath,"UTF-8");
                //String content = new FileReader(filePath).readString();
                if (content == null || content.trim().isEmpty()) return;

                content = replaceEL(content, map);

                out = new FileOutputStream(new File(filePath));
                out.write(content.getBytes("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IoUtil.close(out);
            }
    }
    /**
     * 解析json
     */
    public static  <T> T jsonParseObject(String json, Class<T> clazz) {
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
     * 提取中括号中内容，忽略中括号中的中括号
     */
    public static List<String> getElExpress(String content) {
        List<String> list = new ArrayList<String>();
        int start = 0;
        int startFlag = 0;
        int endFlag = 0;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '{') {
                startFlag++;
                if (startFlag == endFlag + 1) {
                    start = i;
                }
            } else if (content.charAt(i) == '}') {
                endFlag++;
                if (endFlag == startFlag) {
                    list.add(content.substring(start + 1, i));
                }
            }
        }
        return list;
    }

    /**
     * 获取自定义的表达式  例如: #{abc}
     * @param content
     * @return
     */
    public static List<String> getExperss(String content) {
        return ReUtil.findAll("#\\{([^}])*\\}",content,0);
    }
    /**
     * 替换解析表达式 将文件中的 #{} 替换实际的值
     */
    public static String replaceEL(String content, Map<String, Object> dataMap) {
        List<String> elList = getExperss(content);
        for (String el : elList) {
            String var = el.substring(2, el.lastIndexOf("}"));
            if (ObjectUtil.isNotEmpty(dataMap.get(var))) {
                content = content.replace(el, dataMap.get(var).toString());
            }
        }
        return content;
    }


    public static void main(String[] args) {
        String str = "#{abc} #a0bc  #ewf #{abc.a}";
        String str1 = str.replace("$","\\$");
        String str2 = str.replaceAll("\\$!?" + "ewf" + "(\\W)", "\\$!{" + "ewf" + "}$1");
        List<String> list = ReUtil.findAll("#\\{([^}])*\\}",str,0);

       list.forEach(System.out::println);

        /*List<String> lst = getElExpress(str);
        for(String l:lst){
            System.out.println(l);
        }*/
        //System.out.println(str2);
    }
}
