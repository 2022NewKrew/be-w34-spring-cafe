DROP TABLE IF EXISTS article;

DROP TABLE IF EXISTS users;

CREATE TABLE article
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    writer   VARCHAR NOT NULL,
    title    VARCHAR NOT NULL,
    contents VARCHAR,
    user_pk  INT     NOT NULL,
    deleted  BOOLEAN DEFAULT FALSE
);

CREATE TABLE users
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    user_id  VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    name     VARCHAR NOT NULL,
    email    VARCHAR NOT NULL
);
