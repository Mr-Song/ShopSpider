/*
Navicat MySQL Data Transfer

Source Server         : learn
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : shoppingguide

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-01-03 00:27:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_guide_smzdm_editor
-- ----------------------------
DROP TABLE IF EXISTS `t_guide_smzdm_editor`;
CREATE TABLE `t_guide_smzdm_editor` (
  `article_id` int(11) NOT NULL,
  `article_title` varchar(100) DEFAULT NULL,
  `article_price` varchar(100) DEFAULT NULL,
  `article_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `article_timesort` bigint(20) DEFAULT NULL,
  `article_pic` varchar(100) DEFAULT NULL,
  `article_worthy` int(11) DEFAULT NULL,
  `article_unworthy` int(11) DEFAULT NULL,
  `article_mall` varchar(40) DEFAULT NULL,
  `article_comment` int(255) DEFAULT NULL,
  `article_collection` int(255) DEFAULT NULL,
  `article_is_timeout` varchar(10) DEFAULT NULL,
  `article_is_sold_out` varchar(50) DEFAULT NULL,
  `article_favorite` varchar(10) DEFAULT NULL,
  `time_sort` bigint(20) DEFAULT NULL,
  `link` varchar(50) DEFAULT NULL,
  `link_type` varchar(20) DEFAULT NULL,
  `sub_type` varchar(20) DEFAULT NULL,
  `link_val` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
SET FOREIGN_KEY_CHECKS=1;
