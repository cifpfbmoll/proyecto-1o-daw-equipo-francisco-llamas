CREATE TABLE `coches_db`.`coches` ( `matricula` VARCHAR(20) NOT NULL , `marca` VARCHAR(30) NOT NULL , `modelo` VARCHAR(30) NOT NULL , `precio` FLOAT NOT NULL , `fecha_añadido` VARCHAR(20) NOT NULL , `color` VARCHAR(20) NOT NULL , `imagen` LONGBLOB NOT NULL , PRIMARY KEY (`matricula`)) ENGINE = InnoDB;