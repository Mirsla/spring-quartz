/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : framework

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 28/11/2019 17:37:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uri` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '访问路径',
  `method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求的方法。默认为ALL',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) NOT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1, '/user', 'ALL', '用户页面', 1);
INSERT INTO `authority` VALUES (2, '/log', 'ALL', '日志页面', 1);
INSERT INTO `authority` VALUES (3, '/role', 'ALL', '角色页面', 1);
INSERT INTO `authority` VALUES (4, '/product', 'ALL', '商品页面', 1);
INSERT INTO `authority` VALUES (5, '/menu', 'ALL', '菜单页面', 1);

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_authority
-- ----------------------------
INSERT INTO `role_authority` VALUES (1, 1, 1);
INSERT INTO `role_authority` VALUES (2, 1, 2);
INSERT INTO `role_authority` VALUES (3, 1, 3);
INSERT INTO `role_authority` VALUES (4, 2, 4);
INSERT INTO `role_authority` VALUES (5, 2, 5);

SET FOREIGN_KEY_CHECKS = 1;
