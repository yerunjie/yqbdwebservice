-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_content` varchar(255) DEFAULT '',
  `rate` int(11) DEFAULT 0,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info` (
  `company_id` int(11) NOT NULL,
  `company_name` varchar(255) DEFAULT '',
  `company_account` varchar(255) DEFAULT '',
  `password` varchar(255) DEFAULT '',
  `classification` varchar(255) DEFAULT '',
  `summary` varchar(255) DEFAULT '',
  PRIMARY KEY (`company_id`),
  KEY `company_id` (`company_id`,`company_account`,`password`),
  KEY `company_id_2` (`company_id`,`classification`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for company_take
-- ----------------------------
DROP TABLE IF EXISTS `company_take`;
CREATE TABLE `company_take` (
  `task_id` int(11) NOT NULL,
  `company_id` int(11) DEFAULT 0,
  `status` int(11) DEFAULT 0,
  `user_comment_id` int(11) DEFAULT 0,
  `company_comment_id` int(11) DEFAULT 0,
  PRIMARY KEY (`task_id`),
  KEY `company_id` (`company_id`),
  KEY `user_comment_id` (`user_comment_id`),
  KEY `company_comment_id` (`company_comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for experience
-- ----------------------------
DROP TABLE IF EXISTS `experience`;
CREATE TABLE `experience` (
  `experience_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT 0,
  `company_id` int(11) DEFAULT 0,
  `task_id` int(11) DEFAULT 0,
  `title` varchar(255) DEFAULT '',
  `content` text,
  PRIMARY KEY (`experience_id`),
  KEY `user_id` (`user_id`),
  KEY `company_id` (`company_id`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for experience_picture
-- ----------------------------
DROP TABLE IF EXISTS `experience_picture`;
CREATE TABLE `experience_picture` (
  `experience_id` int(11) NOT NULL,
  `picture_address` varchar(255) DEFAULT '',
  PRIMARY KEY (`experience_id`,`picture_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for group_info
-- ----------------------------
DROP TABLE IF EXISTS `group_info`;
CREATE TABLE `group_info` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT 0,
  `group_title` varchar(255) DEFAULT '',
  `group_description` text,
  `current_people_number` int(11) DEFAULT 0,
  `max_people_number` int(11) DEFAULT 0,
  PRIMARY KEY (`group_id`),
  KEY `company_id` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for group_member
-- ----------------------------
DROP TABLE IF EXISTS `group_member`;
CREATE TABLE `group_member` (
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `participate_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT 0,
  PRIMARY KEY (`group_id`,`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT 0,
  `user_id` int(11) DEFAULT 0,
  `classification` varchar(255) DEFAULT '',
  `task_title` varchar(255) DEFAULT '',
  `task_description` text,
  `task_status` int(11) DEFAULT 0,
  `pay` double DEFAULT 0,
  `publish_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deadline` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `start_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `complete_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sign_up_people_number` int(11) DEFAULT 0,
  `current_people_number` int(11) DEFAULT 0,
  `max_people_number` int(11) DEFAULT 0,
  `simple_drawing_address` varchar(255) DEFAULT '',
  `group_id` int(11)  DEFAULT 0,
  `province` varchar(255) DEFAULT '',
  `city` varchar(255) DEFAULT '',
  `district` varchar(255) DEFAULT '',
  `task_address` varchar(255) DEFAULT '',
  PRIMARY KEY (`task_id`),
  KEY `user_id` (`user_id`),
  KEY `company_id` (`company_id`) USING BTREE,
  KEY `province` (`province`,`city`,`district`) USING BTREE,
  KEY `classification` (`classification`) USING BTREE,
  KEY `start_time` (`start_time`,`complete_time`) USING BTREE,
  KEY `task_status` (`task_status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for task_type
-- ----------------------------
DROP TABLE IF EXISTS `task_type`;
CREATE TABLE `task_type` (
  `task_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`task_id`,`type_id`),
  KEY `task_type_ibfk_2` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `type_id` int(255) NOT NULL AUTO_INCREMENT,
  `type_classification` varchar(255) DEFAULT '',
  `type_name` varchar(255) DEFAULT '',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex` varchar(255) DEFAULT '',
  `real_name` varchar(255) DEFAULT '',
  `nick_name` varchar(255) DEFAULT '',
  `head_portrait` varchar(255) DEFAULT '',
  `professional_level` int(11) DEFAULT 0,
  `credit_level` int(11) DEFAULT 0,
  PRIMARY KEY (`user_id`),
  KEY `user_id` (`user_id`,`account_number`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for user_take
-- ----------------------------
DROP TABLE IF EXISTS `user_take`;
CREATE TABLE `user_take` (
  `task_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` int(11) DEFAULT 0,
  `publisher_comment_id` int(11) DEFAULT 0,
  `receiver_comment_id` int(11) DEFAULT 0,
  PRIMARY KEY (`task_id`,`user_id`),
  KEY `user_id` (`user_id`),
  KEY `user_comment_id` (`publisher_comment_id`),
  KEY `company_comment_id` (`receiver_comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


