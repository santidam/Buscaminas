CREATE DATABASE  IF NOT EXISTS `crm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `crm`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: crm
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `accion`
--

DROP TABLE IF EXISTS `accion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accion` (
  `accion_id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(20) NOT NULL,
  `fecha` date NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `comercial` varchar(20) NOT NULL,
  `empresa` varchar(12) NOT NULL,
  PRIMARY KEY (`accion_id`),
  KEY `fk_comercial_idx` (`comercial`),
  KEY `fk_empresa_idx` (`empresa`),
  CONSTRAINT `fk_comercial` FOREIGN KEY (`comercial`) REFERENCES `comercial` (`dni`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_empresa` FOREIGN KEY (`empresa`) REFERENCES `empresa` (`phone_number`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accion`
--

LOCK TABLES `accion` WRITE;
/*!40000 ALTER TABLE `accion` DISABLE KEYS */;
/*!40000 ALTER TABLE `accion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accion_email`
--

DROP TABLE IF EXISTS `accion_email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accion_email` (
  `accionId` int NOT NULL,
  `email` varchar(100) NOT NULL,
  `es_promocion` tinyint NOT NULL,
  PRIMARY KEY (`accionId`),
  CONSTRAINT `fk_IdAccion` FOREIGN KEY (`accionId`) REFERENCES `accion` (`accion_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accion_email`
--

LOCK TABLES `accion_email` WRITE;
/*!40000 ALTER TABLE `accion_email` DISABLE KEYS */;
/*!40000 ALTER TABLE `accion_email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accion_telefono`
--

DROP TABLE IF EXISTS `accion_telefono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accion_telefono` (
  `accionId` int NOT NULL,
  `numero_telefono` varchar(12) NOT NULL,
  `acuerdos` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`accionId`),
  CONSTRAINT `fk_accion` FOREIGN KEY (`accionId`) REFERENCES `accion` (`accion_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accion_telefono`
--

LOCK TABLES `accion_telefono` WRITE;
/*!40000 ALTER TABLE `accion_telefono` DISABLE KEYS */;
/*!40000 ALTER TABLE `accion_telefono` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accion_visita`
--

DROP TABLE IF EXISTS `accion_visita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accion_visita` (
  `accionId` int NOT NULL,
  `direccion` varchar(60) NOT NULL,
  `acuerdos` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`accionId`),
  CONSTRAINT `fk_accion_id` FOREIGN KEY (`accionId`) REFERENCES `accion` (`accion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accion_visita`
--

LOCK TABLES `accion_visita` WRITE;
/*!40000 ALTER TABLE `accion_visita` DISABLE KEYS */;
/*!40000 ALTER TABLE `accion_visita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comercial`
--

DROP TABLE IF EXISTS `comercial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comercial` (
  `dni` varchar(20) NOT NULL,
  `codigo` varchar(20) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `porcentaje_comision` int NOT NULL,
  `fecha_incorporacion` date NOT NULL,
  `contrasenya` varchar(256) NOT NULL,
  PRIMARY KEY (`dni`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercial`
--

LOCK TABLES `comercial` WRITE;
/*!40000 ALTER TABLE `comercial` DISABLE KEYS */;
/*!40000 ALTER TABLE `comercial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `phone_number` varchar(12) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `representante` varchar(45) NOT NULL,
  `direccion` varchar(65) NOT NULL,
  `CP` int NOT NULL,
  `ciudad` varchar(45) NOT NULL,
  `comunidad_autonoma` varchar(45) NOT NULL,
  `pagina_web` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`phone_number`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-10 17:46:35
