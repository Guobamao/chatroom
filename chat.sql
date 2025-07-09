/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : chat

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 09/07/2025 17:38:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for db_account
-- ----------------------------
DROP TABLE IF EXISTS `db_account`;
CREATE TABLE `db_account`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `avatar` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户链接',
  `role` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户权限',
  `register_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '账户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_account
-- ----------------------------
INSERT INTO `db_account` VALUES (8, '张三', '$2a$10$6su00xsmk0bll4UM/JE1weCl7PBnoke9pWFoo9MOjwjNXyVTA2GBC', '123456@qq.com', 'https://www.keaitupian.cn/cjpic/frombd/1/253/2388638869/4221373505.jpg', 'user', '2024-10-28 11:37:41');
INSERT INTO `db_account` VALUES (9, '李四', '$2a$10$j0dSanN42/Iw/mAS9r706.8pHfq5vu1/gPRN7fAuaFGXRZD6H4BH2', '123111@qq.com', 'https://ts1.cn.mm.bing.net/th/id/R-C.f696c70eea53bd54e91fff5b65d8d06a?rik=2mBjOKN3%2b0pYag&riu=http%3a%2f%2fimg.touxiangwu.com%2f2020%2f3%2f3QRfqa.jpg&ehk=263ZRDleUxN%2fG6wux14UWLQ7Vp5%2fd2KdU5l1bTUhHJc%3d&risl=&pid=ImgRaw&r=0', 'user', '2024-10-28 11:37:56');

-- ----------------------------
-- Table structure for db_message
-- ----------------------------
DROP TABLE IF EXISTS `db_message`;
CREATE TABLE `db_message`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `send_id` int NOT NULL COMMENT '发送者id',
  `to_id` int NOT NULL COMMENT '发送到id,0表示群发',
  `message` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '聊天信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '聊天记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_message
-- ----------------------------
INSERT INTO `db_message` VALUES (33, 9, 8, '哈喽 张三同学\n');
INSERT INTO `db_message` VALUES (34, 8, 9, '你好\n');
INSERT INTO `db_message` VALUES (35, 8, 9, '吃午饭了吗\n');
INSERT INTO `db_message` VALUES (36, 8, 0, '有人在吗\n');
INSERT INTO `db_message` VALUES (37, 9, 0, '有的\n');

SET FOREIGN_KEY_CHECKS = 1;
