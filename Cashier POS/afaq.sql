-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generation Time: Jul 26, 2015 at 08:11 AM
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
create database afaq;
use afaq;
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

-- 
-- Dumping data for table `buy_bills`
-- 

INSERT INTO `buy_bills` VALUES (1, '1', '', 'مورد 1', 41.5, 41.5, 0, '24-4-2014', 'البدر', '');
INSERT INTO `buy_bills` VALUES (2, '2', '', 'مورد 1 ', 200, 200, 0, '204-4-2014', 'البدر', '');
INSERT INTO `buy_bills` VALUES (3, '2', '', 'مورد 1', 200, 200, 0, '2-4-2014', 'البدر', '');
INSERT INTO `buy_bills` VALUES (4, '2', '', 'مورد 1', 200, 200, 0, '2-4-2014', 'البدر', '');
INSERT INTO `buy_bills` VALUES (5, '2', '', 'مورد 1', 200, 200, 0, '2-4-2014', 'البدر', '');
INSERT INTO `buy_bills` VALUES (6, '25', '', 'مورد 1', 20, 20, 0, '24-4-2014', 'البدر', '');
INSERT INTO `buy_bills` VALUES (7, '25', '', 'مورد 1', 20, 20, 0, '24-4-2014', 'البدر', '');
INSERT INTO `buy_bills` VALUES (8, '26', '', 'مورد 1', 2.5, 2.5, 0, '2014-12-18', 'البدر', '');
INSERT INTO `buy_bills` VALUES (9, '27', '', 'مورد 1', 2.5, 2.5, 0, '2014-12-18', 'البدر', '');
INSERT INTO `buy_bills` VALUES (10, '28', '', 'مورد 1', 2.5, 2.5, 0, '2014-12-18', 'البدر', '');
INSERT INTO `buy_bills` VALUES (11, '5', '', 'مورد 1', 41, 41, 0, NULL, 'البدر', '');

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=63 ;

-- 
-- Dumping data for table `company`
-- 

INSERT INTO `company` VALUES (59, 'مورد 1', 'فغعفعفغع', '654646456', 'غعقفقغقف6');
INSERT INTO `company` VALUES (62, 'hjkjhkjssssh', 'jkjljghjghjhgj', 'ghjghjghj', 'ملاحظات');

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

-- 
-- Dumping data for table `customer`
-- 

INSERT INTO `customer` VALUES (8, 'amr', 'فلاقلففقلق', '456465465', 'عهاعخغعاهعغه');
INSERT INTO `customer` VALUES (9, 'عميل 10', 'فعفغعفع', '32565445', 'ملاحظات');

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
-- Table structure for table `main_rep`
-- 

CREATE TABLE `main_rep` (
  `id` bigint(20) NOT NULL auto_increment,
  `tot_buy` double default NULL,
  `tot_sell` double default NULL,
  `tot_service` double default NULL,
  `tot_buy_ret` double default NULL,
  `tot_sell_ret` double default NULL,
  `tot` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

-- 
-- Dumping data for table `main_rep`
-- 

INSERT INTO `main_rep` VALUES (1, 0, 458, 0, 0, 0, 458);

-- --------------------------------------------------------

-- 
-- Table structure for table `q_bill`
-- 

CREATE TABLE `q_bill` (
  `id` bigint(20) NOT NULL auto_increment,
  `cus_name` longtext,
  `items` longtext,
  `qty` longtext,
  `sell_price` double default NULL,
  `tot_price` double default NULL,
  `sell_date` longtext,
  `note` longtext,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- 
-- Dumping data for table `q_bill`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `sell_bills`
-- 

CREATE TABLE `sell_bills` (
  `id` bigint(20) NOT NULL auto_increment,
  `bill_no` longtext,
  `item` text NOT NULL,
  `cus_name` longtext,
  `tot_price` double default NULL,
  `paid` double default NULL,
  `change` double default NULL,
  `bill_date` longtext,
  `user_name` longtext,
  `Comment` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

-- 
-- Dumping data for table `sell_bills`
-- 

INSERT INTO `sell_bills` VALUES (1, '1', 'موز', 'amr', 10, 8, 2, '24/4/1992', NULL, 'No Comment');

-- --------------------------------------------------------

-- 
-- Table structure for table `users`
-- 

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_name` longtext,
  `pass` longtext,
  `note` longtext,
  `stock1` longtext,
  `stock2` longtext,
  `stock3` longtext,
  `stock4` longtext,
  `stock5` longtext,
  `stock6` longtext,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

-- 
-- Dumping data for table `users`
-- 

INSERT INTO `users` VALUES (3, 'محمد', '', '', 'true', 'true', 'false', 'false', 'false', 'true');
INSERT INTO `users` VALUES (8, 'محمد', '', '', 'true', 'true', 'false', 'false', 'false', 'true');
