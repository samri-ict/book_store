-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 30, 2017 at 02:49 PM
-- Server version: 5.1.39
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `lbrydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

CREATE TABLE IF NOT EXISTS `authors` (
  `AUTHOR_ID` int(10) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `MIDDELNAME` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `LASTNAME` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `GENDER` enum('Male','Female') COLLATE utf8_unicode_ci DEFAULT NULL,
  `NATIONALITY` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`AUTHOR_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=9 ;

--
-- Dumping data for table `authors`
--

INSERT INTO `authors` (`AUTHOR_ID`, `FIRSTNAME`, `MIDDELNAME`, `LASTNAME`, `GENDER`, `NATIONALITY`) VALUES
(1, 'Baalu', 'Girma', '', 'Male', 'Ethiopian'),
(2, 'Wole', 'Babatunde', 'Soyinka', 'Male', 'Nigerians'),
(3, 'Ernest', 'Miller', 'Hemingway', 'Male', 'Americans'),
(4, 'Marguerite', 'Annie', 'Angelou', 'Female', 'Americans'),
(5, 'Mamo', 'Wedineh', '', 'Male', 'Ethiopians'),
(6, 'Albert', 'Chenualumogu', 'Achebe', 'Male', 'Nigerians'),
(7, 'Chloe', 'Ardelia', 'Wofford', 'Female', 'Americans'),
(8, 'Hadis', 'Alemayehu', '', 'Male', 'Ethiopians');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE IF NOT EXISTS `books` (
  `BOOK_ID` int(10) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `AUTHOR_ID` int(10) DEFAULT NULL,
  `SUBJECT_ID` int(10) DEFAULT NULL,
  `YEARPUBLISHED` date NOT NULL,
  `ISBN` char(13) COLLATE utf8_unicode_ci NOT NULL,
  `SHELFSTATUS` enum('Reserved','CheckedOut','CheckedIn') COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`BOOK_ID`),
  KEY `AUTHOR_ID` (`AUTHOR_ID`),
  KEY `SUBJECT_ID` (`SUBJECT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`BOOK_ID`, `TITLE`, `AUTHOR_ID`, `SUBJECT_ID`, `YEARPUBLISHED`, `ISBN`, `SHELFSTATUS`) VALUES
(1, 'Fikir Eske Mekabir', 8, NULL, '1970-02-12', '4785692145697', 'CheckedOut'),
(2, 'Oromay', 1, NULL, '1970-02-12', '4569823871495', 'Reserved'),
(3, 'Arrow of God', 6, 3, '1985-02-12', '4215963281354', 'CheckedOut');

-- --------------------------------------------------------

--
-- Table structure for table `counteractivity`
--

CREATE TABLE IF NOT EXISTS `counteractivity` (
  `ACTIVITYID` int(10) NOT NULL AUTO_INCREMENT,
  `USERID` int(10) NOT NULL,
  `MEMBERID` int(10) NOT NULL,
  `BOOKID` int(10) NOT NULL,
  `ACTIVITYTYPE` enum('Reserve','CheckOut','CheckIn') COLLATE utf8_unicode_ci NOT NULL,
  `ACTIVITYDATE` date NOT NULL,
  PRIMARY KEY (`ACTIVITYID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `counteractivity`
--

INSERT INTO `counteractivity` (`ACTIVITYID`, `USERID`, `MEMBERID`, `BOOKID`, `ACTIVITYTYPE`, `ACTIVITYDATE`) VALUES
(1, 1, 6, 1, 'CheckOut', '2016-12-08'),
(2, 1, 2, 2, 'Reserve', '2016-12-06'),
(3, 1, 2, 2, 'CheckOut', '2016-12-08'),
(4, 1, 2, 2, 'CheckIn', '2016-12-16'),
(5, 1, 1, 3, 'CheckOut', '2017-02-12'),
(6, 1, 2, 2, 'Reserve', '2017-01-10');

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE IF NOT EXISTS `members` (
  `MEMBER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `MIDDELNAME` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `LASTNAME` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `GENDER` enum('Male','Female') COLLATE utf8_unicode_ci DEFAULT NULL,
  `DATESUBCRIBED` date DEFAULT NULL,
  PRIMARY KEY (`MEMBER_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`MEMBER_ID`, `FIRSTNAME`, `MIDDELNAME`, `LASTNAME`, `GENDER`, `DATESUBCRIBED`) VALUES
(1, 'Desta', 'B', 'L', 'Male', '2017-01-04'),
(2, 'Naomi', 'R', 'J', 'Female', '2016-12-07'),
(3, 'Darim', 'K', 'E', 'Male', '2017-01-10'),
(4, 'Valonia', 'P', 'S', 'Female', '2016-11-09'),
(5, 'Maria', 'I', 'M', 'Female', '2017-01-04'),
(6, 'Kim', 'L', 'J', 'Female', '2016-12-06'),
(7, 'abebe', 'abebe', 'abebe', 'Male', '2017-02-12');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `SUBJECT_ID` int(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SUBJECT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`SUBJECT_ID`, `NAME`, `DESCRIPTION`) VALUES
(1, 'Political Philosophy', ''),
(2, 'Botany', ''),
(3, 'Historical Fiction', ''),
(4, 'Romance', ''),
(5, 'Theology', 'theology'),
(6, 'Computer Sci', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `USER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `PASSWORD` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `MEMBER_ID` int(10) DEFAULT NULL,
  `ISACTIVE` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `CANCREATEUSER` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `CANREGISTERMEMBER` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `CANREGISTERAUTHOR` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `CANREGISTERSUBJECT` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `CANREGISTERBOOK` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `CANRESERVEBOOK` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `CANCHECKINBOOK` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `CANCHECKOUTBOOK` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`),
  KEY `MEMBER_ID` (`MEMBER_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`USER_ID`, `USERNAME`, `PASSWORD`, `MEMBER_ID`, `ISACTIVE`, `CANCREATEUSER`, `CANREGISTERMEMBER`, `CANREGISTERAUTHOR`, `CANREGISTERSUBJECT`, `CANREGISTERBOOK`, `CANRESERVEBOOK`, `CANCHECKINBOOK`, `CANCHECKOUTBOOK`) VALUES
(1, 'user', '123', 3, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'),
(2, 'user2', '456', 1, 'Y', 'N', 'N', 'N', 'N', 'N', 'Y', 'Y', 'Y'),
(5, 'user3', '789', NULL, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'N', 'N', 'N'),
(6, 'user4', '753', NULL, 'Y', 'Y', 'Y', 'N', 'N', 'N', 'Y', 'N', 'N');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `books_ibfk_1` FOREIGN KEY (`AUTHOR_ID`) REFERENCES `authors` (`AUTHOR_ID`),
  ADD CONSTRAINT `books_ibfk_2` FOREIGN KEY (`SUBJECT_ID`) REFERENCES `subject` (`SUBJECT_ID`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`MEMBER_ID`) REFERENCES `members` (`MEMBER_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
