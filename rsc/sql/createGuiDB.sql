-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb`;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8;
USE `mydb`;

-- -----------------------------------------------------
-- Table `mydb`.`admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`admin`;

CREATE TABLE IF NOT EXISTS `mydb`.`admin`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(45) NOT NULL,
    `address`  VARCHAR(45) NOT NULL,
    `phone`    INT         NOT NULL,
    `email`    VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`customer`;

CREATE TABLE IF NOT EXISTS `mydb`.`customer`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `phone`    INT         NOT NULL,
    `email`    VARCHAR(45) NOT NULL,
    `balance`  INT         NOT NULL,
    `address`  VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`movie`;

CREATE TABLE IF NOT EXISTS `mydb`.`movie`
(
    `mid`      INT            NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(45)    NOT NULL,
    `desc`     LONGTEXT       NOT NULL,
    `tor`      DATETIME       NOT NULL,
    `price`    DECIMAL(10, 0) NOT NULL,
    `lang`     VARCHAR(45)    NOT NULL,
    `rating`   INT            NOT NULL,
    `len`      TIME           NOT NULL,
    `admin_id` INT            NOT NULL,
    PRIMARY KEY (`mid`),
    UNIQUE INDEX `movie_name_UNIQUE` (`name` ASC) VISIBLE,
    INDEX `fk_movie_admin1_idx` (`admin_id` ASC) VISIBLE,
    CONSTRAINT `fk_movie_admin1`
        FOREIGN KEY (`admin_id`)
            REFERENCES `mydb`.`admin` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`room`;

CREATE TABLE IF NOT EXISTS `mydb`.`room`
(
    `id`           INT         NOT NULL,
    `screen_size`  VARCHAR(45) NOT NULL,
    `theater_type` VARCHAR(45) NOT NULL,
    `number_seats` INT         NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`seat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`seat`;

CREATE TABLE IF NOT EXISTS `mydb`.`seat`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `seat_num` VARCHAR(45) NOT NULL,
    `row_num`  INT         NOT NULL,
    `taken`    VARCHAR(5)  NOT NULL,
    `room_id`  INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_seat_rooms1_idx` (`room_id` ASC) VISIBLE,
    CONSTRAINT `fk_seat_rooms1`
        FOREIGN KEY (`room_id`)
            REFERENCES `mydb`.`room` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ticket`;

CREATE TABLE IF NOT EXISTS `mydb`.`ticket`
(
    `id`          INT       NOT NULL AUTO_INCREMENT,
    `time_booked` TIMESTAMP NOT NULL,
    `customer_id` INT       NOT NULL,
    `movie_id`    INT       NOT NULL,
    `seat_id`     INT       NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_ticket_customer_idx` (`customer_id` ASC) VISIBLE,
    INDEX `fk_ticket_movie1_idx` (`movie_id` ASC) VISIBLE,
    INDEX `fk_ticket_seat1_idx` (`seat_id` ASC) VISIBLE,
    CONSTRAINT `fk_ticket_customer`
        FOREIGN KEY (`customer_id`)
            REFERENCES `mydb`.`customer` (`id`),
    CONSTRAINT `fk_ticket_movie1`
        FOREIGN KEY (`movie_id`)
            REFERENCES `mydb`.`movie` (`mid`),
    CONSTRAINT `fk_ticket_seat1`
        FOREIGN KEY (`seat_id`)
            REFERENCES `mydb`.`seat` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`category`;

CREATE TABLE IF NOT EXISTS `mydb`.`category`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`movie_has_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`movie_has_category`;

CREATE TABLE IF NOT EXISTS `mydb`.`movie_has_category`
(
    `id`          INT NOT NULL AUTO_INCREMENT,
    `category_id` INT NOT NULL,
    `movie_id`    INT NOT NULL,
    INDEX `fk_movie_has_category_category1_idx` (`category_id` ASC) VISIBLE,
    PRIMARY KEY (`id`),
    INDEX `fk_movie_has_category_movie1_idx` (`movie_id` ASC) INVISIBLE,
    CONSTRAINT `fk_movie_has_category_category1`
        FOREIGN KEY (`category_id`)
            REFERENCES `mydb`.`category` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_movie_has_category_movie1`
        FOREIGN KEY (`movie_id`)
            REFERENCES `mydb`.`movie` (`mid`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
