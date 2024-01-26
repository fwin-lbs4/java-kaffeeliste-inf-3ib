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

Use mydb;

INSERT INTO `mydb`.`Fertigungsstraße` (`FertigungsstrID`, `Kürzel`, `Bezeichnung`)
VALUES
    (1,'FS1', 'Produktion A'),
    (2,'FS2', 'Produktion B'),
    (3,'FS3', 'Produktion C'),
    (4,'FS4', 'Produktion D'),
    (5,'FS5', 'Produktion E'),
    (6,'FS6', 'Produktion F'),
    (7,'FS7', 'Produktion G'),
    (8,'FS8', 'Produktion H'),
    (9,'FS9', 'Produktion I'),
    (10,'FS10', 'Produktion J');

-- Produkt
INSERT INTO `mydb`.`Produkt` (`Artikelnummer`, `Bezeichnung`, `FertigungsstrID`)
VALUES
    (1,'Produkt A', 1),
    (2,'Produkt B', 2),
    (3,'Produkt C', 3),
    (4,'Produkt D', 4),
    (5,'Produkt E', 5),
    (6,'Produkt F', 6),
    (7,'Produkt G', 7),
    (8,'Produkt H', 8),
    (9,'Produkt I', 9),
    (10,'Produkt J', 10);


-- Fertigungsstation
INSERT INTO `mydb`.`Fertigungsstation` (`FertigungsstationID`, `Bezeichnung`, `Kürzel`, `FertigungsstrID`)
VALUES
    (1,'Station 1', 'S1', 1),
    (2,'Station 2', 'S2', 1),
    (3,'Station 3', 'S3', 2),
    (4,'Station 4', 'S4', 2),
    (5,'Station 5', 'S5', 3),
    (6,'Station 6', 'S6', 3),
    (7,'Station 7', 'S7', 4),
    (8,'Station 8', 'S8', 4),
    (9,'Station 9', 'S9', 5),
    (10,'Station 10', 'S10', 5);

-- Maschine
INSERT INTO `mydb`.`Maschine` (`MaschinenID`, `Bezeichnung`, `Analgennummer`, `Zustand`, `FertigungsstationID`)
VALUES
    (1,'Maschine 1', 'A123', 'Betriebsbereit', 1),
    (2,'Maschine 2', 'B456', 'Wartung erforderlich', 2),
    (3,'Maschine 3', 'C789', 'In Reparatur', 3),
    (4,'Maschine 4', 'D987', 'Betriebsbereit', 4),
    (5,'Maschine 5', 'E654', 'Wartung erforderlich', 5),
    (6,'Maschine 6', 'F321', 'In Reparatur', 6),
    (7,'Maschine 7', 'G999', 'Betriebsbereit', 7),
    (8,'Maschine 8', 'H888', 'Wartung erforderlich', 8),
    (9,'Maschine 9', 'I777', 'In Reparatur', 9),
    (10,'Maschine 10', 'J666', 'Betriebsbereit', 10);

-- Ort
INSERT INTO `mydb`.`Ort` (`OrtID`, `PLZ`, `Ortsname`)
VALUES
    (1,'12345', 'Stadt A'),
    (2,'23456', 'Stadt B'),
    (3,'34567', 'Stadt C'),
    (4,'45678', 'Stadt D'),
    (5,'56789', 'Stadt E'),
    (6,'67890', 'Stadt F'),
    (7,'78901', 'Stadt G'),
    (8,'89012', 'Stadt H'),
    (9,'90123', 'Stadt I'),
    (10,'01234', 'Stadt J');


-- Mitarbeiter
INSERT INTO `mydb`.`Mitarbeiter` (`Personalnummer`, `Vorname`, `Nachname`, `Anrede`, `Telefon`, `Passwort`, `Geburtstag`, `File`, `Straße`, `Email`, `OrtID`, `FertigungsstationID`)
VALUES
    ('001', 'Max', 'Mustermann', 'Herr', '123456789', 'pass123', '1990-01-01', NULL, 'Musterstraße 123', 'max@example.com', 1, 1),
    ('002', 'Anna', 'Schmidt', 'Frau', '987654321', 'anna_pass', '1985-05-15', NULL, 'Schmidtstraße 45', 'anna@example.com', 2, 2),
    ('003', 'Peter', 'Müller', 'Herr', '555555555', 'peter_pass', '1988-08-08', NULL, 'Müllerweg 67', 'peter@example.com', 3, 3),
    ('004', 'Laura', 'Schneider', 'Frau', '333333333', 'laura_pass', '1995-11-20', NULL, 'Schneiderweg 89', 'laura@example.com', 4, 4),
    ('005', 'Markus', 'Lehmann', 'Herr', '666666666', 'markus_pass', '1982-03-25', NULL, 'Lehmannstraße 12', 'markus@example.com', 5, 5),
    ('006', 'Sandra', 'Schulz', 'Frau', '444444444', 'sandra_pass', '1993-07-10', NULL, 'Schulzplatz 34', 'sandra@example.com', 6, 6),
    ('007', 'Kevin', 'Becker', 'Herr', '999999999', 'kevin_pass', '1980-09-05', NULL, 'Beckerweg 56', 'kevin@example.com', 7, 7),
    ('008', 'Nina', 'Hofmann', 'Frau', '777777777', 'nina_pass', '1998-02-15', NULL, 'Hofmannstraße 78', 'nina@example.com', 8, 8),
    ('009', 'Tom', 'Wagner', 'Herr', '222222222', 'tom_pass', '1987-04-30', NULL, 'Wagnerweg 90', 'tom@example.com', 9, 9),
    ('010', 'Lisa', 'Koch', 'Frau', '888888888', 'lisa_pass', '1991-06-18', NULL, 'Kochplatz 23', 'lisa@example.com', 10, 10);


INSERT INTO `mydb`.`Mitarbeiter_Maschine` (`Personalnummer`, `MaschinenID`)
VALUES
    ('001', 1),
    ('002', 2),
    ('003', 3),
    ('004', 4),
    ('005', 5),
    ('006', 6),
    ('007', 7),
    ('008', 8),
    ('009', 9),
    ('010', 10);