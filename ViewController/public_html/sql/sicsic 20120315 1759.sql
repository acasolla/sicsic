-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.20


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema nauticasicsic
--

CREATE DATABASE IF NOT EXISTS nauticasicsic;
USE nauticasicsic;

--
-- Definition of table `barche`
--

DROP TABLE IF EXISTS `barche`;
CREATE TABLE `barche` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomeBarca` varchar(45) DEFAULT NULL,
  `siglaIscrizione` varchar(45) DEFAULT NULL,
  `costruitaDa` varchar(45) DEFAULT NULL,
  `serialeScafo` varchar(45) DEFAULT NULL,
  `lunghezza` double DEFAULT NULL,
  `pescaggio` double DEFAULT NULL,
  `motorizzazione` varchar(45) DEFAULT NULL,
  `capacitaGasolio` double DEFAULT NULL,
  `numMinEquipaggio` int(10) DEFAULT NULL,
  `numMaxEquipaggio` int(10) DEFAULT NULL,
  `bandiera` varchar(45) DEFAULT NULL,
  `annoCostruzione` int(10) unsigned DEFAULT NULL,
  `tipoModello` varchar(200) DEFAULT NULL,
  `certificazioneCE` varchar(45) DEFAULT NULL,
  `larghezza` double DEFAULT NULL,
  `dislocamento` double DEFAULT NULL,
  `matricoleMotori` varchar(45) DEFAULT NULL,
  `capacitaAcqua` double DEFAULT NULL,
  `limitiNavigazione` varchar(45) DEFAULT NULL,
  `assPolizzaCorpi` varchar(45) DEFAULT NULL,
  `assPolizzaRC` varchar(45) DEFAULT NULL,
  `numeroRegistro` varchar(45) DEFAULT NULL,
  `note` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barche`
--

/*!40000 ALTER TABLE `barche` DISABLE KEYS */;
INSERT INTO `barche` (`id`,`nomeBarca`,`siglaIscrizione`,`costruitaDa`,`serialeScafo`,`lunghezza`,`pescaggio`,`motorizzazione`,`capacitaGasolio`,`numMinEquipaggio`,`numMaxEquipaggio`,`bandiera`,`annoCostruzione`,`tipoModello`,`certificazioneCE`,`larghezza`,`dislocamento`,`matricoleMotori`,`capacitaAcqua`,`limitiNavigazione`,`assPolizzaCorpi`,`assPolizzaRC`,`numeroRegistro`,`note`) VALUES 
 (30,'MIGS','NA 7062 D','Cantieritecnica',NULL,11,0.8,'Volvo Penta 2 x 306 HP',1200,1,10,'Italiana',1965,'Speranza',NULL,NULL,14.3,NULL,300,'Oltre 6 miglia',NULL,'Groupama ass. 312/90/023914 scad. 30/05/2011',NULL,NULL);
/*!40000 ALTER TABLE `barche` ENABLE KEYS */;


