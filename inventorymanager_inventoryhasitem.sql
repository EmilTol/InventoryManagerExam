-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: inventorymanager
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `inventoryhasitem`
--

DROP TABLE IF EXISTS `inventoryhasitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventoryhasitem` (
  `idinventoryhasitem` int NOT NULL AUTO_INCREMENT,
  `fkinventory` int DEFAULT NULL,
  `fkitem` int DEFAULT NULL,
  PRIMARY KEY (`idinventoryhasitem`)
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventoryhasitem`
--

LOCK TABLES `inventoryhasitem` WRITE;
/*!40000 ALTER TABLE `inventoryhasitem` DISABLE KEYS */;
INSERT INTO `inventoryhasitem` VALUES (6,2,5),(7,2,6),(8,3,2),(10,2,3),(35,6,1),(106,2,7),(158,1,1),(159,1,1),(160,1,1),(161,1,1),(162,1,1),(163,1,1),(164,1,1),(166,1,7),(167,1,1),(168,1,1),(169,1,7),(171,1,1),(172,1,6),(173,1,1),(174,1,1),(175,1,1),(176,1,1),(177,1,1),(178,1,1),(179,1,1),(180,1,1),(181,1,1),(182,1,1),(183,1,1),(184,1,2),(185,1,2),(186,1,1),(187,1,1),(188,1,1),(189,1,1),(190,1,1),(191,1,1),(192,1,1),(193,1,1),(194,1,1),(195,1,2),(197,5,9),(198,5,8),(199,5,6),(200,5,3),(201,5,4),(202,5,1),(203,5,1),(204,5,1),(205,5,1),(206,5,1),(207,5,1),(208,5,1),(209,5,1),(210,5,1),(211,5,1),(212,5,1);
/*!40000 ALTER TABLE `inventoryhasitem` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-18 10:38:36
