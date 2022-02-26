-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`admin` ;

CREATE TABLE IF NOT EXISTS `mydb`.`admin` (
                                              `id` INT NOT NULL AUTO_INCREMENT,
                                              `aname` VARCHAR(45) NOT NULL,
                                              `address` VARCHAR(45) NOT NULL,
                                              `phone` INT NOT NULL,
                                              `email` VARCHAR(45) NOT NULL,
                                              `password` VARCHAR(45) NOT NULL,
                                              PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`category` ;

CREATE TABLE IF NOT EXISTS `mydb`.`category` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `name` VARCHAR(45) NOT NULL,
                                                 PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`customer` ;

CREATE TABLE IF NOT EXISTS `mydb`.`customer` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `username` VARCHAR(45) NOT NULL,
                                                 `password` VARCHAR(45) NOT NULL,
                                                 `phone` INT NOT NULL,
                                                 `email` VARCHAR(45) NOT NULL,
                                                 `balance` DECIMAL(10,0) NOT NULL,
                                                 `address` VARCHAR(45) NOT NULL,
                                                 `tickets_bought` INT NOT NULL DEFAULT '0',
                                                 PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`movie` ;

CREATE TABLE IF NOT EXISTS `mydb`.`movie` (
                                              `mid` INT NOT NULL AUTO_INCREMENT,
                                              `mname` VARCHAR(45) NOT NULL,
                                              `description` LONGTEXT NOT NULL,
                                              `tor` DATETIME NOT NULL,
                                              `price` DOUBLE NOT NULL,
                                              `lang` VARCHAR(45) NOT NULL,
                                              `rating` DOUBLE NOT NULL,
                                              `len` TIME NOT NULL,
                                              `tickets_sold` INT NOT NULL DEFAULT '0',
                                              `admin_id` INT NULL DEFAULT NULL,
                                              `image` LONGTEXT NULL DEFAULT NULL,
                                              PRIMARY KEY (`mid`),
                                              UNIQUE INDEX `movie_name_UNIQUE` (`mname` ASC) VISIBLE,
                                              INDEX `fk_movie_admin1_idx` (`admin_id` ASC) VISIBLE,
                                              CONSTRAINT `fk_movie_admin1`
                                                  FOREIGN KEY (`admin_id`)
                                                      REFERENCES `mydb`.`admin` (`id`)
                                                      ON DELETE SET NULL
                                                      ON UPDATE NO ACTION)
    ENGINE = InnoDB
    AUTO_INCREMENT = 15
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`movie_has_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`movie_has_category` ;

CREATE TABLE IF NOT EXISTS `mydb`.`movie_has_category` (
                                                           `id` INT NOT NULL AUTO_INCREMENT,
                                                           `category_id` INT NOT NULL,
                                                           `movie_id` INT NOT NULL,
                                                           PRIMARY KEY (`id`),
                                                           INDEX `fk_movie_has_category_category1_idx` (`category_id` ASC) VISIBLE,
                                                           INDEX `fk_movie_has_category_movie1_idx` (`movie_id` ASC) INVISIBLE,
                                                           CONSTRAINT `fk_movie_has_category_category1`
                                                               FOREIGN KEY (`category_id`)
                                                                   REFERENCES `mydb`.`category` (`id`)
                                                                   ON DELETE CASCADE
                                                                   ON UPDATE NO ACTION,
                                                           CONSTRAINT `fk_movie_has_category_movie1`
                                                               FOREIGN KEY (`movie_id`)
                                                                   REFERENCES `mydb`.`movie` (`mid`)
                                                                   ON DELETE CASCADE
                                                                   ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`room` ;

CREATE TABLE IF NOT EXISTS `mydb`.`room` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `screen_size` VARCHAR(45) NOT NULL,
                                             `theater_type` VARCHAR(45) NOT NULL,
                                             `number_seats` INT NOT NULL,
                                             PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`room_has_movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`room_has_movie` ;

CREATE TABLE IF NOT EXISTS `mydb`.`room_has_movie` (
                                                       `room_id` INT NOT NULL,
                                                       `movie_mid` INT NOT NULL,
                                                       `start_time` DATETIME NOT NULL,
                                                       `end_time` DATETIME NOT NULL,
                                                       PRIMARY KEY (`room_id`, `movie_mid`),
                                                       INDEX `fk_room_has_movie_movie1_idx` (`movie_mid` ASC) VISIBLE,
                                                       INDEX `fk_room_has_movie_room1_idx` (`room_id` ASC) VISIBLE,
                                                       CONSTRAINT `fk_room_has_movie_movie1`
                                                           FOREIGN KEY (`movie_mid`)
                                                               REFERENCES `mydb`.`movie` (`mid`)
                                                               ON DELETE CASCADE
                                                               ON UPDATE NO ACTION,
                                                       CONSTRAINT `fk_room_has_movie_room1`
                                                           FOREIGN KEY (`room_id`)
                                                               REFERENCES `mydb`.`room` (`id`)
                                                               ON DELETE CASCADE
                                                               ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`seat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`seat` ;

CREATE TABLE IF NOT EXISTS `mydb`.`seat` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `seat_num` INT NOT NULL,
                                             `row_num` INT NOT NULL,
                                             `room_id` INT NOT NULL,
                                             PRIMARY KEY (`id`),
                                             INDEX `fk_seat_rooms1_idx` (`room_id` ASC) VISIBLE,
                                             CONSTRAINT `fk_seat_rooms1`
                                                 FOREIGN KEY (`room_id`)
                                                     REFERENCES `mydb`.`room` (`id`)
                                                     ON DELETE CASCADE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ticket` ;

CREATE TABLE IF NOT EXISTS `mydb`.`ticket` (
                                               `id` INT NOT NULL AUTO_INCREMENT,
                                               `time_booked` DATETIME NOT NULL,
                                               `customer_id` INT NOT NULL,
                                               `movie_id` INT NOT NULL,
                                               `seat_id` INT NOT NULL,
                                               PRIMARY KEY (`id`),
                                               INDEX `fk_ticket_customer_idx` (`customer_id` ASC) VISIBLE,
                                               INDEX `fk_ticket_movie1_idx` (`movie_id` ASC) VISIBLE,
                                               INDEX `fk_ticket_seat1_idx` (`seat_id` ASC) VISIBLE,
                                               CONSTRAINT `fk_ticket_customer`
                                                   FOREIGN KEY (`customer_id`)
                                                       REFERENCES `mydb`.`customer` (`id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,
                                               CONSTRAINT `fk_ticket_movie1`
                                                   FOREIGN KEY (`movie_id`)
                                                       REFERENCES `mydb`.`movie` (`mid`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,
                                               CONSTRAINT `fk_ticket_seat1`
                                                   FOREIGN KEY (`seat_id`)
                                                       REFERENCES `mydb`.`seat` (`id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION)
    ENGINE = InnoDB
    AUTO_INCREMENT = 21
    DEFAULT CHARACTER SET = utf8mb3;

USE `mydb`;

DELIMITER $$

USE `mydb`$$
DROP TRIGGER IF EXISTS `mydb`.`ticket_AFTER_INSERT` $$
USE `mydb`$$
CREATE
    DEFINER=`root`@`localhost`
    TRIGGER `mydb`.`ticket_AFTER_INSERT`
    AFTER INSERT ON `mydb`.`ticket`
    FOR EACH ROW
BEGIN
    update movie set movie.tickets_sold = movie.tickets_sold + 1 where new.movie_id = movie.mid;
END$$


USE `mydb`$$
DROP TRIGGER IF EXISTS `mydb`.`ticket_AFTER_INSERT_1` $$
USE `mydb`$$
CREATE
    DEFINER=`root`@`localhost`
    TRIGGER `mydb`.`ticket_AFTER_INSERT_1`
    AFTER INSERT ON `mydb`.`ticket`
    FOR EACH ROW
BEGIN
    update customer set customer.tickets_bought = customer.tickets_bought + 1 where new.customer_id = customer.id;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
