-- MySQL dump 10.13  Distrib 8.0.24, for Linux (x86_64)
--
-- Host: localhost    Database: gpt
-- ------------------------------------------------------
-- Server version	8.0.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcement` (
  `id` bigint NOT NULL,
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '公告内容',
  `sort` int DEFAULT '0' COMMENT '排序',
  `type` int DEFAULT NULL COMMENT '公告类型 1-公告、2-指南',
  `data_version` int DEFAULT '0' COMMENT '数据版本（默认为0，每次编辑+1）',
  `deleted` int DEFAULT '0' COMMENT '是否删除：0-否、1-是',
  `creator` bigint DEFAULT '0' COMMENT '创建人编号（默认为0）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（默认为创建时服务器时间）',
  `operator` bigint DEFAULT '0' COMMENT '操作人编号（默认为0）',
  `operate_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间（每次更新时自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_table`
--

DROP TABLE IF EXISTS `gen_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table` (
  `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) COLLATE utf8mb4_general_ci DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作 sub主子表操作）',
  `package_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) COLLATE utf8mb4_general_ci DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='代码生成业务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table`
--

LOCK TABLES `gen_table` WRITE;
/*!40000 ALTER TABLE `gen_table` DISABLE KEYS */;
INSERT INTO `gen_table` VALUES (1,'announcement','公告','',NULL,'Announcement','crud','com.tenant.ai','ai','announcement','公告','luanyu','0','/','{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"chatGPT\",\"treeCode\":\"\"}','admin','2023-04-18 21:36:02','','2023-04-18 22:51:29',''),(2,'gpt_key','gptkey','',NULL,'GptKey','crud','com.tenant.ai','ai','key','gptkey','luanyu','0','/','{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"chatGPT\",\"treeCode\":\"\"}','admin','2023-04-18 21:36:08','','2023-04-18 22:47:52',''),(3,'pay_config','支付配置','',NULL,'PayConfig','crud','com.tenant.ai','ai','payconfig','支付配置','luanyu','0','/','{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"chatGPT\",\"treeCode\":\"\"}','admin','2023-04-18 21:36:18','','2023-04-18 22:45:49',''),(4,'product','产品表','',NULL,'Product','crud','com.tenant.ai','ai','product','产品','luanyu','0','/','{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"chatGPT\",\"treeCode\":\"\"}','admin','2023-04-18 21:36:28','','2023-04-18 22:40:47',''),(5,'refueling_kit','加油包','',NULL,'RefuelingKit','crud','com.tenant.ai','ai','kit','加油包','luanyu','0','/','{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"chatGPT\",\"treeCode\":\"\"}','admin','2023-04-18 21:36:36','','2023-04-18 22:37:21',''),(6,'sys_config2','系统配置','',NULL,'SysConfig2','crud','com.tenant.ai','ai','config','系统配置','luanyu','0','/','{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"chatGPT\",\"treeCode\":\"\"}','admin','2023-04-18 21:36:42','','2023-04-18 22:31:14',''),(7,'t_order','订单表','',NULL,'TOrder','crud','com.tenant.ai','ai','order','订单','luanyu','0','/','{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"chatGPT\",\"treeCode\":\"\"}','admin','2023-04-18 21:36:53','','2023-04-18 22:26:05',''),(8,'use_log','使用记录表','',NULL,'UseLog','crud','com.tenant.ai','ai','userlog','使用记录','luanyu','0','/','{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"chatGPT\",\"treeCode\":\"\"}','admin','2023-04-18 21:37:02','','2023-04-18 22:13:57',''),(9,'user','用户表','',NULL,'User','crud','com.tenant.ai','ai','user','用户','luanyu','0','/','{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"chatGPT\",\"treeCode\":\"\"}','admin','2023-04-18 21:37:11','','2023-04-18 21:55:57',''),(10,'wx_log','微信日志','',NULL,'WxLog','crud','com.tenant.ai','ai','log','微信日志','luanyu','0','/','{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"chatGPT\",\"treeCode\":\"\"}','admin','2023-04-18 21:37:24','','2023-04-18 21:59:29','');
/*!40000 ALTER TABLE `gen_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_table_column`
--

DROP TABLE IF EXISTS `gen_table_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table_column` (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) COLLATE utf8mb4_general_ci DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='代码生成业务表字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table_column`
--

LOCK TABLES `gen_table_column` WRITE;
/*!40000 ALTER TABLE `gen_table_column` DISABLE KEYS */;
INSERT INTO `gen_table_column` VALUES (1,'1','id','','bigint','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2023-04-18 21:36:03',NULL,'2023-04-18 22:51:29'),(2,'1','title','标题','varchar(30)','String','title','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2023-04-18 21:36:03',NULL,'2023-04-18 22:51:30'),(3,'1','content','公告内容','text','String','content','0','0',NULL,'1','1','1',NULL,'EQ','summernote','',3,'admin','2023-04-18 21:36:04',NULL,'2023-04-18 22:51:30'),(4,'1','sort','排序','int','Long','sort','0','0',NULL,'1','1','1',NULL,'EQ','input','',4,'admin','2023-04-18 21:36:04',NULL,'2023-04-18 22:51:31'),(5,'1','type','公告类型','int','Long','type','0','0',NULL,'1','1','1','1','EQ','select','announcement_type',5,'admin','2023-04-18 21:36:05',NULL,'2023-04-18 22:51:32'),(6,'1','data_version','数据版本（默认为0，每次编辑+1）','int','Long','dataVersion','0','0',NULL,'1','1','1',NULL,'EQ','input','',6,'admin','2023-04-18 21:36:05',NULL,'2023-04-18 22:51:32'),(7,'1','deleted','是否删除','int','Long','deleted','0','0',NULL,'1','1','1','1','EQ','select','is_not',7,'admin','2023-04-18 21:36:06',NULL,'2023-04-18 22:51:33'),(8,'1','creator','创建人编号（默认为0）','bigint','Long','creator','0','0',NULL,'1','1','1',NULL,'EQ','input','',8,'admin','2023-04-18 21:36:06',NULL,'2023-04-18 22:51:33'),(9,'1','create_time','创建时间（默认为创建时服务器时间）','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',9,'admin','2023-04-18 21:36:06',NULL,'2023-04-18 22:51:34'),(10,'1','operator','操作人编号（默认为0）','bigint','Long','operator','0','0',NULL,'1','1','1',NULL,'EQ','input','',10,'admin','2023-04-18 21:36:07',NULL,'2023-04-18 22:51:35'),(11,'1','operate_time','操作时间（每次更新时自动更新）','datetime','Date','operateTime','0','0',NULL,'1','1','1','1','BETWEEN','datetime','',11,'admin','2023-04-18 21:36:08',NULL,'2023-04-18 22:51:35'),(12,'2','id','','bigint','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2023-04-18 21:36:12',NULL,'2023-04-18 22:47:53'),(13,'2','key','key','varchar(100)','String','key','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2023-04-18 21:36:13',NULL,'2023-04-18 22:47:53'),(14,'2','use_number','使用次数','int','Long','useNumber','0','0',NULL,'1','1','1',NULL,'EQ','input','',3,'admin','2023-04-18 21:36:13',NULL,'2023-04-18 22:47:54'),(15,'2','sort','排序','int','Long','sort','0','0',NULL,'1','1','1',NULL,'EQ','input','',4,'admin','2023-04-18 21:36:13',NULL,'2023-04-18 22:47:54'),(16,'2','state','状态 0 启用 1禁用','int','Long','state','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2023-04-18 21:36:14',NULL,'2023-04-18 22:47:55'),(17,'2','data_version','数据版本（默认为0，每次编辑+1）','int','Long','dataVersion','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2023-04-18 21:36:15',NULL,'2023-04-18 22:47:55'),(18,'2','deleted','是否删除','int','Long','deleted','0','0',NULL,'1','1','1','1','EQ','select','is_not',7,'admin','2023-04-18 21:36:16',NULL,'2023-04-18 22:47:56'),(19,'2','creator','创建人编号（默认为0）','bigint','Long','creator','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2023-04-18 21:36:16',NULL,'2023-04-18 22:47:57'),(20,'2','create_time','创建时间（默认为创建时服务器时间）','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',9,'admin','2023-04-18 21:36:17',NULL,'2023-04-18 22:47:57'),(21,'2','operator','操作人编号（默认为0）','bigint','Long','operator','0','0',NULL,'1','1','1','1','EQ','input','',10,'admin','2023-04-18 21:36:17',NULL,'2023-04-18 22:47:58'),(22,'2','operate_time','操作时间（每次更新时自动更新）','datetime','Date','operateTime','0','0',NULL,'1','1','1','1','BETWEEN','datetime','',11,'admin','2023-04-18 21:36:18',NULL,'2023-04-18 22:47:59'),(23,'3','id','','bigint','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2023-04-18 21:36:19',NULL,'2023-04-18 22:45:50'),(24,'3','pid','商户id','int','Long','pid','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2023-04-18 21:36:19',NULL,'2023-04-18 22:45:51'),(25,'3','secret_key','商户密钥','varchar(100)','String','secretKey','0','0',NULL,'1','1','1',NULL,'EQ','input','',3,'admin','2023-04-18 21:36:20',NULL,'2023-04-18 22:45:51'),(26,'3','notify_url','回调域名','varchar(255)','String','notifyUrl','0','0',NULL,'1','1','1',NULL,'EQ','input','',4,'admin','2023-04-18 21:36:20',NULL,'2023-04-18 22:45:52'),(27,'3','return_url','跳转通知地址','varchar(255)','String','returnUrl','0','0',NULL,'1','1','1',NULL,'EQ','input','',5,'admin','2023-04-18 21:36:21',NULL,'2023-04-18 22:45:53'),(28,'3','submit_url','支付请求域名','varchar(255)','String','submitUrl','0','0',NULL,'1','1','1',NULL,'EQ','input','',6,'admin','2023-04-18 21:36:21',NULL,'2023-04-18 22:45:54'),(29,'3','ali_app_id','支付宝appid','varchar(50)','String','aliAppId','0','0',NULL,'1','1','1',NULL,'EQ','input','',7,'admin','2023-04-18 21:36:21',NULL,'2023-04-18 22:45:55'),(30,'3','ali_private_key','支付宝应用私钥','varchar(3000)','String','aliPrivateKey','0','0',NULL,'1','1','1',NULL,'EQ','textarea','',8,'admin','2023-04-18 21:36:22',NULL,'2023-04-18 22:45:55'),(31,'3','ali_public_key','支付宝应用公钥','varchar(500)','String','aliPublicKey','0','0',NULL,'1','1','1',NULL,'EQ','textarea','',9,'admin','2023-04-18 21:36:22',NULL,'2023-04-18 22:45:56'),(32,'3','ali_gateway_url','支付宝接口地址','varchar(100)','String','aliGatewayUrl','0','0',NULL,'1','1','1',NULL,'EQ','input','',10,'admin','2023-04-18 21:36:23',NULL,'2023-04-18 22:45:57'),(33,'3','ali_notify_url','支付宝回调地址','varchar(100)','String','aliNotifyUrl','0','0',NULL,'1','1','1',NULL,'EQ','input','',11,'admin','2023-04-18 21:36:23',NULL,'2023-04-18 22:45:58'),(34,'3','ali_return_url','支付宝页面跳转地址','varchar(100)','String','aliReturnUrl','0','0',NULL,'1','1','1',NULL,'EQ','input','',12,'admin','2023-04-18 21:36:24',NULL,'2023-04-18 22:45:59'),(35,'3','pay_type','支付类型 0 易支付 1微信 2支付宝 3支付宝、微信','tinyint','Long','payType','0','0',NULL,'1','1','1','1','EQ','input','',13,'admin','2023-04-18 21:36:25',NULL,'2023-04-18 22:45:59'),(36,'3','data_version','数据版本（默认为0，每次编辑+1）','int','Long','dataVersion','0','0',NULL,'1','1','1',NULL,'EQ','input','',14,'admin','2023-04-18 21:36:25',NULL,'2023-04-18 22:46:00'),(37,'3','deleted','是否删除','int','Long','deleted','0','0',NULL,'1','1','1','1','EQ','select','is_not',15,'admin','2023-04-18 21:36:25',NULL,'2023-04-18 22:46:01'),(38,'3','creator','创建人编号（默认为0）','bigint','Long','creator','0','0',NULL,'1','1','1',NULL,'EQ','input','',16,'admin','2023-04-18 21:36:26',NULL,'2023-04-18 22:46:02'),(39,'3','create_time','创建时间（默认为创建时服务器时间）','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',17,'admin','2023-04-18 21:36:27',NULL,'2023-04-18 22:46:02'),(40,'3','operator','操作人编号（默认为0）','bigint','Long','operator','0','0',NULL,'1','1','1',NULL,'EQ','input','',18,'admin','2023-04-18 21:36:27',NULL,'2023-04-18 22:46:03'),(41,'3','operate_time','操作时间（每次更新时自动更新）','datetime','Date','operateTime','0','0',NULL,'1','1','1','1','BETWEEN','datetime','',19,'admin','2023-04-18 21:36:28',NULL,'2023-04-18 22:46:04'),(42,'4','id','','bigint','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2023-04-18 21:36:29',NULL,'2023-04-18 22:40:47'),(43,'4','name','产品名','varchar(30)','String','name','0','0',NULL,'1','1','1','1','LIKE','input','',2,'admin','2023-04-18 21:36:29',NULL,'2023-04-18 22:40:48'),(44,'4','price','价格','decimal(10,2)','BigDecimal','price','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2023-04-18 21:36:30',NULL,'2023-04-18 22:40:49'),(45,'4','type','类型','tinyint','Long','type','0','0',NULL,'1','1','1','1','EQ','select','use_type',4,'admin','2023-04-18 21:36:30',NULL,'2023-04-18 22:40:49'),(46,'4','number_times','次数','int','Long','numberTimes','0','0',NULL,'1','1','1',NULL,'EQ','input','',5,'admin','2023-04-18 21:36:31',NULL,'2023-04-18 22:40:50'),(47,'4','monthly_number','月卡每日可使用次数','int','Long','monthlyNumber','0','0',NULL,'1','1','1',NULL,'EQ','input','',6,'admin','2023-04-18 21:36:31',NULL,'2023-04-18 22:40:50'),(48,'4','sort','排序','int','Long','sort','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2023-04-18 21:36:32',NULL,'2023-04-18 22:40:51'),(49,'4','stock','库存数','int','Long','stock','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2023-04-18 21:36:32',NULL,'2023-04-18 22:40:51'),(50,'4','data_version','数据版本（默认为0，每次编辑+1）','int','Long','dataVersion','0','0',NULL,'1','1','1','1','EQ','input','',9,'admin','2023-04-18 21:36:34',NULL,'2023-04-18 22:40:52'),(51,'4','deleted','是否删除','int','Long','deleted','0','0',NULL,'1','1','1','1','EQ','select','is_not',10,'admin','2023-04-18 21:36:34',NULL,'2023-04-18 22:40:53'),(52,'4','creator','创建人编号（默认为0）','bigint','Long','creator','0','0',NULL,'1','1','1','1','EQ','input','',11,'admin','2023-04-18 21:36:34',NULL,'2023-04-18 22:40:53'),(53,'4','create_time','创建时间（默认为创建时服务器时间）','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',12,'admin','2023-04-18 21:36:35',NULL,'2023-04-18 22:40:54'),(54,'4','operator','操作人编号（默认为0）','bigint','Long','operator','0','0',NULL,'1','1','1',NULL,'EQ','input','',13,'admin','2023-04-18 21:36:35',NULL,'2023-04-18 22:40:54'),(55,'4','operate_time','操作时间（每次更新时自动更新）','datetime','Date','operateTime','0','0',NULL,'1','1','1','1','BETWEEN','datetime','',14,'admin','2023-04-18 21:36:36',NULL,'2023-04-18 22:40:55'),(56,'5','id','','bigint','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2023-04-18 21:36:37',NULL,'2023-04-18 22:37:22'),(57,'5','product_id','产品id','bigint','Long','productId','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2023-04-18 21:36:38',NULL,'2023-04-18 22:37:22'),(58,'5','user_id','用户id','bigint','Long','userId','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2023-04-18 21:36:39',NULL,'2023-04-18 22:37:23'),(59,'5','number_times','可使用次数','int','Long','numberTimes','0','0',NULL,'1','1','1',NULL,'EQ','input','',4,'admin','2023-04-18 21:36:39',NULL,'2023-04-18 22:37:23'),(60,'5','data_version','数据版本（默认为0，每次编辑+1）','int','Long','dataVersion','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2023-04-18 21:36:39',NULL,'2023-04-18 22:37:24'),(61,'5','deleted','是否删除','int','Long','deleted','0','0',NULL,'1','1','1','1','EQ','select','is_not',6,'admin','2023-04-18 21:36:40',NULL,'2023-04-18 22:37:25'),(62,'5','creator','创建人编号（默认为0）','bigint','Long','creator','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2023-04-18 21:36:40',NULL,'2023-04-18 22:37:26'),(63,'5','create_time','创建时间（默认为创建时服务器时间）','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',8,'admin','2023-04-18 21:36:41',NULL,'2023-04-18 22:37:26'),(64,'5','operator','操作人编号（默认为0）','bigint','Long','operator','0','0',NULL,'1','1','1',NULL,'EQ','input','',9,'admin','2023-04-18 21:36:41',NULL,'2023-04-18 22:37:27'),(65,'5','operate_time','操作时间（每次更新时自动更新）','datetime','Date','operateTime','0','0',NULL,'1','1','1','1','BETWEEN','datetime','',10,'admin','2023-04-18 21:36:42',NULL,'2023-04-18 22:37:27'),(66,'6','id','','bigint','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2023-04-18 21:36:43',NULL,'2023-04-18 22:31:15'),(67,'6','registration_method','注册模式','tinyint','Long','registrationMethod','0','0',NULL,'1','1','1','1','EQ','select','registration_method',2,'admin','2023-04-18 21:36:43',NULL,'2023-04-18 22:31:16'),(68,'6','key_switch','是否禁用自动禁用key','tinyint','Long','keySwitch','0','0',NULL,'1','1','1','1','EQ','select','is_not',3,'admin','2023-04-18 21:36:44',NULL,'2023-04-18 22:31:17'),(69,'6','default_times','默认注册次数','int','Long','defaultTimes','0','0',NULL,'1','1','1',NULL,'EQ','input','',4,'admin','2023-04-18 21:36:44',NULL,'2023-04-18 22:31:17'),(70,'6','ali_access_key_id','阿里云accessKeyId','varchar(100)','String','aliAccessKeyId','0','0',NULL,'1','1','1',NULL,'EQ','input','',5,'admin','2023-04-18 21:36:45',NULL,'2023-04-18 22:31:18'),(71,'6','ali_secret','阿里云secret','varchar(100)','String','aliSecret','0','0',NULL,'1','1','1',NULL,'EQ','input','',6,'admin','2023-04-18 21:36:47',NULL,'2023-04-18 22:31:19'),(72,'6','ali_sign_name','阿里云短信签名','varchar(50)','String','aliSignName','0','0',NULL,'1','1','1',NULL,'LIKE','input','',7,'admin','2023-04-18 21:36:49',NULL,'2023-04-18 22:31:20'),(73,'6','ali_template_code','阿里云短信模版id','varchar(50)','String','aliTemplateCode','0','0',NULL,'1','1','1',NULL,'EQ','input','',8,'admin','2023-04-18 21:36:49',NULL,'2023-04-18 22:31:20'),(74,'6','data_version','数据版本（默认为0，每次编辑+1）','int','Long','dataVersion','0','0',NULL,'1','1','1',NULL,'EQ','input','',9,'admin','2023-04-18 21:36:50',NULL,'2023-04-18 22:31:22'),(75,'6','deleted','是否删除','int','Long','deleted','0','0',NULL,'1','1','1','1','EQ','select','is_not',10,'admin','2023-04-18 21:36:50',NULL,'2023-04-18 22:31:23'),(76,'6','creator','创建人编号（默认为0）','bigint','Long','creator','0','0',NULL,'1','1','1',NULL,'EQ','input','',11,'admin','2023-04-18 21:36:51',NULL,'2023-04-18 22:31:23'),(77,'6','create_time','创建时间（默认为创建时服务器时间）','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',12,'admin','2023-04-18 21:36:51',NULL,'2023-04-18 22:31:24'),(78,'6','operator','操作人编号（默认为0）','bigint','Long','operator','0','0',NULL,'1','1','1',NULL,'EQ','input','',13,'admin','2023-04-18 21:36:52',NULL,'2023-04-18 22:31:25'),(79,'6','operate_time','操作时间（每次更新时自动更新）','datetime','Date','operateTime','0','0',NULL,'1','1','1','1','BETWEEN','datetime','',14,'admin','2023-04-18 21:36:52',NULL,'2023-04-18 22:31:26'),(80,'7','id','','bigint','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2023-04-18 21:36:53',NULL,'2023-04-18 22:26:06'),(81,'7','product_id','产品id','bigint','Long','productId','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2023-04-18 21:36:54',NULL,'2023-04-18 22:26:07'),(82,'7','user_id','用户id','bigint','Long','userId','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2023-04-18 21:36:54',NULL,'2023-04-18 22:26:07'),(83,'7','price','价格','decimal(10,2)','BigDecimal','price','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2023-04-18 21:36:55',NULL,'2023-04-18 22:26:08'),(84,'7','state','状态','int','Long','state','0','0',NULL,'1','1','1','1','EQ','select','pay_state',5,'admin','2023-04-18 21:36:56',NULL,'2023-04-18 22:26:09'),(85,'7','pay_type','支付方式','varchar(10)','String','payType','0','0',NULL,'1','1','1','1','EQ','select','pay_type',6,'admin','2023-04-18 21:36:57',NULL,'2023-04-18 22:26:09'),(86,'7','pay_number','购买数量','int','Long','payNumber','0','0',NULL,'1','1','1',NULL,'EQ','input','',7,'admin','2023-04-18 21:36:57',NULL,'2023-04-18 22:26:10'),(87,'7','trade_no','支付订单号','varchar(255)','String','tradeNo','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2023-04-18 21:36:58',NULL,'2023-04-18 22:26:10'),(88,'7','msg','支付结果消息','varchar(255)','String','msg','0','0',NULL,'1','1','1',NULL,'EQ','input','',9,'admin','2023-04-18 21:36:58',NULL,'2023-04-18 22:26:11'),(89,'7','data_version','数据版本（默认为0，每次编辑+1）','int','Long','dataVersion','0','0',NULL,'1','1','1',NULL,'EQ','input','',10,'admin','2023-04-18 21:36:59',NULL,'2023-04-18 22:26:11'),(90,'7','deleted','是否删除','int','Long','deleted','0','0',NULL,'1','1','1','1','EQ','select','is_not',11,'admin','2023-04-18 21:36:59',NULL,'2023-04-18 22:26:12'),(91,'7','creator','创建人编号（默认为0）','bigint','Long','creator','0','0',NULL,'1','1','1','1','EQ','input','',12,'admin','2023-04-18 21:37:00',NULL,'2023-04-18 22:26:12'),(92,'7','create_time','创建时间（默认为创建时服务器时间）','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',13,'admin','2023-04-18 21:37:01',NULL,'2023-04-18 22:26:13'),(93,'7','operator','操作人编号（默认为0）','bigint','Long','operator','0','0',NULL,'1','1','1',NULL,'EQ','input','',14,'admin','2023-04-18 21:37:01',NULL,'2023-04-18 22:26:14'),(94,'7','operate_time','操作时间（每次更新时自动更新）','datetime','Date','operateTime','0','0',NULL,'1','1','1','1','BETWEEN','datetime','',15,'admin','2023-04-18 21:37:02',NULL,'2023-04-18 22:26:14'),(95,'8','id','','bigint','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2023-04-18 21:37:03',NULL,'2023-04-18 22:13:57'),(96,'8','user_id','用户id','bigint','Long','userId','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2023-04-18 21:37:03',NULL,'2023-04-18 22:13:58'),(97,'8','use_number','使用次数','int','Long','useNumber','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2023-04-18 21:37:04',NULL,'2023-04-18 22:13:59'),(98,'8','use_type','使用类型','tinyint','Long','useType','0','0',NULL,'1','1','1','1','EQ','select','use_type',4,'admin','2023-04-18 21:37:04',NULL,'2023-04-18 22:13:59'),(99,'8','use_value','聊天内容','text','String','useValue','0','0',NULL,'1','1','1',NULL,'EQ','textarea','',5,'admin','2023-04-18 21:37:05',NULL,'2023-04-18 22:14:00'),(100,'8','kit_id','加油包id','bigint','Long','kitId','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2023-04-18 21:37:05',NULL,'2023-04-18 22:14:01'),(101,'8','gpt_key','使用gptkey','varchar(255)','String','gptKey','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2023-04-18 21:37:05',NULL,'2023-04-18 22:14:02'),(102,'8','state','是否成功','tinyint','Long','state','0','0',NULL,'1','1','1','1','EQ','select','is_not',8,'admin','2023-04-18 21:37:06',NULL,'2023-04-18 22:14:02'),(103,'8','question','问题','mediumtext','String','question','0','0',NULL,'1','1','1',NULL,'EQ','textarea','',9,'admin','2023-04-18 21:37:07',NULL,'2023-04-18 22:14:03'),(104,'8','answer','答案','mediumtext','String','answer','0','0',NULL,'1','1','1',NULL,'EQ','textarea','',10,'admin','2023-04-18 21:37:07',NULL,'2023-04-18 22:14:03'),(105,'8','send_type','消息类型','tinyint','Long','sendType','0','0',NULL,'1','1','1','1','EQ','select','msg_type',11,'admin','2023-04-18 21:37:08',NULL,'2023-04-18 22:14:04'),(106,'8','data_version','数据版本（默认为0，每次编辑+1）','int','Long','dataVersion','0','0',NULL,'1','1','1','1','EQ','input','',12,'admin','2023-04-18 21:37:08',NULL,'2023-04-18 22:14:04'),(107,'8','deleted','是否删除','int','Long','deleted','0','0',NULL,'1','1','1','1','EQ','select','is_not',13,'admin','2023-04-18 21:37:09',NULL,'2023-04-18 22:14:05'),(108,'8','creator','创建人编号（默认为0）','bigint','Long','creator','0','0',NULL,'1','1','1','1','EQ','input','',14,'admin','2023-04-18 21:37:09',NULL,'2023-04-18 22:14:05'),(109,'8','create_time','创建时间（默认为创建时服务器时间）','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',15,'admin','2023-04-18 21:37:09',NULL,'2023-04-18 22:14:06'),(110,'8','operator','操作人编号（默认为0）','bigint','Long','operator','0','0',NULL,'1','1','1','1','EQ','input','',16,'admin','2023-04-18 21:37:10',NULL,'2023-04-18 22:14:07'),(111,'8','operate_time','操作时间（每次更新时自动更新）','datetime','Date','operateTime','0','0',NULL,'1','1','1','1','BETWEEN','datetime','',17,'admin','2023-04-18 21:37:11',NULL,'2023-04-18 22:14:07'),(112,'9','id','','bigint','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2023-04-18 21:37:12',NULL,'2023-04-18 21:55:57'),(113,'9','name','姓名','varchar(30)','String','name','0','0',NULL,'1','1','1','1','LIKE','input','',2,'admin','2023-04-18 21:37:12',NULL,'2023-04-18 21:55:58'),(114,'9','mobile','手机号','varchar(30)','String','mobile','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2023-04-18 21:37:13',NULL,'2023-04-18 21:55:58'),(115,'9','password','密码','varchar(30)','String','password','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',4,'admin','2023-04-18 21:37:14',NULL,'2023-04-18 21:55:59'),(116,'9','last_login_time','上次登录时间','datetime','Date','lastLoginTime','0','0',NULL,'1','1','1',NULL,'EQ','datetime','',5,'admin','2023-04-18 21:37:15',NULL,'2023-04-18 21:55:59'),(117,'9','type','类型','tinyint','Long','type','0','0',NULL,'1','1','1','1','EQ','select','user_type',6,'admin','2023-04-18 21:37:15',NULL,'2023-04-18 21:56:00'),(118,'9','expiration_time','月卡到期日期','datetime','Date','expirationTime','0','0',NULL,'1','1','1','1','BETWEEN','datetime','',7,'admin','2023-04-18 21:37:15',NULL,'2023-04-18 21:56:01'),(119,'9','remaining_times','剩余次数','int','Long','remainingTimes','0','0',NULL,'1','1','1',NULL,'EQ','input','',8,'admin','2023-04-18 21:37:16',NULL,'2023-04-18 21:56:01'),(120,'9','card_day_max_number','月卡当日使用最大次数','int','Long','cardDayMaxNumber','0','0',NULL,'1','1','1',NULL,'EQ','input','',9,'admin','2023-04-18 21:37:16',NULL,'2023-04-18 21:56:02'),(121,'9','from_user_name','微信用户账号','varchar(100)','String','fromUserName','0','0',NULL,'1','1','1','1','EQ','input','',10,'admin','2023-04-18 21:37:17',NULL,'2023-04-18 21:56:02'),(122,'9','is_event','是否关注公众号','tinyint','Long','isEvent','0','0',NULL,'1','1','1','1','EQ','select','is_not',11,'admin','2023-04-18 21:37:17',NULL,'2023-04-18 21:56:03'),(123,'9','data_version','数据版本（默认为0，每次编辑+1）','int','Long','dataVersion','0','0',NULL,'1','1','1',NULL,'EQ','input','',12,'admin','2023-04-18 21:37:19',NULL,'2023-04-18 21:56:03'),(124,'9','deleted','是否删除','int','Long','deleted','0','0',NULL,'1','1','1','1','EQ','select','is_not',13,'admin','2023-04-18 21:37:20',NULL,'2023-04-18 21:56:04'),(125,'9','creator','创建人编号（默认为0）','bigint','Long','creator','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',14,'admin','2023-04-18 21:37:21',NULL,'2023-04-18 21:56:04'),(126,'9','create_time','创建时间（默认为创建时服务器时间）','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',15,'admin','2023-04-18 21:37:22',NULL,'2023-04-18 21:56:05'),(127,'9','operator','操作人编号（默认为0）','bigint','Long','operator','0','0',NULL,'1','1','1','1','EQ','input','',16,'admin','2023-04-18 21:37:22',NULL,'2023-04-18 21:56:05'),(128,'9','operate_time','操作时间（每次更新时自动更新）','datetime','Date','operateTime','0','0',NULL,'1','1','1','1','EQ','datetime','',17,'admin','2023-04-18 21:37:23',NULL,'2023-04-18 21:56:06'),(129,'10','id','','bigint','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2023-04-18 21:37:24',NULL,'2023-04-18 21:59:29'),(130,'10','content','请求内容','text','String','content','0','0',NULL,'1','1','1',NULL,'EQ','summernote','',2,'admin','2023-04-18 21:37:25',NULL,'2023-04-18 21:59:30'),(131,'10','from_user_name','微信用户账号','varchar(255)','String','fromUserName','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2023-04-18 21:37:25',NULL,'2023-04-18 21:59:31'),(132,'10','data_version','数据版本（默认为0，每次编辑+1）','int','Long','dataVersion','0','0',NULL,'1','1','1',NULL,'EQ','input','',4,'admin','2023-04-18 21:37:25',NULL,'2023-04-18 21:59:31'),(133,'10','deleted','是否删除','int','Long','deleted','0','0',NULL,'1','1','1','1','EQ','select','is_not',5,'admin','2023-04-18 21:37:27',NULL,'2023-04-18 21:59:32'),(134,'10','creator','创建人编号（默认为0）','bigint','Long','creator','0','0',NULL,'1','1','1',NULL,'EQ','input','',6,'admin','2023-04-18 21:37:27',NULL,'2023-04-18 21:59:32'),(135,'10','create_time','创建时间（默认为创建时服务器时间）','datetime','Date','createTime','0','0',NULL,'1',NULL,'1',NULL,'EQ','datetime','',7,'admin','2023-04-18 21:37:28',NULL,'2023-04-18 21:59:33'),(136,'10','operator','操作人编号（默认为0）','bigint','Long','operator','0','0',NULL,'1','1','1',NULL,'EQ','input','',8,'admin','2023-04-18 21:37:28',NULL,'2023-04-18 21:59:33'),(137,'10','operate_time','操作时间（每次更新时自动更新）','datetime','Date','operateTime','0','0',NULL,'1','1','1','1','BETWEEN','datetime','',9,'admin','2023-04-18 21:37:29',NULL,'2023-04-18 21:59:34');
/*!40000 ALTER TABLE `gen_table_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gpt_key`
--

DROP TABLE IF EXISTS `gpt_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gpt_key` (
  `id` bigint NOT NULL,
  `key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'key',
  `use_number` int DEFAULT '0' COMMENT '使用次数',
  `sort` int DEFAULT '0' COMMENT '排序',
  `state` int DEFAULT '0' COMMENT '状态 0 启用 1禁用',
  `data_version` int DEFAULT '0' COMMENT '数据版本（默认为0，每次编辑+1）',
  `deleted` int DEFAULT '0' COMMENT '是否删除：0-否、1-是',
  `creator` bigint DEFAULT '0' COMMENT '创建人编号（默认为0）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（默认为创建时服务器时间）',
  `operator` bigint DEFAULT '0' COMMENT '操作人编号（默认为0）',
  `operate_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间（每次更新时自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='gptkey\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpt_key`
--

LOCK TABLES `gpt_key` WRITE;
/*!40000 ALTER TABLE `gpt_key` DISABLE KEYS */;
/*!40000 ALTER TABLE `gpt_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay_config`
--

DROP TABLE IF EXISTS `pay_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pay_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pid` int DEFAULT NULL COMMENT '商户id',
  `secret_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商户密钥',
  `notify_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '回调域名',
  `return_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '跳转通知地址',
  `submit_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付请求域名',
  `ali_app_id` varchar(50) DEFAULT NULL COMMENT '支付宝appid',
  `ali_private_key` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付宝应用私钥',
  `ali_public_key` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付宝应用公钥',
  `ali_gateway_url` varchar(100) DEFAULT NULL COMMENT '支付宝接口地址',
  `ali_notify_url` varchar(100) DEFAULT NULL COMMENT '支付宝回调地址',
  `ali_return_url` varchar(100) DEFAULT NULL COMMENT '支付宝页面跳转地址',
  `pay_type` tinyint DEFAULT '0' COMMENT '支付类型 0 易支付 1微信 2支付宝 3支付宝、微信',
  `data_version` int DEFAULT '0' COMMENT '数据版本（默认为0，每次编辑+1）',
  `deleted` int DEFAULT '0' COMMENT '是否删除：0-否、1-是',
  `creator` bigint DEFAULT '0' COMMENT '创建人编号（默认为0）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（默认为创建时服务器时间）',
  `operator` bigint DEFAULT '0' COMMENT '操作人编号（默认为0）',
  `operate_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间（每次更新时自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='支付配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay_config`
--

LOCK TABLES `pay_config` WRITE;
/*!40000 ALTER TABLE `pay_config` DISABLE KEYS */;
INSERT INTO `pay_config` VALUES (1,NULL,'','','','','2019110968993804','MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDNsoHcQaF5+ZR5YGn2E9hhaF+0S76J8Zv8tCKKL9RHodFlmpVD5to2S1/ie4CyJcuukY+dCL7w0WpN6FKpAmXbfMUWgg5+25U6hHOqblmkOYLDhEj7W3/VzzgR1ubhXdg012W5bQGJrYepjywMMoj2XZFD1jRDDAT1wlGic9W1g50U4t1sRjj5lB99ZpdB03sBLMKs6gq4GTfAQeSJveTUGPuWT2mK71AO28NFZLgUg4OtyiXR/QEBnH9oL6jzxmOEmI97Smujkwl6Xb4SLnImtEIQoJT2Mmz7FEVJQxp8JtvUOIt/ArKbt2DQ4oyG2F5CGbX/hzDEZtP6HpeSeltxAgMBAAECggEBAJiOkh2nV3m9j07OIQhvA4aLAKAZpUFtvkEX4J4Yhxpxe3odwiLQLnsiQAUbkAGQYpYgQyE2gVbeGjeZ3OSYcK7uxMXCw7l2ZiE2Zwq6OSXBuagEjpRN8mvC+lTQy//NuDLIpht2KzPPTdSF6SdKu6XtE3X6glxPp8YVzrHrs06g7y5DOUSZ8JfUfly0QwLyHUqBfB8KBZmdkNUr0nJeo9PdwHeU/PHYO+bOJ69F6BO7y9/HunHjBEQTQ9o4INQBK5fER1aNZc79wnYWy7jsioAtVSFAvLQBcyerHPqBbo57k3N4hecn6fgBog2km0n72Lt25DUB/8R7W8yf1gX3wAECgYEA9tidFFIlavUsD6bl7B7H2pQLTiqIg4yc1SMDKj8460eky5qb9uaSLkgSDdkZiCXHdxMJqpEZG0At0WKUUfMExnWDxlrUEU49EDsAFGZGjXoBi+psW9O9g5ECC9/SE9Lq5bj4k54P8x674B9ir2a5L9Fg6m0jeN7+ZHKKWocYxXECgYEA1VNBROJd9P43rf6qC9nMf0Ch4OCFC1EYcyik8CCx68SXDVobTp99tBLEXZM9JjY844PNFBwWoHr5m91Te+7u7ngCAkHRj+UiI+jjZjyCwy8rS1BGtcPib1K3c7/Y3IbctKUH0Qg9ZO47a7bOEkktFQQBeAI5eWWzxWPEN0iX9gECgYEAmY6dAOQtNME53K7Ff0KSru3DrqeU7sjNU2WJdYGI5gxWfvY6Kbi7nSDmYkj4shJWaEOmkr3asXIWTpgVjnkwMkkuZXlhTTavk+YwIYCSSTZpueFl8SXiUfMmI77jj8N4rtmHgUUlxOcMltrPaC/t6cpFkO+9zlZiSJhcav55sXECgYEAvESY0uFxCJofq8iYbJMQfrIc66la4cz1ZhTV1DMWszwyD4+Zj/8EYps5gFhE3Gd7v1N/96J+ea1ug5eGaVdKuV1icHmEKJmRUt3ja0Q2J2qGrBYyAZLaUWNQErCbBK1lNWx8hIfgHyZ9fKe+ILdCOUzquu5DqRcP3EIbVqsTqgECgYEAoZZm98R8GjB0yLi8dpOH6NF3oxfyYy5E9qzjLFLuGI6R+KSlZoktKiaM+dmLVJSjGbyJCvRH0KJ+knFkhrYBDvmta65thlAO+SaYR+r8pXkQ/yjEMye1SVQrg843lYn6Q39qKaQNberO58oE2BosNVTH+/vJi1lMwsHpEAywrsE=','MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAptygeHGKo+QxdJrC0jyOGfFIlyxaseXyicWCOj6PhYXc4A/ffHnxuu7vqBeJJ+wgdZcHijffJgWTq5vomrYoD2g3zi2Z+s8bysKHaFU+IaHWHJrATVrRaeBSCZltKVWaDYAFp35y9Lkf9Xd6VDVHonxzPuvIIbvJzBRlwg5w1UMFJ45Uc2UGtiPyCWZgrhFH1NR87sU4KSqV8sKqsJlWCY5YNKk+dCzqf+4Rn0+Usr4IpQq70sTfehGyBftH6zy8cbzZhxTvxS9mLLET1OnvP5fIRtjVuMHHW3sOWQagjKbwBYZf2bi2990ysIXTv8YNBdXuiq5OTeK9we+S3td5uwIDAQAB','','http://chat.renren.club/api/api/ali/callBack','http://chat.renren.club/api/api/return/url',2,0,0,0,'2023-04-19 12:33:33',0,'2023-04-19 12:33:32');
/*!40000 ALTER TABLE `pay_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品名',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `type` tinyint DEFAULT '0' COMMENT '类型 0 次数 1 月卡 2 加油包',
  `number_times` int DEFAULT NULL COMMENT '次数',
  `monthly_number` int DEFAULT NULL COMMENT '月卡每日可使用次数',
  `sort` int DEFAULT '0' COMMENT '排序',
  `stock` int DEFAULT '1' COMMENT '库存数',
  `data_version` int DEFAULT '0' COMMENT '数据版本（默认为0，每次编辑+1）',
  `deleted` int DEFAULT '0' COMMENT '是否删除：0-否、1-是',
  `creator` bigint DEFAULT '0' COMMENT '创建人编号（默认为0）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（默认为创建时服务器时间）',
  `operator` bigint DEFAULT '0' COMMENT '操作人编号（默认为0）',
  `operate_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间（每次更新时自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='产品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'500次',20.00,1,500,NULL,0,100,0,0,0,'2023-04-19 12:36:39',0,'2023-04-19 00:00:00');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_blob_triggers`
--

DROP TABLE IF EXISTS `qrtz_blob_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `blob_data` blob COMMENT '存放持久化Trigger对象',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Blob类型的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_blob_triggers`
--

LOCK TABLES `qrtz_blob_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_calendars`
--

DROP TABLE IF EXISTS `qrtz_calendars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `calendar_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日历名称',
  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='日历信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_calendars`
--

LOCK TABLES `qrtz_calendars` WRITE;
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_cron_triggers`
--

DROP TABLE IF EXISTS `qrtz_cron_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `cron_expression` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'cron表达式',
  `time_zone_id` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '时区',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Cron类型的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_cron_triggers`
--

LOCK TABLES `qrtz_cron_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_fired_triggers`
--

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `entry_id` varchar(95) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度器实例id',
  `trigger_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `instance_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度器实例名',
  `fired_time` bigint NOT NULL COMMENT '触发的时间',
  `sched_time` bigint NOT NULL COMMENT '定时器制定的时间',
  `priority` int NOT NULL COMMENT '优先级',
  `state` varchar(16) COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `job_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '任务组名',
  `is_nonconcurrent` varchar(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否并发',
  `requests_recovery` varchar(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否接受恢复执行',
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='已触发的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_fired_triggers`
--

LOCK TABLES `qrtz_fired_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_job_details`
--

DROP TABLE IF EXISTS `qrtz_job_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `job_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `description` varchar(250) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '相关介绍',
  `job_class_name` varchar(250) COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行任务类名称',
  `is_durable` varchar(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否持久化',
  `is_nonconcurrent` varchar(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否并发',
  `is_update_data` varchar(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否更新数据',
  `requests_recovery` varchar(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否接受恢复执行',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='任务详细信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_job_details`
--

LOCK TABLES `qrtz_job_details` WRITE;
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_locks`
--

DROP TABLE IF EXISTS `qrtz_locks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `lock_name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '悲观锁名称',
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='存储的悲观锁信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_locks`
--

LOCK TABLES `qrtz_locks` WRITE;
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_paused_trigger_grps`
--

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_group` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='暂停的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_paused_trigger_grps`
--

LOCK TABLES `qrtz_paused_trigger_grps` WRITE;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_scheduler_state`
--

DROP TABLE IF EXISTS `qrtz_scheduler_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `instance_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例名称',
  `last_checkin_time` bigint NOT NULL COMMENT '上次检查时间',
  `checkin_interval` bigint NOT NULL COMMENT '检查间隔时间',
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='调度器状态表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_scheduler_state`
--

LOCK TABLES `qrtz_scheduler_state` WRITE;
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simple_triggers`
--

DROP TABLE IF EXISTS `qrtz_simple_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `repeat_count` bigint NOT NULL COMMENT '重复的次数统计',
  `repeat_interval` bigint NOT NULL COMMENT '重复的间隔时间',
  `times_triggered` bigint NOT NULL COMMENT '已经触发的次数',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='简单触发器的信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simple_triggers`
--

LOCK TABLES `qrtz_simple_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simprop_triggers`
--

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `str_prop_1` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
  `str_prop_2` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
  `str_prop_3` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
  `int_prop_1` int DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
  `int_prop_2` int DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
  `long_prop_1` bigint DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
  `long_prop_2` bigint DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
  `dec_prop_1` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
  `dec_prop_2` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
  `bool_prop_1` varchar(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
  `bool_prop_2` varchar(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='同步机制的行锁表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simprop_triggers`
--

LOCK TABLES `qrtz_simprop_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_triggers`
--

DROP TABLE IF EXISTS `qrtz_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器的名字',
  `trigger_group` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器所属组的名字',
  `job_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
  `job_group` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
  `description` varchar(250) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '相关介绍',
  `next_fire_time` bigint DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
  `prev_fire_time` bigint DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
  `priority` int DEFAULT NULL COMMENT '优先级',
  `trigger_state` varchar(16) COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器状态',
  `trigger_type` varchar(8) COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器的类型',
  `start_time` bigint NOT NULL COMMENT '开始时间',
  `end_time` bigint DEFAULT NULL COMMENT '结束时间',
  `calendar_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日程表名称',
  `misfire_instr` smallint DEFAULT NULL COMMENT '补偿执行的策略',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='触发器详细信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_triggers`
--

LOCK TABLES `qrtz_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refueling_kit`
--

DROP TABLE IF EXISTS `refueling_kit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refueling_kit` (
  `id` bigint NOT NULL,
  `product_id` bigint DEFAULT NULL COMMENT '产品id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `number_times` int DEFAULT NULL COMMENT '可使用次数',
  `data_version` int DEFAULT '0' COMMENT '数据版本（默认为0，每次编辑+1）',
  `deleted` int DEFAULT '0' COMMENT '是否删除：0-否、1-是',
  `creator` bigint DEFAULT '0' COMMENT '创建人编号（默认为0）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（默认为创建时服务器时间）',
  `operator` bigint DEFAULT '0' COMMENT '操作人编号（默认为0）',
  `operate_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间（每次更新时自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='加油包';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refueling_kit`
--

LOCK TABLES `refueling_kit` WRITE;
/*!40000 ALTER TABLE `refueling_kit` DISABLE KEYS */;
/*!40000 ALTER TABLE `refueling_kit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_ryconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_ryconfig` (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数键名',
  `config_value` text COLLATE utf8mb4_general_ci COMMENT '参数键值',
  `config_type` char(1) COLLATE utf8mb4_general_ci DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_ryconfig` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_ryconfig` VALUES (1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2023-04-18 21:15:00','',NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2023-04-18 21:15:00','',NULL,'初始化密码 123456'),(3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2023-04-18 21:15:00','',NULL,'深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue'),(4,'账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y','admin','2023-04-18 21:15:00','',NULL,'是否开启注册用户功能（true开启，false关闭）'),(5,'用户管理-密码字符范围','sys.account.chrtype','0','Y','admin','2023-04-18 21:15:00','',NULL,'默认任意字符范围，0任意（密码可以输入任意字符），1数字（密码只能为0-9数字），2英文字母（密码只能为a-z和A-Z字母），3字母和数字（密码必须包含字母，数字）,4字母数字和特殊字符（目前支持的特殊字符包括：~!@#$%^&*()-=_+）'),(6,'用户管理-初始密码修改策略','sys.account.initPasswordModify','0','Y','admin','2023-04-18 21:15:00','',NULL,'0：初始密码修改策略关闭，没有任何提示，1：提醒用户，如果未修改初始密码，则在登录时就会提醒修改密码对话框'),(7,'用户管理-账号密码更新周期','sys.account.passwordValidateDays','0','Y','admin','2023-04-18 21:15:00','',NULL,'密码更新周期（填写数字，数据初始化值为0不限制，若修改必须为大于0小于365的正整数），如果超过这个周期登录系统时，则在登录时就会提醒修改密码对话框'),(8,'主框架页-菜单导航显示风格','sys.index.menuStyle','default','Y','admin','2023-04-18 21:15:00','',NULL,'菜单导航显示风格（default为左侧导航菜单，topnav为顶部导航菜单）'),(9,'主框架页-是否开启页脚','sys.index.footer','true','Y','admin','2023-04-18 21:15:00','',NULL,'是否开启底部页脚显示（true显示，false隐藏）'),(10,'主框架页-是否开启页签','sys.index.tagsView','true','Y','admin','2023-04-18 21:15:00','',NULL,'是否开启菜单多页签显示（true显示，false隐藏）'),(11,'用户登录-黑名单列表','sys.login.blackIPList','','Y','admin','2023-04-18 21:15:00','',NULL,'设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config2`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_config` (
  `id` bigint NOT NULL,
  `registration_method` tinyint DEFAULT '1' COMMENT '注册模式 1账号密码  2 短信注册 3 关闭注册',
  `key_switch` tinyint DEFAULT '0' COMMENT '是否禁用自动禁用key 0关闭 1开启',
  `default_times` int DEFAULT '10' COMMENT '默认注册次数',
  `ali_access_key_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '阿里云accessKeyId',
  `ali_secret` varchar(100) DEFAULT NULL COMMENT '阿里云secret',
  `ali_sign_name` varchar(50) DEFAULT NULL COMMENT '阿里云短信签名',
  `ali_template_code` varchar(50) DEFAULT NULL COMMENT '阿里云短信模版id',
  `data_version` int DEFAULT '0' COMMENT '数据版本（默认为0，每次编辑+1）',
  `deleted` int DEFAULT '0' COMMENT '是否删除：0-否、1-是',
  `creator` bigint DEFAULT '0' COMMENT '创建人编号（默认为0）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（默认为创建时服务器时间）',
  `operator` bigint DEFAULT '0' COMMENT '操作人编号（默认为0）',
  `operate_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间（每次更新时自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config2`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config2` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,1,0,10,NULL,NULL,NULL,NULL,0,0,0,'2023-04-18 16:38:01',0,'2023-04-18 16:38:01');
/*!40000 ALTER TABLE `sys_config2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (100,0,'0','若依科技',0,'若依','15888888888','ry@qq.com','0','0','admin','2023-04-18 21:11:50','',NULL),(101,100,'0,100','深圳总公司',1,'若依','15888888888','ry@qq.com','0','0','admin','2023-04-18 21:11:50','',NULL),(102,100,'0,100','长沙分公司',2,'若依','15888888888','ry@qq.com','0','0','admin','2023-04-18 21:11:50','',NULL),(103,101,'0,100,101','研发部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2023-04-18 21:11:50','',NULL),(104,101,'0,100,101','市场部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2023-04-18 21:11:50','',NULL),(105,101,'0,100,101','测试部门',3,'若依','15888888888','ry@qq.com','0','0','admin','2023-04-18 21:11:50','',NULL),(106,101,'0,100,101','财务部门',4,'若依','15888888888','ry@qq.com','0','0','admin','2023-04-18 21:11:50','',NULL),(107,101,'0,100,101','运维部门',5,'若依','15888888888','ry@qq.com','0','0','admin','2023-04-18 21:11:50','',NULL),(108,102,'0,100,102','市场部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2023-04-18 21:11:50','',NULL),(109,102,'0,100,102','财务部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2023-04-18 21:11:50','',NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS `sys_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) COLLATE utf8mb4_general_ci DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_data`
--

LOCK TABLES `sys_dict_data` WRITE;
/*!40000 ALTER TABLE `sys_dict_data` DISABLE KEYS */;
INSERT INTO `sys_dict_data` VALUES (1,1,'男','0','sys_user_sex','','','Y','0','admin','2023-04-18 21:11:52','',NULL,'性别男'),(2,2,'女','1','sys_user_sex','','','N','0','admin','2023-04-18 21:11:52','',NULL,'性别女'),(3,3,'未知','2','sys_user_sex','','','N','0','admin','2023-04-18 21:11:52','',NULL,'性别未知'),(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2023-04-18 21:11:52','',NULL,'显示菜单'),(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2023-04-18 21:11:52','',NULL,'隐藏菜单'),(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2023-04-18 21:11:52','',NULL,'正常状态'),(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2023-04-18 21:11:52','',NULL,'停用状态'),(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2023-04-18 21:11:52','',NULL,'正常状态'),(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2023-04-18 21:11:52','',NULL,'停用状态'),(10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2023-04-18 21:11:52','',NULL,'默认分组'),(11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2023-04-18 21:11:52','',NULL,'系统分组'),(12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2023-04-18 21:11:52','',NULL,'系统默认是'),(13,2,'否','N','sys_yes_no','','danger','N','0','admin','2023-04-18 21:11:52','',NULL,'系统默认否'),(14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2023-04-18 21:11:52','',NULL,'通知'),(15,2,'公告','2','sys_notice_type','','success','N','0','admin','2023-04-18 21:11:52','',NULL,'公告'),(16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2023-04-18 21:11:52','',NULL,'正常状态'),(17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2023-04-18 21:11:52','',NULL,'关闭状态'),(18,99,'其他','0','sys_oper_type','','info','N','0','admin','2023-04-18 21:11:52','',NULL,'其他操作'),(19,1,'新增','1','sys_oper_type','','info','N','0','admin','2023-04-18 21:11:52','',NULL,'新增操作'),(20,2,'修改','2','sys_oper_type','','info','N','0','admin','2023-04-18 21:11:52','',NULL,'修改操作'),(21,3,'删除','3','sys_oper_type','','danger','N','0','admin','2023-04-18 21:11:53','',NULL,'删除操作'),(22,4,'授权','4','sys_oper_type','','primary','N','0','admin','2023-04-18 21:11:53','',NULL,'授权操作'),(23,5,'导出','5','sys_oper_type','','warning','N','0','admin','2023-04-18 21:11:53','',NULL,'导出操作'),(24,6,'导入','6','sys_oper_type','','warning','N','0','admin','2023-04-18 21:11:53','',NULL,'导入操作'),(25,7,'强退','7','sys_oper_type','','danger','N','0','admin','2023-04-18 21:11:53','',NULL,'强退操作'),(26,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2023-04-18 21:11:53','',NULL,'生成操作'),(27,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2023-04-18 21:11:53','',NULL,'清空操作'),(28,1,'成功','0','sys_common_status','','primary','N','0','admin','2023-04-18 21:11:53','',NULL,'正常状态'),(29,2,'失败','1','sys_common_status','','danger','N','0','admin','2023-04-18 21:11:53','',NULL,'停用状态'),(100,1,'是','1','is_not',NULL,'info','Y','0','admin','2023-04-18 21:43:55','',NULL,NULL),(101,0,'否','0','is_not',NULL,'warning','Y','0','admin','2023-04-18 21:44:14','',NULL,NULL),(102,0,'次数用户','0','user_type',NULL,'default','N','0','admin','2023-04-18 21:46:37','',NULL,'类型 0 次数用户 1 月卡用户 -2 管理员'),(103,1,'月卡用户','1','user_type',NULL,'default','N','0','admin','2023-04-18 21:47:13','',NULL,NULL),(104,2,'管理员','2','user_type',NULL,'default','N','0','admin','2023-04-18 21:47:36','',NULL,NULL),(105,1,'次数','1','use_type',NULL,'default','Y','0','admin','2023-04-18 22:01:39','',NULL,'使用类型 1 次数 2 月卡 3加油包'),(106,2,'月卡','2','use_type',NULL,'default','Y','0','admin','2023-04-18 22:02:57','',NULL,NULL),(107,3,'加油包','3','use_type',NULL,NULL,'Y','0','admin','2023-04-18 22:03:30','',NULL,NULL),(108,0,'正常','0','msg_type',NULL,NULL,'Y','0','admin','2023-04-18 22:08:31','',NULL,NULL),(109,1,'流式','1','msg_type',NULL,NULL,'Y','0','admin','2023-04-18 22:08:49','',NULL,NULL),(110,0,'待支付','0','pay_state',NULL,NULL,'Y','0','admin','2023-04-18 22:17:39','',NULL,NULL),(111,1,'支付成功','1','pay_state',NULL,'default','Y','0','admin','2023-04-18 22:18:19','',NULL,NULL),(112,2,'支付失败','2','pay_state',NULL,'default','Y','0','admin','2023-04-18 22:18:43','',NULL,NULL),(113,0,'微信','wxpay','pay_type',NULL,NULL,'Y','0','admin','2023-04-18 22:21:11','',NULL,'支付方式 wxpay、alipay、qqpay'),(115,1,'支付宝','alipay','pay_type',NULL,NULL,'Y','0','admin','2023-04-18 22:22:43','',NULL,NULL),(116,2,'QQ','qqpay','pay_type',NULL,NULL,'Y','0','admin','2023-04-18 22:23:03','',NULL,NULL),(117,1,'账号密码','1','registration_method',NULL,NULL,'Y','0','admin','2023-04-18 22:35:35','',NULL,'1账号密码 2 短信注册 3...');
/*!40000 ALTER TABLE `sys_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
INSERT INTO `sys_dict_type` VALUES (1,'用户性别','sys_user_sex','0','admin','2023-04-18 21:11:52','',NULL,'用户性别列表'),(2,'菜单状态','sys_show_hide','0','admin','2023-04-18 21:11:52','',NULL,'菜单状态列表'),(3,'系统开关','sys_normal_disable','0','admin','2023-04-18 21:11:52','',NULL,'系统开关列表'),(4,'任务状态','sys_job_status','0','admin','2023-04-18 21:11:52','',NULL,'任务状态列表'),(5,'任务分组','sys_job_group','0','admin','2023-04-18 21:11:52','',NULL,'任务分组列表'),(6,'系统是否','sys_yes_no','0','admin','2023-04-18 21:11:52','',NULL,'系统是否列表'),(7,'通知类型','sys_notice_type','0','admin','2023-04-18 21:11:52','',NULL,'通知类型列表'),(8,'通知状态','sys_notice_status','0','admin','2023-04-18 21:11:52','',NULL,'通知状态列表'),(9,'操作类型','sys_oper_type','0','admin','2023-04-18 21:11:52','',NULL,'操作类型列表'),(10,'系统状态','sys_common_status','0','admin','2023-04-18 21:11:52','',NULL,'登录状态列表'),(100,'是否','is_not','0','admin','2023-04-18 21:42:51','',NULL,NULL),(101,'用户类型','user_type','0','admin','2023-04-18 21:45:18','',NULL,NULL),(102,'使用类型','use_type','0','admin','2023-04-18 22:00:59','',NULL,'使用类型 1 次数 2 月卡 3加油包'),(103,'消息类型','msg_type','0','admin','2023-04-18 22:07:02','',NULL,'消息类型 0-正常 1-流式'),(104,'支付状态','pay_state','0','admin','2023-04-18 22:16:57','',NULL,'状态 0待支付 1支付完成 2 支付失败'),(105,'支付方式','pay_type','0','admin','2023-04-18 22:20:01','',NULL,'支付方式 wxpay、alipay、qqpay'),(106,'注册模式','registration_method','0','admin','2023-04-18 22:29:33','',NULL,'注册模式 1账号密码  2 短信注册 3 关闭注册'),(107,'公告类型','announcement_type','0','admin','2023-04-18 22:50:36','',NULL,NULL);
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS `sys_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job` (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='定时任务调度表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job`
--

LOCK TABLES `sys_job` WRITE;
/*!40000 ALTER TABLE `sys_job` DISABLE KEYS */;
INSERT INTO `sys_job` VALUES (1,'系统默认（无参）','DEFAULT','ryTask.ryNoParams','0/10 * * * * ?','3','1','1','admin','2023-04-18 21:11:53','',NULL,''),(2,'系统默认（有参）','DEFAULT','ryTask.ryParams(\'ry\')','0/15 * * * * ?','3','1','1','admin','2023-04-18 21:11:53','',NULL,''),(3,'系统默认（多参）','DEFAULT','ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)','0/20 * * * * ?','3','1','1','admin','2023-04-18 21:11:53','',NULL,'');
/*!40000 ALTER TABLE `sys_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job_log`
--

DROP TABLE IF EXISTS `sys_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志信息',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='定时任务调度日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job_log`
--

LOCK TABLES `sys_job_log` WRITE;
/*!40000 ALTER TABLE `sys_job_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_job_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_logininfor`
--

DROP TABLE IF EXISTS `sys_logininfor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(128) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作系统',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`),
  KEY `idx_sys_logininfor_s` (`status`),
  KEY `idx_sys_logininfor_lt` (`login_time`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统访问记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_logininfor`
--

LOCK TABLES `sys_logininfor` WRITE;
/*!40000 ALTER TABLE `sys_logininfor` DISABLE KEYS */;
INSERT INTO `sys_logininfor` VALUES (100,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','密码输入错误1次','2023-04-18 21:33:02'),(101,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2023-04-18 21:33:25'),(102,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2023-04-18 23:08:43'),(103,NULL,'127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2023-04-18 23:08:48'),(104,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2023-04-18 23:09:02'),(105,'admin','39.144.38.170','安徽省 合肥市','Chrome 10','Windows 10','0','登录成功','2023-04-19 11:17:17'),(106,'admin','39.144.38.170','安徽省 合肥市','Chrome 10','Windows 10','0','登录成功','2023-04-19 12:16:06'),(107,'admin','39.144.38.170','安徽省 合肥市','Chrome 10','Windows 10','0','登录成功','2023-04-19 13:30:35');
/*!40000 ALTER TABLE `sys_logininfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) COLLATE utf8mb4_general_ci DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `is_refresh` char(1) COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '是否刷新（0刷新 1不刷新）',
  `perms` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2181 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,1,'#','','M','0','1','','fa fa-gear','admin','2023-04-18 21:11:51','',NULL,'系统管理目录'),(2,'系统监控',0,2,'#','','M','0','1','','fa fa-video-camera','admin','2023-04-18 21:11:51','',NULL,'系统监控目录'),(3,'系统工具',0,3,'#','','M','0','1','','fa fa-bars','admin','2023-04-18 21:11:51','',NULL,'系统工具目录'),
(100,'用户管理',1,1,'/system/user','','C','0','1','system:user:view','fa fa-user-o','admin','2023-04-18 21:11:51','',NULL,'用户管理菜单'),(101,'角色管理',1,2,'/system/role','','C','0','1','system:role:view','fa fa-user-secret','admin','2023-04-18 21:11:51','',NULL,'角色管理菜单'),(102,'菜单管理',1,3,'/system/menu','','C','0','1','system:menu:view','fa fa-th-list','admin','2023-04-18 21:11:51','',NULL,'菜单管理菜单'),(103,'部门管理',1,4,'/system/dept','','C','0','1','system:dept:view','fa fa-outdent','admin','2023-04-18 21:11:51','',NULL,'部门管理菜单'),(104,'岗位管理',1,5,'/system/post','','C','0','1','system:post:view','fa fa-address-card-o','admin','2023-04-18 21:11:51','',NULL,'岗位管理菜单'),(105,'字典管理',1,6,'/system/dict','','C','0','1','system:dict:view','fa fa-bookmark-o','admin','2023-04-18 21:11:51','',NULL,'字典管理菜单'),(106,'参数设置',1,7,'/system/config','','C','0','1','system:config:view','fa fa-sun-o','admin','2023-04-18 21:11:51','',NULL,'参数设置菜单'),(107,'通知公告',1,8,'/system/notice','','C','0','1','system:notice:view','fa fa-bullhorn','admin','2023-04-18 21:11:51','',NULL,'通知公告菜单'),(108,'日志管理',1,9,'#','','M','0','1','','fa fa-pencil-square-o','admin','2023-04-18 21:11:51','',NULL,'日志管理菜单'),(109,'在线用户',2,1,'/monitor/online','','C','0','1','monitor:online:view','fa fa-user-circle','admin','2023-04-18 21:11:51','',NULL,'在线用户菜单'),(110,'定时任务',2,2,'/monitor/job','','C','0','1','monitor:job:view','fa fa-tasks','admin','2023-04-18 21:11:51','',NULL,'定时任务菜单'),(111,'数据监控',2,3,'/monitor/data','','C','0','1','monitor:data:view','fa fa-bug','admin','2023-04-18 21:11:51','',NULL,'数据监控菜单'),(112,'服务监控',2,4,'/monitor/server','','C','0','1','monitor:server:view','fa fa-server','admin','2023-04-18 21:11:51','',NULL,'服务监控菜单'),(113,'缓存监控',2,5,'/monitor/cache','','C','0','1','monitor:cache:view','fa fa-cube','admin','2023-04-18 21:11:51','',NULL,'缓存监控菜单'),(114,'表单构建',3,1,'/tool/build','','C','0','1','tool:build:view','fa fa-wpforms','admin','2023-04-18 21:11:51','',NULL,'表单构建菜单'),(115,'代码生成',3,2,'/tool/gen','','C','0','1','tool:gen:view','fa fa-code','admin','2023-04-18 21:11:51','',NULL,'代码生成菜单'),(116,'系统接口',3,3,'/tool/swagger','','C','0','1','tool:swagger:view','fa fa-gg','admin','2023-04-18 21:11:51','',NULL,'系统接口菜单'),(500,'操作日志',108,1,'/monitor/operlog','','C','0','1','monitor:operlog:view','fa fa-address-book','admin','2023-04-18 21:11:51','',NULL,'操作日志菜单'),(501,'登录日志',108,2,'/monitor/logininfor','','C','0','1','monitor:logininfor:view','fa fa-file-image-o','admin','2023-04-18 21:11:51','',NULL,'登录日志菜单'),(1000,'用户查询',100,1,'#','','F','0','1','system:user:list','#','admin','2023-04-18 21:11:51','',NULL,''),(1001,'用户新增',100,2,'#','','F','0','1','system:user:add','#','admin','2023-04-18 21:11:51','',NULL,''),(1002,'用户修改',100,3,'#','','F','0','1','system:user:edit','#','admin','2023-04-18 21:11:51','',NULL,''),(1003,'用户删除',100,4,'#','','F','0','1','system:user:remove','#','admin','2023-04-18 21:11:51','',NULL,''),(1004,'用户导出',100,5,'#','','F','0','1','system:user:export','#','admin','2023-04-18 21:11:51','',NULL,''),(1005,'用户导入',100,6,'#','','F','0','1','system:user:import','#','admin','2023-04-18 21:11:51','',NULL,''),(1006,'重置密码',100,7,'#','','F','0','1','system:user:resetPwd','#','admin','2023-04-18 21:11:51','',NULL,''),(1007,'角色查询',101,1,'#','','F','0','1','system:role:list','#','admin','2023-04-18 21:11:51','',NULL,''),(1008,'角色新增',101,2,'#','','F','0','1','system:role:add','#','admin','2023-04-18 21:11:51','',NULL,''),(1009,'角色修改',101,3,'#','','F','0','1','system:role:edit','#','admin','2023-04-18 21:11:51','',NULL,''),(1010,'角色删除',101,4,'#','','F','0','1','system:role:remove','#','admin','2023-04-18 21:11:51','',NULL,''),(1011,'角色导出',101,5,'#','','F','0','1','system:role:export','#','admin','2023-04-18 21:11:51','',NULL,''),(1012,'菜单查询',102,1,'#','','F','0','1','system:menu:list','#','admin','2023-04-18 21:11:51','',NULL,''),(1013,'菜单新增',102,2,'#','','F','0','1','system:menu:add','#','admin','2023-04-18 21:11:51','',NULL,''),(1014,'菜单修改',102,3,'#','','F','0','1','system:menu:edit','#','admin','2023-04-18 21:11:51','',NULL,''),(1015,'菜单删除',102,4,'#','','F','0','1','system:menu:remove','#','admin','2023-04-18 21:11:51','',NULL,''),(1016,'部门查询',103,1,'#','','F','0','1','system:dept:list','#','admin','2023-04-18 21:11:51','',NULL,''),(1017,'部门新增',103,2,'#','','F','0','1','system:dept:add','#','admin','2023-04-18 21:11:51','',NULL,''),(1018,'部门修改',103,3,'#','','F','0','1','system:dept:edit','#','admin','2023-04-18 21:11:51','',NULL,''),(1019,'部门删除',103,4,'#','','F','0','1','system:dept:remove','#','admin','2023-04-18 21:11:51','',NULL,''),(1020,'岗位查询',104,1,'#','','F','0','1','system:post:list','#','admin','2023-04-18 21:11:51','',NULL,''),(1021,'岗位新增',104,2,'#','','F','0','1','system:post:add','#','admin','2023-04-18 21:11:51','',NULL,''),(1022,'岗位修改',104,3,'#','','F','0','1','system:post:edit','#','admin','2023-04-18 21:11:51','',NULL,''),(1023,'岗位删除',104,4,'#','','F','0','1','system:post:remove','#','admin','2023-04-18 21:11:51','',NULL,''),(1024,'岗位导出',104,5,'#','','F','0','1','system:post:export','#','admin','2023-04-18 21:11:51','',NULL,''),(1025,'字典查询',105,1,'#','','F','0','1','system:dict:list','#','admin','2023-04-18 21:11:51','',NULL,''),(1026,'字典新增',105,2,'#','','F','0','1','system:dict:add','#','admin','2023-04-18 21:11:51','',NULL,''),(1027,'字典修改',105,3,'#','','F','0','1','system:dict:edit','#','admin','2023-04-18 21:11:51','',NULL,''),(1028,'字典删除',105,4,'#','','F','0','1','system:dict:remove','#','admin','2023-04-18 21:11:51','',NULL,''),(1029,'字典导出',105,5,'#','','F','0','1','system:dict:export','#','admin','2023-04-18 21:11:51','',NULL,''),(1030,'参数查询',106,1,'#','','F','0','1','system:config:list','#','admin','2023-04-18 21:11:51','',NULL,''),(1031,'参数新增',106,2,'#','','F','0','1','system:config:add','#','admin','2023-04-18 21:11:51','',NULL,''),(1032,'参数修改',106,3,'#','','F','0','1','system:config:edit','#','admin','2023-04-18 21:11:51','',NULL,''),(1033,'参数删除',106,4,'#','','F','0','1','system:config:remove','#','admin','2023-04-18 21:11:51','',NULL,''),(1034,'参数导出',106,5,'#','','F','0','1','system:config:export','#','admin','2023-04-18 21:11:51','',NULL,''),(1035,'公告查询',107,1,'#','','F','0','1','system:notice:list','#','admin','2023-04-18 21:11:51','',NULL,''),(1036,'公告新增',107,2,'#','','F','0','1','system:notice:add','#','admin','2023-04-18 21:11:51','',NULL,''),(1037,'公告修改',107,3,'#','','F','0','1','system:notice:edit','#','admin','2023-04-18 21:11:51','',NULL,''),(1038,'公告删除',107,4,'#','','F','0','1','system:notice:remove','#','admin','2023-04-18 21:11:51','',NULL,''),(1039,'操作查询',500,1,'#','','F','0','1','monitor:operlog:list','#','admin','2023-04-18 21:11:51','',NULL,''),(1040,'操作删除',500,2,'#','','F','0','1','monitor:operlog:remove','#','admin','2023-04-18 21:11:51','',NULL,''),(1041,'详细信息',500,3,'#','','F','0','1','monitor:operlog:detail','#','admin','2023-04-18 21:11:51','',NULL,''),(1042,'日志导出',500,4,'#','','F','0','1','monitor:operlog:export','#','admin','2023-04-18 21:11:51','',NULL,''),(1043,'登录查询',501,1,'#','','F','0','1','monitor:logininfor:list','#','admin','2023-04-18 21:11:51','',NULL,''),(1044,'登录删除',501,2,'#','','F','0','1','monitor:logininfor:remove','#','admin','2023-04-18 21:11:51','',NULL,''),(1045,'日志导出',501,3,'#','','F','0','1','monitor:logininfor:export','#','admin','2023-04-18 21:11:51','',NULL,''),(1046,'账户解锁',501,4,'#','','F','0','1','monitor:logininfor:unlock','#','admin','2023-04-18 21:11:51','',NULL,''),(1047,'在线查询',109,1,'#','','F','0','1','monitor:online:list','#','admin','2023-04-18 21:11:51','',NULL,''),(1048,'批量强退',109,2,'#','','F','0','1','monitor:online:batchForceLogout','#','admin','2023-04-18 21:11:51','',NULL,''),(1049,'单条强退',109,3,'#','','F','0','1','monitor:online:forceLogout','#','admin','2023-04-18 21:11:51','',NULL,''),(1050,'任务查询',110,1,'#','','F','0','1','monitor:job:list','#','admin','2023-04-18 21:11:51','',NULL,''),(1051,'任务新增',110,2,'#','','F','0','1','monitor:job:add','#','admin','2023-04-18 21:11:51','',NULL,''),(1052,'任务修改',110,3,'#','','F','0','1','monitor:job:edit','#','admin','2023-04-18 21:11:51','',NULL,''),(1053,'任务删除',110,4,'#','','F','0','1','monitor:job:remove','#','admin','2023-04-18 21:11:51','',NULL,''),(1054,'状态修改',110,5,'#','','F','0','1','monitor:job:changeStatus','#','admin','2023-04-18 21:11:51','',NULL,''),(1055,'任务详细',110,6,'#','','F','0','1','monitor:job:detail','#','admin','2023-04-18 21:11:51','',NULL,''),(1056,'任务导出',110,7,'#','','F','0','1','monitor:job:export','#','admin','2023-04-18 21:11:51','',NULL,''),(1057,'生成查询',115,1,'#','','F','0','1','tool:gen:list','#','admin','2023-04-18 21:11:51','',NULL,''),(1058,'生成修改',115,2,'#','','F','0','1','tool:gen:edit','#','admin','2023-04-18 21:11:51','',NULL,''),(1059,'生成删除',115,3,'#','','F','0','1','tool:gen:remove','#','admin','2023-04-18 21:11:51','',NULL,''),(1060,'预览代码',115,4,'#','','F','0','1','tool:gen:preview','#','admin','2023-04-18 21:11:51','',NULL,''),(1061,'生成代码',115,5,'#','','F','0','1','tool:gen:code','#','admin','2023-04-18 21:11:51','',NULL,''),(2000,'chatGPT',0,5,'#','menuItem','M','0','1',NULL,'fa fa-heart-o','admin','2023-04-18 21:55:10','',NULL,''),(2121,'产品',2000,1,'/ai/product','','C','0','1','ai:product:view','#','admin','2023-04-18 23:20:36','',NULL,'产品菜单'),(2122,'产品查询',2121,1,'#','','F','0','1','ai:product:list','#','admin','2023-04-18 23:20:36','',NULL,''),(2123,'产品新增',2121,2,'#','','F','0','1','ai:product:add','#','admin','2023-04-18 23:20:36','',NULL,''),(2124,'产品修改',2121,3,'#','','F','0','1','ai:product:edit','#','admin','2023-04-18 23:20:36','',NULL,''),(2125,'产品删除',2121,4,'#','','F','0','1','ai:product:remove','#','admin','2023-04-18 23:20:36','',NULL,''),(2126,'产品导出',2121,5,'#','','F','0','1','ai:product:export','#','admin','2023-04-18 23:20:36','',NULL,''),(2127,'支付配置',2000,1,'/ai/payconfig','','C','0','1','ai:payconfig:view','#','admin','2023-04-18 23:21:06','',NULL,'支付配置菜单'),(2128,'支付配置查询',2127,1,'#','','F','0','1','ai:payconfig:list','#','admin','2023-04-18 23:21:06','',NULL,''),(2129,'支付配置新增',2127,2,'#','','F','0','1','ai:payconfig:add','#','admin','2023-04-18 23:21:06','',NULL,''),(2130,'支付配置修改',2127,3,'#','','F','0','1','ai:payconfig:edit','#','admin','2023-04-18 23:21:06','',NULL,''),(2131,'支付配置删除',2127,4,'#','','F','0','1','ai:payconfig:remove','#','admin','2023-04-18 23:21:06','',NULL,''),(2132,'支付配置导出',2127,5,'#','','F','0','1','ai:payconfig:export','#','admin','2023-04-18 23:21:06','',NULL,''),(2133,'使用记录',2000,1,'/ai/userlog','','C','0','1','ai:userlog:view','#','admin','2023-04-18 23:21:32','',NULL,'使用记录菜单'),(2134,'使用记录查询',2133,1,'#','','F','0','1','ai:userlog:list','#','admin','2023-04-18 23:21:32','',NULL,''),(2135,'使用记录新增',2133,2,'#','','F','0','1','ai:userlog:add','#','admin','2023-04-18 23:21:32','',NULL,''),(2136,'使用记录修改',2133,3,'#','','F','0','1','ai:userlog:edit','#','admin','2023-04-18 23:21:32','',NULL,''),(2137,'使用记录删除',2133,4,'#','','F','0','1','ai:userlog:remove','#','admin','2023-04-18 23:21:32','',NULL,''),(2138,'使用记录导出',2133,5,'#','','F','0','1','ai:userlog:export','#','admin','2023-04-18 23:21:32','',NULL,''),(2139,'用户',2000,1,'/ai/user','','C','0','1','ai:user:view','#','admin','2023-04-18 23:21:46','',NULL,'用户菜单'),(2140,'用户查询',2139,1,'#','','F','0','1','ai:user:list','#','admin','2023-04-18 23:21:46','',NULL,''),(2141,'用户新增',2139,2,'#','','F','0','1','ai:user:add','#','admin','2023-04-18 23:21:46','',NULL,''),(2142,'用户修改',2139,3,'#','','F','0','1','ai:user:edit','#','admin','2023-04-18 23:21:46','',NULL,''),(2143,'用户删除',2139,4,'#','','F','0','1','ai:user:remove','#','admin','2023-04-18 23:21:46','',NULL,''),(2144,'用户导出',2139,5,'#','','F','0','1','ai:user:export','#','admin','2023-04-18 23:21:46','',NULL,''),(2145,'公告',2000,1,'/ai/announcement','','C','0','1','ai:announcement:view','#','admin','2023-04-18 23:21:59','',NULL,'公告菜单'),(2146,'公告查询',2145,1,'#','','F','0','1','ai:announcement:list','#','admin','2023-04-18 23:21:59','',NULL,''),(2147,'公告新增',2145,2,'#','','F','0','1','ai:announcement:add','#','admin','2023-04-18 23:21:59','',NULL,''),(2148,'公告修改',2145,3,'#','','F','0','1','ai:announcement:edit','#','admin','2023-04-18 23:21:59','',NULL,''),(2149,'公告删除',2145,4,'#','','F','0','1','ai:announcement:remove','#','admin','2023-04-18 23:21:59','',NULL,''),(2150,'公告导出',2145,5,'#','','F','0','1','ai:announcement:export','#','admin','2023-04-18 23:21:59','',NULL,''),(2151,'系统配置',2000,1,'/ai/config','','C','0','1','ai:config:view','#','admin','2023-04-18 23:22:12','',NULL,'系统配置菜单'),(2152,'系统配置查询',2151,1,'#','','F','0','1','ai:config:list','#','admin','2023-04-18 23:22:12','',NULL,''),(2153,'系统配置新增',2151,2,'#','','F','0','1','ai:config:add','#','admin','2023-04-18 23:22:12','',NULL,''),(2154,'系统配置修改',2151,3,'#','','F','0','1','ai:config:edit','#','admin','2023-04-18 23:22:12','',NULL,''),(2155,'系统配置删除',2151,4,'#','','F','0','1','ai:config:remove','#','admin','2023-04-18 23:22:12','',NULL,''),(2156,'系统配置导出',2151,5,'#','','F','0','1','ai:config:export','#','admin','2023-04-18 23:22:12','',NULL,''),(2157,'gptkey',2000,1,'/ai/key','','C','0','1','ai:key:view','#','admin','2023-04-18 23:22:59','',NULL,'gptkey菜单'),(2158,'gptkey查询',2157,1,'#','','F','0','1','ai:key:list','#','admin','2023-04-18 23:22:59','',NULL,''),(2159,'gptkey新增',2157,2,'#','','F','0','1','ai:key:add','#','admin','2023-04-18 23:22:59','',NULL,''),(2160,'gptkey修改',2157,3,'#','','F','0','1','ai:key:edit','#','admin','2023-04-18 23:22:59','',NULL,''),(2161,'gptkey删除',2157,4,'#','','F','0','1','ai:key:remove','#','admin','2023-04-18 23:22:59','',NULL,''),(2162,'gptkey导出',2157,5,'#','','F','0','1','ai:key:export','#','admin','2023-04-18 23:22:59','',NULL,''),(2163,'加油包',2000,1,'/ai/kit','','C','0','1','ai:kit:view','#','admin','2023-04-18 23:23:12','',NULL,'加油包菜单'),(2164,'加油包查询',2163,1,'#','','F','0','1','ai:kit:list','#','admin','2023-04-18 23:23:12','',NULL,''),(2165,'加油包新增',2163,2,'#','','F','0','1','ai:kit:add','#','admin','2023-04-18 23:23:12','',NULL,''),(2166,'加油包修改',2163,3,'#','','F','0','1','ai:kit:edit','#','admin','2023-04-18 23:23:12','',NULL,''),(2167,'加油包删除',2163,4,'#','','F','0','1','ai:kit:remove','#','admin','2023-04-18 23:23:12','',NULL,''),(2168,'加油包导出',2163,5,'#','','F','0','1','ai:kit:export','#','admin','2023-04-18 23:23:12','',NULL,''),(2169,'微信日志',2000,1,'/ai/log','','C','0','1','ai:log:view','#','admin','2023-04-18 23:23:22','',NULL,'微信日志菜单'),(2170,'微信日志查询',2169,1,'#','','F','0','1','ai:log:list','#','admin','2023-04-18 23:23:22','',NULL,''),(2171,'微信日志新增',2169,2,'#','','F','0','1','ai:log:add','#','admin','2023-04-18 23:23:22','',NULL,''),(2172,'微信日志修改',2169,3,'#','','F','0','1','ai:log:edit','#','admin','2023-04-18 23:23:22','',NULL,''),(2173,'微信日志删除',2169,4,'#','','F','0','1','ai:log:remove','#','admin','2023-04-18 23:23:22','',NULL,''),(2174,'微信日志导出',2169,5,'#','','F','0','1','ai:log:export','#','admin','2023-04-18 23:23:22','',NULL,''),(2175,'订单',2000,1,'/ai/order','','C','0','1','ai:order:view','#','admin','2023-04-18 23:23:32','',NULL,'订单菜单'),(2176,'订单查询',2175,1,'#','','F','0','1','ai:order:list','#','admin','2023-04-18 23:23:32','',NULL,''),(2177,'订单新增',2175,2,'#','','F','0','1','ai:order:add','#','admin','2023-04-18 23:23:32','',NULL,''),(2178,'订单修改',2175,3,'#','','F','0','1','ai:order:edit','#','admin','2023-04-18 23:23:32','',NULL,''),(2179,'订单删除',2175,4,'#','','F','0','1','ai:order:remove','#','admin','2023-04-18 23:23:32','',NULL,''),(2180,'订单导出',2175,5,'#','','F','0','1','ai:order:export','#','admin','2023-04-18 23:23:32','',NULL,'');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公告内容',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='通知公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
INSERT INTO `sys_notice` VALUES (1,'温馨提醒：2018-07-01 若依新版本发布啦','2','新版本内容','0','admin','2023-04-18 21:11:53','',NULL,'管理员'),(2,'维护通知：2018-07-01 若依系统凌晨维护','1','维护内容','0','admin','2023-04-18 21:11:53','',NULL,'管理员');
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oper_log`
--

DROP TABLE IF EXISTS `sys_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '模块标题',
  `business_type` int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求方式',
  `operator_type` int DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '返回参数',
  `status` int DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint DEFAULT '0' COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`),
  KEY `idx_sys_oper_log_bt` (`business_type`),
  KEY `idx_sys_oper_log_s` (`status`),
  KEY `idx_sys_oper_log_ot` (`oper_time`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oper_log`
--

LOCK TABLES `sys_oper_log` WRITE;
/*!40000 ALTER TABLE `sys_oper_log` DISABLE KEYS */;
INSERT INTO `sys_oper_log` VALUES (100,'代码生成',6,'com.tenant.generator.controller.GenController.importTableSave()','POST',1,'admin','研发部门','/tool/gen/importTable','127.0.0.1','内网IP','{\"tables\":[\"use_log,user,wx_log,refueling_kit,product,t_order,pay_config,gpt_key,announcement,sys_config2\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 21:37:31',89743),(101,'字典类型',1,'com.tenant.web.controller.system.SysDictTypeController.addSave()','POST',1,'admin','研发部门','/system/dict/add','127.0.0.1','内网IP','{\"dictName\":[\"是否\"],\"dictType\":[\"is_not\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 21:42:53',2345),(102,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"是\"],\"dictValue\":[\"1\"],\"dictType\":[\"is_not\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"info\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 21:43:57',1543),(103,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"否\"],\"dictValue\":[\"0\"],\"dictType\":[\"is_not\"],\"cssClass\":[\"\"],\"dictSort\":[\"0\"],\"listClass\":[\"warning\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 21:44:16',2031),(104,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"次数用户\"],\"dictValue\":[\"0\"],\"dictType\":[\"user_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"0\"],\"listClass\":[\"default\"],\"isDefault\":[\"N\"],\"status\":[\"0\"],\"remark\":[\"类型 0 次数用户 1 月卡用户 -2 管理员\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 21:46:38',1893),(105,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"月卡用户\"],\"dictValue\":[\"1\"],\"dictType\":[\"user_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"default\"],\"isDefault\":[\"N\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 21:47:15',3308),(106,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"管理员\"],\"dictValue\":[\"2\"],\"dictType\":[\"user_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"2\"],\"listClass\":[\"default\"],\"isDefault\":[\"N\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 21:47:37',1113),(107,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"9\"],\"tableName\":[\"user\"],\"tableComment\":[\"用户表\"],\"className\":[\"User\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"112\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"113\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"姓名\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"name\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"LIKE\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"114\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"手机号\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"mobile\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"115\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"密码\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"password\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"116\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"上次登录时间\"],\"columns[4].javaType\":[\"Date\"],\"columns[4].javaField\":[\"lastLoginTime\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"datetime\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"117\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"类型\"],\"columns[5].javaType\":[\"Long\"],\"columns[5].javaField\":[\"type\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"columns[5].isList\":[\"1\"],\"columns[5].isQuery\":[\"1\"],\"columns[5].que','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 21:51:52',9934),(108,'菜单管理',1,'com.tenant.web.controller.system.SysMenuController.addSave()','POST',1,'admin','研发部门','/system/menu/add','127.0.0.1','内网IP','{\"parentId\":[\"0\"],\"menuType\":[\"M\"],\"menuName\":[\"chatGPT\"],\"url\":[\"\"],\"target\":[\"menuItem\"],\"perms\":[\"\"],\"orderNum\":[\"5\"],\"icon\":[\"fa fa-heart-o\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 21:55:11',1583),(109,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"9\"],\"tableName\":[\"user\"],\"tableComment\":[\"用户表\"],\"className\":[\"User\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"112\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"113\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"姓名\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"name\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"LIKE\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"114\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"手机号\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"mobile\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"115\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"密码\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"password\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"116\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"上次登录时间\"],\"columns[4].javaType\":[\"Date\"],\"columns[4].javaField\":[\"lastLoginTime\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"datetime\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"117\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"类型\"],\"columns[5].javaType\":[\"Long\"],\"columns[5].javaField\":[\"type\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"columns[5].isList\":[\"1\"],\"columns[5].isQuery\":[\"1\"],\"columns[5].que','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 21:56:07',10340),(110,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"10\"],\"tableName\":[\"wx_log\"],\"tableComment\":[\"微信日志\"],\"className\":[\"WxLog\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"129\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"130\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"请求内容\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"content\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"summernote\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"131\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"微信用户账号\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"fromUserName\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"132\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"数据版本（默认为0，每次编辑+1）\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"dataVersion\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"133\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"是否删除\"],\"columns[4].javaType\":[\"Long\"],\"columns[4].javaField\":[\"deleted\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"select\"],\"columns[4].dictType\":[\"is_not\"],\"columns[5].columnId\":[\"134\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"创建人编号（默认为0）\"],\"columns[5].javaType\":[\"Long\"],\"columns[5].javaField\":[\"creator\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 21:59:36',7848),(111,'字典类型',1,'com.tenant.web.controller.system.SysDictTypeController.addSave()','POST',1,'admin','研发部门','/system/dict/add','127.0.0.1','内网IP','{\"dictName\":[\"使用类型\"],\"dictType\":[\"use_type\"],\"status\":[\"0\"],\"remark\":[\"使用类型 1 次数 2 月卡 3加油包\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:01:00',1613),(112,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"次数\"],\"dictValue\":[\"1\"],\"dictType\":[\"use_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"default\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"使用类型 1 次数 2 月卡 3加油包\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:01:40',1266),(113,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"月卡\"],\"dictValue\":[\"2\"],\"dictType\":[\"use_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"2\"],\"listClass\":[\"default\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:02:58',1992),(114,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"加油包\"],\"dictValue\":[\"3\"],\"dictType\":[\"use_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"3\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:03:31',1271),(115,'字典类型',1,'com.tenant.web.controller.system.SysDictTypeController.addSave()','POST',1,'admin','研发部门','/system/dict/add','127.0.0.1','内网IP','{\"dictName\":[\"消息类型\"],\"dictType\":[\"msg_type\"],\"status\":[\"0\"],\"remark\":[\"消息类型 0-正常 1-流式\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:07:04',1975),(116,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"正常\"],\"dictValue\":[\"0\"],\"dictType\":[\"msg_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"0\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:08:32',1623),(117,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"流式\"],\"dictValue\":[\"1\"],\"dictType\":[\"msg_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:08:51',1820),(118,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"8\"],\"tableName\":[\"use_log\"],\"tableComment\":[\"使用记录表\"],\"className\":[\"UseLog\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"95\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"96\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"用户id\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"userId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"97\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"使用次数\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"useNumber\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"98\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"使用类型\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"useType\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"select\"],\"columns[3].dictType\":[\"use_type\"],\"columns[4].columnId\":[\"99\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"聊天内容\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"useValue\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"textarea\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"100\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"加油包id\"],\"columns[5].javaType\":[\"Long\"],\"columns[5].javaField\":[\"kitId\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"col','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:12:51',18725),(119,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"8\"],\"tableName\":[\"use_log\"],\"tableComment\":[\"使用记录表\"],\"className\":[\"UseLog\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"95\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"96\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"用户id\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"userId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"97\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"使用次数\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"useNumber\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"98\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"使用类型\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"useType\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"select\"],\"columns[3].dictType\":[\"use_type\"],\"columns[4].columnId\":[\"99\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"聊天内容\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"useValue\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"textarea\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"100\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"加油包id\"],\"columns[5].javaType\":[\"Long\"],\"columns[5].javaField\":[\"kitId\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"col','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:14:09',12581),(120,'字典类型',1,'com.tenant.web.controller.system.SysDictTypeController.addSave()','POST',1,'admin','研发部门','/system/dict/add','127.0.0.1','内网IP','{\"dictName\":[\"支付状态\"],\"dictType\":[\"pay_state\"],\"status\":[\"0\"],\"remark\":[\"状态 0待支付 1支付完成 2 支付失败\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:16:58',1272),(121,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"待支付\"],\"dictValue\":[\"0\"],\"dictType\":[\"pay_state\"],\"cssClass\":[\"\"],\"dictSort\":[\"0\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:17:42',3064),(122,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"支付成功\"],\"dictValue\":[\"1\"],\"dictType\":[\"pay_state\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"default\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:18:20',1303),(123,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"支付失败\"],\"dictValue\":[\"2\"],\"dictType\":[\"pay_state\"],\"cssClass\":[\"\"],\"dictSort\":[\"2\"],\"listClass\":[\"default\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:18:46',2734),(124,'字典类型',1,'com.tenant.web.controller.system.SysDictTypeController.addSave()','POST',1,'admin','研发部门','/system/dict/add','127.0.0.1','内网IP','{\"dictName\":[\"支付方式\"],\"dictType\":[\"pay_type\"],\"status\":[\"0\"],\"remark\":[\"支付方式 wxpay、alipay、qqpay\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:20:02',2330),(125,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"微信\"],\"dictValue\":[\"wxpay\"],\"dictType\":[\"pay_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"0\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"支付方式 wxpay、alipay、qqpay\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:21:13',1279),(126,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"支付宝\"],\"dictValue\":[\"alipay\"],\"dictType\":[\"pay_state\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:21:31',1746),(127,'字典数据',3,'com.tenant.web.controller.system.SysDictDataController.remove()','POST',1,'admin','研发部门','/system/dict/data/remove','127.0.0.1','内网IP','{\"ids\":[\"114\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:22:20',1932),(128,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"支付宝\"],\"dictValue\":[\"alipay\"],\"dictType\":[\"pay_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:22:44',1287),(129,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"QQ\"],\"dictValue\":[\"qqpay\"],\"dictType\":[\"pay_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"2\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:23:04',2287),(130,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"7\"],\"tableName\":[\"t_order\"],\"tableComment\":[\"订单表\"],\"className\":[\"TOrder\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"80\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"81\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"产品id\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"productId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"82\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"用户id\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"userId\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"83\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"价格\"],\"columns[3].javaType\":[\"BigDecimal\"],\"columns[3].javaField\":[\"price\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"84\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"状态 \"],\"columns[4].javaType\":[\"Long\"],\"columns[4].javaField\":[\"state\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"select\"],\"columns[4].dictType\":[\"pay_state\"],\"columns[5].columnId\":[\"85\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"支付方式\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"payType\"],\"columns[5].isInsert\":[\"1\"],\"columns[','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:26:15',11035),(131,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"6\"],\"tableName\":[\"sys_config2\"],\"tableComment\":[\"系统配置\"],\"className\":[\"SysConfig2\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"66\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"67\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"注册模式 1账号密码  2 短信注册 3 关闭注册\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"registrationMethod\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"68\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"是否禁用自动禁用key \"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"keySwitch\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"select\"],\"columns[2].dictType\":[\"is_not\"],\"columns[3].columnId\":[\"69\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"默认注册次数\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"defaultTimes\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"70\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"阿里云accessKeyId\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"aliAccessKeyId\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"71\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"阿里云secret\"],\"columns[5].javaType\":[\"St','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:29:13',10869),(132,'字典类型',1,'com.tenant.web.controller.system.SysDictTypeController.addSave()','POST',1,'admin','研发部门','/system/dict/add','127.0.0.1','内网IP','{\"dictName\":[\"注册模式 \"],\"dictType\":[\"registration_method\"],\"status\":[\"0\"],\"remark\":[\"注册模式 1账号密码  2 短信注册 3 关闭注册\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:29:34',1560),(133,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"6\"],\"tableName\":[\"sys_config2\"],\"tableComment\":[\"系统配置\"],\"className\":[\"SysConfig2\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"66\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"67\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"注册模式 \"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"registrationMethod\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"select\"],\"columns[1].dictType\":[\"registration_method\"],\"columns[2].columnId\":[\"68\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"是否禁用自动禁用key\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"keySwitch\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"select\"],\"columns[2].dictType\":[\"is_not\"],\"columns[3].columnId\":[\"69\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"默认注册次数\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"defaultTimes\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"70\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"阿里云accessKeyId\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"aliAccessKeyId\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"71\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"阿里云secret\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"aliSecret\"],\"columns[5]','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:31:28',14035),(134,'字典数据',1,'com.tenant.web.controller.system.SysDictDataController.addSave()','POST',1,'admin','研发部门','/system/dict/data/add','127.0.0.1','内网IP','{\"dictLabel\":[\"账号密码\"],\"dictValue\":[\"1\"],\"dictType\":[\"registration_method\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"1账号密码 2 短信注册 3...\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:35:37',1997),(135,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"5\"],\"tableName\":[\"refueling_kit\"],\"tableComment\":[\"加油包\"],\"className\":[\"RefuelingKit\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"56\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"57\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"产品id\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"productId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"58\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"用户id\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"userId\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"59\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"可使用次数\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"numberTimes\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"60\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"数据版本（默认为0，每次编辑+1）\"],\"columns[4].javaType\":[\"Long\"],\"columns[4].javaField\":[\"dataVersion\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"61\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"是否删除\"],\"columns[5].javaType\":[\"Long\"],\"columns[5].javaField\":[\"deleted\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].i','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:37:29',7839),(136,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"4\"],\"tableName\":[\"product\"],\"tableComment\":[\"产品表\"],\"className\":[\"Product\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"42\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"43\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"产品名\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"name\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"LIKE\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"44\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"价格\"],\"columns[2].javaType\":[\"BigDecimal\"],\"columns[2].javaField\":[\"price\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"45\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"类型\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"type\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"select\"],\"columns[3].dictType\":[\"use_type\"],\"columns[4].columnId\":[\"46\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"次数\"],\"columns[4].javaType\":[\"Long\"],\"columns[4].javaField\":[\"numberTimes\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"47\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"月卡每日可使用次数\"],\"columns[5].javaType\":[\"Long\"],\"columns[5].javaField\":[\"monthlyNumber\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"c','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:40:56',9783),(137,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"3\"],\"tableName\":[\"pay_config\"],\"tableComment\":[\"支付配置\"],\"className\":[\"PayConfig\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"23\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"24\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"商户id\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"pid\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"25\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"商户密钥\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"secretKey\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"26\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"回调域名\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"notifyUrl\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"27\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"跳转通知地址\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"returnUrl\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"28\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"支付请求域名\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"submitUrl\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"columns[5].isList\":[\"1\"],\"columns[5].queryType\":[\"EQ','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:46:06',18626),(138,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"2\"],\"tableName\":[\"gpt_key\"],\"tableComment\":[\"gptkey\"],\"className\":[\"GptKey\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"12\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"13\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"key\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"key\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"14\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"使用次数\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"useNumber\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"15\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"排序\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"sort\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"16\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"状态 0 启用 1禁用\"],\"columns[4].javaType\":[\"Long\"],\"columns[4].javaField\":[\"state\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"17\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"数据版本（默认为0，每次编辑+1）\"],\"columns[5].javaType\":[\"Long\"],\"columns[5].javaField\":[\"dataVersion\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"columns[5].isList\":[\"1\"],\"col','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:48:01',8710),(139,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"1\"],\"tableName\":[\"announcement\"],\"tableComment\":[\"公告\"],\"className\":[\"Announcement\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"1\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"2\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"标题\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"title\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"3\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"公告内容\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"content\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"summernote\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"4\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"排序\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"sort\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"5\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"公告类型 1-公告、2-指南\"],\"columns[4].javaType\":[\"Long\"],\"columns[4].javaField\":[\"type\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"select\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"6\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"数据版本（默认为0，每次编辑+1）\"],\"columns[5].javaType\":[\"Long\"],\"columns[5].javaField\":[\"dataVersion\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"columns[5].isList\":','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:49:55',8252),(140,'字典类型',1,'com.tenant.web.controller.system.SysDictTypeController.addSave()','POST',1,'admin','研发部门','/system/dict/add','127.0.0.1','内网IP','{\"dictName\":[\"公告类型\"],\"dictType\":[\"announcement_type\"],\"status\":[\"0\"],\"remark\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:50:37',1297),(141,'代码生成',2,'com.tenant.generator.controller.GenController.editSave()','POST',1,'admin','研发部门','/tool/gen/edit','127.0.0.1','内网IP','{\"tableId\":[\"1\"],\"tableName\":[\"announcement\"],\"tableComment\":[\"公告\"],\"className\":[\"Announcement\"],\"functionAuthor\":[\"luanyu\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"1\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"2\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"标题\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"title\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"3\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"公告内容\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"content\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"summernote\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"4\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"排序\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"sort\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"5\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"公告类型\"],\"columns[4].javaType\":[\"Long\"],\"columns[4].javaField\":[\"type\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"select\"],\"columns[4].dictType\":[\"announcement_type\"],\"columns[5].columnId\":[\"6\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"数据版本（默认为0，每次编辑+1）\"],\"columns[5].javaType\":[\"Long\"],\"columns[5].javaField\":[\"dataVersion\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"columns[5].i','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 22:51:37',8461),(142,'代码生成',8,'com.tenant.generator.controller.GenController.batchGenCode()','GET',1,'admin','研发部门','/tool/gen/batchGenCode','127.0.0.1','内网IP','{\"tables\":[\"wx_log,user,use_log,t_order,sys_config2,refueling_kit,product,pay_config,gpt_key,announcement\"]}',NULL,0,NULL,'2023-04-18 22:52:32',13331),(143,'菜单管理',3,'com.tenant.web.controller.system.SysMenuController.remove()','GET',1,'admin','研发部门','/system/menu/remove/2001','127.0.0.1','内网IP','2001','{\"msg\":\"存在子菜单,不允许删除\",\"code\":301}',0,NULL,'2023-04-18 23:13:05',258),(144,'菜单管理',3,'com.tenant.web.controller.system.SysMenuController.remove()','GET',1,'admin','研发部门','/system/menu/remove/2005','127.0.0.1','内网IP','2005','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-18 23:13:19',1702),(145,'用户',3,'com.tenant.ai.controller.UserController.remove()','POST',1,'admin','研发部门','/ai/user/remove','39.144.38.170','安徽省 合肥市','{\"ids\":[\"1648246508083294200\"]}','{\"msg\":\"操作失败\",\"code\":500}',0,NULL,'2023-04-19 11:19:41',68),(146,'用户',3,'com.tenant.ai.controller.UserController.remove()','POST',1,'admin','研发部门','/ai/user/remove','39.144.38.170','安徽省 合肥市','{\"ids\":[\"1648246508083294200\"]}','{\"msg\":\"操作失败\",\"code\":500}',0,NULL,'2023-04-19 11:20:08',14),(147,'支付配置',1,'com.tenant.ai.controller.PayConfigController.addSave()','POST',1,'admin','研发部门','/ai/payconfig/add','39.144.38.170','安徽省 合肥市','{\"pid\":[\"\"],\"secretKey\":[\"\"],\"notifyUrl\":[\"\"],\"returnUrl\":[\"\"],\"submitUrl\":[\"\"],\"aliAppId\":[\"2019110968993804\"],\"aliPrivateKey\":[\"MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDNsoHcQaF5+ZR5YGn2E9hhaF+0S76J8Zv8tCKKL9RHodFlmpVD5to2S1/ie4CyJcuukY+dCL7w0WpN6FKpAmXbfMUWgg5+25U6hHOqblmkOYLDhEj7W3/VzzgR1ubhXdg012W5bQGJrYepjywMMoj2XZFD1jRDDAT1wlGic9W1g50U4t1sRjj5lB99ZpdB03sBLMKs6gq4GTfAQeSJveTUGPuWT2mK71AO28NFZLgUg4OtyiXR/QEBnH9oL6jzxmOEmI97Smujkwl6Xb4SLnImtEIQoJT2Mmz7FEVJQxp8JtvUOIt/ArKbt2DQ4oyG2F5CGbX/hzDEZtP6HpeSeltxAgMBAAECggEBAJiOkh2nV3m9j07OIQhvA4aLAKAZpUFtvkEX4J4Yhxpxe3odwiLQLnsiQAUbkAGQYpYgQyE2gVbeGjeZ3OSYcK7uxMXCw7l2ZiE2Zwq6OSXBuagEjpRN8mvC+lTQy//NuDLIpht2KzPPTdSF6SdKu6XtE3X6glxPp8YVzrHrs06g7y5DOUSZ8JfUfly0QwLyHUqBfB8KBZmdkNUr0nJeo9PdwHeU/PHYO+bOJ69F6BO7y9/HunHjBEQTQ9o4INQBK5fER1aNZc79wnYWy7jsioAtVSFAvLQBcyerHPqBbo57k3N4hecn6fgBog2km0n72Lt25DUB/8R7W8yf1gX3wAECgYEA9tidFFIlavUsD6bl7B7H2pQLTiqIg4yc1SMDKj8460eky5qb9uaSLkgSDdkZiCXHdxMJqpEZG0At0WKUUfMExnWDxlrUEU49EDsAFGZGjXoBi+psW9O9g5ECC9/SE9Lq5bj4k54P8x674B9ir2a5L9Fg6m0jeN7+ZHKKWocYxXECgYEA1VNBROJd9P43rf6qC9nMf0Ch4OCFC1EYcyik8CCx68SXDVobTp99tBLEXZM9JjY844PNFBwWoHr5m91Te+7u7ngCAkHRj+UiI+jjZjyCwy8rS1BGtcPib1K3c7/Y3IbctKUH0Qg9ZO47a7bOEkktFQQBeAI5eWWzxWPEN0iX9gECgYEAmY6dAOQtNME53K7Ff0KSru3DrqeU7sjNU2WJdYGI5gxWfvY6Kbi7nSDmYkj4shJWaEOmkr3asXIWTpgVjnkwMkkuZXlhTTavk+YwIYCSSTZpueFl8SXiUfMmI77jj8N4rtmHgUUlxOcMltrPaC/t6cpFkO+9zlZiSJhcav55sXECgYEAvESY0uFxCJofq8iYbJMQfrIc66la4cz1ZhTV1DMWszwyD4+Zj/8EYps5gFhE3Gd7v1N/96J+ea1ug5eGaVdKuV1icHmEKJmRUt3ja0Q2J2qGrBYyAZLaUWNQErCbBK1lNWx8hIfgHyZ9fKe+ILdCOUzquu5DqRcP3EIbVqsTqgECgYEAoZZm98R8GjB0yLi8dpOH6NF3oxfyYy5E9qzjLFLuGI6R+KSlZoktKiaM+dmLVJSjGbyJCvRH0KJ+knFkhrYBDvmta65thlAO+SaYR+r8pXkQ/yjEMye1SVQrg843lYn6Q39qKaQNberO58oE2BosNVTH+/vJi1lMwsHpEAywrsE=\"],\"aliPublicKey\":[\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAptygeHGKo+QxdJrC0jyOGfFIlyxaseXyicWCOj6PhYXc4A/ffHnxuu7vqBeJJ+wgdZcHijffJgWTq5vomrYoD2g3zi2Z+s8bysKHaFU+IaHWHJrATVrRaeBSCZltKVWaDYAFp35y9Lkf9Xd6VDVHonxzPuvIIbvJzBRlwg5w1UMFJ45Uc2',NULL,1,'\n### Error updating database.  Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\n### The error may exist in URL [jar:file:/workspace/tenant-admin.jar!/BOOT-INF/lib/tenant-chatGPT-4.7.6.jar!/mapper/ai/PayConfigMapper.xml]\n### The error may involve com.tenant.ai.mapper.PayConfigMapper.insertPayConfig-Inline\n### The error occurred while setting parameters\n### SQL: insert into pay_config          ( secret_key,             notify_url,             return_url,             submit_url,             ali_app_id,             ali_private_key,             ali_public_key,             ali_gateway_url,             ali_notify_url,             ali_return_url,             pay_type,                          deleted,                          create_time )           values ( ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,                          ?,                          ? )\n### Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\n; Field \'id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'id\' doesn\'t have a default value','2023-04-19 12:31:26',134),(148,'支付配置',1,'com.tenant.ai.controller.PayConfigController.addSave()','POST',1,'admin','研发部门','/ai/payconfig/add','39.144.38.170','安徽省 合肥市','{\"pid\":[\"\"],\"secretKey\":[\"\"],\"notifyUrl\":[\"\"],\"returnUrl\":[\"\"],\"submitUrl\":[\"\"],\"aliAppId\":[\"2019110968993804\"],\"aliPrivateKey\":[\"MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDNsoHcQaF5+ZR5YGn2E9hhaF+0S76J8Zv8tCKKL9RHodFlmpVD5to2S1/ie4CyJcuukY+dCL7w0WpN6FKpAmXbfMUWgg5+25U6hHOqblmkOYLDhEj7W3/VzzgR1ubhXdg012W5bQGJrYepjywMMoj2XZFD1jRDDAT1wlGic9W1g50U4t1sRjj5lB99ZpdB03sBLMKs6gq4GTfAQeSJveTUGPuWT2mK71AO28NFZLgUg4OtyiXR/QEBnH9oL6jzxmOEmI97Smujkwl6Xb4SLnImtEIQoJT2Mmz7FEVJQxp8JtvUOIt/ArKbt2DQ4oyG2F5CGbX/hzDEZtP6HpeSeltxAgMBAAECggEBAJiOkh2nV3m9j07OIQhvA4aLAKAZpUFtvkEX4J4Yhxpxe3odwiLQLnsiQAUbkAGQYpYgQyE2gVbeGjeZ3OSYcK7uxMXCw7l2ZiE2Zwq6OSXBuagEjpRN8mvC+lTQy//NuDLIpht2KzPPTdSF6SdKu6XtE3X6glxPp8YVzrHrs06g7y5DOUSZ8JfUfly0QwLyHUqBfB8KBZmdkNUr0nJeo9PdwHeU/PHYO+bOJ69F6BO7y9/HunHjBEQTQ9o4INQBK5fER1aNZc79wnYWy7jsioAtVSFAvLQBcyerHPqBbo57k3N4hecn6fgBog2km0n72Lt25DUB/8R7W8yf1gX3wAECgYEA9tidFFIlavUsD6bl7B7H2pQLTiqIg4yc1SMDKj8460eky5qb9uaSLkgSDdkZiCXHdxMJqpEZG0At0WKUUfMExnWDxlrUEU49EDsAFGZGjXoBi+psW9O9g5ECC9/SE9Lq5bj4k54P8x674B9ir2a5L9Fg6m0jeN7+ZHKKWocYxXECgYEA1VNBROJd9P43rf6qC9nMf0Ch4OCFC1EYcyik8CCx68SXDVobTp99tBLEXZM9JjY844PNFBwWoHr5m91Te+7u7ngCAkHRj+UiI+jjZjyCwy8rS1BGtcPib1K3c7/Y3IbctKUH0Qg9ZO47a7bOEkktFQQBeAI5eWWzxWPEN0iX9gECgYEAmY6dAOQtNME53K7Ff0KSru3DrqeU7sjNU2WJdYGI5gxWfvY6Kbi7nSDmYkj4shJWaEOmkr3asXIWTpgVjnkwMkkuZXlhTTavk+YwIYCSSTZpueFl8SXiUfMmI77jj8N4rtmHgUUlxOcMltrPaC/t6cpFkO+9zlZiSJhcav55sXECgYEAvESY0uFxCJofq8iYbJMQfrIc66la4cz1ZhTV1DMWszwyD4+Zj/8EYps5gFhE3Gd7v1N/96J+ea1ug5eGaVdKuV1icHmEKJmRUt3ja0Q2J2qGrBYyAZLaUWNQErCbBK1lNWx8hIfgHyZ9fKe+ILdCOUzquu5DqRcP3EIbVqsTqgECgYEAoZZm98R8GjB0yLi8dpOH6NF3oxfyYy5E9qzjLFLuGI6R+KSlZoktKiaM+dmLVJSjGbyJCvRH0KJ+knFkhrYBDvmta65thlAO+SaYR+r8pXkQ/yjEMye1SVQrg843lYn6Q39qKaQNberO58oE2BosNVTH+/vJi1lMwsHpEAywrsE=\"],\"aliPublicKey\":[\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAptygeHGKo+QxdJrC0jyOGfFIlyxaseXyicWCOj6PhYXc4A/ffHnxuu7vqBeJJ+wgdZcHijffJgWTq5vomrYoD2g3zi2Z+s8bysKHaFU+IaHWHJrATVrRaeBSCZltKVWaDYAFp35y9Lkf9Xd6VDVHonxzPuvIIbvJzBRlwg5w1UMFJ45Uc2',NULL,1,'\n### Error updating database.  Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\n### The error may exist in URL [jar:file:/workspace/tenant-admin.jar!/BOOT-INF/lib/tenant-chatGPT-4.7.6.jar!/mapper/ai/PayConfigMapper.xml]\n### The error may involve com.tenant.ai.mapper.PayConfigMapper.insertPayConfig-Inline\n### The error occurred while setting parameters\n### SQL: insert into pay_config          ( secret_key,             notify_url,             return_url,             submit_url,             ali_app_id,             ali_private_key,             ali_public_key,             ali_gateway_url,             ali_notify_url,             ali_return_url,             pay_type,                          deleted,                          create_time )           values ( ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,                          ?,                          ? )\n### Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\n; Field \'id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'id\' doesn\'t have a default value','2023-04-19 12:31:49',8),(149,'支付配置',1,'com.tenant.ai.controller.PayConfigController.addSave()','POST',1,'admin','研发部门','/ai/payconfig/add','39.144.38.170','安徽省 合肥市','{\"pid\":[\"\"],\"secretKey\":[\"\"],\"notifyUrl\":[\"\"],\"returnUrl\":[\"\"],\"submitUrl\":[\"\"],\"aliAppId\":[\"2019110968993804\"],\"aliPrivateKey\":[\"MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDNsoHcQaF5+ZR5YGn2E9hhaF+0S76J8Zv8tCKKL9RHodFlmpVD5to2S1/ie4CyJcuukY+dCL7w0WpN6FKpAmXbfMUWgg5+25U6hHOqblmkOYLDhEj7W3/VzzgR1ubhXdg012W5bQGJrYepjywMMoj2XZFD1jRDDAT1wlGic9W1g50U4t1sRjj5lB99ZpdB03sBLMKs6gq4GTfAQeSJveTUGPuWT2mK71AO28NFZLgUg4OtyiXR/QEBnH9oL6jzxmOEmI97Smujkwl6Xb4SLnImtEIQoJT2Mmz7FEVJQxp8JtvUOIt/ArKbt2DQ4oyG2F5CGbX/hzDEZtP6HpeSeltxAgMBAAECggEBAJiOkh2nV3m9j07OIQhvA4aLAKAZpUFtvkEX4J4Yhxpxe3odwiLQLnsiQAUbkAGQYpYgQyE2gVbeGjeZ3OSYcK7uxMXCw7l2ZiE2Zwq6OSXBuagEjpRN8mvC+lTQy//NuDLIpht2KzPPTdSF6SdKu6XtE3X6glxPp8YVzrHrs06g7y5DOUSZ8JfUfly0QwLyHUqBfB8KBZmdkNUr0nJeo9PdwHeU/PHYO+bOJ69F6BO7y9/HunHjBEQTQ9o4INQBK5fER1aNZc79wnYWy7jsioAtVSFAvLQBcyerHPqBbo57k3N4hecn6fgBog2km0n72Lt25DUB/8R7W8yf1gX3wAECgYEA9tidFFIlavUsD6bl7B7H2pQLTiqIg4yc1SMDKj8460eky5qb9uaSLkgSDdkZiCXHdxMJqpEZG0At0WKUUfMExnWDxlrUEU49EDsAFGZGjXoBi+psW9O9g5ECC9/SE9Lq5bj4k54P8x674B9ir2a5L9Fg6m0jeN7+ZHKKWocYxXECgYEA1VNBROJd9P43rf6qC9nMf0Ch4OCFC1EYcyik8CCx68SXDVobTp99tBLEXZM9JjY844PNFBwWoHr5m91Te+7u7ngCAkHRj+UiI+jjZjyCwy8rS1BGtcPib1K3c7/Y3IbctKUH0Qg9ZO47a7bOEkktFQQBeAI5eWWzxWPEN0iX9gECgYEAmY6dAOQtNME53K7Ff0KSru3DrqeU7sjNU2WJdYGI5gxWfvY6Kbi7nSDmYkj4shJWaEOmkr3asXIWTpgVjnkwMkkuZXlhTTavk+YwIYCSSTZpueFl8SXiUfMmI77jj8N4rtmHgUUlxOcMltrPaC/t6cpFkO+9zlZiSJhcav55sXECgYEAvESY0uFxCJofq8iYbJMQfrIc66la4cz1ZhTV1DMWszwyD4+Zj/8EYps5gFhE3Gd7v1N/96J+ea1ug5eGaVdKuV1icHmEKJmRUt3ja0Q2J2qGrBYyAZLaUWNQErCbBK1lNWx8hIfgHyZ9fKe+ILdCOUzquu5DqRcP3EIbVqsTqgECgYEAoZZm98R8GjB0yLi8dpOH6NF3oxfyYy5E9qzjLFLuGI6R+KSlZoktKiaM+dmLVJSjGbyJCvRH0KJ+knFkhrYBDvmta65thlAO+SaYR+r8pXkQ/yjEMye1SVQrg843lYn6Q39qKaQNberO58oE2BosNVTH+/vJi1lMwsHpEAywrsE=\"],\"aliPublicKey\":[\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAptygeHGKo+QxdJrC0jyOGfFIlyxaseXyicWCOj6PhYXc4A/ffHnxuu7vqBeJJ+wgdZcHijffJgWTq5vomrYoD2g3zi2Z+s8bysKHaFU+IaHWHJrATVrRaeBSCZltKVWaDYAFp35y9Lkf9Xd6VDVHonxzPuvIIbvJzBRlwg5w1UMFJ45Uc2','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-19 12:33:33',18),(150,'产品',1,'com.tenant.ai.controller.ProductController.addSave()','POST',1,'admin','研发部门','/ai/product/add','39.144.38.170','安徽省 合肥市','{\"name\":[\"500次\"],\"price\":[\"20\"],\"type\":[\"1\"],\"numberTimes\":[\"500\"],\"monthlyNumber\":[\"\"],\"sort\":[\"\"],\"stock\":[\"\"],\"dataVersion\":[\"\"],\"deleted\":[\"0\"],\"creator\":[\"\"],\"operator\":[\"\"],\"operateTime\":[\"\"]}',NULL,1,'\n### Error updating database.  Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\n### The error may exist in URL [jar:file:/workspace/tenant-admin.jar!/BOOT-INF/lib/tenant-chatGPT-4.7.6.jar!/mapper/ai/ProductMapper.xml]\n### The error may involve com.tenant.ai.mapper.ProductMapper.insertProduct-Inline\n### The error occurred while setting parameters\n### SQL: insert into product          ( name,             price,             type,             number_times,                                                                 deleted,                          create_time )           values ( ?,             ?,             ?,             ?,                                                                 ?,                          ? )\n### Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\n; Field \'id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'id\' doesn\'t have a default value','2023-04-19 12:35:22',8),(151,'产品',1,'com.tenant.ai.controller.ProductController.addSave()','POST',1,'admin','研发部门','/ai/product/add','39.144.38.170','安徽省 合肥市','{\"name\":[\"500次\"],\"price\":[\"20\"],\"type\":[\"1\"],\"numberTimes\":[\"500\"],\"monthlyNumber\":[\"\"],\"sort\":[\"\"],\"stock\":[\"\"],\"dataVersion\":[\"\"],\"deleted\":[\"0\"],\"creator\":[\"\"],\"operator\":[\"\"],\"operateTime\":[\"\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-19 12:36:39',14),(152,'产品',2,'com.tenant.ai.controller.ProductController.editSave()','POST',1,'admin','研发部门','/ai/product/edit','39.144.38.170','安徽省 合肥市','{\"id\":[\"1\"],\"name\":[\"500次\"],\"price\":[\"20.00\"],\"type\":[\"1\"],\"numberTimes\":[\"500\"],\"monthlyNumber\":[\"\"],\"sort\":[\"0\"],\"stock\":[\"100\"],\"dataVersion\":[\"0\"],\"deleted\":[\"0\"],\"creator\":[\"0\"],\"operator\":[\"0\"],\"operateTime\":[\"2023-04-19\"]}','{\"msg\":\"操作成功\",\"code\":0}',0,NULL,'2023-04-19 12:37:09',11);
/*!40000 ALTER TABLE `sys_oper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_post` (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='岗位信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` VALUES (1,'ceo','董事长',1,'0','admin','2023-04-18 21:11:50','',NULL,''),(2,'se','项目经理',2,'0','admin','2023-04-18 21:11:50','',NULL,''),(3,'hr','人力资源',3,'0','admin','2023-04-18 21:11:50','',NULL,''),(4,'user','普通员工',4,'0','admin','2023-04-18 21:11:50','',NULL,'');
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','admin',1,'1','0','0','admin','2023-04-18 21:11:51','',NULL,'超级管理员'),(2,'普通角色','common',2,'2','0','0','admin','2023-04-18 21:11:51','',NULL,'普通角色');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色和部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_dept`
--

LOCK TABLES `sys_role_dept` WRITE;
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
INSERT INTO `sys_role_dept` VALUES (2,100),(2,101),(2,105);
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (2,1),(2,2),(2,3),(2,4),(2,100),(2,101),(2,102),(2,103),(2,104),(2,105),(2,106),(2,107),(2,108),(2,109),(2,110),(2,111),(2,112),(2,113),(2,114),(2,115),(2,116),(2,500),(2,501),(2,1000),(2,1001),(2,1002),(2,1003),(2,1004),(2,1005),(2,1006),(2,1007),(2,1008),(2,1009),(2,1010),(2,1011),(2,1012),(2,1013),(2,1014),(2,1015),(2,1016),(2,1017),(2,1018),(2,1019),(2,1020),(2,1021),(2,1022),(2,1023),(2,1024),(2,1025),(2,1026),(2,1027),(2,1028),(2,1029),(2,1030),(2,1031),(2,1032),(2,1033),(2,1034),(2,1035),(2,1036),(2,1037),(2,1038),(2,1039),(2,1040),(2,1041),(2,1042),(2,1043),(2,1044),(2,1045),(2,1046),(2,1047),(2,1048),(2,1049),(2,1050),(2,1051),(2,1052),(2,1053),(2,1054),(2,1055),(2,1056),(2,1057),(2,1058),(2,1059),(2,1060),(2,1061);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户昵称',
  `user_type` varchar(2) COLLATE utf8mb4_general_ci DEFAULT '00' COMMENT '用户类型（00系统用户 01注册用户）',
  `email` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '手机号码',
  `sex` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '密码',
  `salt` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '盐加密',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `pwd_update_date` datetime DEFAULT NULL COMMENT '密码最后更新时间',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,103,'admin','若依','00','ry@163.com','15888888888','1','','29c67a30398638269fe600f73a054934','111111','0','0','39.144.38.170','2023-04-19 13:30:35','2023-04-18 21:11:50','admin','2023-04-18 21:11:50','','2023-04-19 13:30:34','管理员'),(2,105,'ry','若依','00','ry@qq.com','15666666666','1','','8e6d98b90472783cc73c17047ddccf36','222222','0','0','127.0.0.1','2023-04-18 21:11:50','2023-04-18 21:11:50','admin','2023-04-18 21:11:50','',NULL,'测试员');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_online`
--

DROP TABLE IF EXISTS `sys_user_online`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(128) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int DEFAULT '0' COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='在线用户记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_online`
--

LOCK TABLES `sys_user_online` WRITE;
/*!40000 ALTER TABLE `sys_user_online` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_online` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_post`
--

DROP TABLE IF EXISTS `sys_user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_post` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户与岗位关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_post`
--

LOCK TABLES `sys_user_post` WRITE;
/*!40000 ALTER TABLE `sys_user_post` DISABLE KEYS */;
INSERT INTO `sys_user_post` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `sys_user_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_order` (
  `id` bigint NOT NULL,
  `product_id` bigint DEFAULT NULL COMMENT '产品id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `state` int DEFAULT '0' COMMENT '状态 0待支付 1支付完成 2 支付失败',
  `pay_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付方式 wxpay、alipay、qqpay',
  `pay_number` int DEFAULT NULL COMMENT '购买数量',
  `trade_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '易支付订单号',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付结果消息\n',
  `data_version` int DEFAULT '0' COMMENT '数据版本（默认为0，每次编辑+1）',
  `deleted` int DEFAULT '0' COMMENT '是否删除：0-否、1-是',
  `creator` bigint DEFAULT '0' COMMENT '创建人编号（默认为0）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间（默认为创建时服务器时间）',
  `operator` bigint DEFAULT '0' COMMENT '操作人编号（默认为0）',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间（每次更新时自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `use_log`
--

DROP TABLE IF EXISTS `use_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `use_log` (
  `id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `use_number` int DEFAULT '1' COMMENT '使用次数',
  `use_type` tinyint DEFAULT '1' COMMENT '使用类型 1 次数 2 月卡 3加油包',
  `use_value` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '聊天内容',
  `kit_id` bigint DEFAULT NULL COMMENT '加油包id',
  `gpt_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '使用gptkey',
  `state` tinyint DEFAULT '0' COMMENT '是否成功 0成功 1失败',
  `question` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '问题',
  `answer` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '答案',
  `send_type` tinyint DEFAULT '0' COMMENT '消息类型 0-正常 1-流式',
  `data_version` int DEFAULT '0' COMMENT '数据版本（默认为0，每次编辑+1）',
  `deleted` int DEFAULT '0' COMMENT '是否删除：0-否、1-是',
  `creator` bigint DEFAULT '0' COMMENT '创建人编号（默认为0）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（默认为创建时服务器时间）',
  `operator` bigint DEFAULT '0' COMMENT '操作人编号（默认为0）',
  `operate_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间（每次更新时自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='使用记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `use_log`
--

LOCK TABLES `use_log` WRITE;
/*!40000 ALTER TABLE `use_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `use_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '123456' COMMENT '密码',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `type` tinyint DEFAULT '0' COMMENT '类型 0 次数用户 1 月卡用户 -1 管理员',
  `expiration_time` datetime DEFAULT NULL COMMENT '月卡到期日期',
  `remaining_times` int DEFAULT '5' COMMENT '剩余次数',
  `card_day_max_number` int DEFAULT '0' COMMENT '月卡当日使用最大次数',
  `from_user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '微信用户账号',
  `is_event` tinyint DEFAULT '0' COMMENT '是否关注公众号 0未关注 1关注',
  `data_version` int DEFAULT '0' COMMENT '数据版本（默认为0，每次编辑+1）',
  `deleted` int DEFAULT '0' COMMENT '是否删除：0-否、1-是',
  `creator` bigint DEFAULT '0' COMMENT '创建人编号（默认为0）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（默认为创建时服务器时间）',
  `operator` bigint DEFAULT '0' COMMENT '操作人编号（默认为0）',
  `operate_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间（每次更新时自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1648246508083294210,'luanyu','luanyu','123456',NULL,0,NULL,10,0,NULL,0,0,0,0,'2023-04-18 16:46:05',0,'2023-04-18 16:46:05'),(1648248357389676546,'chat','15665512808','123456','2023-04-19 12:06:15',0,NULL,0,0,NULL,0,10,0,0,'2023-04-18 16:53:26',0,'2023-04-19 12:06:15');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_log`
--

DROP TABLE IF EXISTS `wx_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wx_log` (
  `id` bigint NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求内容',
  `from_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '微信用户账号',
  `data_version` int DEFAULT '0' COMMENT '数据版本（默认为0，每次编辑+1）',
  `deleted` int DEFAULT '0' COMMENT '是否删除：0-否、1-是',
  `creator` bigint DEFAULT '0' COMMENT '创建人编号（默认为0）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（默认为创建时服务器时间）',
  `operator` bigint DEFAULT '0' COMMENT '操作人编号（默认为0）',
  `operate_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间（每次更新时自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='微信日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_log`
--

LOCK TABLES `wx_log` WRITE;
/*!40000 ALTER TABLE `wx_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `wx_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'gpt'
--

--
-- Dumping routines for database 'gpt'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-19 14:48:09
