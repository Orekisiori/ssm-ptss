/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : air

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 19/04/2021 20:29:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for airline_info
-- ----------------------------
DROP TABLE IF EXISTS `airline_info`;
CREATE TABLE `airline_info`  (
  `airline_id` int(11) NOT NULL AUTO_INCREMENT,
  `airline_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `airline_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `airline_identity` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `airline_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `registerdate` timestamp(0) NULL DEFAULT NULL,
  `airline_account` float(10, 1) NULL DEFAULT NULL,
  `isvalid` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`airline_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of airline_info
-- ----------------------------
INSERT INTO `airline_info` VALUES (1, '王氏航空', '456123', '4897456165', '1654894650', '2020-10-07 22:52:13', 1236.2, 'yes');
INSERT INTO `airline_info` VALUES (2, 'Trump直达', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `airline_info` VALUES (3, '中国国航', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `airline_info` VALUES (4, '东方航空', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `airline_info` VALUES (6, '新西兰 Air', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `airline_info` VALUES (7, '德尔塔 Qantas', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_date` timestamp(0) NULL DEFAULT NULL,
  `ticket_id` int(11) NULL DEFAULT NULL,
  `ticket_price` float(10, 1) NULL DEFAULT NULL,
  `startplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `endplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `starttime` timestamp(0) NULL DEFAULT NULL,
  `endtime` timestamp(0) NULL DEFAULT NULL,
  `passenger_id` int(11) NULL DEFAULT NULL,
  `passenger_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `passenger_identity` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `passenger_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `order_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `purchase_number` int(11) NULL DEFAULT NULL,
  `Order_price` float(10, 1) NULL DEFAULT NULL,
  `seat` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (38, '2021-01-01 15:32:31', 3, 980.0, '香港', '东京', '2021-01-03 21:30:00', '2021-01-04 11:00:00', 1, '折木', '110101199003071997', '13018374138', '已改签', 1, 980.0, '2-2c');
INSERT INTO `order_info` VALUES (37, '2021-01-01 15:31:50', 1, 4888.0, '芜湖', '上海', '2020-12-19 14:30:00', '2020-12-19 21:00:00', 1, '折木', '110101199003071997', '13018374138', '已退票', 1, 4888.0, '2-3a');
INSERT INTO `order_info` VALUES (36, '2021-01-01 14:47:24', 3, 980.0, '香港', '东京', '2021-01-03 21:30:00', '2021-01-04 11:00:00', 1, '折木', '110101199003071997', '13018374138', '已改签', 1, 980.0, '1-1b');
INSERT INTO `order_info` VALUES (35, '2020-12-04 07:44:35', 3, 980.0, '香港', '东京', '2021-01-02 21:30:00', '2021-01-03 11:00:00', 1, '折木', '110101199003071997', '13018374138', '已退票', 1, 980.0, '1-1a');
INSERT INTO `order_info` VALUES (34, '2020-12-04 07:41:53', 9, 1280.0, '宝鸡', '拉萨', '2020-11-30 10:00:05', '2020-11-30 22:50:00', 1, '折木', '110101199003071997', '13018374138', '已退票', 1, 1280.0, '4-3a');
INSERT INTO `order_info` VALUES (33, '2020-12-04 07:35:10', 1, 4888.0, '芜湖', '上海', '2020-12-19 14:30:00', '2020-12-19 21:00:00', 1, '折木', '110101199003071997', '13018374138', '支付成功', 1, 4888.0, '1-2c');
INSERT INTO `order_info` VALUES (32, '2020-11-23 07:40:15', 17, 1080.0, '香港', '东京', '2020-12-15 10:00:00', '2020-12-15 15:00:00', 1, '折木', '110101199003071997', '13018374138', '已改签', 1, 1080.0, '2-1c');
INSERT INTO `order_info` VALUES (31, '2020-11-23 07:39:26', 1, 4888.0, '芜湖', '上海', '2020-12-19 14:30:00', '2020-12-19 21:00:00', 1, '折木', '110101199003071997', '13018374138', '已退票', 1, 4888.0, '1-1a');

-- ----------------------------
-- Table structure for passenger_info
-- ----------------------------
DROP TABLE IF EXISTS `passenger_info`;
CREATE TABLE `passenger_info`  (
  `passenger_id` int(11) NOT NULL AUTO_INCREMENT,
  `passenger_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `passenger_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `passenger_identity` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `passenger_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `passenger_account` float NULL DEFAULT NULL,
  PRIMARY KEY (`passenger_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of passenger_info
-- ----------------------------
INSERT INTO `passenger_info` VALUES (1, '折木', 'qwe112233', '110101199003071997', '13018374138', 1990240);
INSERT INTO `passenger_info` VALUES (2, 'Harry', '93645728', '0002', '123456789', 666.6);

-- ----------------------------
-- Table structure for recharge_record
-- ----------------------------
DROP TABLE IF EXISTS `recharge_record`;
CREATE TABLE `recharge_record`  (
  `recharge_id` int(11) NOT NULL AUTO_INCREMENT,
  `passenger_id` int(11) NULL DEFAULT NULL,
  `passenger_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `passenger_identity` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `passenger_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `recharge_account` float(10, 1) NULL DEFAULT NULL,
  `option_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `recharge_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`recharge_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recharge_record
-- ----------------------------
INSERT INTO `recharge_record` VALUES (77, 1, '折木', '110101199003071997', '13018374138', -100.0, '签改补款', '2021-01-01 15:33:05');
INSERT INTO `recharge_record` VALUES (78, 1, '折木', '110101199003071997', '13018374138', 5000.0, '账户充值', '2021-01-01 15:33:26');
INSERT INTO `recharge_record` VALUES (74, 1, '折木', '110101199003071997', '13018374138', -4888.0, '购买机票', '2021-01-01 15:31:50');
INSERT INTO `recharge_record` VALUES (75, 1, '折木', '110101199003071997', '13018374138', 4888.0, '退票金额', '2021-01-01 15:32:05');
INSERT INTO `recharge_record` VALUES (76, 1, '折木', '110101199003071997', '13018374138', -980.0, '购买机票', '2021-01-01 15:32:31');
INSERT INTO `recharge_record` VALUES (73, 1, '折木', '110101199003071997', '13018374138', -100.0, '签改补款', '2021-01-01 14:48:06');
INSERT INTO `recharge_record` VALUES (72, 1, '折木', '110101199003071997', '13018374138', -980.0, '购买机票', '2021-01-01 14:47:24');
INSERT INTO `recharge_record` VALUES (71, 1, '折木', '110101199003071997', '13018374138', 5000.0, '账户充值', '2021-01-01 14:44:39');
INSERT INTO `recharge_record` VALUES (70, 1, '折木', '110101199003071997', '13018374138', 1280.0, '退票金额', '2020-12-21 10:33:40');
INSERT INTO `recharge_record` VALUES (69, 1, '折木', '110101199003071997', '13018374138', 980.0, '退票金额', '2020-12-21 10:33:34');
INSERT INTO `recharge_record` VALUES (68, 1, '折木', '110101199003071997', '13018374138', -980.0, '购买机票', '2020-12-04 07:44:35');
INSERT INTO `recharge_record` VALUES (67, 1, '折木', '110101199003071997', '13018374138', -1280.0, '购买机票', '2020-12-04 07:41:53');
INSERT INTO `recharge_record` VALUES (66, 1, '折木', '110101199003071997', '13018374138', -4888.0, '购买机票', '2020-12-04 07:35:10');
INSERT INTO `recharge_record` VALUES (63, 1, '折木', '110101199003071997', '13018374138', 100.0, '签改退款', '2020-11-23 07:41:35');
INSERT INTO `recharge_record` VALUES (65, 1, '折木', '110101199003071997', '13018374138', 5000.0, '账户充值', '2020-12-04 07:33:57');
INSERT INTO `recharge_record` VALUES (64, 1, '折木', '110101199003071997', '13018374138', 500.0, '账户充值', '2020-11-23 07:41:50');
INSERT INTO `recharge_record` VALUES (62, 1, '折木', '110101199003071997', '13018374138', -1080.0, '购买机票', '2020-11-23 07:40:15');
INSERT INTO `recharge_record` VALUES (61, 1, '折木', '110101199003071997', '13018374138', 4888.0, '退票金额', '2020-11-23 07:39:48');
INSERT INTO `recharge_record` VALUES (60, 1, '折木', '110101199003071997', '13018374138', -4888.0, '购买机票', '2020-11-23 07:39:26');

-- ----------------------------
-- Table structure for tickets_info
-- ----------------------------
DROP TABLE IF EXISTS `tickets_info`;
CREATE TABLE `tickets_info`  (
  `ticket_id` int(11) NOT NULL AUTO_INCREMENT,
  `airline_id` int(11) NULL DEFAULT NULL,
  `airline_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `ticket_startplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `ticket_endplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `ticket_begintime` timestamp(0) NULL DEFAULT NULL,
  `ticket_endtime` timestamp(0) NULL DEFAULT NULL,
  `ticket_number` int(11) NULL DEFAULT NULL,
  `ticket_price` float(10, 1) NULL DEFAULT NULL,
  `ticket_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ticket_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tickets_info
-- ----------------------------
INSERT INTO `tickets_info` VALUES (1, 1, '四川航空', '芜湖', '上海', '2020-12-19 14:30:00', '2020-12-19 21:00:00', 13, 4888.0, 'Shanghai.jpg');
INSERT INTO `tickets_info` VALUES (2, 2, 'Trump直达', '北京', '广州', '2020-12-05 21:45:00', '2020-12-06 19:30:00', 30, 4399.0, 'Gaungzhou.jpg');
INSERT INTO `tickets_info` VALUES (3, 3, 'Hong Kong Express', '香港', '东京', '2021-01-03 21:30:00', '2021-01-04 11:00:00', 75, 980.0, 'Tokyo1.jpg');
INSERT INTO `tickets_info` VALUES (4, 4, '新西兰 Air', '巴黎', '纽约', '2020-12-20 18:00:00', '2020-12-21 14:50:00', 532, 10999.0, 'NewYork.jpg');
INSERT INTO `tickets_info` VALUES (17, 17, '新西兰 Air', '香港', '东京', '2021-01-13 10:00:00', '2021-01-13 15:00:00', 54, 1080.0, 'Tokyo2.jpg');
INSERT INTO `tickets_info` VALUES (5, 5, '中国国航', '深圳', '大阪', '2020-11-27 09:45:00', '2020-11-27 15:30:00', 49, 2550.0, 'Osaka.jpg');
INSERT INTO `tickets_info` VALUES (6, 6, '东方航空', '石家庄', '冰岛', '2020-12-08 09:45:00', '2020-11-10 00:30:00', 37, 8800.0, 'Iceland.jpg');
INSERT INTO `tickets_info` VALUES (7, 7, '王氏航空', '十堰', '成都', '2020-11-23 16:55:00', '2020-11-24 19:10:00', 66, 840.0, 'Chengdu.jpg');
INSERT INTO `tickets_info` VALUES (8, 8, '德尔塔 Qantas', '济南', '伦敦', '2021-01-15 11:30:00', '2021-01-15 23:30:00', 33, 6440.0, 'London.jpg');
INSERT INTO `tickets_info` VALUES (9, 9, 'Trump直达', '宝鸡', '拉萨', '2020-11-30 10:00:05', '2020-11-30 22:50:00', 15, 1280.0, 'Lasa.jpg');
INSERT INTO `tickets_info` VALUES (10, 10, '中国国航', '汕头', '阿格拉', '2021-02-05 10:00:00', '2021-02-06 18:15:00', 107, 1980.0, 'Taj Mahal.jpg');

SET FOREIGN_KEY_CHECKS = 1;
