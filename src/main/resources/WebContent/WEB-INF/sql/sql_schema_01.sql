-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 06, 2018 at 01:59 PM
-- Server version: 5.5.58-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `farooqfe_carhebti`
--

-- --------------------------------------------------------

--
-- Table structure for table `config`
--

CREATE TABLE IF NOT EXISTS `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `odometer` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `config`
--

INSERT INTO `config` (`id`, `odometer`) VALUES
(1, 15450);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE IF NOT EXISTS `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `periodicity` int(1) NOT NULL COMMENT `0 for odometer, 1 for date`,
  `frequence` decimal(16,0) NOT NULL,
  `last_event` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notification_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Service`
--

CREATE TABLE IF NOT EXISTS `Service` (
  `id_service` int(11) NOT NULL AUTO_INCREMENT,
  `date_service` date NOT NULL,
  `odometer_service` decimal(16,0) DEFAULT NULL,
  `provider_service` varchar(50) NOT NULL,
  `qte_service` decimal(16,0) DEFAULT NULL,
  `type_service` int(11) NOT NULL,
  `comment_service` varchar(300) DEFAULT NULL,
  `cost` decimal(16,0) NOT NULL,
  PRIMARY KEY (`id_service`),
  KEY `fk_Service_type_idx` (`type_service`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT=`All events here` AUTO_INCREMENT=5 ;

--
-- Dumping data for table `Service`
--

INSERT INTO `Service` (`id_service`, `date_service`, `odometer_service`, `provider_service`, `qte_service`, `type_service`, `comment_service`, `cost`) VALUES
(1, `2017-05-16`, 15000, `Station`, 3, 2, `a Comment`, 0),
(2, `2018-02-23`, NULL, `Esso`, 20, 1, `Operation`, 0),
(3, `2018-02-23`, NULL, `Shell`, 35, 1, `Second`, 0),
(4, `2018-02-23`, NULL, `COMAR`, NULL, 3, `Operation`, 0);

-- --------------------------------------------------------

--
-- Table structure for table `Type`
--

CREATE TABLE IF NOT EXISTS `Type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `provider_column_name` varchar(50) NOT NULL,
  `odometer` tinyint(1) NOT NULL,
  `qte` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `Type`
--

INSERT INTO `Type` (`id`, `name`, `provider_column_name`, `odometer`, `qte`) VALUES
(1, `Refuel`, `Station`, 0, 1),
(2, `Oil Change`, `Brand`, 1, 0),
(3, `Insurance`, `Insurer`, 0, 0),
(11, `Battery`, `Trade`, 1, 0),
(12, `Brakes change`, `FIAT House`, 1, 0),
(17, `Type To Delete`, `ToDelete`, 1, 0),
(18, `Other type`, `pro`, 1, 0);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `fk_notification_type` FOREIGN KEY (`type`) REFERENCES `Type` (`id`);

--
-- Constraints for table `Service`
--
ALTER TABLE `Service`
  ADD CONSTRAINT `fk_Service_type` FOREIGN KEY (`type_service`) REFERENCES `Type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;