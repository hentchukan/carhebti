
--
-- Database: `farooqfe_carhebti`
--

-- --------------------------------------------------------

--
-- Table structure for table `Car`
--

CREATE TABLE IF NOT EXISTS `Car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manifacturer` varchar(30) NOT NULL,
  `trade` varchar(30) NOT NULL,
  `number` varchar(30) NOT NULL,
  `grey_card` varchar(30) NOT NULL,
  `owner` int(11) DEFAULT NULL;
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`,`grey_card`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

ALTER TABLE `Service` ADD COLUMN car int null;
ALTER TABLE `Service` ADD CONSTRAINT FOREIGN KEY (car) REFERENCES Car(id);
