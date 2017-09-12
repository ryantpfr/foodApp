-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema foodapp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema foodapp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `foodapp` DEFAULT CHARACTER SET utf8 ;
USE `foodapp` ;

-- -----------------------------------------------------
-- Table `foodapp`.`session_t`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foodapp`.`session_t` (
  `SESSION_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `ACTIVE` BIT(1) NOT NULL,
  PRIMARY KEY (`SESSION_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `foodapp`.`restaurant_t`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foodapp`.`restaurant_t` (
  `RESTAURANT_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `SESSION_ID` INT(11) NOT NULL,
  PRIMARY KEY (`RESTAURANT_ID`),
  INDEX `RESTAURANT_SESSION_FK_idx` (`SESSION_ID` ASC),
  CONSTRAINT `RESTAURANT_SESSION_FK`
    FOREIGN KEY (`SESSION_ID`)
    REFERENCES `foodapp`.`session_t` (`SESSION_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `foodapp`.`user_t`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foodapp`.`user_t` (
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(60) NOT NULL,
  `IS_ADMIN` BIT(1) NULL DEFAULT b'0',
  PRIMARY KEY (`USERNAME`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `foodapp`.`vote_t`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foodapp`.`vote_t` (
  `VOTE_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `USER` VARCHAR(45) NOT NULL,
  `SESSION_ID` INT(11) NOT NULL,
  `RESTAURANT_ID` INT(11) NOT NULL,
  PRIMARY KEY (`VOTE_ID`),
  INDEX `VOTE_SESSION_FK_idx` (`SESSION_ID` ASC),
  INDEX `VOTE_RESTAURANT_FK_idx` (`RESTAURANT_ID` ASC),
  CONSTRAINT `VOTE_RESTAURANT_FK`
    FOREIGN KEY (`RESTAURANT_ID`)
    REFERENCES `foodapp`.`restaurant_t` (`RESTAURANT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `VOTE_SESSION_FK`
    FOREIGN KEY (`SESSION_ID`)
    REFERENCES `foodapp`.`session_t` (`SESSION_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
