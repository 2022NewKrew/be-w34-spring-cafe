DROP TABLE IF EXISTS `reply`;
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
    `is_deleted` BOOLEAN DEFAULT FALSE,
    primary key(`id`),
    constraint `user_question_fk` foreign key(`writer`) references `user`(`id`)
);

CREATE TABLE `reply` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `created_date_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    `question_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `contents` TEXT NOT NULL,
    `is_deleted` BOOLEAN DEFAULT FALSE ,
    primary key(`id`),
    constraint `user_reply_fk` foreign key(`user_id`) references `user`(`id`),
    constraint `question_reply_fk` foreign key(`question_id`) references `question`(`id`)
);