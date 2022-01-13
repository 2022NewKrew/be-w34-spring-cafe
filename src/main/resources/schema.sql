DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS `user`;

CREATE TABLE article
(
    id integer NOT NULL AUTO_INCREMENT,
    title varchar(255) not null,
    content varchar(255),
    writer varchar(255),
    date timestamp,
    primary key(id)
);

CREATE TABLE `user`
(
    id integer NOT NULL AUTO_INCREMENT,
    userId varchar(255) not null,
    password int,
    name varchar(255),
    email varchar(255),
    primary key(id)
);
