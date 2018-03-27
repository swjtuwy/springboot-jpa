-- MySQL dump 10.13  Distrib 5.5.59, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: paper
-- ------------------------------------------------------
-- Server version	5.5.59-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `parent_id` BIGINT NOT NULL,
  `category` varchar(256) NOT NULL,
  `catrgory_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code`
--

DROP TABLE IF EXISTS `code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `paper_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `author` varchar(256) DEFAULT NULL,
  `path` varchar(45) NOT NULL,
  `comments` text NOT NULL,
  `flag` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_code_1_idx` (`paper_id`),
  KEY `fk_code_2_idx` (`user_id`),
  CONSTRAINT `fk_code_1` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_code_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code`
--

LOCK TABLES `code` WRITE;
/*!40000 ALTER TABLE `code` DISABLE KEYS */;
/*!40000 ALTER TABLE `code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` BIGINT NOT NULL,
  `paper_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `comments` text NOT NULL,
  `time` date NOT NULL,
  `flag` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comments_1_idx` (`paper_id`),
  KEY `fk_comments_2_idx` (`user_id`),
  CONSTRAINT `fk_comments_1` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `id` BIGINT NOT NULL,
  `paper_id` BIGINT DEFAULT NULL,
  `user_id` BIGINT NOT NULL,
  `operation` varchar(256) DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_history_1_idx` (`paper_id`),
  KEY `fk_history_2_idx` (`user_id`),
  CONSTRAINT `fk_history_1` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_history_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL,
  `summary` text NOT NULL,
  `path` varchar(256) NOT NULL,
  `filename` varchar(256) NOT NULL,
  `tag` varchar(256) NOT NULL,
  `author` varchar(45) NOT NULL,
  `download_time` int(11) NOT NULL DEFAULT '0',
  `category_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `year` date NOT NULL,
  `create_time` date NOT NULL,
  `delete_user_id` BIGINT NOT NULL,
  `delete_time` date NOT NULL,
  `rank` int(11) NOT NULL DEFAULT '3',
  `flag` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_paper_1_idx` (`user_id`),
  KEY `fk_paper_2_idx` (`category_id`),
  KEY `fk_paper_3_idx` (`delete_user_id`),
  CONSTRAINT `fk_paper_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_paper_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_paper_3` FOREIGN KEY (`delete_user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rank`
--

DROP TABLE IF EXISTS `rank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rank` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `paper_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `rank` int(11) NOT NULL,
  `time` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rank_1_idx` (`paper_id`),
  KEY `fk_rank_2_idx` (`user_id`),
  CONSTRAINT `fk_rank_1` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rank_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rank`
--

LOCK TABLES `rank` WRITE;
/*!40000 ALTER TABLE `rank` DISABLE KEYS */;
/*!40000 ALTER TABLE `rank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscribe`
--

DROP TABLE IF EXISTS `subscribe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscribe` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `paper_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_subscribe_1_idx` (`paper_id`),
  KEY `fk_subscribe_2_idx` (`user_id`),
  CONSTRAINT `fk_subscribe_1` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_subscribe_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscribe`
--

LOCK TABLES `subscribe` WRITE;
/*!40000 ALTER TABLE `subscribe` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscribe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `email` varchar(256) NOT NULL,
  `remarks` text NOT NULL,
  `role` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-26 12:19:27
