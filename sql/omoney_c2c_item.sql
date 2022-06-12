-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: omoney_c2c
-- ------------------------------------------------------
-- Server version 8.0.29

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,_binary '','Chaussures sparco original état neuve. Pointure 42 noires',NULL,'Chaussures sparco. Chaussures',35,'NEW',1),(2,_binary '','Chaussures en cuir noir véritable en très bon état. Très peut porter',NULL,'Chaussures cuir. Chaussures',30,'GOOD_QUALITY',1),(3,_binary '','Chaussure bébé 23',NULL,'Chaussure bebe. Chaussure 23. Chaussure enfant',12,'GOOD_QUALITY',1),(4,_binary '','Chaussure bébé 23',NULL,'Chaussure bebe. Chaussure 23. Chaussure enfant',12,'GOOD_QUALITY',1),(5,_binary '','Chaussures à lacets. chaussures abride',NULL,'Chaussure',5,'DAMAGED',1),(6,_binary '','Vendschaussures marche cause santé',NULL,'Chaussures de marche',35,'GOOD_QUALITY',1),(7,_binary '','Je vend mes chaussures de randonnée MEINDL LITEPEAK GTX BLEU, Goretex Variofix. Je me suis trompé dans la pointure. J\'ai fait une sortie de 25 kms seulement. Achat en février 2022 à 259,90 € sur Snowleader spécialiste de la randonnée.',NULL,'Chaussures de randonnée montagne',180,'GOOD_QUALITY',1),(8,_binary '','Sandalettes très peu portées car talon un peu haut pour moi.',NULL,'Sandalettes',17,'NEW',1),(9,_binary '','Bottés Valverde del Camino pointure 39 cuir noir et daim taupe. Portées moins de 5 fois, achetées 270 €',NULL,'Bottes andalouses',125,'GOOD_QUALITY',1),(10,_binary '','Baskets roses KAPPA pointure 36\r\nNeuves avec étiquette\r\nstyle baskets basses \'Bensimon\'\r\nprix 10 €',NULL,'Baskets KAPPA en toile T.36',10,'NEW',1),(11,_binary '','Baskets puma',NULL,'taille 39 bon état',40,'GOOD_QUALITY',2),(12,_binary '','Baskets marque puma',NULL,'Chaussures de taille eur 40 jamais porté',40,'NEW',2),(13,_binary '','Baskets noires puma',NULL,'Paire de chaussures sportives noire',40,'GOOD_QUALITY',2),(14,_binary '','Baskets addidas enfant',NULL,'petites baskets pour enfant jaune',30,'NEW',2),(15,_binary '','Baskets addidas',NULL,'baskets bleue de la marque addidas',50,'GOOD_QUALITY',2),(16,_binary '','Baskets addidas',NULL,'petites baskets pour enfant',60,'NEW',2),(17,_binary '','Chaussures de running',NULL,'marque nike bon état marron',40,'GOOD_QUALITY',2),(18,_binary '','baskets nike',NULL,'petites baskets marque nike pour enfant',50,'NEW',2),(19,_binary '','Baskets marque nike',NULL,'chaussures taille 45 convient pour grand pied',45,'GOOD_QUALITY',2),(20,_binary '','chaussures de sport',NULL,'taille enfant',30,'GOOD_QUALITY',2),(21,_binary '','DEBAIJIA Homme Sneakers',NULL,'DEBAIJIA Homme Sneakers Chaussures De Course Baskets Respirantes Chaussures De Running Antidérapant Sport Confortables Décontractées vert',38,'GOOD_QUALITY',3),(22,_binary '','Chaussure de Sécurité Homme',NULL,'Ulogu Chaussure de Sécurité Homme Légère Baskets de Sécurité Femme Respirant Chaussure de Travail en Acier Toe',35,'GOOD_QUALITY',3),(23,_binary '','Tommy Hilfiger Essential Leather Sneaker',NULL,'Tommy Hilfiger Essential Leather Sneaker, Baskets Basses Homme',30,'GOOD_QUALITY',3),(24,_binary '','Geox U Symbol B',NULL,'Geox U Symbol B, Sneaker Basse Homme',30,'GOOD_QUALITY',3),(25,_binary '','Sneaker Garçon Mixte Enfant',NULL,'Fila Disruptor Kids, Sneaker Garçon Mixte Enfant rouge',30,'GOOD_QUALITY',3),(26,_binary '','adidas Superstar Vegan',NULL,'adidas Superstar Vegan, Sneaker Homme',30,'GOOD_QUALITY',3),(27,_binary '','adidas Originals HANDBALL SPEZIAL 033620',NULL,'Baskets mode mixte adulte',37,'GOOD_QUALITY',3),(28,_binary '','Geox D Sozy S A',NULL,'Sandal Femme',41,'GOOD_QUALITY',3),(29,_binary '','Nike Team Hustle D 10',NULL,'Nike Team Hustle D 10, Baskets Mixte Enfant',30,'GOOD_QUALITY',3),(30,_binary '','PUMA Smash WNS V2 L',NULL,'Sneaker Basse Femme rose',25,'GOOD_QUALITY',3),(31,_binary '','Vans Ward Canvas',NULL,'Basket Femme',27,'GOOD_QUALITY',3),(32,_binary '','Nike Defy All Day',NULL,'Baskets Homme',30,'GOOD_QUALITY',3),(33,_binary '','Baskets basses',NULL,'Reebok Classic LEGACY GROW UNISEX',55,'NEW',3),(34,_binary '','Baskets basses',NULL,'Nike Sportswear AIR HUARACHE UNISEX',95,'NEW',3),(35,_binary '','Baskets basses',NULL,'Marque puma, couleur blanche',51,'GOOD_QUALITY',3),(36,_binary '','1461 UNISEX - Chaussures à lacets',NULL,'marque DR. Martens couleur noir',&20,'GOOD_QUALITY',3),(37,_binary '','Baskets montantes',NULL,'Marque Converse CHUCK TAYLOR ALL STAR HI',67,'GOOD_QUALITY',3),(38,_binary '','Baskets montantes',NULL,'AIR 1 MID SE marque Jordan',130,'NEW',3),(39,_binary '','Mules',NULL,'adidas Originals ADILETTE LITE',27,'GOOD_QUALITY',3),(40,_binary '','Mocassins',NULL,'Vans UA CLASSIC SLIP-ON',39,'GOOD_QUALITY',3);





/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-12 16:15:07
