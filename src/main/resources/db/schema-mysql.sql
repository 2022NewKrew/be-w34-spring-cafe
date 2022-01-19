DROP TABLE IF EXISTS article;

DROP TABLE IF EXISTS users;

CREATE TABLE article
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    writer   VARCHAR(50) NOT NULL,
    title    VARCHAR(50) NOT NULL,
    contents VARCHAR(255)
);

CREATE TABLE users
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    user_id  VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    name     VARCHAR(50) NOT NULL,
    email    VARCHAR(50) NOT NULL
);
