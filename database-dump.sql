-- MySQL dump 10.13  Distrib 8.0.18, for Linux (x86_64)
--
-- Host: localhost    Database: order_db
-- ------------------------------------------------------
-- Server version	8.0.18

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


DROP DATABASE IF EXISTS `order_db`;
CREATE DATABASE `order_db`;
USE `order_db`;

--
-- Table structure for table `customer_tb`
--

DROP TABLE IF EXISTS `customer_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_tb` (
  `custId` int(11) NOT NULL AUTO_INCREMENT,
  `custName` varchar(45) NOT NULL,
  `custPhone` varchar(10) NOT NULL,
  `custEmail` varchar(45) NOT NULL,
  `custAddress` varchar(255) NOT NULL,
  PRIMARY KEY (`custId`),
  UNIQUE KEY `custPhone_UNIQUE` (`custPhone`),
  UNIQUE KEY `custEmail_UNIQUE` (`custEmail`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_tb`
--

LOCK TABLES `customer_tb` WRITE;
/*!40000 ALTER TABLE `customer_tb` DISABLE KEYS */;
INSERT INTO `customer_tb` VALUES (1,'Sanjeet Anand','8987045110','sanjeet.shannonitu25@gmail.com','Bhopal, MP'),(2,'Guddu','8083486995','guddusharma2508@gmail.com','Bhopal, MP');
/*!40000 ALTER TABLE `customer_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_tb`
--

DROP TABLE IF EXISTS `order_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_tb` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `custId` int(11) NOT NULL,
  `orderDate` date NOT NULL,
  `orderStatus` varchar(45) NOT NULL,
  `orderCost` double NOT NULL,
  `orderProduct` varchar(255) NOT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_tb`
--

LOCK TABLES `order_tb` WRITE;
/*!40000 ALTER TABLE `order_tb` DISABLE KEYS */;
INSERT INTO `order_tb` VALUES (2,2,'2020-04-25','PACKAGED',146,'Cigrette:1,Cold Drink:2,Rice:1');
/*!40000 ALTER TABLE `order_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_tb`
--

DROP TABLE IF EXISTS `product_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_tb` (
  `prodId` int(11) NOT NULL AUTO_INCREMENT,
  `prodName` varchar(45) NOT NULL,
  `prodCategory` varchar(45) NOT NULL,
  `prodDesc` varchar(255) DEFAULT NULL,
  `prodPrice` double NOT NULL,
  `prodQuantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`prodId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_tb`
--

LOCK TABLES `product_tb` WRITE;
/*!40000 ALTER TABLE `product_tb` DISABLE KEYS */;
INSERT INTO `product_tb` VALUES (1,'Cigrette','MISCELLANEOUS','Long White Narcotics Stick',16,10),(2,'Cold Drink','MISCELLANEOUS','Carbonated Liquid with flavour',45,20),(3,'Rice','GRAINS','White in colour',40,50);
/*!40000 ALTER TABLE `product_tb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-25 17:32:28
