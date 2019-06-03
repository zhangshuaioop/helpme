/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : helpme

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-06-03 13:18:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `dept` varchar(100) DEFAULT NULL COMMENT '部门',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `flag_enabled` tinyint(1) DEFAULT '0' COMMENT '是否启用0禁用1启用',
  `head_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `login_date` datetime DEFAULT NULL COMMENT '登陆时间',
  `login_ip` varchar(20) DEFAULT NULL COMMENT '登陆ip',
  `flag_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除0未删除1删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', '技术部', '啦啦啦啦', '1', null, '', '13565678909', null, null, '0', '2019-01-31 14:08:47', '2019-05-31 11:41:37');
INSERT INTO `sys_admin` VALUES ('2', 'user', 'e10adc3949ba59abbe56e057f20f883e', '管理员', 'it', '呵呵呵呵', '1', null, '', '', null, null, '0', '2019-05-30 10:22:47', '2019-06-03 12:47:52');
INSERT INTO `sys_admin` VALUES ('73', '213421', 'e10adc3949ba59abbe56e057f20f883e', '', '', '', '0', null, '', '', null, null, '1', '2019-06-03 12:41:28', '2019-06-03 12:46:02');
INSERT INTO `sys_admin` VALUES ('74', '76', 'e10adc3949ba59abbe56e057f20f883e', '', '', '', '1', null, '', '', null, null, '1', '2019-06-03 13:03:45', '2019-06-03 13:03:53');

-- ----------------------------
-- Table structure for sys_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role`;
CREATE TABLE `sys_admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';

-- ----------------------------
-- Records of sys_admin_role
-- ----------------------------
INSERT INTO `sys_admin_role` VALUES ('1', '1', '1');
INSERT INTO `sys_admin_role` VALUES ('9', '73', '2');
INSERT INTO `sys_admin_role` VALUES ('10', '73', '1');
INSERT INTO `sys_admin_role` VALUES ('11', '2', '2');

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL COMMENT '资源路径',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `flag_enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用0禁用1启用',
  `flag_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除0未删除1删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `icon` varchar(15) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES ('1', '用户管理', '0', '1', '', '用户管理XXX', '1', '0', '2019-01-31 14:32:39', '2019-01-31 14:32:41', 'dashboard');
INSERT INTO `sys_authority` VALUES ('3', '用户列表', '1', '3', '/admin/admin/list', '前端注册的用户', '1', '0', '2019-01-31 14:34:28', '2019-01-31 14:34:29', null);
INSERT INTO `sys_authority` VALUES ('4', '系统管理', '0', '4', null, '系统管理XXX', '1', '0', '2019-01-31 14:35:10', '2019-01-31 14:35:12', 'desktop');
INSERT INTO `sys_authority` VALUES ('5', 'banner', '4', '5', null, 'banner管理', '1', '0', '2019-01-31 14:35:53', '2019-01-31 14:35:54', null);
INSERT INTO `sys_authority` VALUES ('6', '消息管理', '0', '6', null, null, '1', '0', '2019-02-01 17:11:25', '2019-02-01 17:11:26', 'bar-chart-o');
INSERT INTO `sys_authority` VALUES ('7', '角色列表', '1', '7', '/admin/role/list', '角色列表', '1', '0', '2019-02-01 17:11:49', '2019-02-01 17:11:50', 'qrcode');
INSERT INTO `sys_authority` VALUES ('8', '模板管理', '0', '8', null, null, '1', '0', '2019-02-01 17:12:24', '2019-02-01 17:12:25', 'table');
INSERT INTO `sys_authority` VALUES ('9', '组织结构', '0', '9', null, null, '1', '0', '2019-02-01 17:13:04', '2019-02-01 17:13:05', 'edit');
INSERT INTO `sys_authority` VALUES ('10', '视频管理', '0', '10', null, null, '1', '0', '2019-02-01 17:13:18', '2019-02-01 17:13:20', 'sitemap');
INSERT INTO `sys_authority` VALUES ('11', '服务管理', '0', '11', null, null, '1', '0', '2019-02-01 17:13:37', '2019-02-01 17:13:38', 'file');
INSERT INTO `sys_authority` VALUES ('12', '类型管理', '0', '12', null, null, '1', '0', '2019-02-01 17:13:53', '2019-02-01 17:13:55', 'qrcode');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `flag_enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用0禁用1启用',
  `flag_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除0未删除1删除',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '1', '0', '所有权限', '2019-01-31 14:31:27', '2019-01-31 14:31:29');
