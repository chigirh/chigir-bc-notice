CREATE TABLE IF NOT EXISTS `notification_mail_address`(
    `mail_address` VARCHAR(300),
    `begin_date` datetime,
    `end_date` datetime,
    PRIMARY KEY(`mail_address`,`begin_date`)
);