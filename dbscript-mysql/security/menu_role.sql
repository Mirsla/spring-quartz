/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : lottery

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 27/11/2019 17:53:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL COMMENT '父菜单ID，没有为-1',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单的访问路径',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'icon 图标',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '多级菜单的排序，从大到小',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '菜单的状态，0启用，1启用',
  `level` int(11) NOT NULL DEFAULT 1 COMMENT '菜单层级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, -1, '/index', '主页', 'fa-dashboard', 0, 1, 1);
INSERT INTO `menu` VALUES (2, -1, '#', '系统管理', 'fa-windows', 100, 1, 1);
INSERT INTO `menu` VALUES (3, 2, '/menu', '菜单管理', 'fa-circle-o', 0, 1, 2);
INSERT INTO `menu` VALUES (4, 2, '/role', '角色管理', 'fa-circle-o', 100, 1, 2);
INSERT INTO `menu` VALUES (5, 2, '/sys/user', '系统用户管理', 'fa-circle-o', 200, 1, 2);
INSERT INTO `menu` VALUES (6, -1, '#', '一级菜单', 'fa-edit', 300, 1, 1);
INSERT INTO `menu` VALUES (7, 6, '/two', '二级菜单1', 'fa-circle-o', 0, 1, 2);
INSERT INTO `menu` VALUES (8, 6, '#', '二级菜单2', 'fa-circle-o', 100, 1, 2);
INSERT INTO `menu` VALUES (9, 6, '#', '二级菜单3', 'fa-circle-o', 200, 1, 2);
INSERT INTO `menu` VALUES (10, 6, '#', '二级菜单4', 'fa-circle-o', 300, 1, 2);
INSERT INTO `menu` VALUES (11, 8, '#', '三级菜单1', 'fa-circle-o', 0, 1, 3);
INSERT INTO `menu` VALUES (12, 8, '#', '三级菜单2', 'fa-circle-o', 200, 1, 3);
INSERT INTO `menu` VALUES (13, 10, '#', '其他菜单1', 'fa-circle-o', 100, 1, 3);
INSERT INTO `menu` VALUES (14, 10, '#', '其他菜单2', 'fa-circle-o', 0, 1, 3);

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `menu_role_index`(`menu_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单，角色关系对应表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES (1, 1, 1);
INSERT INTO `menu_role` VALUES (15, 1, 2);
INSERT INTO `menu_role` VALUES (2, 2, 1);
INSERT INTO `menu_role` VALUES (16, 2, 2);
INSERT INTO `menu_role` VALUES (3, 3, 1);
INSERT INTO `menu_role` VALUES (17, 3, 2);
INSERT INTO `menu_role` VALUES (4, 4, 1);
INSERT INTO `menu_role` VALUES (18, 4, 2);
INSERT INTO `menu_role` VALUES (5, 5, 1);
INSERT INTO `menu_role` VALUES (6, 6, 1);
INSERT INTO `menu_role` VALUES (7, 7, 1);
INSERT INTO `menu_role` VALUES (8, 8, 1);
INSERT INTO `menu_role` VALUES (9, 9, 1);
INSERT INTO `menu_role` VALUES (10, 10, 1);
INSERT INTO `menu_role` VALUES (14, 11, 1);
INSERT INTO `menu_role` VALUES (11, 12, 1);
INSERT INTO `menu_role` VALUES (12, 13, 1);
INSERT INTO `menu_role` VALUES (13, 14, 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `company_id` int(11) NOT NULL COMMENT '公司ID',
  `created_time` bigint(19) NOT NULL COMMENT '创建时间',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enabled` int(11) NOT NULL DEFAULT 0 COMMENT '角色是否有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '系统管理员', 0, 1570608114000, '整个系统的系统管理员', 1);
INSERT INTO `role` VALUES (2, '普通管理员', 0, 1570608114000, '看看菜单重复问题的管理员', 1);

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `sys_user_id` int(11) NOT NULL COMMENT '系统用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_index`(`role_id`, `sys_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色对应表，一个用于可以对应多个角色\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES (1, 1, 1);
INSERT INTO `role_user` VALUES (2, 2, 1);

SET FOREIGN_KEY_CHECKS = 1;
