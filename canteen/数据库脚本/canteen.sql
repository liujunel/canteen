/*
 Navicat Premium Data Transfer

 Source Server         : 111
 Source Server Type    : MySQL
 Source Server Version : 50553
 Source Host           : localhost:3306
 Source Schema         : bishe

 Target Server Type    : MySQL
 Target Server Version : 50553
 File Encoding         : 65001

 Date: 01/05/2020 08:30:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_id` int(11) DEFAULT NULL COMMENT '菜谱的id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '评论的内容',
  `create_time` datetime DEFAULT NULL COMMENT '评论的时间',
  `comment_faculty_number` int(11) DEFAULT NULL COMMENT '评论人的id',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES (5, 2, '这个卖相好，美味极了', '2020-02-23 16:33:21', 8924);
INSERT INTO `tb_comment` VALUES (4, 1, '这个好吃，便宜', '2020-02-23 16:31:39', 8924);
INSERT INTO `tb_comment` VALUES (6, 1, '正宗的广东小吃', '2020-02-23 16:33:53', 8925);
INSERT INTO `tb_comment` VALUES (7, 2, '美味~', '2020-02-23 16:34:14', 8925);
INSERT INTO `tb_comment` VALUES (8, 1, '合我的口味', '2020-02-23 16:34:44', 8926);
INSERT INTO `tb_comment` VALUES (9, 2, '奶黄包哟~', '2020-02-23 16:35:02', 8926);
INSERT INTO `tb_comment` VALUES (10, 3, '好吃', '2020-03-23 11:02:39', 8888);
INSERT INTO `tb_comment` VALUES (13, 7, '来了来了', '2020-03-24 06:23:47', 8888);
INSERT INTO `tb_comment` VALUES (14, 7, '行，不贵', '2020-03-24 06:24:27', 8924);
INSERT INTO `tb_comment` VALUES (16, 4, '看起来真不错，应该很好吃的', '2020-04-02 11:17:21', 8924);
INSERT INTO `tb_comment` VALUES (21, 2, '很好吃', '2020-04-11 09:17:18', 8924);
INSERT INTO `tb_comment` VALUES (18, 2, '很好吃', '2020-04-10 15:15:31', 8888);
INSERT INTO `tb_comment` VALUES (19, 2, '特别好吃', '2020-04-11 04:47:52', 8932);
INSERT INTO `tb_comment` VALUES (20, 2, '很便宜很好吃', '2020-04-11 05:00:47', 8924);

-- ----------------------------
-- Table structure for tb_dept
-- ----------------------------
DROP TABLE IF EXISTS `tb_dept`;
CREATE TABLE `tb_dept`  (
  `dept_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '饭堂部门id',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '饭堂部门名',
  `dept_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '饭堂部门地址',
  `dept_iphone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '饭堂部门联系热线',
  `dept_note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '饭堂部门备注',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_dept
-- ----------------------------
INSERT INTO `tb_dept` VALUES (1, '总经办', '综合楼5楼', '02087818009', '管理饭堂的各个部门');
INSERT INTO `tb_dept` VALUES (2, '厨师部', '南门男饭1楼', '02087818807', '管理饭堂厨师工作');
INSERT INTO `tb_dept` VALUES (3, '操作部', '北门男饭2楼', '02087818066', '管理饭堂操作工作');
INSERT INTO `tb_dept` VALUES (4, '切配部', '西门男饭1楼', '02087818804', '管理饭堂切菜配肉工作');
INSERT INTO `tb_dept` VALUES (5, '荤敦加工部', '中部男饭1楼', '02087818122', '管理肉类加工工作');
INSERT INTO `tb_dept` VALUES (6, '素食加工部', '男饭1楼', '02087818012', '管理素类加工工作');
INSERT INTO `tb_dept` VALUES (7, '粗加工部', '男饭1楼', '02087818718', '管理洗菜工作');
INSERT INTO `tb_dept` VALUES (8, '洗消部', '男饭1楼', '02087818101', '管理饭堂消毒工作');
INSERT INTO `tb_dept` VALUES (9, '面食部', '男饭1楼', '02087818171', '管理饭堂面粉工作');
INSERT INTO `tb_dept` VALUES (10, '仓管部', '北门仓库1楼', '02087818099', '管理食材库存工作');
INSERT INTO `tb_dept` VALUES (11, '采购部', '男饭2楼', '020-9988', '管理采购工作');

-- ----------------------------
-- Table structure for tb_faculty
-- ----------------------------
DROP TABLE IF EXISTS `tb_faculty`;
CREATE TABLE `tb_faculty`  (
  `faculty_number` int(10) NOT NULL AUTO_INCREMENT COMMENT '教工工号',
  `faculty_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教工姓名',
  `faculty_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教工地址',
  `faculty_sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教工性别',
  `faculty_password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教工密码',
  `faculty_hiredate` datetime DEFAULT NULL COMMENT '教工入职时间',
  `faculty_mgr` int(10) DEFAULT NULL COMMENT '教工直系领导',
  `faculty_section_id` int(10) DEFAULT NULL COMMENT '教工系别',
  `faculty_img_path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教工头像',
  PRIMARY KEY (`faculty_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8933 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_faculty
-- ----------------------------
INSERT INTO `tb_faculty` VALUES (8888, '演示人员', '广州', '1', '2f8a5d52db9c326bddc828767c73eb2f', '2020-02-21 03:20:55', NULL, 1, '2020-04-02/0ef3c34329af47f1882b0d94be1c8861.jpg');
INSERT INTO `tb_faculty` VALUES (8923, '丁聪华', '广州', '1', '2f8a5d52db9c326bddc828767c73eb2f', '2020-02-21 14:03:13', NULL, 1, '2020-02-21/4cc06dba789c4031b4be4c3693ae3b69.jpg');
INSERT INTO `tb_faculty` VALUES (8924, '夏潇琦', '北京', '0', '2f8a5d52db9c326bddc828767c73eb2f', '2020-02-16 16:00:00', 8923, 1, '2020-02-21/d035b0d6c085440ea18de6c10cb479ec.jpg');
INSERT INTO `tb_faculty` VALUES (8925, '曾帛员', '广州', '1', '2f8a5d52db9c326bddc828767c73eb2f', '2020-02-19 16:00:00', 8923, 2, '2020-02-21/6aca6d8ff22245cb94cfaf2c0849ec07.jpg');
INSERT INTO `tb_faculty` VALUES (8926, '孙蝶妃', '广州', '0', '2f8a5d52db9c326bddc828767c73eb2f', '2020-02-21 14:05:50', 8924, 3, '2020-02-21/7c618b995c224a91903c26246f841a67.jpg');
INSERT INTO `tb_faculty` VALUES (8927, '江浩华', '北京', '1', '2f8a5d52db9c326bddc828767c73eb2f', '2020-02-21 14:06:25', 8923, 4, '2020-02-21/3e5cae068cbd401791dfa73d3447f3c3.jpg');
INSERT INTO `tb_faculty` VALUES (8928, '田宇旺', '深圳', '1', '2f8a5d52db9c326bddc828767c73eb2f', '2020-02-21 14:07:21', 8923, 5, '2020-02-21/41d9ec8f9e5445b2bb46a92cc33134af.jpg');
INSERT INTO `tb_faculty` VALUES (8929, '许娇翔', '广西', '0', '2f8a5d52db9c326bddc828767c73eb2f', '2020-02-19 20:07:30', 8923, 6, '2020-02-21/6dccc5e6c6ef4dfe87a6f1345d4fc91c.jpg');
INSERT INTO `tb_faculty` VALUES (8930, '冉迪振', '南宁', '1', '2f8a5d52db9c326bddc828767c73eb2f', '2020-02-19 22:13:20', 8923, 8, '2020-02-21/510710bc2a1f4a708bb81bc38084ccda.jpg');
INSERT INTO `tb_faculty` VALUES (8931, '赵美珍', '湖北', '0', '2f8a5d52db9c326bddc828767c73eb2f', '2020-02-14 16:00:00', 8923, 10, '2020-02-21/2fa4fb1dc7734e0ba885061f130ae4c1.jpg');
INSERT INTO `tb_faculty` VALUES (8932, '唐亚升', '北京', '1', '2f8a5d52db9c326bddc828767c73eb2f', '2020-02-21 14:10:31', 8923, 9, '2020-02-21/a55b8891f7334d4ea1c6f38d9ef4f5c9.jpg');

-- ----------------------------
-- Table structure for tb_login_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_login_info`;
CREATE TABLE `tb_login_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录教工工号和姓名',
  `login_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录ip',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 438 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_login_info
-- ----------------------------
INSERT INTO `tb_login_info` VALUES (133, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-21 16:05:47');
INSERT INTO `tb_login_info` VALUES (134, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-21 16:06:30');
INSERT INTO `tb_login_info` VALUES (135, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-21 16:07:10');
INSERT INTO `tb_login_info` VALUES (136, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-21 16:08:40');
INSERT INTO `tb_login_info` VALUES (137, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-21 16:09:43');
INSERT INTO `tb_login_info` VALUES (138, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-21 16:09:59');
INSERT INTO `tb_login_info` VALUES (139, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-21 16:28:45');
INSERT INTO `tb_login_info` VALUES (140, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 01:59:03');
INSERT INTO `tb_login_info` VALUES (141, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 02:14:15');
INSERT INTO `tb_login_info` VALUES (142, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 02:15:29');
INSERT INTO `tb_login_info` VALUES (143, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 03:20:59');
INSERT INTO `tb_login_info` VALUES (144, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 03:42:31');
INSERT INTO `tb_login_info` VALUES (145, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 03:52:25');
INSERT INTO `tb_login_info` VALUES (146, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 03:52:47');
INSERT INTO `tb_login_info` VALUES (147, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 03:55:29');
INSERT INTO `tb_login_info` VALUES (148, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 04:00:40');
INSERT INTO `tb_login_info` VALUES (149, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 04:05:07');
INSERT INTO `tb_login_info` VALUES (150, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 04:26:02');
INSERT INTO `tb_login_info` VALUES (151, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 04:52:55');
INSERT INTO `tb_login_info` VALUES (152, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 05:23:26');
INSERT INTO `tb_login_info` VALUES (153, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 05:31:31');
INSERT INTO `tb_login_info` VALUES (154, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 05:35:48');
INSERT INTO `tb_login_info` VALUES (155, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 05:43:34');
INSERT INTO `tb_login_info` VALUES (156, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 05:47:58');
INSERT INTO `tb_login_info` VALUES (157, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 05:48:22');
INSERT INTO `tb_login_info` VALUES (158, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 05:50:00');
INSERT INTO `tb_login_info` VALUES (159, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 05:50:53');
INSERT INTO `tb_login_info` VALUES (160, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 05:59:03');
INSERT INTO `tb_login_info` VALUES (161, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 05:59:59');
INSERT INTO `tb_login_info` VALUES (162, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 06:04:34');
INSERT INTO `tb_login_info` VALUES (163, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 06:20:28');
INSERT INTO `tb_login_info` VALUES (164, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 06:27:01');
INSERT INTO `tb_login_info` VALUES (165, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 06:50:24');
INSERT INTO `tb_login_info` VALUES (166, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 06:57:09');
INSERT INTO `tb_login_info` VALUES (167, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 07:01:08');
INSERT INTO `tb_login_info` VALUES (168, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 07:05:10');
INSERT INTO `tb_login_info` VALUES (169, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 07:09:26');
INSERT INTO `tb_login_info` VALUES (170, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 07:51:13');
INSERT INTO `tb_login_info` VALUES (171, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 07:51:38');
INSERT INTO `tb_login_info` VALUES (172, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 08:13:20');
INSERT INTO `tb_login_info` VALUES (173, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 08:14:59');
INSERT INTO `tb_login_info` VALUES (174, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 08:26:04');
INSERT INTO `tb_login_info` VALUES (175, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 08:42:13');
INSERT INTO `tb_login_info` VALUES (176, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 08:45:23');
INSERT INTO `tb_login_info` VALUES (177, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 08:52:18');
INSERT INTO `tb_login_info` VALUES (178, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 08:56:10');
INSERT INTO `tb_login_info` VALUES (179, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 09:15:56');
INSERT INTO `tb_login_info` VALUES (180, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 10:06:55');
INSERT INTO `tb_login_info` VALUES (181, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 12:06:13');
INSERT INTO `tb_login_info` VALUES (182, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 12:37:32');
INSERT INTO `tb_login_info` VALUES (183, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 13:34:10');
INSERT INTO `tb_login_info` VALUES (184, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 14:13:58');
INSERT INTO `tb_login_info` VALUES (185, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 15:53:12');
INSERT INTO `tb_login_info` VALUES (186, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 15:54:33');
INSERT INTO `tb_login_info` VALUES (187, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 16:14:23');
INSERT INTO `tb_login_info` VALUES (188, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 16:23:32');
INSERT INTO `tb_login_info` VALUES (189, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 16:29:23');
INSERT INTO `tb_login_info` VALUES (190, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 16:30:07');
INSERT INTO `tb_login_info` VALUES (191, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 16:40:33');
INSERT INTO `tb_login_info` VALUES (192, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 16:44:27');
INSERT INTO `tb_login_info` VALUES (193, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 17:06:46');
INSERT INTO `tb_login_info` VALUES (194, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 17:42:42');
INSERT INTO `tb_login_info` VALUES (195, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-22 17:54:25');
INSERT INTO `tb_login_info` VALUES (196, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 02:09:34');
INSERT INTO `tb_login_info` VALUES (197, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 02:22:06');
INSERT INTO `tb_login_info` VALUES (198, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 02:34:12');
INSERT INTO `tb_login_info` VALUES (199, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 02:43:40');
INSERT INTO `tb_login_info` VALUES (200, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 03:07:47');
INSERT INTO `tb_login_info` VALUES (201, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 03:08:48');
INSERT INTO `tb_login_info` VALUES (202, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 03:45:44');
INSERT INTO `tb_login_info` VALUES (203, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 03:49:51');
INSERT INTO `tb_login_info` VALUES (204, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 03:56:04');
INSERT INTO `tb_login_info` VALUES (205, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 03:58:34');
INSERT INTO `tb_login_info` VALUES (206, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 04:10:09');
INSERT INTO `tb_login_info` VALUES (207, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 04:35:58');
INSERT INTO `tb_login_info` VALUES (208, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 04:47:23');
INSERT INTO `tb_login_info` VALUES (209, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 04:49:28');
INSERT INTO `tb_login_info` VALUES (210, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 04:53:57');
INSERT INTO `tb_login_info` VALUES (211, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 05:13:55');
INSERT INTO `tb_login_info` VALUES (212, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 05:53:33');
INSERT INTO `tb_login_info` VALUES (213, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 06:02:55');
INSERT INTO `tb_login_info` VALUES (214, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 06:06:19');
INSERT INTO `tb_login_info` VALUES (215, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 06:24:44');
INSERT INTO `tb_login_info` VALUES (216, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 06:30:46');
INSERT INTO `tb_login_info` VALUES (217, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 07:16:42');
INSERT INTO `tb_login_info` VALUES (218, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 07:26:18');
INSERT INTO `tb_login_info` VALUES (219, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 07:31:08');
INSERT INTO `tb_login_info` VALUES (220, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 07:34:03');
INSERT INTO `tb_login_info` VALUES (231, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 09:05:39');
INSERT INTO `tb_login_info` VALUES (232, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 09:09:09');
INSERT INTO `tb_login_info` VALUES (233, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 09:14:54');
INSERT INTO `tb_login_info` VALUES (234, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 11:05:19');
INSERT INTO `tb_login_info` VALUES (235, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 11:30:06');
INSERT INTO `tb_login_info` VALUES (236, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 11:32:02');
INSERT INTO `tb_login_info` VALUES (237, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 12:00:45');
INSERT INTO `tb_login_info` VALUES (238, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 12:12:37');
INSERT INTO `tb_login_info` VALUES (239, '8923-丁聪华-食堂管理员', '127.0.0.1', '2020-02-23 12:13:50');
INSERT INTO `tb_login_info` VALUES (240, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 12:14:42');
INSERT INTO `tb_login_info` VALUES (241, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 12:37:26');
INSERT INTO `tb_login_info` VALUES (242, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 12:38:35');
INSERT INTO `tb_login_info` VALUES (243, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 12:55:56');
INSERT INTO `tb_login_info` VALUES (244, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 12:58:16');
INSERT INTO `tb_login_info` VALUES (245, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 13:04:13');
INSERT INTO `tb_login_info` VALUES (246, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 13:08:27');
INSERT INTO `tb_login_info` VALUES (247, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 14:04:42');
INSERT INTO `tb_login_info` VALUES (248, '8924-夏潇琦-超级管理员', '127.0.0.1', '2020-02-23 14:05:41');
INSERT INTO `tb_login_info` VALUES (249, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 14:06:07');
INSERT INTO `tb_login_info` VALUES (250, '8924-夏潇琦-超级管理员1', '127.0.0.1', '2020-02-23 14:06:46');
INSERT INTO `tb_login_info` VALUES (251, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 14:07:16');
INSERT INTO `tb_login_info` VALUES (252, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 14:32:26');
INSERT INTO `tb_login_info` VALUES (253, '8924-夏潇琦-教职工', '127.0.0.1', '2020-02-23 14:34:29');
INSERT INTO `tb_login_info` VALUES (254, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 14:42:27');
INSERT INTO `tb_login_info` VALUES (255, '8924-夏潇琦-教职工', '127.0.0.1', '2020-02-23 14:50:22');
INSERT INTO `tb_login_info` VALUES (256, '8924-夏潇琦-教职工', '127.0.0.1', '2020-02-23 15:26:09');
INSERT INTO `tb_login_info` VALUES (257, '8924-夏潇琦-教职工', '127.0.0.1', '2020-02-23 15:27:03');
INSERT INTO `tb_login_info` VALUES (258, '8924-夏潇琦-教职工', '127.0.0.1', '2020-02-23 15:34:26');
INSERT INTO `tb_login_info` VALUES (259, '8924-夏潇琦-教职工', '127.0.0.1', '2020-02-23 15:37:26');
INSERT INTO `tb_login_info` VALUES (260, '8924-夏潇琦-教职工', '127.0.0.1', '2020-02-23 15:42:22');
INSERT INTO `tb_login_info` VALUES (261, '8924-夏潇琦-教职工', '127.0.0.1', '2020-02-23 15:52:04');
INSERT INTO `tb_login_info` VALUES (262, '8924-夏潇琦-教职工', '127.0.0.1', '2020-02-23 15:57:23');
INSERT INTO `tb_login_info` VALUES (263, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 15:58:21');
INSERT INTO `tb_login_info` VALUES (264, '8925-曾帛员-教职工', '127.0.0.1', '2020-02-23 15:58:45');
INSERT INTO `tb_login_info` VALUES (265, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 15:59:52');
INSERT INTO `tb_login_info` VALUES (266, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 16:03:41');
INSERT INTO `tb_login_info` VALUES (267, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 16:04:50');
INSERT INTO `tb_login_info` VALUES (268, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 16:08:31');
INSERT INTO `tb_login_info` VALUES (269, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 16:22:14');
INSERT INTO `tb_login_info` VALUES (270, '8924-夏潇琦-教职工', '127.0.0.1', '2020-02-23 16:24:18');
INSERT INTO `tb_login_info` VALUES (271, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 16:24:31');
INSERT INTO `tb_login_info` VALUES (272, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 16:30:41');
INSERT INTO `tb_login_info` VALUES (273, '8924-夏潇琦-教职工', '127.0.0.1', '2020-02-23 16:31:19');
INSERT INTO `tb_login_info` VALUES (274, '8925-曾帛员-教职工', '127.0.0.1', '2020-02-23 16:33:37');
INSERT INTO `tb_login_info` VALUES (275, '8926-孙蝶妃-教职工', '127.0.0.1', '2020-02-23 16:34:23');
INSERT INTO `tb_login_info` VALUES (276, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 16:35:07');
INSERT INTO `tb_login_info` VALUES (277, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 16:37:09');
INSERT INTO `tb_login_info` VALUES (278, '8923-丁聪华-食堂管理员', '127.0.0.1', '2020-02-23 16:38:05');
INSERT INTO `tb_login_info` VALUES (279, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 16:38:35');
INSERT INTO `tb_login_info` VALUES (280, '8932-唐亚升-后勤处处长', '127.0.0.1', '2020-02-23 16:40:45');
INSERT INTO `tb_login_info` VALUES (281, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 16:41:13');
INSERT INTO `tb_login_info` VALUES (282, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 16:42:29');
INSERT INTO `tb_login_info` VALUES (283, '8923-丁聪华-食堂管理员', '127.0.0.1', '2020-02-23 16:57:02');
INSERT INTO `tb_login_info` VALUES (284, '8932-唐亚升-后勤处处长', '127.0.0.1', '2020-02-23 16:57:56');
INSERT INTO `tb_login_info` VALUES (285, '8888-演示人员-系统管理员', '127.0.0.1', '2020-02-23 16:58:17');
INSERT INTO `tb_login_info` VALUES (288, '8933-测试1-食堂管理员', '0:0:0:0:0:0:0:1', '2020-03-23 11:03:09');
INSERT INTO `tb_login_info` VALUES (289, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-23 11:03:55');
INSERT INTO `tb_login_info` VALUES (290, '8933-测试1-教职工', '0:0:0:0:0:0:0:1', '2020-03-23 11:04:15');
INSERT INTO `tb_login_info` VALUES (291, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-23 11:04:28');
INSERT INTO `tb_login_info` VALUES (292, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-23 14:07:21');
INSERT INTO `tb_login_info` VALUES (295, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-23 14:40:34');
INSERT INTO `tb_login_info` VALUES (296, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-23 14:50:51');
INSERT INTO `tb_login_info` VALUES (297, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-23 14:51:34');
INSERT INTO `tb_login_info` VALUES (298, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-23 14:55:54');
INSERT INTO `tb_login_info` VALUES (299, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-23 15:37:00');
INSERT INTO `tb_login_info` VALUES (300, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 03:03:37');
INSERT INTO `tb_login_info` VALUES (301, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 03:06:06');
INSERT INTO `tb_login_info` VALUES (302, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 03:06:43');
INSERT INTO `tb_login_info` VALUES (303, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 03:56:47');
INSERT INTO `tb_login_info` VALUES (304, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 04:13:05');
INSERT INTO `tb_login_info` VALUES (305, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 04:16:34');
INSERT INTO `tb_login_info` VALUES (306, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 04:26:16');
INSERT INTO `tb_login_info` VALUES (307, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 04:36:17');
INSERT INTO `tb_login_info` VALUES (308, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 04:42:16');
INSERT INTO `tb_login_info` VALUES (309, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 04:44:42');
INSERT INTO `tb_login_info` VALUES (310, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 04:45:23');
INSERT INTO `tb_login_info` VALUES (311, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 04:47:15');
INSERT INTO `tb_login_info` VALUES (312, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 05:27:37');
INSERT INTO `tb_login_info` VALUES (313, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 05:44:52');
INSERT INTO `tb_login_info` VALUES (314, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 05:51:35');
INSERT INTO `tb_login_info` VALUES (315, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 05:54:38');
INSERT INTO `tb_login_info` VALUES (316, '8924-夏潇琦-教职工', '0:0:0:0:0:0:0:1', '2020-03-24 06:04:49');
INSERT INTO `tb_login_info` VALUES (317, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 06:05:06');
INSERT INTO `tb_login_info` VALUES (318, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 06:20:54');
INSERT INTO `tb_login_info` VALUES (319, '8924-夏潇琦-教职工', '0:0:0:0:0:0:0:1', '2020-03-24 06:23:59');
INSERT INTO `tb_login_info` VALUES (320, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 06:24:47');
INSERT INTO `tb_login_info` VALUES (321, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 06:35:47');
INSERT INTO `tb_login_info` VALUES (324, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 06:45:47');
INSERT INTO `tb_login_info` VALUES (325, '8935-测试1-食堂管理员', '0:0:0:0:0:0:0:1', '2020-03-24 06:46:02');
INSERT INTO `tb_login_info` VALUES (326, '8924-夏潇琦-教职工', '0:0:0:0:0:0:0:1', '2020-03-24 06:46:16');
INSERT INTO `tb_login_info` VALUES (327, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 06:46:26');
INSERT INTO `tb_login_info` VALUES (328, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 06:55:46');
INSERT INTO `tb_login_info` VALUES (329, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-24 13:15:00');
INSERT INTO `tb_login_info` VALUES (330, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-25 05:07:17');
INSERT INTO `tb_login_info` VALUES (331, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-26 12:59:11');
INSERT INTO `tb_login_info` VALUES (332, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-26 13:05:40');
INSERT INTO `tb_login_info` VALUES (333, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-26 14:59:51');
INSERT INTO `tb_login_info` VALUES (334, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-27 05:07:54');
INSERT INTO `tb_login_info` VALUES (335, '8924-夏潇琦-教职工', '0:0:0:0:0:0:0:1', '2020-03-27 05:20:22');
INSERT INTO `tb_login_info` VALUES (336, '8924-夏潇琦-教职工', '0:0:0:0:0:0:0:1', '2020-03-27 05:20:53');
INSERT INTO `tb_login_info` VALUES (337, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-27 05:25:32');
INSERT INTO `tb_login_info` VALUES (338, '8924-夏潇琦-教职工', '0:0:0:0:0:0:0:1', '2020-03-27 05:26:20');
INSERT INTO `tb_login_info` VALUES (339, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-27 05:31:09');
INSERT INTO `tb_login_info` VALUES (340, '8923-丁聪华-食堂管理员', '0:0:0:0:0:0:0:1', '2020-03-27 05:31:26');
INSERT INTO `tb_login_info` VALUES (341, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-27 07:04:15');
INSERT INTO `tb_login_info` VALUES (342, '8932-唐亚升-后勤处处长', '0:0:0:0:0:0:0:1', '2020-03-27 07:05:19');
INSERT INTO `tb_login_info` VALUES (343, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-27 07:24:05');
INSERT INTO `tb_login_info` VALUES (344, '8924-夏潇琦-教职工', '0:0:0:0:0:0:0:1', '2020-03-27 07:24:32');
INSERT INTO `tb_login_info` VALUES (345, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-27 07:24:47');
INSERT INTO `tb_login_info` VALUES (346, '8923-丁聪华-食堂管理员', '0:0:0:0:0:0:0:1', '2020-03-27 07:25:11');
INSERT INTO `tb_login_info` VALUES (347, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-27 07:45:43');
INSERT INTO `tb_login_info` VALUES (348, '8924-夏潇琦-教职工', '0:0:0:0:0:0:0:1', '2020-03-27 08:03:34');
INSERT INTO `tb_login_info` VALUES (349, '8923-丁聪华-食堂管理员', '0:0:0:0:0:0:0:1', '2020-03-27 08:13:46');
INSERT INTO `tb_login_info` VALUES (350, '8932-唐亚升-后勤处处长', '0:0:0:0:0:0:0:1', '2020-03-27 08:14:00');
INSERT INTO `tb_login_info` VALUES (351, '8923-丁聪华-食堂管理员', '0:0:0:0:0:0:0:1', '2020-03-27 08:19:01');
INSERT INTO `tb_login_info` VALUES (352, '8932-唐亚升-后勤处处长', '0:0:0:0:0:0:0:1', '2020-03-27 08:23:02');
INSERT INTO `tb_login_info` VALUES (353, '8923-丁聪华-食堂管理员', '0:0:0:0:0:0:0:1', '2020-03-28 03:36:45');
INSERT INTO `tb_login_info` VALUES (354, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-28 03:48:51');
INSERT INTO `tb_login_info` VALUES (355, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-28 13:57:34');
INSERT INTO `tb_login_info` VALUES (356, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-29 04:38:03');
INSERT INTO `tb_login_info` VALUES (357, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-29 05:34:34');
INSERT INTO `tb_login_info` VALUES (358, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-29 06:20:54');
INSERT INTO `tb_login_info` VALUES (359, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-30 06:20:04');
INSERT INTO `tb_login_info` VALUES (360, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-31 04:46:53');
INSERT INTO `tb_login_info` VALUES (361, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-31 06:01:24');
INSERT INTO `tb_login_info` VALUES (362, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-31 07:32:56');
INSERT INTO `tb_login_info` VALUES (363, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-03-31 08:08:37');
INSERT INTO `tb_login_info` VALUES (364, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 00:43:37');
INSERT INTO `tb_login_info` VALUES (365, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 02:37:20');
INSERT INTO `tb_login_info` VALUES (366, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 04:31:39');
INSERT INTO `tb_login_info` VALUES (367, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 04:34:59');
INSERT INTO `tb_login_info` VALUES (368, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 04:36:02');
INSERT INTO `tb_login_info` VALUES (369, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 04:57:26');
INSERT INTO `tb_login_info` VALUES (370, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 04:58:18');
INSERT INTO `tb_login_info` VALUES (371, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 06:08:07');
INSERT INTO `tb_login_info` VALUES (372, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 06:45:44');
INSERT INTO `tb_login_info` VALUES (373, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 06:50:57');
INSERT INTO `tb_login_info` VALUES (374, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 08:11:23');
INSERT INTO `tb_login_info` VALUES (375, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 08:12:56');
INSERT INTO `tb_login_info` VALUES (376, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 08:53:19');
INSERT INTO `tb_login_info` VALUES (377, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 15:03:30');
INSERT INTO `tb_login_info` VALUES (378, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 15:12:32');
INSERT INTO `tb_login_info` VALUES (379, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-01 16:35:47');
INSERT INTO `tb_login_info` VALUES (380, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-02 01:16:57');
INSERT INTO `tb_login_info` VALUES (381, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-02 01:26:37');
INSERT INTO `tb_login_info` VALUES (382, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-02 06:46:30');
INSERT INTO `tb_login_info` VALUES (383, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-02 06:49:08');
INSERT INTO `tb_login_info` VALUES (384, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-02 06:50:14');
INSERT INTO `tb_login_info` VALUES (385, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-02 06:51:24');
INSERT INTO `tb_login_info` VALUES (386, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-02 06:57:33');
INSERT INTO `tb_login_info` VALUES (387, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-02 07:03:27');
INSERT INTO `tb_login_info` VALUES (388, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-02 07:30:44');
INSERT INTO `tb_login_info` VALUES (389, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-02 07:34:47');
INSERT INTO `tb_login_info` VALUES (390, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-02 07:35:11');
INSERT INTO `tb_login_info` VALUES (391, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-02 09:59:15');
INSERT INTO `tb_login_info` VALUES (392, '8923-丁聪华-食堂管理员', '0:0:0:0:0:0:0:1', '2020-04-02 11:11:51');
INSERT INTO `tb_login_info` VALUES (393, '8924-夏潇琦-教职工', '0:0:0:0:0:0:0:1', '2020-04-02 11:12:07');
INSERT INTO `tb_login_info` VALUES (394, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-02 11:19:48');
INSERT INTO `tb_login_info` VALUES (395, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-02 11:25:45');
INSERT INTO `tb_login_info` VALUES (396, '8923-丁聪华-食堂管理员', '0:0:0:0:0:0:0:1', '2020-04-02 11:55:22');
INSERT INTO `tb_login_info` VALUES (397, '8924-夏潇琦-教职工', '0:0:0:0:0:0:0:1', '2020-04-02 11:58:48');
INSERT INTO `tb_login_info` VALUES (398, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-02 12:04:39');
INSERT INTO `tb_login_info` VALUES (399, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-03 05:27:41');
INSERT INTO `tb_login_info` VALUES (400, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-03 05:41:06');
INSERT INTO `tb_login_info` VALUES (401, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-03 05:42:07');
INSERT INTO `tb_login_info` VALUES (402, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-03 05:51:56');
INSERT INTO `tb_login_info` VALUES (403, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-03 05:54:32');
INSERT INTO `tb_login_info` VALUES (404, '8888-演示人员-系统管理员', '0:0:0:0:0:0:0:1', '2020-04-03 06:14:31');
INSERT INTO `tb_login_info` VALUES (405, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-10 05:08:56');
INSERT INTO `tb_login_info` VALUES (406, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-10 05:11:55');
INSERT INTO `tb_login_info` VALUES (407, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-10 05:48:28');
INSERT INTO `tb_login_info` VALUES (408, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-10 12:02:45');
INSERT INTO `tb_login_info` VALUES (409, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-10 12:58:08');
INSERT INTO `tb_login_info` VALUES (410, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-10 13:47:57');
INSERT INTO `tb_login_info` VALUES (411, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-10 15:14:32');
INSERT INTO `tb_login_info` VALUES (412, '8931-赵美珍-食堂管理员', '127.0.0.1', '2020-04-10 15:22:59');
INSERT INTO `tb_login_info` VALUES (413, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-10 15:23:25');
INSERT INTO `tb_login_info` VALUES (414, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-11 03:50:43');
INSERT INTO `tb_login_info` VALUES (415, '8923-丁聪华-食堂管理员', '127.0.0.1', '2020-04-11 03:51:24');
INSERT INTO `tb_login_info` VALUES (416, '8932-唐亚升-后勤处处长', '127.0.0.1', '2020-04-11 03:53:09');
INSERT INTO `tb_login_info` VALUES (417, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-11 04:27:43');
INSERT INTO `tb_login_info` VALUES (418, '8932-唐亚升-后勤处处长', '127.0.0.1', '2020-04-11 04:47:21');
INSERT INTO `tb_login_info` VALUES (419, '8923-丁聪华-食堂管理员', '127.0.0.1', '2020-04-11 04:48:41');
INSERT INTO `tb_login_info` VALUES (420, '8923-丁聪华-食堂管理员', '127.0.0.1', '2020-04-11 04:57:39');
INSERT INTO `tb_login_info` VALUES (421, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-11 04:57:48');
INSERT INTO `tb_login_info` VALUES (422, '8923-丁聪华-食堂管理员', '127.0.0.1', '2020-04-11 05:00:05');
INSERT INTO `tb_login_info` VALUES (423, '8924-夏潇琦-教职工', '127.0.0.1', '2020-04-11 05:00:16');
INSERT INTO `tb_login_info` VALUES (424, '8923-丁聪华-食堂管理员', '127.0.0.1', '2020-04-11 05:16:05');
INSERT INTO `tb_login_info` VALUES (425, '8932-唐亚升-后勤处处长', '127.0.0.1', '2020-04-11 05:16:19');
INSERT INTO `tb_login_info` VALUES (426, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-11 05:16:37');
INSERT INTO `tb_login_info` VALUES (427, '8924-夏潇琦-教职工', '127.0.0.1', '2020-04-11 05:17:39');
INSERT INTO `tb_login_info` VALUES (428, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-11 05:17:51');
INSERT INTO `tb_login_info` VALUES (429, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-11 06:06:23');
INSERT INTO `tb_login_info` VALUES (430, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-11 07:32:32');
INSERT INTO `tb_login_info` VALUES (431, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-11 08:02:13');
INSERT INTO `tb_login_info` VALUES (432, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-11 08:03:15');
INSERT INTO `tb_login_info` VALUES (433, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-11 09:16:22');
INSERT INTO `tb_login_info` VALUES (434, '8924-夏潇琦-教职工', '127.0.0.1', '2020-04-11 09:17:00');
INSERT INTO `tb_login_info` VALUES (435, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-11 09:58:20');
INSERT INTO `tb_login_info` VALUES (436, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-11 11:42:51');
INSERT INTO `tb_login_info` VALUES (437, '8888-演示人员-系统管理员', '127.0.0.1', '2020-04-11 13:46:35');

-- ----------------------------
-- Table structure for tb_material
-- ----------------------------
DROP TABLE IF EXISTS `tb_material`;
CREATE TABLE `tb_material`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `material_num` int(11) DEFAULT NULL COMMENT '数量',
  `material_unit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位',
  `material_category_id` int(11) DEFAULT NULL COMMENT '食材类别',
  `expiration_date` datetime DEFAULT NULL COMMENT '保质期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `material_img_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '食材图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_material
-- ----------------------------
INSERT INTO `tb_material` VALUES (7, '大米', 110, '袋', 5, '2021-02-21 16:00:00', '2020-02-22 07:22:48', '大米', '2020-02-22/7f1574cdc87246d19977e0ed4d0bd530.jpg');
INSERT INTO `tb_material` VALUES (8, '面粉', 108, '袋', 5, '2021-02-23 16:00:00', '2020-02-22 07:23:29', '面粉', '2020-02-22/8acec9f9494a463fba51f61124d83bc0.jpg');
INSERT INTO `tb_material` VALUES (9, '香菇', 50, '袋', 6, '2022-02-21 16:00:00', '2020-02-22 07:24:08', '香菇', '2020-02-22/70512eb5799741f5b8d777170cfba753.jpg');
INSERT INTO `tb_material` VALUES (10, '紫菜', 30, '袋', 6, '2023-02-21 16:00:00', '2020-02-22 07:24:42', '', '2020-02-22/c2e4f3dafb8540e5abe00f3ce33f63f3.jpg');
INSERT INTO `tb_material` VALUES (11, '芝麻', 50, '袋', 9, '2022-02-21 19:14:13', '2020-02-22 07:25:25', '', '2020-02-22/4a8beac40ada482fb0598921e4cb9e56.jpg');
INSERT INTO `tb_material` VALUES (12, '牛奶', 73, '箱', 10, '2022-02-21 16:00:00', '2020-02-22 07:25:54', '', '2020-02-22/08420facddf148aa88fc686169f754c0.jpg');
INSERT INTO `tb_material` VALUES (13, '鸡蛋', 120, '叠', 10, '2020-04-19 16:00:00', '2020-02-22 07:26:32', '', '2020-02-22/5a16ec8594ff4f38b99a02b7101cf11a.jpg');
INSERT INTO `tb_material` VALUES (14, '猪肉', 40, '斤', 7, '2020-02-23 16:00:00', '2020-02-22 07:27:33', '', '2020-02-22/3c51cc5e79b04d4ca2ad4b009ff9d386.jpg');
INSERT INTO `tb_material` VALUES (15, '萝卜', 90, '斤', 2, '2020-02-28 16:00:00', '2020-02-22 07:28:42', '', '2020-02-22/fb8901c68fc4463fbebe95f52af0af18.jpg');
INSERT INTO `tb_material` VALUES (16, '白菜', 108, '斤', 2, '2020-02-22 16:00:00', '2020-02-22 07:29:11', '', '2020-02-22/b70255047d6940c690fda3f1ec0c4881.jpg');
INSERT INTO `tb_material` VALUES (17, '花生油', 20, '罐', 1, '2022-02-21 16:00:00', '2020-02-22 07:29:49', '', '2020-02-22/7fb3f8bca11d41d49d304b69f1b40653.jpg');
INSERT INTO `tb_material` VALUES (18, '盐', 30, '斤', 1, '2024-02-21 16:00:00', '2020-02-22 07:30:25', '', '2020-02-22/b6bebf3eaabf4b5587460d8d60e138e0.jpg');

-- ----------------------------
-- Table structure for tb_material_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_material_category`;
CREATE TABLE `tb_material_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material_category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '食材类别名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_material_category
-- ----------------------------
INSERT INTO `tb_material_category` VALUES (1, '粮油类');
INSERT INTO `tb_material_category` VALUES (2, '蔬菜类');
INSERT INTO `tb_material_category` VALUES (3, '鱼肉类');
INSERT INTO `tb_material_category` VALUES (4, '水果类');
INSERT INTO `tb_material_category` VALUES (5, '谷薯类');
INSERT INTO `tb_material_category` VALUES (6, '菌藻类');
INSERT INTO `tb_material_category` VALUES (7, '畜禽类');
INSERT INTO `tb_material_category` VALUES (8, '水产类');
INSERT INTO `tb_material_category` VALUES (9, '坚果类');
INSERT INTO `tb_material_category` VALUES (10, '奶蛋类');

-- ----------------------------
-- Table structure for tb_outstorage
-- ----------------------------
DROP TABLE IF EXISTS `tb_outstorage`;
CREATE TABLE `tb_outstorage`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `outstorage_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出库编号(同一单保持一致)',
  `material_id` int(11) NOT NULL COMMENT '食材id',
  `outstorage_num` int(11) DEFAULT NULL COMMENT '出库数量',
  `outstorage_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '出库单位',
  `outstorage_reason` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '出库原因',
  `outstorage_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '出库负责人',
  `receiver` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货人',
  `outstorage_time` datetime DEFAULT NULL COMMENT '出库时间',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `outstorage_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '出库状态（1 出库中 2 已出库）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_outstorage
-- ----------------------------
INSERT INTO `tb_outstorage` VALUES (2, '15bc644d018042908fb002fa61e3b4441582444691186', 17, 1, '罐', '炒菜需要', '演示人员', '小李', '2020-02-23 07:58:04', '1', '1');
INSERT INTO `tb_outstorage` VALUES (3, '15bc644d018042908fb002fa61e3b4441582444691186', 18, 10, '罐', '炒菜需要', '演示人员', '小李子', '2020-02-23 07:58:21', '2', '2');
INSERT INTO `tb_outstorage` VALUES (4, 'f5145fc8862d4252a73407f675b8d72f1582444720430', 15, 10, '罐', '炒菜需要', '演示人员', '小李', '2020-02-23 07:58:38', '3', '2');
INSERT INTO `tb_outstorage` VALUES (5, '2666e59872a04dc787055bc7ff730d921582446556364', 12, 2, '箱', '炒菜需要', '演示人员', '小李子', '2020-02-23 08:29:14', '', '2');
INSERT INTO `tb_outstorage` VALUES (6, '576c5cee63b949d7965b274b7b0d6fca1582462128154', 11, 8, '斤', '炒菜需要', '演示人员', '小李', '2020-02-23 12:48:44', '无', '2');
INSERT INTO `tb_outstorage` VALUES (7, 'e45c0fa5677940fd8f12739ec1ad2fa21582463624236', 18, 10, '袋', '炒菜需要', '演示人员', '小李子', '2020-02-23 13:13:42', '', '2');
INSERT INTO `tb_outstorage` VALUES (8, 'f3521ef974db4fcca2a05db4ac6b609a1582476794825', 17, 10, '罐', '炒菜需要', '演示人员', '小李子', '2020-02-01 16:53:12', '111', '2');
INSERT INTO `tb_outstorage` VALUES (9, '7aeb0d8007324663a7671a7e72f5e6c31584961163724', 16, 8, '斤', '炒菜需要', '演示人员', '小李', '2020-03-23 10:59:21', '11111222', '2');
INSERT INTO `tb_outstorage` VALUES (10, 'a206c9c5c8ff4b849b5533837ebac7361586532008260', 13, 10, '箱', '制作', '演示人员', '小李', '2020-04-10 15:20:05', '111', '2');
INSERT INTO `tb_outstorage` VALUES (11, 'e1a67b0e94b34cc18c55c87dacf8e33c1586581922292', 12, 10, '箱', '制作奶黄包', '演示人员', '李四', '2020-04-11 05:11:47', '让李四过来拿食材', '2');
INSERT INTO `tb_outstorage` VALUES (12, 'bccc750f7ff84ca1962e22c2e48fd0a41586596995167', 12, 10, '箱', '制作菜谱', '演示人员', '张三', '2020-04-11 09:23:11', 'qqqq', '2');

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission`  (
  `permission_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `permission_pid` int(10) DEFAULT NULL COMMENT '父级菜单编号',
  `permission_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限类型【menu】',
  `permission_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单名称',
  `permission_icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `permission_href` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单跳转地址',
  `permission_spread` int(1) DEFAULT NULL COMMENT '是否展开【0 不展开 1展开】',
  `permission_percode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限编码【预留】',
  `permission_order_num` int(4) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES (1, 0, 'menu', '学院教工饭堂系统管理', NULL, NULL, 0, NULL, 1);
INSERT INTO `tb_permission` VALUES (2, 14, 'menu', '教工系别管理', '&#xe613;', '/sys/toSectionManager', 0, NULL, 3);
INSERT INTO `tb_permission` VALUES (3, 14, 'menu', '教工管理', '&#xe770;', '/sys/toFacultyManager', 0, '', 4);
INSERT INTO `tb_permission` VALUES (4, 1, 'menu', '饭堂信息管理', '&#xe62e;', NULL, 0, NULL, 5);
INSERT INTO `tb_permission` VALUES (5, 1, 'menu', '系统管理', '&#xe631;', NULL, 0, NULL, 36);
INSERT INTO `tb_permission` VALUES (7, 5, 'menu', '角色管理', '&#xe672;', '/sys/toRoleManager', 0, NULL, 8);
INSERT INTO `tb_permission` VALUES (9, 5, 'menu', '登录日志管理', '&#xe60e;', '/sys/toLoginInfoManager', 0, NULL, 10);
INSERT INTO `tb_permission` VALUES (11, 4, 'menu', '饭堂部门管理', '&#xe613;', '/sys/toDeptManager', 0, NULL, 11);
INSERT INTO `tb_permission` VALUES (12, 4, 'menu', '饭堂员工管理', '&#xe612;', '/sys/toStaffManager', 0, NULL, 12);
INSERT INTO `tb_permission` VALUES (13, 5, 'menu', '图标管理', '&#xe60c;', '/sys/toIconManager', 0, NULL, 13);
INSERT INTO `tb_permission` VALUES (14, 1, 'menu', '学院信息管理', '&#xe62e;', NULL, 0, NULL, 2);
INSERT INTO `tb_permission` VALUES (15, 2, 'permission', '查看教工系别管理', NULL, NULL, NULL, 'section:view', 14);
INSERT INTO `tb_permission` VALUES (16, 3, 'permission', '查看教工管理', NULL, NULL, NULL, 'faculty:view', 15);
INSERT INTO `tb_permission` VALUES (17, 7, 'permission', '查看角色管理', NULL, NULL, NULL, 'role:view', 16);
INSERT INTO `tb_permission` VALUES (18, 9, 'permission', '查看登录日志管理', NULL, NULL, NULL, 'loginInfo:view', 17);
INSERT INTO `tb_permission` VALUES (19, 11, 'permission', '查看饭堂部门管理', NULL, NULL, NULL, 'dept:view', 18);
INSERT INTO `tb_permission` VALUES (20, 12, 'permission', '查看饭堂员工管理', NULL, NULL, NULL, 'staff:view', 19);
INSERT INTO `tb_permission` VALUES (21, 13, 'permission', '查看图标管理', NULL, NULL, NULL, 'icon:view', 20);
INSERT INTO `tb_permission` VALUES (35, 1, 'menu', '食堂管理', '&#xe62e;', NULL, 0, NULL, 6);
INSERT INTO `tb_permission` VALUES (36, 1, 'menu', '食品管理', '&#xe62e;', NULL, 0, NULL, 7);
INSERT INTO `tb_permission` VALUES (37, 35, 'menu', '食材类别管理', '&#xe857;', '/bus/toMaterialCategoryManager', 0, '', 21);
INSERT INTO `tb_permission` VALUES (38, 35, 'menu', '食材库存管理', '&#xe656;', '/bus/toMaterialManager', 0, '', 22);
INSERT INTO `tb_permission` VALUES (39, 35, 'menu', '食材采购管理', '&#xe657;', '/bus/toPurchaseManager', 0, '', 23);
INSERT INTO `tb_permission` VALUES (40, 35, 'menu', '食材入库管理', '&#xe65b;', '/bus/toPutStorageManager', 0, '', 24);
INSERT INTO `tb_permission` VALUES (41, 36, 'menu', '食品菜谱类别管理', '&#xe63c;', '/bus/toRecipeCategoryManager', 0, '', 25);
INSERT INTO `tb_permission` VALUES (42, 36, 'menu', '食品菜谱管理', '&#xe705;', '/bus/toRecipeManager', 0, '', 26);
INSERT INTO `tb_permission` VALUES (43, 37, 'permission', '查看食材类别管理', NULL, NULL, NULL, 'materialCategory:view', 27);
INSERT INTO `tb_permission` VALUES (44, 38, 'permission', '查看食材库存管理', NULL, NULL, NULL, 'material:view', 28);
INSERT INTO `tb_permission` VALUES (45, 39, 'permission', '查看食材采购管理', '', '', NULL, 'purchase:view', 29);
INSERT INTO `tb_permission` VALUES (46, 40, 'permission', '查看食材入库管理', NULL, NULL, NULL, 'putStorage:view', 30);
INSERT INTO `tb_permission` VALUES (47, 41, 'permission', '查看食品菜谱类别管理', NULL, NULL, NULL, 'recipeCategory:view', 32);
INSERT INTO `tb_permission` VALUES (48, 42, 'permission', '查看食品菜谱管理', NULL, NULL, NULL, 'recipe:view', 33);
INSERT INTO `tb_permission` VALUES (49, 35, 'menu', '食材出库管理', '&#xe65a;', '/bus/toOutStorageManager', 0, NULL, 34);
INSERT INTO `tb_permission` VALUES (50, 49, 'permission', '查看食材出库管理', NULL, NULL, NULL, 'outStorage:view', 35);
INSERT INTO `tb_permission` VALUES (51, 1, 'menu', '统计管理', '&#xe62e;', NULL, 0, NULL, 9);
INSERT INTO `tb_permission` VALUES (52, 51, 'menu', '当日食材库存统计', '&#xe62c;', '/bus/toTodayStatistics', 0, NULL, 37);
INSERT INTO `tb_permission` VALUES (53, 51, 'menu', '食材出库统计', '&#xe62c;', '/bus/toOutstorageStatistics', 0, NULL, 38);
INSERT INTO `tb_permission` VALUES (54, 52, 'permission', '查看当日食材库存统计', NULL, NULL, NULL, 'todayStatistics:view', 39);
INSERT INTO `tb_permission` VALUES (55, 53, 'permission', '查看食材出库统计', NULL, NULL, NULL, 'outstorageStatistics:view', 40);
INSERT INTO `tb_permission` VALUES (56, 36, 'menu', '食品菜谱公布管理', '&#xe705;', '/bus/toRecipeViewManager', 0, NULL, 41);
INSERT INTO `tb_permission` VALUES (57, 36, 'menu', '食品菜谱评论管理', '&#xe66c;', '/bus/toViewCommentManager', 0, NULL, 42);
INSERT INTO `tb_permission` VALUES (58, 57, 'permission', '查看菜谱评论管理', NULL, NULL, NULL, 'viewComment:view', 43);
INSERT INTO `tb_permission` VALUES (59, 56, 'permission', '查看食品菜谱公布管理', NULL, NULL, NULL, 'viewRecipeManager:view', 44);
INSERT INTO `tb_permission` VALUES (60, 1, 'menu', '公布的菜谱信息', '&#xe705;', '/bus/toRecipeView', 0, NULL, 45);

-- ----------------------------
-- Table structure for tb_purchase
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase`;
CREATE TABLE `tb_purchase`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '采购编号(同一单保持一致)',
  `material_id` int(11) NOT NULL COMMENT '食材id',
  `purchase_num` int(11) NOT NULL COMMENT '采购数量',
  `purchase_price` decimal(10, 2) DEFAULT NULL COMMENT '预计采购价格',
  `purchase_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '采购单位',
  `purchase_company` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '供应商',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `purchase_auditor` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '采购单创建人',
  `purchase_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '状态（0，已放弃，1，待采购，2采购中，3，已入库）',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_purchase
-- ----------------------------
INSERT INTO `tb_purchase` VALUES (11, '3605284e48b1406498cb68ad26b350961582431896973', 17, 10, 50.00, '罐', '天天超市', '2020-02-23 04:24:50', '演示人员', '2', '1');
INSERT INTO `tb_purchase` VALUES (12, '3605284e48b1406498cb68ad26b350961582431896973', 18, 10, 30.00, '袋', '广州大米有限公司', '2020-02-23 04:25:12', '演示人员', '3', '2');
INSERT INTO `tb_purchase` VALUES (13, '8735175bf72e4dea8c3d109c7ff543a01582431949428', 12, 11, 50.00, '箱', '广州大米有限公司', '2020-02-23 04:25:46', '演示人员', '3', '3');
INSERT INTO `tb_purchase` VALUES (14, '1f15199357ff4b7687281a72772144ea1582440872404', 7, 10, 50.00, '袋', '天天超市', '2020-02-23 06:54:25', '演示人员', '3', '大米');
INSERT INTO `tb_purchase` VALUES (16, '7110f2beaea142a4b3961e36d457145e1582446377073', 12, 10, 25.00, '箱', '天天超市', '2020-02-23 08:26:12', '演示人员', '3', '');
INSERT INTO `tb_purchase` VALUES (18, 'f1185855a1c441f4bd7e7646637e3d871582461892069', 9, 10, 5.50, '斤', '天天超市', '2020-02-23 12:44:47', '演示人员', '2', '无');
INSERT INTO `tb_purchase` VALUES (19, 'f1185855a1c441f4bd7e7646637e3d871582461892069', 11, 10, 10.00, '斤', '天天超市', '2020-02-23 12:45:17', '演示人员', '3', '无');
INSERT INTO `tb_purchase` VALUES (20, '376febb0de55468bb3efc229d3151bd31582463529224', 18, 10, 50.00, '袋', '天天超市', '2020-02-23 13:12:08', '演示人员', '3', '');
INSERT INTO `tb_purchase` VALUES (21, '7cb22e335c1841b6915457afb8abac0a1582476626259', 9, 10, 50.00, '罐', '天天超市', '2020-02-23 16:50:22', '演示人员', '2', '113');
INSERT INTO `tb_purchase` VALUES (22, '7cb22e335c1841b6915457afb8abac0a1582476626259', 17, 10, 30.00, '罐', '天天超市', '2020-02-23 16:50:38', '演示人员', '3', '333');
INSERT INTO `tb_purchase` VALUES (23, '8d65d004144a4e31b80b731b36c74a071584960871967', 16, 10, 30.00, '斤', '天天超市', '2020-03-23 10:54:25', '演示人员', '3', '11111111');
INSERT INTO `tb_purchase` VALUES (24, '8d65d004144a4e31b80b731b36c74a071584960871967', 15, 10, 50.00, '斤', '天天超市', '2020-03-23 10:54:52', '演示人员', '2', '2222222');
INSERT INTO `tb_purchase` VALUES (26, '83d11fcdfb1844d694f69871b9c9548e1584972803042', 16, 10, 50.00, '斤', '天天超市', '2020-03-23 14:13:47', '演示人员', '3', '22222');
INSERT INTO `tb_purchase` VALUES (30, '3afd8674892e417599ca51be3c2fe40e1585640428016', 17, 10, 30.00, '罐', '天天超市', '2020-03-31 07:40:25', '演示人员', '3', '111');
INSERT INTO `tb_purchase` VALUES (31, 'd98b848145654a90bc287911c9179d1b1585755275644', 13, 100, 1.00, '个', '天天超市', '2020-04-01 15:33:53', '演示人员', '1', '采购鸡蛋1111111');
INSERT INTO `tb_purchase` VALUES (32, '56dce9db73f14a7ebc63c8f79125f02a1586531820326', 13, 100, 1.00, '箱', '天天超市', '2020-04-10 15:16:56', '演示人员', '3', '11111');
INSERT INTO `tb_purchase` VALUES (33, '56dce9db73f14a7ebc63c8f79125f02a1586531820326', 12, 100, 2.00, '箱', '天天超市', '2020-04-10 15:17:33', '演示人员', '3', '1111');
INSERT INTO `tb_purchase` VALUES (34, '3e12378d2f0f4cae81bb976eb12f9f921586581490685', 12, 10, 30.00, '箱', '天天超市', '2020-04-11 05:04:36', '演示人员', '3', '让张三去采购');
INSERT INTO `tb_purchase` VALUES (35, '3e12378d2f0f4cae81bb976eb12f9f921586581490685', 13, 50, 30.00, '叠', '天天超市', '2020-04-11 05:05:33', '演示人员', '1', '让张三去采购食材');
INSERT INTO `tb_purchase` VALUES (36, '7071a8f1cda440418454884f8632d0971586596729896', 12, 10, 25.00, '箱', '天天超市', '2020-04-11 09:18:40', '演示人员', '3', '让张三去采购');
INSERT INTO `tb_purchase` VALUES (37, '7071a8f1cda440418454884f8632d0971586596729896', 13, 50, 30.00, '叠', '天天超市', '2020-04-11 09:19:26', '演示人员', '3', '让张三去采购');

-- ----------------------------
-- Table structure for tb_putstorage
-- ----------------------------
DROP TABLE IF EXISTS `tb_putstorage`;
CREATE TABLE `tb_putstorage`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `putstorage_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '入库编号(同一单保持一致)',
  `material_id` int(11) DEFAULT NULL COMMENT '食材id',
  `putstorage_num` int(11) DEFAULT NULL COMMENT '数量',
  `putstorage_price` decimal(10, 2) DEFAULT NULL COMMENT '采购的价格',
  `putstorage_money` decimal(10, 2) DEFAULT NULL COMMENT '小计',
  `putstorage_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位',
  `purchase_company` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '生产单位',
  `purchase_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '采购者',
  `purchase_id` int(11) DEFAULT NULL COMMENT '采购单',
  `putstorage_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '入库接收者',
  `create_time` datetime DEFAULT NULL COMMENT '入库时间',
  `putstorage_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '状态（0，已放弃，1采购中，2已入库）',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_putstorage
-- ----------------------------
INSERT INTO `tb_putstorage` VALUES (6, '3605284e48b1406498cb68ad26b350961582431896973', 17, 10, 50.00, 500.00, '罐', '天天超市', '演示人员', 11, '', '2020-02-23 12:54:40', '1', '1');
INSERT INTO `tb_putstorage` VALUES (7, '3605284e48b1406498cb68ad26b350961582431896973', 18, 10, 30.00, 300.00, '袋', '广州大米有限公司', '演示人员', 12, '演示人员', '2020-02-23 04:25:12', '2', '2');
INSERT INTO `tb_putstorage` VALUES (8, '8735175bf72e4dea8c3d109c7ff543a01582431949428', 12, 10, 50.50, 505.00, '箱', '广州大米有限公司', '演示人员', 13, '演示人员', '2020-02-23 06:27:13', '2', '3');
INSERT INTO `tb_putstorage` VALUES (9, '1f15199357ff4b7687281a72772144ea1582440872404', 7, 10, 50.00, 500.00, '袋', '天天超市', '演示人员', 14, '演示人员', '2020-02-23 06:57:14', '2', '大米');
INSERT INTO `tb_putstorage` VALUES (11, '7110f2beaea142a4b3961e36d457145e1582446377073', 12, 5, 25.00, 125.00, '箱', '天天超市', '演示人员', 16, '演示人员', '2020-02-23 08:28:07', '2', '');
INSERT INTO `tb_putstorage` VALUES (13, 'f1185855a1c441f4bd7e7646637e3d871582461892069', 9, 10, 5.50, 55.00, '斤', '天天超市', '演示人员', 18, '', NULL, '1', '无');
INSERT INTO `tb_putstorage` VALUES (14, 'f1185855a1c441f4bd7e7646637e3d871582461892069', 11, 8, 10.00, 80.00, '斤', '天天超市', '演示人员', 19, '演示人员', '2020-02-23 12:46:53', '2', '无');
INSERT INTO `tb_putstorage` VALUES (15, '376febb0de55468bb3efc229d3151bd31582463529224', 18, 10, 50.00, 500.00, '袋', '天天超市', '演示人员', 20, '演示人员', '2020-02-23 13:12:54', '2', '');
INSERT INTO `tb_putstorage` VALUES (16, '7cb22e335c1841b6915457afb8abac0a1582476626259', 9, 10, 50.00, 500.00, '罐', '天天超市', '演示人员', 21, '', NULL, '1', '11');
INSERT INTO `tb_putstorage` VALUES (17, '7cb22e335c1841b6915457afb8abac0a1582476626259', 17, 10, 30.00, 300.00, '罐', '天天超市', '演示人员', 22, '演示人员', '2020-02-23 16:52:27', '2', '33');
INSERT INTO `tb_putstorage` VALUES (18, '8d65d004144a4e31b80b731b36c74a071584960871967', 16, 8, 30.00, 240.00, '斤', '天天超市', '演示人员', 23, '演示人员', '2020-03-23 10:58:36', '2', '111111112222');
INSERT INTO `tb_putstorage` VALUES (19, '8d65d004144a4e31b80b731b36c74a071584960871967', 15, 10, 50.00, 500.00, '斤', '天天超市', '演示人员', 24, '', NULL, '1', '2222222');
INSERT INTO `tb_putstorage` VALUES (21, '83d11fcdfb1844d694f69871b9c9548e1584972803042', 16, 8, 40.00, 320.00, '斤', '天天超市', '演示人员', 26, '演示人员', '2020-03-23 14:16:53', '2', '2222233333');
INSERT INTO `tb_putstorage` VALUES (25, '3afd8674892e417599ca51be3c2fe40e1585640428016', 17, 10, 30.00, 300.00, '罐', '天天超市', '演示人员', 30, '演示人员', '2020-03-31 07:40:41', '2', '111');
INSERT INTO `tb_putstorage` VALUES (26, 'd98b848145654a90bc287911c9179d1b1585755275644', 13, 100, 1.00, 100.00, '个', '天天超市', '演示人员', 31, '', NULL, '1', '采购鸡蛋');
INSERT INTO `tb_putstorage` VALUES (27, '56dce9db73f14a7ebc63c8f79125f02a1586531820326', 13, 85, 1.00, 85.00, '箱', '天天超市', '演示人员', 32, '演示人员', '2020-04-10 15:19:21', '2', '11111');
INSERT INTO `tb_putstorage` VALUES (28, '56dce9db73f14a7ebc63c8f79125f02a1586531820326', 12, 50, 2.00, 100.00, '箱', '天天超市', '演示人员', 33, '演示人员', '2020-04-10 15:18:33', '2', '1111');
INSERT INTO `tb_putstorage` VALUES (29, '3e12378d2f0f4cae81bb976eb12f9f921586581490685', 12, 10, 30.00, 300.00, '箱', '天天超市', '演示人员', 34, '演示人员', '2020-04-11 05:10:29', '2', '让张三去采购');
INSERT INTO `tb_putstorage` VALUES (30, '3e12378d2f0f4cae81bb976eb12f9f921586581490685', 13, 10, 20.00, 200.00, '叠', '天天超市', '演示人员', 35, '', '2020-04-11 05:10:04', '1', '让张三去采购食材');
INSERT INTO `tb_putstorage` VALUES (31, '7071a8f1cda440418454884f8632d0971586596729896', 12, 10, 25.00, 250.00, '箱', '天天超市', '演示人员', 36, '演示人员', '2020-04-11 09:22:11', '2', '让张三去采购');
INSERT INTO `tb_putstorage` VALUES (32, '7071a8f1cda440418454884f8632d0971586596729896', 13, 30, 30.00, 900.00, '叠', '天天超市', '演示人员', 37, '演示人员', '2020-04-11 09:21:15', '2', '入库中');

-- ----------------------------
-- Table structure for tb_recipe
-- ----------------------------
DROP TABLE IF EXISTS `tb_recipe`;
CREATE TABLE `tb_recipe`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜品名称',
  `recipe_price` decimal(10, 0) DEFAULT NULL COMMENT '菜品价格',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `recipe_category_id` int(11) DEFAULT NULL COMMENT '菜品类别',
  `recipe_img_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜品图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_recipe
-- ----------------------------
INSERT INTO `tb_recipe` VALUES (1, '鸡蛋肠粉', 5, '2020-02-22 09:25:02', '2个鸡蛋、适量小麦淀粉', 2, '2020-02-22/56b8bb459f9a4504ab1be21233222e15.jpg');
INSERT INTO `tb_recipe` VALUES (2, '熊猫奶黄包', 10, '2020-02-22 09:17:34', '2个蛋黄、160ml牛奶、适量面粉、酵母、黑芝麻', 2, '2020-02-22/35059d4534ee42fdb395e3d26490d642.jpg');
INSERT INTO `tb_recipe` VALUES (3, '青菜鸡蛋面条', 10, '2020-02-22 09:19:05', '1把面条、2个鸡蛋、少许菠菜、半根玉米', 3, '2020-02-22/647017d937c84f288d396292e0ab8295.jpg');
INSERT INTO `tb_recipe` VALUES (4, '香葱鸡蛋面', 12, '2020-02-22 09:19:38', '1把细面条、1个水煮蛋、适量佐料', 3, '2020-02-22/cf7da0847b014d7d9e0fda1043782c17.jpg');
INSERT INTO `tb_recipe` VALUES (5, '皮蛋香肠粥', 6, '2020-02-22 09:20:11', '适量大米、1个皮蛋、1小段香肠', 4, '2020-02-22/fc1e5bc9982c4f548b99ddb23e6dbef4.jpg');
INSERT INTO `tb_recipe` VALUES (6, '牛肉粥', 8, '2020-02-22 09:21:03', '适量大米、100克牛肉', 4, '2020-02-22/ec20356596654f31b5e41864d1ffda41.jpg');
INSERT INTO `tb_recipe` VALUES (7, '鲜肉小馄饨', 15, '2020-02-22 09:21:31', '适量鸡蛋、适量里脊肉', 5, '2020-02-22/e323ebcc444440e08bb80fae6f5ff243.jpg');
INSERT INTO `tb_recipe` VALUES (8, '冰糖葫芦', 4, '2020-02-22 09:22:10', '适量山楂、适量白糖', 6, '2020-02-22/cb3f8cf21fcc48c185a5f8b108b04902.jpg');
INSERT INTO `tb_recipe` VALUES (9, '蜜三刀', 15, '2020-02-22 09:22:40', '', 6, '2020-02-22/95329bd127cb4824aa4849ac82e095c9.jpg');
INSERT INTO `tb_recipe` VALUES (10, '蛋炒饭', 12, '2020-02-22 09:23:14', '', 7, '2020-02-22/f2bdb9d0c529482f8950d07137fee711.jpg');
INSERT INTO `tb_recipe` VALUES (11, '牛肉泡椒炒饭', 18, '2020-02-22 09:23:44', '', 7, '2020-02-22/9ac3f47ce1f74a26a3cb5c1ee6ad7dab.jpg');
INSERT INTO `tb_recipe` VALUES (12, '鸡蛋酱油炒饭', 25, '2020-02-22 09:24:18', '', 7, '2020-02-22/f36044140c7941409794a98529843d2b.jpg');
INSERT INTO `tb_recipe` VALUES (13, '水晶饺', 35, '2020-02-22 09:25:00', '', 8, '2020-02-22/afbf1343f67d48d995757df5666a2b5b.jpg');

-- ----------------------------
-- Table structure for tb_recipe_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_recipe_category`;
CREATE TABLE `tb_recipe_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜谱类别名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_recipe_category
-- ----------------------------
INSERT INTO `tb_recipe_category` VALUES (1, '五谷杂粮');
INSERT INTO `tb_recipe_category` VALUES (2, '广东小吃');
INSERT INTO `tb_recipe_category` VALUES (3, '面条');
INSERT INTO `tb_recipe_category` VALUES (4, '粥');
INSERT INTO `tb_recipe_category` VALUES (5, '馄饨');
INSERT INTO `tb_recipe_category` VALUES (6, '北京小吃');
INSERT INTO `tb_recipe_category` VALUES (7, '炒饭');
INSERT INTO `tb_recipe_category` VALUES (8, '饺子');

-- ----------------------------
-- Table structure for tb_recipe_week
-- ----------------------------
DROP TABLE IF EXISTS `tb_recipe_week`;
CREATE TABLE `tb_recipe_week`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `recipe_id` int(11) DEFAULT NULL COMMENT '菜谱的id',
  `view_week` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '星期【1 星期一 2 星期二 3 星期三 4 星期四 5 星期五 6 星期六 7 星期日】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of tb_recipe_week
-- ----------------------------
INSERT INTO `tb_recipe_week` VALUES (25, 9, '3');
INSERT INTO `tb_recipe_week` VALUES (23, 7, '3');
INSERT INTO `tb_recipe_week` VALUES (19, 1, '1');
INSERT INTO `tb_recipe_week` VALUES (20, 4, '1');
INSERT INTO `tb_recipe_week` VALUES (27, 3, '2');
INSERT INTO `tb_recipe_week` VALUES (26, 2, '2');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `role_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, '系统管理员');
INSERT INTO `tb_role` VALUES (2, '教职工');
INSERT INTO `tb_role` VALUES (3, '食堂管理员');
INSERT INTO `tb_role` VALUES (4, '后勤处处长');

-- ----------------------------
-- Table structure for tb_role_faculty
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_faculty`;
CREATE TABLE `tb_role_faculty`  (
  `role_id` int(10) NOT NULL COMMENT '角色id',
  `faculty_number` int(10) NOT NULL COMMENT '教工工号',
  PRIMARY KEY (`role_id`, `faculty_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_role_faculty
-- ----------------------------
INSERT INTO `tb_role_faculty` VALUES (1, 8888);
INSERT INTO `tb_role_faculty` VALUES (2, 8894);
INSERT INTO `tb_role_faculty` VALUES (2, 8895);
INSERT INTO `tb_role_faculty` VALUES (2, 8924);
INSERT INTO `tb_role_faculty` VALUES (2, 8925);
INSERT INTO `tb_role_faculty` VALUES (2, 8926);
INSERT INTO `tb_role_faculty` VALUES (2, 8927);
INSERT INTO `tb_role_faculty` VALUES (2, 8928);
INSERT INTO `tb_role_faculty` VALUES (2, 8929);
INSERT INTO `tb_role_faculty` VALUES (2, 8930);
INSERT INTO `tb_role_faculty` VALUES (3, 8923);
INSERT INTO `tb_role_faculty` VALUES (3, 8931);
INSERT INTO `tb_role_faculty` VALUES (4, 8932);

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission`  (
  `role_id` int(10) NOT NULL COMMENT '角色id',
  `permission_id` int(10) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
INSERT INTO `tb_role_permission` VALUES (1, 4);
INSERT INTO `tb_role_permission` VALUES (1, 5);
INSERT INTO `tb_role_permission` VALUES (1, 14);
INSERT INTO `tb_role_permission` VALUES (1, 35);
INSERT INTO `tb_role_permission` VALUES (1, 36);
INSERT INTO `tb_role_permission` VALUES (1, 51);
INSERT INTO `tb_role_permission` VALUES (1, 60);
INSERT INTO `tb_role_permission` VALUES (2, 60);
INSERT INTO `tb_role_permission` VALUES (3, 4);
INSERT INTO `tb_role_permission` VALUES (3, 35);
INSERT INTO `tb_role_permission` VALUES (3, 36);
INSERT INTO `tb_role_permission` VALUES (3, 51);
INSERT INTO `tb_role_permission` VALUES (3, 60);
INSERT INTO `tb_role_permission` VALUES (4, 14);
INSERT INTO `tb_role_permission` VALUES (4, 60);
INSERT INTO `tb_role_permission` VALUES (9, 4);
INSERT INTO `tb_role_permission` VALUES (9, 5);
INSERT INTO `tb_role_permission` VALUES (9, 14);
INSERT INTO `tb_role_permission` VALUES (9, 35);
INSERT INTO `tb_role_permission` VALUES (9, 36);
INSERT INTO `tb_role_permission` VALUES (9, 51);
INSERT INTO `tb_role_permission` VALUES (10, 4);
INSERT INTO `tb_role_permission` VALUES (10, 5);
INSERT INTO `tb_role_permission` VALUES (11, 4);
INSERT INTO `tb_role_permission` VALUES (11, 5);
INSERT INTO `tb_role_permission` VALUES (11, 14);
INSERT INTO `tb_role_permission` VALUES (11, 35);
INSERT INTO `tb_role_permission` VALUES (11, 36);
INSERT INTO `tb_role_permission` VALUES (11, 51);
INSERT INTO `tb_role_permission` VALUES (11, 56);

-- ----------------------------
-- Table structure for tb_section
-- ----------------------------
DROP TABLE IF EXISTS `tb_section`;
CREATE TABLE `tb_section`  (
  `section_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '系号',
  `section_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系别',
  `section_address` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '系别所在地址',
  `section_iphone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '系别联系热线',
  `section_note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '系别备注',
  PRIMARY KEY (`section_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_section
-- ----------------------------
INSERT INTO `tb_section` VALUES (1, '软件系', '综合楼5楼', '02087818800', '管理软件工程专业和信息工程专业');
INSERT INTO `tb_section` VALUES (2, '网络系', '综合楼5楼', '02087818801', '管理网络工程专业');
INSERT INTO `tb_section` VALUES (3, '计算机系', '综合楼4楼', '02087814612', '管理计算机专业');
INSERT INTO `tb_section` VALUES (4, '电子系', '综合楼4楼', '02087818610', '管理电子专业');
INSERT INTO `tb_section` VALUES (5, '游戏系', '综合楼3楼', '02087818817', '管理游戏专业');
INSERT INTO `tb_section` VALUES (6, '管理系', '综合楼3楼', '02087818819', '管理管理专业');
INSERT INTO `tb_section` VALUES (7, '国贸系', '综合楼2楼', '02087818131', '管理国际贸易专业');
INSERT INTO `tb_section` VALUES (8, '财会系', '综合楼2楼', '02087818498', '管理财会专业');
INSERT INTO `tb_section` VALUES (9, '数码系', '综合楼6楼', '02087818822', '管理数据媒体专业');
INSERT INTO `tb_section` VALUES (10, '外语系', '综合楼6楼', '02087818898', '管理外语专业');

-- ----------------------------
-- Table structure for tb_staff
-- ----------------------------
DROP TABLE IF EXISTS `tb_staff`;
CREATE TABLE `tb_staff`  (
  `staff_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '饭堂员工id',
  `staff_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '饭堂员工姓名',
  `staff_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '饭堂员工地址',
  `staff_sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '饭堂员工性别',
  `staff_remark` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '饭堂员工备注',
  `staff_hiredate` datetime DEFAULT NULL COMMENT '饭堂员工入职时间',
  `staff_mgr` int(10) DEFAULT NULL COMMENT '饭堂员工上级领导id[工作流要使用]',
  `staff_position` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '饭堂员工职位',
  `staff_dept_id` int(10) DEFAULT NULL COMMENT '饭堂员工部门id',
  `staff_img_path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '饭堂员工头像',
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_staff
-- ----------------------------
INSERT INTO `tb_staff` VALUES (6, '张四', '广州', '1', '负责食堂工作', '2020-02-20 15:19:53', NULL, '饭堂经理', 1, '2020-02-21/23d2c4483888443c898a9a420c01cc4a.jpg');
INSERT INTO `tb_staff` VALUES (19, '大厨', '广州', '0', '负责菜品生产工作', '2020-02-21 14:17:30', 6, '厨师长', 2, '2020-02-21/0a641c065739476ebe56b5df88bcb943.jpg');
INSERT INTO `tb_staff` VALUES (20, '操作小妹', '深圳', '0', '保证食品加工过程的卫生安全', '2020-02-21 14:21:43', 6, '操作部门组长', 3, '2020-02-21/3945dff307064c138a2fab5445c3a6f0.jpg');
INSERT INTO `tb_staff` VALUES (21, '切配小哥', '广州', '1', '准备好食材、合理分档取料', '2020-02-22 05:06:05', 6, '切配部组长', 4, '2020-02-21/ce965a4510e64cfabd71db58f3edbd90.jpg');
INSERT INTO `tb_staff` VALUES (22, '荤墩大姐', '广西', '1', '分开生熟肉及优质肉', '2020-02-21 14:24:53', 6, '荤墩部组长', 5, '2020-02-21/4ba7ff2e02a943f1bc46fb50191eaafe.jpg');
INSERT INTO `tb_staff` VALUES (23, '罗娟', '湖南', '0', '加工优质新鲜菜品', '2020-02-10 16:00:00', 6, '普通员工', 6, '2020-02-21/5265992970fc46dcb4f3463770badae5.jpg');
INSERT INTO `tb_staff` VALUES (24, '伍一雨', '北京', '0', '负责消毒、洗涤、清洁', '2020-02-21 14:28:22', 6, '洗消部组长', 8, '2020-02-21/223add94f15a471f8bd996fac07034ee.jpg');
INSERT INTO `tb_staff` VALUES (25, '叶星辰', '江西', '1', '负责仓库管理', '2020-02-21 14:29:18', 6, '仓管部组长', 10, '2020-02-21/16cdfddbd9fb4d169c53ea840e2caf50.jpg');

SET FOREIGN_KEY_CHECKS = 1;
