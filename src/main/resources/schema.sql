DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nickname` varchar(32) NOT NULL,
    `password` varchar(32) NOT NULL,
    `name` varchar(32),
    `email` varchar(32),
    primary key(`id`)
);