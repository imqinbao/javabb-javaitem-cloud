package cn.javabb.generator.config.constants;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/12 16:36
 */
public interface ConstVal {
    /**
     * 默认的数据库url参数，如果传入的url没有配置参数，将会默认传递该内容
     */
     String DEF_DB_URL_PARAMS = "useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
    /**
     * UTF-8 字符集
     */
    String UTF8 = "UTF-8";
    /**
     * GBK 字符集
     */
    String GBK = "GBK";

    String MODULE_NAME = "ModuleName";

    String ENTITY = "Entity";
    String SERVICE = "Service";
    String SERVICE_IMPL = "ServiceImpl";
    String MAPPER = "Mapper";
    String XML = "Xml";
    String CONTROLLER = "Controller";

    String ENTITY_PATH = "entity_path";
    String SERVICE_PATH = "service_path";
    String SERVICE_IMPL_PATH = "service_impl_path";
    String MAPPER_PATH = "mapper_path";
    String XML_PATH = "xml_path";
    String CONTROLLER_PATH = "controller_path";

    String JAVA_TMPDIR = "java.io.tmpdir";

}
