DROP TABLE IF EXISTS `question`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nickname` varchar(32) NOT NULL,
    `password` varchar(32) NOT NULL,
    `name` varchar(32),
    `email` varchar(32),
    primary key(`id`)
);

CREATE TABLE `question` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `created_date_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    `writer` BIGINT NOT NULL,
    `title` VARCHAR(64) NOT NULL,
    `contents` TEXT NOT NULL,
    primary key(`id`),
    constraint `user_fk` foreign key(`writer`) references `user`(`id`)
);