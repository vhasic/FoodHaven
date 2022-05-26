-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ingredientservicedb
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Current Database: `ingredientservicedb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ingredientservicedb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
GRANT ALL ON ingredientServiceDB.* TO 'springuser'@'%';

USE `ingredientservicedb`;

--
-- Table structure for table `ingredient`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient` (
  `id` char(36) NOT NULL,
  `calorie_count` int DEFAULT NULL,
  `carbohidrates` int DEFAULT NULL,
  `fat` int DEFAULT NULL,
  `measuring_unit` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `proteins` int DEFAULT NULL,
  `vitamins` int DEFAULT NULL,
  `ingredient_picture_id` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4x4ygxyhqi7gwh3pd5kdafouf` (`ingredient_picture_id`),
  CONSTRAINT `FK4x4ygxyhqi7gwh3pd5kdafouf` FOREIGN KEY (`ingredient_picture_id`) REFERENCES `picture` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--


--
-- Table structure for table `ingredient_recipe`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient_recipe` (
  `id` char(36) NOT NULL,
  `quantity` int DEFAULT NULL,
  `recipeid` char(36) DEFAULT NULL,
  `ingredient_recipeid_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm9gqek56u99yj7dra5w6uipln` (`ingredient_recipeid_id`),
  CONSTRAINT `FKm9gqek56u99yj7dra5w6uipln` FOREIGN KEY (`ingredient_recipeid_id`) REFERENCES `ingredient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient_recipe`
--


--
-- Table structure for table `picture`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picture` (
  `id` char(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pic_byte` mediumblob,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--


--
-- Current Database: `recipeservicedb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `recipeservicedb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
GRANT ALL ON recipeServiceDB.* TO 'springuser'@'%';

USE `recipeservicedb`;

--
-- Table structure for table `category`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` char(36) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `name` varchar(50) NOT NULL,
  `category_picture_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6lxc43bviodc19wbbanus223d` (`category_picture_id`),
  CONSTRAINT `FK6lxc43bviodc19wbbanus223d` FOREIGN KEY (`category_picture_id`) REFERENCES `picture` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `date_created`, `last_updated`, `name`, `category_picture_id`) VALUES ('378ca46b-5525-41d6-94da-9f271b1694b1','2022-05-18 14:03:18.894455','2022-05-18 14:03:18.894455','Mexican','8a8341b5-9509-4721-a1e2-ed32cf148a08'),('3db1686d-f07e-458f-8f50-bd125b676b98','2022-05-18 14:03:18.841953','2022-05-18 14:03:18.841953','French','d24588bf-f78b-4026-93bb-31a378bcc566'),('4b4d3cc7-6886-45e9-aaab-ed87b2dc7624','2022-05-18 14:03:18.873195','2022-05-18 14:03:18.873195','Italian','a81574d0-c8af-402e-805f-2e59507dcabf');

--
-- Table structure for table `picture`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picture` (
  `id` char(36) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pic_byte` mediumblob,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

INSERT INTO `picture` (`id`, `date_created`, `last_updated`, `name`, `pic_byte`, `type`) VALUES ('8a8341b5-9509-4721-a1e2-ed32cf148a08','2022-05-18 14:03:18.826331','2022-05-18 14:03:18.826331','image.jpg',_binary 'x�\�\�s\�\�\�b``\�\�\�p	Ҍ \��$\�E�t�8\�A\�N\�\�ɼ�rq�ONHH��\��\�\�\�P��\�\r�`\�t\�sY\�\�\0U<_','image/jpeg'),('a81574d0-c8af-402e-805f-2e59507dcabf','2022-05-18 14:03:18.810709','2022-05-18 14:03:18.810709','image.jpg',_binary 'x�\�\�s\�\�\�b``\�\�\�p	Ҍ \��$\�E�t�8\�A\�N\�\�ɼ�rq�ONHH��\��\�\�\�P��\�\r�`\�t\�sY\�\�\0U<_','image/jpeg'),('d24588bf-f78b-4026-93bb-31a378bcc566','2022-05-18 14:03:18.773727','2022-05-18 14:03:18.773727','image.jpg',_binary 'x�\�\�s\�\�\�b``\�\�\�p	Ҍ \��$\�E�t�8\�A\�N\�\�ɼ�rq�ONHH��\��\�\�\�P��\�\r�`\�t\�sY\�\�\0U<_','image/jpeg');

--
-- Table structure for table `recipe`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `id` char(36) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_updated` datetime(6) NOT NULL,
  `name` varchar(50) NOT NULL,
  `preparation_time` int NOT NULL,
  `userid` char(36) NOT NULL,
  `recipe_category_id` char(36) NOT NULL,
  `recipe_picture_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8w5y7i7u924xuhxbeumltsv84` (`recipe_category_id`),
  KEY `FKqe2kb8qs58vg0hiqfa4i2sx3o` (`recipe_picture_id`),
  CONSTRAINT `FK8w5y7i7u924xuhxbeumltsv84` FOREIGN KEY (`recipe_category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKqe2kb8qs58vg0hiqfa4i2sx3o` FOREIGN KEY (`recipe_picture_id`) REFERENCES `picture` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

INSERT INTO `recipe` (`id`, `date_created`, `deleted`, `description`, `last_updated`, `name`, `preparation_time`, `userid`, `recipe_category_id`, `recipe_picture_id`) VALUES ('1ac52574-e01a-4675-b379-b276f9a0a0cf','2022-05-18 14:03:19.019427',_binary '\0','Tortillas are an all-time family favourite down Mexico way, and they\'re fast becoming just as popular around Kiwi tables. ','2022-05-18 14:03:19.019427','Mexican Tortilla',40,'d655a515-aa56-40ca-a1cc-78a896e03c5e','378ca46b-5525-41d6-94da-9f271b1694b1','8a8341b5-9509-4721-a1e2-ed32cf148a08'),('26873b88-94da-4899-b5f6-a1e43ed5bebd','2022-05-18 14:03:18.972562',_binary '\0','Tajarin is the Piemontese version of tagliatelle.','2022-05-18 14:03:18.972562','Tajarin al Tartufo',20,'1124dc7e-1a3a-4a2b-9c8b-c7a3d3ed9476','4b4d3cc7-6886-45e9-aaab-ed87b2dc7624','a81574d0-c8af-402e-805f-2e59507dcabf'),('9c4b58c0-9096-401a-a981-dc72de9fac6e','2022-05-18 14:03:18.941328',_binary '\0','This Creme Brulee recipe is delicious, creamy, and the most perfect French dessert...','2022-05-18 14:03:18.941328','Creme brulee',30,'1124dc7e-1a3a-4a2b-9c8b-c7a3d3ed9476','3db1686d-f07e-458f-8f50-bd125b676b98','d24588bf-f78b-4026-93bb-31a378bcc566');

--
-- Table structure for table `step`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `step` (
  `id` char(36) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `description` varchar(255) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `o_number` int NOT NULL,
  `step_picture_id` char(36) DEFAULT NULL,
  `step_recipe_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp0y6k3ir54dm00x72e2cg3e4v` (`step_picture_id`),
  KEY `FKe6orvvb80cyvvjiam91ylppw0` (`step_recipe_id`),
  CONSTRAINT `FKe6orvvb80cyvvjiam91ylppw0` FOREIGN KEY (`step_recipe_id`) REFERENCES `recipe` (`id`),
  CONSTRAINT `FKp0y6k3ir54dm00x72e2cg3e4v` FOREIGN KEY (`step_picture_id`) REFERENCES `picture` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `step`
--

INSERT INTO `step` (`id`, `date_created`, `description`, `last_updated`, `o_number`, `step_picture_id`, `step_recipe_id`) VALUES ('83c8e5b0-0e63-4393-8a5b-e124420fa791','2022-05-18 14:03:19.050669','In saucepan, combine milk and whipping cream','2022-05-18 14:03:19.050669',1,'d24588bf-f78b-4026-93bb-31a378bcc566','9c4b58c0-9096-401a-a981-dc72de9fac6e'),('c3d63161-c41d-4219-b41a-a25b8c9c6653','2022-05-18 14:03:19.087611','Heat a non-stick frying pan or wok with a dash of oil, stir fry schnitzel strips until browned, this is best done in two batches, set aside.','2022-05-18 14:03:19.087611',1,'a81574d0-c8af-402e-805f-2e59507dcabf','26873b88-94da-4899-b5f6-a1e43ed5bebd'),('cc612601-0707-43f4-a535-4f6b57b3d1ec','2022-05-18 14:03:19.103233','Using a rolling pin, roll out the pieces of dough into thin, flat rectangles. Leave to rest for a few minutes.','2022-05-18 14:03:19.103233',1,'8a8341b5-9509-4721-a1e2-ed32cf148a08','1ac52574-e01a-4675-b379-b276f9a0a0cf');

--
-- Current Database: `reviewservicedb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `reviewservicedb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
GRANT ALL ON reviewServiceDB.* TO 'springuser'@'%';

USE `reviewservicedb`;

--
-- Table structure for table `category`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` char(36) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `name` varchar(50) NOT NULL,
  `category_picture_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6lxc43bviodc19wbbanus223d` (`category_picture_id`),
  CONSTRAINT `FK6lxc43bviodc19wbbanus223d` FOREIGN KEY (`category_picture_id`) REFERENCES `picture` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--


--
-- Table structure for table `picture`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picture` (
  `id` char(36) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pic_byte` mediumblob,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--


--
-- Table structure for table `rating`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `id` char(36) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `rating` int NOT NULL,
  `recipe_id` char(36) NOT NULL,
  `user_id` char(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`id`, `comment`, `date_created`, `last_updated`, `rating`, `recipe_id`, `user_id`) VALUES ('134856a9-e5da-48c1-bc3a-67c686e5e193','Ovo je testni komentar 3 kojitreba biti mnogooogo mnogo mnogo duzi kako bih provjerila da li sve radi kako treba i da li ce preci u novired ako vidi da je ovo kriza dugo','2022-05-18 14:08:21.405113','2022-05-18 14:08:21.405113',3,'1ac52574-e01a-4675-b379-b276f9a0a0cf','1124dc7e-1a3a-4a2b-9c8b-c7a3d3ed9476'),('2d41f73e-5b49-4b91-a54c-b3fede415cad','Ovo je testni komentar 2','2022-05-18 14:08:21.389482','2022-05-18 14:08:21.389482',4,'26873b88-94da-4899-b5f6-a1e43ed5bebd','1124dc7e-1a3a-4a2b-9c8b-c7a3d3ed9476'),('a0978c5c-74ce-45fb-abae-6303a44263b9','Ovo je testni komentar','2022-05-18 14:08:21.289241','2022-05-18 14:08:21.289241',5,'1ac52574-e01a-4675-b379-b276f9a0a0cf','d655a515-aa56-40ca-a1cc-78a896e03c5e');

--
-- Table structure for table `recipe`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `id` char(36) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_updated` datetime(6) NOT NULL,
  `name` varchar(50) NOT NULL,
  `preparation_time` int NOT NULL,
  `userid` char(36) NOT NULL,
  `recipe_category_id` char(36) NOT NULL,
  `recipe_picture_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8w5y7i7u924xuhxbeumltsv84` (`recipe_category_id`),
  KEY `FKqe2kb8qs58vg0hiqfa4i2sx3o` (`recipe_picture_id`),
  CONSTRAINT `FK8w5y7i7u924xuhxbeumltsv84` FOREIGN KEY (`recipe_category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKqe2kb8qs58vg0hiqfa4i2sx3o` FOREIGN KEY (`recipe_picture_id`) REFERENCES `picture` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--


--
-- Table structure for table `step`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `step` (
  `id` char(36) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `description` varchar(255) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `o_number` int NOT NULL,
  `step_picture_id` char(36) NOT NULL,
  `step_recipe_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp0y6k3ir54dm00x72e2cg3e4v` (`step_picture_id`),
  KEY `FKe6orvvb80cyvvjiam91ylppw0` (`step_recipe_id`),
  CONSTRAINT `FKe6orvvb80cyvvjiam91ylppw0` FOREIGN KEY (`step_recipe_id`) REFERENCES `recipe` (`id`),
  CONSTRAINT `FKp0y6k3ir54dm00x72e2cg3e4v` FOREIGN KEY (`step_picture_id`) REFERENCES `picture` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `step`
--


--
-- Current Database: `userservicedb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `userservicedb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
GRANT ALL ON userServiceDB.* TO 'springuser'@'%';

USE `userservicedb`;

--
-- Table structure for table `role`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` char(36) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `date_created`, `last_updated`, `name`) VALUES ('39f815d9-9f82-4015-858c-a5bf37d7a3ae','2022-05-18 13:52:03.169084','2022-05-18 13:52:03.169084','User'),('4cc8d514-53b7-4c2b-9c20-c394788d5447','2022-05-18 13:52:03.084270','2022-05-18 13:52:03.084270','Administrator');

--
-- Table structure for table `user`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` char(36) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `role_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKk0j847d0uhn0nxb3a1grvqdao` (`role_id`),
  CONSTRAINT `FKk0j847d0uhn0nxb3a1grvqdao` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `date_created`, `email`, `first_name`, `last_name`, `last_updated`, `password`, `username`, `role_id`) VALUES ('1124dc7e-1a3a-4a2b-9c8b-c7a3d3ed9476','2022-05-18 13:52:03.215990','admin@nesto.com','Administrator','Administrator','2022-05-18 13:52:03.215990','Password1!','admin','4cc8d514-53b7-4c2b-9c20-c394788d5447'),('92e04ccb-fdbb-4c1d-bce8-4c838243b313','2022-05-26 09:51:53.973385','test@test.com','test','test','2022-05-26 09:51:53.973385','Password1!','test','39f815d9-9f82-4015-858c-a5bf37d7a3ae'),('d655a515-aa56-40ca-a1cc-78a896e03c5e','2022-05-18 13:52:03.262818','user@nesto.com','User','User','2022-05-18 13:52:03.262818','Password1!','user','39f815d9-9f82-4015-858c-a5bf37d7a3ae'),('ff0a8037-5fc0-498a-ae80-028af9c292e3','2022-05-26 09:56:59.246672','test2@test2.com','test2','test2','2022-05-26 09:56:59.246672','Password1!','test2','39f815d9-9f82-4015-858c-a5bf37d7a3ae');

--
-- Current Database: `systemeventsservicedb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `systemeventsservicedb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
GRANT ALL ON systemEventsServiceDB.* TO 'springuser'@'%';

USE `systemeventsservicedb`;

--
-- Table structure for table `action`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `action` (
  `id` char(36) NOT NULL,
  `action_type` varchar(255) NOT NULL,
  `resource_name` varchar(255) NOT NULL,
  `response_type` varchar(255) NOT NULL,
  `service` varchar(255) NOT NULL,
  `timestamp` datetime(6) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action`
--

INSERT INTO `action` (`id`, `action_type`, `resource_name`, `response_type`, `service`, `timestamp`, `username`) VALUES ('25fb52fd-40bc-4d67-9c7c-37ea73b40f57','GET','/api/ratings','OK','rating-service','2022-04-14 06:49:54.953865','test'),('351e42f6-730e-4e29-85ea-edc06cb18311','GET','/api/ratings','OK','rating-service','2022-04-11 15:18:38.902060','test');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-26 17:21:09
