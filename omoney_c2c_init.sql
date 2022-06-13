-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: omoney_c2c
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `latitude` double DEFAULT NULL,
  `line1` varchar(255) NOT NULL,
  `longitude` double DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Paris','France',48.8218371,'84 Boulevard Masséna',2.3676038,'75013'),(2,'Orléans','France',47.9031098,'2 Rue Fernand Rabier',1.9099589,'45000'),(3,'Lyon','France',45.7259242,'8 Rue Philippe Fabia',4.8714796,'68009');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `images` longblob,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quality` varchar(255) DEFAULT NULL,
  `owner_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbsy28atipvkpduqjge6ab95o3` (`owner_id`),
  CONSTRAINT `FKbsy28atipvkpduqjge6ab95o3` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,_binary '','Chaussures sparco original état neuve. Pointure 42 noires',NULL,'Chaussures sparco. Chaussures',35,'NEW',1),(2,_binary '','Chaussures en cuir noir véritable en très bon état. Très peut porter',NULL,'Chaussures cuir. Chaussures',30,'GOOD_QUALITY',1),(3,_binary '','Chaussure bébé 23',NULL,'Chaussure bebe. Chaussure 23. Chaussure enfant',12,'GOOD_QUALITY',1),(4,_binary '','Chaussure bébé 23',NULL,'Chaussure bebe. Chaussure 23. Chaussure enfant',12,'GOOD_QUALITY',1),(5,_binary '','Chaussures à lacets. chaussures abride',NULL,'Chaussure',5,'DAMAGED',1),(6,_binary '','Vendschaussures marche cause santé',NULL,'Chaussures de marche',35,'GOOD_QUALITY',1),(7,_binary '','Je vend mes chaussures de randonnée MEINDL LITEPEAK GTX BLEU, Goretex Variofix. Je me suis trompé dans la pointure. J\'ai fait une sortie de 25 kms seulement. Achat en février 2022 à 259,90 € sur Snowleader spécialiste de la randonnée.',NULL,'Chaussures de randonnée montagne',180,'GOOD_QUALITY',1),(8,_binary '','Sandalettes très peu portées car talon un peu haut pour moi.',NULL,'Sandalettes',17,'NEW',1),(9,_binary '','Bottés Valverde del Camino pointure 39 cuir noir et daim taupe. Portées moins de 5 fois, achetées 270 €',NULL,'Bottes andalouses',125,'GOOD_QUALITY',1),(10,_binary '','Baskets roses KAPPA pointure 36\r\nNeuves avec étiquette\r\nstyle baskets basses \'Bensimon\'\r\nprix 10 €',NULL,'Baskets KAPPA en toile T.36',10,'NEW',1),(11,_binary '','Baskets puma',NULL,'taille 39 bon état',40,'GOOD_QUALITY',2),(12,_binary '','Baskets marque puma',NULL,'Chaussures de taille eur 40 jamais porté',40,'NEW',2),(13,_binary '','Baskets noires puma',NULL,'Paire de chaussures sportives noire',40,'GOOD_QUALITY',2),(14,_binary '','Baskets addidas enfant',NULL,'petites baskets pour enfant jaune',30,'NEW',2),(15,_binary '','Baskets addidas',NULL,'baskets bleue de la marque addidas',50,'GOOD_QUALITY',2),(16,_binary '','Baskets addidas',NULL,'petites baskets pour enfant',60,'NEW',2),(17,_binary '','Chaussures de running',NULL,'marque nike bon état marron',40,'GOOD_QUALITY',2),(18,_binary '','baskets nike',NULL,'petites baskets marque nike pour enfant',50,'NEW',2),(19,_binary '','Baskets marque nike',NULL,'chaussures taille 45 convient pour grand pied',45,'GOOD_QUALITY',2),(20,_binary '','chaussures de sport',NULL,'taille enfant',30,'GOOD_QUALITY',2),(21,_binary '','DEBAIJIA Homme Sneakers',NULL,'DEBAIJIA Homme Sneakers Chaussures De Course Baskets Respirantes Chaussures De Running Antidérapant Sport Confortables Décontractées vert',38,'GOOD_QUALITY',3),(22,_binary '','Chaussure de Sécurité Homme',NULL,'Ulogu Chaussure de Sécurité Homme Légère Baskets de Sécurité Femme Respirant Chaussure de Travail en Acier Toe',35,'GOOD_QUALITY',3),(23,_binary '','Tommy Hilfiger Essential Leather Sneaker',NULL,'Tommy Hilfiger Essential Leather Sneaker, Baskets Basses Homme',30,'GOOD_QUALITY',3),(24,_binary '','Geox U Symbol B',NULL,'Geox U Symbol B, Sneaker Basse Homme',30,'GOOD_QUALITY',3),(25,_binary '','Sneaker Garçon Mixte Enfant',NULL,'Fila Disruptor Kids, Sneaker Garçon Mixte Enfant rouge',30,'GOOD_QUALITY',3),(26,_binary '','adidas Superstar Vegan',NULL,'adidas Superstar Vegan, Sneaker Homme',30,'GOOD_QUALITY',3),(27,_binary '','adidas Originals HANDBALL SPEZIAL 033620',NULL,'Baskets mode mixte adulte',37,'GOOD_QUALITY',3),(28,_binary '','Geox D Sozy S A',NULL,'Sandal Femme',41,'GOOD_QUALITY',3),(29,_binary '','Nike Team Hustle D 10',NULL,'Nike Team Hustle D 10, Baskets Mixte Enfant',30,'GOOD_QUALITY',3),(30,_binary '','PUMA Smash WNS V2 L',NULL,'Sneaker Basse Femme rose',25,'GOOD_QUALITY',3),(31,_binary '','Vans Ward Canvas',NULL,'Basket Femme',27,'GOOD_QUALITY',3),(32,_binary '','Nike Defy All Day',NULL,'Baskets Homme',30,'GOOD_QUALITY',3),(33,_binary '','Baskets basses',NULL,'Reebok Classic LEGACY GROW UNISEX',55,'NEW',3),(34,_binary '','Baskets basses',NULL,'Nike Sportswear AIR HUARACHE UNISEX',95,'NEW',3),(35,_binary '','Baskets basses',NULL,'Marque puma, couleur blanche',51,'GOOD_QUALITY',3),(36,_binary '','1461 UNISEX - Chaussures à lacets',NULL,'marque DR. Martens couleur noir',20,'GOOD_QUALITY',3),(37,_binary '','Baskets montantes',NULL,'Marque Converse CHUCK TAYLOR ALL STAR HI',67,'GOOD_QUALITY',3),(38,_binary '','Baskets montantes',NULL,'AIR 1 MID SE marque Jordan',130,'NEW',3),(39,_binary '','Mules',NULL,'adidas Originals ADILETTE LITE',27,'GOOD_QUALITY',3),(40,_binary '','Mocassins',NULL,'Vans UA CLASSIC SLIP-ON',39,'GOOD_QUALITY',3);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `sender_id` bigint NOT NULL,
  `transaction_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcnj2qaf5yc36v2f90jw2ipl9b` (`sender_id`),
  KEY `FKp8ofja80q6n2tglirsw9f3aek` (`transaction_id`),
  CONSTRAINT `FKcnj2qaf5yc36v2f90jw2ipl9b` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKp8ofja80q6n2tglirsw9f3aek` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'Bonjour, êtes-vous intéressé par mon offre ?','2022-06-13 23:42:33.796000',1,1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rating` double DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL,
  `author_id` bigint NOT NULL,
  `subject_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlmwuh7gvb6ch5ryq1bwgadbkn` (`author_id`),
  KEY `FKl2c153bl233k64x10kgdybdbn` (`subject_id`),
  CONSTRAINT `FKl2c153bl233k64x10kgdybdbn` FOREIGN KEY (`subject_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKlmwuh7gvb6ch5ryq1bwgadbkn` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'2022-06-13 23:42:10.100000'),(2,'2022-06-13 23:53:06.444000'),(3,'2022-06-13 23:55:24.294000'),(4,'2022-06-13 23:56:50.216000');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_approval`
--

DROP TABLE IF EXISTS `transaction_approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_approval` (
  `status` varchar(255) DEFAULT NULL,
  `transaction_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`transaction_id`,`user_id`),
  KEY `FKb90lfp9kko5m2u09ibx99u7ys` (`user_id`),
  CONSTRAINT `FKb90lfp9kko5m2u09ibx99u7ys` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKlldisa85g2vq65q36utxnxwq6` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_approval`
