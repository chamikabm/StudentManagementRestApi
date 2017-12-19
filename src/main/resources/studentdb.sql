--
-- Drop data base if exist and creat a new database.
--

DROP DATABASE IF EXISTS STUDENT_DATA_DB;
CREATE DATABASE STUDENT_DATA_DB;
USE STUDENT_DATA_DB;

--
-- Table structure for table `JOB`
--

DROP TABLE IF EXISTS `STUDENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STUDENT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `AGE` int(11) NOT NULL,
  `DEPARTMENT` enum('Electronic','Civil','Mechanical', 'Chemical', 'ComputerScience') NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `STUDENT` WRITE;
/*!40000 ALTER TABLE `STUDENT` DISABLE KEYS */;
INSERT INTO `STUDENT` VALUES (1,'Chamika', 28, 'ComputerScience');
INSERT INTO `STUDENT` VALUES (2,'Kasun', 25, 'Mechanical');
INSERT INTO `STUDENT` VALUES (3,'Bandara', 21, 'Chemical');
/*!40000 ALTER TABLE `STUDENT` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `DEPARTMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DEPARTMENT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `DEPARTMENTHEAD` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `DEPARTMENT` WRITE;
/*!40000 ALTER TABLE `DEPARTMENT` DISABLE KEYS */;
INSERT INTO `DEPARTMENT` VALUES (1,'ComputerScience', 'Dr.Gamage');
INSERT INTO `DEPARTMENT` VALUES (2,'Mechanical', 'Dr.Fernando');
INSERT INTO `DEPARTMENT` VALUES (3,'Chemical', 'Dr.Indika');
/*!40000 ALTER TABLE `DEPARTMENT` ENABLE KEYS */;
UNLOCK TABLES;