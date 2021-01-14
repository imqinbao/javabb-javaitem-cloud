# 模板制作

## 模板属性

### 基本属性

在模板中可以直接通过`${xxx}`获取到

| 属性标识    | 属性说明 | 默认值                    |
| ----------- | -------- | ------------------------- |
| author      | 作者     | 例如：javabb              |
| now         | 当前日期 | 例如：2021-01-01 12:00:05 |
| packageName | 包名     | 例如：cn.javabb           |
| projectName | 项目名   | 例如：javabb-cms          |
| table       | 表信息   | 参考：TableInfo           |
| package     | 包信息   |                           |

### table 表信息

在模板中通过`${table.xxx}`获取到值。

| 属性值           | 属性说明        | 类型    |                             |
| :--------------- | --------------- | ------- | --------------------------- |
| name             | 数据库表名      | String  | 例如：t_user                |
| comment          | 数据库表注释    | String  | 例如：用户表                |
| entityName       | 实体类名        | String  | 例如：User                  |
| mapperName       | mapper接口名    | String  | 例如：UserMapper            |
| xmlName          | mybatis文件名   | String  | 例如：UserMapper            |
| serviceName      | service接口名   | String  | 例如：UserService           |
| serviceImplName  | service实现类名 | String  | 例如：UserServiceImpl       |
| controllerName   | controller类名  | String  | 例如：UserController        |
| havePk           | 表是否有主键    | boolean | 例如：true/false            |
| fieldNames       | 拼凑表字段名    | String  | 例如：id,name,age,address   |
| fields           | 表字段集合      | List    | 参考： TableField           |
| ~~commonFields~~ | 公共字段        |         | 当前没用到                  |
| importPackages   | 引入依赖        | Set     | 例如：import java.util.List |

### TableField 表字段信息

| 属性名          | 属性说明                           | 属性类型   |             |
| --------------- | ---------------------------------- | ---------- | ----------- |
| keyFlag         | 是否主键                           | boolean    | true/false  |
| keyIdentityFlag | 是否自增主键                       | boolean    | truue/false |
| name            | 列字段名                           | String     | user_name   |
| type            | 列字段类型                         | String     | varchar     |
| propertyName    | 列属性名，驼峰转换后               | String     | userName    |
| comment         | 字段描述                           | String     |             |
| keywords        | 是否关键字                         | boolean    | varchar     |
| columnName      | 列名，如果是关键字就是'columnName' |            |             |
| columnType      | 列类型                             | {type,pkg} |             |

