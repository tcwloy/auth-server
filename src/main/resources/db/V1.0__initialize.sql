
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
                             `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                             `name` varchar(32) NOT NULL COMMENT '权限名称',
                             `code` varchar(32) NOT NULL DEFAULT '' COMMENT '权限code',
                             `remark` varchar(64) NOT NULL DEFAULT '' COMMENT '权限说明',
                             `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
                              `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                              `name` varchar(64) NOT NULL DEFAULT '' COMMENT '部门名称',
                              `code` varchar(32) NOT NULL DEFAULT '' COMMENT '部门编码',
                              `type` tinyint(2) NOT NULL COMMENT '部门类型',
                              `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级部门id',
                              `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志',
                              `creata_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                        `name` varchar(32) NOT NULL DEFAULT '' COMMENT '角色名称',
                        `remark` varchar(64) NOT NULL DEFAULT '' COMMENT '角色描述',
                        `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for role_authority_rel
-- ----------------------------
DROP TABLE IF EXISTS `role_authority_rel`;
CREATE TABLE `role_authority_rel` (
                                      `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                                      `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
                                      `authority_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '权限id',
                                      `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                                      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      PRIMARY KEY (`id`),
                                      KEY `ix_role` (`role_id`),
                                      KEY `ix_authority` (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                        `name` varchar(64) NOT NULL DEFAULT '' COMMENT '姓名',
                        `sex` tinyint(1) NOT NULL DEFAULT '-1' COMMENT '性别 -1:不详 0:女 1:男',
                        `id_no` varchar(32) NOT NULL DEFAULT '' COMMENT '身份证号',
                        `phone` varchar(24) NOT NULL DEFAULT '' COMMENT '手机号',
                        `birthday` date DEFAULT NULL COMMENT '出生日期',
                        `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志位 0:未删除 1:已删除',
                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`),
                        KEY `ix_name` (`name`),
                        KEY `ix_id_no` (`id_no`),
                        KEY `ix_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='用户基本信息表';

-- ----------------------------
-- Table structure for user_dept_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_dept_rel`;
CREATE TABLE `user_dept_rel` (
                                 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                                 `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
                                 `dept_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '部门id',
                                 `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_role_rel`;
CREATE TABLE `user_role_rel` (
                                 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                                 `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
                                 `role_id` bigint(20) NOT NULL COMMENT '角色id',
                                 `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`),
                                 KEY `ix_user` (`user_id`),
                                 KEY `ix_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
