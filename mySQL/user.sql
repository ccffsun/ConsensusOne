CREATE TABLE `ConsensusOne`.`user` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `tel` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
INSERT INTO `ConsensusOne`.`user` (`id`, `name`, `email`, `tel`) VALUES ('1', 'Leo', 'leo@gmail.com', '425-777-8888');
INSERT INTO `ConsensusOne`.`user` (`id`, `name`, `email`, `tel`) VALUES ('2', 'Judy', 'judy@gmail.com', '425-666-8888');
INSERT INTO `ConsensusOne`.`user` (`id`, `name`, `email`, `tel`) VALUES ('3','Ricky', 'ricky@gmail.com', '425-555-8888');

CREATE TABLE  ConsensusOne.product (
id INT NOT NULL,
name VARCHAR (45) NULL,
unit VARCHAR (45) NULL,
price double NULL,
firstMonthPrice double NULL,
PRIMARY KEY (id));