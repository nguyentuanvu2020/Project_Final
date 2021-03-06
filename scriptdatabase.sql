-- MySQL Script generated by MySQL Workbench
-- Sat Nov 16 00:27:41 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema project_final
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema project_final
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `project_final` DEFAULT CHARACTER SET utf8mb4 ;
USE `project_final` ;

-- -----------------------------------------------------
-- Table `project_final`.`acc_role_relationship`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`acc_role_relationship` (
  `account_id` INT(11) NOT NULL,
  `account_role_id` INT(11) NOT NULL,
  INDEX `FK43on092tg1iwtfc724fckq4l` (`account_role_id` ASC) VISIBLE,
  INDEX `FK8sjbq3lnf37mk4ckcej6t25bf` (`account_id` ASC) VISIBLE)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `phoneNumber` VARCHAR(255) NULL DEFAULT NULL,
  `birth_date` DATE NULL DEFAULT NULL,
  `disabled` BIT(1) NOT NULL,
  `gender` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`account_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`account_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`customers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `phoneNumber` VARCHAR(255) NULL DEFAULT NULL,
  `account_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKsrdrlm9qxopan7pqism3sf5a5` (`account_id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`favorites`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`favorites` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `account_id` INT(11) NULL DEFAULT NULL,
  `product_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKbqsp0h5gswfy61ybvpr8evogi` (`account_id` ASC) VISIBLE,
  INDEX `FK6sgu5npe8ug4o42bf9j71x20c` (`product_id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`order_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`order_details` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `color` VARCHAR(255) NULL DEFAULT NULL,
  `isReviewed` BIT(1) NOT NULL,
  `price` DOUBLE NOT NULL,
  `quantity` INT(11) NOT NULL,
  `size` INT(11) NOT NULL,
  `unitPrice` DOUBLE NOT NULL,
  `order_id` INT(11) NULL DEFAULT NULL,
  `product_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id` ASC) VISIBLE,
  INDEX `FK4q98utpd73imf4yhttm3w0eax` (`product_id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`orders` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `deliveredDate` DATETIME NULL DEFAULT NULL,
  `note` VARCHAR(255) NULL DEFAULT NULL,
  `orderDate` DATETIME NULL DEFAULT NULL,
  `orderStatus` VARCHAR(255) NULL DEFAULT NULL,
  `totalPrice` DOUBLE NOT NULL,
  `customer_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKpxtb8awmi0dk6smoh2vp1litg` (`customer_id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`product_color`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`product_color` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `productColor` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`product_images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`product_images` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `product_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKqnq71xsohugpqwf3c9gxmsuy` (`product_id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`product_promotion_relationship`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`product_promotion_relationship` (
  `promotion_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  INDEX `FK1od4w1f599nugw7pm88svnp5n` (`product_id` ASC) VISIBLE,
  INDEX `FKk73ytcduy58fdq0iu1y1k98se` (`promotion_id` ASC) VISIBLE)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`product_size`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`product_size` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `productSize` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`productdetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`productdetails` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `productQuantity` INT(11) NOT NULL,
  `color_id` INT(11) NULL DEFAULT NULL,
  `product_id` INT(11) NULL DEFAULT NULL,
  `size_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKet6iyeasvhqmw0ukfnpue3n9p` (`color_id` ASC) VISIBLE,
  INDEX `FKs0plrno2obtpd85gxqd2d3gvo` (`product_id` ASC) VISIBLE,
  INDEX `FK2t7u4ugr9w684vsegwditdyjq` (`size_id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 145
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`products` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` TEXT NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `price` DOUBLE NOT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `category_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK1cf90etcu98x1e6n9aks3tel3` (`category_id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`promotions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`promotions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `discount` INT(11) NOT NULL,
  `endDate` DATE NULL DEFAULT NULL,
  `image` VARCHAR(255) NULL DEFAULT NULL,
  `startDate` DATE NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `project_final`.`reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_final`.`reviews` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NULL DEFAULT NULL,
  `dateReview` DATETIME NULL DEFAULT NULL,
  `orderDetailId` INT(11) NOT NULL,
  `rate` INT(11) NOT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `typeOfShoes` VARCHAR(255) NULL DEFAULT NULL,
  `account_id` INT(11) NULL DEFAULT NULL,
  `product_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK6wik3howqayl69l5vf356n3hh` (`account_id` ASC) VISIBLE,
  INDEX `FKpl51cejpw4gy5swfar8br9ngi` (`product_id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