INSERT INTO `sys_role` VALUES ('2', '普通管理员', '1', '0', '', '2019-05-31 16:29:48', '2019-06-03 12:48:17');
INSERT INTO `sys_role` VALUES ('3', '刚回来', '1', '1', '', '2019-05-31 16:31:42', '2019-05-31 16:31:42');
INSERT INTO `sys_role` VALUES ('4', '12321', '1', '1', '', '2019-05-31 19:37:49', '2019-05-31 19:37:49');
INSERT INTO `sys_role` VALUES ('5', '呵呵呵', '1', '1', '', '2019-05-31 23:20:05', '2019-06-03 10:59:24');
INSERT INTO `sys_role` VALUES ('6', '1', '1', '1', '', '2019-06-02 21:47:09', '2019-06-02 21:47:09');
INSERT INTO `sys_role` VALUES ('7', '11', '1', '1', '', '2019-06-02 21:47:18', '2019-06-02 21:47:18');
INSERT INTO `sys_role` VALUES ('8', 'ds', '1', '1', '', '2019-06-02 21:49:44', '2019-06-02 21:49:44');
INSERT INTO `sys_role` VALUES ('9', 'fds', '1', '1', '', '2019-06-02 21:51:07', '2019-06-02 21:51:07');
INSERT INTO `sys_role` VALUES ('10', 'dsds', '1', '1', '', '2019-06-02 22:39:25', '2019-06-02 22:39:25');
INSERT INTO `sys_role` VALUES ('11', 'fsdfsd', '1', '1', '', '2019-06-02 22:39:57', '2019-06-02 22:39:57');
INSERT INTO `sys_role` VALUES ('12', 'fsddsds', '1', '1', '', '2019-06-02 22:40:50', '2019-06-02 22:40:50');
INSERT INTO `sys_role` VALUES ('13', '范德萨广东', '1', '1', '', '2019-06-03 09:51:27', '2019-06-03 10:59:49');
INSERT INTO `sys_role` VALUES ('14', '大师傅但是', '1', '1', '', '2019-06-03 11:00:23', '2019-06-03 11:00:23');
INSERT INTO `sys_role` VALUES ('15', '和任何人', '1', '1', '', '2019-06-03 11:12:59', '2019-06-03 11:13:23');
INSERT INTO `sys_role` VALUES ('16', '12', '1', '1', '', '2019-06-03 13:00:45', '2019-06-03 13:00:45');
INSERT INTO `sys_role` VALUES ('17', '12', '1', '1', '', '2019-06-03 13:00:51', '2019-06-03 13:00:51');
INSERT INTO `sys_role` VALUES ('18', 'oooo', '1', '1', '', '2019-06-03 13:03:17', '2019-06-03 13:03:28');

-- ----------------------------
-- Table structure for sys_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_authority`;
CREATE TABLE `sys_role_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COMMENT='系统角色权限表';

-- ----------------------------
-- Records of sys_role_authority
-- ----------------------------
INSERT INTO `sys_role_authority` VALUES ('1', '1', '1');
INSERT INTO `sys_role_authority` VALUES ('2', '1', '2');
INSERT INTO `sys_role_authority` VALUES ('3', '1', '3');
INSERT INTO `sys_role_authority` VALUES ('4', '1', '4');
INSERT INTO `sys_role_authority` VALUES ('5', '1', '5');
INSERT INTO `sys_role_authority` VALUES ('6', '1', '6');
INSERT INTO `sys_role_authority` VALUES ('7', '1', '7');
INSERT INTO `sys_role_authority` VALUES ('8', '1', '8');
INSERT INTO `sys_role_authority` VALUES ('9', '1', '9');
INSERT INTO `sys_role_authority` VALUES ('10', '1', '10');
INSERT INTO `sys_role_authority` VALUES ('11', '1', '11');
INSERT INTO `sys_role_authority` VALUES ('12', '1', '12');
INSERT INTO `sys_role_authority` VALUES ('13', '10', '1');
INSERT INTO `sys_role_authority` VALUES ('14', '10', '3');
INSERT INTO `sys_role_authority` VALUES ('15', '11', '1');
INSERT INTO `sys_role_authority` VALUES ('16', '11', '3');
INSERT INTO `sys_role_authority` VALUES ('17', '11', '4');
INSERT INTO `sys_role_authority` VALUES ('18', '11', '5');
INSERT INTO `sys_role_authority` VALUES ('19', '12', '1');
INSERT INTO `sys_role_authority` VALUES ('20', '12', '3');
INSERT INTO `sys_role_authority` VALUES ('44', '5', '1');
INSERT INTO `sys_role_authority` VALUES ('45', '5', '3');
INSERT INTO `sys_role_authority` VALUES ('46', '5', '7');
INSERT INTO `sys_role_authority` VALUES ('47', '5', '4');
INSERT INTO `sys_role_authority` VALUES ('48', '5', '5');
INSERT INTO `sys_role_authority` VALUES ('49', '13', '1');
INSERT INTO `sys_role_authority` VALUES ('50', '13', '4');
INSERT INTO `sys_role_authority` VALUES ('51', '13', '5');
INSERT INTO `sys_role_authority` VALUES ('52', '13', '6');
INSERT INTO `sys_role_authority` VALUES ('53', '14', '3');
INSERT INTO `sys_role_authority` VALUES ('54', '14', '4');
INSERT INTO `sys_role_authority` VALUES ('59', '15', '3');
INSERT INTO `sys_role_authority` VALUES ('60', '15', '4');
INSERT INTO `sys_role_authority` VALUES ('61', '15', '5');
INSERT INTO `sys_role_authority` VALUES ('62', '2', '1');
INSERT INTO `sys_role_authority` VALUES ('63', '2', '3');
