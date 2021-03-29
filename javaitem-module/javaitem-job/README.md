
## 常见问题
- 问题1
~~~
Failure obtaining db row lock: Table 'javaitem-cloud.QRTZ_LOCKS' doesn't exist
~~~
解决办法:
异常信息表明：在创建quartz自带表时，表明是小写的，程序中使用的是大写表明，由于mysql数据库区分大小写，故无法找到大写表名的表。

解决方案一:  
将对应quartz自带表的表名全部改为大写。

解决方案二:  
修改mysql数据库，使其不区分大小写，在my.cnf的[mysqld]节点下加入lower-case-table-names=1
sudo vim /etc/mysql/my.cnf
在[mysqld]下面添加：
lower_case_table_names = 1
然后保存退出，重启Mysql
sudo restart mysql