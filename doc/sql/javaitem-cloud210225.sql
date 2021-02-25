/*
 Navicat Premium Data Transfer

 Source Server         : alimysql
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 59.110.236.115:3306
 Source Schema         : javaitem-cloud

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 25/02/2021 23:56:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典id',
  `dict_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典标识',
  `dict_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `order_no` int(11) NOT NULL DEFAULT 1 COMMENT '排序号',
  `remark` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除,0否,1是',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES (1, 'sex', '性别', 1, '', 0, '2020-03-15 13:04:39', '2020-03-15 13:04:39');
INSERT INTO `sys_dictionary` VALUES (2, 'org_type', '机构类型', 2, '', 0, '2020-03-16 00:32:36', '2021-02-23 21:05:37');
INSERT INTO `sys_dictionary` VALUES (3, '安抚', '用户类型', 1, NULL, 0, '2021-02-22 21:54:01', '2021-02-22 21:54:01');

-- ----------------------------
-- Table structure for sys_dictionary_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary_data`;
CREATE TABLE `sys_dictionary_data`  (
  `dict_data_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典项id',
  `dict_id` int(11) NOT NULL COMMENT '字典id',
  `dict_data_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项标识',
  `dict_data_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项名称',
  `sort_no` int(11) NOT NULL DEFAULT 1 COMMENT '排序号',
  `remark` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除,0否,1是',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`dict_data_id`) USING BTREE,
  INDEX `dict_id`(`dict_id`) USING BTREE,
  CONSTRAINT `sys_dictionary_data_ibfk_1` FOREIGN KEY (`dict_id`) REFERENCES `sys_dictionary` (`dict_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典项' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dictionary_data
-- ----------------------------
INSERT INTO `sys_dictionary_data` VALUES (1, 1, 'male', '男', 1, '', 0, '2020-03-15 13:07:28', '2020-03-15 13:07:28');
INSERT INTO `sys_dictionary_data` VALUES (2, 1, 'female', '女', 2, '', 0, '2020-03-15 13:07:41', '2020-03-15 15:58:04');
INSERT INTO `sys_dictionary_data` VALUES (3, 2, 'company', '公司', 1, '', 0, '2020-03-16 00:34:32', '2020-03-16 00:34:32');
INSERT INTO `sys_dictionary_data` VALUES (4, 2, 'subsidiary', '子公司', 2, '', 0, '2020-03-16 00:35:02', '2020-03-16 00:35:02');
INSERT INTO `sys_dictionary_data` VALUES (5, 2, 'department', '部门', 3, '', 0, '2020-03-16 00:35:18', '2020-03-16 00:35:18');
INSERT INTO `sys_dictionary_data` VALUES (6, 2, 'group', '小组', 4, '', 0, '2020-03-16 00:35:36', '2020-03-16 00:35:36');

-- ----------------------------
-- Table structure for sys_login_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_record`;
CREATE TABLE `sys_login_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `os` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `device` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备名',
  `browser` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器类型',
  `ip` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `oper_type` int(11) NOT NULL COMMENT '操作类型,0登录成功,1登录失败,2退出登录,3刷新token',
  `remark` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '登录日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_login_record
-- ----------------------------
INSERT INTO `sys_login_record` VALUES (1, 'admin', 'Windows 10', 'Windows 10', 'Chrome 8', '192.168.1.6', 1, NULL, '2021-02-25 21:00:05', '2021-02-25 21:00:04');
INSERT INTO `sys_login_record` VALUES (2, 'admin', 'Windows 10', 'Windows 10', 'Chrome 8', '192.168.1.6', 0, NULL, '2021-02-25 23:45:48', '2021-02-25 23:45:47');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '上级id,0是顶级',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单路由关键字,目录为空',
  `component` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单组件地址,目录为空',
  `menu_type` int(11) NULL DEFAULT 0 COMMENT '类型,0菜单,1按钮',
  `sort_no` int(11) NOT NULL DEFAULT 1 COMMENT '排序号',
  `authority` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `target` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '_self' COMMENT '打开位置',
  `color` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标颜色',
  `uid` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '嵌套路由左侧选中',
  `hide` int(11) NOT NULL DEFAULT 0 COMMENT '是否隐藏,0否,1是(仅注册路由不显示左侧菜单)',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除,0否,1是',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', 'el-icon-setting', NULL, NULL, 0, 1, NULL, '_self', NULL, NULL, 0, 0, '2020-02-26 12:51:23', '2020-09-16 11:02:56');
INSERT INTO `sys_menu` VALUES (2, 1, '用户管理', 'el-icon-user', '/system/user', '/system/user', 0, 1, 'sys:user:view', '_self', NULL, NULL, 0, 0, '2020-02-26 12:51:55', '2020-09-16 11:05:20');
INSERT INTO `sys_menu` VALUES (3, 2, '查询用户', NULL, NULL, NULL, 1, 1, 'sys:user:list', '_self', NULL, NULL, 0, 0, '2020-02-26 12:52:06', '2020-09-16 11:05:35');
INSERT INTO `sys_menu` VALUES (4, 2, '添加用户', NULL, NULL, NULL, 1, 2, 'sys:user:save', '_self', NULL, NULL, 0, 0, '2020-02-26 12:52:26', '2020-03-21 18:45:29');
INSERT INTO `sys_menu` VALUES (5, 2, '修改用户', NULL, NULL, NULL, 1, 3, 'sys:user:update', '_self', NULL, NULL, 0, 0, '2020-02-26 12:52:50', '2020-03-21 18:45:30');
INSERT INTO `sys_menu` VALUES (6, 2, '删除用户', NULL, NULL, NULL, 1, 4, 'sys:user:remove', '_self', NULL, NULL, 0, 0, '2020-02-26 12:53:13', '2020-03-21 18:45:32');
INSERT INTO `sys_menu` VALUES (7, 1, '角色管理', 'el-icon-postcard', '/system/role', '/system/role', 0, 2, 'sys:role:view', '_self', NULL, NULL, 0, 0, '2020-03-13 13:29:08', '2020-09-16 11:06:09');
INSERT INTO `sys_menu` VALUES (8, 7, '查询角色', NULL, NULL, NULL, 1, 1, 'sys:role:list', '_self', NULL, NULL, 0, 0, '2020-03-13 13:30:41', '2020-03-21 18:45:34');
INSERT INTO `sys_menu` VALUES (9, 7, '添加角色', NULL, NULL, NULL, 1, 2, 'sys:role:save', '_self', NULL, NULL, 0, 0, '2020-03-15 13:02:07', '2020-03-21 18:45:35');
INSERT INTO `sys_menu` VALUES (10, 7, '修改角色', NULL, NULL, NULL, 1, 3, 'sys:role:update', '_self', NULL, NULL, 0, 0, '2020-03-15 13:02:49', '2020-03-21 18:45:36');
INSERT INTO `sys_menu` VALUES (11, 7, '删除角色', NULL, NULL, NULL, 1, 4, 'sys:role:remove', '_self', NULL, NULL, 0, 0, '2020-03-20 17:58:51', '2020-03-21 18:45:38');
INSERT INTO `sys_menu` VALUES (12, 1, '菜单管理', 'el-icon-s-operation', '/system/menu', '/system/menu', 0, 3, 'sys:menu:view', '_self', NULL, NULL, 0, 0, '2020-03-21 01:07:13', '2020-09-16 11:06:32');
INSERT INTO `sys_menu` VALUES (13, 12, '查询菜单', NULL, NULL, NULL, 1, 1, 'sys:menu:list', '_self', NULL, NULL, 0, 0, '2020-03-21 16:43:30', '2020-03-21 18:45:40');
INSERT INTO `sys_menu` VALUES (14, 12, '添加菜单', NULL, NULL, NULL, 1, 2, 'sys:menu:save', '_self', NULL, NULL, 0, 0, '2020-03-21 16:43:54', '2020-03-21 18:45:41');
INSERT INTO `sys_menu` VALUES (15, 12, '修改菜单', NULL, NULL, NULL, 1, 3, 'sys:menu:update', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:17', '2020-03-21 18:45:43');
INSERT INTO `sys_menu` VALUES (16, 12, '删除菜单', NULL, NULL, NULL, 1, 4, 'sys:menu:remove', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:18', '2020-03-21 18:45:44');
INSERT INTO `sys_menu` VALUES (17, 1, '机构管理', 'el-icon-office-building', '/system/organization', '/system/organization', 0, 5, 'sys:org:view', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:20', '2020-09-16 14:21:55');
INSERT INTO `sys_menu` VALUES (18, 17, '查询机构', NULL, NULL, NULL, 1, 1, 'sys:org:list', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:21', '2020-03-21 18:44:36');
INSERT INTO `sys_menu` VALUES (19, 17, '添加机构', NULL, NULL, NULL, 1, 2, 'sys:org:save', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:22', '2020-03-21 18:45:51');
INSERT INTO `sys_menu` VALUES (20, 17, '修改机构', NULL, NULL, NULL, 1, 3, 'sys:org:update', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:24', '2020-03-21 18:45:52');
INSERT INTO `sys_menu` VALUES (21, 17, '删除机构', NULL, NULL, NULL, 1, 4, 'sys:org:remove', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:25', '2020-03-21 18:45:54');
INSERT INTO `sys_menu` VALUES (22, 1, '字典管理', 'el-icon-notebook-2', '/system/dictionary', '/system/dictionary', 0, 4, 'sys:dict:view', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:26', '2020-09-16 14:21:56');
INSERT INTO `sys_menu` VALUES (23, 22, '查询字典', NULL, NULL, NULL, 1, 1, 'sys:dict:list', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:27', '2020-03-21 18:44:42');
INSERT INTO `sys_menu` VALUES (24, 22, '添加字典', NULL, NULL, NULL, 1, 2, 'sys:dict:save', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:28', '2020-03-21 18:45:59');
INSERT INTO `sys_menu` VALUES (25, 22, '修改字典', NULL, NULL, NULL, 1, 3, 'sys:dict:update', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:29', '2020-03-21 18:46:01');
INSERT INTO `sys_menu` VALUES (26, 22, '删除字典', NULL, NULL, NULL, 1, 4, 'sys:dict:remove', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:31', '2020-03-21 18:46:02');
INSERT INTO `sys_menu` VALUES (27, 1, '登录日志', 'el-icon-date', '/system/login-record', '/system/login-record', 0, 6, 'sys:login_record:view', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:33', '2020-09-16 14:22:18');
INSERT INTO `sys_menu` VALUES (28, 1, '操作日志', 'el-icon-_retrieve', '/system/oper-record', '/system/oper-record', 0, 7, 'sys:oper_record:view', '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:34', '2020-09-16 14:22:19');
INSERT INTO `sys_menu` VALUES (29, 0, 'Dashboard', 'el-icon-house', NULL, NULL, 0, 0, NULL, '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:32', '2020-09-16 13:35:06');
INSERT INTO `sys_menu` VALUES (30, 29, '分析页', 'el-icon-data-analysis', '/dashboard/console', '/dashboard/console', 0, 2, NULL, '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:35', '2020-09-16 13:20:10');
INSERT INTO `sys_menu` VALUES (31, 29, '工作台', 'el-icon-monitor', '/dashboard/workplace', '/dashboard/workplace', 0, 1, NULL, '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:36', '2020-09-16 13:20:44');
INSERT INTO `sys_menu` VALUES (32, 29, '监控页', 'el-icon-odometer', '/dashboard/monitor', '/dashboard/monitor', 0, 3, NULL, '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:38', '2020-09-16 13:21:19');
INSERT INTO `sys_menu` VALUES (33, 0, '表单页面', 'el-icon-tickets', NULL, NULL, 0, 2, NULL, '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:39', '2020-09-16 13:21:59');
INSERT INTO `sys_menu` VALUES (34, 33, '基础表单', 'el-icon-_feedback', '/form/basic', '/form/basic', 0, 1, NULL, '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:40', '2020-09-16 13:22:31');
INSERT INTO `sys_menu` VALUES (35, 33, '复杂表单', 'el-icon-_visa', '/form/advanced', '/form/advanced', 0, 2, NULL, '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:41', '2020-09-16 13:22:53');
INSERT INTO `sys_menu` VALUES (36, 33, '分步表单', 'el-icon-c-scale-to-original', '/form/step', '/form/step', 0, 3, NULL, '_self', NULL, NULL, 0, 0, '2020-03-21 18:24:42', '2020-09-16 13:23:17');
INSERT INTO `sys_menu` VALUES (37, 0, '列表页面', 'el-icon-_table', NULL, NULL, 0, 3, NULL, '_self', NULL, NULL, 0, 0, '2020-03-28 12:20:52', '2020-09-16 13:23:38');
INSERT INTO `sys_menu` VALUES (38, 37, '基础列表', 'el-icon-document', '/list/basic', '/list/basic', 0, 1, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:24:05', '2020-09-16 13:24:05');
INSERT INTO `sys_menu` VALUES (39, 37, '复杂列表', 'el-icon-_cols', '/list/advanced', '/list/advanced', 0, 2, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:24:32', '2020-09-16 13:24:32');
INSERT INTO `sys_menu` VALUES (40, 37, '卡片列表', 'el-icon-_menu', '/list/card', '/list/card', 0, 3, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:24:57', '2020-09-16 17:36:48');
INSERT INTO `sys_menu` VALUES (41, 0, '结果页面', 'el-icon-circle-check', NULL, NULL, 0, 4, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:25:15', '2020-09-16 13:25:15');
INSERT INTO `sys_menu` VALUES (42, 41, '成功页', 'el-icon-circle-check', '/result/success', '/result/success', 0, 1, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:25:32', '2020-09-16 13:25:55');
INSERT INTO `sys_menu` VALUES (43, 41, '失败页', 'el-icon-circle-close', '/result/fail', '/result/fail', 0, 2, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:25:51', '2020-09-16 13:26:02');
INSERT INTO `sys_menu` VALUES (44, 0, '异常页面', 'el-icon-document-delete', NULL, NULL, 0, 5, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:26:18', '2020-09-16 13:26:18');
INSERT INTO `sys_menu` VALUES (45, 44, '403', 'el-icon-document-checked', '/exception/403', '/common/exception/403', 0, 1, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:26:37', '2020-09-16 13:27:35');
INSERT INTO `sys_menu` VALUES (46, 44, '404', 'el-icon-document-remove', '/exception/404', '/common/exception/404', 0, 2, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:27:01', '2020-09-16 13:27:33');
INSERT INTO `sys_menu` VALUES (47, 44, '500', 'el-icon-document-delete', '/exception/500', '/common/exception/500', 0, 3, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:27:32', '2020-09-16 13:27:42');
INSERT INTO `sys_menu` VALUES (48, 0, '个人中心', 'el-icon-set-up', NULL, NULL, 0, 6, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:28:08', '2020-09-16 13:28:08');
INSERT INTO `sys_menu` VALUES (49, 48, '个人资料', 'el-icon-_prerogative', '/user/info', '/user/info', 0, 1, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:28:29', '2020-09-16 13:28:29');
INSERT INTO `sys_menu` VALUES (50, 48, '我的消息', 'el-icon-chat-dot-round', '/user/message', '/user/message', 0, 2, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:28:46', '2020-09-16 13:29:42');
INSERT INTO `sys_menu` VALUES (51, 0, '扩展组件', 'el-icon-open', NULL, NULL, 0, 7, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:29:38', '2020-09-16 13:29:38');
INSERT INTO `sys_menu` VALUES (52, 51, '图标', 'el-icon-_face-smile', '/extension/basic/icon', '/extension/basic/icon', 0, 1, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:30:02', '2020-09-16 13:30:02');
INSERT INTO `sys_menu` VALUES (53, 51, '标签', 'el-icon-price-tag', '/extension/basic/tag', '/extension/basic/tag', 0, 2, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:30:33', '2020-09-16 13:30:33');
INSERT INTO `sys_menu` VALUES (54, 51, '步骤条', 'el-icon-_timeline', '/extension/basic/steps', '/extension/basic/steps', 0, 3, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:30:57', '2020-09-16 13:30:57');
INSERT INTO `sys_menu` VALUES (55, 51, '警告', 'el-icon-_warning', '/extension/basic/alert', '/extension/basic/alert', 0, 4, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:31:18', '2020-09-16 13:31:18');
INSERT INTO `sys_menu` VALUES (56, 51, '导航', 'el-icon-_nav', '/extension/basic/menu', '/extension/basic/menu', 0, 5, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:31:35', '2020-09-16 13:31:45');
INSERT INTO `sys_menu` VALUES (57, 51, '更多', 'el-icon-more', '/extension/basic/more', '/extension/basic/more', 0, 6, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:32:04', '2020-09-16 13:32:04');
INSERT INTO `sys_menu` VALUES (58, 0, '高级组件', 'el-icon-_component', NULL, NULL, 0, 8, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:32:19', '2020-09-16 13:32:19');
INSERT INTO `sys_menu` VALUES (59, 58, '打印插件', 'el-icon-printer', '/extension/advanced/printer', '/extension/advanced/printer', 0, 1, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:32:39', '2020-09-16 13:32:39');
INSERT INTO `sys_menu` VALUES (60, 58, 'excel插件', 'el-icon-_table', '/extension/advanced/excel', '/extension/advanced/excel', 0, 2, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:32:59', '2020-09-16 13:32:59');
INSERT INTO `sys_menu` VALUES (61, 58, '拖拽排序', 'el-icon-rank', '/extension/advanced/dragsort', '/extension/advanced/dragsort', 0, 3, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:33:18', '2020-09-16 13:33:18');
INSERT INTO `sys_menu` VALUES (62, 58, '地图插件', 'el-icon-map-location', '/extension/advanced/map', '/extension/advanced/map', 0, 4, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:33:41', '2020-09-16 13:33:41');
INSERT INTO `sys_menu` VALUES (63, 58, '视频播放器', 'el-icon-_video', '/extension/advanced/player', '/extension/advanced/player', 0, 5, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:33:56', '2020-09-16 13:33:56');
INSERT INTO `sys_menu` VALUES (64, 58, '富文本编辑器', 'el-icon-_font-family', '/extension/advanced/editor', '/extension/advanced/editor', 0, 6, NULL, '_self', NULL, NULL, 0, 0, '2020-09-16 13:34:14', '2020-09-16 13:34:14');

-- ----------------------------
-- Table structure for sys_oper_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_record`;
CREATE TABLE `sys_oper_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `model` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作模块',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作方法',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `request_method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `oper_method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用方法',
  `param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '返回结果',
  `ip` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `spend_time` int(11) NULL DEFAULT NULL COMMENT '请求耗时,单位毫秒',
  `state` int(11) NOT NULL DEFAULT 0 COMMENT '状态,0成功,1异常',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `sys_oper_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oper_record
-- ----------------------------

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization`  (
  `org_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '机构id',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '上级id,0是顶级',
  `org_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构名称',
  `org_full_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构全称',
  `org_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构代码',
  `org_type` int(11) NOT NULL COMMENT '机构类型',
  `leader_id` int(11) NULL DEFAULT NULL COMMENT '负责人id',
  `sort_no` int(11) NOT NULL DEFAULT 1 COMMENT '排序号',
  `remark` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除,0否,1是',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`org_id`) USING BTREE,
  INDEX `leader_id`(`leader_id`) USING BTREE,
  CONSTRAINT `sys_organization_ibfk_1` FOREIGN KEY (`leader_id`) REFERENCES `sys_user` (`user_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织机构' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES (1, 0, '湖北集团总公司', 'XXXXXXXXX科技有限公司', '1', 3, NULL, 1, '', 0, '2020-03-15 13:14:55', '2020-03-21 15:12:49');
INSERT INTO `sys_organization` VALUES (2, 1, '武汉分公司', '武汉分公司', '11', 5, NULL, 2, '', 0, '2020-03-15 13:15:16', '2020-03-16 00:43:09');
INSERT INTO `sys_organization` VALUES (3, 2, '后端研发部门', '后端研发部门', '121', 6, NULL, 3, '', 0, '2020-03-15 13:15:45', '2020-03-16 00:42:49');
INSERT INTO `sys_organization` VALUES (4, 2, '前端研发部门', '前端研发部门', '122', 6, NULL, 4, '', 0, '2020-03-15 13:16:15', '2020-03-16 00:42:54');
INSERT INTO `sys_organization` VALUES (5, 2, '人事部', '人事部', '123', 6, NULL, 5, '', 0, '2020-03-15 13:16:37', '2020-03-21 15:13:05');
INSERT INTO `sys_organization` VALUES (6, 2, '大数据组', '大数据组', '124', 6, NULL, 6, '', 0, '2020-03-15 13:16:57', '2020-03-16 00:43:03');
INSERT INTO `sys_organization` VALUES (7, 1, '宜昌分公司', '宜昌分公司', '12', 5, NULL, 6, '', 0, '2020-03-15 13:17:19', '2020-03-16 00:43:14');
INSERT INTO `sys_organization` VALUES (8, 1, '襄阳分公司', 'XX集团襄阳分公司', '13', 5, NULL, 7, '', 0, '2020-03-15 13:17:56', '2020-03-16 00:43:18');
INSERT INTO `sys_organization` VALUES (9, 1, '荆州分公司', 'XX集团荆州分公司', '14', 5, NULL, 8, '', 0, '2020-03-15 13:18:15', '2020-03-16 00:43:23');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除,0否,1是',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', '管理员', 0, '2020-02-26 15:18:37', '2020-03-21 15:15:54');
INSERT INTO `sys_role` VALUES (2, '普通用户', 'user', '普通用户', 0, '2020-02-26 15:18:52', '2020-03-21 15:16:02');
INSERT INTO `sys_role` VALUES (3, '游客', 'guest', '游客', 0, '2020-02-26 15:19:49', '2020-03-21 15:16:57');
INSERT INTO `sys_role` VALUES (5, 'asdf', 'asdf', NULL, 0, '2021-02-21 17:07:30', '2021-02-21 17:07:30');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_sys_role_permission_role`(`role_id`) USING BTREE,
  INDEX `menu_id`(`menu_id`) USING BTREE,
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, 1, '2020-09-16 13:35:38', '2020-09-16 13:35:38');
INSERT INTO `sys_role_menu` VALUES (2, 1, 2, '2020-09-16 13:35:41', '2020-09-16 13:35:41');
INSERT INTO `sys_role_menu` VALUES (3, 1, 3, '2020-09-16 13:35:43', '2020-09-16 13:35:43');
INSERT INTO `sys_role_menu` VALUES (4, 1, 4, '2020-09-16 13:35:45', '2020-09-16 13:35:45');
INSERT INTO `sys_role_menu` VALUES (5, 1, 5, '2020-09-16 13:35:46', '2020-09-16 13:35:46');
INSERT INTO `sys_role_menu` VALUES (6, 1, 6, '2020-09-16 13:35:49', '2020-09-16 13:35:49');
INSERT INTO `sys_role_menu` VALUES (7, 1, 7, '2020-09-16 13:35:50', '2020-09-16 13:35:50');
INSERT INTO `sys_role_menu` VALUES (8, 1, 8, '2020-09-16 13:35:52', '2020-09-16 13:35:52');
INSERT INTO `sys_role_menu` VALUES (9, 1, 9, '2020-09-16 13:35:54', '2020-09-16 13:35:54');
INSERT INTO `sys_role_menu` VALUES (10, 1, 10, '2020-09-16 13:35:56', '2020-09-16 13:35:56');
INSERT INTO `sys_role_menu` VALUES (11, 1, 11, '2020-09-16 13:35:57', '2020-09-16 13:35:57');
INSERT INTO `sys_role_menu` VALUES (12, 1, 12, '2020-09-16 13:35:59', '2020-09-16 13:35:59');
INSERT INTO `sys_role_menu` VALUES (13, 1, 13, '2020-09-16 13:36:01', '2020-09-16 13:36:01');
INSERT INTO `sys_role_menu` VALUES (14, 1, 14, '2020-09-16 13:36:03', '2020-09-16 13:36:03');
INSERT INTO `sys_role_menu` VALUES (15, 1, 15, '2020-09-16 13:36:05', '2020-09-16 13:36:05');
INSERT INTO `sys_role_menu` VALUES (16, 1, 16, '2020-09-16 13:36:07', '2020-09-16 13:36:07');
INSERT INTO `sys_role_menu` VALUES (17, 1, 17, '2020-09-16 13:36:08', '2020-09-16 13:36:08');
INSERT INTO `sys_role_menu` VALUES (18, 1, 18, '2020-09-16 13:36:10', '2020-09-16 13:36:10');
INSERT INTO `sys_role_menu` VALUES (19, 1, 19, '2020-09-16 13:36:12', '2020-09-16 13:36:12');
INSERT INTO `sys_role_menu` VALUES (20, 1, 20, '2020-09-16 13:36:15', '2020-09-16 13:36:15');
INSERT INTO `sys_role_menu` VALUES (21, 1, 21, '2020-09-16 13:36:18', '2020-09-16 13:36:18');
INSERT INTO `sys_role_menu` VALUES (22, 1, 22, '2020-09-16 13:36:20', '2020-09-16 13:36:20');
INSERT INTO `sys_role_menu` VALUES (23, 1, 23, '2020-09-16 13:36:22', '2020-09-16 13:36:22');
INSERT INTO `sys_role_menu` VALUES (24, 1, 24, '2020-09-16 13:36:23', '2020-09-16 13:36:23');
INSERT INTO `sys_role_menu` VALUES (25, 1, 25, '2020-09-16 13:36:25', '2020-09-16 13:36:25');
INSERT INTO `sys_role_menu` VALUES (26, 1, 26, '2020-09-16 13:36:27', '2020-09-16 13:36:27');
INSERT INTO `sys_role_menu` VALUES (27, 1, 27, '2020-09-16 13:36:33', '2020-09-16 13:36:33');
INSERT INTO `sys_role_menu` VALUES (28, 1, 28, '2020-09-16 13:36:36', '2020-09-16 13:36:36');
INSERT INTO `sys_role_menu` VALUES (29, 1, 29, '2020-09-16 13:36:39', '2020-09-16 13:36:39');
INSERT INTO `sys_role_menu` VALUES (30, 1, 30, '2020-09-16 13:36:41', '2020-09-16 13:36:41');
INSERT INTO `sys_role_menu` VALUES (31, 1, 31, '2020-09-16 13:36:43', '2020-09-16 13:36:43');
INSERT INTO `sys_role_menu` VALUES (32, 1, 32, '2020-09-16 13:36:45', '2020-09-16 13:36:45');
INSERT INTO `sys_role_menu` VALUES (33, 1, 33, '2020-09-16 13:36:47', '2020-09-16 13:36:47');
INSERT INTO `sys_role_menu` VALUES (34, 1, 34, '2020-09-16 13:36:49', '2020-09-16 13:36:49');
INSERT INTO `sys_role_menu` VALUES (35, 1, 35, '2020-09-16 13:36:51', '2020-09-16 13:36:51');
INSERT INTO `sys_role_menu` VALUES (36, 1, 36, '2020-09-16 13:36:53', '2020-09-16 13:36:53');
INSERT INTO `sys_role_menu` VALUES (37, 1, 37, '2020-09-16 13:36:54', '2020-09-16 13:36:54');
INSERT INTO `sys_role_menu` VALUES (38, 1, 38, '2020-09-16 13:36:56', '2020-09-16 13:36:56');
INSERT INTO `sys_role_menu` VALUES (39, 1, 39, '2020-09-16 13:37:01', '2020-09-16 13:37:01');
INSERT INTO `sys_role_menu` VALUES (40, 1, 40, '2020-09-16 13:37:03', '2020-09-16 13:37:03');
INSERT INTO `sys_role_menu` VALUES (41, 1, 41, '2020-09-16 13:37:05', '2020-09-16 13:37:05');
INSERT INTO `sys_role_menu` VALUES (42, 1, 42, '2020-09-16 13:37:07', '2020-09-16 13:37:07');
INSERT INTO `sys_role_menu` VALUES (43, 1, 43, '2020-09-16 13:37:09', '2020-09-16 13:37:09');
INSERT INTO `sys_role_menu` VALUES (44, 1, 44, '2020-09-16 13:37:10', '2020-09-16 13:37:10');
INSERT INTO `sys_role_menu` VALUES (45, 1, 45, '2020-09-16 13:37:13', '2020-09-16 13:37:13');
INSERT INTO `sys_role_menu` VALUES (46, 1, 46, '2020-09-16 13:37:16', '2020-09-16 13:37:16');
INSERT INTO `sys_role_menu` VALUES (47, 1, 47, '2020-09-16 13:37:18', '2020-09-16 13:37:18');
INSERT INTO `sys_role_menu` VALUES (48, 1, 48, '2020-09-16 13:37:21', '2020-09-16 13:37:21');
INSERT INTO `sys_role_menu` VALUES (49, 1, 49, '2020-09-16 13:37:24', '2020-09-16 13:37:24');
INSERT INTO `sys_role_menu` VALUES (50, 1, 50, '2020-09-16 13:38:22', '2020-09-16 13:38:22');
INSERT INTO `sys_role_menu` VALUES (51, 1, 51, '2020-09-16 13:37:29', '2020-09-16 13:38:07');
INSERT INTO `sys_role_menu` VALUES (52, 1, 52, '2020-09-16 13:37:32', '2020-09-16 13:38:04');
INSERT INTO `sys_role_menu` VALUES (53, 1, 53, '2020-09-16 13:37:34', '2020-09-16 13:38:02');
INSERT INTO `sys_role_menu` VALUES (54, 1, 54, '2020-09-16 13:37:35', '2020-09-16 13:38:00');
INSERT INTO `sys_role_menu` VALUES (55, 1, 55, '2020-09-16 13:37:39', '2020-09-16 13:37:58');
INSERT INTO `sys_role_menu` VALUES (56, 1, 56, '2020-09-16 13:37:41', '2020-09-16 13:37:57');
INSERT INTO `sys_role_menu` VALUES (57, 1, 57, '2020-09-16 13:37:43', '2020-09-16 13:37:54');
INSERT INTO `sys_role_menu` VALUES (58, 1, 58, '2020-09-16 13:37:45', '2020-09-16 13:37:53');
INSERT INTO `sys_role_menu` VALUES (59, 1, 59, '2020-09-16 13:37:51', '2020-09-16 13:37:51');
INSERT INTO `sys_role_menu` VALUES (60, 1, 60, '2020-09-16 13:38:28', '2020-09-16 13:38:28');
INSERT INTO `sys_role_menu` VALUES (61, 1, 61, '2020-09-16 13:38:32', '2020-09-16 13:38:32');
INSERT INTO `sys_role_menu` VALUES (62, 1, 62, '2020-09-16 13:38:34', '2020-09-16 13:38:34');
INSERT INTO `sys_role_menu` VALUES (63, 1, 63, '2020-09-16 13:38:36', '2020-09-16 13:38:36');
INSERT INTO `sys_role_menu` VALUES (64, 1, 64, '2020-09-16 13:38:38', '2020-09-16 13:38:38');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `email_verified` int(1) NOT NULL DEFAULT 0 COMMENT '邮箱是否验证,0否,1是',
  `true_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `introduction` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '机构id',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '状态,0正常,1冻结',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除,0否,1是',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `org_id`(`org_id`) USING BTREE,
  CONSTRAINT `org_id` FOREIGN KEY (`org_id`) REFERENCES `sys_organization` (`org_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$QbAbiMqWF/1g4K6CMxJD8.qZPTcWQKoQaylnJS3o55cUq/Dx8/vem', '管理员', 'https://gitee.com/imqinbao/img-bed/raw/master/avatar/20210203205312.jpeg', 1, '12345678901', '', 0, NULL, NULL, NULL, '备注', 1, 0, 0, '2020-01-13 14:43:52', '2021-02-25 23:55:31');
INSERT INTO `sys_user` VALUES (8, 'javabb', '$2a$10$rsQwKdgqEBSooNfpnpdtz.9CbB/OXLy6PfFTPcepOwaB0UiUzDo0S', 'javabb', NULL, 1, '17689408313', 'javabb@163.com', 0, NULL, NULL, NULL, '个人简介', NULL, 0, 0, '2021-02-19 17:04:57', '2021-02-19 17:04:57');
INSERT INTO `sys_user` VALUES (9, 'jack', '$2a$10$U3tpj5Ar8U8sSgI1FZ7HouAo//97roXSQtxlSG.HDvXvMH7XDm9ZK', 'jack', NULL, 1, NULL, NULL, 0, NULL, NULL, NULL, 'admin', NULL, 0, 0, '2021-02-19 17:05:32', '2021-02-24 00:06:54');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_sys_user_role`(`user_id`) USING BTREE,
  INDEX `FK_sys_user_role_role`(`role_id`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1, '2020-03-12 23:20:08', '2020-03-21 15:18:00');
INSERT INTO `sys_user_role` VALUES (10, 9, 2, '2021-02-19 17:54:12', '2021-02-19 17:54:12');

SET FOREIGN_KEY_CHECKS = 1;
