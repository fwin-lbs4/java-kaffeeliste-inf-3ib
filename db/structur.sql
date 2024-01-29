# ------------------------------ SQL CREATE DATABASE ------------------------------------------
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema kaffeeliste
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema kaffeeliste
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `kaffeeliste` DEFAULT CHARACTER SET utf8 ;
USE `kaffeeliste` ;

-- -----------------------------------------------------
-- Table `kaffeeliste`.`Kaffee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kaffeeliste`.`Kaffee` (
                                                      `idKaffee` INT NOT NULL AUTO_INCREMENT,
                                                      `Name` VARCHAR(45) NULL,
                                                      `Preis` INT NULL,
                                                      PRIMARY KEY (`idKaffee`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaffeeliste`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kaffeeliste`.`User` (
                                                    `idUser` INT NOT NULL AUTO_INCREMENT,
                                                    `Name` VARCHAR(45) NULL,
                                                    `Rolle` VARCHAR(45) NULL,
                                                    PRIMARY KEY (`idUser`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaffeeliste`.`Schulden`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kaffeeliste`.`Schulden` (
                                                        `idSchulden` INT NOT NULL AUTO_INCREMENT,
                                                        `User_idUser` INT NOT NULL,
                                                        `Anderung` INT NULL,
                                                        `Zeitstempel` DATETIME NULL,
                                                        PRIMARY KEY (`idSchulden`, `User_idUser`),
                                                        INDEX `fk_Schulden_User_idx` (`User_idUser` ASC),
                                                        CONSTRAINT `fk_Schulden_User`
                                                            FOREIGN KEY (`User_idUser`)
                                                                REFERENCES `kaffeeliste`.`User` (`idUser`)
                                                                ON DELETE NO ACTION
                                                                ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

#--------------------------------- INSERT INTO STATEMENTS --------------------------------------
USE kaffeeliste;

INSERT INTO `user` (`idUser`, `Name`, `Rolle`) VALUES
                                                   (1, 'Mayer', 'User'),
                                                   (2, 'Probst', 'User'),
                                                   (3, 'Ackerman', 'User'),
                                                   (4, 'Foerster', 'User'),
                                                   (5, 'Friedmann', 'User'),
                                                   (6, 'Fassbinder', 'User'),
                                                   (7, 'Ostermann', 'User'),
                                                   (8, 'Windisch', 'Administrator');


INSERT INTO `kaffee` (`idKaffee`, `Name`, `Preis`) VALUES
                                                       (1, 'Espresso', 100),
                                                       (2, 'Verlängerter', 150),
                                                       (3, 'Cappuccino', 250);
#------pin

Use kaffeeliste;

ALTER TABLE user
    ADD pin int NULL;