--

LOCK TABLES `transaction_approval` WRITE;
/*!40000 ALTER TABLE `transaction_approval` DISABLE KEYS */;
INSERT INTO `transaction_approval` VALUES ('ACCEPTED',1,1),('ACCEPTED',1,2),('PENDING',2,1),('PENDING',2,2),('ACCEPTED',2,3),('REJECTED',3,1),('PENDING',3,3),('ACCEPTED',4,2),('ACCEPTED',4,3);
/*!40000 ALTER TABLE `transaction_approval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_item`
--

DROP TABLE IF EXISTS `transaction_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_item` (
  `transaction_id` bigint NOT NULL,
  `item_id` bigint NOT NULL,
  PRIMARY KEY (`transaction_id`,`item_id`),
  KEY `FKkd2i12itoy3ei8d8s1qwebdg8` (`item_id`),
  CONSTRAINT `FK1wc2dvhj3oos47in473fqi3q8` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`),
  CONSTRAINT `FKkd2i12itoy3ei8d8s1qwebdg8` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_item`
--

LOCK TABLES `transaction_item` WRITE;
/*!40000 ALTER TABLE `transaction_item` DISABLE KEYS */;
INSERT INTO `transaction_item` VALUES (1,1),(2,2),(2,3),(3,6),(2,11),(1,13),(4,13),(2,24),(3,25),(4,26);
/*!40000 ALTER TABLE `transaction_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telephone_number` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `address_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKddefmvbrws3hvl5t0hnnsv8ox` (`address_id`),
  CONSTRAINT `FKddefmvbrws3hvl5t0hnnsv8ox` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user1@gmail.com','123azerty','0123456789','user1',1),(2,'user2@gmail.com','123azerty','0123456789','user2',2),(3,'user3@gmail.com','123azerty','0123456789','user3',3);
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

-- Dump completed on 2022-06-14  0:04:54
