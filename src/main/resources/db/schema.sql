DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    key         BigInt       not null auto_increment,
    id      varchar(256) not null,
    name    varchar(256) not null,
    pw      varchar(256) not null,
    email   varchar(256) not null,
    primary key (key)
);

DROP TABLE IF EXISTS article;
CREATE TABLE article
(
    key         Bigint          not null auto_increment,
    author      varchar(256)    not null,
    title       varchar(256)    not null,
    content     varchar(256)    not null,
    postTime    datetime        not null
);