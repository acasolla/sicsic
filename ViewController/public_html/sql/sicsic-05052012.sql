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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barche`
--

/*!40000 ALTER TABLE `barche` DISABLE KEYS */;
INSERT INTO `barche` (`id`,`nomeBarca`,`siglaIscrizione`,`costruitaDa`,`serialeScafo`,`lunghezza`,`pescaggio`,`motorizzazione`,`capacitaGasolio`,`numMinEquipaggio`,`numMaxEquipaggio`,`bandiera`,`annoCostruzione`,`tipoModello`,`certificazioneCE`,`larghezza`,`dislocamento`,`matricoleMotori`,`capacitaAcqua`,`limitiNavigazione`,`assPolizzaCorpi`,`assPolizzaRC`,`numeroRegistro`,`note`) VALUES 
 (30,'MIGSa','NA 7062 Da','Cantieritecnicaa','a',5,5,'Volvo Penta 2 x 306 HPa',5,5,5,'Italianaa',5,'Speranzaa','a',5,5,'a',5,'Oltre 6 migliaa','a','ascad. 30/05/2011','a',NULL),
 (32,'pluto',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (33,'aaaa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (34,'bbbb',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (35,'test',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
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
  `sedeLegale` varchar(3000) DEFAULT NULL,
  `partitaIva` varchar(300) DEFAULT NULL,
  `ragioneSociale` varchar(300) DEFAULT NULL,
  `note` longtext,
  `tipoDocumento` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clienti`
--

/*!40000 ALTER TABLE `clienti` DISABLE KEYS */;
INSERT INTO `clienti` (`id`,`nome`,`cognome`,`natoIl`,`natoA`,`indirizzo`,`citta`,`provincia`,`documento`,`telefono`,`fax`,`email`,`sedeLegale`,`partitaIva`,`ragioneSociale`,`note`,`tipoDocumento`) VALUES 
 (8,'Alessandro','Abbisogno detto o cazz',NULL,'Vallo dellla Licania','via Parsano, 25','Sorrento','Napoli','CI-83884jjd99394','+39 3393848193','+39 0817875972','alexabbi@gmail.com','',NULL,'',NULL,NULL),
 (10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456','132','322','1212','Marina grande Sorrento',NULL,'Nautica SIC SIC',NULL,NULL),
 (11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Mercogliano',NULL,'SOUL',NULL,NULL),
 (12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'New York',NULL,'NIKE',NULL,NULL),
 (13,'Alessandro','Casolla',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Carta di identità'),
 (14,'peppe','ilbello','20120502','napoli','via Aniello del sabato','napoli','na','5555r5r5','8948548484','8484848448','peppeilbello.it',NULL,NULL,NULL,NULL,'Passaporto'),
 (15,'aa','a','20120517','a',NULL,NULL,NULL,'as',NULL,NULL,NULL,NULL,'3232131','pippo',NULL,'CF/P.Iva'),
 (16,'aas',NULL,'20120517',NULL,NULL,NULL,NULL,'scxczczxc',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CF/P.Iva'),
 (17,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'asasa','rrrr',NULL,NULL),
 (18,'ssaas',NULL,'20120517',NULL,NULL,NULL,NULL,'847234320',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Carta di identità'),
 (19,'sasadadadasd',NULL,'20120517',NULL,NULL,NULL,NULL,'oorooorooro',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Carta di identità'),
 (20,'',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,'12121212121','sssssssssssssssssssss',NULL,'CF/P.iva'),
 (21,'tony',NULL,'20120516',NULL,NULL,NULL,NULL,'09393209130912',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Patente nautica');
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `noleggio`
--

/*!40000 ALTER TABLE `noleggio` DISABLE KEYS */;
INSERT INTO `noleggio` (`id`,`idBarca`,`idCliente`,`idSkipper`,`importoNolo`,`importoCarburante`,`pagamento`,`note`,`dataNoleggio`) VALUES 
 (12,31,8,18,11150.49,75,0,NULL,'20120316'),
 (19,30,8,18,3,12121,1,NULL,'20120302'),
 (28,32,13,18,3,12121,1,NULL,'20120411'),
 (29,33,10,18,1223,67,1,'assordo','20120425');
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
  `rilasciataDa` longtext,
  `note` longtext,
  `cognome` varchar(45) DEFAULT NULL,
  `indirizzo` varchar(300) DEFAULT NULL,
  `rilasciataIl` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skipper`
--

/*!40000 ALTER TABLE `skipper` DISABLE KEYS */;
INSERT INTO `skipper` (`id`,`nome`,`natoIl`,`natoA`,`residenza`,`numPatenteNautica`,`rilasciataDa`,`note`,`cognome`,`indirizzo`,`rilasciataIl`) VALUES 
 (18,'Maurizio','19790612','Vico Equense','Sorrento','n.151396 ','rilasciata dalla Capitaneria di Porto di Castellammare di Stabia il 23.06.2002',NULL,'Gargiulo','Via degli Aranci 139',NULL),
 (20,'i','20120509','n','n','n','n',NULL,'i','n',NULL),
 (21,'aa','20120525','sos','aa','a','a',NULL,'aa','a','20120516');
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
  `note` longtext,
  `pagamento` tinyint(1) DEFAULT NULL,
  `idBarca` int(10) unsigned DEFAULT NULL,
  `idSkipper` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `spese`
--

/*!40000 ALTER TABLE `spese` DISABLE KEYS */;
INSERT INTO `spese` (`id`,`dataSpesa`,`descrizione`,`importoSpese`,`note`,`pagamento`,`idBarca`,`idSkipper`) VALUES 
 (53,'20120423','qwqw',3,'wqwqwq',1,30,0),
 (54,'20120405','aòe',1,'notenote',0,30,0),
 (55,'20120407','aòe',2,'ASDDA',0,31,0),
 (58,'20120426','asaa',4,NULL,1,35,0),
 (59,'20120412','ddsdd',1223.5,'hhhh',0,30,0),
 (60,'20120419','Alimentare',2121,'a',1,30,0),
 (61,'20120413','Cancelleria',222,'asa',0,30,0),
 (62,'20120429','Accessori nautici',152,'iiii',0,30,0);
/*!40000 ALTER TABLE `spese` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
