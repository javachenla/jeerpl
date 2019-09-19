/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.98
Source Server Version : 50624
Source Host           : 192.168.2.98:3307
Source Database       : jmfinal

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2019-02-28 16:23:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for serv_activelist
-- ----------------------------
DROP TABLE IF EXISTS `serv_activelist`;
CREATE TABLE `serv_activelist` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `title` varchar(64) DEFAULT NULL COMMENT '活动标题',
  `starttime` datetime DEFAULT NULL COMMENT '活动开始',
  `endtime` datetime DEFAULT NULL COMMENT '结束时间',
  `content` longtext COMMENT '活动内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of serv_activelist
-- ----------------------------
INSERT INTO `serv_activelist` VALUES ('1', '2019-02-21 16:05:09', '星期一上午', '2019-02-21 16:05:16', '2019-03-10 16:05:21', '语文、数学、英语、政治');
INSERT INTO `serv_activelist` VALUES ('2', '2019-02-21 16:05:09', '星期一下午', '2019-02-21 16:05:16', '2019-03-10 16:05:21', '美术、体育、音乐、语文');
INSERT INTO `serv_activelist` VALUES ('3', '2019-02-21 16:05:09', '星期二上午', '2019-02-21 16:05:16', '2019-03-10 16:05:21', '政治、历史、地理、文综');
INSERT INTO `serv_activelist` VALUES ('4', '2019-02-21 16:05:09', '星期二下午', '2019-02-21 16:05:16', '2019-03-10 16:05:21', '美术、体育、音乐、语文');
INSERT INTO `serv_activelist` VALUES ('5', '2019-02-21 16:05:09', '星期三上午', '2019-02-21 16:05:16', '2019-03-10 16:05:21', '语文、数学、英语、政治');
INSERT INTO `serv_activelist` VALUES ('6', '2019-02-21 16:05:09', '星期三下午', '2019-02-21 16:05:16', '2019-03-10 16:05:21', '美术、体育、音乐、语文');
INSERT INTO `serv_activelist` VALUES ('7', '2019-02-21 16:05:09', '星期四上午', '2019-02-21 16:05:16', '2019-03-10 16:05:21', '政治、历史、地理、文综');
INSERT INTO `serv_activelist` VALUES ('8', '2019-02-21 16:05:09', '星期四下午', '2019-02-21 16:05:16', '2019-03-10 16:05:21', '美术、体育、音乐、语文');
