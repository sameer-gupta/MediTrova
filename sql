ALTER TABLE `healthhack`.`vendor` 
ADD COLUMN `name` VARCHAR(35) NOT NULL AFTER `Location`,
ADD PRIMARY KEY (`Location`);

INSERT INTO `vendor` (`Name`, `Timing`, `Medicine`, `Quantity`, `Prize`, `Location`)
 VALUES ('a pharma','6am - 9pm', 'paracetamol', '200', '300', '110.227.146.198');

INSERT INTO `vendor` (`Name`, `Timing`, `Medicine`, `Quantity`, `Prize`, `Location`)
 VALUES ('b pharma','6am - 9pm', 'citrizine', '2000', '3100', '182.68.216.166');

INSERT INTO `vendor` (`Name`, `Timing`, `Medicine`, `Quantity`, `Prize`, `Location`)
 VALUES ('c pharma','12am - 12pm', 'crocine', '100', '100', '122.176.220.105');

INSERT INTO `vendor` (`Name`, `Timing`, `Medicine`, `Quantity`, `Prize`, `Location`)
 VALUES ('d pharma','3am - 9pm', 'accolate', '400', '500', '189.219.210.31');


INSERT INTO `vendor` (`Name`, `Timing`, `Medicine`, `Quantity`, `Prize`, `Location`)
 VALUES ('a pharma','6am - 9pm', 'citrizine', '200', '300', '110.227.146.198');

INSERT INTO `vendor` (`Name`, `Timing`, `Medicine`, `Quantity`, `Prize`, `Location`)
 VALUES ('b pharma','6am - 9pm', 'citrizine', '2000', '3100', '182.68.216.166');

INSERT INTO `vendor` (`Name`, `Timing`, `Medicine`, `Quantity`, `Prize`, `Location`)
 VALUES ('c pharma','12am - 12pm', 'citrizine', '100', '100', '122.176.220.105');

INSERT INTO `vendor` (`Name`, `Timing`, `Medicine`, `Quantity`, `Prize`, `Location`)
 VALUES ('d pharma','3am - 9pm', 'citrizine', '400', '500', '189.219.210.31');
 

 INSERT INTO `vendor` (`Name`, `Timing`, `Medicine`, `Quantity`, `Prize`, `Location`)
 VALUES ('a pharma','6am - 9pm', 'accolate', '200', '300', '110.227.146.198');


INSERT INTO `vendor` (`Name`, `Timing`, `Medicine`, `Quantity`, `Prize`, `Location`)
 VALUES ('c pharma','12am - 12pm', 'accolate', '100', '100', '122.176.220.105');
 

select * from `vendor`;