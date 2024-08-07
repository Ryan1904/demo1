/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 07/08/2024 23:07:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `captcha` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1234' COMMENT '令牌',
  `Login_Fail_Count` int UNSIGNED NULL DEFAULT 0,
  `sex` int NULL DEFAULT 1 COMMENT '1：男 2：女',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '老王', 'wyu', '123456', 'e10adc3949ba59abbe56e057f20f883e', '1234', 0, 1, '12345678910');
INSERT INTO `user` VALUES (2, '老张', '123', '123456', 'e10adc3949ba59abbe56e057f20f883e', '1234', 0, 1, '12345678911');
INSERT INTO `user` VALUES (3, '不知名', 'null', '123456', NULL, '1234', 0, 1, '12345678912');
INSERT INTO `user` VALUES (6, '老王', '123456', '123456', 'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFzbGRqbGsiLCJleHAiOjE3MjI4MjM3MTh9.EEo4NpDR93mXGcNwGM_w7MSgjGaZTJSJuFS1XHs-5K0', '1234', 0, 1, '15421541523');

SET FOREIGN_KEY_CHECKS = 1;
