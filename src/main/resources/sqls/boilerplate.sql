CREATE SCHEMA IF NOT EXISTS `sophie_glimsager_psykologi_db`;
USE `sophie_glimsager_psykologi_db`;

DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS admin;

CREATE TABLE clients(
    `id` BIGINT(9) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(35) NOT NULL,
    `email` VARCHAR(35) NOT NULL,
    `birthdate` DATE NOT NULL,
    `consultation` ENUM(
        'ONLINE',
        'PHONE',
        'PHYSICAL'
    ) NOT NULL,

    PRIMARY KEY(`id`)
);

CREATE TABLE bookings(
    `client_id` INT(9),
    `start` DATETIME NOT NULL,
    `end` DATETIME NOT NULL,
    `subject` ENUM(
        'STRESS',
        'DEATH_MOANING',
        'ANXIETY',
        'DEPRESSION'
    ),
    `title` VARCHAR(20),
    `description` VARCHAR(250),
    `timestamp` DATETIME NOT NULL,

     PRIMARY KEY(`start`,`end`),
     FOREIGN KEY(`client_id`) REFERENCES clients(`id`)
);

CREATE TABLE admin(
    `password` VARCHAR(25),
    `email` VARCHAR(30),
    `telephone` INT(8),
    `address` VARCHAR(40)
);

CREATE TABLE mobile_pay(
    `reference` VARCHAR(30) NOT NULL,
    `amount` DOUBLE NOT NULL,
    `paymentId` VARCHAR(36) NOT NULL,

    PRIMARY KEY(`reference`)
);