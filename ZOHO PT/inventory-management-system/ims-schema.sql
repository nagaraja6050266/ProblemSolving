-- MySQL dump 10.13  Distrib 8.0.38, for macos14 (x86_64)
--
-- Host: localhost    Database: ims
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adjustedgoods`
--

DROP TABLE IF EXISTS `adjustedgoods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adjustedgoods` (
  `itemId` bigint unsigned NOT NULL,
  `reason` varchar(30) DEFAULT NULL,
  `dateRemoved` date NOT NULL,
  `quantity` double NOT NULL,
  `batchId` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`itemId`),
  KEY `batchId` (`batchId`),
  CONSTRAINT `adjustedgoods_ibfk_2` FOREIGN KEY (`itemId`) REFERENCES `Items` (`itemId`) ON DELETE CASCADE,
  CONSTRAINT `adjustedgoods_ibfk_3` FOREIGN KEY (`batchId`) REFERENCES `batches` (`batchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `batches`
--

DROP TABLE IF EXISTS `batches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batches` (
  `batchId` bigint unsigned NOT NULL AUTO_INCREMENT,
  `warehouseId` bigint unsigned DEFAULT NULL,
  `itemId` bigint unsigned DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `expiryDate` date DEFAULT NULL,
  PRIMARY KEY (`batchId`),
  KEY `batchTableWarehouseId` (`warehouseId`),
  KEY `batchTableItemId` (`itemId`),
  CONSTRAINT `batchTableItemId` FOREIGN KEY (`itemId`) REFERENCES `items` (`itemId`) ON DELETE CASCADE,
  CONSTRAINT `batchTableWarehouseId` FOREIGN KEY (`warehouseId`) REFERENCES `warehouses` (`warehouseId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `categoryId` bigint unsigned NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(30) NOT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customerId` bigint unsigned NOT NULL AUTO_INCREMENT,
  `customerName` varchar(30) NOT NULL,
  `phNumber` bigint NOT NULL,
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `itemId` bigint unsigned NOT NULL AUTO_INCREMENT,
  `itemName` varchar(30) NOT NULL,
  `categoryId` bigint unsigned DEFAULT NULL,
  `costPrice` double NOT NULL,
  `sellingPrice` double NOT NULL,
  PRIMARY KEY (`itemId`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `items_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`categoryId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Managers`
--

DROP TABLE IF EXISTS `Managers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Managers` (
  `managerId` bigint unsigned NOT NULL AUTO_INCREMENT,
  `managerName` varchar(30) DEFAULT NULL,
  `access` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`managerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `purchaseItems`
--

DROP TABLE IF EXISTS `purchaseItems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchaseItems` (
  `purchaseId` bigint unsigned NOT NULL,
  `itemId` bigint unsigned NOT NULL,
  `quantity` double NOT NULL,
  `amount` double NOT NULL,
  `expiryDate` date DEFAULT NULL,
  PRIMARY KEY (`itemId`,`purchaseId`),
  KEY `purchaseitems_ibfk_2` (`purchaseId`),
  CONSTRAINT `purchaseitems_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `Items` (`itemId`) ON DELETE CASCADE,
  CONSTRAINT `purchaseitems_ibfk_2` FOREIGN KEY (`purchaseId`) REFERENCES `Purchases` (`purchaseId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchases` (
  `purchaseId` bigint unsigned NOT NULL AUTO_INCREMENT,
  `supplierId` bigint unsigned NOT NULL,
  `date` date NOT NULL,
  `totalAmount` double NOT NULL,
  `managerId` bigint unsigned DEFAULT NULL,
  `warehouseId` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`purchaseId`),
  KEY `purchases_ibfk_1` (`supplierId`),
  KEY `purchaseTableManagerId` (`managerId`),
  KEY `warehouseId` (`warehouseId`),
  CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`supplierId`) REFERENCES `Suppliers` (`supplierId`) ON DELETE CASCADE,
  CONSTRAINT `purchases_ibfk_2` FOREIGN KEY (`warehouseId`) REFERENCES `warehouses` (`warehouseId`) ON DELETE CASCADE,
  CONSTRAINT `purchaseTableManagerId` FOREIGN KEY (`managerId`) REFERENCES `managers` (`managerId`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `salesId` bigint unsigned NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `customerId` bigint unsigned NOT NULL,
  `totalAmount` float DEFAULT NULL,
  PRIMARY KEY (`salesId`),
  KEY `sales_ibfk_1` (`customerId`),
  CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `Customers` (`customerId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `salesItems`
--

DROP TABLE IF EXISTS `salesItems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salesItems` (
  `salesId` bigint unsigned NOT NULL,
  `itemId` bigint unsigned NOT NULL,
  `quantity` double NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`itemId`,`salesId`),
  KEY `salesitems_ibfk_2` (`salesId`),
  CONSTRAINT `salesitems_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `Items` (`itemId`) ON DELETE CASCADE,
  CONSTRAINT `salesitems_ibfk_2` FOREIGN KEY (`salesId`) REFERENCES `Sales` (`salesId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `supplierId` bigint unsigned NOT NULL AUTO_INCREMENT,
  `supplierName` varchar(30) NOT NULL,
  `phNumber` varchar(10) NOT NULL,
  PRIMARY KEY (`supplierId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `warehouseitems`
--

DROP TABLE IF EXISTS `warehouseitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouseitems` (
  `warehouseId` bigint unsigned NOT NULL AUTO_INCREMENT,
  `itemId` bigint unsigned NOT NULL,
  `quantity` double DEFAULT NULL,
  PRIMARY KEY (`itemId`,`warehouseId`),
  KEY `warehouseId` (`warehouseId`),
  CONSTRAINT `warehouseitems_ibfk_3` FOREIGN KEY (`itemId`) REFERENCES `items` (`itemId`) ON DELETE CASCADE,
  CONSTRAINT `warehouseitems_ibfk_4` FOREIGN KEY (`warehouseId`) REFERENCES `warehouses` (`warehouseId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `warehouses`
--

DROP TABLE IF EXISTS `warehouses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouses` (
  `warehouseId` bigint unsigned NOT NULL AUTO_INCREMENT,
  `warehouseName` varchar(30) NOT NULL,
  `location` varchar(100) NOT NULL,
  `capacity` int NOT NULL,
  PRIMARY KEY (`warehouseId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-18 10:04:30
