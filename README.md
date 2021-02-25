# JavaItem-Cloud

## 项目介绍

JavaItem-Cloud是一个新开发的微服务架构平台，基于最新流行的技术SpringBoot、SpringCloud & SpringCloud Alibaba、Vue、Vuex、ElementUI。

## 系统特性

- 后端使用当前流行技术，SpringBoot、SpringCloud、SpringCloud Alibaba、Mybatis、Mybatis-Plus、hutool
- 前端使用Vue2、ElementUI搭建
- 完整的登录鉴权、统一网关，动态路由流程
- 使用主流技术栈，工程化模块化
- 界面简洁美观，可灵活配置主题
- 提供全部源码，便于学习和扩展
- 完善的开发文档，助你快速掌握

## 内置功能

已有功能：

- 用户管理
- 部门管理
- 角色管理
- 菜单管理
- 权限管理
- 字典管理
- 参数管理
- 操作日志
- 登陆日志
- 代码生成
- 服务监控

待开发功能：

- 动态表单构建
- 在线用户管理
- 定时任务配置
- 内容管理模块
- 商品管理模块
- 。。。

> 之前用SpringBoot开发过一个Javabb-bbs，后面会以这个微服务脚手架来开发一个论坛管理系统。

## 项目模块

~~~
├─javaitem-auth		#授权服务
├─javaitem-base
│  ├─javaitem-cache		#缓存模块
│  ├─javaitem-common	#公共模块
│  ├─javaitem-log		#日志模块
│  ├─javaitem-security	#安全模块
│  └─javaitem-swagger	#Swagger模块
├─javaitem-gateway		#网关服务
├─javaitem-module		
│  └─javaitem-gen		#代码生成服务
├─javaitem-nacos		#nacos
├─javaitem-sys
│  ├─javaitem-sys-api		#系统Api服务
│  └─javaitem-sys-server	#系统服务

~~~

## 项目部署

1，导入mysql数据库文件，javaitem-config.sql（Nacos配置SQL）和javaitem-cloud.sql（系统SQL）文件

2，启动Nacos

项目依赖Nacos，在部署之前要先部署Nacos。

修改Nacos模块下的`nacos/conf/application.properties`文件配置：

~~~properties
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://localhost:3306/javaitem-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=root
db.password=123456
~~~

3，修改数据库链接配置

打开Nacos界面`http://localhost:8848/nacos`默认账户密码是nacos/nacos，修改其中的数据库配置和redis配置，改成本地的。

![image-20210225233701708](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225233708.png)

4，启动微服务

服务启动没有先后顺序

~~~
javaitem-sys-server:8080	（必须启动）
javaitem-auth:8001	（必须启动）
javaitem-gateway:9001	（必须启动）
javaitem-gen:8082	(非必须，代码生成用)
~~~

![image-20210225234152091](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225234152.png)

## 系统界面

系统接口

![image-20210225234401273](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225234401.png)

系统界面

![image-20210225234501414](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225234501.png)

用户管理

![image-20210225234624592](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225234624.png)

添加用户

![image-20210225234905752](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225234905.png)

角色管理

![image-20210225234654881](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225234654.png)

分配权限

![image-20210225234723045](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225234723.png)

菜单管理

![image-20210225234745444](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225234745.png)

字典管理

![image-20210225234805413](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225234805.png)

部门管理

![image-20210225234827593](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225234827.png)

操作日志

![image-20210225234936699](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225234936.png)

个人中心

![image-20210225235020941](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225235021.png)

我的消息

![image-20210225235040388](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225235040.png)

主题设置

![image-20210225235233528](https://gitee.com/imqinbao/img-bed/raw/master/typora/20210225235233.png)

