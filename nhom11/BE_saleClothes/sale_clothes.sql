/*
 Navicat Premium Data Transfer

 Source Server         : huyhoang2004
 Source Server Type    : MySQL
 Source Server Version : 100526
 Source Host           : localhost:3306
 Source Schema         : sale_clothes

 Target Server Type    : MySQL
 Target Server Version : 100526
 File Encoding         : 65001

 Date: 03/03/2025 00:12:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `detail_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `district` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_address` bit(1) NOT NULL,
  `village` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `kh_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKq494j6y18t1gbrvblvqhmohql`(`kh_username` ASC) USING BTREE,
  CONSTRAINT `FKq494j6y18t1gbrvblvqhmohql` FOREIGN KEY (`kh_username`) REFERENCES `khach_hang` (`kh_username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (4, 'àgfgàd', 'Quận Nam Từ Liêm', 'Trần Huy Hoàng', '0965523100', 'Thành phố Hà Nội', b'1', 'Phường Cầu Diễn', 'tranhuyhoang2004');
INSERT INTO `address` VALUES (5, 'ádfgfd', 'Huyện Mèo Vạc', 'Trần Huy Hoàng', '0965523100', 'Tỉnh Hà Giang', b'1', 'Xã Khâu Vai', 'tranhuyhoang2004');
INSERT INTO `address` VALUES (6, 'ádfđ', 'Huyện Pác Nặm', 'Hoàng', '0965523100', 'Tỉnh Bắc Kạn', b'1', 'Xã Bộc Bố', 'tranhuyhoang2004');

-- ----------------------------
-- Table structure for chi_tiet_don_hang
-- ----------------------------
DROP TABLE IF EXISTS `chi_tiet_don_hang`;
CREATE TABLE `chi_tiet_don_hang`  (
  `ctdh_ma` int NOT NULL AUTO_INCREMENT,
  `size` int NULL DEFAULT NULL,
  `soluong` int NULL DEFAULT NULL,
  `theloai` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trangthaidonhang` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dh_ma` int NULL DEFAULT NULL,
  `sp_ma` int NULL DEFAULT NULL,
  PRIMARY KEY (`ctdh_ma`) USING BTREE,
  INDEX `FK44mjqs3fj4n0g1n5bhnvms68w`(`dh_ma` ASC) USING BTREE,
  INDEX `FKnv8x94klk7xqh78ox9l9g25ao`(`sp_ma` ASC) USING BTREE,
  CONSTRAINT `FK44mjqs3fj4n0g1n5bhnvms68w` FOREIGN KEY (`dh_ma`) REFERENCES `don_dat_hang` (`dh_ma`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKnv8x94klk7xqh78ox9l9g25ao` FOREIGN KEY (`sp_ma`) REFERENCES `san_pham` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chi_tiet_don_hang
-- ----------------------------

-- ----------------------------
-- Table structure for color
-- ----------------------------
DROP TABLE IF EXISTS `color`;
CREATE TABLE `color`  (
  `colorid` int NOT NULL,
  `color_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `color_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`colorid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of color
-- ----------------------------
INSERT INTO `color` VALUES (252, '#f7f8f9', 'Trắng ngà');
INSERT INTO `color` VALUES (253, '#000000', 'Đen');
INSERT INTO `color` VALUES (254, '#e83e8c', 'Hồng san hô');
INSERT INTO `color` VALUES (255, '#ffffff', 'Trắng');
INSERT INTO `color` VALUES (256, '#f4bfc9', 'Hồng nhạt');
INSERT INTO `color` VALUES (302, '#bfaf59', 'Vàng bơ');
INSERT INTO `color` VALUES (303, '#91a8ae', 'Xanh ghi đá');
INSERT INTO `color` VALUES (352, '#716e97', 'Ghi khói');
INSERT INTO `color` VALUES (353, '#4B0082', 'Tím than');
INSERT INTO `color` VALUES (354, '#c1a783', 'Be');
INSERT INTO `color` VALUES (355, '#bfab58', 'Vàng bơ');
INSERT INTO `color` VALUES (356, '#383f68', 'Xanh dương đậm');
INSERT INTO `color` VALUES (357, '#62362b', 'Nâu socola');
INSERT INTO `color` VALUES (402, '#8d2a30', 'Đỏ');
INSERT INTO `color` VALUES (403, '#9ba4a4', 'Xanh bầu trời');
INSERT INTO `color` VALUES (404, '#63636c', 'Ghi');
INSERT INTO `color` VALUES (405, '#a896ba', 'Tím nhạt');
INSERT INTO `color` VALUES (406, '#68273c', 'Đỏ mận');

-- ----------------------------
-- Table structure for color_seq
-- ----------------------------
DROP TABLE IF EXISTS `color_seq`;
CREATE TABLE `color_seq`  (
  `next_val` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of color_seq
-- ----------------------------
INSERT INTO `color_seq` VALUES (501);

-- ----------------------------
-- Table structure for cua_hang
-- ----------------------------
DROP TABLE IF EXISTS `cua_hang`;
CREATE TABLE `cua_hang`  (
  `ch_ma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ch_diachi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ch_sdt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ch_ten` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ch_ma`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cua_hang
-- ----------------------------

-- ----------------------------
-- Table structure for danh_muc
-- ----------------------------
DROP TABLE IF EXISTS `danh_muc`;
CREATE TABLE `danh_muc`  (
  `dm_ma` int NOT NULL AUTO_INCREMENT,
  `dm_ten` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dm_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dm_ma`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of danh_muc
-- ----------------------------
INSERT INTO `danh_muc` VALUES (12, 'Phụ Kiện', 'Nữ');
INSERT INTO `danh_muc` VALUES (13, 'Áo', 'Nữ');
INSERT INTO `danh_muc` VALUES (14, 'Áo Khoác', 'Nữ');
INSERT INTO `danh_muc` VALUES (15, 'Chân váy', 'Nữ');
INSERT INTO `danh_muc` VALUES (16, 'Quần', 'Nữ');
INSERT INTO `danh_muc` VALUES (17, 'Set Bộ', 'Nữ');
INSERT INTO `danh_muc` VALUES (18, 'Đầm/Áo Dài', 'Nữ');
INSERT INTO `danh_muc` VALUES (19, 'Áo', 'Nam');
INSERT INTO `danh_muc` VALUES (21, 'Quần', 'Nam');
INSERT INTO `danh_muc` VALUES (22, 'Áo', 'Bé Gái');
INSERT INTO `danh_muc` VALUES (23, 'Quần', 'Bé Gái');
INSERT INTO `danh_muc` VALUES (24, 'Váy', 'Bé Gái');
INSERT INTO `danh_muc` VALUES (25, 'Chân Váy', 'Bé Gái');
INSERT INTO `danh_muc` VALUES (26, 'Phụ Kiện', 'Bé Gái');
INSERT INTO `danh_muc` VALUES (27, 'Áo', 'Bé Nam');
INSERT INTO `danh_muc` VALUES (28, 'Quần', 'Bé Nam');
INSERT INTO `danh_muc` VALUES (29, 'Giày', 'Bé Nam');
INSERT INTO `danh_muc` VALUES (30, 'Giày', 'Nữ');
INSERT INTO `danh_muc` VALUES (31, 'Giày', 'Nam');

-- ----------------------------
-- Table structure for danh_muc_con
-- ----------------------------
DROP TABLE IF EXISTS `danh_muc_con`;
CREATE TABLE `danh_muc_con`  (
  `dmc_ma` int NOT NULL AUTO_INCREMENT,
  `dmc_ten` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dm_ma` int NULL DEFAULT NULL,
  PRIMARY KEY (`dmc_ma`) USING BTREE,
  INDEX `FK16mct191jc9f7qeokifnuhiv7`(`dm_ma` ASC) USING BTREE,
  CONSTRAINT `FK16mct191jc9f7qeokifnuhiv7` FOREIGN KEY (`dm_ma`) REFERENCES `danh_muc` (`dm_ma`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of danh_muc_con
-- ----------------------------
INSERT INTO `danh_muc_con` VALUES (7, 'Áo sơ mi', 13);
INSERT INTO `danh_muc_con` VALUES (8, 'Áo thun', 13);
INSERT INTO `danh_muc_con` VALUES (9, 'Áo croptop', 13);
INSERT INTO `danh_muc_con` VALUES (10, 'Áo len', 13);
INSERT INTO `danh_muc_con` VALUES (11, 'Áo dạ', 14);
INSERT INTO `danh_muc_con` VALUES (12, 'Măng Tô', 14);
INSERT INTO `danh_muc_con` VALUES (13, 'Hoodie', 14);
INSERT INTO `danh_muc_con` VALUES (14, 'Áo khoác dù', 14);
INSERT INTO `danh_muc_con` VALUES (15, 'Túi/ví', 12);
INSERT INTO `danh_muc_con` VALUES (16, 'Đồ lót', 12);
INSERT INTO `danh_muc_con` VALUES (17, 'Phụ Kiện', 12);
INSERT INTO `danh_muc_con` VALUES (18, 'Chân Váy Jean', 15);
INSERT INTO `danh_muc_con` VALUES (19, 'Chân váy chữ A', 15);
INSERT INTO `danh_muc_con` VALUES (20, 'Chân váy bút chì', 15);
INSERT INTO `danh_muc_con` VALUES (21, 'Chân váy xòe', 15);
INSERT INTO `danh_muc_con` VALUES (22, 'Chân jean', 16);
INSERT INTO `danh_muc_con` VALUES (23, 'Chân short', 16);
INSERT INTO `danh_muc_con` VALUES (24, 'Chân dài', 16);
INSERT INTO `danh_muc_con` VALUES (25, 'Set bộ công sở', 17);
INSERT INTO `danh_muc_con` VALUES (26, 'Set bộ mùa đông', 17);
INSERT INTO `danh_muc_con` VALUES (27, 'Bộ mùa hè', 17);
INSERT INTO `danh_muc_con` VALUES (28, 'Set bộ len', 17);
INSERT INTO `danh_muc_con` VALUES (29, 'Áo Dài', 18);
INSERT INTO `danh_muc_con` VALUES (30, 'Đầm công sở', 18);
INSERT INTO `danh_muc_con` VALUES (31, 'Váy Đầm', 18);
INSERT INTO `danh_muc_con` VALUES (32, 'Áo thun', 19);
INSERT INTO `danh_muc_con` VALUES (33, 'Áo khoác', 19);
INSERT INTO `danh_muc_con` VALUES (34, 'Áo polo', 19);
INSERT INTO `danh_muc_con` VALUES (35, 'Áo sơ mi', 19);
INSERT INTO `danh_muc_con` VALUES (36, 'Áo len', 19);
INSERT INTO `danh_muc_con` VALUES (37, 'Quần Jean', 21);
INSERT INTO `danh_muc_con` VALUES (38, 'Quần Short', 21);
INSERT INTO `danh_muc_con` VALUES (39, 'Quần dài', 21);
INSERT INTO `danh_muc_con` VALUES (40, 'Quần kaki', 21);
INSERT INTO `danh_muc_con` VALUES (41, 'Quần thể thao', 21);
INSERT INTO `danh_muc_con` VALUES (42, 'Quần âu', 21);
INSERT INTO `danh_muc_con` VALUES (43, 'Giày thể thao', 30);
INSERT INTO `danh_muc_con` VALUES (44, 'Giày boot', 30);
INSERT INTO `danh_muc_con` VALUES (45, 'Giày xăng đan', 30);
INSERT INTO `danh_muc_con` VALUES (46, 'Giày đế bệt', 30);
INSERT INTO `danh_muc_con` VALUES (48, 'Giày sneaker', 31);
INSERT INTO `danh_muc_con` VALUES (49, 'Giày Oxford', 31);
INSERT INTO `danh_muc_con` VALUES (50, 'Giày Derby', 31);
INSERT INTO `danh_muc_con` VALUES (51, 'Giày boot nam', 31);
INSERT INTO `danh_muc_con` VALUES (52, 'Giày da', 31);

-- ----------------------------
-- Table structure for don_dat_hang
-- ----------------------------
DROP TABLE IF EXISTS `don_dat_hang`;
CREATE TABLE `don_dat_hang`  (
  `dh_ma` int NOT NULL AUTO_INCREMENT,
  `dh_ngaygiao` date NULL DEFAULT NULL,
  `dh_ngaylap` date NULL DEFAULT NULL,
  `dh_noigiao` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dh_trangthaithanhtoan` int NULL DEFAULT NULL,
  `kh_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dh_ma`) USING BTREE,
  INDEX `FKpf4boyem6nru3hvln4qhitr42`(`kh_username` ASC) USING BTREE,
  CONSTRAINT `FKpf4boyem6nru3hvln4qhitr42` FOREIGN KEY (`kh_username`) REFERENCES `khach_hang` (`kh_username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of don_dat_hang
-- ----------------------------

-- ----------------------------
-- Table structure for favorte_product
-- ----------------------------
DROP TABLE IF EXISTS `favorte_product`;
CREATE TABLE `favorte_product`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `kh_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKte16qdxdllx52yn3qg7l4ikgq`(`kh_username` ASC) USING BTREE,
  INDEX `FKaqg7y2r51bgsjcbypv9rsvdmn`(`product_id` ASC) USING BTREE,
  CONSTRAINT `FKaqg7y2r51bgsjcbypv9rsvdmn` FOREIGN KEY (`product_id`) REFERENCES `san_pham` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKte16qdxdllx52yn3qg7l4ikgq` FOREIGN KEY (`kh_username`) REFERENCES `khach_hang` (`kh_username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorte_product
-- ----------------------------
INSERT INTO `favorte_product` VALUES (19, '2025-02-23 19:03:56.000000', 'tranhuyhoang2004', 62);
INSERT INTO `favorte_product` VALUES (22, '2025-02-27 22:57:20.000000', 'tranhuyhoang2004', 51);
INSERT INTO `favorte_product` VALUES (23, '2025-02-27 22:57:24.000000', 'tranhuyhoang2004', 52);

-- ----------------------------
-- Table structure for file_data
-- ----------------------------
DROP TABLE IF EXISTS `file_data`;
CREATE TABLE `file_data`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `variant_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKs21krwtb7ybk9wlnvv6utbj3j`(`variant_id` ASC) USING BTREE,
  CONSTRAINT `FKs21krwtb7ybk9wlnvv6utbj3j` FOREIGN KEY (`variant_id`) REFERENCES `product_variant` (`variant_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 295 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_data
-- ----------------------------
INSERT INTO `file_data` VALUES (52, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\8d5a1c12045294c57bcd302714b7ea47.webp', '8d5a1c12045294c57bcd302714b7ea47.webp', 'image/webp', 62);
INSERT INTO `file_data` VALUES (53, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\e4d7362e0cf885067a90bf0deeb9f709.webp', 'e4d7362e0cf885067a90bf0deeb9f709.webp', 'image/webp', 62);
INSERT INTO `file_data` VALUES (54, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\1dfd5278ed6abd816bd0a55351061741.webp', '1dfd5278ed6abd816bd0a55351061741.webp', 'image/webp', 63);
INSERT INTO `file_data` VALUES (55, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\f2a2a6ad18754f338262eade2b9e0e3d.webp', 'f2a2a6ad18754f338262eade2b9e0e3d.webp', 'image/webp', 62);
INSERT INTO `file_data` VALUES (56, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\8bbf20f728f0bcd291c8d6202edba45e.webp', '8bbf20f728f0bcd291c8d6202edba45e.webp', 'image/webp', 63);
INSERT INTO `file_data` VALUES (57, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\69b9a41c433c7599d3a6c12ea6ccfca6.webp', '69b9a41c433c7599d3a6c12ea6ccfca6.webp', 'image/webp', 63);
INSERT INTO `file_data` VALUES (64, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\9b316d6bb71ee8bb6f5e334b960a3aa1.webp', '9b316d6bb71ee8bb6f5e334b960a3aa1.webp', 'image/webp', 66);
INSERT INTO `file_data` VALUES (65, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\24ae1cfe366df39c4d5f42e0563435be.webp', '24ae1cfe366df39c4d5f42e0563435be.webp', 'image/webp', 66);
INSERT INTO `file_data` VALUES (66, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\511635316eb8171f073c85cb82b7dfd3.webp', '511635316eb8171f073c85cb82b7dfd3.webp', 'image/webp', 66);
INSERT INTO `file_data` VALUES (67, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\ab4cc94b48e2880c5bc82c31af240f70.webp', 'ab4cc94b48e2880c5bc82c31af240f70.webp', 'image/webp', 66);
INSERT INTO `file_data` VALUES (68, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\3b0efe52c22e271b14943618d12e45e0.webp', '3b0efe52c22e271b14943618d12e45e0.webp', 'image/webp', 67);
INSERT INTO `file_data` VALUES (69, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\4bdddd348f47574a635b891243789511.webp', '4bdddd348f47574a635b891243789511.webp', 'image/webp', 67);
INSERT INTO `file_data` VALUES (70, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\78a2aa392a18142e599728f845cc05d5.webp', '78a2aa392a18142e599728f845cc05d5.webp', 'image/webp', 67);
INSERT INTO `file_data` VALUES (71, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\cbedcdf9df43acf0eee541c144cefa61.webp', 'cbedcdf9df43acf0eee541c144cefa61.webp', 'image/webp', 67);
INSERT INTO `file_data` VALUES (72, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\4e0a4187dc42c63cbb575655ec790c23.webp', '4e0a4187dc42c63cbb575655ec790c23.webp', 'image/webp', 68);
INSERT INTO `file_data` VALUES (73, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\765bf181aac9c71caa8696d1c499d028.webp', '765bf181aac9c71caa8696d1c499d028.webp', 'image/webp', 68);
INSERT INTO `file_data` VALUES (74, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\49788a6504eafbaa86aaf8612c5a1b1b.webp', '49788a6504eafbaa86aaf8612c5a1b1b.webp', 'image/webp', 68);
INSERT INTO `file_data` VALUES (75, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\bd45cfb46e09f981ca9bafaef9354e20.webp', 'bd45cfb46e09f981ca9bafaef9354e20.webp', 'image/webp', 68);
INSERT INTO `file_data` VALUES (76, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\d6a9889e22dfab60fa8506202638c816.webp', 'd6a9889e22dfab60fa8506202638c816.webp', 'image/webp', 68);
INSERT INTO `file_data` VALUES (81, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\4dc0c9c5e1d7076a71fd5f257966703a.webp', '4dc0c9c5e1d7076a71fd5f257966703a.webp', 'image/webp', 72);
INSERT INTO `file_data` VALUES (82, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\b74f22f0ff1611942575dd96db3132a3.webp', 'b74f22f0ff1611942575dd96db3132a3.webp', 'image/webp', 72);
INSERT INTO `file_data` VALUES (83, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\b669119fd50b17d40a6af276130574e4.webp', 'b669119fd50b17d40a6af276130574e4.webp', 'image/webp', 72);
INSERT INTO `file_data` VALUES (84, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\d9af4e23dadecbcebbed067a69c2a8f1.webp', 'd9af4e23dadecbcebbed067a69c2a8f1.webp', 'image/webp', 72);
INSERT INTO `file_data` VALUES (85, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\e3b5def64d57eabac5a08f91f61be6d0.webp', 'e3b5def64d57eabac5a08f91f61be6d0.webp', 'image/webp', 72);
INSERT INTO `file_data` VALUES (86, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\98c4e87d27657f646350ac828941572e.webp', '98c4e87d27657f646350ac828941572e.webp', 'image/webp', 73);
INSERT INTO `file_data` VALUES (87, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\ac1f753ea278379a6b2826fb379f995d.webp', 'ac1f753ea278379a6b2826fb379f995d.webp', 'image/webp', 73);
INSERT INTO `file_data` VALUES (88, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\c48cbea836f3a28569b335d704edcb8d.webp', 'c48cbea836f3a28569b335d704edcb8d.webp', 'image/webp', 73);
INSERT INTO `file_data` VALUES (89, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\cce1d7c7d51b6a6b2bf4f6469f7272c0.webp', 'cce1d7c7d51b6a6b2bf4f6469f7272c0.webp', 'image/webp', 73);
INSERT INTO `file_data` VALUES (90, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\d5d5d5228b6775e53fac58d999792156.webp', 'd5d5d5228b6775e53fac58d999792156.webp', 'image/webp', 73);
INSERT INTO `file_data` VALUES (91, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\01ea68d62b6d9ff8c665e2ba7fdb841d.webp', '01ea68d62b6d9ff8c665e2ba7fdb841d.webp', 'image/webp', 74);
INSERT INTO `file_data` VALUES (92, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\078b656e987f61180b56fe908cb2c9a5.webp', '078b656e987f61180b56fe908cb2c9a5.webp', 'image/webp', 75);
INSERT INTO `file_data` VALUES (93, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\1abccfaf1be0591ef24d7218008e34f2.webp', '1abccfaf1be0591ef24d7218008e34f2.webp', 'image/webp', 74);
INSERT INTO `file_data` VALUES (94, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\2a3c3520c599a2557f56d9a2327c8451.webp', '2a3c3520c599a2557f56d9a2327c8451.webp', 'image/webp', 74);
INSERT INTO `file_data` VALUES (95, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\387ac2f31d114bb02ac8e2816707d4f4.webp', '387ac2f31d114bb02ac8e2816707d4f4.webp', 'image/webp', 75);
INSERT INTO `file_data` VALUES (96, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\410ec8da34f28d88fb93f40cf4a2cc2c.webp', '410ec8da34f28d88fb93f40cf4a2cc2c.webp', 'image/webp', 75);
INSERT INTO `file_data` VALUES (97, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\5d5609b8d89c1dec1b38922e38d8672d.webp', '5d5609b8d89c1dec1b38922e38d8672d.webp', 'image/webp', 74);
INSERT INTO `file_data` VALUES (98, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\372174386fc11a01021e3f0fe33627f1.webp', '372174386fc11a01021e3f0fe33627f1.webp', 'image/webp', 74);
INSERT INTO `file_data` VALUES (99, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\b1f00beaf8fef76ddf886e2fb9efa6bc.webp', 'b1f00beaf8fef76ddf886e2fb9efa6bc.webp', 'image/webp', 75);
INSERT INTO `file_data` VALUES (100, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\8d600e4e4fd65e6545d9501a443748ad.webp', '8d600e4e4fd65e6545d9501a443748ad.webp', 'image/webp', 76);
INSERT INTO `file_data` VALUES (101, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\66ae7f110e7a9078c18730008d389545.webp', '66ae7f110e7a9078c18730008d389545.webp', 'image/webp', 76);
INSERT INTO `file_data` VALUES (102, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\90ce303957aa54f2c636c8d85c29b2ac.webp', '90ce303957aa54f2c636c8d85c29b2ac.webp', 'image/webp', 76);
INSERT INTO `file_data` VALUES (103, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\93eb28c38e3f810c8e8b1d5010154bca.webp', '93eb28c38e3f810c8e8b1d5010154bca.webp', 'image/webp', 76);
INSERT INTO `file_data` VALUES (104, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\078b656e987f61180b56fe908cb2c9a5.webp', '078b656e987f61180b56fe908cb2c9a5.webp', 'image/webp', 77);
INSERT INTO `file_data` VALUES (105, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\387ac2f31d114bb02ac8e2816707d4f4.webp', '387ac2f31d114bb02ac8e2816707d4f4.webp', 'image/webp', 77);
INSERT INTO `file_data` VALUES (106, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\231afd388ce3cb4ad71a1bcd56797b5a.webp', '231afd388ce3cb4ad71a1bcd56797b5a.webp', 'image/webp', 76);
INSERT INTO `file_data` VALUES (107, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\410ec8da34f28d88fb93f40cf4a2cc2c.webp', '410ec8da34f28d88fb93f40cf4a2cc2c.webp', 'image/webp', 77);
INSERT INTO `file_data` VALUES (108, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\da8cdeacb61218d11ee2953b56800464.webp', 'da8cdeacb61218d11ee2953b56800464.webp', 'image/webp', 76);
INSERT INTO `file_data` VALUES (109, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\b1f00beaf8fef76ddf886e2fb9efa6bc.webp', 'b1f00beaf8fef76ddf886e2fb9efa6bc.webp', 'image/webp', 77);
INSERT INTO `file_data` VALUES (110, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\f601917d65fd3c45b612c09feba41b1f.webp', 'f601917d65fd3c45b612c09feba41b1f.webp', 'image/webp', 76);
INSERT INTO `file_data` VALUES (111, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\47ca454a9b9be1156fdd61f2989968d5.webp', '47ca454a9b9be1156fdd61f2989968d5.webp', 'image/webp', 78);
INSERT INTO `file_data` VALUES (112, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\678d9a7f56ec4d5c7710a9793e4549d6.webp', '678d9a7f56ec4d5c7710a9793e4549d6.webp', 'image/webp', 78);
INSERT INTO `file_data` VALUES (113, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\905e78ee1184a4bf62f00577cc5fbc4c.webp', '905e78ee1184a4bf62f00577cc5fbc4c.webp', 'image/webp', 78);
INSERT INTO `file_data` VALUES (114, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\71576b85a6bf6566d5e0baf052f3515b.webp', '71576b85a6bf6566d5e0baf052f3515b.webp', 'image/webp', 78);
INSERT INTO `file_data` VALUES (115, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\e19caeb56d4381d7d0dac95263b5d0a9.webp', 'e19caeb56d4381d7d0dac95263b5d0a9.webp', 'image/webp', 78);
INSERT INTO `file_data` VALUES (116, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\f15acd41746729f6f47a5ff5402ebb4f.webp', 'f15acd41746729f6f47a5ff5402ebb4f.webp', 'image/webp', 78);
INSERT INTO `file_data` VALUES (117, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\0c10b05d910ba10db11fc938aca8dea4.webp', '0c10b05d910ba10db11fc938aca8dea4.webp', 'image/webp', 79);
INSERT INTO `file_data` VALUES (118, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\2285583745e655a786065d27c698600c.jpg', '2285583745e655a786065d27c698600c.jpg', 'image/jpeg', 79);
INSERT INTO `file_data` VALUES (119, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\e4bd3d401ffb82344039210cdfbe17bc.webp', 'e4bd3d401ffb82344039210cdfbe17bc.webp', 'image/webp', 79);
INSERT INTO `file_data` VALUES (120, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\fd947f7684fae14c1ce1d925a89ccd3b.webp', 'fd947f7684fae14c1ce1d925a89ccd3b.webp', 'image/webp', 79);
INSERT INTO `file_data` VALUES (121, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\0e918ac514163d1a28a8cbdb2d0b372e.webp', '0e918ac514163d1a28a8cbdb2d0b372e.webp', 'image/webp', 80);
INSERT INTO `file_data` VALUES (122, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\1e65693a73220a6c5b43dfebb2663189.webp', '1e65693a73220a6c5b43dfebb2663189.webp', 'image/webp', 80);
INSERT INTO `file_data` VALUES (123, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\3ae72f0bb68f1791359cf65dca2bdcad.webp', '3ae72f0bb68f1791359cf65dca2bdcad.webp', 'image/webp', 80);
INSERT INTO `file_data` VALUES (124, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\9ec352f0c064ac8d018b7f271a17b8dd.jpg', '9ec352f0c064ac8d018b7f271a17b8dd.jpg', 'image/jpeg', 80);
INSERT INTO `file_data` VALUES (125, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\78b77224341db1ec42a47936d62ef6a7.webp', '78b77224341db1ec42a47936d62ef6a7.webp', 'image/webp', 80);
INSERT INTO `file_data` VALUES (126, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\85a3d919898c0bce48b0fbcf82a4b4ea.jpg', '85a3d919898c0bce48b0fbcf82a4b4ea.jpg', 'image/jpeg', 80);
INSERT INTO `file_data` VALUES (127, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\486b595c5fac788f2950848ab5e6c28c.jpg', '486b595c5fac788f2950848ab5e6c28c.jpg', 'image/jpeg', 80);
INSERT INTO `file_data` VALUES (128, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\d1c5a170379a9177dce2c1d784d99701.jpg', 'd1c5a170379a9177dce2c1d784d99701.jpg', 'image/jpeg', 80);
INSERT INTO `file_data` VALUES (129, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\6e979f00f521530e219ffd13bf406522.webp', '6e979f00f521530e219ffd13bf406522.webp', 'image/webp', 81);
INSERT INTO `file_data` VALUES (130, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\58ee7880900787c485b2a30c6d243170.webp', '58ee7880900787c485b2a30c6d243170.webp', 'image/webp', 81);
INSERT INTO `file_data` VALUES (131, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\76d2a0cc5876f7b68fb8b9422d2b9b61.webp', '76d2a0cc5876f7b68fb8b9422d2b9b61.webp', 'image/webp', 81);
INSERT INTO `file_data` VALUES (132, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\493c67d823c2f1e8cbf280652c8d73d5.webp', '493c67d823c2f1e8cbf280652c8d73d5.webp', 'image/webp', 81);
INSERT INTO `file_data` VALUES (133, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\db55bf5d995b84e0e77b171c37fa4da2.webp', 'db55bf5d995b84e0e77b171c37fa4da2.webp', 'image/webp', 81);
INSERT INTO `file_data` VALUES (134, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\e8fe78f8f6b5e5b20b09a679785c11c9.webp', 'e8fe78f8f6b5e5b20b09a679785c11c9.webp', 'image/webp', 81);
INSERT INTO `file_data` VALUES (135, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\34ff6ad1b32ad28283a69ace147723aa.webp', '34ff6ad1b32ad28283a69ace147723aa.webp', 'image/webp', 82);
INSERT INTO `file_data` VALUES (136, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\72a9a511a2c4c6fdf16f8a91b962d40c.webp', '72a9a511a2c4c6fdf16f8a91b962d40c.webp', 'image/webp', 82);
INSERT INTO `file_data` VALUES (137, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\6673fa7167ad076d7ac087792a659532.webp', '6673fa7167ad076d7ac087792a659532.webp', 'image/webp', 82);
INSERT INTO `file_data` VALUES (138, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\cc540c53de18363b5edeb9024f080eef.webp', 'cc540c53de18363b5edeb9024f080eef.webp', 'image/webp', 82);
INSERT INTO `file_data` VALUES (139, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\fde79a46351d1f3804d272d837877e54.webp', 'fde79a46351d1f3804d272d837877e54.webp', 'image/webp', 82);
INSERT INTO `file_data` VALUES (140, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\0ae1b47d7f209f8a0d52a356adfd4d38.webp', '0ae1b47d7f209f8a0d52a356adfd4d38.webp', 'image/webp', 83);
INSERT INTO `file_data` VALUES (141, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\8f4dea3cd7af66fa6c8180fa084493bb.webp', '8f4dea3cd7af66fa6c8180fa084493bb.webp', 'image/webp', 83);
INSERT INTO `file_data` VALUES (142, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\b53304e7b6cb77d4eda1d892f36640ba.webp', 'b53304e7b6cb77d4eda1d892f36640ba.webp', 'image/webp', 83);
INSERT INTO `file_data` VALUES (143, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\eafc0b08da9f3649bb6b763c3916db89.webp', 'eafc0b08da9f3649bb6b763c3916db89.webp', 'image/webp', 83);
INSERT INTO `file_data` VALUES (144, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\1bc8b754d014a7faadcc62257204b242.webp', '1bc8b754d014a7faadcc62257204b242.webp', 'image/webp', 84);
INSERT INTO `file_data` VALUES (145, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\5fda358213a438aa5af6ca191b9f94ba.webp', '5fda358213a438aa5af6ca191b9f94ba.webp', 'image/webp', 84);
INSERT INTO `file_data` VALUES (146, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\387269a6b560c9873b768073ce0be326.webp', '387269a6b560c9873b768073ce0be326.webp', 'image/webp', 84);
INSERT INTO `file_data` VALUES (147, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\be568e7809cdca202e34514b1c0651cc.webp', 'be568e7809cdca202e34514b1c0651cc.webp', 'image/webp', 84);
INSERT INTO `file_data` VALUES (148, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\9c56613d5825323cd8867b8c7227d68e.webp', '9c56613d5825323cd8867b8c7227d68e.webp', 'image/webp', 85);
INSERT INTO `file_data` VALUES (149, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\607712fbb885489eddc9e06202a91c08.webp', '607712fbb885489eddc9e06202a91c08.webp', 'image/webp', 85);
INSERT INTO `file_data` VALUES (150, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\03804818b2e8bb0d3f42409f5d7ddaeb.webp', '03804818b2e8bb0d3f42409f5d7ddaeb.webp', 'image/webp', 85);
INSERT INTO `file_data` VALUES (151, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\9d6735439973d775e21c71208263dfcf.webp', '9d6735439973d775e21c71208263dfcf.webp', 'image/webp', 86);
INSERT INTO `file_data` VALUES (152, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\429992360a82de543ea65bd18270bd18.webp', '429992360a82de543ea65bd18270bd18.webp', 'image/webp', 85);
INSERT INTO `file_data` VALUES (153, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\81e52bd17f28bafda5dc7f0c648cb1b3.webp', '81e52bd17f28bafda5dc7f0c648cb1b3.webp', 'image/webp', 86);
INSERT INTO `file_data` VALUES (154, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\ca33050fd5e5b54ce9a71a56ea1f6ae7.webp', 'ca33050fd5e5b54ce9a71a56ea1f6ae7.webp', 'image/webp', 85);
INSERT INTO `file_data` VALUES (155, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\404b05cc4cc96c7024e4d256119c9227.webp', '404b05cc4cc96c7024e4d256119c9227.webp', 'image/webp', 86);
INSERT INTO `file_data` VALUES (156, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\572a85ed6e5162f4da93442f4b8b5768.webp', '572a85ed6e5162f4da93442f4b8b5768.webp', 'image/webp', 86);
INSERT INTO `file_data` VALUES (157, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\aa6b95f7670c3a16bc3b86eb19ab8ab0.webp', 'aa6b95f7670c3a16bc3b86eb19ab8ab0.webp', 'image/webp', 86);
INSERT INTO `file_data` VALUES (158, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\02d9f8b1d7b45c2b015a7466768dd3cd.webp', '02d9f8b1d7b45c2b015a7466768dd3cd.webp', 'image/webp', 87);
INSERT INTO `file_data` VALUES (159, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\06ae1f0d2157a7b14479f3e01b18c3bc.webp', '06ae1f0d2157a7b14479f3e01b18c3bc.webp', 'image/webp', 87);
INSERT INTO `file_data` VALUES (160, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\7f2dea5208d96467f02ffced9770f66a.webp', '7f2dea5208d96467f02ffced9770f66a.webp', 'image/webp', 87);
INSERT INTO `file_data` VALUES (161, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\03804818b2e8bb0d3f42409f5d7ddaeb.webp', '03804818b2e8bb0d3f42409f5d7ddaeb.webp', 'image/webp', 87);
INSERT INTO `file_data` VALUES (162, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\9d6735439973d775e21c71208263dfcf.webp', '9d6735439973d775e21c71208263dfcf.webp', 'image/webp', 88);
INSERT INTO `file_data` VALUES (163, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\81e52bd17f28bafda5dc7f0c648cb1b3.webp', '81e52bd17f28bafda5dc7f0c648cb1b3.webp', 'image/webp', 88);
INSERT INTO `file_data` VALUES (164, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\429992360a82de543ea65bd18270bd18.webp', '429992360a82de543ea65bd18270bd18.webp', 'image/webp', 87);
INSERT INTO `file_data` VALUES (165, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\404b05cc4cc96c7024e4d256119c9227.webp', '404b05cc4cc96c7024e4d256119c9227.webp', 'image/webp', 88);
INSERT INTO `file_data` VALUES (166, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\cbb4a2c4c9b1d71883b32cecdf956f86.webp', 'cbb4a2c4c9b1d71883b32cecdf956f86.webp', 'image/webp', 87);
INSERT INTO `file_data` VALUES (167, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\572a85ed6e5162f4da93442f4b8b5768.webp', '572a85ed6e5162f4da93442f4b8b5768.webp', 'image/webp', 88);
INSERT INTO `file_data` VALUES (168, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\cf3373013217ac616048241bd81c30bc.webp', 'cf3373013217ac616048241bd81c30bc.webp', 'image/webp', 87);
INSERT INTO `file_data` VALUES (169, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\aa6b95f7670c3a16bc3b86eb19ab8ab0.webp', 'aa6b95f7670c3a16bc3b86eb19ab8ab0.webp', 'image/webp', 88);
INSERT INTO `file_data` VALUES (170, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\eaee0d45a3ed8c58061b570fd5464578.webp', 'eaee0d45a3ed8c58061b570fd5464578.webp', 'image/webp', 87);
INSERT INTO `file_data` VALUES (171, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\5c3db4a45faf9d94775ae1db8a0d0498.jpg', '5c3db4a45faf9d94775ae1db8a0d0498.jpg', 'image/jpeg', 90);
INSERT INTO `file_data` VALUES (172, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\37f160a7a8d729c0d404444d701c62ed.jpg', '37f160a7a8d729c0d404444d701c62ed.jpg', 'image/jpeg', 90);
INSERT INTO `file_data` VALUES (173, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\ca5e4ad6c6b3a8e97609844e4622a7d8.jpg', 'ca5e4ad6c6b3a8e97609844e4622a7d8.jpg', 'image/jpeg', 90);
INSERT INTO `file_data` VALUES (174, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\d62fc5840bcefa5d78d1a32a773c4bd0.jpg', 'd62fc5840bcefa5d78d1a32a773c4bd0.jpg', 'image/jpeg', 90);
INSERT INTO `file_data` VALUES (175, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\e3a81ea85fb43b398cf45ffa16b589cc.jpg', 'e3a81ea85fb43b398cf45ffa16b589cc.jpg', 'image/jpeg', 90);
INSERT INTO `file_data` VALUES (176, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\55a39aa573ced9e326ff2d8dc7acd91b.webp', '55a39aa573ced9e326ff2d8dc7acd91b.webp', 'image/webp', 89);
INSERT INTO `file_data` VALUES (177, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\64b34e922984f131141f9a7680fbf9e2.webp', '64b34e922984f131141f9a7680fbf9e2.webp', 'image/webp', 89);
INSERT INTO `file_data` VALUES (178, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\cf2242c08029c388162597fe412d4974.webp', 'cf2242c08029c388162597fe412d4974.webp', 'image/webp', 89);
INSERT INTO `file_data` VALUES (179, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\e01b44fdd1aae98e677f736e8556abdf.jpg', 'e01b44fdd1aae98e677f736e8556abdf.jpg', 'image/jpeg', 89);
INSERT INTO `file_data` VALUES (180, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\e3aaa40452861e5472d29aa0213b478e.webp', 'e3aaa40452861e5472d29aa0213b478e.webp', 'image/webp', 89);
INSERT INTO `file_data` VALUES (181, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\40c76f6954fcf61dc622096faa98899b.webp', '40c76f6954fcf61dc622096faa98899b.webp', 'image/webp', 91);
INSERT INTO `file_data` VALUES (182, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\549bf1fdec5b471b75d3b93aa08fcda5.webp', '549bf1fdec5b471b75d3b93aa08fcda5.webp', 'image/webp', 91);
INSERT INTO `file_data` VALUES (183, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\46937687594f4b626b68c77a4c6bf9a4.webp', '46937687594f4b626b68c77a4c6bf9a4.webp', 'image/webp', 91);
INSERT INTO `file_data` VALUES (184, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\b1ab20f75395bd31290c20d677a40041.webp', 'b1ab20f75395bd31290c20d677a40041.webp', 'image/webp', 91);
INSERT INTO `file_data` VALUES (185, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\002f26301a1f1d6b2ad20f0323d64223.webp', '002f26301a1f1d6b2ad20f0323d64223.webp', 'image/webp', 92);
INSERT INTO `file_data` VALUES (186, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\d4e8271cba1dbd2ee3e783cd2f334ad7.webp', 'd4e8271cba1dbd2ee3e783cd2f334ad7.webp', 'image/webp', 91);
INSERT INTO `file_data` VALUES (187, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\29a47a33c1080cb4ec91a39e6cdd7f5c.webp', '29a47a33c1080cb4ec91a39e6cdd7f5c.webp', 'image/webp', 92);
INSERT INTO `file_data` VALUES (188, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\771ad374de4dd773471b2428a8270891.webp', '771ad374de4dd773471b2428a8270891.webp', 'image/webp', 92);
INSERT INTO `file_data` VALUES (189, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\607347b869fe9f8cf398e9dba788ca32.webp', '607347b869fe9f8cf398e9dba788ca32.webp', 'image/webp', 92);
INSERT INTO `file_data` VALUES (190, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\ef004c63e56a7ef7021d944646c30ecb.webp', 'ef004c63e56a7ef7021d944646c30ecb.webp', 'image/webp', 92);
INSERT INTO `file_data` VALUES (191, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\0a5ef2ee643ad76dba6504697dc5cc81.webp', '0a5ef2ee643ad76dba6504697dc5cc81.webp', 'image/webp', 93);
INSERT INTO `file_data` VALUES (192, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\336ce6cb6fd4d07323a036561a07cc0d.webp', '336ce6cb6fd4d07323a036561a07cc0d.webp', 'image/webp', 93);
INSERT INTO `file_data` VALUES (193, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\01b45c6a628ed1c5cb0cbff3d8fe0fdd.webp', '01b45c6a628ed1c5cb0cbff3d8fe0fdd.webp', 'image/webp', 94);
INSERT INTO `file_data` VALUES (194, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\7957dcb7ed7c7fc9314ec49ac9c2b846.webp', '7957dcb7ed7c7fc9314ec49ac9c2b846.webp', 'image/webp', 93);
INSERT INTO `file_data` VALUES (195, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\2b2c527c237caf43b9a25c2854560d5b.webp', '2b2c527c237caf43b9a25c2854560d5b.webp', 'image/webp', 94);
INSERT INTO `file_data` VALUES (196, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\9157eeb92589b1b0b405d746863d520b.webp', '9157eeb92589b1b0b405d746863d520b.webp', 'image/webp', 94);
INSERT INTO `file_data` VALUES (197, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\64271b4ca6be0e95823335dca3b1e3ce.webp', '64271b4ca6be0e95823335dca3b1e3ce.webp', 'image/webp', 93);
INSERT INTO `file_data` VALUES (198, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\c0cc5f4b240fe2ae40d4193b954f6539.webp', 'c0cc5f4b240fe2ae40d4193b954f6539.webp', 'image/webp', 93);
INSERT INTO `file_data` VALUES (199, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\aaf4d56641957c9f9a04f2857944b057.webp', 'aaf4d56641957c9f9a04f2857944b057.webp', 'image/webp', 94);
INSERT INTO `file_data` VALUES (200, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\d7eda921af19f6ca9d94fb3e07427fae.webp', 'd7eda921af19f6ca9d94fb3e07427fae.webp', 'image/webp', 93);
INSERT INTO `file_data` VALUES (201, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\b0f15d8250fdc52f29357a5f5e3347dc.webp', 'b0f15d8250fdc52f29357a5f5e3347dc.webp', 'image/webp', 94);
INSERT INTO `file_data` VALUES (202, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\b1cb76c726b9097bda123d7e7a1c3882.webp', 'b1cb76c726b9097bda123d7e7a1c3882.webp', 'image/webp', 94);
INSERT INTO `file_data` VALUES (203, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\e73a2e1f93ee4bf2fc6ed77712469d3b.webp', 'e73a2e1f93ee4bf2fc6ed77712469d3b.webp', 'image/webp', 94);
INSERT INTO `file_data` VALUES (204, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\eca07972a1b49fb4bbd203e6e4babdad.webp', 'eca07972a1b49fb4bbd203e6e4babdad.webp', 'image/webp', 94);
INSERT INTO `file_data` VALUES (205, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\5cdc42b3530f8343e20fbc4bec22394d.webp', '5cdc42b3530f8343e20fbc4bec22394d.webp', 'image/webp', 95);
INSERT INTO `file_data` VALUES (206, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\7f36dee72cb5a0cbbdf8295bcb76e751.webp', '7f36dee72cb5a0cbbdf8295bcb76e751.webp', 'image/webp', 95);
INSERT INTO `file_data` VALUES (207, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\10fb7b1bdb1813f27a8fa1f3c0d3b73a.webp', '10fb7b1bdb1813f27a8fa1f3c0d3b73a.webp', 'image/webp', 95);
INSERT INTO `file_data` VALUES (208, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\439a3a9c56e18eb2babc95f574e2cb80.webp', '439a3a9c56e18eb2babc95f574e2cb80.webp', 'image/webp', 95);
INSERT INTO `file_data` VALUES (209, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\a5d9f67949ca675c0ea0ee077850b9b1.webp', 'a5d9f67949ca675c0ea0ee077850b9b1.webp', 'image/webp', 95);
INSERT INTO `file_data` VALUES (210, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\6cffd422ed3d2d36ba181f2529fad746.webp', '6cffd422ed3d2d36ba181f2529fad746.webp', 'image/webp', 96);
INSERT INTO `file_data` VALUES (211, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\8bf5224fbe0809f0f1e2149822e8c41c.webp', '8bf5224fbe0809f0f1e2149822e8c41c.webp', 'image/webp', 96);
INSERT INTO `file_data` VALUES (212, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\cf54ea5b705ec89d101444ad9f4b0766.webp', 'cf54ea5b705ec89d101444ad9f4b0766.webp', 'image/webp', 95);
INSERT INTO `file_data` VALUES (213, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\23a0fe1d3d750b5aafce7a610455d3ec.webp', '23a0fe1d3d750b5aafce7a610455d3ec.webp', 'image/webp', 96);
INSERT INTO `file_data` VALUES (214, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\1818e67f69b9c2cc57d39bceb21a870b.webp', '1818e67f69b9c2cc57d39bceb21a870b.webp', 'image/webp', 96);
INSERT INTO `file_data` VALUES (215, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\3198124c888506067e1dff0dda9d2305.webp', '3198124c888506067e1dff0dda9d2305.webp', 'image/webp', 96);
INSERT INTO `file_data` VALUES (216, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\b1e1a3be2e94a5e1ca8510746397bd87.webp', 'b1e1a3be2e94a5e1ca8510746397bd87.webp', 'image/webp', 96);
INSERT INTO `file_data` VALUES (217, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\a270fec075bd95a84b4d10ea40c26c0b.webp', 'a270fec075bd95a84b4d10ea40c26c0b.webp', 'image/webp', 97);
INSERT INTO `file_data` VALUES (218, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\d69e75b526373b1f751735974fd65ab0.webp', 'd69e75b526373b1f751735974fd65ab0.webp', 'image/webp', 97);
INSERT INTO `file_data` VALUES (219, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\ee359db81bc4e0e1a2b34275e4d38767.webp', 'ee359db81bc4e0e1a2b34275e4d38767.webp', 'image/webp', 97);
INSERT INTO `file_data` VALUES (220, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\6cffd422ed3d2d36ba181f2529fad746.webp', '6cffd422ed3d2d36ba181f2529fad746.webp', 'image/webp', 98);
INSERT INTO `file_data` VALUES (221, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\8bf5224fbe0809f0f1e2149822e8c41c.webp', '8bf5224fbe0809f0f1e2149822e8c41c.webp', 'image/webp', 98);
INSERT INTO `file_data` VALUES (222, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\23a0fe1d3d750b5aafce7a610455d3ec.webp', '23a0fe1d3d750b5aafce7a610455d3ec.webp', 'image/webp', 98);
INSERT INTO `file_data` VALUES (223, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\1818e67f69b9c2cc57d39bceb21a870b.webp', '1818e67f69b9c2cc57d39bceb21a870b.webp', 'image/webp', 98);
INSERT INTO `file_data` VALUES (224, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\3198124c888506067e1dff0dda9d2305.webp', '3198124c888506067e1dff0dda9d2305.webp', 'image/webp', 98);
INSERT INTO `file_data` VALUES (225, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\b1e1a3be2e94a5e1ca8510746397bd87.webp', 'b1e1a3be2e94a5e1ca8510746397bd87.webp', 'image/webp', 98);
INSERT INTO `file_data` VALUES (226, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\7b8f8f34f6361af38cb757427933f5c5.webp', '7b8f8f34f6361af38cb757427933f5c5.webp', 'image/webp', 99);
INSERT INTO `file_data` VALUES (227, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\e6731621634100bd81061857af7f2b66.webp', 'e6731621634100bd81061857af7f2b66.webp', 'image/webp', 99);
INSERT INTO `file_data` VALUES (228, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\f6ff005b0aa9c6ca3078d00ac0b50bb9.webp', 'f6ff005b0aa9c6ca3078d00ac0b50bb9.webp', 'image/webp', 99);
INSERT INTO `file_data` VALUES (229, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\fd809ae2af7258a3fa645a3a28931c7d.webp', 'fd809ae2af7258a3fa645a3a28931c7d.webp', 'image/webp', 99);
INSERT INTO `file_data` VALUES (230, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\6cffd422ed3d2d36ba181f2529fad746.webp', '6cffd422ed3d2d36ba181f2529fad746.webp', 'image/webp', 100);
INSERT INTO `file_data` VALUES (231, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\8bf5224fbe0809f0f1e2149822e8c41c.webp', '8bf5224fbe0809f0f1e2149822e8c41c.webp', 'image/webp', 100);
INSERT INTO `file_data` VALUES (232, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\23a0fe1d3d750b5aafce7a610455d3ec.webp', '23a0fe1d3d750b5aafce7a610455d3ec.webp', 'image/webp', 100);
INSERT INTO `file_data` VALUES (233, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\1818e67f69b9c2cc57d39bceb21a870b.webp', '1818e67f69b9c2cc57d39bceb21a870b.webp', 'image/webp', 100);
INSERT INTO `file_data` VALUES (234, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\3198124c888506067e1dff0dda9d2305.webp', '3198124c888506067e1dff0dda9d2305.webp', 'image/webp', 100);
INSERT INTO `file_data` VALUES (235, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\b1e1a3be2e94a5e1ca8510746397bd87.webp', 'b1e1a3be2e94a5e1ca8510746397bd87.webp', 'image/webp', 100);
INSERT INTO `file_data` VALUES (236, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\18ab7ad128d42533ac8c75a48240d356.webp', '18ab7ad128d42533ac8c75a48240d356.webp', 'image/webp', 101);
INSERT INTO `file_data` VALUES (237, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\40f732c032bd9336407b57785f60fecc.webp', '40f732c032bd9336407b57785f60fecc.webp', 'image/webp', 101);
INSERT INTO `file_data` VALUES (238, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\687f5477fcc3a732fb66e5eaad833799.webp', '687f5477fcc3a732fb66e5eaad833799.webp', 'image/webp', 101);
INSERT INTO `file_data` VALUES (239, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\02432b49dbdce2ecd1923251f9100d99.webp', '02432b49dbdce2ecd1923251f9100d99.webp', 'image/webp', 101);
INSERT INTO `file_data` VALUES (240, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\95268ad9b8e035485c00a369269c9991.webp', '95268ad9b8e035485c00a369269c9991.webp', 'image/webp', 101);
INSERT INTO `file_data` VALUES (241, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\98c4e87d27657f646350ac828941572e.webp', '98c4e87d27657f646350ac828941572e.webp', 'image/webp', 102);
INSERT INTO `file_data` VALUES (242, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\ac1f753ea278379a6b2826fb379f995d.webp', 'ac1f753ea278379a6b2826fb379f995d.webp', 'image/webp', 102);
INSERT INTO `file_data` VALUES (243, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\bb0e166c2c6cbe7449c365d5340732aa.webp', 'bb0e166c2c6cbe7449c365d5340732aa.webp', 'image/webp', 102);
INSERT INTO `file_data` VALUES (244, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\cce1d7c7d51b6a6b2bf4f6469f7272c0.webp', 'cce1d7c7d51b6a6b2bf4f6469f7272c0.webp', 'image/webp', 102);
INSERT INTO `file_data` VALUES (245, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\d5d5d5228b6775e53fac58d999792156.webp', 'd5d5d5228b6775e53fac58d999792156.webp', 'image/webp', 102);
INSERT INTO `file_data` VALUES (246, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\6bad621ba861c54c13f7925b2bee108d.webp', '6bad621ba861c54c13f7925b2bee108d.webp', 'image/webp', 103);
INSERT INTO `file_data` VALUES (247, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\192f923f3b7a7bd3862674e275624a0c.webp', '192f923f3b7a7bd3862674e275624a0c.webp', 'image/webp', 103);
INSERT INTO `file_data` VALUES (248, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\5909076723eb122b10faecdd8662cba3.webp', '5909076723eb122b10faecdd8662cba3.webp', 'image/webp', 103);
INSERT INTO `file_data` VALUES (249, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\17624139836ba79a1f1aa6ba4c29edb8.webp', '17624139836ba79a1f1aa6ba4c29edb8.webp', 'image/webp', 103);
INSERT INTO `file_data` VALUES (250, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\98c4e87d27657f646350ac828941572e.webp', '98c4e87d27657f646350ac828941572e.webp', 'image/webp', 104);
INSERT INTO `file_data` VALUES (251, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\bb0e166c2c6cbe7449c365d5340732aa.webp', 'bb0e166c2c6cbe7449c365d5340732aa.webp', 'image/webp', 103);
INSERT INTO `file_data` VALUES (252, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\ac1f753ea278379a6b2826fb379f995d.webp', 'ac1f753ea278379a6b2826fb379f995d.webp', 'image/webp', 104);
INSERT INTO `file_data` VALUES (253, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\bb0e166c2c6cbe7449c365d5340732aa.webp', 'bb0e166c2c6cbe7449c365d5340732aa.webp', 'image/webp', 104);
INSERT INTO `file_data` VALUES (254, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\cce1d7c7d51b6a6b2bf4f6469f7272c0.webp', 'cce1d7c7d51b6a6b2bf4f6469f7272c0.webp', 'image/webp', 104);
INSERT INTO `file_data` VALUES (255, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\d5d5d5228b6775e53fac58d999792156.webp', 'd5d5d5228b6775e53fac58d999792156.webp', 'image/webp', 104);
INSERT INTO `file_data` VALUES (256, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\6bad621ba861c54c13f7925b2bee108d.webp', '6bad621ba861c54c13f7925b2bee108d.webp', 'image/webp', 105);
INSERT INTO `file_data` VALUES (257, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\192f923f3b7a7bd3862674e275624a0c.webp', '192f923f3b7a7bd3862674e275624a0c.webp', 'image/webp', 105);
INSERT INTO `file_data` VALUES (258, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\5909076723eb122b10faecdd8662cba3.webp', '5909076723eb122b10faecdd8662cba3.webp', 'image/webp', 105);
INSERT INTO `file_data` VALUES (259, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\17624139836ba79a1f1aa6ba4c29edb8.webp', '17624139836ba79a1f1aa6ba4c29edb8.webp', 'image/webp', 105);
INSERT INTO `file_data` VALUES (260, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\bb0e166c2c6cbe7449c365d5340732aa.webp', 'bb0e166c2c6cbe7449c365d5340732aa.webp', 'image/webp', 105);
INSERT INTO `file_data` VALUES (261, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\98c4e87d27657f646350ac828941572e.webp', '98c4e87d27657f646350ac828941572e.webp', 'image/webp', 106);
INSERT INTO `file_data` VALUES (262, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\ac1f753ea278379a6b2826fb379f995d.webp', 'ac1f753ea278379a6b2826fb379f995d.webp', 'image/webp', 106);
INSERT INTO `file_data` VALUES (263, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\bb0e166c2c6cbe7449c365d5340732aa.webp', 'bb0e166c2c6cbe7449c365d5340732aa.webp', 'image/webp', 106);
INSERT INTO `file_data` VALUES (264, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\cce1d7c7d51b6a6b2bf4f6469f7272c0.webp', 'cce1d7c7d51b6a6b2bf4f6469f7272c0.webp', 'image/webp', 106);
INSERT INTO `file_data` VALUES (265, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\d5d5d5228b6775e53fac58d999792156.webp', 'd5d5d5228b6775e53fac58d999792156.webp', 'image/webp', 106);
INSERT INTO `file_data` VALUES (266, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\97708cb37580be928e6848dc30a7f689.webp', '97708cb37580be928e6848dc30a7f689.webp', 'image/webp', 108);
INSERT INTO `file_data` VALUES (267, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\b5a03fd0c700c8e051fd6cfdaceabbfe.webp', 'b5a03fd0c700c8e051fd6cfdaceabbfe.webp', 'image/webp', 108);
INSERT INTO `file_data` VALUES (268, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\f73563b901bb15b22aada6cd3b2f883c.webp', 'f73563b901bb15b22aada6cd3b2f883c.webp', 'image/webp', 108);
INSERT INTO `file_data` VALUES (269, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\3d524c622e0e31bda97091af59a7242b.jpg', '3d524c622e0e31bda97091af59a7242b.jpg', 'image/jpeg', 109);
INSERT INTO `file_data` VALUES (270, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\a7fc38b49fd4a494538db8c332b1b84f.jpg', 'a7fc38b49fd4a494538db8c332b1b84f.jpg', 'image/jpeg', 109);
INSERT INTO `file_data` VALUES (271, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\ea1602084750c6c9a6ad13572f70fdec.jpg', 'ea1602084750c6c9a6ad13572f70fdec.jpg', 'image/jpeg', 109);
INSERT INTO `file_data` VALUES (272, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\d2fe0bc8e9a017d6004cf42212525110.webp', 'd2fe0bc8e9a017d6004cf42212525110.webp', 'image/webp', 110);
INSERT INTO `file_data` VALUES (273, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\e379c196ee27f342fceadbdbc5b99848.webp', 'e379c196ee27f342fceadbdbc5b99848.webp', 'image/webp', 110);
INSERT INTO `file_data` VALUES (274, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\f3f010ca179323e4ff1337411f29bae1.jpg', 'f3f010ca179323e4ff1337411f29bae1.jpg', 'image/jpeg', 110);
INSERT INTO `file_data` VALUES (275, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\19f56c76c4548f13d3243ce6eff082e4.webp', '19f56c76c4548f13d3243ce6eff082e4.webp', 'image/webp', 111);
INSERT INTO `file_data` VALUES (276, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\98f643f997fe3317201fa89a8435f437.webp', '98f643f997fe3317201fa89a8435f437.webp', 'image/webp', 111);
INSERT INTO `file_data` VALUES (277, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\bc7dc910118ebdb4e9fba647530692ee.webp', 'bc7dc910118ebdb4e9fba647530692ee.webp', 'image/webp', 111);
INSERT INTO `file_data` VALUES (278, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\1fbb30557ebd2dfff7a69b2b3de32e97.webp', '1fbb30557ebd2dfff7a69b2b3de32e97.webp', 'image/webp', 112);
INSERT INTO `file_data` VALUES (279, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\bcd89a9876b8cb8cbb9b3de59562695e.webp', 'bcd89a9876b8cb8cbb9b3de59562695e.webp', 'image/webp', 111);
INSERT INTO `file_data` VALUES (280, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\2711c33c00c4ffb9cacd3477d3bca414.webp', '2711c33c00c4ffb9cacd3477d3bca414.webp', 'image/webp', 112);
INSERT INTO `file_data` VALUES (281, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\b5be32d2df6c97456d30fdd4897f84b1.webp', 'b5be32d2df6c97456d30fdd4897f84b1.webp', 'image/webp', 112);
INSERT INTO `file_data` VALUES (282, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\3d524c622e0e31bda97091af59a7242b.jpg', '3d524c622e0e31bda97091af59a7242b.jpg', 'image/jpeg', 113);
INSERT INTO `file_data` VALUES (283, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\a7fc38b49fd4a494538db8c332b1b84f.jpg', 'a7fc38b49fd4a494538db8c332b1b84f.jpg', 'image/jpeg', 113);
INSERT INTO `file_data` VALUES (284, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\e263b51f57bfbdff2ee472f0717ca14a.webp', 'e263b51f57bfbdff2ee472f0717ca14a.webp', 'image/webp', 112);
INSERT INTO `file_data` VALUES (285, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\ea1602084750c6c9a6ad13572f70fdec.jpg', 'ea1602084750c6c9a6ad13572f70fdec.jpg', 'image/jpeg', 113);
INSERT INTO `file_data` VALUES (286, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\d2fe0bc8e9a017d6004cf42212525110.webp', 'd2fe0bc8e9a017d6004cf42212525110.webp', 'image/webp', 114);
INSERT INTO `file_data` VALUES (287, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\e379c196ee27f342fceadbdbc5b99848.webp', 'e379c196ee27f342fceadbdbc5b99848.webp', 'image/webp', 114);
INSERT INTO `file_data` VALUES (288, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\f3f010ca179323e4ff1337411f29bae1.jpg', 'f3f010ca179323e4ff1337411f29bae1.jpg', 'image/jpeg', 114);
INSERT INTO `file_data` VALUES (289, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\0ae413da670ff98db2d480deb5ff6944.webp', '0ae413da670ff98db2d480deb5ff6944.webp', 'image/webp', 107);
INSERT INTO `file_data` VALUES (290, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\47df0eb521ce27292b7531645c2b7da9.jpg', '47df0eb521ce27292b7531645c2b7da9.jpg', 'image/jpeg', 107);
INSERT INTO `file_data` VALUES (291, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\8849af0f6bd8b58e1c6936977f49e720.webp', '8849af0f6bd8b58e1c6936977f49e720.webp', 'image/webp', 107);
INSERT INTO `file_data` VALUES (292, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\25152dc244d90277fc9587735f0711d3.webp', '25152dc244d90277fc9587735f0711d3.webp', 'image/webp', 107);
INSERT INTO `file_data` VALUES (293, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\cc97c55b5e6d07827fd5479f4cf99a09.webp', 'cc97c55b5e6d07827fd5479f4cf99a09.webp', 'image/webp', 107);
INSERT INTO `file_data` VALUES (294, 'F:\\WorkSpace\\Project\\saleClothes\\Image\\f808986659d6cc071425e2a94b071281.webp', 'f808986659d6cc071425e2a94b071281.webp', 'image/webp', 107);

-- ----------------------------
-- Table structure for gio_hang
-- ----------------------------
DROP TABLE IF EXISTS `gio_hang`;
CREATE TABLE `gio_hang`  (
  `gh_ma` int NOT NULL AUTO_INCREMENT,
  `color_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`gh_ma`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gio_hang
-- ----------------------------
INSERT INTO `gio_hang` VALUES (2, 255, 38, 1, 'XL', 'admin');
INSERT INTO `gio_hang` VALUES (5, 352, 49, 1, 'L', 'admin');
INSERT INTO `gio_hang` VALUES (34, 253, 36, 1, 'XL', 'admin');
INSERT INTO `gio_hang` VALUES (35, 252, 36, 1, 'L', 'admin');

-- ----------------------------
-- Table structure for hinh_thuc_thanh_toan
-- ----------------------------
DROP TABLE IF EXISTS `hinh_thuc_thanh_toan`;
CREATE TABLE `hinh_thuc_thanh_toan`  (
  `httt_ma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `httt_ten` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`httt_ma`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hinh_thuc_thanh_toan
-- ----------------------------

-- ----------------------------
-- Table structure for invalidted_token
-- ----------------------------
DROP TABLE IF EXISTS `invalidted_token`;
CREATE TABLE `invalidted_token`  (
  `token_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `expiry_time` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`token_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of invalidted_token
-- ----------------------------
INSERT INTO `invalidted_token` VALUES ('001e1451-6465-41f0-ab1f-ca96f83a9213', '2025-02-05 20:37:22.000000');
INSERT INTO `invalidted_token` VALUES ('06114825-27f8-4ca5-8a8e-36004de431e2', '2025-02-12 22:31:59.000000');
INSERT INTO `invalidted_token` VALUES ('0627f999-b5d1-4699-9a3f-96658fde05cf', '2025-02-22 19:17:46.000000');
INSERT INTO `invalidted_token` VALUES ('0e089bcf-f410-4fa7-88df-c35785199c2e', '2025-02-12 17:39:24.000000');
INSERT INTO `invalidted_token` VALUES ('22020847-a04a-44a1-9ffd-be00eb46664e', '2025-02-12 16:43:06.000000');
INSERT INTO `invalidted_token` VALUES ('29b95619-21e2-4a45-aa63-c1a0dfe9551b', '2025-02-11 09:36:55.000000');
INSERT INTO `invalidted_token` VALUES ('2b0669b0-c2e7-4a7c-bb27-78380cddd82e', '2025-02-23 20:27:14.000000');
INSERT INTO `invalidted_token` VALUES ('2e0d689b-6e97-43e6-8b5f-cad80d91b763', '2025-02-22 19:19:19.000000');
INSERT INTO `invalidted_token` VALUES ('4011a59a-7c08-4d45-bb05-05b44543f9ca', '2025-02-12 17:43:25.000000');
INSERT INTO `invalidted_token` VALUES ('5d9bc9af-5b04-4bc5-9b05-e0ea042db902', '2025-01-22 15:44:41.000000');
INSERT INTO `invalidted_token` VALUES ('64631d2c-155a-45d3-be54-ca2da9b94651', '2025-01-22 13:38:25.000000');
INSERT INTO `invalidted_token` VALUES ('6bf3ee4c-2802-4443-8152-0c914fa097ae', '2025-02-12 17:32:01.000000');
INSERT INTO `invalidted_token` VALUES ('702b27ab-b628-4e48-b144-edc2ae92ff69', '2025-02-17 14:52:43.000000');
INSERT INTO `invalidted_token` VALUES ('8216e773-40a9-4f75-ba8e-8b4b08251ea0', '2025-02-07 00:17:22.000000');
INSERT INTO `invalidted_token` VALUES ('839d4fdd-5a02-4b0b-8927-7d79f71b6a11', '2025-02-05 13:57:19.000000');
INSERT INTO `invalidted_token` VALUES ('867ef6c3-2a41-40e8-9b77-bd9b3a044dbd', '2025-02-23 20:27:47.000000');
INSERT INTO `invalidted_token` VALUES ('958a1cb6-1853-4586-ac37-72c97c0ae545', '2025-02-24 15:45:58.000000');
INSERT INTO `invalidted_token` VALUES ('96e2d555-4b72-4fa6-9fbe-4b0a39097e4b', '2025-02-17 07:37:58.000000');
INSERT INTO `invalidted_token` VALUES ('a11052ff-f6fc-439a-8f3f-094fc2388aa4', '2025-02-11 17:43:04.000000');
INSERT INTO `invalidted_token` VALUES ('a6c13e0c-03a8-42c6-b17b-1f1c3d66ce62', '2025-02-06 00:24:21.000000');
INSERT INTO `invalidted_token` VALUES ('abd85f8c-fef3-44c9-9b59-70146639df81', '2025-02-22 19:12:34.000000');
INSERT INTO `invalidted_token` VALUES ('af2e8864-273f-4d82-8609-39e70eb3ad2b', '2025-02-22 19:12:14.000000');
INSERT INTO `invalidted_token` VALUES ('b1f76787-eaa4-427f-8c34-722407e518e4', '2024-12-18 17:41:45.000000');
INSERT INTO `invalidted_token` VALUES ('b29c7916-fa0b-4d5d-8e01-d5821c94f390', '2025-02-04 18:18:48.000000');
INSERT INTO `invalidted_token` VALUES ('c27c8661-359b-4432-9c84-f64434ec9043', '2024-12-17 10:02:41.000000');
INSERT INTO `invalidted_token` VALUES ('c605368a-e21c-4576-b490-d914c4cdd423', '2024-12-17 17:53:06.000000');
INSERT INTO `invalidted_token` VALUES ('cbb93259-6fa2-4391-a756-e95cd17cfe1d', '2024-12-18 13:37:18.000000');
INSERT INTO `invalidted_token` VALUES ('de43a9b8-0df8-43ba-a6ee-c0aa9feaa49a', '2025-02-04 12:21:34.000000');
INSERT INTO `invalidted_token` VALUES ('e838a493-6953-4ca8-b4ee-5dd255e2d226', '2025-02-06 22:16:38.000000');
INSERT INTO `invalidted_token` VALUES ('ecc7a7d9-cc0e-4ae0-a209-b26f2dfb9a9e', '2024-12-18 15:41:36.000000');
INSERT INTO `invalidted_token` VALUES ('ee667672-45f3-45f8-9ec3-8889db9c00c2', '2024-12-13 11:32:24.000000');
INSERT INTO `invalidted_token` VALUES ('f36690a9-f633-41b5-b6ad-b02b6531e04f', '2025-02-23 15:05:41.000000');
INSERT INTO `invalidted_token` VALUES ('fc44bba7-3151-4b63-8938-8218b700c1e9', '2025-02-12 17:46:54.000000');

-- ----------------------------
-- Table structure for khach_hang
-- ----------------------------
DROP TABLE IF EXISTS `khach_hang`;
CREATE TABLE `khach_hang`  (
  `kh_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `kh_cmnd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `kh_diachi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `kh_dienthoai` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `kh_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `kh_gioitinh` bit(1) NULL DEFAULT NULL,
  `kh_namsinh` int NULL DEFAULT NULL,
  `kh_ngaysinh` int NULL DEFAULT NULL,
  `kh_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `kh_ten` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `kh_thangsinh` int NULL DEFAULT NULL,
  PRIMARY KEY (`kh_username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of khach_hang
-- ----------------------------
INSERT INTO `khach_hang` VALUES ('admin', NULL, NULL, NULL, 'tranhuyyhoang2004@gmail.com', NULL, 0, 0, '$2a$10$CZ7VnMJki9SeWFtZm3VT2.dgKPIyoYHhpnavQZeLDk44iwFCA0MZm', 'hoang', 0);
INSERT INTO `khach_hang` VALUES ('dinhphuc', NULL, NULL, NULL, 'tranhuyyhoang2003324@gmail.com', NULL, 0, 0, '$2a$10$JrL6I4/lyLcVPvvXysh53OiTp0Ae.N35DMWAJAxl8SxIr9n/IIMoG', 'Đình Phuc', 0);
INSERT INTO `khach_hang` VALUES ('quanquyen2003', NULL, NULL, NULL, 'quanquyen2003@gmail.com', NULL, 0, 0, '$2a$10$M2MjWEXUDfU1FC.tt93KheJufWlgyNbHaipWpwvNQWlz.m/wS6W9C', 'Quanquyen', 0);
INSERT INTO `khach_hang` VALUES ('tranhuyhoang2004', NULL, NULL, '0965523100', 'tranhuyyhoang20044@gmail.com', b'1', 2004, 20, '$2a$10$NApq8/UzIBo1PJzVcuVQouR1pIze0jEsF92eyxRW0KYqewscnbAXC', 'Trần Huy Hoàng', 3);

-- ----------------------------
-- Table structure for khachhang_roles
-- ----------------------------
DROP TABLE IF EXISTS `khachhang_roles`;
CREATE TABLE `khachhang_roles`  (
  `kh_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `FKt7r63apu2h77y574ow4pb1juu`(`kh_username` ASC) USING BTREE,
  CONSTRAINT `FKt7r63apu2h77y574ow4pb1juu` FOREIGN KEY (`kh_username`) REFERENCES `khach_hang` (`kh_username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of khachhang_roles
-- ----------------------------
INSERT INTO `khachhang_roles` VALUES ('admin', 'ADMIN');
INSERT INTO `khachhang_roles` VALUES ('tranhuyhoang2004', 'CUSTOMER');
INSERT INTO `khachhang_roles` VALUES ('dinhphuc', 'CUSTOMER');
INSERT INTO `khachhang_roles` VALUES ('quanquyen2003', 'CUSTOMER');

-- ----------------------------
-- Table structure for khuyen_mai
-- ----------------------------
DROP TABLE IF EXISTS `khuyen_mai`;
CREATE TABLE `khuyen_mai`  (
  `km_ma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `km_denngay` date NULL DEFAULT NULL,
  `km_noidung` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `km_ten` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `km_tungay` date NULL DEFAULT NULL,
  PRIMARY KEY (`km_ma`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of khuyen_mai
-- ----------------------------

-- ----------------------------
-- Table structure for loai_san_pham
-- ----------------------------
DROP TABLE IF EXISTS `loai_san_pham`;
CREATE TABLE `loai_san_pham`  (
  `lsp_ma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lsp_mota` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lsp_ten` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`lsp_ma`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of loai_san_pham
-- ----------------------------

-- ----------------------------
-- Table structure for nhan_vien
-- ----------------------------
DROP TABLE IF EXISTS `nhan_vien`;
CREATE TABLE `nhan_vien`  (
  `nv_ma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nv_diachi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nv_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nv_luong` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nv_ngayvao` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nv_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nv_ten` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nv_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nv_vaitro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ch_ma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`nv_ma`) USING BTREE,
  INDEX `FK2dmdi6lqffhrqebvrjjfjiywp`(`ch_ma` ASC) USING BTREE,
  CONSTRAINT `FK2dmdi6lqffhrqebvrjjfjiywp` FOREIGN KEY (`ch_ma`) REFERENCES `cua_hang` (`ch_ma`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nhan_vien
-- ----------------------------

-- ----------------------------
-- Table structure for product_size
-- ----------------------------
DROP TABLE IF EXISTS `product_size`;
CREATE TABLE `product_size`  (
  `variant_id` int NOT NULL,
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `FKcw0gi0nr1j3wkprhhhj5j2x4x`(`variant_id` ASC) USING BTREE,
  CONSTRAINT `FKcw0gi0nr1j3wkprhhhj5j2x4x` FOREIGN KEY (`variant_id`) REFERENCES `product_variant` (`variant_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_size
-- ----------------------------
INSERT INTO `product_size` VALUES (62, '2XL');
INSERT INTO `product_size` VALUES (62, 'L');
INSERT INTO `product_size` VALUES (62, 'M');
INSERT INTO `product_size` VALUES (63, 'S');
INSERT INTO `product_size` VALUES (63, 'XL');
INSERT INTO `product_size` VALUES (63, '2XL');
INSERT INTO `product_size` VALUES (63, 'L');
INSERT INTO `product_size` VALUES (63, 'M');
INSERT INTO `product_size` VALUES (66, 'S');
INSERT INTO `product_size` VALUES (67, 'S');
INSERT INTO `product_size` VALUES (67, 'XL');
INSERT INTO `product_size` VALUES (67, '2XL');
INSERT INTO `product_size` VALUES (67, 'L');
INSERT INTO `product_size` VALUES (67, 'M');
INSERT INTO `product_size` VALUES (66, 'XL');
INSERT INTO `product_size` VALUES (66, '2XL');
INSERT INTO `product_size` VALUES (66, 'L');
INSERT INTO `product_size` VALUES (66, 'M');
INSERT INTO `product_size` VALUES (68, 'S');
INSERT INTO `product_size` VALUES (68, 'XL');
INSERT INTO `product_size` VALUES (68, '2XL');
INSERT INTO `product_size` VALUES (68, 'L');
INSERT INTO `product_size` VALUES (68, 'M');
INSERT INTO `product_size` VALUES (73, 'XL');
INSERT INTO `product_size` VALUES (73, 'L');
INSERT INTO `product_size` VALUES (72, 'S');
INSERT INTO `product_size` VALUES (72, 'XL');
INSERT INTO `product_size` VALUES (72, 'M');
INSERT INTO `product_size` VALUES (74, '2XL');
INSERT INTO `product_size` VALUES (74, 'L');
INSERT INTO `product_size` VALUES (74, 'M');
INSERT INTO `product_size` VALUES (75, 'XL');
INSERT INTO `product_size` VALUES (75, '2XL');
INSERT INTO `product_size` VALUES (75, 'L');
INSERT INTO `product_size` VALUES (75, 'M');
INSERT INTO `product_size` VALUES (76, 'S');
INSERT INTO `product_size` VALUES (76, 'XL');
INSERT INTO `product_size` VALUES (76, '2XL');
INSERT INTO `product_size` VALUES (76, 'L');
INSERT INTO `product_size` VALUES (76, 'M');
INSERT INTO `product_size` VALUES (77, 'XL');
INSERT INTO `product_size` VALUES (77, '2XL');
INSERT INTO `product_size` VALUES (77, 'L');
INSERT INTO `product_size` VALUES (77, 'M');
INSERT INTO `product_size` VALUES (78, 'S');
INSERT INTO `product_size` VALUES (78, 'XL');
INSERT INTO `product_size` VALUES (78, '2XL');
INSERT INTO `product_size` VALUES (78, 'L');
INSERT INTO `product_size` VALUES (78, 'M');
INSERT INTO `product_size` VALUES (80, 'XL');
INSERT INTO `product_size` VALUES (79, 'S');
INSERT INTO `product_size` VALUES (80, 'L');
INSERT INTO `product_size` VALUES (80, 'M');
INSERT INTO `product_size` VALUES (79, 'XL');
INSERT INTO `product_size` VALUES (79, 'L');
INSERT INTO `product_size` VALUES (79, 'M');
INSERT INTO `product_size` VALUES (81, 'XL');
INSERT INTO `product_size` VALUES (81, '2XL');
INSERT INTO `product_size` VALUES (82, 'S');
INSERT INTO `product_size` VALUES (82, 'XL');
INSERT INTO `product_size` VALUES (82, '2XL');
INSERT INTO `product_size` VALUES (82, 'L');
INSERT INTO `product_size` VALUES (82, 'M');
INSERT INTO `product_size` VALUES (83, '2XL');
INSERT INTO `product_size` VALUES (83, 'L');
INSERT INTO `product_size` VALUES (84, 'S');
INSERT INTO `product_size` VALUES (84, 'XL');
INSERT INTO `product_size` VALUES (84, '2XL');
INSERT INTO `product_size` VALUES (84, 'L');
INSERT INTO `product_size` VALUES (84, 'M');
INSERT INTO `product_size` VALUES (85, 'S');
INSERT INTO `product_size` VALUES (85, 'XL');
INSERT INTO `product_size` VALUES (85, '2XL');
INSERT INTO `product_size` VALUES (85, 'L');
INSERT INTO `product_size` VALUES (85, 'M');
INSERT INTO `product_size` VALUES (86, 'S');
INSERT INTO `product_size` VALUES (86, 'XL');
INSERT INTO `product_size` VALUES (86, 'L');
INSERT INTO `product_size` VALUES (87, 'S');
INSERT INTO `product_size` VALUES (87, 'XL');
INSERT INTO `product_size` VALUES (87, '2XL');
INSERT INTO `product_size` VALUES (87, 'L');
INSERT INTO `product_size` VALUES (87, 'M');
INSERT INTO `product_size` VALUES (88, 'S');
INSERT INTO `product_size` VALUES (88, 'XL');
INSERT INTO `product_size` VALUES (88, 'L');
INSERT INTO `product_size` VALUES (89, 'S');
INSERT INTO `product_size` VALUES (90, 'S');
INSERT INTO `product_size` VALUES (90, 'XL');
INSERT INTO `product_size` VALUES (90, '2XL');
INSERT INTO `product_size` VALUES (90, 'L');
INSERT INTO `product_size` VALUES (90, 'M');
INSERT INTO `product_size` VALUES (89, 'XL');
INSERT INTO `product_size` VALUES (89, '2XL');
INSERT INTO `product_size` VALUES (89, 'L');
INSERT INTO `product_size` VALUES (89, 'M');
INSERT INTO `product_size` VALUES (91, 'S');
INSERT INTO `product_size` VALUES (91, 'XL');
INSERT INTO `product_size` VALUES (91, '2XL');
INSERT INTO `product_size` VALUES (91, 'L');
INSERT INTO `product_size` VALUES (91, 'M');
INSERT INTO `product_size` VALUES (92, 'XL');
INSERT INTO `product_size` VALUES (92, '2XL');
INSERT INTO `product_size` VALUES (92, 'L');
INSERT INTO `product_size` VALUES (93, 'S');
INSERT INTO `product_size` VALUES (93, 'L');
INSERT INTO `product_size` VALUES (93, 'M');
INSERT INTO `product_size` VALUES (94, 'S');
INSERT INTO `product_size` VALUES (94, '2XL');
INSERT INTO `product_size` VALUES (94, 'L');
INSERT INTO `product_size` VALUES (94, 'M');
INSERT INTO `product_size` VALUES (95, 'S');
INSERT INTO `product_size` VALUES (95, '2XL');
INSERT INTO `product_size` VALUES (95, 'L');
INSERT INTO `product_size` VALUES (95, 'M');
INSERT INTO `product_size` VALUES (96, '2XL');
INSERT INTO `product_size` VALUES (96, 'L');
INSERT INTO `product_size` VALUES (96, 'M');
INSERT INTO `product_size` VALUES (97, 'S');
INSERT INTO `product_size` VALUES (97, 'XL');
INSERT INTO `product_size` VALUES (97, '2XL');
INSERT INTO `product_size` VALUES (97, 'L');
INSERT INTO `product_size` VALUES (98, '2XL');
INSERT INTO `product_size` VALUES (98, 'L');
INSERT INTO `product_size` VALUES (98, 'M');
INSERT INTO `product_size` VALUES (99, 'S');
INSERT INTO `product_size` VALUES (99, '2XL');
INSERT INTO `product_size` VALUES (99, 'L');
INSERT INTO `product_size` VALUES (100, '2XL');
INSERT INTO `product_size` VALUES (100, 'L');
INSERT INTO `product_size` VALUES (100, 'M');
INSERT INTO `product_size` VALUES (101, 'S');
INSERT INTO `product_size` VALUES (101, 'XL');
INSERT INTO `product_size` VALUES (101, '2XL');
INSERT INTO `product_size` VALUES (101, 'L');
INSERT INTO `product_size` VALUES (101, 'M');
INSERT INTO `product_size` VALUES (102, 'S');
INSERT INTO `product_size` VALUES (102, 'L');
INSERT INTO `product_size` VALUES (102, 'M');
INSERT INTO `product_size` VALUES (103, 'S');
INSERT INTO `product_size` VALUES (103, 'XL');
INSERT INTO `product_size` VALUES (103, '2XL');
INSERT INTO `product_size` VALUES (103, 'L');
INSERT INTO `product_size` VALUES (103, 'M');
INSERT INTO `product_size` VALUES (104, 'S');
INSERT INTO `product_size` VALUES (104, 'L');
INSERT INTO `product_size` VALUES (104, 'M');
INSERT INTO `product_size` VALUES (105, 'XL');
INSERT INTO `product_size` VALUES (105, '2XL');
INSERT INTO `product_size` VALUES (105, 'L');
INSERT INTO `product_size` VALUES (105, 'M');
INSERT INTO `product_size` VALUES (106, 'S');
INSERT INTO `product_size` VALUES (106, 'L');
INSERT INTO `product_size` VALUES (106, 'M');
INSERT INTO `product_size` VALUES (111, 'S');
INSERT INTO `product_size` VALUES (111, 'M');
INSERT INTO `product_size` VALUES (112, 'S');
INSERT INTO `product_size` VALUES (112, 'XL');
INSERT INTO `product_size` VALUES (112, 'L');
INSERT INTO `product_size` VALUES (113, 'S');
INSERT INTO `product_size` VALUES (113, 'M');
INSERT INTO `product_size` VALUES (114, 'S');
INSERT INTO `product_size` VALUES (114, 'L');
INSERT INTO `product_size` VALUES (114, 'M');
INSERT INTO `product_size` VALUES (110, 'S');
INSERT INTO `product_size` VALUES (108, 'S');
INSERT INTO `product_size` VALUES (107, 'S');
INSERT INTO `product_size` VALUES (109, 'S');
INSERT INTO `product_size` VALUES (110, 'L');
INSERT INTO `product_size` VALUES (110, 'M');
INSERT INTO `product_size` VALUES (109, 'M');
INSERT INTO `product_size` VALUES (108, 'L');
INSERT INTO `product_size` VALUES (108, 'M');
INSERT INTO `product_size` VALUES (107, 'M');

-- ----------------------------
-- Table structure for product_variant
-- ----------------------------
DROP TABLE IF EXISTS `product_variant`;
CREATE TABLE `product_variant`  (
  `variant_id` int NOT NULL AUTO_INCREMENT,
  `stock_quantity` int NOT NULL,
  `color_id` int NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`variant_id`) USING BTREE,
  INDEX `FK7ic0arew3txgoctacehy5nal9`(`color_id` ASC) USING BTREE,
  INDEX `FKkpcn3jidqowtn13c9dbo1xmp6`(`product_id` ASC) USING BTREE,
  CONSTRAINT `FK7ic0arew3txgoctacehy5nal9` FOREIGN KEY (`color_id`) REFERENCES `color` (`colorid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKkpcn3jidqowtn13c9dbo1xmp6` FOREIGN KEY (`product_id`) REFERENCES `san_pham` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_variant
-- ----------------------------
INSERT INTO `product_variant` VALUES (62, 324, 252, 36);
INSERT INTO `product_variant` VALUES (63, 423, 253, 36);
INSERT INTO `product_variant` VALUES (66, 34, 255, 38);
INSERT INTO `product_variant` VALUES (67, 32, 256, 38);
INSERT INTO `product_variant` VALUES (68, 334, 302, 39);
INSERT INTO `product_variant` VALUES (72, 34, 254, 42);
INSERT INTO `product_variant` VALUES (73, 37, 303, 43);
INSERT INTO `product_variant` VALUES (74, 54, 352, 44);
INSERT INTO `product_variant` VALUES (75, 32, 353, 44);
INSERT INTO `product_variant` VALUES (76, 12, 354, 45);
INSERT INTO `product_variant` VALUES (77, 32, 353, 45);
INSERT INTO `product_variant` VALUES (78, 86, 302, 46);
INSERT INTO `product_variant` VALUES (79, 23, 356, 47);
INSERT INTO `product_variant` VALUES (80, 12, 357, 47);
INSERT INTO `product_variant` VALUES (81, 12, 354, 48);
INSERT INTO `product_variant` VALUES (82, 35, 352, 49);
INSERT INTO `product_variant` VALUES (83, 34, 354, 50);
INSERT INTO `product_variant` VALUES (84, 42, 303, 50);
INSERT INTO `product_variant` VALUES (85, 31, 255, 51);
INSERT INTO `product_variant` VALUES (86, 14, 253, 51);
INSERT INTO `product_variant` VALUES (87, 23, 354, 52);
INSERT INTO `product_variant` VALUES (88, 14, 253, 52);
INSERT INTO `product_variant` VALUES (89, 32, 255, 53);
INSERT INTO `product_variant` VALUES (90, 13, 354, 53);
INSERT INTO `product_variant` VALUES (91, 14, 354, 54);
INSERT INTO `product_variant` VALUES (92, 23, 253, 54);
INSERT INTO `product_variant` VALUES (93, 26, 402, 55);
INSERT INTO `product_variant` VALUES (94, 12, 403, 55);
INSERT INTO `product_variant` VALUES (95, 34, 253, 56);
INSERT INTO `product_variant` VALUES (96, 15, 255, 56);
INSERT INTO `product_variant` VALUES (97, 34, 354, 57);
INSERT INTO `product_variant` VALUES (98, 15, 255, 57);
INSERT INTO `product_variant` VALUES (99, 34, 253, 58);
INSERT INTO `product_variant` VALUES (100, 15, 255, 58);
INSERT INTO `product_variant` VALUES (101, 34, 255, 59);
INSERT INTO `product_variant` VALUES (102, 23, 253, 59);
INSERT INTO `product_variant` VALUES (103, 23, 253, 60);
INSERT INTO `product_variant` VALUES (104, 23, 253, 60);
INSERT INTO `product_variant` VALUES (105, 14, 253, 61);
INSERT INTO `product_variant` VALUES (106, 23, 253, 61);
INSERT INTO `product_variant` VALUES (107, 23, 354, 62);
INSERT INTO `product_variant` VALUES (108, 43, 357, 62);
INSERT INTO `product_variant` VALUES (109, 13, 404, 62);
INSERT INTO `product_variant` VALUES (110, 8, 405, 62);
INSERT INTO `product_variant` VALUES (111, 45, 253, 63);
INSERT INTO `product_variant` VALUES (112, 72, 406, 63);
INSERT INTO `product_variant` VALUES (113, 13, 404, 63);
INSERT INTO `product_variant` VALUES (114, 8, 405, 63);

-- ----------------------------
-- Table structure for san_pham
-- ----------------------------
DROP TABLE IF EXISTS `san_pham`;
CREATE TABLE `san_pham`  (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `base_price` double NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `discount_percentage` int NOT NULL,
  `instruction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `material` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dmc_ma` int NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `FK8x47louidm0hea9e65pbhrmbf`(`dmc_ma` ASC) USING BTREE,
  CONSTRAINT `FK8x47louidm0hea9e65pbhrmbf` FOREIGN KEY (`dmc_ma`) REFERENCES `danh_muc_con` (`dmc_ma`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of san_pham
-- ----------------------------
INSERT INTO `san_pham` VALUES (36, 973000, 'Với áo Ember, nàng sẽ cảm nhận được sự nhẹ nhàng và tinh tế trong từng chi tiết, khẳng định gu thời trang nữ tính và hiện đại!\nLựa chọn chất liệu lụa cao cấp, mềm mại và bóng nhẹ, mang lại cảm giác thoải mái, thoáng mát đồng thời giúp áo giữ được phom dáng hoàn hảo.\nHọa tiết hoa chìm được dệt tinh tế, tạo hiệu ứng thị giác sang trọng mà vẫn giữ được sự tối giản.\nDáng suông nhẹ, ôm vừa vặn cơ thể. Tay áo dài giúp người mặc linh hoạt hơn trong phối đồ và phù hợp với nhiều hoàn cảnh.', 30, '.', 'Lụa', 'Áo sơ mi lụa Ember', NULL, 7);
INSERT INTO `san_pham` VALUES (38, 830000, 'Thiết kế được lựa chọn trong BST Office Divas, mang đậm dấu ấn phong cách hiện đại dành riêng cho phái đẹp. Ngôn ngữ thiết kế tối giản được điểm xuyết khéo léo bằng các chi tiết cách điệu mềm mại không chỉ nâng tầm vẻ đẹp thanh lịch mà còn thể hiện cá tính độc lập và gu thời trang đẳng cấp.\n\nÁo sơ mi Croptop Office là sự kết hợp hoàn hảo giữa phong cách trẻ trung, hiện đại bên cạnh nét thanh lịch chuyên nghiệp.\n\n- Chất liệu lụa gân cao cấp, mềm mại, thoáng mát, giữ phom dáng\n\n- Thiết kế croptop hiện đại, trẻ trung, dễ phối với quần hoặc chân váy cạp cao\n\n- Cổ tròn xếp ly tinh tế, tạo điểm nhấn thanh lịch và nữ tính\n\n- Tay dài bo gấu gọn gàng, mang lại vẻ chỉn chu và thoải mái\n\n- Phù hợp cho nhiều dịp như đi làm, gặp gỡ bạn bè, hoặc sự kiện', 30, '.', 'Vải lụa', 'Áo sơ mi Croptop Office', NULL, 7);
INSERT INTO `san_pham` VALUES (39, 763000, 'Áo sơ mi Spring Glow là sự kết hợp hoàn hảo giữa chất liệu lụa mềm mại bên cạnh thiết kế dáng croptop trẻ trung, mang đến vẻ ngoài vừa nữ tính vừa hiện đại. Đây là món đồ không thể thiếu trong tủ đồ mùa xuân của nàng, giúp tôn lên vẻ đẹp đầy năng lượng và tinh tế.\n\n- Chất liệu lụa cao cấp với độ bóng nhẹ, mềm mại, tạo cảm giác thoải mái và sang trọng khi mặc.\n\n- Thiết kế dáng ôm croptop, tôn lên vòng eo thanh mảnh, mang lại vẻ ngoài năng động và cuốn hút.\n\n- Đường may gọn gàng, tỉ mỉ giúp áo giữ được phom dáng hoàn hảo.\n\n- Áo dễ dàng phối hợp với quần âu, chân váy bút chì, hoặc quần jeans, phù hợp từ phong cách công sở chuyên nghiệp đến trang phục dạo phố hiện đại.', 30, '.', 'Vải lụa', 'Áo sơ mi lụa Spring Glow', NULL, 7);
INSERT INTO `san_pham` VALUES (42, 623000, 'Thiết kế nằm trong BST Dreamy Bloom, người bạn đồng hành hoàn hảo của phái đẹp, giúp nàng luôn tỏa sáng.\n\nSản phẩm được may từ chất liệu cao cấp, mềm mại như nâng niu làn da người mặc. Từng chi tiết của trang phục đều được chăm chút tỉ mỉ, từ kiểu dáng thanh thoát đến phần hoạ tiết nữ tính, cho nàng tự tin thả dáng trong từng khoảnh khắc.', 30, '.', 'Vải lụa', 'Áo sơ mi Dreamy cổ kiểu', NULL, 7);
INSERT INTO `san_pham` VALUES (43, 945000, 'Áo sơ mi Scarlet Wave là sự lựa chọn hoàn hảo cho những cô nàng yêu thích phong cách tinh giản và thanh lịch. Với thiết kế dáng suông thoải mái và chất liệu lụa mềm mại, chiếc áo này mang lại cảm giác nhẹ nhàng, sang trọng nhưng vẫn rất linh hoạt trong mọi hoàn cảnh.\n\n- Chất liệu lụa cao cấp với độ bóng nhẹ, mềm mại, mang lại cảm giác dễ chịu và sang trọng khi mặc.\n\n- Thiết kế dáng suông rộng, thoải mái, phù hợp với nhiều dáng người, giúp che khuyết điểm một cách tinh tế.\n\n- Áo cổ đức truyền thống, tôn lên vẻ lịch sự và trang nhã.\n\n- Hai tông màu trắng - xanh ghi đá dễ dàng lựa chọn.', 30, 'Áo sơ mi Scarlet Wave có thể kết hợp với quần âu, chân váy bút chì hoặc quần jeans để tạo nên nhiều phong cách khác nhau, từ công sở chuyên nghiệp đến dạo phố nhẹ nhàng.', 'Vải lụa', 'Áo sơ mi Scarlet Wave', NULL, 7);
INSERT INTO `san_pham` VALUES (44, 1345000, 'Thiết kế nằm trong BST “SOLAR - SHINE TOGETHER”, hướng tới tôn vinh tính nữ hiện đại thông qua từng đường nét – từ sự mềm mại và duyên dáng đến sự mạnh mẽ và tự chủ. Mỗi sản phẩm đều thể hiện tính kiêu hãnh, đồng thời phá bỏ mọi rào cản để phụ nữ tự do thể hiện cá tính riêng, sống đúng với con người thật của mình.\n\nÁo blazer mang đến vẻ đẹp thanh lịch và hiện đại với thiết kế trẻ trung, thời thượng. Chất liệu vải Serge dệt vân chéo cao cấp không chỉ mềm mại mà còn bền bỉ, giữ được phom dáng chuẩn trong suốt thời gian dài sử dụng. Họa tiết kẻ sọc tinh tế giúp chiếc áo trở nên nổi bật nhưng vẫn dễ phối đồ, tạo nên nhiều phong cách thời trang đa dạng.', 50, '.', 'Tuysi', 'Áo Blazer kẻ Serge', NULL, 11);
INSERT INTO `san_pham` VALUES (45, 995000, 'Nằm trong BST mùa thu đông 2024, “SOLAR - SHINE TOGETHER” được trình làng với giới mộ điệu như một bản hòa ca tôn vinh tinh thần tự do, sự lạc quan và vẻ đẹp rực rỡ của phái đẹp. Đánh dấu sự chuyển mình của phụ nữ trong thời hiện đại, vượt qua những tiêu chuẩn “kép” áp đặt lên họ.\n\nNhững thiết kế trong BST hướng tới tôn vinh tính nữ hiện đại thông qua từng đường nét – từ sự mềm mại và duyên dáng đến sự mạnh mẽ và tự chủ. Mỗi sản phẩm đều thể hiện tính kiêu hãnh, đồng thời phá bỏ mọi rào cản để phụ nữ tự do thể hiện cá tính riêng, sống đúng với con người thật của mình.\n\nTự tin, nổi bật với phong cách chuyên nghiệp, thanh lịch và hiện đại, áo vest là lựa chọn lý tưởng cho môi trường công sở hoặc các dịp trang trọng. Được làm từ chất liệu Tuytsi cao cấp, áo vest mềm mại nhưng vẫn giữ phom dáng chuẩn, giúp người mặc thoải mái suốt cả ngày dài. Màu beige trung tính mang đến vẻ ngoài nhã nhặn, dễ phối đồ, phù hợp với nhiều tone màu da và phong cách thời trang khác nhau. \n\nThiết kế ôm vừa vặn với các đường may tỉ mỉ tạo cảm giác gọn gàng, thanh thoát. Áo vest có phần cổ tạo kiểu nổi bật. Phần eo thắt nhẹ nhàng tôn dáng Dễ dàng phối cùng quần âu hoặc chân váy để tạo nên một bộ trang phục công sở hoàn hảo. ', 50, '.', 'Tuysi', 'Áo vest Tuytsi You Beige', NULL, 11);
INSERT INTO `san_pham` VALUES (46, 1323000, 'Sở hữu những đường nét tinh xảo cùng phom dáng được cách tân khéo léo bởi loạt sáng tạo trong thiết kế, Dusky Charm là lời khẳng định về sự tinh tế vượt thời gian của người phụ nữ hiện đại.\n\nÁo vest lựa chọn gam màu vàng bơ tươi sáng, điểm nhấn nổi bật mang đến cảm giác nhẹ nhàng, trẻ trung nhưng vẫn sang trọng. Thiết kế hoàn hảo cho môi trường công sở, các buổi gặp gỡ bạn bè hay sự kiện quan trọng.\n\n- Chất liệu Tuytsi cao cấp, mềm mại, giữ phom dáng tốt và thoáng mát\n\n- Dáng áo suông thoải mái, cổ tròn thanh lịch, tay lỡ nhẹ nhàng\n\n- Thiết kế độ dài ngang hông, tạo cảm giác cân đối và dễ phối đồ\n\n- Màu sắc vàng bơ tươi sáng, nhã nhặn mà hiện đại\n\n- Dễ dàng kết hợp với đầm ôm, quần tây, hoặc chân váy bút chì để hoàn thiện vẻ ngoài thời thượng', 30, '.', 'Tuysi', 'Áo vest Dusky Charm', NULL, 11);
INSERT INTO `san_pham` VALUES (47, 8050000, 'Áo khoác thiết kế cổ chữ K bản to, dáng dài cùng màu sắc sang trọng, kiểu dáng vô cùng đẳng cấp. Với chất liệu áo làm bằng Lông cừu cao cấp, khâu tay 100% với các mũi khâu được khâu tỉ mỉ khéo léo không làm lộ chỉ. Với đặc điểm mềm, mỏng, nhẹ và giữ ấm tuyệt đối. Nắm bắt kịp xu hướng cũng như nhu cầu này, IVY moda đã cho ra đời BST áo khoác dạ ép lông cừu cao cấp đạt chuẩn với nhiều kiểu dáng, màu sắc cho phái đẹp lựa chọn.\n\nLấy cảm hứng từ trang phục của quân đội châu Âu, những chiếc áo trench coat từ lâu đã khẳng định sự “thống trị” trong tủ đồ thời trang thu – đông. Thời thượng, thanh lịch nhưng không kém phần cá tính, áo trench coat dễ dàng làm xiêu lòng cả những quý cô khó tính nhất.', 30, '.', 'Dạ', 'Wool Trench Coat', NULL, 12);
INSERT INTO `san_pham` VALUES (48, 1393000, 'Ánh nắng mùa thu, dù không còn chói chang, vẫn mang đến cảm giác ấm áp, nhẹ nhàng tựa như sự mềm mại và lãng mạn luôn hiện hữu bên trong mỗi người phụ nữ. Đây chính là nguồn cảm hứng chủ đạo cho những thiết kế tinh tế trong BST Muse of the Sun.\n\nMỗi thiết kế công sở hiện đại trên gam màu vàng nhạt của sắc thu mang đến cảm giác vừa ấm áp vừa dịu mắt, như tia nắng lấp lánh trên từng trang phục.\n\nÁo vest Tweed Kose là một thiết kế thanh lịch và hiện đại, mang đến vẻ ngoài sang trọng với chất liệu tweed cao cấp, bền bỉ. \n\nThiết kế cổ đức không chân mang đến sự tinh tế, mới mẻ, giúp chiếc áo vừa giữ được nét trang trọng của vest nhưng vẫn tạo cảm giác nhẹ nhàng, thoải mái cho người mặc. Độ dài ngang hông tôn dáng, cân đối và dễ phối đồ. Với kiểu dáng này, bạn có thể dễ dàng kết hợp áo vest với áo sơ mi, chân váy, quần tây hay quần jeans để tạo nên phong cách từ chuyên nghiệp đến thời trang đường phố.', 30, '.', 'Tweed', 'Áo vest Tweed Kose', NULL, 11);
INSERT INTO `san_pham` VALUES (49, 873000, 'Chân váy Manche Longue thể hiện sự nữ tính và chuyên nghiệp, với thiết kế dáng bút chì kinh điển được cách tân để mang lại vẻ đẹp hiện đại, thanh lịch cho nàng công sở. \n\nLựa chọn chất liệu vải cao cấp mềm mại, co giãn vừa phải, đảm bảo sự thoải mái khi di chuyển mà vẫn giữ được phom dáng hoàn hảo.\n\nKiểu dáng bút chì ôm cơ thể, tôn lên đường cong mềm mại, mang lại vẻ ngoài thanh thoát và chuyên nghiệp. Cùng với đó, thiết kế cạp cao giúp tôn dáng vòng eo, tạo hiệu ứng đôi chân dài hơn để dễ phối hợp với nhiều kiểu áo.', 30, '.', 'Tuysi', 'Chân váy bút chì Manche Longue', NULL, 20);
INSERT INTO `san_pham` VALUES (50, 945000, ',', 30, '.', 'Tweed', 'Chân váy Tweed bút chì Scarlet', NULL, 20);
INSERT INTO `san_pham` VALUES (51, 665000, 'Chân váy Glow Skirt là lựa chọn hoàn hảo cho những quý cô yêu thích sự thanh lịch và nữ tính, đồng thời muốn thể hiện sự chuyên nghiệp trong môi trường công sở.\n\nĐược làm từ vải tuytsi cao cấp, mềm mại, giúp giữ phom dáng chuẩn, tạo cảm giác thoải mái trong suốt cả ngày dài mà vẫn tôn lên vẻ ngoài sang trọng.\n\nKiểu dáng bút chì ôm nhẹ cơ thể, tôn lên đường cong nữ tính nhưng vẫn giữ được sự thanh thoát và chuyên nghiệp. Hơn nữa, cạp cao tôn dáng eo thon gọn, để nàng dễ dàng kết hợp với áo sơ mi, blouse hoặc các kiểu áo công sở khác.\n\nChân váy có 2 màu cơ bản là đen và trắng, dễ dàng phối hợp với nhiều màu sắc và kiểu dáng áo khác nhau, mang lại vẻ đẹp tối giản nhưng đầy ấn tượng.', 30, '.', 'Tuysi', 'Chân váy 2 lớp Glow Skirt', NULL, 20);
INSERT INTO `san_pham` VALUES (52, 763000, '.', 30, '.', 'Khaki', 'Chân váy Mini Khaki', NULL, 19);
INSERT INTO `san_pham` VALUES (53, 805000, 'Chân zuýp độ dài qua gối, dáng Midi nhẹ nhàng, tôn hông và che khuyết điểm tốt. Thiết kế được tạo kiểu cạp xếp 2 lớp kết hợp đai nối cá tính. Khuy cài, đai nối kim loại được bọc màu đối lập với chân váy, mang đến sự nổi bật. \n\nChân váy lựa chọn chất liệu vải Tweed dày dặn, bên trong lớp lót lụa mềm mại, thích hợp với thời tiết sang Thu - vào Đông. Thiết kế  basic dễ  mix với nhiều kiểu áo khác nhau để tạo nên set đồ thời thượng. ', 30, '.', 'Tweed', 'Chân váy 2 khuy phối dây', NULL, 19);
INSERT INTO `san_pham` VALUES (54, 833000, 'Lựa chọn hoàn hảo cho những quý cô yêu thích sự thanh lịch và nữ tính, đồng thời muốn thể hiện sự chuyên nghiệp trong môi trường công sở.\n\nĐược làm từ vải Thô Cotton cao cấp, giúp giữ phom dáng chuẩn, tạo cảm giác thoải mái trong suốt cả ngày dài mà vẫn tôn lên vẻ ngoài sang trọng.\n\nKiểu dáng A ôm nhẹ cơ thể, tôn lên đường cong nữ tính nhưng vẫn giữ được sự thanh thoát và chuyên nghiệp. Hơn nữa, cạp cao tôn dáng eo thon gọn, để nàng dễ dàng kết hợp với áo sơ mi, blouse hoặc các kiểu áo công sở khác.\n\nChân váy có 2 màu cơ bản là đen và nâu, dễ dàng phối hợp với nhiều màu sắc và kiểu dáng áo khác nhau, mang lại vẻ đẹp tối giản nhưng đầy ấn tượng.', 30, '.', 'Thô', 'Chân váy A Velvet Claw', NULL, 19);
INSERT INTO `san_pham` VALUES (55, 623000, 'Chân váy chữ A dáng ngắn, lưng cao giấu cạp, tôn vòng eo thon gọn của nàng. Thiết kế được cách điệu, tạo điểm xếp lớp nhẹ nhàng. \n\nChất liệu Tweed sang trọng, đứng phom. Chân váy trẻ trung dành cho nàng diện trong nhiều dịp, dễ dàng mix cùng nhiều kiểu áo với các phong cách khác nhau.', 30, '.', 'Tweed', 'Chân váy Feline Allure', NULL, 19);
INSERT INTO `san_pham` VALUES (56, 623000, 'Chân váy xòe Flare nữ tính, thanh lịch và thoải mái. Được làm từ chất liệu thun vân hoa mềm mại, chiếc váy mang lại cảm giác dễ chịu khi mặc, đồng thời tạo hiệu ứng bề mặt tinh tế và độc đáo.\n\n- Chất liệu thun vân hoa cao cấp, mềm mại, thoáng mát và co giãn tốt\n\n- Dáng xòe nhẹ nhàng, độ dài qua gối, tạo vẻ thanh thoát, nữ tính\n\n- Điểm nhấn bề mặt vân hoa tinh tế, tăng thêm sự sang trọng mà cuốn hút\n\n- Linh hoạt trong nhiều tình huống như công sở, dạo phố hoặc dự tiệc nhẹ\n\n- Dễ dàng kết hợp với áo sơ mi, áo thun ôm hoặc áo blazer để hoàn thiện phong cách', 30, '.', 'Thun', 'Chân váy xòe Flare', NULL, 19);
INSERT INTO `san_pham` VALUES (57, 973000, 'Quần suông Tuysi LOUTLIE là lựa chọn hoàn hảo dành cho những quý cô yêu thích phong cách thanh lịch và hiện đại. Được thiết kế từ chất liệu Tuysi cao cấp, chiếc quần không chỉ mang lại cảm giác thoải mái, mềm mại mà còn giúp giữ phom dáng chuẩn chỉnh, tạo nên vẻ ngoài tự tin, cuốn hút.\n\n- Chất liệu Tuytsi cao cấp, mềm mại, giữ phom dáng tốt, mang lại sự thoải mái khi mặc\n\n- Thiết kế dáng suông hiện đại, cạp cao tôn dáng và tạo sự thanh thoát\n\n- Linh hoạt cho công sở, dạo phố hay sự kiện \n\n- Kết hợp hoàn hảo với áo sơ mi, áo thun hoặc áo vest để hoàn thiện phong cách thanh lịch và thời thượng', 30, '.', 'Tuysi', 'Quần suông Tuytsi LOUTLIE', NULL, 24);
INSERT INTO `san_pham` VALUES (58, 645000, 'Thiết kế quần ống loe kết hợp hoàn hảo giữa nét cổ điển và phong cách hiện đại, mang đến vẻ ngoài quyến rũ, thời thượng cho người mặc.\n\n- Chất liệu Tuytsi cao cấp, thoáng mát, mềm mại và giữ phom dáng đẹp\n\n- Thiết kế ống loe nhẹ nhàng, tôn lên chiều cao và vẻ thanh thoát\n\n- Cạp cao vừa vặn, tôn vòng eo, phù hợp với nhiều dáng người\n\n- Dễ dàng phối hợp với các kiểu áo để tạo phong cách khác nhau\n\n- Phù hợp cho nhiều dịp: đi làm, dạo phố, gặp gỡ bạn bè, hoặc các sự kiện quan trọng', 50, '.', 'Tuysi', 'Quần Tuytsi ống loe Artiste', NULL, 24);
INSERT INTO `san_pham` VALUES (59, 1043000, '.', 30, '.', '.', 'Quần dài Tuysi Sleek', NULL, 24);
INSERT INTO `san_pham` VALUES (60, 903000, 'Thiết kế nằm trong Léopard De Luxe - BST dành riêng cho những quý cô đang tìm kiếm sự táo bạo trong phong cách công sở hiện đại. \n\nSử dụng chất kiệu vải Tuysi mềm nhẹ và thoải mái, Jungle che khuyết điểm tốt giúp nàng tự tin mỗi khi xuất hiện.\n\nQuần kèm hai túi sườn và độ dài ngang mắt cá chân. Thiết kế tỉ mỉ, nhấn thêm cạp thun co giãn, phù hợp với nhiều dáng người khác nhau. \n\nNàng công sở có thể kết hợp với nhiều kiểu áo để có những bộ trang phục chỉn chu và lịch sự.  ', 30, '.', 'Tuysi', 'Quần suông Jungle', NULL, 24);
INSERT INTO `san_pham` VALUES (61, 903000, 'Thiết kế mang đến vẻ ngoài hiện đại và sang trọng với dáng suông thanh lịch, phù hợp cho cả công sở lẫn các dịp đi chơi. Chất liệu Tuysi cao cấp giúp chiếc quần giữ dáng tốt, đồng thời mang lại cảm giác thoải mái khi mặc.\n\nĐiểm nhấn đặc biệt của chiếc quần là phần đính khuy kiểu tinh tế, tạo nên nét độc đáo cho trang phục. Dáng suông giúp chiếc quần dễ dàng kết hợp với áo sơ mi, áo thun hay blazer, người diện sẽ luôn sự tự tin trong mọi hoàn cảnh.\n\nQuần suông Black  Tuysi  là một item không thể thiếu cho những ai yêu thích phong cách tối giản nhưng vẫn muốn nổi bật với những chi tiết thời trang tinh tế.', 30, '.', 'Tuysi', 'Quần suông Black Tuysi khuy kiểu', NULL, 24);
INSERT INTO `san_pham` VALUES (62, 545000, 'Sử dụng chất kiệu vải Tuysi mềm nhẹ, thoải mái, thiết kế che khuyết điểm tốt giúp nàng tự tin mỗi khi xuất hiện.\n\nQuần kèm hai túi sườn và độ dài ngang mắt cá chân. Thiết kế chỉn chu, tỉ mỉ, nhấn cạp kiểu độc đáo với những đường gấp nối mới lạ.\n\nNàng công sở có thể kết hợp với nhiều kiểu áo khác nhau để mang đến những bộ trang phục chỉn chu và lịch sự.  ', 50, '.', 'Tuysi', 'Quần dài Tuysi Piece', NULL, 24);
INSERT INTO `san_pham` VALUES (63, 903000, 'Thiết kế tối giản nhưng không kém phần tinh tế, dành cho những cô nàng yêu thích phong cách thanh lịch và hiện đại. Sử dụng chất liệu len cao cấp, chiếc áo mang đến sự mềm mại, ấm áp. Áo ôm nhẹ nhàng theo đường nét cơ thể, tạo cảm giác vừa vặn và thoải mái.\n\nChiếc áo khoác là lựa chọn lý tưởng cho các ngày thời tiết se lạnh. Dễ dàng kết hợp cùng đầm, áo thun, quần jeans hay chân váy, mang lại vẻ ngoài gọn gàng và tinh tế, phù hợp cho cả phong cách hàng ngày lẫn công sở.', 30, '.', 'Len', 'Áo khoác len Ôm cơ bản', NULL, 10);

SET FOREIGN_KEY_CHECKS = 1;
