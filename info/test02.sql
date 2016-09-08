/*
Navicat MySQL Data Transfer

Source Server         : test02
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : test02

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2016-09-08 12:44:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `_desc` varchar(200) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `createDate` varchar(50) DEFAULT NULL,
  `lastLoginDate` varchar(50) DEFAULT NULL,
  `type` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`id`),
  KEY `user_createDate` (`createDate`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('16', '1', '1', '0', '2016-09-08 10:25:37', '', '0');
INSERT INTO `user` VALUES ('17', '2', '2', '0', '2016-09-08 10:25:43', '', '0');
INSERT INTO `user` VALUES ('18', '3', '3', '0', '2016-09-08 10:25:47', '', '0');
INSERT INTO `user` VALUES ('19', '565555', '45', '0', '2016-09-08 10:56:00', '', '0');
INSERT INTO `user` VALUES ('21', '24', '111111111111111', '1', '2016-09-08 11:53:55', '', '0');
INSERT INTO `user` VALUES ('22', '1233', '22', '1', '2016-09-08 12:29:33', '', '0');
