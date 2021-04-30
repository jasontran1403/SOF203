-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: account
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `liststudent`
--

DROP TABLE IF EXISTS `liststudent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `liststudent` (
  `studentid` varchar(10) NOT NULL,
  `fullname` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phonenum` varchar(14) DEFAULT NULL,
  `sex` varchar(6) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `imgpath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liststudent`
--

LOCK TABLES `liststudent` WRITE;
/*!40000 ALTER TABLE `liststudent` DISABLE KEYS */;
INSERT INTO `liststudent` VALUES ('PS14635','Lê Quý Vương','vuonglq14635@fpt.edu.vn','0934513968','Male','12 Quận 2','C:\\Users\\Jason\\Desktop\\SOF203\\images\\PS14635.png'),('PS14692','Trần Nguyên Hải','haitnps14692@fpt.edu.vn','0934513969','Male','12 Bình Thạnh','C:\\Users\\Jason\\Desktop\\SOF203\\images\\PS14692.png'),('PS14707','Nguyễn Quang Huy','huynqps14707@fpt.edu.vn','0912342132','Male','Quận Gò Vấp','C:\\Users\\Jason\\Desktop\\SOF203\\images\\PS14707.png'),('PS14843','Hồ Trung Tính','tinhht14843@fpt.edu.vn','0934513968','Male','12 Quận 12','C:\\Users\\Jason\\Desktop\\SOF203\\images\\PS14843.png'),('PS14932','Doãn Hoài Nam','namdh14932@fpt.edu.vn','0934513968','Male','12 Quận 8','C:\\Users\\Jason\\Desktop\\SOF203\\images\\PS14932.png');
/*!40000 ALTER TABLE `liststudent` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-22  8:48:53
