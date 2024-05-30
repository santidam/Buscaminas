CREATE DATABASE  IF NOT EXISTS `crm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `crm`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: crm
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
  `accion_id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(20) NOT NULL,
  `fecha` date NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `comercial` varchar(20) NOT NULL,
  `empresa` varchar(50) NOT NULL,
  PRIMARY KEY (`accion_id`),
  KEY `fk_comercial_idx` (`comercial`),
  KEY `fk_empresa_idx` (`empresa`),
  CONSTRAINT `fk_comercial` FOREIGN KEY (`comercial`) REFERENCES `comercial` (`dni`) ON UPDATE CASCADE,
  CONSTRAINT `fk_empresa` FOREIGN KEY (`empresa`) REFERENCES `empresa` (`phone_number`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accion`
--

LOCK TABLES `accion` WRITE;
/*!40000 ALTER TABLE `accion` DISABLE KEYS */;
INSERT INTO `accion` VALUES (1,'visita','2024-05-28','prueba','admin','111222333'),(2,'email','2024-05-29','em','admin','111222333'),(3,'visita','2024-05-30','pruebaEliminar','admin','666777888'),(4,'visita','2024-05-23','prueba','admin','999999999');
/*!40000 ALTER TABLE `accion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accion_email`
--

DROP TABLE IF EXISTS `accion_email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accion_email` (
  `accionId` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `es_promocion` tinyint(1) NOT NULL,
  PRIMARY KEY (`accionId`),
  CONSTRAINT `fk_IdAccion` FOREIGN KEY (`accionId`) REFERENCES `accion` (`accion_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accion_email`
--

LOCK TABLES `accion_email` WRITE;
/*!40000 ALTER TABLE `accion_email` DISABLE KEYS */;
INSERT INTO `accion_email` VALUES (2,'vampyr@gmail.com',0);
/*!40000 ALTER TABLE `accion_email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accion_telefono`
--

DROP TABLE IF EXISTS `accion_telefono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accion_telefono` (
  `accionId` int(11) NOT NULL,
  `numero_telefono` varchar(12) NOT NULL,
  `acuerdos` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`accionId`),
  CONSTRAINT `fk_accion` FOREIGN KEY (`accionId`) REFERENCES `accion` (`accion_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
  `accionId` int(11) NOT NULL,
  `direccion` varchar(60) NOT NULL,
  `acuerdos` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`accionId`),
  CONSTRAINT `fk_accion_id` FOREIGN KEY (`accionId`) REFERENCES `accion` (`accion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accion_visita`
--

LOCK TABLES `accion_visita` WRITE;
/*!40000 ALTER TABLE `accion_visita` DISABLE KEYS */;
INSERT INTO `accion_visita` VALUES (1,'asdf','laldsf'),(3,'asf','asdf'),(4,'Calm Belt','nada');
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
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `porcentaje_comision` int(11) NOT NULL,
  `fecha_incorporacion` date NOT NULL,
  `contrasenya` varchar(256) NOT NULL,
  PRIMARY KEY (`dni`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercial`
--

LOCK TABLES `comercial` WRITE;
/*!40000 ALTER TABLE `comercial` DISABLE KEYS */;
INSERT INTO `comercial` VALUES ('11111111p',4,'Valentina','Bruno',5,'2023-02-12','4321'),('12345678z',8,'Jordi','Hornos',7,'2024-05-07','1234'),('14235678u',3,'David','Dan',4,'2023-02-10','1234'),('46528793t',7,'Marcos','Borrallo',9,'2024-05-01','1234'),('78945612p',2,'Santi','Gonzalez',5,'2021-01-11','1234'),('admin',1,'admin','admin',5,'2020-10-20','1234');
/*!40000 ALTER TABLE `comercial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `phone_number` varchar(50) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `representante` varchar(45) NOT NULL,
  `direccion` varchar(65) NOT NULL,
  `CP` int(11) NOT NULL,
  `ciudad` varchar(45) NOT NULL,
  `comunidad_autonoma` varchar(45) NOT NULL,
  `pagina_web` varchar(255) DEFAULT NULL,
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`phone_number`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `id_empresa_UNIQUE` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=286 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES ('000000000','Ofipack, S.L.S. (Demo)','ofipack.sefed@gmail.com','Mr. Montse Dos','Av. Europa, 125',28003,'Madrid','Comunidad de Madrid','https://rpalau6.wixsite.com/website',7),('111222333','rrggb','vampyr@gmail.com','Mr Vampyr','pueblo paleta',90854,'verdegris','kanto','',280),('600398340','LA BECAINA, SLS','labecaina@biada.net','Mr. OLGA JULIAN SEGURA','Av. Puig i Cadafalch, 89-99',8303,'Mataró','Catalunya','https://labecaina22.wixsite.com/la-becaina',9),('600439316','S2ANALYTICS, SLS','recepcio.s2analytics@gmail.com','Mr. Apol.lonia Llull','C/ Joan Miró, 22. 07300 Inca',7300,'Inca','Illes Balears','https://app.empresaula.com/websites/s2analytics-sls',10),('601996497','SERVEIS ELS ALFACS, SLS','ecoalfacs@insalfacs.cat','Mr. Montserrat Tomàs Talarn','C/Dr. Torné, s/n',43540,'La Ràpita','Catalunya','',11),('603231337','ALMIBLUE, SLS','almiblue.sefed@gmail.com','Mr. José  Fernández Fraile','Avda. Lehendakari Aguirre, 29-3º',48014,'BILBAO','Euskadi','',12),('604039674','URBAN MOBILITY WIRTZ, SLS','urbanmobility.sefed.wirtz@gmail.com','Mr. Ana Isabel Yebra Ferreiro','Rúa Caballeros, 1',15006,'LA CORUÑA','Galicia','https://urbanmobility.wixsite.com/inicio',13),('604428689','ENCESTA, S.L.S.','encesta2.sefed@gmail.com','Mr. INÉS MARÍA DÁVILA DÁVILA','Avda. de España, 50.  Fregenal de la Sierra. Extremadura',6340,'Fregenal de la Sierra','Extremadura','https://sites.google.com/educarex.es/encestasls/inicio',14),('605292489','LUXURY COSMETICS, SLS','luxurycosmeticsbaixmontseny@gmail.com','Mr. Pre Fuster Gregori','Plaça Muriel Casals, 2',8470,'Sant Celoni','Catalunya','https://sites.google.com/d/1D4ktP0bhyqeWSWnhO3piyvBsWGbIXcTz/p/1dz9LIQBSNKchlvwrjrHzBurdAzYdHS-E/edit',15),('606552265','PELUDIÑOS, SLS','peludinhosjohan@gmail.com','Mr. ANA ÁLVAREZ PORTELA','Rúa Staffan Morling, 4',36930,'Bueu','Galicia','https://peludinos.wordpress.com/',16),('606879665','FARMAGUINEUETA, SLS','farmaguine2023sefed@gmail.com','Mr. Elena Silvestre Catalan','Carrer de l\'Artesania, 55',8042,'BARCELONA','Catalunya','https://app.empresaula.com/websites/farmaguine',17),('607073893','Segarra Mobel, SLS','segarramobel@gmail.com','Mr. David Serra Barril','C. President Macià, 11',25200,'CERVERA','Catalunya','',18),('607765583','SILICONGINER','siliconginer.sefed@gmail.com','Mr. Elisabet Viana','C/ Maqueda, 8, MADRID, MADRID',28024,'MADRID','Comunidad de Madrid','https://siliconginersefed.wixsite.com/website-1',19),('610442264','COPIVENUS, SAS','copivenus@ieselcalamot.com','Mr. Miquel Àngel Campo Valent','Avinguda Joan Carles I, 62',8850,'Gavà (Barcelona)','Catalunya','https://app.empresaula.com/websites/copivenus-sas',20),('610782130','ErgoMarket. SLS','ergomarket.sl@gmail.com','Mr. Eva Alcoverro Serres','Passatge dels Tres Turons, 1',8350,'Arenys de Mar','Catalunya','https://app.empresaula.com/websites/ergomarket-sls',21),('612233209','All 4 Business, SLS','gestion.a4b@gmail.com','Mr. Emma Valera Guillamot','C. Ramon Llull, 430-438',8930,'Sant Adrià de Besòs','Catalunya','',22),('614138001','Calasanz Merchandising, S.L.S','merchandising.calasanz@gmail.com','Mr. Contact','C/ Hospital bajo, 11',48980,'Santurtzi','Euskadi','',23),('617268161','ECOCARS TIRANT, SLS','ecocarstirantsefed@gmail.com','Mr. VICENT MAHIQUES FORNÉS','C/ Dels Estudiants, 2',46701,'Gandía','Comunitat Valenciana','https://ecocarstirantsefed.wixsite.com/ecocarstirant',24),('618999033','UTEBO SEGURIDAD, SAS','uteboseguridad.sefed@gmail.com','Mr. Paz Altelarrea García','C/ Las Fuentes, 14',50180,'Utebo','Aragón','https://uteboseguridadsefe.wixsite.com/website',25),('619761355','Almerink, SLS','almerink@gmail.com','Mr. ANA PASTOR','Avda. Federico García Lorca, 60',4005,'Almería','Andalucía','https://almerink.wixsite.com/my-site',26),('619916988','Mediof, S.L.S.','mediof.sefed@gmail.com','Mr. M.Ángeles Moratinos Fernández','Avda. de Portugal, 58',47400,'Medina del Campo','Castilla y León','https://mediofsls.wordpress.com/',27),('621231638','ADELSAGROUPTIRANT, SLS','adelsagrouptirantsefed@gmail.com','Mr. VICENT MAHIQUES FORNÉS','C/ Dels Estudiants, 2',46701,'Gandía','Comunitat Valenciana','https://adelsagroup.wixsite.com/adelsagroup',28),('621292244','Mobles Almatà, SLS','moblesalmata@almata.cat','Mr. Antònia Regué Solé','Parc del Reial, s/n - 25600 Balaguer (LLeida)',25600,'Balaguer','Catalunya','https://moblesalmatacat.wordpress.com',29),('622777616','GAMTEC S.L.S','gamtec@outlook.es','Mr. Toni Escoto','Cami Llavador',3700,'Denia','Comunitat Valenciana','',30),('623166077','Pack Fiction, SAS','packfictionsas@gmail.com','Mr. Cristina Linares C','Rambla Marina, 393 - 08907 L\'Hospitalet de Llobregat',8907,'L\'Hospitalet de Llobregat','Catalunya','ww//ingriddiaz5.wixsite.com/pack-fiction-s-a-s',32),('623306636','WaterGC, S.L.S.','watergc@outlook.es','Mr. JOSEP  FRANCESC LLAVADOR CASTELLÀ','C/ Del Río Alfadali, s/n',46780,'Oliva','Comunitat Valenciana','https://app.empresaula.com/websites/watergc',31),('623319884','OLIMPIUS GYM, S.L.S.','olimpiusgym2023@gmail.com','Mr. Vicent Puig Moratal','CAMÍ DEL LLAVADOR, 1 LES MARINES',3700,'DENIA','Comunitat Valenciana','',33),('625312009','GALIPRINT, S.L.S.','galiprint@iesgalileo.es','Mr. Alfonso CASADO BUENO','C/ Villabáñez, 52',47012,'Valladolid','Castilla y León','https://galiprint.wordpress.com/',34),('626145465','Ecolim Salinas, S.L.S.','ecolimsalinas@outlook.es','Mr. MIGUEL ANGEL GONZALEZ REY','Avda. Espadaña, 3.',47140,'Laguna de Duero','Castilla y León','',35),('628502441','B&A, SLS','bebidasalcohol.stucom@gmail.com','Mr. Gemma Mayoral Cazorla','Travessera de les Corts ,78',8028,'BARCELONA','Catalunya','',36),('628545358','Adasun, S.A.S.','adasun@asuncionbenaguasil.es','Mr. CORAL GARCÍA RAMADA','Calle Pedralba, 38',46180,'Benaguasil','Comunitat Valenciana','https://sites.google.com/asuncionbenaguasil.es/adasun-s-a-s/inicio',37),('628941371','Equipalma, SLS','equipalma.sefed@santjosepobrer.es','Mr. M. ÀNGELS COMPANY CAPÓ','C/ Sebastià Arrom, 3',7008,'PALMA DE MALLORCA','Illes Balears','https://compresequipalma.wixsite.com/equipalma',38),('629367800','4EVERCLEAN, SLS','4evercleansls@iesbajocinca.org','Mr. Ana Belén Pallás Craver','C/ Río Cinca, s/n',22520,'Fraga','Aragón','https://4everclean5.wixsite.com/4everclean-sls',39),('632509302','ESSENTIAL DEPOT, SLS','essentialdepotoffice@gmail.com','Mr. Gemma Mayoral Cazorla','CATALUNYA 66 , BARCELONA',8007,'BARCELONA','Catalunya','https://app.empresaula.com/websites/essential-depot-sls',40),('634690286','CRIC CRAC SAS','criccrac.sefed@gmail.com','Mr. Blanca Cabré Serrano','C/ Jacint Barrau, 1',43201,'REUS','Catalunya','',41),('634690499','ECOFRESH COSMETICS, SAS','ecofresh.sefed@gmail.com','Mr. Blanca Cabré Serrano','Carrer Jacint Barrau, 1',43201,'REUS','Catalunya','',42),('634784332','MEV BORGES, SLS','mevborgesmp13@gmail.com','Mr. Gerard Sala Casanovas','C. Dr. Josep Trueta, s/n',25400,'Les Borges Blanques','Catalunya','https://app.empresaula.com/websites/mev-borges',43),('634978245','BOSFIT, SLS','bosfitelpilar@gmail.com','Mr. Jesús Estévez','C/Real, 21, Soto del Real, 28791 ( Madrid)',28791,'Soto del Real','Comunidad de Madrid','https://app.empresaula.com/websites/bosfit-sls',44),('635692188','CHOCOLATES MARCOS ZARAGOZA, S.L.S.','chocolatesmz.sefed@iesmarcoszaragoza.es','Mr. Álvaro José Gómez Sánchez','C/ Ferrocarril, 22',3570,'Vilajoyosa (Alicante)','Comunitat Valenciana','https://app.empresaula.com/websites/chocolates-marcos-zaragoza-s-l-s',45),('636574400','Decasarre, SAS','decasarre@gmail.com','Mr. Alma Heras Flores','C/ General Shelly 1',47013,'barcelona','Castilla y León','https://www.decasarre.com',46),('637484934','Atec, SLS','atec.sefed@floridauniversitaria.es','Mr. Isabel Redolat Iborra','C/ Rei en Jaume I, nº 2',46470,'Catarroja','Comunitat Valenciana','https://app.empresaula.com/websites/atec-sls',47),('639676696','ALGAS DO MORRAZO, SLS','algasdomorrazo@gmail.com','Mr. Estefanía Astray Roca','Rúa Staffan Morling, 4',36930,'Bueu','Galicia','https://algasdelmorrazo.wordpress.com/',48),('640575368','SNEAKOUT, SLS','soporte.sneakout@gmail.com','Mr. Encarna Amorós Díaz','107 - 108 Avenida Diagonal, Barcelona',8005,'BARCELONA','Catalunya','https://soportesneakout.wixsite.com/sneakout',49),('642521739','TRANSMINA, SLS','agome118@xtec.cat','Mr. Gonzalo Preciado Masero','C. Llull, 450',8930,'Sant Adrià de Besòs','Catalunya','https://app.empresaula.com/websites/transmina-sls',50),('644036298','WATERDROP, SAS','waterdrop@carmelites.cat','Mr. LEONOR NAVARRO','Carrer Rovira i Virgili, 8',43002,'Tarragona','Catalunya','',51),('644782255','LIMPIEZAS NAVARRO, S.L.S.','bionet.sefed@gmail.com','Mr. Pepi Sanjuan Ballester','C/ San Benito, s/n',3400,'Villena','Comunitat Valenciana','https://bionetsefed.wixsite.com/bionet-sls',52),('646940540','SALLEMAT SUMINISTROS, S.L.S.','info.sallematsuministros@gmail.com','Mr. Mateo Fernández','C/ la Salle, 2',39400,'Los Corrales De Buelna','Cantabria','infosallematsumini.wixsite.com/sallemat-suministros',53),('650700844','PUBLIPLEINS, SLS','publipleins.sl.sefed@gmail.com','Mr. Rosa Torres López','C/ Quito, 25-37, 080030-BARCELONA, CATALUÑA',8030,'BARCELONA','Catalunya','',55),('652525399','OFICARLY  S.L.S.','info.oficarly@gmail.com','Mr. RAMON OLIVERA SANCHEZ','C/ SAN FRANCISCO S/N',28350,'CIEMPOZUELOS','Comunidad de Madrid','',56),('656717042','BDNMERCH, SLS','bdnmerch@gmail.com','Mr. Contact','Carrer de la Batllòria, s/n',8917,'Badalona','Catalunya','https://vendesbdnmerch.wixsite.com/bdn-merch',57),('658776656','SABOR DIVINO, SLS','recepcion.sefed@gmail.com','Mr. Pilar Marset Reyna','C. Ramon Llull,, 430-438',8930,'Sant Adrià de Besòs','Catalunya','',58),('659809461','GON-BER, S.L.S.','gonzaloberceo.sefed@gmail.com','Mr. Rafael Morales Gisbert','Paseo de la Florida, 25',26540,'Alfaro','La Rioja','https://gonzaloberceosefed.wixsite.com/website',59),('660914329','ECOS, S.L.S.','ecos.info23@colegioesclavasbenirredra.com','Mr. Berchmans Costa','Carrer Convent nº22',46703,'Benirredrà','Comunitat Valenciana','https://ecosoficinas.wixsite.com/eco-s',60),('661614622','BIOPAU, SAS','biopau24@gmail.com','Mr. Martí Terrasa','C/Joan Miró, 22',7300,'Inca','Illes Balears','https://neusfornes7.wixsite.com/biopau',61),('663159101','Informàtica de Ponent, SLS','inponent.sefed@gmail.com','Mr. Teresa Bosch Falcó','c/ La Palma, nº 33',25002,'LLEIDA','Catalunya','',62),('663513272','Aroma de Tramuntana, SLS','aromadetramuntanasl@gmail.com','Mr. MEDARD ORIOL','C. Arquitecte Pelai Martínez, 1, 17600 FIGUERES, CATALUNYA',17600,'FIGUERES','Catalunya','https://ichernitskyi.wixsite.com/website-1',63),('663680984','BUSITRAVA S.L.S.','busitrava@gmail.com','Mr. Isabel Díaz Picón','Paseo Universidad, 1',13005,'Ciudad Real','Castilla-La Mancha','',64),('666666666','STUTECH, SLS','stutechsls@gmail.com','Mr. Encarna Amorós Díaz','Carrer de València 260 08007',8923,'BARCELONA','Catalunya','https://gkurtdavegarcia.wixsite.com/misitio',65),('666777666','WIKKO, SLS','wikkoolot@gmail.com','Mr. Mariona Jorda i Serra','C/Toledo, 12',17800,'Olot','Catalunya','https://mzoubair932.wixsite.com/wikko/ca',66),('666777888','Capsule Corp','demon@gmail.com','Majiin Bu','Australia',99900,'kawaii','kanta','',281),('669393790','ECOJEX, S.L.S.','ecojex22@gmail.com','Mr. Antonia Moreno Arevalo','Barriada El Pomar, s/n',6380,'Jerez de los Caballeros','Extremadura','',67),('670044421','Art i Vidre Molins, SAS','artividre.molins@gmail.com','Mr. JOSEFINA PLUMED RAMOS','C/ Francesc Layret, 15 (Polígon El Pla)',8750,'Molins de Rei','Catalunya','https://artividremolins.wixsite.com/artividre',69),('670378504','SIMARROPAC, SLS','simarropac@gmail.com','Mr. MªJOSÉ MARTORELL ESCRIBÁ','Avda. de les Corts Valencianes s/n',46800,'Xàtiva','Comunitat Valenciana','https://simarropac.wixsite.com/simarropac--sls',71),('671618434','CONDIAZ, S.L.S','condiaz.sefed@salesianospizarrales.com','Mr. Mercedes Guevara Velázquez','Ctra. Ledesma, 32-52',37006,'Salamanca','Castilla y León','https://condiazsefed.wixsite.com/condiazsls',72),('672141654','STANFORD, SLS','expovendingsl@xarxa.fedac.cat','Mr. Carme Parella Puntas','Carrer Pare Coll, 1',8600,'Berga','Catalunya','https://stanford3.webnode.es/sobre-nosotros/',73),('672142466','CAVINTER, SLS','cavinter@intermunicipal.com','Mr. Eva María Garrote Fernández','Plaça Santiago Rusiñol, s/n',8770,'Sant Sadurní d\'Anoia','Catalunya','https://recepciocavinter.wixsite.com/cavinter2022',74),('672165109','DISPROSOR S.L.S.','disprosor@v-espino.es','Mr. Felix Gracia Salvador','C/ Santa Teresa de Jesús,  1',42003,'Soria','Castilla y León','disprosorsl.mydurable.com',75),('673835984','MANDUCA MANLLEU, SLS','manducamanlleu.sefed@gmail.com','Mr. Margarita Ros Arguimbau','Baixa Cortada, 1',8560,'Manlleu','Catalunya','https://manducamanlleusefed.wixsite.com/manduca',76),('674676748','ARRELS, SLS','arrels.sefed@gmail.com','Mr. Contact','Rambleta de Joan Miró s/n',8191,'RUBÍ','Catalunya','https://arrelssefed.wixsite.com/rubi',77),('675748766','EPICO, SLS','info.epicosls@gmail.com','Mr. Sandra Galeano Corchero','C/San Francisco, 1',28350,'Ciempozuelos','Comunidad de Madrid','https://infoepicosls.wixsite.com/epicosls',78),('676564237','LC Soluciones informáticas, SLS','lsolucionessefed2020@gmail.com','Mr. ELVIRA LAMEIRAS GARCÍA','C/ Isla de Sálvora, 153',28400,'COLLADO VILLALBA','Comunidad de Madrid','https://app.empresaula.com/websites/lc-soluciones-informaticas-sls',79),('677492117','BRUGULART, SLS','brugulart2324@gmail.com','Mr. Núria Heras Geli','Carretera Figueroles, 19',17820,'Banyoles','Catalunya','https://brugulart2324.wixsite.com/my-site-1',80),('679056720','Asunco, S.L.S.','asunco@asuncionbenaguasil.es','Mr. LOLA DE LA CRUZ TORRES','CALLE PEDRALBA, 38',46180,'BENAGUASIL','Comunitat Valenciana','',81),('680603182','Fruit for Life, S.L.S.','fruitforlifesanmartin@gmail.com','Mr. ANA MARÍA GÓMEZ PALOMINO','C/ Goya, s/n.',10310,'Talayuela','Extremadura','https://app.empresaula.com/websites/fruit-for-life-s-l-s',82),('681964069','EXTINFIREmerindades, S.L.S.','merindadesextinfire@gmail.com','Mr. David García de Dompablo','C/ El Soto s/n',9550,'Villarcayo de Merindad de Castilla La Vieja','Castilla y León','https://app.empresaula.com/websites/extinfiremerindades-s-l-s',83),('682279570','TECNUS,  SAS','tecnusventas@lasallesagradocorazon.es','Mr. Nieves Herrero Egea','Avda Cardenal Herrera Oria, 242; MADRID',28035,'MADRID','Comunidad de Madrid','https://tecnusventas.wixsite.com/tecnus',84),('682715477','BALMES TECNOLÒGIC, SCCLS','info.tecnologic@balmesinnova.onmicrosoft.com','Mr. Alisabet Ribalta Guzmán','Travessia Industrial, 157-161',8907,'L\'Hospitalet de Llobregat','Catalunya','https://balmestecnologic.wixsite.com/my-site',85),('683397277','GESIDU, SLS','gesidu@iesbotanic.es','Mr. Enrique Serna Lopez','Avinguda Jaume I, 60',12600,'La Vall d\'Uxò','Comunitat Valenciana','https://nereaperezbalum.wixsite.com/gesidu',86),('683442379','Riberpublic, SLS','riberpublic.sefed@gmail.com','Mr. Felisa Delgado Rodríguez','C/ Mirabel, 25',47010,'Valladolid','Castilla y León','https://riberpublicsefedribera.on.drv.tw/Web_RiberPublic/Index.html',87),('686591230','ULISES, SLS','ulises.sefed@gmail.com','Mr. ARACELI ECHAIDE IRIBARREN','C/Cataluña, 18 , PAMPLONA, NAVARRA',31006,'PAMPLONA','Comunidad Foral de Navarra','https://empresaulises.wordpress.com/',89),('687772690','Lomasgredos, SLS','lomasgredos.sefed@gmail.com','Mr. MARIA BELEN FERNANDEZ  SANTOS','Apdto 59 Avenida de Lourdes, 2',5400,'Arenas de San Pedro','Castilla y León','https://app.empresaula.com/websites/lomasgredos-sls',90),('688677437','ACIJAR, S.L.S','acijar.sefed@gmail.com','Mr. Igor Etxabe Lete','C/ Álava, 39',1006,'VITORIA-GASTEIZ (ÁLAVA)','Euskadi','https://app.empresaula.com/websites/acijar-s-l-s',92),('688820002','GARBI, SLS','garbi.sefed@gmail.com','Mr. Alberto Larrea Alava','Agirrebidea, 2',20570,'BERGARA (GIPUZKOA)','Euskadi','https://garbisefed.wixsite.com/inicio',93),('688901229','IRP Green Solutions, SAS','Igsol.sefed@irp.cat','Mr. Marisa BATALLA','C/Cristóbal de Moura, 223, 08019 Barcelona',8019,'Barcelona','Catalunya','https://app.empresaula.com/websites/irp-green-solutions-sas',94),('689207609','LOVIEXPED, SAS','loviexped@gmail.com','Mr. Montse Piera Eroles','C/Consell de Cent, 397',8009,'Barcelona','Catalunya','https://app.empresaula.com/websites/loviexped-sas',95),('689679958','CC TECH, SLS','cctech@culturalbadalona.com','Mr. Iván Alcoceba López','C/ Termes Romanes, 10',8911,'Badalona','Catalunya','https://cctech8.wixsite.com/cc-tech',96),('691546695','BIOZAL, SLS','biozal@zalima.es','Mr. Araceli Calvo','Sánchez de Feria, 1',14003,'Córdoba','Andalucía','https://biozal.wixsite.com/biozal-1',97),('691772220','Cuencamovil Sefed, S.L.S.','informacion@cuencamovilsefed.com','Mr. Marta Moya Pascual','Parque del Huécar, 2, bajo',16001,'Cuenca','Castilla-La Mancha','https://cuencamovilsefed20.wixsite.com/cuencamovilsefed',98),('695560279','Almàssera de l\'Empordà','almasseradelemporda@cendrassos.net','Mr. Contact','C/Arquitecte Pelai Martínez, 1, FIGUERES, CATALUNYA',17600,'FIGUERES','Catalunya','https://sites.google.com/cendrassos.net/almsseradelemporda',99),('697488300','TEXBOPRO, SLS','texbopro.sefed@santjosepobrer.es','Mr. M. ÀNGELS COMPANY CAPÓ','C/ Sebastià Arrom, 3-B',7008,'Palma de Mallorca','Illes Balears','https://vendestexbopro.wixsite.com/texbopro',100),('699289069','ALES, SLS','alescompany1@gmail.com','Mr. Pol Ferrús Santolalla','C/Toledo, 12',17800,'Olot','Catalunya','https://riyadmrabti2006.wixsite.com/my-site',101),('699669090','Desarroyo, SLS','desarroyoelectroluz.sefed@gmail.com','Mr. Contact','C/Carlos Barriga, 13',10900,'Arroyo de la Luz','Extremadura','',102),('722527966','Design Me, SLS','designme2324@gmail.com','Mr. María del Mar Sánchez Morán','Avda.Felipe Corchero',6800,'MERIDA','Extremadura','https://designme94.webnode.es',103),('747447833','TEKNOBURU, SLS','ventas.teknoburu@fpbarakaldo.lh','Mr. Josean Alvarez Quiroga','C/ Aldapa, 3B',48901,'BARAKALDO (BIZKAIA)','Euskadi','',105),('747447836','ZAINDULAN, SLS','recepcion.zaindulan@nlarburu.com','Mr. Ismael Medeiros','C/ Aldapa, 3B',48901,'BARAKALDO (BIZKAIA)','Euskadi','https://marketingzaindulan.wixsite.com/zaindulan',104),('848431640','BAZTANEKO, SLS','baztanekosl@gmail.com','Mr. Miren Mikelajauregi Inziarte','Diputazio Etorbidea, 7',31,'Elizondo','Comunidad Foral de Navarra','https://sites.google.com/view/baztanekosls/idiomahizkuntza',107),('876636152','Flor and Garden, SLS','florandgarden@iesmardearagon.es','Mr. Lorena Landa Balaguer','C/ José Mª Albareda, s/',50700,'Caspe','Aragón','',108),('877225102','TERRISSAL, SLS','terrissal.sefed.vendrell@gmail.com','Mr. Sylvie Bastide Richart','CARRER CAMÍ REIAL, 13-17',43700,'VENDRELL, EL','Catalunya','https://terrissalsefed.wixsite.com/vendrell/es',109),('877913253','Plàstics Dertosa, SAS','pdertosa.sefed@iesebre.com','Mr. Ferran Sabaté Borràs','Av. Colom, 34-42,  TORTOSA, CATALUNYA',43500,'TORTOSA','Catalunya','https://app.empresaula.com/websites/plastics-dertosa-sas',110),('877913254','Decora i Regala, SLS','decora.sefed@iesebre.com','Mr. Pilar Nuez Garcia','Avda. Colom, 34-42',43500,'TORTOSA','Catalunya','https://app.empresaula.com/websites/dcore',111),('881867188','Comedela S.Coop.S','comedela24gal@gmail.com','Mr. María Fe Blanco González','Frións s/n',15967,'RIBEIRA','Galicia','https://app.empresaula.com/websites/comedela-manxares-galegos',112),('886111003','GALLAECIA DISTRIBUCIÓN, SLS','gallaeciadistribucion@gmail.com','Mr. YOLANDA ÁLVAREZ GARCÍA','Avda.Dona Fermina, 1',36207,'VIGO','Galicia','',113),('886121021','RIMELLA, SLS','rimella.sefed@gmail.com','Mr. José Antonio Pérez Torres','Estrada Vella de Madrid, 177, VIGO, GALICIA',36214,'VIGO','Galicia','https://app.empresaula.com/websites/rimella-sls',114),('914306809','INTELTAB,SAS','inteltabga2b@gmail.com','Mr. Nerea Palacios','C/Corregidor Diego de Valderrábanos, 35',28030,'Madrid','Comunidad de Madrid','https://luzmariahernandezs.wixsite.com/my-site',116),('914342030','BOSCOPETS, SLS','sefed.boscopets@ciudaddelosmuchachos.com','Mr. VICTORIA DOMÍNGUEZ ÁLVAREZ','C/Santa Marta, 15',28038,'MADRID','Comunidad de Madrid','https://app.empresaula.com/websites/boscopets-sls',117),('914615211','RENACIMIENTO, SLS','iesrenacimientosl@gmail.com','Mr. Contact','C/Castelflorite, 4',28019,'Madrid','Comunidad de Madrid','',118),('914627411','MAKING GIFTS, SLS','makingifts.sefed@gmail.com','Mr. MANOLI SÁNCHEZ','Calle Madre Nazaria 5',28044,'MADRID','Comunidad de Madrid','https://app.empresaula.com/websites/making-gifts-sls',119),('914991144','DUQUEVES, SLS','duquevessls@gmail.com','Mr. Silvia Achaerandio Fernández','Paseo de la Chopera, 64',28523,'Rivas-Vaciamadrid','Comunidad de Madrid','https://app.empresaula.com/websites/duqueves-sls',120),('915195257','Comercial CLARA, SLS','ga.comercialclara@gmail.com','Mr. Melania Merino González','Calle Padre Claret, 8',28002,'Madrid','Comunidad de Madrid','',122),('915738015','Saysar, SLS','saysar@sasr.es','Mr. Javier Zofío Jiménez','Calle del Doctor Esquerdo, 53',28028,'Madrid','Comunidad de Madrid','https://app.empresaula.com/websites/saysar-sls',123),('916680790','LUZ Y COLOR','infolivos.sefed@gmail.com','Mr. ANA FERNÁNDEZ','C/ Joan Miró, 1,',28840,'MEJORADA DEL CAMPO','Comunidad de Madrid','https://marketingluzycolor.wixsite.com/luz-y-color',124),('917050011','VENDING LA SALLE, SLS','vending.sefed@lasalleinstitucion.es','Mr. ALICIA GUTIÉRREZ MIGUEL','Blas Cabrera, 50 - 28044 Madrid',28044,'MADRID','Comunidad de Madrid','https://armsatour.wixsite.com/vending-la-salle',125),('917066616','EMPAPELARTES, SLS','empapelartesldv@gmail.com','Mr. ALBA ALCAIDE PELETERO','C/de Blas Cabrera, 90',28044,'Madrid','Comunidad de Madrid','https://empapelartesldv.wixsite.com/empapelartes',126),('917594152','Mundiluz, SLS','mundiluzsls.sefed@gmail.com','Mr. Paloma González','C/ Benita de Ávila, 3, , MADRID, MADRID',28043,'MADRID','Comunidad de Madrid','https://app.empresaula.com/websites/mundiluz-sls',127),('919080889','UNISTYLE, S.L.S','info@unistyle-s-l-s.com','Mr. Beatriz Sánchez Mascaraque','C/ Corregidor Diego de Valderrábano, 35',28030,'Madrid','Comunidad de Madrid','https://sites.google.com/view/unistylega2a/inicio',128),('923139012','Delicias Salmantinas, S.A.S.','delisal.sefed@hotmail.com','Mr. Raúl Gallego Martín','C/ General Gutiérrez Mellado, 6-8',37900,'Santa Marta de Tormes','Castilla y León','https://app.empresaula.com/websites/delicias-salmantinas-s-a-s',129),('924021780','Impresión-Arte Lacimurga, SLS','impresionartelacimurga2020@gmail.com','Mr. Óscar Ramón Torres Alfonso','Avda. de la Constitución, s/n. 06760 Navalvillar de Pela',6760,'Navalvillar de la Pela','Extremadura','https://app.empresaula.com/websites/impresion-arte-lacimurga-sls',131),('924021812','OFINASA, S.L.S','ofinasasl.sefed@gmail.com','Mr. Contact','C/Torres Isunza, s/n',6400,'Don Benito','Extremadura','https://ofinasaslsefed.wixsite.com/ofinasa-s-l',132),('924028606','Ecoval, S.L.S.','ecoval2.sefed@gmail.com','Mr. Fernando García González','Avenida San Ignacio, s/n',6220,'Vilafranca de Barros','Extremadura','https://ecovalmarketing.wixsite.com/ecoval--s-l-s',133),('925284043','HAVAZARQUIEL, S.L.S.','havazarquiel@ies-azarquiel.es','Mr. Almudena del Río Roig','Paseo San Eugenio, 21',45003,'Toledo','Castilla-La Mancha','https://havazarquiel.jimdosite.com/',134),('927005260','West Office, SLS','westoffice.sls.sefed@gmail.com','Mr. José Luis Romero García','C/ Carlos Barriga, 13',10900,'Arroyo de la Luz','Extremadura','https://westofficeslssefed.wixsite.com/west-office-sls',139),('927006716','MOLDÁGORA, S.LS.','moldagora.sefed@gmail.com','Mr. María Simón Solís','C/ Lima, s/n',10005,'Cáceres','Extremadura','https://app.empresaula.com/websites/moldagora-s-ls',135),('927015806','INTERGAS, SLUS','intergasslus@gmail.com','Mr. José Luis Vega Prieto','C/ San Sebastián,, s/n.',10810,'Montehermoso','Extremadura','https://app.empresaula.com/websites/intergas-slus',140),('927016706','ZURBASHOP, S.L.S.','ZURBASHOPSLS@gmail.com','Mr. María del Carmen Sánchez Campo','Crta. Rosalejo, s/n',10300,'Navalmoral de la Mata','Extremadura','',136),('927017732','Distribuidora El Español, S.L.S.','2023elespanol@gmail.com','Mr. HORTENSIA GIL APARICIO','Avda. Virgen del Puerto, 20',10600,'Plasencia','Extremadura','',141),('927022530','Miel de Luna, SLS','mieldelunasls@gmail.com','Mr. SONIA MARÍA VAQUERO DÍAZ','Avda. Mario Roso de Luna, s/n',10120,'Logrosán','Extremadura','https://app.empresaula.com/websites/miel-de-luna-sls',142),('927028528','MOMENTOS EXPECIALES, S.L.S.','momentosexpeciales@gmail.com','Mr. PENÉLOPE DÍAZ MARTÍNEZ','Avda. Diputación, s/n',10500,'Valencia de Alcántara','Extremadura','',143),('927274634','KitHome Muebles, S.L.U.S.','kithomemobiliario.sefed@gmail.com','Mr. Contact','C/ Las Américas, 1',10800,'Coria','Extremadura','https://kithomemobiliarios.wixsite.com/website-1',137),('927274635','Buyco.Merchandising, S.L.U.S','buycomerchand@gmail.com','Mr. Mario García Sánchez','C/ Las Américas, 1.',10800,'Coria','Extremadura','https://buycomerchanslus.wixsite.com/my-site',138),('931683120','Talentum Formació, S.L.S.','info.talentum.sefed@gmail.com','Mr. Contact','C. dels Calders, 32 Sabadell, Barcelona',8203,'Sabadell','Catalunya','https://talentumformacio.wixsite.com/inicio',144),('931767686','VelocityCar, SLS','velocitycar.sefed@gmail.com','Mr. Clara Soriano Rodríguez','C/ Balboa, 18, , BARCELONA, CATALUÑA',8003,'BARCELONA','Catalunya','https://www.velocitycar.tk/',151),('931800200','PELEGRIPACK, SLS','pelegripack.sefed@gmail.com','Mr. Roser Arnal','Consell de Cent, 14',8014,'BARCELONA','Catalunya','https://www.VM Sostenible, SLS',152),('931830436','DISTRICT1, SLS','corporation.atlantiss@gmail.com','Mr. Encarna Amorós Díaz','Carrer de la Marina, 16, 08005 Barcelona',8005,'BARCELONA','Catalunya','',153),('932065050','BOSCO BARCELONA, SLS','sefed.boscobarcelona@gmail.com','Mr. Patxi Béjar Rodríguez','Passeig Sant Joan Bosco, 42, BARCELONA, CATALUÑA',8017,'BARCELONA','Catalunya','https://app.empresaula.com/websites/bosco-barcelona-sls',155),('932213010','ECAIB informàtica, S.L.S.','sefed@iespoblenou.org','Mr. Jaume Morera Guevara','C/ Doctor Trueta, 206',8005,'BARCELONA','Catalunya','https://21759943b.wixsite.com/ecaibinformaticasls',156),('932523080','Office Line, SAS','officeline.barcelona@centrosfest.net','Mr. Julio Llorente Álvarez','Avda. d\'Esplugues 62-70',8034,'BARCELONA','Catalunya','https://app.empresaula.com/websites/office-line-sas',157),('932684661','OFFImate, SAS','offimate@etpx.fjaverianas.com','Mr. Irene González Ruiz','Avda.Francesc Cambó, 12',8003,'BARCELONA','Catalunya','https://offimate.wixsite.com/offimate-sas',145),('933015696','CAPON, SLS','info.caponsl@gmail.com','Mr. Gemma Mayoral Cazorla','C/ Gran Via de les Corts Catalanes, 675, Barcelona',8013,'BARCELONA','Catalunya','https://infocaponsl.wixsite.com/cap-on',160),('933191700','XOCO BARCINO, SLS','xocobarcino@etpx.fjaverianas.com','Mr. Irene González Ruiz','Avda.Francesc Cambó, 12',8003,'BARCELONA','Catalunya','https://jhonnajefrys54.wixsite.com/xocobacino',146),('933303667','CALFRED, SAS','calfredsas@gmail.com','Mr. VICTORIA DE REGAS CHAVARRIA','C/ Sant Pius X, 8',8901,'L\'HOSPITALET DE LLOBREGAT','Catalunya','',162),('933382553','Repaper, SAS','repapersassimulacio@gmail.com','Mr. Juani Robles','C/ Sant Pius X, 8',8901,'L\'Hospitalet de  Llobregat','Catalunya','https://app.empresaula.com/websites/repaper-sa',163),('933533810','SALUT I VIDA, SLS','salutivida.sefed@centresmolina.com','Mr. Marissa Cerezo','Lorena, 61-63',8042,'Barcelona','Catalunya','https://salutividasefet.wixsite.com/salut-i-vida',164),('933771100','ETSHEALTHY, SAS','etshealthy.sefed@iesesteveterradas.cat','Mr. Contact','Carrer Bonavista, 70',8940,'Cornellà de Llobregat','Catalunya','https://app.empresaula.com/websites/etshealthy',165),('933794097','STUDENT FOODS, SLS','studentfoods@inslessalines.cat','Mr. Rosa María Martínez Fernández','Av Onze de Setembre, 36-38',8820,'El Prat de Llobregat','Catalunya','',159),('933978511','SETPACK, S.A.S','setpack@institutb7.cat','Mr. Jorge Luzón','Carrer d\'Ausiàs Marc, 86',8912,'Badalona','Catalunya','',166),('933990511','COLOR VITAL, SLS','comercial.colorvital@hotmail.com','Mr. Juan Miguel del Río Borrego','Carrer de la Batllòria, s/n',8917,'Badalona','Catalunya','https://evapineo.wixsite.com/color-vital-esp',167),('934126896','DIOMOND, SLS','diomond.company.sls@gmail.com','Mr. Contact','Calle del General Álvarez de Castro, 6',8019,'BARCELONA','Catalunya','',169),('934144444','METREL , SLS','metrel-sls@proton.me','Mr. Josep Molinero','Carrer Santaló, 36.',8021,'Barcelona','Catalunya','https://app.empresaula.com/websites/metrel-sls',170),('934181288','VALLHEBRON, SLS  Marketing per a  empreses','vallhebron.sefed@gmail.com','Mr. MªDesirée Romero Hernández','Passeig de la vall d\'Hebron, 93-95',8035,'BARCELONA','Catalunya','https://88.87.202.77/shop.asp?es_obj=art_list&cmd=cat&id=2936&lan=ES&p=2&n=&o=&s=&k=',171),('934190383','DETALL EMPRESARIAL, SLS','detallempresarialsls@gmail.com','Mr. GUILLERMO TOBALINA ATANET','Comte d\'Urgell, 187',8036,'BARCELONA','Catalunya','',147),('934551471','Infor You, SLS','inforyou.sefed@palcam.cat','Mr. Isaac Guardiola Ripollès','C. Rosalía de Castro, 32',8025,'BARCELONA','Catalunya','',172),('934794530','CARGOPRAT, S.A.S.','cargoprat@gmail.com','Mr. Montserrat Sánchez Fernández','C/ de les Moreres, 48',8820,'PRAT DE LLOBREGAT, EL','Catalunya','https://cargoprat.wixsite.com/cargoprat',148),('934800475','ET Ecològics, SAS','etecologics.sefed@iesesteveterradas.cat','Mr. Antonia Jimenez Valverde','Carrer Bonavista, 70',8940,'CORNELLA DE LLOBREGAT','Catalunya','https://app.empresaula.com/websites/et-ecologics-sas',175),('934841232','AMBQESCRIC, SLS','ambqescric2022sefed@gmail.com','Mr. Cristina Dedeu Peyri','Avinguda Francesc Cambó, 10',8003,'Barcelona','Catalunya','https://ambqescric.wixsite.com/website',176),('935051604','GIFTIFY, SLS','empresasimulada4@politecnics.barcelona','Mr. Xavier Serra Cantallops','C/ Plaça Urquinaona 10',8010,'BARCELONA','Catalunya','',178),('935308488','ACT, SAS','act.serveiscentrals@gmail.com','Mr. Manel Marín Donate','Ronda Sant Antoni, 19',8011,'BARCELONA','Catalunya','https://workersuit.wixsite.com/worker-suit',179),('935348239','AC DESIGN ROCA, SLS','sefed.acdesing2122@gmail.com','Mr. Sandra Ginel Jalencas','Av. Meridiana, 263',8027,'BARCELONA','Catalunya','https://app.empresaula.com/websites/ac-design-roca-sls',180),('935566989','COMERCIAL SABADELL , SLS','comercialsbd@escolaindustrial.org','Mr. Gemma Solano','Carrer Calderón, 56',8201,'SABADELL','Catalunya','https://comercialsbd.wixsite.com/my-site',177),('936334450','ALOTRANS, SLS','alotrans@ieselcalamot.com','Mr. Izaskun Fernandez','Av. de Joan Carles I',8850,'Gavà','Catalunya','https://alotrans.wixsite.com/alotrans',181),('936408369','RENZO BIANCHI, SLS','renzo.bianchi.official@gmail.com','Mr. Gemma Mayoral Cazorla','Carrer de la Costa brava, 30',8030,'BARCELONA','Catalunya','',182),('936686697','Begudes Bernat, SLS','bbernatsefed@gmail.com','Mr. Yolanda Rodríguez Vargas','Ntra. Sra. de Lourdes 34, 08750 , MOLINS DE REI , CATALUNYA',8750,'MOLINS DE REI','Catalunya','https://sites.google.com/ibf.cat/begudesbernat/inici',183),('936768005','Jardineria Sant Vicenç, SAS','jsv@santvicenc.salesians.cat','Mr. Carme Pérez Fernández','C/ Rafael Casanova, 132',8620,'SANT VICENÇ DELS HORTS','Catalunya','https://app.empresaula.com/websites/jardineria-sant-vicenc',184),('936923204','TECHFY, SLS','techfy@inspalauausit.cat','Mr. Javier Asenjo Fernández','Ctra. de la Santiga, 56',8291,'Ripollet','Catalunya','https://',185),('937258744','ASSESSORIA I RECURSOS JURÍDICS SABADELL, SLS','assessoriasbd@escolaindustrial.org','Mr. BEGOÑA CASADO MORENO','Carrer Calderón, 56',8201,'SABADELL','Catalunya','https://assessoriasbd.wixsite.com/assessoria-i-recurso',187),('937336580','MERKATRONIK, SLS','MERKATRONIK@insmontserratroig.cat','Mr. JOVITA SÁNCHEZ CONTRERAS','C/Cervantes, 46',8221,'Terrassa','Catalunya','',188),('937549282','TOT PLANT, SLS','totplantsl2023@gmail.com','Mr. Yolanda Zaragoza Sola','Rafael de Casanova, s/n',8330,'Premià de Mar','Catalunya','https://totplantsls.wordpress.com/',189),('937785534','Seguretat Blanxart, SAS','simu.sefed@insdanielblanxart.cat','Mr. MARÍA PILAR MORALEDA GARETA','C/ Vall d´Aran, 96-98',8640,'OLESA DE MONTSERRAT','Catalunya','https://simusefed4.wixsite.com/website',186),('937981489','MINDVERD, S.L.S.','mindverd@biada.net','Mr. Contact','Puig i Cadafalch 89-99',8303,'MATARO','Catalunya','https://app.empresaula.com/websites/mindverd-s-l-s',149),('938045817','OFFICE+,  SLS','technoffice.sefed@gmail.com','Mr. Contact','Avda. Emili Vallés, 4,',8700,'IGUALADA','Catalunya','',190),('938055720','PAPERPACK, SAS','paperpack.sefed@gmail.com','Mr. Carla Terradas','Avda. Emili Vallés, 4',8700,'IGUALADA','Catalunya','https://lhigueras2.wixsite.com/paperpack/',191),('938142742','NOVET, SAS','novet.sefed@gmail.com','Mr. LOLI MARTINEZ','Rambla Principal, 71, VILANOVA I LA GELTRÚ, CATALUNYA',8800,'VILANOVA I LA GELTRÚ','Catalunya','https://novetsefed.wixsite.com/novet',192),('938386004','CARS SANT BOI, SLS','carssantboi2021@gmail.com','Mr. Alicia Molina','Av. Aragó, 40; 08830 Sant Boi de Llobregat (Barcelona)',8830,'Sant Boi de Llobregat','Catalunya','https://infohomecenter2.wixsite.com/my-site-1',193),('938791881','Sweet Sefed, SLS','sweet.sefed@epiagranollers.cat','Mr. Fina Jerez','C/ Guayaquil, 54',8401,'GRANOLLERS','Catalunya','https://app.empresaula.com/websites/sweet-sefed-sls',194),('938930617','DISTRIBUCIONS CARPEDIEM, SLS','distribucionscarpediem@gmail.com','Mr. Contact','Rambla Principal, 71',8800,'Vilanova i la Geltrú','Catalunya','https://distribucionscarpe.wixsite.com/website',196),('938961405','BINARI, SAS','binari.sefed@gmail.com','Mr. Sonia Robres','Joan Maragall, s/n, , SANT PERE DE RIBES, CATALUÑA',8810,'SANT PERE DE RIBES','Catalunya','https://app.empresaula.com/websites/binari-sas',197),('938962301','Protec Laboral, S.A.S.','proteclaboral.sefed@hotmail.com','Mr. yolanda marquez carmona','C/ Major, 110',8810,'Sant Pere de Ribes','Catalunya','https://proteclaboralsefed.wixsite.com/santperederibes',198),('941304765','COSSI BRAND, S.A.S','cossibrand.sefed@gmail.com','Mr. Contact','C/ Manuel Bartolomé Cossío, S/N',26200,'Haro','La Rioja','https://sites.google.com/view/cossibrand/inicio?authuser=0',199),('942754112','Arte y Decoración, S.L.S.','artedeco.sefed@hotmail.com','Mr. Eva Carcaboso Álvarez','C/ Dr. Jiménez Díaz, 6.',39200,'Reinosa','Cantabria','https://arteydecoracionsls.wixsite.com/arteydecoracion',200),('942802805','Dobra, SLS','dobra.sefed@iesmiguelherrero.com','Mr. Julián Iturri Iglesias','C/ Julio Hauzeur, 59',39300,'Torrelavega','Cantabria','https://app.empresaula.com/websites/dobra-sls',203),('943899167','EIBAR LOGISTICS, SLS','eibarlogistics.sefed@uni.eus','Mr. Amaia olabarria','Otaola Hiribidea, 29',20600,'Eibar','Euskadi','',206),('944001807','OFIMAT BASAURI, SLS','ofimat.sefed@gmail.com','Mr. LEIRE ETXEANDIA SOLAGUREN','C. Lehendakari Agirre, 97',48970,'BASAURI (BIZKAIA)','Euskadi','ofimat36.webnode.es/',207),('944160337','RECYCLON, SLS','recyclon.sefed@gmail.com','Mr. FERNANDO ZABALA BASABE','C/ Esperanza, 12',48005,'BILBAO (BIZKAIA)','Euskadi','https://app.empresaula.com/websites/recyclon-sls',208),('944446734','PROVENDING, SLS','mikeldiprovending@gmail.com','Mr. MARTA CRISTOBAL SARRAMIAN','C/ Autonomía, 62',48012,'BILBAO (BIZKAIA)','Euskadi','https://cms.provendingmikeldi.webnode.es/',209),('944668211','VENDING MIKELDI, SLS','vendingmikeldiafi@gmail.com','Mr. MARTA CRISTOBAL SARRAMIAN','Calle Autonomía 62',48012,'BILBAO (BIZKAIA)','Euskadi','https://app.empresaula.com/websites/vending-mikeldi-sls',210),('944668803','Iurretabike, S.L.S.','iurretabike24@gmail.com','Mr. Contact','C/ Olaburu, 19',48215,'Iurreta','Euskadi','',212),('944751117','EKI ENERGY, S.L.S.','ekienergy@elorrieta-errekamari.com','Mr. Eneko Tolosa','San Pedro,  5',48014,'Bilbo','Euskadi','',211),('946169421','LEA-ARTIBAI, SLS','leartibai.sefed@gmail.com','Mr. ARANTZAZU ELORDI ARRIZABALAGA','Xemein Etorbidea, 19',48270,'MARKINA-XEMEIN','Euskadi','https://afi20romu.wixsite.com/my-site',213),('947233645','Deskubre35, S.L.S.','deskubre35.sefed@gmail.com','Mr. RAFAEL TABARES RUIZ','C/Francisco de Vitoria s/n,',9006,'Burgos','Castilla y León','https://deskubre35sefed.wixsite.com/inicio',215),('948317790','GESLAN Pruebas (Testing)','docentesefed@gmail.com','Mr. Pilar','ARKUETABIDE,15',31820,'ETXARRI ARANATZ','','',216),('960470707','Tecnocomenius, SLS','tecnocomenius.sefed@comenius.es','Mr. José Mª  Navarro Piqueras','C. Músico Jarque Cualladó, 9',46009,'Valencia','Comunitat Valenciana','https://app.empresaula.com/websites/tecnocomenius-sls',217),('961383014','Sallegest, SLS','sallegest.sefed@gmail.com','Mr. Elena García Barta','C/ San Martín, 56',46980,'Paterna','Comunitat Valenciana','https://app.empresaula.com/websites/sallegest-sls',218),('963918200','AIRXAVIER, SAS','airxavier.sefed@gmail.com','Mr. CRISTINA GARCÍA RAFAEL','C. Pintor López, 8',46003,'Valencia','Comunitat Valenciana','https://app.empresaula.com/websites/airxavier-sas',219),('966527660','ECHEES, S.L.S','echeesshop@gmail.com','Mr. Ricardo Jorge Jordá Miralles','Carrer Societat Unió Musical, 8',3802,'Alcoi','Comunitat Valenciana','',220),('972150567','CAP NORFEU ENTERPRISE, SLS','cfgm2empresa@gmail.com','Mr. Isabel Pérez del Río','C/Gran Via de Pau Casals, 115',17480,'Roses','Catalunya','https://app.empresaula.com/websites/shizen-keshohin',222),('972208316','EURONA, SLS','eurona.etg@gmail.com','Mr. Contact','Cr. Barcelona nº 199',17005,'Girona','Catalunya','',223),('972215481','VIVERD, SLS','viverdsl.sefed@gmail.com','Mr. Arantxa Alvarez Huerta','Av. Montilivi, 125',17003,'GIRONA','Catalunya','https://app.empresaula.com/websites/viverd-sls',224),('972640050','Terracotta Ceràmiques, SLS','terracotta.sefed@ieslabisbal.cat','Mr. Andreu Cardona','C/Eusebi Díaz Costa, 16-38',17100,'LA BISBAL D\'EMPORDÀ','Catalunya','https://terramarqueti.wixsite.com/terracotta-cer-mique',226),('972843965','Comercial Farners, SLS','cfarners.sefed@gmail.com','Mr. Xavier Muñoz Dilmé','Avda. Salvador Espriu, s/n',17430,'SANTA COLOMA DE FARNERS','Catalunya','https://cfarnerssefed.wixsite.com/comercial-farners--s',227),('973231549','Bionatura, SAS','bionatura_asefed@escoladeltreball.cat','Mr. Contact','C/ Pi i Margall, 49-51,    25004 , LLEIDA, CATALUÑA',25004,'LLEIDA','Catalunya','https://app.empresaula.com/websites/bionatura-sas',228),('973242000','ECOFRUITS, S.Coop,S','ecofruits.sefed@gmail.com','Mr. Miquel Menal Castell','C/ Pare Palau, 5',25005,'Lleida','Catalunya','https://ecosefed.wixsite.com/ecofruits',229),('973314948','Menage i Hoteleria Tàrrega, SAS','mhtarrega.sefed@gmail.com','Mr. Núria Esquerra Magrí','Avda. Tarragona s/n,',25300,'TARREGA','Catalunya','https://app.empresaula.com/websites/menage-i-hoteleria-tarrega-sas',230),('973350144','FUSTES LA SALLE, SLS','fusteslasalle.sefed@lasalle.cat','Mr. Montse Rispa Botanch','C/ Sant Joan Baptista de la Salle, 27',25700,'SEU D\'URGELL, LA','berlin','',231),('973600270','Salle Mollerussa, SLS','sallesafemollerussa@gmail.com','Mr. Maria Teresa  Silvestre Solé','C/ Ferrer i Busquets, 17',25230,'MOLLERUSSA','Catalunya','https://sallesafemolleruss.wixsite.com/salle-safe',232),('973651499','Packaging Pallars, SCCLS','packagingpallars@iestremp.cat','Mr. Contact','C/ Bisbe Iglesias, 5',25620,'Tremp','Catalunya','https://m507460.wixsite.com/packaging-pallars',233),('974231097','Aromatic´s, SLS','aromaticssls.sefed@gmail.com','Mr. Jorge Tebas  Medrano','Ramón.J Sender, 4,',22005,'Huesca','Aragón','',234),('974420360','Litera Create, SLS','literacreate@ieslallitera.com','Mr. REBECA SERRAT CLAVER','C/ La Colomina, s/n',22550,'Tamarite de litera','Aragón','https://app.empresaula.com/websites/litera-create-sls',235),('974572429','Reciclax, SAS','reciclax.sefed@gmail.com','Mr. Alicia Luesma Lacasa','C/ de las Torres, s/n',22200,'Sariñena','Aragón','https://reciclaxsefed.wixsite.com/website-1',236),('976167937','Aragaia, SLS','aragaiasls@gmail.com','Mr. Loli Aladren Samper','C/ Agustina de Aragón, s/n',50740,'Fuentes de Ebro','Aragón','https://app.empresaula.com/websites/aragaia-sls',239),('976280054','Agasajo, SAS','agasajo.sefed@gmail.com','Mr. Isabel Baranda Casorrán','C/ París, 1',50003,'Zaragoza','Aragón','https://agasajosas.wixsite.com/website',240),('976418529','Ofiaragón, S.LS.','ofiaragon1.sefed@gmail.com','Mr. MERCEDES BARRANCO NAVARRO','C/ Batalla de Lepanto, 30',50002,'Zaragoza','Aragón','https://sites.google.com/iespabloserrano.com/ofiaragnsls/inicio',241),('976525302','ZAHUTE, S.L.','zahute@iestiemposmodernos.com','Mr. INMA GIMENO NASARRE','C/ Segundo de Chomón, s/n,',50018,'Zaragoza','Aragón','https://zahute.wixsite.com/website (no activa)',242),('976582422','Glycitaca S.L.S.','glycitaca@gmail.com','Mr. Fco. Javier Garrido','Avda. de los Estudiantes, 1',50016,'Zaragoza','Aragón','https://app.empresaula.com/websites/glycitaca',243),('976641192','TARABEBE S.L.S.','tarabebe@iestubalcain.net','Mr. Tarabebe Tarazona','Avda. de la Paz, s/n   50500 TARAZONA  (Zaragoza)',50500,'Tarazona','Aragón','https://tarabebe.wixsite.com/tarabebe',244),('976812480','Lotes Locales, S.L.S.','loteslocales@gmail.com','Mr. Leticia Henar Palacin','Carrera La Hilera, s/n',50100,'La Almunia de Doña Godina (Zaragoza)','Aragón','https://ventasloteslocales.wixsite.com/lotes-locales',245),('976878209','INFOREXEA, SLS','inforexea@iescincovillas.com','Mr. Sonia Pons Lambán','Paseo de la Constitución, 122-126',50600,'Ejea de los Caballeros','Aragón','https://inforexea.wixsite.com/inforexea',238),('977010858','PROCAMP REUS, SAS','procampreus23@gmail.com','Mr. Joan Maria Rovira Rojals','C/ Terol, 1',43206,'REUS','Catalunya','https://procampreus.wixsite.com/website',248),('977240496','ViB Company SLS','vibcompany@vidalibarraquer.net','Mr. Ivan Benito Fuster','Rambla President Lluís Companys, 3',43005,'Tarragona','Catalunya','https://vibcompany235.wixsite.com/vibcompany',249),('977240696','DESK-TGN, SLS','desktgn2sefed@gmail.com','Mr. Carmen Aparicio','Rambla President Companys, 3, TARRAGONA, CATALUNYA',43005,'TARRAGONA','Catalunya','https://desktgn2sefed.wixsite.com/2021-2022',250),('977243103','ORGANIC WAVE, SLS','organicwave@vidalibarraquer.net','Mr. YAGO RIOLA VECIANA','Rambla President Companys, 3',43005,'TARRAGONA','Catalunya','https://organicwave5.wixsite.com/organicwave',247),('977361496','TECNOSEC RB, SLS','recepcio.tecnosecrb@rbiv.cat','Mr. Contact','C/Bertran , 3',43850,'Cambrils','Catalunya','https://vendestecnosecrb.wixsite.com/tecnosecrb',251),('977585851','SEFED Ebre, SAS','comercial.ebre@gmail.com','Mr. Anna Bernardez Fanlo','C/ Nova Estació, 21',43500,'TORTOSA','Catalunya','https://comercialebre.wixsite.com/sefedebre',252),('977700043','NATUR FRESH WORLD, SLS','recepcio_naturfreshworld@iesmontsia.org','Mr. Anna Segura Gironés','C/ Madrid, 35-49',43870,'Amposta','Catalunya','https://marquetingnaturfre.wixsite.com/nature-fresh-world',253),('977768432','ANDOFFICE, SLS','andoffice34@gmail.com','Mr. Contact','Carrer de Baltasar de Toda i de Tàpies, 16',43330,'Riudoms','Catalunya','',254),('978620564','EPISMUDEJAR, SLS','epismudejar@gmail.com','Mr. Mª Ángeles Hernández','C/ Juez Villanueva, 1',44002,'Teruel','Aragón','https://app.empresaula.com/websites/epismudejar-sls',256),('978758050','Suministros Servilimpia.SLS','utrilimpia@iesutrillas.es','Mr. Manuel Rodriguez','C/ Miguel Servet, 4',44760,'Utrillas','Aragón','',257),('978835737','Merchandising Alcañiz','merchandisingalcaniz.sefed@gmail.com','Mr. María Vera Tomeo','C/ José Pardo sastrón, 1, , ALCAÑIZ, ARAGÓN',44600,'Alcañiz','Aragón','',258),('978842162','Decor-ando, SLS','decorando.sefed@gmail.com','Mr. Dina Balaguer Valero','C/ Hermanas Zapata, 8, 44.500 , ANDORRA (TERUEL), ARAGÓN',44500,'Andorra (Teruel)','Aragón','https://decorandosefed.wixsite.com/decorando',255),('980512860','Ofimática Vaguada, S.L.S.','ofivaguada.sefed@gmail.com','Mr. Contact','C/ Villalpando, 11',49005,'Zamora','Castilla y León','https://ofivaguadasefed.wixsite.com/misitio',260),('982828067','A Pinguela Calidade, SLS','pinguelacalidadesls@gmail.com','Mr. ANA BELÉN  LEIRADO MARTÍNEZ','Estrada de Sober, s/n',27400,'MONFORTE DE LEMOS','Galicia','https://pinguelacalidadesl.wixsite.com/website',261),('983148442','GF OFFICE , S.L.S.','gfoffice.sefed@gregoriofer.com','Mr. Inmaculada de Frutos','C/ Gabilondo Nº 23',47007,'Valladolid','Castilla y León','https://namegf2018.wixsite.com/gfoffice',263),('983225901','ARCAS REALES, SAS','arcasreales@outlook.com','Mr. Roberto Bombin Bombín','C/ General Shelly 1',47013,'Valladolid','Castilla y León','https://arcasrealessas.wixsite.com/tienda',264),('983507303','Embalajes Grial, S.A.S.','embgrial@gmail.com','Mr. Angélica Salgado Martín','C/ Ruiz Hernández, 14',47002,'Valladolid','Castilla y León','https://embgrial.wixsite.com/my-site',262),('985848734','Mayoristas Pelayo, SLS','mayoristaspelayosls.sefed@gmail.com','Mr. Ana Rosa Muñiz Suárez','C/ Contranquil, 7',33550,'CANGAS DE ONÍS','Principado de Asturias','https://app.empresaula.com/websites/mayoristas-pelayo-sls',265),('986300012','Circuitos Integrados de Rodeira (CIR), SLS','cir@iesrodeira.gal','Mr. Oscar Perez','Avenida de Ourense, s/n',36940,'Cangas do Morrazo','Galicia','https://app.empresaula.com/websites/cir',266),('986857700','SEOANE DISTRIBUCIONES, SLS','seoane.comercio@gmail.com','Mr. Joaquin Pece Montenegro','Calle Luxemburgo, 1',36162,'Pontevedra','Galicia','',267),('988788912','BioPorto, SLS','bioporto23.24@gmail.com','Mr. Contact','Rúa Luís Trabazos nº 1, 32004, Ourense',32004,'OURENSE','Galicia','https://bioporto2324.wixsite.com/bioporto',268),('999999999','Square Enix','yuni@gmail.com','Yunaleska','to zanarkand',10,'Spira','Del anillo','www. asdfafa.asdf',285);
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'crm'
--
/*!50003 DROP PROCEDURE IF EXISTS `borrar_prefijo_telefono` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_prefijo_telefono`()
BEGIN
	DECLARE done INT DEFAULT 0;
    DECLARE nuevoTel VARCHAR(50);
    DECLARE borrar_cursor CURSOR FOR SELECT phone_number from empresa;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    open borrar_cursor;
    borrar_loop: LOOP
    FETCH borrar_cursor INTO nuevoTel;
    if done then
		leave borrar_loop;
	end if;
    UPDATE empresa SET phone_number = REPLACE(phone_number, '+34', '');
    UPDATE empresa SET phone_number = REPLACE(phone_number, ' ', '');
    end loop;
    close borrar_cursor; 

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `borrar_telefonos_Longitud_erronea` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_telefonos_Longitud_erronea`()
BEGIN
DECLARE done INT DEFAULT 0;
    DECLARE nuevoTel VARCHAR(50);
    DECLARE borrar_cursor CURSOR FOR SELECT phone_number from empresa;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    
    open borrar_cursor;
    borrar_loop: LOOP
    FETCH borrar_cursor INTO nuevoTel;
    if done then
		leave borrar_loop;
	end if;
    If LENGTH(nuevoTel) != 9 THEN
		DELETE FROM empresa WHERE phone_number = nuevoTel;
	END IF;
    end loop;
    close borrar_cursor; 

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `new_procedure` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `new_procedure`()
BEGIN
DECLARE done INT DEFAULT 0;
    DECLARE nuevoTel VARCHAR(50);
    DECLARE borrar_cursor CURSOR FOR SELECT phone_number from empresa;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    
    open borrar_cursor;
    borrar_loop: LOOP
    FETCH borrar_cursor INTO nuevoTel;
    if done then
		leave borrar_loop;
	end if;
    If LENGTH(nuevoTel) != 9 THEN
		DELETE FROM empresa WHERE phone_number = nuevoTel;
	END IF;
    end loop;
    close borrar_cursor; 

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registrar_accion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrar_accion`(IN tipo VARCHAR(20), IN fecha DATE, IN descripcion VARCHAR(255), IN comercial VARCHAR(20), IN phone VARCHAR(50), out id int)
BEGIN
	INSERT INTO accion(tipo, fecha, descripcion, comercial, empresa) VALUES(tipo, fecha, descripcion, comercial, phone);
    SET id = last_insert_id();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registrar_accion_email` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrar_accion_email`(IN tipo VARCHAR(20), IN fecha DATE, IN descripcion VARCHAR(255), IN comercial VARCHAR(20), IN phone VARCHAR(50), IN email VARCHAR(100), IN esPromocion TINYINT(1))
BEGIN
	DECLARE id INT;
    DECLARE contador int;
    DECLARE nuevoContador int;
    SET contador = (SELECT COUNT(*) FROM accion_email);
    start transaction;
	CALL registrar_accion(tipo, fecha, descripcion, comercial, phone, id);
    INSERT INTO accion_email(accionId, email, es_promocion) VALUES(id, email, esPromocion);
    set nuevoContador =  (SELECT COUNT(*) from accion_email);
    if contador = nuevoContador THEN
		rollback;
	else
		commit;
	end if;
    

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registrar_accion_llamada` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrar_accion_llamada`(IN tipo VARCHAR(20), IN fecha DATE, IN descripcion VARCHAR(255), IN comercial VARCHAR(20), IN phone VARCHAR(50), IN acuerdos VARCHAR(255))
BEGIN
	DECLARE id INT;
	DECLARE contador int;
    DECLARE nuevoContador int;
    SET contador = (SELECT COUNT(*) FROM accion_telefono);
    start transaction;
	CALL registrar_accion(tipo, fecha, descripcion, comercial, phone, id);
    INSERT INTO accion_telefono(accionId, numero_telefono, acuerdos) VALUES(id, phone, acuerdos);
	set nuevoContador =  (SELECT COUNT(*) from accion_telefono);
    if contador = nuevoContador THEN
		rollback;
	else
		commit;
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registrar_accion_visita` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrar_accion_visita`(IN tipo VARCHAR(20), IN fecha DATE, IN descripcion VARCHAR(255), IN comercial VARCHAR(20), IN phone VARCHAR(50), IN acuerdos VARCHAR(255), IN direccion varchar(60))
BEGIN
	DECLARE id INT;
    DECLARE contador int;
    DECLARE nuevoContador int;
    SET contador = (SELECT COUNT(*) FROM accion_visita);
    start transaction;
	CALL registrar_accion(tipo, fecha, descripcion, comercial, phone, id);
    INSERT INTO accion_visita(accionId, direccion, acuerdos) VALUES(id, direccion, acuerdos);
	set nuevoContador =  (SELECT COUNT(*) from accion_visita);
    if contador = nuevoContador THEN
		rollback;
	else 
		commit;
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-31  0:11:31
