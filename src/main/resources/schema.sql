DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id
             int
        auto_increment
        primary
        key,
    userId
             varchar(20) not null unique,
    password varchar(20) not null,
    name     varchar(20) not null,
    email    varchar(50) not null
);
DROP TABLE IF EXISTS articles;
CREATE TABLE articles
(
    id
             int
        auto_increment
        primary
        key,
    writer
             varchar(20) not null,
    title    varchar(50) not null,
    contents longtext    not null
);
