-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generation Time: Sep 17, 2015 at 05:39 PM
-- Server version: 5.0.51
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Database: `afaq`
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `buy_bills`
-- 

CREATE TABLE `buy_bills` (
  `id` bigint(20) NOT NULL auto_increment,
  `bill_no` longtext,
  `item` text NOT NULL,
  `com_name` longtext,
  `tot_price` double default NULL,
  `paid` double default NULL,
  `change` double default NULL,
  `bill_date` longtext,
  `user_name` longtext,
  `Comment` text NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `buy_bills_IX_buy_bills` (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

-- 
-- Dumping data for table `buy_bills`
-- 

INSERT INTO `buy_bills` VALUES (1, '1', '', NULL, NULL, NULL, NULL, NULL, NULL, '');

-- --------------------------------------------------------

-- 
-- Table structure for table `check`
-- 

CREATE TABLE `check` (
  `id` int(11) NOT NULL auto_increment,
  `user` varchar(50) NOT NULL,
  `name_check` text NOT NULL,
  `Date_out` text NOT NULL,
  `Date_in` text NOT NULL,
  `state` text NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

-- 
-- Dumping data for table `check`
-- 

INSERT INTO `check` VALUES (1, 'تاجر', 'شةق', '2015-09-02', '2015-09-16', 'تم', 150);
INSERT INTO `check` VALUES (2, 'تاجر', 'nmbnmbnm', '2015-09-08', '2015-09-09', 'لم يستلم', 150);

-- --------------------------------------------------------

-- 
-- Table structure for table `company`
-- 

CREATE TABLE `company` (
  `id` bigint(20) NOT NULL auto_increment,
  `com_name` longtext,
  `address` longtext,
  `tel` longtext,
  `note` longtext,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

-- 
-- Dumping data for table `company`
-- 

INSERT INTO `company` VALUES (1, 'تاجر', '', '', '');

-- --------------------------------------------------------

-- 
-- Table structure for table `customer`
-- 

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL auto_increment,
  `cus_name` longtext,
  `address` longtext,
  `tel` longtext,
  `note` longtext,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- 
-- Dumping data for table `customer`
-- 

INSERT INTO `customer` VALUES (1, 'عمرو', 'يلل', '111', '');
INSERT INTO `customer` VALUES (2, 'معتز', 'عمرو سعيد', '01226123123', 'سيبسيب');

-- --------------------------------------------------------

-- 
-- Table structure for table `info`
-- 

CREATE TABLE `info` (
  `id` bigint(20) NOT NULL auto_increment,
  `com_name` longtext,
  `address` longtext,
  `tel` longtext,
  `note` longtext,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- 
-- Dumping data for table `info`
-- 

INSERT INTO `info` VALUES (2, 'البدر', 'الماركت العجيزي شارع المنحر امام ش خالد بن الوليد', '01000073157', 'المخبز العجيزى شارع المنحر امام وهدان');

-- --------------------------------------------------------

-- 
-- Table structure for table `sell_bill`
-- 

CREATE TABLE `sell_bill` (
  `id` int(11) NOT NULL auto_increment,
  `bill_no` text NOT NULL,
  `item` text NOT NULL,
  `cus_name` text NOT NULL,
  `tot_price` text NOT NULL,
  `bill_date` text NOT NULL,
  `Comment` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

-- 
-- Dumping data for table `sell_bill`
-- 

INSERT INTO `sell_bill` VALUES (11, '2', 'prod', 'عمرو', '150', '2015-09-07', 'asdfadasdasd');
INSERT INTO `sell_bill` VALUES (6, '2', 'prod1', 'معتز', '500', '2015-09-07', 'cooment');
INSERT INTO `sell_bill` VALUES (8, '2', 'ghchg', 'عمرو', '500', '2015-09-07', 'comment');

-- --------------------------------------------------------

-- 
-- Table structure for table `users`
-- 

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_name` longtext,
  `pass` longtext,
  `note` longtext,
  `permission1` longtext,
  `permission2` longtext,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

-- 
-- Dumping data for table `users`
-- 

INSERT INTO `users` VALUES (9, 'aa', 'aa', 'null', 'true', 'true');
