/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost
 Source Database       : yqbd

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 10/23/2017 12:05:33 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_content` varchar(255) DEFAULT '',
  `rate` int(11) DEFAULT '0',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `company_info`
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info` (
  `company_id` int(11) NOT NULL,
  `company_name` varchar(255) DEFAULT '',
  `company_account` varchar(255) DEFAULT '',
  `password` varchar(255) DEFAULT '',
  `classification` varchar(255) DEFAULT '',
  `summary` text,
  `head_portrait_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`company_id`),
  KEY `company_id` (`company_id`,`company_account`,`password`),
  KEY `company_id_2` (`company_id`,`classification`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `company_info`
-- ----------------------------
BEGIN;
INSERT INTO `company_info` VALUES ('1', '上海海洋水族馆', '', '', '', '合作起始时间为2010年，社会实践中心负责招募志愿者前往上海水族馆为游客提供引导服务，志愿者招募频率为一个月两次，每次人数为10-12人。', 'images/上海海洋水族馆.jpg'), ('2', '闵行图书馆', '', '', '', '合作起始时间为2017年6月，与华东政法大学共同负责场馆内英语角志愿者项目，每周招募一位志愿者。', 'images/闵行图书馆.jpg'), ('3', '上海图书馆', '', '', '', '合作起始时间为2010年，社会实践中心负责招募志愿者前往上海图书馆负责读者借还书事务和典藏书库寻书工作。志愿者招募频率为一个月两次，每次人数为8-10人。上海图书馆志愿者负责老师会定期向社会实践中心反馈志愿者服务质量。', 'images/上海图书馆.jpg'), ('4', '上海兴家残疾人子女义务辅导学校', '', '', '', '“兴家”校长房金妹是第二届“中华慈善事业突出贡献奖”、首届“中国百名优秀母亲”和“新中国60年上海百位突出贡献女性”获得者，这位动过六次手术的高位截瘫者为“兴家”付出了全部的精力和心血。她是位全职的志愿者，在她的精神感召下，越来越多的志愿者来到了“兴家”。', ''), ('5', '华东师范大学兴家志愿者社团', '', '', '', '', 'images/社团-兴家.jpg'), ('6', '中华艺术宫	', '', '', '', '合作起始时间为2012年，志愿者招募频率为两周一次，每次 10-12个人。', ''), ('7', '“小脑袋”沪语班', '', '', '', '合作起始时间为2010年，社会实践中心已与浦东文化艺术指导中心、黄浦区、长宁区新华街道、普陀区长风街道、五里桥建立长期合作。中心每年寒暑假招录沪语老师和助教共计20人前往各社区授课。寒暑假授课频率约为每月2次。', ''), ('8', '科教进社区', '', '', '', '合作起始时间为2012年，社会实践中心与长宁区新华街道、普陀区长风街道建立了合作关系，每年寒暑假招募约15名老师前往社区授课。', ''), ('9', '华东师范大学经管书院社会实践中心', '', '', '', '经管书院团委社会实践中心成立于2017年6月，是经管书院成立后组建的新部门。中心承袭了原经管学部实践部的主要事务。其工作宗旨是促进广大团员青年投身社会实践，培养团员青年精神，使其在社会实践中不断充实完善自己，提高社会责任感和科学实践能力。中心整体上负责经管书院志愿者项目和寒暑假社会实践项目的统筹协调工作。项目组与经管书院团委达成的合作主要针对其与校外场馆建立长期联系的几个志愿者项目。', 'images/经管书院团委社会实践中心.jpg'), ('10', '普陀区志愿服务指导中心', '', '', '', '负责指导、协调全区的志愿服务活动，依法维护志愿者和志愿服务者组织的合法权益。首先，协会以智力、技能、体力积极开展经常性的助老、助残、助医、助学、助困、邻里互助、敬老爱幼、护绿保洁、交通管理、治安防范、法律咨询、就业指导、科学普及、医疗保健、家电维修等公益活动； 其次，为国际国内交流活动、大型文化娱乐活动等提供服务；第三，加强区域志愿者的招募、注册登记、培训教育及评估表彰等日常组织管理工作；第四，指导、协调、总结、交流部门志愿者活动；最后，建立志愿服务基地，创建志愿服务品牌，为社会道德实践和物质文明、精神文明建设作贡献。', 'images/社团-普陀区志愿.jpg'), ('11', 'AIESEC华东师范大学分会', '', '', '', 'AIESEC是法语“Association Internationale des Etudiants en Sciences Economiques et Commerciales”缩写，意为“国际经济学商学学生联合会”。今天AIESEC的成员不再局限于经济学和商学学生，而容纳更加广泛学科背景的学生。自 1948 年成立以来，AIESEC 一直在全球致力于将有潜力的年轻人培养成具有全球视野的领导者，截至2016年6月已在全球126个国家与地区的2400多所大学设立分会。', 'images/社团-Aiesec white blue.png');
COMMIT;

-- ----------------------------
--  Table structure for `company_take`
-- ----------------------------
DROP TABLE IF EXISTS `company_take`;
CREATE TABLE `company_take` (
  `task_id` int(11) NOT NULL,
  `company_id` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  `user_comment_id` int(11) DEFAULT '0',
  `company_comment_id` int(11) DEFAULT '0',
  PRIMARY KEY (`task_id`),
  KEY `company_id` (`company_id`),
  KEY `user_comment_id` (`user_comment_id`),
  KEY `company_comment_id` (`company_comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `experience`
-- ----------------------------
DROP TABLE IF EXISTS `experience`;
CREATE TABLE `experience` (
  `experience_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT '0',
  `company_id` int(11) DEFAULT '0',
  `task_id` int(11) DEFAULT '0',
  `title` varchar(255) DEFAULT '',
  `content` text,
  PRIMARY KEY (`experience_id`),
  KEY `user_id` (`user_id`),
  KEY `company_id` (`company_id`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `experience_picture`
-- ----------------------------
DROP TABLE IF EXISTS `experience_picture`;
CREATE TABLE `experience_picture` (
  `experience_id` int(11) NOT NULL,
  `picture_address` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`experience_id`,`picture_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `group_info`
-- ----------------------------
DROP TABLE IF EXISTS `group_info`;
CREATE TABLE `group_info` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT '0',
  `group_title` varchar(255) DEFAULT '',
  `group_description` text,
  `current_people_number` int(11) DEFAULT '0',
  `max_people_number` int(11) DEFAULT '0',
  PRIMARY KEY (`group_id`),
  KEY `company_id` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `group_member`
-- ----------------------------
DROP TABLE IF EXISTS `group_member`;
CREATE TABLE `group_member` (
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `participate_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`group_id`,`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT '0',
  `user_id` int(11) DEFAULT '0',
  `classification` varchar(255) DEFAULT '',
  `task_title` varchar(255) DEFAULT '',
  `task_description` text,
  `task_status` int(11) DEFAULT '0',
  `pay` double DEFAULT '0',
  `publish_time` datetime DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `complete_time` datetime DEFAULT NULL,
  `sign_up_people_number` int(11) DEFAULT '0',
  `current_people_number` int(11) DEFAULT '0',
  `max_people_number` int(11) DEFAULT '0',
  `simple_drawing_address` varchar(255) DEFAULT '',
  `group_id` int(11) DEFAULT '0',
  `province` varchar(255) DEFAULT '',
  `city` varchar(255) DEFAULT '',
  `district` varchar(255) DEFAULT '',
  `task_address` varchar(255) DEFAULT '',
  `primary_work` varchar(255) DEFAULT '',
  `other_company` varchar(255) DEFAULT '',
  `primary_contact` varchar(255) DEFAULT '',
  `remark` text,
  PRIMARY KEY (`task_id`),
  KEY `user_id` (`user_id`),
  KEY `company_id` (`company_id`) USING BTREE,
  KEY `province` (`province`,`city`,`district`) USING BTREE,
  KEY `classification` (`classification`) USING BTREE,
  KEY `start_time` (`start_time`,`complete_time`) USING BTREE,
  KEY `task_status` (`task_status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `task`
-- ----------------------------
BEGIN;
INSERT INTO `task` VALUES ('1', '1', '0', '', '上海海洋水族馆志愿者', '活动简介：不知不觉中，十一的脚步悄然而至，大家想不想利用假期做一些有意义的事情呢？实践菌在这里向你发出邀请，跟我们出发，一起加入看海的队伍吧！', '0', '0', '2017-09-28 00:00:00', '2017-10-03 00:00:00', '2017-09-28 08:30:00', '2017-10-06 16:30:00', '0', '0', '10', 'images/上海海洋水族馆.jpg', '0', '上海市', '上海市', '浦东新区', '陆家嘴环路1388号上海海洋水族馆', '协助馆内工作人员引导游客', '华东师范大学经管书院社会实践中心', '15157457293（陈同学）', ''), ('2', '2', '0', '', '闵行图书馆志愿者', '新学期开始，实践菌也为大家带来了新的志愿者活动，读书与志愿者的结合，闵行图书馆绿叶助学志愿者。', '0', '0', '2017-09-03 00:00:00', '2017-09-07 00:00:00', '2017-09-10 13:50:00', '2017-09-10 15:50:00', '0', '0', '1', 'images/闵行图书馆.jpg', '0', '上海市', '上海市', '闵行区', '闵行图书馆（莘庄地铁站附近）', '为英语角撰写话题，做英语角老师的助教', '华东师范大学经管书院社会实践中心', '', '志愿者需要自己在外解决午餐；提供补贴；服务时长满35小时，可以参与评选优秀志愿者'), ('3', '2', '0', '', '闵行图书馆志愿者', '新学期开始，实践菌也为大家带来了新的志愿者活动，读书与志愿者的结合，闵行图书馆绿叶助学志愿者。', '0', '0', '2017-09-03 00:00:00', '2017-09-07 00:00:00', '2017-09-17 13:50:00', '2017-09-17 15:50:00', '0', '0', '1', 'images/闵行图书馆.jpg', '0', '上海市', '上海市', '闵行区', '闵行图书馆（莘庄地铁站附近）', '为英语角撰写话题，做英语角老师的助教', '华东师范大学经管书院社会实践中心', '', '志愿者需要自己在外解决午餐；提供补贴；服务时长满35小时，可以参与评选优秀志愿者'), ('4', '2', '0', '', '闵行图书馆志愿者', '新学期开始，实践菌也为大家带来了新的志愿者活动，读书与志愿者的结合，闵行图书馆绿叶助学志愿者。', '0', '0', '2017-09-03 00:00:00', '2017-09-07 00:00:00', '2017-09-24 13:50:00', '2017-09-24 15:50:00', '0', '0', '1', 'images/闵行图书馆.jpg', '0', '上海市', '上海市', '闵行区', '闵行图书馆（莘庄地铁站附近）', '为英语角撰写话题，做英语角老师的助教', '华东师范大学经管书院社会实践中心', '', '志愿者需要自己在外解决午餐；提供补贴；服务时长满35小时，可以参与评选优秀志愿者'), ('5', '3', '0', '', '上海图书馆志愿者', '：没有任何快艇像一本书，把我们带到遥远的地方。实践菌邀请热爱图书的你，抓住国庆长假的尾巴，一起去书的海洋中畅游。', '0', '0', '2017-09-28 00:00:00', '2017-10-03 00:00:00', '2017-10-07 08:30:00', '2017-10-07 16:30:00', '0', '0', '8', 'images/上海图书馆.jpg', '0', '上海市', '上海市', '徐汇区', '淮海中路1555号上海图书馆 ', '寻找市民所需书籍 ', '华东师范大学经管书院社会实践中心', '15157457293（陈同学）', ''), ('6', '4', '0', '', '残疾人子女义务辅导', '残障家庭志愿者家教帮扶是利用大学生资源，让大学生走进残障困难家庭进行对其子女进行教育帮助，解决残疾人士辅导自己的困难以及体现社会的人文关怀。', '0', '0', '2017-10-01 23:18:53', '2018-01-23 00:00:00', '2017-09-01 00:00:00', '2018-01-23 00:00:00', '0', '0', '0', 'images/活动图片-兴家家教.jpg', '0', '上海市', '上海市', '', '', '家教辅导', '华东师范大学兴家志愿者社团', '17780115916（陈同学）', '一年工作期满可获得志愿证明证书，志愿者以在校大学生优先'), ('7', '12', '0', '', '老年大学义务英语教师服务', '作为英语教师，到社区老年大学辅导老人进行日常口语交流甚至系统英语学习，陪伴老人的同时给予老人语言知识与新潮信息。', '0', '0', '2017-10-01 23:18:53', '2018-01-23 00:00:00', '2017-09-01 00:00:00', '2018-01-23 00:00:00', '0', '0', '0', 'images/活动图片-老年大学.jpg', '0', '上海市', '上海市', '', '', '英语授课', '', '', '一年工作期满可获得志愿证明证书，英语能力强者优先'), ('8', '11', '0', '', '国外青年志愿者当地义务接待', '寒假期间，AIESEC的国外志愿者将到中国进行自闭症儿童关怀志愿项目，届时，需要招募临时的志愿者进行语言翻译，生活协助，教学协助等工作。', '0', '0', '2017-10-01 23:18:53', '2018-01-23 00:00:00', '2018-01-17 00:00:00', '2018-02-15 00:00:00', '0', '0', '0', 'images/活动图片-国际志愿者翻译.jpg', '0', '', '', '', '', '英语翻译；志愿工作协助', '', '', '活动可能会占用春节时间，上海本地志愿者优先。申请必须出示英语能力相关证明作为筛选依据'), ('9', '11', '0', '', '上海10公里马拉松精英赛志愿者', '自去年上海国际10公里精英赛在普陀区落户，上马赛事品牌形成了国内首个全程、半程、十公里三大赛事体系，使上海成为在中心城区每年举办既有“双金”全程赛事、高品质半程赛事，以及10公里精英赛的城市，凭借着普陀特有的生态环境、鲜明的区域特色、精心的赛事组织，十公里精英赛创赛第一年便被中国田径协会评定为铜牌赛事，并受到广大跑友的美誉。2017斐讯上海国际10公里精英赛再度揭幕，展现给跑友的将是一个赛事从有到精的过程，是将打造成与一城双马接轨的精品十公里标杆赛事。', '0', '0', '2017-10-01 23:18:53', '2018-01-23 00:00:00', '2017-10-29 00:00:00', '2017-10-29 23:59:59', '0', '0', '0', 'images/活动图片-10公里.jpg', '0', '', '', '', '', '场地协助；人群引流', '', '', '100元补贴以及志愿者证书。华东师范大学志愿者优先');
COMMIT;

-- ----------------------------
--  Table structure for `task_type`
-- ----------------------------
DROP TABLE IF EXISTS `task_type`;
CREATE TABLE `task_type` (
  `task_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`task_id`,`type_id`),
  KEY `task_type_ibfk_2` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `task_type`
-- ----------------------------
BEGIN;
INSERT INTO `task_type` VALUES ('1', '1'), ('1', '2'), ('1', '3');
COMMIT;

-- ----------------------------
--  Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `type_id` int(255) NOT NULL AUTO_INCREMENT,
  `type_classification` varchar(255) DEFAULT '',
  `type_name` varchar(255) DEFAULT '',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `type`
-- ----------------------------
BEGIN;
INSERT INTO `type` VALUES ('1', '志愿种类', '扶贫济困'), ('2', '志愿种类', '助老助残'), ('3', '志愿种类', '生态建设'), ('4', '志愿种类', '平安巡防'), ('5', '志愿种类', '实践培训'), ('6', '志愿种类', '社区服务'), ('7', '志愿种类', '大型活动'), ('8', '志愿种类', '抢险救灾');
COMMIT;

-- ----------------------------
--  Table structure for `user_collect`
-- ----------------------------
DROP TABLE IF EXISTS `user_collect`;
CREATE TABLE `user_collect` (
  `user_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_collect`
-- ----------------------------
BEGIN;
INSERT INTO `user_collect` VALUES ('1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `user_info`
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
  `professional_level` int(11) DEFAULT '0',
  `credit_level` int(11) DEFAULT '0',
  `telephone` varchar(11) DEFAULT '',
  `school` varchar(255) DEFAULT '',
  `occupation` varchar(255) DEFAULT '',
  `company_name` varchar(255) DEFAULT '',
  PRIMARY KEY (`user_id`),
  KEY `user_id` (`user_id`,`account_number`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_info`
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES ('1', '10152510261', '123456', '男', '叶润杰', 'yrj', '', '1', '1', '17701689978', '华东师范大学', '在校学生', '在校学生'), ('2', '10152510172', '111111', '女', '姜晨怡', 'j', '', '1', '1', '', '', '', ''), ('3', '22222222222', '222222', '女', '2号', 'No2', '', '1', '1', '', '', '', ''), ('4', 'v46brofj7t38', 'h64wyoqz3bub', '', 'hello', '', '', '0', '0', '', '', '', '');
COMMIT;

-- ----------------------------
--  Table structure for `user_take`
-- ----------------------------
DROP TABLE IF EXISTS `user_take`;
CREATE TABLE `user_take` (
  `task_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` int(11) DEFAULT '0',
  `publisher_comment_id` int(11) DEFAULT '0',
  `receiver_comment_id` int(11) DEFAULT '0',
  PRIMARY KEY (`task_id`,`user_id`),
  KEY `user_id` (`user_id`),
  KEY `user_comment_id` (`publisher_comment_id`),
  KEY `company_comment_id` (`receiver_comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
