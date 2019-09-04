CREATE DATABASE  IF NOT EXISTS `msusportsapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `msusportsapp`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: msusportsapp
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adminmsu`
--

DROP TABLE IF EXISTS `adminmsu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `adminmsu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminmsu`
--

LOCK TABLES `adminmsu` WRITE;
/*!40000 ALTER TABLE `adminmsu` DISABLE KEYS */;
INSERT INTO `adminmsu` VALUES (1,'admin','123456');
/*!40000 ALTER TABLE `adminmsu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basketball`
--

DROP TABLE IF EXISTS `basketball`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `basketball` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `regnumber` varchar(45) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `first_team` varchar(45) DEFAULT 'false',
  `programme` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `regnumber_UNIQUE` (`regnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basketball`
--

LOCK TABLES `basketball` WRITE;
/*!40000 ALTER TABLE `basketball` DISABLE KEYS */;
/*!40000 ALTER TABLE `basketball` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basketball_updates`
--

DROP TABLE IF EXISTS `basketball_updates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `basketball_updates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `update_title` varchar(45) DEFAULT NULL,
  `update_description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basketball_updates`
--

LOCK TABLES `basketball_updates` WRITE;
/*!40000 ALTER TABLE `basketball_updates` DISABLE KEYS */;
/*!40000 ALTER TABLE `basketball_updates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coach`
--

DROP TABLE IF EXISTS `coach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coach` (
  `idcoach` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `sport` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcoach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coach`
--

LOCK TABLES `coach` WRITE;
/*!40000 ALTER TABLE `coach` DISABLE KEYS */;
INSERT INTO `coach` VALUES ('C1234S','123456','soccer');
/*!40000 ALTER TABLE `coach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_updates`
--

DROP TABLE IF EXISTS `event_updates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `event_updates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_title` varchar(45) DEFAULT NULL,
  `event_description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_updates`
--

LOCK TABLES `event_updates` WRITE;
/*!40000 ALTER TABLE `event_updates` DISABLE KEYS */;
INSERT INTO `event_updates` VALUES (1,'New Exam Dates','Due to the graduation new exam dates have been initiated and students shall now begin exams on the 20th of May as this will give ways for the graduation of the level 4.2 students');
/*!40000 ALTER TABLE `event_updates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hockey`
--

DROP TABLE IF EXISTS `hockey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hockey` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `regnumber` varchar(45) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `first_team` varchar(45) DEFAULT 'false',
  `programme` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `regnumber_UNIQUE` (`regnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hockey`
--

LOCK TABLES `hockey` WRITE;
/*!40000 ALTER TABLE `hockey` DISABLE KEYS */;
/*!40000 ALTER TABLE `hockey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hockey_updates`
--

DROP TABLE IF EXISTS `hockey_updates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hockey_updates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `update_title` varchar(45) DEFAULT NULL,
  `update_description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hockey_updates`
--

LOCK TABLES `hockey_updates` WRITE;
/*!40000 ALTER TABLE `hockey_updates` DISABLE KEYS */;
/*!40000 ALTER TABLE `hockey_updates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_log`
--

DROP TABLE IF EXISTS `login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) DEFAULT NULL,
  `login_time` varchar(200) DEFAULT NULL,
  `logout_time` varchar(200) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_log`
--

LOCK TABLES `login_log` WRITE;
/*!40000 ALTER TABLE `login_log` DISABLE KEYS */;
INSERT INTO `login_log` VALUES (1,'R156517P','Sat Apr 27 00:13:08 GMT+02:00 2019','Sat Apr 27 15:08:15 GMT+02:00 2019'),(2,'r156517p','Sat Apr 27 00:51:02 GMT+02:00 2019','Sat Apr 27 15:08:15 GMT+02:00 2019'),(3,'c1234s','Sat Apr 27 00:56:26 GMT+02:00 2019','Sat Apr 27 00:56:51 GMT+02:00 2019'),(4,'r156517p','Sat Apr 27 14:43:12 GMT+02:00 2019','Sat Apr 27 15:08:15 GMT+02:00 2019'),(5,'r156517p','Sat Apr 27 15:07:32 GMT+02:00 2019','Sat Apr 27 15:08:15 GMT+02:00 2019');
/*!40000 ALTER TABLE `login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programs`
--

DROP TABLE IF EXISTS `programs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `programs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `program_code` varchar(10) DEFAULT NULL,
  `program_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `program_code_UNIQUE` (`program_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programs`
--

LOCK TABLES `programs` WRITE;
/*!40000 ALTER TABLE `programs` DISABLE KEYS */;
/*!40000 ALTER TABLE `programs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rating` (
  `username` varchar(45) NOT NULL,
  `rate` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES ('r156517p',4),('r156517q',4);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soccer`
--

DROP TABLE IF EXISTS `soccer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `soccer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `regnumber` varchar(45) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `first_team` varchar(45) DEFAULT 'false',
  `programme` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `regnumber_UNIQUE` (`regnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soccer`
--

LOCK TABLES `soccer` WRITE;
/*!40000 ALTER TABLE `soccer` DISABLE KEYS */;
/*!40000 ALTER TABLE `soccer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soccer_updates`
--

DROP TABLE IF EXISTS `soccer_updates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `soccer_updates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `update_title` varchar(45) DEFAULT NULL,
  `update_description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soccer_updates`
--

LOCK TABLES `soccer_updates` WRITE;
/*!40000 ALTER TABLE `soccer_updates` DISABLE KEYS */;
/*!40000 ALTER TABLE `soccer_updates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `regnumber` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `sport` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`regnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('R156517P','123456','soccer'),('r156517q','123456',NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'msusportsapp'
--

--
-- Dumping routines for database 'msusportsapp'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-05  0:07:21