--
-- Definition of table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
CREATE TABLE `calendar` (
  `ID` varchar(200) NOT NULL,
  `PROVIDER` varchar(100) NOT NULL,
  `START_DATE` date NOT NULL,
  `END_DATE` date NOT NULL,
  `DESCRIPTION` varchar(200) NOT NULL,
  `IDBARCA` int(10) unsigned DEFAULT NULL,
  `IDSKIPPER` int(10) unsigned DEFAULT NULL,
  `IDCLIENTE` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `calendar`
--

/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;


--
-- Definition of table `clienti`
--

DROP TABLE IF EXISTS `clienti`;
CREATE TABLE `clienti` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(300) DEFAULT NULL,
  `cognome` varchar(300) DEFAULT NULL,
  `natoIl` varchar(45) DEFAULT NULL,
  `natoA` varchar(45) DEFAULT NULL,
  `indirizzo` varchar(300) DEFAULT NULL,
  `citta` varchar(300) DEFAULT NULL,
  `provincia` varchar(300) DEFAULT NULL,
  `documento` varchar(300) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `email` varchar(300) DEFAULT NULL,
  `sedeLegale` varchar(300) DEFAULT NULL,
  `partitaIva` varchar(300) DEFAULT NULL,
  `ragioneSociale` varchar(300) DEFAULT NULL,
  `note` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clienti`
--

/*!40000 ALTER TABLE `clienti` DISABLE KEYS */;
INSERT INTO `clienti` (`id`,`nome`,`cognome`,`natoIl`,`natoA`,`indirizzo`,`citta`,`provincia`,`documento`,`telefono`,`fax`,`email`,`sedeLegale`,`partitaIva`,`ragioneSociale`,`note`) VALUES 
 (8,'Alessandro','Abbisogno','20120315','Vallo dellla Licania','via Parsano, 25','Sorrento','Napoli','CI-83884jjd99394','+39 3393848193','+39 0817875972','alexabbi@gmail.com','via Margherita, 12',NULL,'AB-DREAM',NULL),
 (9,'Alessandro','Casolla','19780318','Napoli','via Roma, 2','Napoli','Napoli','PP-irbsifn','+39 12345678','+39 98765433','casolla@gmail.com','via Mimosa, 45',NULL,'A-CASOLLA',NULL);
/*!40000 ALTER TABLE `clienti` ENABLE KEYS */;


--
-- Definition of table `noleggio`
--

DROP TABLE IF EXISTS `noleggio`;
CREATE TABLE `noleggio` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idBarca` int(10) unsigned DEFAULT NULL,
  `idCliente` int(10) unsigned DEFAULT NULL,
  `idSkipper` int(10) unsigned DEFAULT NULL,
  `importoNolo` double DEFAULT NULL,
  `importoCarburante` double DEFAULT NULL,
  `pagamento` tinyint(1) DEFAULT NULL,
  `note` longtext,
  `dataNoleggio` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `noleggio`
--

/*!40000 ALTER TABLE `noleggio` DISABLE KEYS */;
INSERT INTO `noleggio` (`id`,`idBarca`,`idCliente`,`idSkipper`,`importoNolo`,`importoCarburante`,`pagamento`,`note`,`dataNoleggio`) VALUES 
 (1,1,0,0,121212,12121,0,'adasdas','20120315'),
 (2,10,0,0,3,3,0,'asasa','20120322'),
 (3,10,0,0,3,12121,0,'asasa','20120315'),
 (4,13,0,0,3,12121,0,'asasa','20120315'),
 (5,13,0,0,3,12121,0,'asasa','20120315'),
 (6,9,0,0,1111,1111,0,'wqwqwq','20120323');
/*!40000 ALTER TABLE `noleggio` ENABLE KEYS */;


--
-- Definition of table `skipper`
--

DROP TABLE IF EXISTS `skipper`;
CREATE TABLE `skipper` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `natoIl` varchar(45) DEFAULT NULL,
  `natoA` varchar(45) DEFAULT NULL,
  `residenza` varchar(300) DEFAULT NULL,
  `numPatenteNautica` varchar(45) DEFAULT NULL,
  `rilasciataDa` varchar(45) DEFAULT NULL,
  `note` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skipper`
--

/*!40000 ALTER TABLE `skipper` DISABLE KEYS */;
INSERT INTO `skipper` (`id`,`nome`,`natoIl`,`natoA`,`residenza`,`numPatenteNautica`,`rilasciataDa`,`note`) VALUES 
 (1,'vincenzo','2121','2121',NULL,'ewew','qwew',''),
 (2,'Alessandro','212121','sorrento','napoli','2121212','carlo',NULL),
 (3,'vincenzo','2121','2121',NULL,'ewew',NULL,NULL),
 (4,'Default','01012000','Sorrento','via aa','3874848448','Napoli',NULL);
/*!40000 ALTER TABLE `skipper` ENABLE KEYS */;


--
-- Definition of table `spese`
--

DROP TABLE IF EXISTS `spese`;
CREATE TABLE `spese` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dataSpesa` varchar(45) DEFAULT NULL,
  `descrizione` varchar(300) DEFAULT NULL,
  `importoSpese` double DEFAULT NULL,
  `barca` varchar(45) DEFAULT NULL,
  `note` longtext,
  `pagamento` tinyint(1) DEFAULT NULL,
  `idBarca` int(10) DEFAULT NULL,
  `idSkipper` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `spese`
--

/*!40000 ALTER TABLE `spese` DISABLE KEYS */;
INSERT INTO `spese` (`id`,`dataSpesa`,`descrizione`,`importoSpese`,`barca`,`note`,`pagamento`,`idBarca`,`idSkipper`) VALUES 
 (1,'20120101','aa',100,'tornado','',0,NULL,NULL),
 (2,'20120201','bb',200,'gommone','',1,NULL,NULL),
 (3,'20120210','cc',300,'gommone',NULL,1,NULL,NULL),
 (4,'20120311','dd',200,'gozzo',NULL,1,NULL,NULL),
 (5,'20120313','ee',100,'gozzo',NULL,0,NULL,NULL),
 (6,'20120305','ff',220,'gommone',NULL,0,NULL,NULL),
 (7,'20120401','gg',400,'tornado',NULL,0,NULL,NULL),
 (8,'20120412','hh',300,'gozzo',NULL,1,NULL,NULL),
 (9,'20120414','ii',400,'gozzo',NULL,1,NULL,NULL),
 (10,'20120501','ll',300,'tornado',NULL,1,NULL,NULL),
 (11,'20120303','aòe',121212,'tornado','jhhkjhk,j',0,0,0),
 (12,'20120302','aòe',900,'tornado','',0,0,0),
 (13,'20120302','aòe',900,'tornado','oioi',0,0,0),
 (14,'20120302','aòe',900,'tornado','oioi',0,0,0),
 (15,'20120302','aòe',900,'tornado','oioi',0,0,0),
 (16,'20120302','aòe',900,'tornado','',0,0,0),
 (17,'20120301','weqweqw',89.98,'tornado','dfafeqwr',0,0,0),
 (18,'20120301','weqweqw',90.76,'tornado','dfafeqwr',0,0,0),
 (19,'20120322','qwqwr',122.77,'tornado','waqerqweqw',0,0,0),
 (20,'20120322','qwqwr',12.3,'tornado','waqerqweqw',0,0,0),
 (21,'20120322','qwqwr',12.45,'tornado','waqerqweqw',0,0,0),
 (22,'20120322','qwqwr',999.99,'tornado','waqerqweqw',0,0,0),
 (23,'20120322','qwqwr',12.12,'tornado','waqerqweqw',0,0,0),
 (24,'20120322','qwqwr',12313.12,'tornado','waqerqweqw',0,0,0),
 (25,'20120322','qwqwr',122,'tornado','waqerqweqw',0,0,0),
 (26,NULL,NULL,23123,NULL,'',0,0,0),
 (27,NULL,NULL,23123,NULL,'',0,0,0),
 (28,'20120305','aaaaaa',23123,NULL,'',0,0,0),
 (29,'20120305','aaaaaa',23123,'tornado','',0,0,0),
 (30,NULL,NULL,NULL,NULL,'',0,0,0),
 (31,NULL,NULL,NULL,NULL,'',0,0,0),
 (32,NULL,NULL,312312312,NULL,'',0,0,0),
 (33,'20120322','aaaaaaaa',NULL,NULL,'',0,0,0),
 (34,'20120315','qwqwr',999,'tornado','',0,0,0),
 (35,'20120315','asasa',22121,'sss','ssaas',0,0,0),
 (36,'20120315','qwqwr',NULL,'tornado',NULL,0,0,0),
 (37,NULL,NULL,12.12,NULL,NULL,0,0,0);
/*!40000 ALTER TABLE `spese` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
