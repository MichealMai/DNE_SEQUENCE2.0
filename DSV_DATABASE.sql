-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: DNA_Sequence
-- ------------------------------------------------------
-- Server version	5.6.39

use DNA_Sequence;

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
-- Table structure for table `DS_Algorithm`
--

DROP TABLE IF EXISTS `DS_Algorithm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DS_Algorithm` (
  `algid` varchar(100) NOT NULL,
  `algname` varchar(50) DEFAULT NULL,
  `algcommand` varchar(50) DEFAULT NULL,
  `algpath` varchar(200) DEFAULT NULL,
  `algparatable` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`algid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DS_Algorithm`
--

LOCK TABLES `DS_Algorithm` WRITE;
/*!40000 ALTER TABLE `DS_Algorithm` DISABLE KEYS */;
INSERT INTO `DS_Algorithm` VALUES ('8061653e-aa94-42dd-bd43-de939bd6e1d8','silver-clusters-master','python run_all.py','/DNASequenceVerify/WebContent/silver-clusters-master/start','DS_LibsvmParameterCommand');
/*!40000 ALTER TABLE `DS_Algorithm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DS_Experiments`
--

DROP TABLE IF EXISTS `DS_Experiments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DS_Experiments` (
  `expid` varchar(100) NOT NULL,
  `algid` varchar(100) DEFAULT NULL,
  `userid` varchar(100) DEFAULT NULL,
  `inputfileid` varchar(1000) DEFAULT NULL,
  `expname` varchar(50) DEFAULT NULL,
  `phase` varchar(50) NOT NULL DEFAULT '1',
  `fn` varchar(100) NOT NULL DEFAULT '1',
  `fp` varchar(100) NOT NULL DEFAULT '1',
  `phaseonefileid` varchar(1000) DEFAULT NULL,
  `phasetwofileid` varchar(1000) DEFAULT NULL,
  `phasethreefileid` varchar(1000) DEFAULT NULL,
  `phasefourfileid` varchar(1000) DEFAULT NULL,
  `motifsnumber` varchar(50) NOT NULL DEFAULT '1',
  `featureselection` varchar(50) NOT NULL DEFAULT '1',
  `extrafeature` varchar(50) NOT NULL DEFAULT 'false',
  `maximummotifs` varchar(100) DEFAULT NULL,
  `kfold` varchar(100) DEFAULT NULL,
  `cvalue` varchar(100) DEFAULT NULL,
  `numberofsequences` varchar(100) DEFAULT NULL,
  `desiredclass` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`expid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DS_Experiments`
--

LOCK TABLES `DS_Experiments` WRITE;
/*!40000 ALTER TABLE `DS_Experiments` DISABLE KEYS */;
INSERT INTO `DS_Experiments` VALUES ('ea3e526f-8f51-4cc8-9771-3d0fc3fd086d','8061653e-aa94-42dd-bd43-de939bd6e1d8','6c33f360-7667-491d-9822-4bef6c8a5513','44195a30-007a-4f3d-bda5-b7e775cd8465','trest','1','1','1',NULL,NULL,NULL,NULL,'1','1','false',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `DS_Experiments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DS_Files`
--

DROP TABLE IF EXISTS `DS_Files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DS_Files` (
  `fileid` varchar(100) NOT NULL,
  `filename` varchar(80) DEFAULT NULL,
  `filepath` varchar(200) DEFAULT NULL,
  `userid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`fileid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DS_Files`
--

LOCK TABLES `DS_Files` WRITE;
/*!40000 ALTER TABLE `DS_Files` DISABLE KEYS */;
INSERT INTO `DS_Files` VALUES ('211cfb86-d920-433f-8336-4e1aa4a826da','integrated-intensity.csv','/DNASequenceVerify/WebContent/silver-clusters-master/data/input_data/integrated-intensity.csv','6c33f360-7667-491d-9822-4bef6c8a5513'),('44195a30-007a-4f3d-bda5-b7e775cd8465','peak-intensity.csv','/DNASequenceVerify/WebContent/silver-clusters-master/data/input_data/peak-intensity.csv','6c33f360-7667-491d-9822-4bef6c8a5513'),('e1edd6a2-2bd2-4087-a6d5-867dc3b740e0','integrated-intensity.csv','/DNASequenceVerify/WebContent/silver-clusters-master/data/input_data/integrated-intensity.csv','6c33f360-7667-491d-9822-4bef6c8a5513');
/*!40000 ALTER TABLE `DS_Files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DS_LibsvmParameterValue`
--

DROP TABLE IF EXISTS `DS_LibsvmParameterValue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DS_LibsvmParameterValue` (
  `thresholdhighvalue` varchar(20) DEFAULT NULL,
  `thresholdlowvalue` varchar(20) DEFAULT NULL,
  `thresholdhigh` varchar(10) DEFAULT NULL,
  `thresholdlow` varchar(10) DEFAULT NULL,
  `verbose` varchar(10) DEFAULT NULL,
  `crossvalidtiontest` varchar(10) DEFAULT NULL,
  `generatenewfeatures` varchar(10) DEFAULT NULL,
  `expid` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DS_LibsvmParameterValue`
--

LOCK TABLES `DS_LibsvmParameterValue` WRITE;
/*!40000 ALTER TABLE `DS_LibsvmParameterValue` DISABLE KEYS */;
INSERT INTO `DS_LibsvmParameterValue` VALUES ('0','0','0','0','0','0','0','ea3e526f-8f51-4cc8-9771-3d0fc3fd086d');
/*!40000 ALTER TABLE `DS_LibsvmParameterValue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DS_ParameterCommand`
--

DROP TABLE IF EXISTS `DS_ParameterCommand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DS_ParameterCommand` (
  `paraid` varchar(100) NOT NULL,
  `algid` varchar(100) NOT NULL,
  `paraname` varchar(50) NOT NULL,
  `paratype` varchar(10) DEFAULT NULL,
  `paracommand` varchar(10) DEFAULT NULL,
  `statement` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`paraid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DS_ParameterCommand`
--

LOCK TABLES `DS_ParameterCommand` WRITE;
/*!40000 ALTER TABLE `DS_ParameterCommand` DISABLE KEYS */;
INSERT INTO `DS_ParameterCommand` VALUES ('3317bcaf-7622-475b-ab3e-21d8e2dbb0b3','8061653e-aa94-42dd-bd43-de939bd6e1d8','verbose','select','-v','same as before but with verbose output'),('5797cdf3-a50a-4fa4-877c-4272b4255bc6','8061653e-aa94-42dd-bd43-de939bd6e1d8','thresholdlowvalue','value',NULL,'below value'),('5de0f471-909e-4df7-9550-f9e28267ee96','8061653e-aa94-42dd-bd43-de939bd6e1d8','thresholdhigh','select','-th','above value'),('7453c83e-1624-494d-b43b-8fceb4850ce3','8061653e-aa94-42dd-bd43-de939bd6e1d8','crossvalidtiontest','select','-c','perform cross validation test on features from training file and stop'),('8e3c1c62-b4dd-4d7c-9954-017a169216fc','8061653e-aa94-42dd-bd43-de939bd6e1d8','generatenewfeatures','select','-g','also generate new features'),('b5844e3c-86e7-4705-b3d9-99d42e9c8f00','8061653e-aa94-42dd-bd43-de939bd6e1d8','thresholdlow','select','-tl','Need below value or not'),('cae7bcf6-546c-4819-a4d5-6180f2eba122','8061653e-aa94-42dd-bd43-de939bd6e1d8','thresholdhighvalue','value',NULL,'Need above value or not');
/*!40000 ALTER TABLE `DS_ParameterCommand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DS_user`
--

DROP TABLE IF EXISTS `DS_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DS_user` (
  `userid` varchar(100) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `isAdmin` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DS_user`
--

LOCK TABLES `DS_user` WRITE;
/*!40000 ALTER TABLE `DS_user` DISABLE KEYS */;
INSERT INTO `DS_user` VALUES ('1d121540-925b-406e-895e-3803498579dc','Micheal','123','43rwefsd@albany.edu','1400 Cold street','417493573',NULL,NULL,'2018-05-17 02:15:28','no'),('6c33f360-7667-491d-9822-4bef6c8a5513','admin','123','hello123@163.com','1400 hello street apt','5185952345','1','2018-04-19 18:36:07','2018-05-17 02:18:51','yes');
/*!40000 ALTER TABLE `DS_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-30 22:55:26
