package cn.javabb.generator.config;

import cn.javabb.generator.util.StrUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/16 18:16
 */
@Data
@Accessors(chain = true)
public class PackageConfig {
    private final static String DOT = ".";
    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    private String parent = "cn.javabb";
    /**
     * 父包模块名
     */
    private String moduleName = "";
    /**
     * Entity包名
     */
    private String entity = "entity";
    /**
     * Service包名
     */
    private String service = "service";
    /**
     * Service Impl包名
     */
    private String serviceImpl = "service.impl";
    /**
     * Mapper包名
     */
    private String mapper = "mapper";
    /**
     * Mapper XML包名
     */
    private String xml = "mapper.xml";
    /**
     * Controller包名
     */
    private String controller = "controller";
    /**
     * 路径配置信息
     */
    private Map<String, String> pathInfo;

    /**
     * 父包名
     */
    public String getParent() {
        if (StrUtil.isNotBlank(moduleName)) {
            return parent + DOT + moduleName;
        }
        return parent;
    }

    public String getEntity() {
        return this.getParent() + DOT + this.entity;
    }

    public String getService() {
        return this.getParent() + DOT + this.service;
    }

    public String getServiceImpl() {
        return this.getParent() + DOT + this.serviceImpl;
    }

    public String getMapper() {
        return this.getParent() + DOT + this.mapper;
    }

    public String getXml() {
        return this.getParent() + DOT + this.xml;
    }

    public String getController() {
        return this.getParent() + DOT + this.controller;
    }
}
