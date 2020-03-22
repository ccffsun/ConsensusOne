CREATE TABLE `ConsensusOne`.`user` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `tel` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE  ConsensusOne.product (
id INT NOT NULL,
name VARCHAR (45) NULL,
unit VARCHAR (45) NULL,
price double NULL,
firstMonthPrice double NULL,
PRIMARY KEY (id));

CREATE TABLE `ConsensusOne`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NULL,
  `productId` INT NULL,
  `orderDate` DATETIME NULL,
  `type` INT NULL,
  `quantity` INT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `ConsensusOne`.`project` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `productId` INT NULL,
  `userId` INT NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
ALTER TABLE `ConsensusOne`.`project`
ADD COLUMN `startDate` DATETIME NULL AFTER `status`;

ALTER TABLE `ConsensusOne`.`order`
DROP COLUMN `userId`,
ADD COLUMN `projectId` INT NULL AFTER `quantity`;
