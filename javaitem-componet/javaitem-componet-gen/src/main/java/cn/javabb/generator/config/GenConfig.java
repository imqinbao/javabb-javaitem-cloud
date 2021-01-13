package cn.javabb.generator.config;

import cn.javabb.generator.model.GenModel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/11 00:33
 */
@Data
@Accessors(chain = true)
public class GenConfig implements Serializable {
    // 生成目录   必须
    @NonNull
    private String baseDir = File.listRoots()[0]+"/javabb-generator/";
    /**
     * 数据库连接地址
     */
    private String dbUrl;
    /**
     * 数据库连接账号
     */
    private String dbUserName;
    /**
     * 数据库连接密码
     */
    private String dbPassword;
    /**
     * 数据库连接驱动名
     */
    private String dbDriverName;
    /**
     * 项目名称
     */
    private String projectName = "example";
    /**
     * Group Id
     */
    private String groupId = "cn.javabb";
    /**
     * 包名
     */
    private String packageName = "cn.javabb";
    /**
     * 项目模板名
     */
    @NonNull
    private String tplName;
    /**
     * 作者名称
     */
    private String author = "javabb";
    /**
     * 是否需要权限注解
     */
    private Boolean needPerm = false;
    /**
     * 是否需要日志注解
     */
    private Boolean needLog = false;
    /**
     * 是否需要生成SQL
     */
    private Boolean needSql =false;
    /**
     * 菜单起始id
     */
    private Integer menuStartId = 0;
    /**
     * 模块划分配置
     */
    private List<GenModel> models = new ArrayList<>();
    //是否实体为lombok模型,默认不开启
    private boolean entityLombokModel = false;
    // 是否驼峰命名
    private boolean entityCamelModel = true;
    // 是否生成swagger代码,默认不开启
    private boolean swagger = false;
    // 本地debug模式,
    private boolean debugModel = false;

    public DataSourceConfig getDataSourceConfig(){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(this.dbUrl);
        dsc.setUsername(this.dbUserName);
        dsc.setPassword(this.dbPassword);
        dsc.setDriverName(this.dbDriverName);
        return dsc;
    }

    public GenConfig() {

    }

    public void setBaseDir(Integer cache){
        this.baseDir =  File.listRoots()[cache]+"/javabb-generator/";
    }

    public static GenConfig getInstance() {
        return new GenConfig();
    }

    // 项目模板路径(解压前的路径)
    public String getTplDir() {
        return this.baseDir + "tpl/";
    }

    // 项目模板解压后的路径
    public String getTemplateDir() {
        if (isDebugModel()) {
            //从项目路径下去找
            return System.getProperty("user.dir")+"/src/main/resources/template/";
        }
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


}
