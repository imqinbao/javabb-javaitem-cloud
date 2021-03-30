
数据库表
~~~sql
# 订单数据库信息 seata_order
DROP DATABASE IF EXISTS seata_order;
CREATE DATABASE seata_order;

DROP TABLE IF EXISTS seata_order.p_order;
CREATE TABLE seata_order.p_order
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    user_id          INT(11) DEFAULT NULL,
    product_id       INT(11) DEFAULT NULL,
    amount           INT(11) DEFAULT NULL,
    total_price      DOUBLE       DEFAULT NULL,
    status           VARCHAR(100) DEFAULT NULL,
    add_time         DATETIME     DEFAULT CURRENT_TIMESTAMP,
    last_update_time DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS seata_order.undo_log;
CREATE TABLE seata_order.undo_log
(
    id            BIGINT(20) NOT NULL AUTO_INCREMENT,
    branch_id     BIGINT(20) NOT NULL,
    xid           VARCHAR(100) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info LONGBLOB     NOT NULL,
    log_status    INT(11) NOT NULL,
    log_created   DATETIME     NOT NULL,
    log_modified  DATETIME     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY ux_undo_log (xid, branch_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;
  
# 产品数据库信息 seata_product
DROP DATABASE IF EXISTS seata_product;
CREATE DATABASE seata_product;

DROP TABLE IF EXISTS seata_product.product;
CREATE TABLE seata_product.product
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    price            DOUBLE   DEFAULT NULL,
    stock            INT(11) DEFAULT NULL,
    last_update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS seata_product.undo_log;
CREATE TABLE seata_product.undo_log
(
    id            BIGINT(20) NOT NULL AUTO_INCREMENT,
    branch_id     BIGINT(20) NOT NULL,
    xid           VARCHAR(100) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info LONGBLOB     NOT NULL,
    log_status    INT(11) NOT NULL,
    log_created   DATETIME     NOT NULL,
    log_modified  DATETIME     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY ux_undo_log (xid, branch_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

INSERT INTO seata_product.product (id, price, stock)
VALUES (1, 10, 20);


# 账户数据库信息 seata_account
DROP DATABASE IF EXISTS seata_account;
CREATE DATABASE seata_account;

DROP TABLE IF EXISTS seata_account.account;
CREATE TABLE seata_account.account
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    balance          DOUBLE   DEFAULT NULL,
    last_update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS seata_account.undo_log;
CREATE TABLE seata_account.undo_log
(
    id            BIGINT(20) NOT NULL AUTO_INCREMENT,
    branch_id     BIGINT(20) NOT NULL,
    xid           VARCHAR(100) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info LONGBLOB     NOT NULL,
    log_status    INT(11) NOT NULL,
    log_created   DATETIME     NOT NULL,
    log_modified  DATETIME     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY ux_undo_log (xid, branch_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;
INSERT INTO seata_account.account (id, balance)
VALUES (1, 50);
~~~
>其中，每个库中的undo_log表，是Seata AT模式必须创建的表，主要用于分支事务的回滚。
>另外，考虑到测试方便，我们插入了一条id = 1的account记录，和一条id = 1的product记录。

Account  
Product  
Order  