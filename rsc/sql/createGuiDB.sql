-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bw8vg030tuezxbpr6mj2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bw8vg030tuezxbpr6mj2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bw8vg030tuezxbpr6mj2` DEFAULT CHARACTER SET utf8 ;
USE `bw8vg030tuezxbpr6mj2` ;

-- -----------------------------------------------------
-- Table `bw8vg030tuezxbpr6mj2`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bw8vg030tuezxbpr6mj2`.`customer` (
  `id_customer` INT NOT NULL AUTO_INCREMENT,
  `username_customer` VARCHAR(45) NOT NULL,
  `password_customer` VARCHAR(45) NOT NULL,
  `money_customer` DECIMAL NULL,
  `email_customer` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_customer`),
  UNIQUE INDEX `username_customer_UNIQUE` (`username_customer` ASC) VISIBLE,
  UNIQUE INDEX `email_customer_UNIQUE` (`email_customer` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bw8vg030tuezxbpr6mj2`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bw8vg030tuezxbpr6mj2`.`movie` (
  `id_movie` INT NOT NULL AUTO_INCREMENT,
  `name_movie` VARCHAR(45) NOT NULL,
  `desc_movie` LONGTEXT NULL,
  `tor_movie` DATETIME NOT NULL,
  `price_movie` DECIMAL NOT NULL,
  PRIMARY KEY (`id_movie`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bw8vg030tuezxbpr6mj2`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bw8vg030tuezxbpr6mj2`.`ticket` (
  `id_ticket` INT NOT NULL AUTO_INCREMENT,
  `dor_ticket` DATETIME NOT NULL,
  `id_customer` INT NOT NULL,
  `id_movie` INT NOT NULL,
  PRIMARY KEY (`id_ticket`),
  INDEX `fk_ticket_customer_idx` (`id_customer` ASC) VISIBLE,
  INDEX `fk_ticket_movie1_idx` (`id_movie` ASC) VISIBLE,
  CONSTRAINT `fk_ticket_customer`
    FOREIGN KEY (`id_customer`)
    REFERENCES `bw8vg030tuezxbpr6mj2`.`customer` (`id_customer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_movie1`
    FOREIGN KEY (`id_movie`)
    REFERENCES `bw8vg030tuezxbpr6mj2`.`movie` (`id_movie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
