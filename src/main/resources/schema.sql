DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (id int PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(64) unique not null,
    nickname varchar(64) not null,
    point int);
