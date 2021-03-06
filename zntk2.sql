/*
Navicat MySQL Data Transfer

Source Server         : text
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : zntk2

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-02-21 10:26:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` varchar(255) NOT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `carcard` varchar(255) DEFAULT NULL,
  `carname` varchar(255) DEFAULT NULL,
  `carinfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car
-- ----------------------------

-- ----------------------------
-- Table structure for contrast
-- ----------------------------
DROP TABLE IF EXISTS `contrast`;
CREATE TABLE `contrast` (
  `id` varchar(255) NOT NULL,
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contrast
-- ----------------------------
INSERT INTO `contrast` VALUES ('1e95f2208c7f4dd9b584889bba7e3164', '入口');
INSERT INTO `contrast` VALUES ('351cc2cdda6547528e20c6444e4a3bbd', '存车中');
INSERT INTO `contrast` VALUES ('729352f0e3b74fee91bf15baa7187e58', '出口');
INSERT INTO `contrast` VALUES ('7bd42e7aad7645e4ad586349691a87e6', '车库-占用');
INSERT INTO `contrast` VALUES ('8184c5aec4b043d4b5b2b4ee8d0acd6e', '可通过');
INSERT INTO `contrast` VALUES ('85f8320ee6004d5eb7f21a470fadb366', '取车中');
INSERT INTO `contrast` VALUES ('920005b52e54468ca653be0b593e6025', '通道-可通过');
INSERT INTO `contrast` VALUES ('9774f488d8054ebab4c9e843ff7e86a4', '通道-占用');
INSERT INTO `contrast` VALUES ('aa8d80e74dd84a418fcf73f2c5b493e0', '不可通过');
INSERT INTO `contrast` VALUES ('aaaeeb3acc5e44899a5d1de5ca5ab11a', '车库-空置');
INSERT INTO `contrast` VALUES ('b8dd53a77a1e4c809550dc1dc750f6ce', '停车中');
INSERT INTO `contrast` VALUES ('d9a4f33ad6fc483fada40fe0e7f81618', '空间');

-- ----------------------------
-- Table structure for emailactive
-- ----------------------------
DROP TABLE IF EXISTS `emailactive`;
CREATE TABLE `emailactive` (
  `id` varchar(255) NOT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emailactive
-- ----------------------------

-- ----------------------------
-- Table structure for parking
-- ----------------------------
DROP TABLE IF EXISTS `parking`;
CREATE TABLE `parking` (
  `id` varchar(255) NOT NULL,
  `carid` varchar(255) DEFAULT NULL,
  `nowspaceid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `feturespaceid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `intime` datetime(6) DEFAULT NULL,
  `outtime` datetime(6) DEFAULT NULL,
  `nature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性质：存车中，停车中，取车中',
  `way` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parking
-- ----------------------------

-- ----------------------------
-- Table structure for parkingsave
-- ----------------------------
DROP TABLE IF EXISTS `parkingsave`;
CREATE TABLE `parkingsave` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `carid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '汽车id',
  `way` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '经过的路径',
  `intime` datetime(6) DEFAULT NULL COMMENT '存车时间',
  `saveinplacetime` datetime(6) DEFAULT NULL COMMENT '存入时间',
  `outtime` datetime(6) DEFAULT NULL COMMENT '取车时间',
  `outinplacetime` datetime(6) DEFAULT NULL COMMENT '取走时间',
  `savespaceid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '存车地点id',
  `nature` varchar(255) DEFAULT '' COMMENT '是否预约停车',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parkingsave
-- ----------------------------

-- ----------------------------
-- Table structure for phoneactive
-- ----------------------------
DROP TABLE IF EXISTS `phoneactive`;
CREATE TABLE `phoneactive` (
  `id` varchar(255) NOT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phoneactive
-- ----------------------------

-- ----------------------------
-- Table structure for space
-- ----------------------------
DROP TABLE IF EXISTS `space`;
CREATE TABLE `space` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `x` int(255) DEFAULT NULL,
  `y` int(255) DEFAULT NULL,
  `z` int(255) DEFAULT NULL,
  `nature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性质：空车位？',
  `carid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of space
-- ----------------------------
INSERT INTO `space` VALUES ('0043af58917c4a4187e06eca6ddf98e7', '14', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('008bf5fc34504f8fa174bdb404d22271', '6', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0108909645c8422583342d14cf0e8358', '19', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0110a581defb476cabe506f23af2a654', '9', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0170dd6dd78c450ba2161038b8365262', '19', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('018ef752e5c143d983764e5efbef6e89', '14', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('01f87bdc930b4806ba2a64d31c755a95', '2', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('02653db9a43e439ab1702f3ce57985e8', '10', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('02dfb5b969554616a44f53a988f5b3a4', '8', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('03050bd16e1e4feeb420a98a4370bdb0', '5', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0317ac582a3b46e8a0b8d4802cbf2f12', '8', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0363c42fb9c84f3f8545213e6004dac9', '12', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('037f217e5d12491ebe9e085499971cda', '3', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('03ab48cb8f4c4724bf3d7294cef2820f', '7', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('041d04d7fc084aeb9edb0179108e10be', '5', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('048d157cb0db49bc95010fe55639155e', '11', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('04c56b031552421e9c94c105a8d202a9', '14', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0565bcb3778c4919a73f97207ea11ccb', '10', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0589ecc3219249dca6ae9b22e7e4115a', '5', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('05950174c16547eb8e32af20f10d50fd', '13', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('05acea1a20994570b2a1400883ebf63c', '21', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('05d1a6d176184882998c9c4ba45ad892', '8', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('06383d97f4a7486298839fdd8f2ca018', '17', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0656c701cece4ef7ba77c6a27861712c', '1', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0679fd9a82d24511a295605180be32b3', '4', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('06adf80fe320401882ed7b16f706df19', '21', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0708a3989b374f68a26ea9b33db9634c', '14', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('079e345e9fe94e78ab01eb72c8f7994f', '8', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('087ecfe4a2f84104b9d612e9634616e2', '11', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('08809a5960d24366954dc3068bb9b83e', '7', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0887e8d1e47847909f0385f30620b6b9', '4', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('08ba06c2fbe4448982a24f4fe5de7951', '22', '1', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('08be31ae30ae445b8750fa980c9546af', '15', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('08d77c1c8edf470d90edd40cab8c072a', '4', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('095f4f109a9549ac9d17dd1eebb4da2a', '6', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0a2d9bc2822841c2b0224e60b31fa6dd', '6', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0a6d290a5117409b856819084c1ff12b', '14', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0a84365c493c407caca705d38c8f822d', '12', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0ab03008d2e3498481d2f0ec73b17a23', '19', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0af9aa9d900a4b0e959b1aef8543d172', '4', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0b107ce2b8ff42cbb20bd8c341353073', '19', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0b3d0d9b557c4097b50ea59baeedfd96', '8', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0b777db385ef475a925e23adef7472b1', '10', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0b7db2c7e15745a9bf631e5b4b19fdf0', '3', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0b7e33d380df4d6b8098417f42ce3c01', '22', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0b9ddaa119144f81a312460d3ef8a771', '12', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0bfbaa86a55a41bab3db7602ccf6bcaf', '14', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0c18325cd6a6427d801fab0521bce0f2', '5', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0c77d4343eee4eb281f74584f5bb4d9a', '20', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0c8ca3bfbc6546499ffe0f0613f96055', '10', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0c9b3501db024c6985f8462d93d39931', '22', '1', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0cc51c4446c2497d9a3e9b4d5890ecbd', '16', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0cc7a62ef36542d2aba43c0e03e89868', '19', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0ccbd2b99ec34f2b95c28486b74833f7', '11', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0cce7c58f909488daa4b1f952da9646e', '19', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0cd0229a44824b4b8584dc67299e6027', '3', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0cdd9525caa046708b165256119398d5', '11', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0cea35fecace405da7b733a9cabcfeab', '7', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0d59c74cd49441d0ba95b14380cf46a0', '16', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0d90330c4ecf404c86bee5c683ce8d89', '17', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0d95b596a12d444a87e92f076ce2ba25', '19', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0de49ada31b24be2922cdc885dfd6d9a', '1', '1', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0e6c50f261e34f32b9baf3601fec7658', '8', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0e7eaf349ba94727ae6cff5943e25fc2', '18', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('0ea4516c55ab49e391c2acee86bd9b28', '5', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0f2fc696b61d48759e31916bf5d4d707', '11', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('0f98b3464d6a4c35b0398b8b5b6e2a0f', '12', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('101c3e8ecb4a447ca8ab5456b74f72da', '11', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1076cd1ab11842389e47c7b755163e47', '16', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1090e10d76794f3fb42070c24cf34646', '19', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('10e66370bfc4468886ec4c0b8dd3756a', '6', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('111cbfed1d2747678fe49b0fed485070', '21', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('11d421cfc8e24640a20e8c5edb2c19c8', '7', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('122fec0c27ab45b8921b4169a6a783d5', '22', '6', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('129a7ff019af4786986d54b4fcdb8c85', '10', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('136cd62ce12b43d4b1f8ce0359dc1e6d', '22', '6', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('140c32df34b0441ebb89a6825d831144', '9', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('1416d67ae410406c9e5f8e2cc995c281', '1', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('14cc2997959c40f9b4159b209cbcf95d', '12', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('152e5d9db96646d19a436c00e3ae4ca6', '19', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1534856f595e4c78884ee348c59f624d', '3', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('15cbbfb3b52949a28911ab60105598c5', '9', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('15fe0f3c815d45258ec268fbd93875df', '5', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('16b7d040b5f74e61987d1e16e3126904', '15', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('16c8bff231cb45efbab8fba2ac44b609', '7', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('174975c2d08b42779ed767df81b28962', '20', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('17ff45daf92d4747a82c1fa4758b926c', '19', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('181bdf0bf18042c8bd05688b4b41f389', '10', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('183b5e4bc6bc4bf080491fff5d7e54e1', '6', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1850078dcf654c228cb006fc9161fc9f', '5', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('18a80def534d4edfba04b273224bd216', '8', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('18dc4f263af34db3b42ec8c3d403d804', '9', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('18ef75a622884476b01ca66d4d9c77c0', '11', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('193579ac2f1649b08458102782d8481e', '21', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('19eebeb9d9814920aa1ab82ead0722b0', '3', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1a1bdf65cf16487994c561d0f6cf8f00', '20', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('1a360ed3c3ce448cb4b43c78cca37d96', '21', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1a87e489614b4a93a333c97cc65c56a2', '2', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('1a9700d3bff341de97fd6ff8f3b727f0', '8', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1ace5e9c66a84876a252071616078140', '19', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1ae14012bcc84239b7c90214a87796b7', '4', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('1b0d200606b54f56bf5d99474e14f02f', '4', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1b41ee05f30a475496cb2e5167636c18', '7', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1b8077dc798d459e9c4b20f9407c8999', '13', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1bdfba8593014841a46ee30a2542fa37', '17', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('1c1ac4555a7043ecb46cee190e047fac', '9', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('1cb246d5d5bf4ebcb4961a40ce84976d', '6', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1cb95c6c10ed40aa898cb0f84a216900', '2', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('1d0e8cf0c3414e00a19266e9336bdfa1', '10', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1d57d1bd00ab45ee81c0c6e3ecdceda6', '21', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1d8f5ac292174534ae8ab1387b214759', '9', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1da3530be6b64207ad6596e6cac8174f', '6', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1dadac354c014f55a3ba1814c3612f00', '18', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1e4294523e374e2daf419ff61589441d', '12', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1e47988d808a473c9323e87f014990aa', '11', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('1e772aabb93f4e23a2677646f6605aec', '9', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1e7ceab0a8444806a86120c067e54dde', '16', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1e8957eee7b04eef85667c42a9410a00', '1', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('1ea7c38831ed4db49d03edb755c6b508', '11', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1ef281828d3b41c8b92ccb8d704101ff', '16', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('1f57dacdd05a472f834e2887e3600461', '20', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1f6e9e403c75404a9af8f79c85b57bbe', '10', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1f7d9ba3a1da41b2aa7196f5154e53eb', '2', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('1fb96b8d99114051ab7e52c6147cd08e', '6', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1fd0ec85f1a246e3a4a8a39fb19b404c', '9', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('1fdcfc4c57b940eda63bda2865b8178b', '18', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('207838b0fe1e468885e7a8895cb3d224', '8', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('209a7ad39f8d4bbbb7f0ae0b46320c32', '1', '4', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('209fe85157114417a1f9e32209b394fe', '13', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('219f504b44b14cc19df7d87e3b807d70', '17', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('21cc4e3c92cc49b2b8a082d8869cb6a1', '1', '9', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('22432bec84034d51a93f33354af2567d', '18', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('227fc6d527404beaae7a2715bab08d2b', '21', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('228504da390a438590f6d9d7aa5e6e0f', '15', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('229b4b081ef542118b7abc7cd6ab7849', '8', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('229f5ed8ede144ffb05f7df1a8dd9629', '5', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('22c7505c14e94c4d82841dbe8eade246', '22', '9', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('22fb6381aa2c4660b0ac04b51524e288', '6', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2330947bfcf24c8e9cf4a3ea120c235d', '9', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('244118e5110743d79e801d61aa594f92', '1', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('260f296618cf4e848b5acd94d0594e02', '2', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('266ca722a2294b4b9de4fb206d59163b', '2', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('267d196a50e04df5a9cda4c29345b417', '2', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('268e603ee2a842eaac2d5acde3346904', '10', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('27077c73613644cdb8638208eff9430f', '10', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2781b7686bc94d9a996e572fbd09ac73', '12', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('27aff933cc6344d79cba86b5e6c0715f', '19', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('27c0777e18a543888e391d66b421e635', '15', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('27e1546b84c544819bfb0d65b50cc99c', '22', '4', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('2885b2d9a13f419bb1331d44b9c33b57', '5', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('28fbdc41a6e94394a64672b17a7d6a3a', '4', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2901b4e729e549228af85bc97ff6e7ad', '18', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('29083899fbd94a7a95574c3e8e59aa7f', '8', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('291264e64f974051ad3bff0163fa62cc', '2', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('29746984d03047228a978e2996515527', '3', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2a450536a73645b7af3c4ec5cba17a97', '10', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2b496ec5f0e94c60947f764c1c57241e', '3', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2b52d2a2d5284e528b67580023275190', '17', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('2bb8b72ca92744f7ae1dea1f9d24dca4', '4', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('2c0f99fc1f4a476889b2193c83e73d03', '5', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2c6ac96ce912491bb2a2e80d90519333', '20', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2cd754ce9ff74222a1e87408a6a206f7', '2', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2ce964661d5247e38a0495f78734c9be', '15', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('2cf312ccfe044f7998821cd61589cc9b', '16', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2d3ff674273b4ccf9f5b3bc1a3fab531', '10', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2d9bb8f62bbb4985b56d220259ca578b', '5', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2dd224a3f0da41558defab56ccd11cec', '2', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2ef6207d6ad744d1afbdc110fab03845', '3', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2f38f36f6d774f7db26f346f4074c1e5', '1', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('2f667af05bee4d218fe7c84321ecdeb4', '7', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('2f6c680b0d664d7b9ad0ca0dae83829e', '16', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('2fa396316c074396a0cce79bfe72e2e6', '7', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('2fca4bdb40014f1696f20d309e8c9831', '1', '7', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('3067a752eb084859a4a1e653256b8ad2', '13', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('3068c63bc00a40ccbc4f4543f736a340', '19', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('30a5c16014e449c89da1be087a4841a2', '21', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('30db03ec47ad42088d0738b07f34c0cc', '16', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('30f60509525d4a02b4d665521fe45b08', '15', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('312494a6490a442dafe9f4eee2310473', '7', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('31807a722dcb49bd91d7f99e93d58399', '1', '6', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('31a051a97a25499fa9e72a2331554e05', '9', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('328dcc92bfb4477386464ef85f9d4de4', '12', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('329107d4222e43d392078274608e5a40', '3', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('32a937aa5892469aa22152a2d45cd6ae', '1', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('32aa8887c0944d4287796b4ad60b2d6d', '8', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('33cc66be3d824bbe83ff7225375c3edd', '3', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('33d94352e6fe4dd9857b39175b7c52d5', '5', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('33de360759dc4436be04a383e1f436fa', '12', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('34fb0da8b26e4ac19b81fcea69b60d7a', '10', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('35640c12e1a343e5b16e58e2dbc269ee', '20', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('358bb26bdd424f04a2d5592df3ad0bcf', '13', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('359a5dd4422143a0a939df2dc8940c24', '18', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('35ac838e26da4a1d889620add0b49393', '8', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('35e6110bcf2a4c07a3697dab4007bdad', '1', '3', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('35f9daedc89d4bae93e16de571f1024d', '19', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('35fbcf32022b4ecc96bbf302b8428194', '2', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('36416fbae34d4e6890fddb7783fd6d12', '13', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('365e4b3d68cc4623893b98838e0603f3', '20', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('365fa38434c744a682778857563e7c0b', '19', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('366f438662a84fc8b77d66454653ffcd', '9', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('36a910d803884894aa363790f6696961', '20', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('36be6725b46f4923b237e44b95c60cdf', '6', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('37a96e98700e48a6a209f6cb09e06589', '8', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('37c2a03fead849d7aa83469cab9d4460', '16', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('38bd0f4d65294c9d8d638cd648e54079', '1', '1', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('38e6efd8109d4d6bb18e9d966f9ea1ca', '15', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('38ff83c00d334b7db0517945df9c03e5', '13', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('390ec94413c4439e9edb0cc602b16b26', '13', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('394432a2fabf42dfbd351567dc19bf73', '8', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('394a4abcee3c488093c4714419711385', '7', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('39782e5f34a34364b3924283a4cf086f', '1', '1', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('39807db4a68d4306ac35ba1a381881b1', '4', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('39ed4d374287432e9f576d5b389260a4', '15', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('3a787c8104624726bdc8860062cbcc34', '6', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('3a90909b3647463abb5a7736db60d273', '13', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('3aed1e63f4ef4024ab4e03fce3315b2d', '13', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('3aed82641a764dc6953f976d7cb4e894', '13', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('3b26ac3596204cb7b051c7ff776cb320', '14', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('3b315254b8ad4c10ac559f4a3cfccc73', '17', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('3c19644307c243f6994f31d834b38f7b', '9', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('3c20f87ff22641e3a9c52b7ffaae6f63', '7', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('3c8d06757f194715aa1c986cc811e8be', '21', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('3c90f998db974801ba1c9c77d8d17cfc', '18', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('3ceb9e2af28d40a281327e76917cbac2', '10', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('3d2c77318259471cad8385747d81b1a4', '13', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('3da38a48891a41a0807dd3dc29e71007', '20', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('3e28197551114235ad9612ec9a95ea1f', '6', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('3e33222427854b55a3792f72e2d1e074', '21', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('3eb8727145774bc38c8007cddc9aac8a', '16', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('3eed86da0b534bdfb437f428446772ee', '7', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('3ef985adb883477abeacd8dab57e5e37', '13', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('3f1678483da44f71a565e2aa49cecd77', '1', '7', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('3f1eb33a12a9488bbf71bad9fcad7f7e', '18', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('3f60e131322f44e5932721d2c7a70318', '1', '3', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('3f98a223d5a643dcb0822e63883b8c1a', '22', '3', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('3fcc7596c913481a84d2d466b06ad80a', '20', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('401cd3574e6a4e99becc59a6f15e984f', '3', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('402e37b7a34f44b8afc8c153b5d75979', '14', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('4031102c9be843fcbe154f3a94ac5e08', '16', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('4034d5bde6b34ba8bf39df6ea9ffee3e', '13', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('40c8204464784b619f75a5940c769624', '5', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('411b58a2bdb94f3c89af83ffd5d9611d', '17', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('412b92b72cf244269cb665426c123434', '16', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('4158c50296da4349b76367a3c1882073', '18', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('419707f6fa67432d8656b130fb85a836', '8', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('419c5d313a734f0893ef25ec678430fa', '5', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('41bced0ec4a242709834d88c7b6c1fca', '22', '1', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('41bd5edeb9f14703ad1cc5ed4da51634', '20', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('41f49c2c3dfd49f390c0b25e8343a523', '1', '7', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('422c239524b54af68a905541429eebbb', '21', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('424b46333f4649f0a15b5ef71c33db7c', '20', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('42aa5fe253cd4c8b8aab36212aa89216', '10', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('42c79304744f42e3b6916dde0c358d84', '4', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('42e4d129adc84bc48403c7af53f841a5', '16', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('42efaad49a65439997cdf43165100ce8', '4', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('431815cbe690471db6e71a986aed866b', '22', '4', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('437e96661bfa407a81f4c43d1d4e0221', '12', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('43a18da2fbf542d594c066bd9dba2830', '22', '4', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('43ac72383c5249d4a6c37f82546b84d1', '14', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('43d0da300c074b7787e7256ee80ebd78', '22', '9', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('43d50e1d72cb4172b8ec66c1bd819239', '6', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('43d86d1cd3944792aa5f9821e9172614', '6', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('43e76add34304984ba90a1744ca33049', '7', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('43e7b9d9a4754bbc8c9c6a20292d3e8e', '1', '6', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('44370dd6204641989863a9e5e221e537', '20', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('44a2c54c3fe444d19c526fcec131aaff', '12', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('44daacfa6bc64f8fbe0c233230e0ee69', '15', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('451a811ebd5b4591935dc4d9581d4a3f', '21', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('457374216f6f41178d5780a1b35fdc6c', '18', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('45ceae3a954240c89c481733df28b325', '1', '4', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('45d875751e27417fb6afe4a0e3e0ef21', '12', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('464dcf23f6f548b1a6e87978ff38fbfc', '10', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('46638c8277ce43d28530d01e3b75e23f', '5', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('47b6d991ffc141339773ef2890d4bd97', '12', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('47d676cf51974fd080422289fa9d95dc', '1', '7', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('486b994a86a14b72b68bdb1076eb6f07', '6', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('488ff592a1074278847e308abd2376ba', '5', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('48960a41f6284a4dbebeb57969328da0', '7', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('48aa8a6c855e4612a7b47c86f614cbc7', '22', '7', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('48ba57207b1a4918a5aace0fcc19647c', '21', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('48dcfa13732c4a9ba0ef5e8119ce618d', '7', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('492bed9e29614d4f8b29df1f91bd2b6c', '13', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('499ad4c471f042699e6047b6cf8ff083', '5', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('49c0055a69a34be980f79d3089f21271', '20', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('4a10f7e0a263461387d29ffd513872f4', '11', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('4ab8d80238ca4d60b57fe020ef7bcf8d', '22', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('4ac435c6d07341d190119c3949637da8', '3', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('4b8c9883f806443ea3ba5d888c58a747', '4', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('4c475b332bba432484548bb82bb6e7c5', '13', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('4c4d4ae70033481084e763e1e9cea705', '7', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('4c4f0a7efbe44b588f8b31d4fd820b59', '7', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('4d13687a4cd044dc8e40fe563e17defc', '17', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('4ef598558c13438fa0f0cde9da7caea2', '18', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('4f57c6d3782e49429428dec7b02a5230', '22', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('4f67c6f98c7e44e988b23e3dd9cb88b5', '15', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('505bc4cc89b1496f8b56258406356fee', '9', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('505fed29f37848569ecefdedd62f63e2', '17', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('5077e9a9b14f4b2b9aa49690c7b48720', '14', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('507de7be9a814282ad29bc0137407743', '6', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('50c2e2488d334a298347fbfc14ada3c3', '22', '3', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('50d39923aec846ef9e3ffe3129aba94e', '5', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5100602209cc4df6b6464b8875934065', '3', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('511cc9f172e9433b8cd937933324b60f', '6', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('51477cfb4151494a8e35b8e1671e1592', '7', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('523c30d7815f4b57a524c27df6722721', '13', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('52954412db44433c958f7e22ab905cc8', '6', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5428438ece5a4715a9c6a722c395fde0', '2', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('544196b6e09c4c6b9c3d3000a3dc6ce4', '8', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('54ec4bb42af34a9bb685b75f8f289022', '3', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('552e45b694574883842ff1aacaee3322', '17', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5575af9913e247fdbd3cf58b70bfcc71', '9', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('557e422c97aa47f98e8b90aaeabd2ea7', '12', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('55b9160981c14941a16624e37e0ef72f', '21', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5712341920494c218aab2b7b97ff35f7', '8', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5735b2ec93544ba3879032dfacacdd92', '9', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('57a61f2b7f7c4313847dd2da51a92f9e', '14', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('585e1cbe5db641a39680c77b70c4b495', '17', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('587a9406f2b6468b957f85c9d44bc6d1', '12', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('588b4b7289b0494286cad8c7735b60b9', '9', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('59501852e15d47e8beaeabb55e33561b', '17', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('59e5ae65727c48c280bf8909e2128a2f', '7', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5a186e6c8718420aa8fd9caa2bd3770c', '4', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('5a8c5954fae54f6c92f9fff640d803ef', '6', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5a9116e4f8e44d03b5d1fa47dd5ebead', '5', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5acf6bba51a4410eac63b0f986799464', '19', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('5b80708b6881486baed570caa1d7e7d6', '17', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('5be2acabcd154ce19814b3a6bfca6ece', '3', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('5c42164c5f294924bbd5ce6807153c1b', '22', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('5c44ad76cf8e4e6eaf330e555333c98e', '14', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5c67ec89e9494947bccff0f91141e8b2', '20', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('5c6a5b6fa3bd406198ceb5b207ffa4fe', '20', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('5c6ba2f7af714150be56001948deff4b', '2', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('5c991f4a545846ed910200a0c5f7aef6', '21', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5ce03f47a368435b8cd7eafebf633ee9', '8', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5e1105c696c24a6fb92197093d1303b6', '2', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('5e19a7b1555343759e29bacc08fb7883', '8', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('5e5a18196d7941d2aa3189bd6e28f710', '4', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5e99272dff8848c39acc3f80fd29018c', '20', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5ee9fb6b69214d16b20fc7407d715a6f', '10', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5f0f0754c4124bb994754dca5970a377', '21', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5f69ed9c688841d498d2a541f117aa75', '15', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5fa733a165a44c4da2d45feb16b493e5', '20', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('5faf2d4db2d844a29070f3c5c2d25bb9', '6', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('5ff402a2fdee402482ee0ec780b45dcc', '7', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6004550dab934636b4b49c0919ff6fa4', '13', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('60754e52350c490d9e1273edabc05bbe', '17', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('60c19f6fdc2148739258e41b70bea153', '21', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('60c4ea7684544766bcb7dfd77dcc4e93', '3', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('60e3aa330ed64067943dc45541920773', '17', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('613bf666bfd34a3b8407386e9b067dd7', '9', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('614db51a2f9742359e1d556de7c77c84', '6', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('61ae7f33ae8a4473b4e289179d4d4968', '10', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('61c931c5ce8640278bfdb774f86df24d', '10', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('61d69928d33748a0a09779b9fc2a5b0d', '8', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6233a7dc5c1049a288698bf374c45126', '7', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6322e8077a9a485eaacd80741eec1b42', '4', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6339f3785b1d4d2dbdf5b2a671dbb429', '19', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6366ee6c0d2e428d9781e572e0e1f1d7', '8', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('63e7ea7aeeab4a03a64be5b7b68e13e2', '13', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6417d763a1f742a9bd1a3c56aa888f54', '3', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6432257af9da4169b194bda50f46794b', '5', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('648031687b3c4840be39b3e5efa874b4', '5', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6484d2768c8f46b9801ae5d00e90aa2b', '16', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6521073928764e488998661e03bce42f', '1', '1', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6539515f900d443487c3afdd7d409e95', '20', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('654ebf174555490fb983bb8140a89235', '18', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6570434944c44a728c2c09059cbdb664', '8', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('65ad79fa9c81416c8c7a915df6b69a79', '22', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('65aec8b6bcb844b6b71c618b9af4bd65', '3', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('65f19d6c08c94552a9ba730e14bb6981', '10', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6603853479af4614991634b31ea4b639', '16', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('668dd9d2ed154aa1ba9590177c7a404e', '7', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6698e6fbc52940aa8203e2cf30f3d40f', '15', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('671cc5686566485384e8c628d8a6e6f8', '15', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('67989de42f4144dbbb456702a76d2128', '14', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('67b80127a1784e0f8baccc59d31ab46f', '13', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('67d9dfc1060747c385f20ffe1771676b', '9', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6842ff5024de42f18eceaa3c7c6a43eb', '13', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('68a5fe3543a94cd4b76e99b336149857', '18', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('68b88860fae34cde89e5c60d98a35ba5', '3', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('68dedf6f49c3451ba88c12be0516e772', '20', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('69433e243c5f4bf5a878b15ffb50bace', '20', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('69743a1a5d3e402c81a4ba8066204eda', '22', '7', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6981406abc8c4b0898e0d8e353aae687', '13', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('69edb50c08a54a699e433bee6dc6f9a9', '18', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6a24bc4fea6145c397fea1e941cf9b06', '5', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6a3aaf06ecfc43c2b66f62b8d1abafd4', '13', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6a49830bfe8b40e18a5c891ce51b8021', '22', '7', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6a8a9e2e26f541eba63b9e062523875c', '21', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6a8fed6c639f4d1d9a833fa4f58325bf', '22', '4', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6aa99e8361d84cb39a71006efe08d019', '20', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6adbc5ce2c0044b58da21792eb798fb2', '21', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6add00da23334e8ab6f391c22a03d454', '14', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6ae674c326fb47baa43c4b264ba2ce7f', '19', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6b02902f09d3450aa1048c110fedd325', '20', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6b15c2fe36d841a8a94a7cd232bcb07e', '6', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6b4619dc62e94252beef0dcd6fe7df03', '18', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6b6cf3d70cc140e8bd5044a203601cde', '13', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6b7391217aaa4ad794f68ee1ad7ebcd2', '5', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6b77b8c7dab048e892cf1d151882250e', '12', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6beff875f6a04f94b279ec2ee5bce86d', '16', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6c58c516e1a04fa784271f43b64f5875', '1', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6d0e2937d2f4403cb0e6697e95659e86', '9', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6d2295f266214fbea35f688a0a2f6681', '16', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6db7929ff6e740e2aba30e3b84c40ccc', '7', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6dc769c871a84fc58fd1415f51583257', '15', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6ddec3f0ece449c7abc01d6a2cab097b', '22', '7', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6e52f29f48d347659ddd4f7792802dc2', '21', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6ea263b050ed4c16863f60a03ae47b89', '20', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6edcd16a98f247c8a144e2a3ecb4a8c8', '11', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6eff804931a244b99f800d1e484a3b45', '9', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6f477c6ae96e40fa8a40540fa645787e', '14', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6f882edf34814e639c2525d581ac5204', '21', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6febd32eaabf4840bd549b37e0d487d2', '17', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('6fed9c72fded46ef91bd09133a3eabe8', '2', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('6ff0a413d28f471896c2f6e42df42b96', '1', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('707498fb57fd4c7e86a49af06f14f4ec', '10', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('707fc25f73cf4efba3c2a32e21044d9f', '18', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('70f45de54c70412d8930f16fb0c8c243', '9', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('7102b10879c94342817f65353f5f10c3', '7', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('712461d4b0924483a370799f26bfc128', '4', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7134381ae3ae44d08c2f2d82a5c25a19', '3', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('71b4ba1a770c48c5afe854c786b02f6c', '12', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('71b6f837f3164c19b44fd4ddcdd323b1', '12', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('7266527525be44c9a491e709bc8b1331', '6', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7278342e5a8d48309bcb57bf093c210c', '10', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('72a89b5a97d345ee8ed3b371eb94d407', '16', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('72aa6cfed68a4f8ab7633ffa1e7d7292', '4', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('731bc34be8a04113aa0721d7d13ea4c1', '11', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7399cc7c42eb4910b8bf3598a46cfa80', '17', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('73db83049804422aaee6a75e6f6ad381', '15', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('73e29386dc8f481b96e8f80b8be3047a', '15', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('7435e0fffd29458783442e21277239a4', '9', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('74c8a46d752a4d6897401c29cea510cb', '5', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('75259eaa6d324172b2d2a3bd00e8e9fa', '21', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('75441c4411154c6d82fac4288662f85d', '2', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('75a1d40c878547f2aeaf31d6271c9b5a', '2', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('75db70de83294f6eb11c743d87ae2d06', '11', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('75e548076b2b49fabedd2552068c9c7a', '2', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('75ea2f4f14c94ebc8e573ed0237d7159', '7', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('75ff60e5bb024745ae7a81b6feb0a849', '15', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('765e0931b5634d7f863451da03e46e0b', '16', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('76712d47d5df47bb874cd210463f95df', '18', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7690e86f689c42b1960ff288e01985b1', '10', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('76cdd9c93c5a412c8859073e4b1e4866', '6', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('77ae1f50e8ef46f9a6e872ab2d213c78', '2', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('77b45568ffbd4d42a3967d378f62481b', '21', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('77c529e8b03343328aa834879d9ba422', '11', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('77ecd3997f5a4f3f8585112589c4e73a', '20', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('77edc74f11c34ef899b1b3fad6b83aa1', '15', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('78441144d6fa45e9b0f4a7546e389b3c', '1', '7', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('786496b646ad463dae7da0518890ebf6', '6', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('78ab7f78b0114a14932091e2cb20dba5', '17', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('78e924e4c4434377a2bf1b5d83743508', '6', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('79741f303a524376a991502bbab5c7a3', '14', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('79ccfd7a1f6641babd83798d74cb8383', '20', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('7a104bbe0aaa47b08eaa8c506237fc59', '15', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7a3f3d88d4124685932a7fd651634ea2', '5', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('7a46e4a2962143f49a4f38a185b612eb', '1', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('7b4117c94e014fd6a833a5ae08b7d944', '11', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7b99686c0eb7491aa201b6acc8bb1eff', '17', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7bfb00185f5a4fb29b73827fc79d1a8f', '1', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('7c013e7d4f4f4e4889e02e70a09e181d', '16', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7c7f38a389ec4998ae45e48da1b2b95d', '22', '4', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('7c88168e2c584336a08c7bca50878d16', '1', '1', '1', '1e95f2208c7f4dd9b584889bba7e3164', null);
INSERT INTO `space` VALUES ('7cb173d9224c4775a66e8aecff5fd8ec', '16', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7cb3db8940214bb994f7cc3cf7ee0e66', '16', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7cb88c4d97ae4cd387c11ad0c995f459', '10', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7cc759d2f80b4009b395572f1706fb0d', '11', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7cf73b10d4244efbae8080105b895613', '13', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7dc129309d4643ef8f3f8d90d7be16ef', '8', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7df4b6330c4b403c85ee98fba52b11df', '18', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('7dfbaf1358834786af9d051ee395f6ce', '4', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('7e0259f1a8554b95bf520f2a23369217', '4', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7e71e399cf5a45e69f36551fc40e2cda', '19', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7e76b1e139e24fd7a7d10b39538bf023', '2', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('7ef53fba1d4942fc91955a0863e4d0aa', '22', '3', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('7f270c661b084016b8c503d62cb41ed7', '21', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7f759f66965e4e048217bbcd7f84e315', '15', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7fa20b5e5a164b6086d3cff8f41b3158', '11', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('7fb57a60571c475b9da3c2815b8ed01f', '3', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('7fd9f28355b341358cceb87b27216ee9', '9', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('80182430cae2478b833bb625d4601d92', '4', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('80d7725a6bc6476f8e3747962fc9c42a', '4', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('811d3444c787477bb82c98f3cd272185', '19', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('819f166959fd4b80ab85e6a7c0b7cdd1', '19', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('81a436ab6705409d98710da0d3e3ad80', '10', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('81f3588ea8bf4544a5417a778bdca410', '9', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('826a8265ba474389bb0fc93074517120', '12', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('827a4143977e4d07bec8221f3697bfa1', '15', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('82cd8c9221c24d6cb2913c977da84932', '1', '9', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('830a7ae5aff34fc5a6da72f59c1e36aa', '11', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8318d8011eeb4524a323abfec8853941', '17', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('83d2dc09a833431ab69e27b24c6df984', '20', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('83dc99b1fd1b4467b2f442880f7a11d9', '17', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('83eaa54af12c4a69bbf03a61065c0561', '12', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8480cd949c5a4a789b94460b77cb10d7', '6', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8496ed8b95a24b959a10c5db639ca5c1', '14', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('849f8eac3043403cadf5f952ca55e0c8', '22', '3', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('84f2c90d4a9040e4aaec30e0a75b7581', '14', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('8524fe42f11e4b348ce8c754c4cfbfe8', '5', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('854890659f754512887a8b4c17c6b26f', '3', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('854dd837fedb49e68f3ed9fc84077e41', '19', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('85c42665e5a843a9b0c850dd2c530ec4', '12', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('85de002f1e7e45e3bb4b288499bd5a91', '21', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('861c534ed7f44c6c9c5c767ddf2ae92d', '8', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8639984b25914fa7ac0b5d1ac5dab421', '15', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('867291d26c26424e8f21b3a80338e4b8', '8', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8688b6a7bb064e9ab41bba4cca42330f', '16', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('86995826370b4267a5f96e87d9de63ea', '22', '9', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('869eacf87fe242cf876d64aa7020c995', '8', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('86b7108aebb941d6a35a92c6ce18f3ee', '13', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('875fc2a01f6243c2a946548b526138a1', '6', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('87b12952fded49c089197f518b83ea75', '17', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8923b89b5b414b75bab32ecde0d2c726', '4', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('895eb3e767fe440aaf2e343722323db2', '4', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('89734fffaab74952a08d4a6d9d88d1d2', '3', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('8a523c164bf24958b1ddb409566afc19', '18', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8a690f6f7f9f4d60a6c18c389592f921', '3', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8abc0941d72f40d5adeec9a2a0f31940', '12', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('8acd7791713b46e0a517168087309294', '16', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('8ad4f21e89e04915a9675b4c71490ab6', '2', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8af11ea4bba24a17b43a407324887bf9', '3', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('8af9e06f43be47cbac9d57a37f549e79', '17', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('8b04c94c0a554522bdac6a11486f29f4', '22', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('8bd2dc9365eb48108920f7fc051814b1', '5', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8c44c58973b84357be54b7fec2ac56c9', '17', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8c94668a77ff4b848887a2eeb8799ae8', '15', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8cd000527b8b45b0a4aedb25a1921eea', '13', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8cfd52a1cf7f490aa0025d55eed30c4b', '16', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8db92d81ee8d49998ad4f7a050c034ba', '15', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8e751be9568c4ef58e4bcb5ce4d38ce1', '15', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('8e84fc18510f4be9b23ccea20984fd77', '15', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('8ee91c8a40914f53abfda8bcaa4441b0', '12', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('8f25a80e7e85436297d93fb2b12378c5', '5', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('8f9d1efe08884de985082cbbabbb7d34', '2', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('906fb9b9af0843b7aad1cccffc746886', '20', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('90e9d9f9e4074ab18eaf1e1629939b9d', '15', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('90f38229eb114f3e8b7b751c430ec7f0', '4', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('90fa9c7cafbb471ebe97b9e2390a0a6a', '8', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('91aec36f8a8643dca8b093764a0f2921', '15', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('91db4a40c4454e90b8f569245b4d3778', '12', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('92b1ca5759014ed190c3773d837b82dd', '4', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('92b572c1407c4be7952530e70508e58c', '15', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9346bd67a0ca4794a307fc6885654f4f', '7', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9373f08c3a4e425cb96f8647439a1175', '8', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('93a9a00ffe5a404d862249a1143545c9', '11', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('93bdbb80f5b04580bdd4ef052d48e7da', '14', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('94562a70f2c9486cb2be91b04f8e973b', '10', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('947f66424068462b93cbacc5c834beaa', '12', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('949ac1150bd042b29e8b07a2e070888d', '5', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('94af1e4d62244655891f4c393317cf08', '18', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('94ed30fa84f1454db0d604e8b0b12e76', '14', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('94f5f4fd7a9b45a7b01b23fc4cf758b8', '5', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('94f940194dce4faaad83e4fa7f601dac', '20', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9519c92bcfbe4edfbc32e102392669c1', '2', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('952af110f2244037999776c0985aeb42', '14', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9549b7bb52014d65a321a01e4dcf79ac', '15', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('954de5afa3804965b5dbdc1d59cdb349', '8', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9566ce35e7934e50b52c03530e0e182f', '7', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('95e8e560d9f2402b95f0a50f4e36b349', '18', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('962f2c01e12042a19280509a1b6d3c90', '6', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('96c3a002d0c2413d9f42ce286e1b842b', '21', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('96d4fa3ade754a3a8d8543732bb68722', '11', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('96f48c7fb9534e438cc54042f5a071ab', '10', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('96fac6edfab84ef69bee66e3551e33f8', '14', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('97834eeec5f74ab8922994fcee60b7fb', '2', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('97b8c664411741d99284cab9a0c00837', '11', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('97de8407ad0544c18abc65a388f2320b', '21', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('981c71d63162499691292f2854171a21', '16', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('98c33475940a44c79942d9a9a7f5aa6a', '21', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('992d6e90ac404136a47886e8ab04fa24', '10', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('995dbdc6cf1348de9782071adef6fff5', '2', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('998abd50c89b416e9843b02d5206d231', '6', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('99cfe3db9760420585aba1dd59e335ce', '22', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9a3921a989d945108e166762143ffd84', '20', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9b0e749034ca4b0cbb9022b523af016a', '6', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9b315bbc815c4c87835c4c685d82c76f', '19', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9be106ff66cd4a4fba1cd7f478067b71', '16', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9bf0e6ba661f4907ba5c6bc4a5a45196', '4', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9c2e45c63cdf4e66a108167b3d4946d0', '16', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9c675659040b45579a43247f27949e65', '18', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9c819f4a2c924c55817dc4f75810f023', '15', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9c91604d7151487492f60f9306f8f359', '6', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9cc3cbaa587a4e96a121fd208996d7ba', '9', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9ccba9ce1f4a4a539575b193c70551cb', '20', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9cce8d3c669b4202bf3beb59b13a2e69', '16', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9cff76fccc3a4e2a9c2ad2bbb5fc263e', '19', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9d38d80d8f6e48cbbabf15f2e42ddb5d', '2', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9d51356fb55c4c4cb32c23d73a15695e', '15', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9d6aefd3f54f4fe89ea63302691b1452', '21', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9d6b4f1989054df0ae897b105021c645', '18', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9db023e019da4e89bf6061b73489868b', '21', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9de614d05ede48a5bb048e928d0bd9d9', '16', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9e11d047b9e249c3a3933faf6c9ad211', '18', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9e6bfb07890447f2b24afb7a95045500', '4', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9e7f6439aba240ee9edad6fa7c9fd399', '20', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9e8f2e817772446e99d82c96c10a9b1e', '8', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9ea6954a788840ad99080d9cbae8ad5f', '22', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9ed21530a2f44fbeb6d073dae31a0bdb', '11', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9eda7464bb81465a99a120a88202aa45', '12', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9f35c7739e2c4b32a98cbacf96d6b57c', '19', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9f50e5e29bf044fbb6b16bcd2ebbccc4', '1', '6', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9f6eeb34a557495299e05575dd9866dc', '4', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9f8cbd0c8a80400ca815583682aa3789', '9', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9f9ac1c8282f4148a9742449c28847ef', '12', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9fadd55907394ad2ae8d841804a9c5ba', '20', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('9fb24179706741839f2f1d4049c18b0b', '5', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('9fb2b84f9f364545b5ff3542bac2fa60', '20', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a017c2c41fe5497099cb53546284cdd9', '13', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a0553612d70f4bc2ad23e64a711c5c5c', '11', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a07e37ffa4cb4e59a5c32ada02be6faf', '11', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a07f5740a0174bc6871f712c085d3c81', '22', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a0cce833b64a4f7fa1e9079d297dd4f9', '2', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a0f0a241b4b44c368d9580f61ede346e', '9', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a23d7edefd0d4980b71c8e2cd2ae0747', '9', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a24fa16aa2b64e8baa26175a7bd3915f', '5', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a25dcf3c80a94d229b6acd11f2ede8b9', '18', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a2c769c428574c19971dcb726494bb58', '4', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a346feeb63344322b0283d1977f7c629', '10', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a39417011d02409680394f4647cd245b', '11', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a39fa378a6fb43efb53a53cbefcfb9d7', '11', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a3ad9ecd43884bf1b572faf9684af24a', '4', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a441b71d41ea4106b7d9289a62da8f0a', '4', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a44680bfc98647c68754c6b790cfb362', '18', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a470da63a8ac4832bcb4141a126ea2e4', '8', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a479e0f1cb2446b39eb9ae8fb5f481f0', '19', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a531041ed2014f1c96529716378e54ee', '18', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a57f472ede994f1a90bd882d696930b7', '17', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a5d973df4dbb40019fe784721f62877e', '13', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a61e60ef34da41ad9d403008d83f8d0e', '21', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a6743f1f0fa74674aa1ef0ddfe348ff1', '3', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a6b3f455691c49ddae017dbcbe7c1bfc', '14', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a6e9509fb30545f9b9c85237dffbcf6a', '9', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a701174cb4024c729ead49cc4112e5f0', '11', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a734b75cf216420aa4ddb487c3fcb46d', '18', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a76a12fe1973431090479cc9998c6e96', '15', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a780566848354c5e81e80bd33ff2115f', '15', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a781fc4853f940ba969e1a22f78b9550', '16', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a8011ee1659841f1bde3b4cab3aa972d', '6', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a87a30a451084c369407ea7d301cebc7', '12', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a8d9d3ae69b94064aab3505fdf8e95b2', '12', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a8f3e816fd6140ee97e0f5de34817c28', '13', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a8f8e3a373e64293adb9661f007c9e15', '10', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a901fd8a21cf471baf56fc88184019bd', '7', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a915f75eb7cd44209f47a8b453aaa63c', '7', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('a94c5a2cf2d14978a22696a23f49311d', '4', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a9dc275a59414e34883c8a64fbe1b274', '9', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('a9fab4b0f47b4fa2ad36f488e5a74258', '9', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('aa0e0f2467b14b2da2b9e4ae5ec1defc', '16', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('aa6ed6036fcd4dd196d791c94ed7d794', '15', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('aaaadc04878e4c3c919aaa09579ec8d3', '19', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('aaaba09d31fe429ca0c3298a0222c933', '3', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('aaf9152be01a4ea8bd37504bc6ac3113', '12', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ab5dcf9c6ba34658a236f538b885f8b2', '17', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ab723ee19ec94ce5b5e0f951f9e8135a', '8', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ac1583a796004453a25ac55f33c6ba2c', '8', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ac1b8c022e6f445c8b3252e8448cf89b', '14', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ac82e9e838894aa295562c47073213db', '7', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ac94cb41d95f446db346eece0e2db682', '7', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ac9716c541e745328993e0facb58cbc1', '11', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('aceaa3e8f5bc41f78dae3e520cb4bf61', '7', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('ad01f6a1cfef44599922d90564d954d6', '5', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('ad17d68fc9854463a54b097afae09aad', '16', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ad5ac38477ca4eb498f12c30089e2abc', '18', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ae01613de3bf4c998ee2b4aa8f86b677', '9', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('ae38096c16234b04bc17d21dc306416e', '3', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('ae4ce9fb0d234231808ca0775804c319', '10', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('aec8a556a2d145d58c263ce1726bc677', '19', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('af681a4a24364b4781275c2f5ee3afce', '19', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('afb1b4eb6ce1450984cbfe926812e3d1', '16', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('b077e581bc4d4252a9d2115d022b921d', '7', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b10205bbd88146c2b85db8c0c2839c62', '10', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b12a1f3967a24a3695e72e0fbca2fbbf', '11', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('b153b85f82d2457b90cddbf54386e6e4', '7', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('b1686e28e264495cae9161e79dfd3589', '9', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b1b18d48c7604a5a8bce8e0e5437dfff', '3', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b1be160c8c134bcab3df7f5a788892f6', '17', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b1ee3087e5914d84bdc753ce4fb41811', '3', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b201902669494025a46316e0cb7b9896', '9', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b22cf8bce5cc4cc2b8f2bac7b28301a5', '7', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('b293c54d0d464b9187fb8c44945b8e70', '11', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b2a1ec52791d4d6f9e592d697f25e17a', '7', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('b2d97bfdb7d649158e4b8ee61b45a0d6', '17', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b3d548932ea84e8889f26955fd0ad70b', '11', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('b3db5e93ec944f619bb47b16a677edfa', '22', '9', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('b44fdcbad1124d919c59c46f7ca747b1', '11', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b4becefcfe6e4547b953f921a9bc8258', '21', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('b53558d3e4984ae186e468a4a7763cc2', '14', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b53d27bef6b44f049f1637c2ca814e02', '12', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b5a54eaff0cb49fd9f8160bc206e01b1', '13', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b5afcfa6bc434f3c81d5446c68081021', '13', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b5b2819d12124b919439aec66f04c165', '18', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b5d96c96082c48729938c4ec077c9be4', '1', '3', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('b6453a0461254cfbb18bc46a06a8396c', '12', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b68f279218e04f7491556de8701c7bff', '14', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b6be84ca106c4b6b9fcb563040caf6da', '16', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b6c7fbf7f57b42a6833f126677a6d68f', '14', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b761482c984d49388b8c9ca9848e826a', '1', '9', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('b7c44f9c972643b384039f226683a153', '16', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b7ca0d5c936444f4816221a478872e00', '19', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b7d9aade932b4ded9e5b176dc8c249a3', '11', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('b8a1c68fe2a7434da7549c16029113dd', '17', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('b917b986fdf340f793c479c5aa932ec6', '2', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('b932f22e46e6412891d9713a7198f420', '1', '4', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('b939cc999e8f44d0baa810ac152067ef', '4', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ba291340b48749bcad3db7157e41c341', '10', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('baabc75be8864cb59c43664efdb18d38', '16', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bb133324e4524e50981eeb2ef969e74a', '14', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bb3e6b67989344179378ec6cfbf3f96e', '7', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('bb4b1da7fe914e1cb860116a4bd061bf', '14', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bb4b502b7f3b4e2082e0c405f643e78c', '20', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bb61723193114f398cc8fd3c28301808', '17', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bc5751d9c03f46b68e9fff0c674f5ff5', '12', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bc6643e9152741fba97fd435a9fcf9ad', '6', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bc84b90eb0e0484c94f5726870c8f99c', '2', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bca57648ec4c42a8be012a266ab547f5', '16', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bcb3ed9fe5884005b2bb8d5c50a8aeb3', '20', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('bcd7415988c544d8a702b5d33b256d9a', '18', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('bce2b3a65bad45669b80ddc04e8bf9ef', '4', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bd11eac61c394475a3d06d4e6f033ce7', '9', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('bd2d5711cd9043ce8c61de93bca6c91e', '4', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bd34717556df46dca00b1384b9969a91', '13', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bdcc4ccf8ffa4b26ac3f9e17d3e18181', '21', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bdf6e2a28bac486aac0b502cd6364fd2', '20', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('be09b935c90b4b3d9d39c4e49159ec84', '14', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('be0de4b60dc84e7c9b7454f3394f468a', '21', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('be3db2b77cbb4d2d945c396b49f20e3a', '3', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('be540f62b31e4e53b641817c567dcdd5', '21', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('be710bb72a0c40929dac47baf37683ac', '17', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('be773cd075804be58e2c2ec1929890db', '21', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('bec4427bba9541c58098937fa0ae25d5', '7', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bf069b6cbdc74e97bf9d0d78256868ed', '1', '9', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('bf280612a37f47c38bd9632e50139db9', '19', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('bf90607db5c6411eaa10b3914f36d855', '19', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('bfa971dd7c0c4e9d87b88b94605bc551', '19', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c078f6ba31c442c086157b91e8c388be', '3', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c095d00a630a4253a43dfc381bd3f170', '9', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c0c61bbb7b40402a88db8f3714787d82', '9', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c0fad03e509d424dae8d9c9a0c3f9117', '3', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c14ea6d14f494943970c747333f0656a', '11', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c18c2e217f814491ad00340c998a7c51', '10', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c1cd1eeb019849a3a4f2f09c509eff4b', '21', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c1dfaf960e1a40abbe9560be0252bc96', '15', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c2352beced6b4adeac7654fd5306d0d2', '18', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c23de06f981f4c41b65631eb5450ac9c', '15', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c283b760b91b4f6aa1b9102730026a4e', '12', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c2c8acd2e795403983e700b787ac6e7f', '15', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c2da1c0b0fa24a899fcae25e51e62f7c', '11', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c2ea6f07b5e04a6285a79a38d28bed6f', '19', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c30e100ba96642498c17e973112d6498', '17', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c32758465c0c4df4ae1bbbf8352f2d58', '6', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c363944752554e1cb076408a545650b1', '12', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c3f3795e80c44fb5961b51a8d56fc1b4', '12', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c4264fb70caf43e3b46f768d9524a680', '2', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c48d8f2867e84d48933ff7379bfcf4f8', '15', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c4c781a8259549b68b320cd8b9b9a882', '14', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c4d3d4e14cf94e6db369eb110d87b48b', '1', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c4f6ba6969644b51aac1c7675952e633', '5', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c517d3f75e1a4022acfdc28c10fa9a35', '11', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c62cf58eda3b42af96fabae058d484f8', '5', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c6317577e901453d9c2a974061bd2a79', '2', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c6509030072c4ab29362bc09c7e8269f', '14', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c69ad945ac6a48f5bb502859d3788b6d', '22', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c73de1ea94dd4f2cbae3f2e27377e8d2', '21', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c76b1fb419bc4ddcb73829a028529737', '2', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c7922c08d7a643528aec7336291a2690', '10', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c7960272cee64bbeb974f8eea711d9c3', '1', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c7a0fade13a64af88d3feaaadb2594c2', '17', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c7e97c51d74744b5aeea5de1327d3549', '17', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c81e9fa98b1d43668266a7e25954a0bb', '10', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c8357d584f9a4820a651b4909fb41d7b', '9', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c861b41a5c5c4c40aa1c7693adb25c0d', '20', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c8bc3c4cd68049399165236d19acf017', '4', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c8fc7c65af064c71b46b011e1230fdee', '22', '3', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c93e17f781114bac88ea8c996c6649d4', '11', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c98504a140e24f3791aea1dcf137afa0', '14', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('c9bfcf9827494c34853354315d344c4a', '7', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c9c0158116f64c14b668a8d1318714e3', '14', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('c9f59238969f4128bec4f03160d0de5f', '8', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('ca2362d76ee4459884afc51975a251e9', '8', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ca27f0908128436db9e89c526e298125', '14', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('cad778f660cd4b79aae737af17f4c372', '19', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('caefda0e7d0b4ef39de2aa2a31ceddb6', '16', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('cafe601052b7465db129bc996dea74df', '9', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('cb1660fb5a644d12b684997dd70a30d1', '17', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('cb253b31e5c842c1b5a4fce6f516ca61', '18', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('cb27811195734fc6a491659a0529091a', '4', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('cb601727fecb44c1a148680a1551b428', '5', '6', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('cbb1fc927ee04c288bbc12c24ac5278f', '17', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('cbdd3cfab1f4444f9d4c27babfda47e7', '5', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('cc7f1e65818248318d8b5cedbfe9d1a4', '21', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('cc8510e49ee340c9a7e22209bc6b9808', '14', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('cca4fad7a0bf415091a9be8b353390ff', '2', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ccc4f42525034688a5777e6bd8c07c80', '17', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('cd25e7a26419485394a221c26880b8b6', '21', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('cd333411f1b44b27a14ac39f322d044c', '20', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('cdb039d595ba4c9b8ad75a9bcf84bcdc', '12', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('ce085ac76ebf450592d10ebf81e3f3f0', '14', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ce61e9c47bb4427bb2f3903db12ff086', '1', '4', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('ce7408554eac4c51a65fd150730c58ac', '21', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('cec03e132955402fa5d9be36e8f5a520', '19', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('cedd4e6f311c47cd843951d7493405b0', '22', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('cf7297768f00430c9855f01822928ce9', '5', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('cf77042c5183429eb6b36ad3ba68e7f0', '2', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('cfab11fe021042f8a113c97233b538b9', '22', '1', '1', '729352f0e3b74fee91bf15baa7187e58', null);
INSERT INTO `space` VALUES ('cfc3615aec484045ba0e38f13e24055a', '2', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d01a7a7d7fe846fcaf64547f81d301dc', '12', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d045cb31a336452f8e15d147c836b24c', '3', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d0af7f3239e54bc192011e081072c80d', '3', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d0bcd47922504ea2ab58176bbae823e5', '17', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d0d6b9377b4a46a7a832187384535a15', '13', '1', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d130d59d01be4b989ed85873a1ae94bc', '20', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d143c2a9d8be4e8dbf70844658d3a286', '21', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d1573ff4f8194acd88bc4e12530fdb3f', '10', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d1703227acf84df9a2153ab19c76d098', '17', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d174b6c00b244a10ac8b370fbe1dd547', '1', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d1f5d918878c42019d5a04414124929e', '18', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d29d8bb603904737b44982a41d0e1633', '12', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d316df49eb184e02aa06dfe481a52170', '18', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d39fed5e71fa479f86e186c3476ab881', '5', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d3da1626c3d94de3997c910b5a878725', '17', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d3f5946f63974d71a20941dcdcb4217a', '15', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d4031d528ad248e183f42906a23ab99b', '9', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d4d51a784ce74f0182a936f70ce474ef', '6', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d532b3c6352b4e9ebc49c9877a08d474', '22', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d54388f0cb2947439efb2ed2f02455ea', '22', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d54468173bcd482d80c0b88f86de1e2d', '12', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d5dbb81b9f10482aba6d8e8b680aff0b', '11', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d62d37bda12a4fc4bdac5bb705df45e9', '18', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d6588370864d452386aa7f615657970b', '3', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d65a8b65d79f4ab2a8cb9fefb5c6edb9', '10', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d6747955202c4577b85898cec066c601', '13', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d67b7ad719d340e5afb84699218437a8', '15', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d7976441b1a94a0eba6cab1ccadf060b', '16', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d7dd80873f69426eb984eb781bfb2951', '8', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d7f0b36c30364cd7aca9f47e9232d1de', '17', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d8428778cceb4e2c8fa828a7682e471c', '4', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d85bff18b00442459f2563400c545867', '9', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d87115c432aa406088d86ea97635e4de', '6', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d88207180c6c48c08082a7f6008005c2', '17', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d88eee61c44841c890a8280246c386ac', '8', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d8dff837bba94eb6acce4dd6ee1aad2f', '5', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d8feed17f94b4387a97a85a98da90de1', '2', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d987e67fa80149d7886b22f6dd5c849f', '14', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('d994da7b3a7f40a1a8a2a4c12637fca2', '4', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d9a3dc7f90074f488f88614e9c6ac2a8', '19', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('d9e3449c0a2749f58f33fb15abb3bb58', '8', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('da131841703a49279350f765b5e0874a', '16', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('da20eae0287843e0b406803f603fc692', '22', '9', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('da7e32c098e645299d1e34ff653c9d6a', '19', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('daac595bb67b4540aeecaf844c2cf147', '19', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('dacba2418b384d75a951b0376f6d2c23', '6', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('db1adfbbf47e42dbb6b0e49dea451375', '13', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('db575f1d3c974dafbb3b6c420a510781', '19', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('db79a5fb0b884f3ca366de5d04c7812b', '4', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('dbbff3a218a042129cc0a96f292cb3d7', '19', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('dc4b8f3179e24f85a468ea4863f374a5', '15', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('dc58f8b1f2ff4b47a6e3b6bd77b89701', '17', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('dc79109b7af8412190c489f58cd94ca4', '10', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('dc913a51fb3a4986847c56b207516260', '12', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('dcd22ef7c1e84e1490bf52b0bfbf47a5', '18', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('dd822581eee641bca7708e14a83a5052', '18', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('dd975a3d575048f489bc4475c13c640a', '3', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ddb2ae67fca24d4c920e17b5f9fcf193', '16', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('de0cad8af4094e9e8e191d68f58791c5', '13', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('de24e34899f6470bb42d298182d521a5', '14', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('df867ef829eb4f799b34b063c93c94b0', '8', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e0251f8121d14e128602f3d44d2ac42b', '3', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e0f6f2575b0743458ae9bcd322da55a3', '18', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e1379c9349a24c33bfc6261e5b8b3b52', '3', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e13fb7922ce0450797fe17eacb81afdc', '19', '4', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e18ad96c6e4c4ace8877ed2d091c9af9', '14', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e1a5ddcf46dd476cac795bf422ed642a', '13', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e1d42e17ca024d3589a022716aad3694', '21', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e1ed7ac0b5e2427a857e58a1efd337ec', '15', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e289401641fe4583a8744761233edf9c', '8', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e34f287e6bd94154bf68c6a73f112fd5', '5', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e37eff3df5b841f59c64d54a82d5168d', '14', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e3e52d21ef17478188d9fe28d32721ea', '3', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e3e954fabd3c486a915bf606955bb6bd', '8', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e3f6f30e5d144a25aec3c9af03ed41d5', '8', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e4103bd09b1b44e68aa369cc887ef280', '9', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e465d3bff894430596f2be9672642df0', '13', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e57cb8786278422299c0e25413b7d586', '20', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e5e5e4b218384444a0eb977a3fbeb498', '1', '3', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e60415c55c9e4a62b6bc41755e02f9a8', '3', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e61dfd0657834d22b4bc70cf328aaed1', '7', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e62446cadfc84dfd825041ffcb71a838', '16', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e66ceb189e3c4974b3265c53d454f102', '10', '4', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e7060dbe420b4a978d8d854b8f00cd92', '11', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e73c70838f7f430195799332f07a7805', '13', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e74f7db76fe84038a6861372f9db1fef', '5', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e7766b6625f04cbc9d82994322dd849a', '7', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e7a88d9a02134c6987e0423142412649', '13', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e7ae2be3678f46aabcc5f1714ffafff1', '9', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e7c6ccb8f34943b59a3febd137bfaacc', '10', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e7f507db8d8d4b7da4d4b0a12b8bc1ba', '5', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e80d748d76c34a1fb04b43e0d717a826', '12', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e87a91e609934a62bc974923f664254d', '6', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e88544bd7b294198a5ed64d1fdd5c410', '22', '6', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e8c55cf50da649f2a671e96b2243c8a3', '4', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e92f8a197bf34b2fa1b1c7438a21aa6f', '22', '6', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e9492af9c0c04a49a1be51291b028f6c', '14', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e94d0e8e761948119fdd6763441bd14e', '14', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e9a2f962e0be4d79a244c3cc2a986b6f', '6', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('e9be66057041445e8c61dfeff80e3e6e', '13', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('e9d19962ef244d77b2f4c47d6c2ee02d', '7', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('eb2a986530024a3197d0c4abd7e8b815', '12', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('eb33881734e6424abec5840a19259955', '9', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('eb4368b4fb9941bc9bc887aa750a828d', '11', '1', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('eb6b9ab25c9c41a38a5eea723811308e', '1', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('ebe9e66ef3a342d59028ddc9c5e4d4a5', '2', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ebf119a67e6d463493ac659f63533048', '18', '7', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ec11d90a6d1b4610b72eee5e05e7a12b', '15', '7', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ec1e42324c8144ecbacbd16e955f2351', '1', '3', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('ecd47de05d7043c88c14012f32ff3b66', '20', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ecd55fe8991c49bd8374b91a512b4deb', '12', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ed07851f47fe424cb28c7e95ec6a0c09', '2', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('ed19e31d74ce4fca81996f4e790f666d', '22', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('ed8ce1770da244f8ba23fe0c3d6efa43', '7', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('edd9e9c62332410e878b5ec997126de8', '13', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('ee1b33388e97448db418190161dc6d59', '2', '5', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('ee1dccd092cd4a20bf9a441c96bba57b', '14', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('eecd4209439548d082a99f3e0a6bec90', '2', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ef70db1a3cda47e28bd2dd561503b312', '10', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('efb93eca70a64449bd79a49cb17e667f', '15', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('efe177e4343141c2b29558e1e9204176', '1', '9', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('efe7e9d803914111b16cc9db1ac0522e', '2', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f056face6e0e4350b80b2364ba610602', '2', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f08d2168ca7d4f38981809f622c075db', '1', '2', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f0bbeff7493641538db73fe1f6a174cc', '16', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f0e3ebf9a9fe4357899796efe0708982', '2', '7', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f1afcf466f6a4c18aa07259c39ffcce7', '11', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f1cfa9753f554f7385414d2d9d4cabae', '12', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f1ec5e64b5aa4a6c80d2434432fcabaa', '10', '8', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f20c6277163b42dc9711a24eb14348c4', '1', '6', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f257cd92fe664a60b89de832f1ee9d5d', '20', '5', '2', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f2aedce10eb34104b5a15773134f9559', '18', '9', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f2be1523dc5947a196658035da8be85c', '17', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f2ca3103a4cd48ed92033fdda8c970e0', '18', '3', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f317aaab738d410485234b1de95b15a3', '11', '3', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f3282b003d2440c895726f3d234e06b7', '19', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f3433920bd1d408cb3ad64464c93b985', '22', '2', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f3471143b9f74e05b2b72646f73825a7', '4', '9', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f3ac322439a841ae90cc8f3509e377cb', '5', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f3d00546ec35496eb43aa9abe18d88d6', '16', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f3e7a2b59dff4e19a8c7b2b076c120f7', '11', '8', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f40e7915619744118f83e10b3b4eecba', '6', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f414e80574c4466ea889285bae15ba56', '7', '6', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f427ee9568fe4793ad1e0f23bdceff41', '6', '2', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f438779b2df94a338bf74be663f8785c', '4', '2', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f452f72a16794072a942cbe196230ca4', '8', '6', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f4a441341ca240b0a3f0d9c924ef6c4f', '6', '3', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f55b20b129c64194a753eb8a44709909', '13', '4', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f63ebac9afed4b46be6af3ea56c0eb12', '22', '7', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f6480404ea484b9b93fe5de0f900d423', '10', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f6a374c16824423db8b6591a748f48ee', '22', '6', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f6cb6284b49844489105e016652c2e2f', '4', '5', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f71fba216cf74d6f824f2c0aa85439c0', '6', '1', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f72289617d054fc38c7177e9d0311666', '1', '6', '3', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f74ac0ef42a24bafb90a1aab3d5c9b8e', '1', '4', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f77e72fc51cf4a9fa350e46536a10c33', '5', '9', '3', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f847e5892c184cdfb1a34c59a6b89294', '9', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f86af5cfa3d84d16896b1271152153a8', '4', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f8903f8165f140d79bb39d8f11e6928e', '11', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f897d89a95ac4a33a32b106f461e5937', '19', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('f90a281827c74d8c88879e1bd2400435', '3', '7', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('f9f8fe09a19143549a88f48928cf4252', '6', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('fa09782f15cb4a65ae8ed451ba66cf98', '3', '6', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('faa56c51744e45abb435a21849026cd1', '21', '5', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('faadf665f0264016aba53ddf2044866b', '20', '5', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('fab5b1fce03a446486bc198355fc9dee', '18', '4', '2', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('fad1d73e64f14b0dba29b6cb3b434ac6', '8', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('fad44a0bd20d4bfbb1913c43d89b5d7f', '3', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('faf07797a0e549a4beb145cd1dc33892', '19', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('fafdfb778af2467aaca0514b590fe27a', '11', '8', '1', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('fb97013a57ff435d9727cad5bc3ce95a', '18', '2', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('fc312e45b80a4c99b1badff86e98ac2c', '7', '6', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('fceaeed6d28a4065888666bde1eea333', '18', '9', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('fd687648f59e4e47868aa1d1a924d6a4', '10', '1', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('fd996276b76645368ce6d61d995c2520', '22', '1', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('fda6a8c46f934414b07b856eb33cc97c', '17', '3', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('fdece67dea1e43c2bc0a94f8465d73be', '14', '1', '1', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('fdf92ee5f7924278ba5ff63d8efd2bca', '2', '9', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('fe0167728ecb40e3b5ed37630d3cab37', '3', '3', '4', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('fe35bbb63b6b4d249007981ce6b6167c', '20', '8', '5', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('fefd24cd45b5426391292cd12f8db33c', '13', '4', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);
INSERT INTO `space` VALUES ('ffea0111f54141598045c57afaaa1414', '2', '8', '4', '920005b52e54468ca653be0b593e6025', null);
INSERT INTO `space` VALUES ('fff6839cbf8347419eb755f8ded6c38e', '21', '7', '5', 'aaaeeb3acc5e44899a5d1de5ca5ab11a', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `idcard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `phonenature` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `emailnature` varchar(255) DEFAULT NULL,
  `nature` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for vip
-- ----------------------------
DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip` (
  `id` varchar(255) NOT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `enddate` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vip
-- ----------------------------
