-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

ALTER TABLE `Service`ADD COLUMN `owner` int;
ALTER TABLE `Service`ADD CONSTRAINT FOREIGN KEY (owner) REFERENCES User(Id);

ALTER TABLE `Type`ADD COLUMN `owner` int;
ALTER TABLE `Type`ADD CONSTRAINT FOREIGN KEY (owner) REFERENCES User(Id);

ALTER TABLE `Car`ADD CONSTRAINT FOREIGN KEY (owner) REFERENCES User(Id);
