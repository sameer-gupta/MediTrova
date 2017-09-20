
CREATE DATABASE m;

use m;

CREATE TABLE `m`.`visit` (
  `v_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `v_url` VARCHAR(45) NOT NULL COMMENT '',
  `v_ip` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`v_id`)  COMMENT '');


CREATE TABLE `m`.`link_detail` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
    `link` VARCHAR(45) NOT NULL COMMENT '',
    `org_url` VARCHAR(45) NOT NULL COMMENT '',
    `click` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '',
    `ip` VARCHAR(45) NOT NULL COMMENT '',
    `createdon` VARCHAR(45) NOT NULL COMMENT '',
    PRIMARY KEY (`id`)  COMMENT '');



select * from m.link_detail;
select * from m.visit;

CHECK ::
SELECT * FROM m.link_detail WHERE link="+link+"\;