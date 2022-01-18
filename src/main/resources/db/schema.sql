SET foreign_key_checks = 0;
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    `key`         BigInt       not null auto_increment,
    id      varchar(256) not null,
    name    varchar(256) not null,
    pw      varchar(256) not null,
    email   varchar(256) not null,
    primary key (`key`)
);

DROP TABLE IF EXISTS article;
CREATE TABLE article
(
    `key`         Bigint          not null auto_increment,
    authorKey      BigInt    not null,
    title       varchar(256)    not null,
    content     varchar(256)    not null,
    postTime    datetime        not null,
    primary key (`key`),
    foreign key (`authorKey`)
    REFERENCES user (`key`)
);
SET foreign_key_checks = 1;