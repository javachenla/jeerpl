/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50726
Source Host           : 127.0.0.1:3306
Source Database       : jmtest

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-08-19 00:15:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for serv_activelist
-- ----------------------------
DROP TABLE IF EXISTS `serv_activelist`;
CREATE TABLE `serv_activelist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `short_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of serv_activelist
-- ----------------------------
INSERT INTO `serv_activelist` VALUES ('1', '斗指', '斗指东北（寅位）；太阳位于黄经315度，公历2月3-5日交节', '斗指东北');
INSERT INTO `serv_activelist` VALUES ('2', '雨水', '雨水太阳位于黄经330度，2月18-20日交节', '雨水太阳');
INSERT INTO `serv_activelist` VALUES ('3', '春分', '春分太阳位于黄经0度，3月20-22日交节', '春分太阳');
INSERT INTO `serv_activelist` VALUES ('4', '清明', '清明太阳位于黄经15度，4月4-6日交节', '清明太阳');
INSERT INTO `serv_activelist` VALUES ('5', '谷雨', '谷雨太阳位于黄经30度，4月19-21日交节', '谷雨太阳');
INSERT INTO `serv_activelist` VALUES ('6', '斗指', '斗指东南；太阳位于黄经45度，公历5月5-7日交节', '斗指东南');
INSERT INTO `serv_activelist` VALUES ('7', '小满', '小满太阳位于黄经60度，5月20-22日交节', '小满太阳');
INSERT INTO `serv_activelist` VALUES ('8', '芒种', '芒种太阳位于黄经75度，6月5-7日交节', '芒种太阳');
INSERT INTO `serv_activelist` VALUES ('9', '夏至', '夏至太阳位于黄经90度，6月21-22日交节', '夏至太阳');
INSERT INTO `serv_activelist` VALUES ('10', '小暑', '小暑太阳位于黄经105度，7月6-8日交节', '小暑太阳');
INSERT INTO `serv_activelist` VALUES ('11', '大暑', '大暑太阳位于黄经120度，7月22-24日交节', '大暑太阳');
INSERT INTO `serv_activelist` VALUES ('12', '斗指', '斗指西南；太阳位于黄经135度，公历8月7-9日交节', '斗指西南');
INSERT INTO `serv_activelist` VALUES ('13', '处暑', '处暑太阳位于黄经150度，8月22-24日交节', '处暑太阳');
INSERT INTO `serv_activelist` VALUES ('14', '白露', '白露太阳位于黄经165度，9月7-9日交节', '白露太阳');
INSERT INTO `serv_activelist` VALUES ('15', '秋分', '秋分太阳位于黄经180度，9月22-24日交节', '秋分太阳');
INSERT INTO `serv_activelist` VALUES ('16', '寒露', '寒露太阳位于黄经195度，10月8-9日交节', '寒露太阳');
INSERT INTO `serv_activelist` VALUES ('17', '霜降', '霜降太阳位于黄经210度，10月23-24日交节', '霜降太阳');
INSERT INTO `serv_activelist` VALUES ('18', '斗指', '斗指西北；太阳位于黄经225度，公历11月7-8日交节', '斗指西北');
INSERT INTO `serv_activelist` VALUES ('19', '小雪', '小雪太阳位于黄经240度，11月22-23日交节', '小雪太阳');
INSERT INTO `serv_activelist` VALUES ('20', '大雪', '大雪太阳位于黄经255度，12月6-8日交节', '大雪太阳');
INSERT INTO `serv_activelist` VALUES ('21', '冬至', '冬至太阳位于黄经270度，12月21-23日交节', '冬至太阳');
INSERT INTO `serv_activelist` VALUES ('22', '小寒', '小寒太阳位于黄经285度，1月5-7日交节', '小寒太阳');

-- ----------------------------
-- Table structure for serv_user
-- ----------------------------
DROP TABLE IF EXISTS `serv_user`;
CREATE TABLE `serv_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of serv_user
-- ----------------------------
INSERT INTO `serv_user` VALUES ('44', '2222', '1111', null, '昵称为空', '1', '山东', '威海');
INSERT INTO `serv_user` VALUES ('45', '7777', '8888', null, '昵称为空', '1', '山东', '威海');
