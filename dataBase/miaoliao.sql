/*
Navicat MySQL Data Transfer

Source Server         : MySQL80
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : miaoliao

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-10-12 22:06:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for miaoliao_chat_msg
-- ----------------------------
DROP TABLE IF EXISTS `miaoliao_chat_msg`;
CREATE TABLE `miaoliao_chat_msg` (
  `id` varchar(64) NOT NULL,
  `send_user_id` varchar(64) NOT NULL,
  `accept_user_id` varchar(64) NOT NULL,
  `msg` varchar(255) NOT NULL,
  `sign_flig` tinyint(1) NOT NULL,
  `creat_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of miaoliao_chat_msg
-- ----------------------------

-- ----------------------------
-- Table structure for miaoliao_friend_request
-- ----------------------------
DROP TABLE IF EXISTS `miaoliao_friend_request`;
CREATE TABLE `miaoliao_friend_request` (
  `id` varchar(64) NOT NULL,
  `send_user_id` varchar(64) NOT NULL,
  `accept_user_id` varchar(64) NOT NULL,
  `request_date_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of miaoliao_friend_request
-- ----------------------------

-- ----------------------------
-- Table structure for miaoliao_my_friends
-- ----------------------------
DROP TABLE IF EXISTS `miaoliao_my_friends`;
CREATE TABLE `miaoliao_my_friends` (
  `id` varchar(64) NOT NULL,
  `my_user_id` varchar(64) NOT NULL,
  `my_friend_user_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of miaoliao_my_friends
-- ----------------------------

-- ----------------------------
-- Table structure for miaoliao_user
-- ----------------------------
DROP TABLE IF EXISTS `miaoliao_user`;
CREATE TABLE `miaoliao_user` (
  `id` varchar(64) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `face_image` varchar(255) NOT NULL,
  `face_image_big` varchar(255) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `qrcode` varchar(255) NOT NULL,
  `cid` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of miaoliao_user
-- ----------------------------
