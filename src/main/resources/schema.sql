DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id  VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    name     VARCHAR(32) NOT NULL,
    email    VARCHAR(32) NOT NULL
);

DROP TABLE IF EXISTS articles;
CREATE TABLE articles
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    title   VARCHAR(32) NOT NULL,
    content VARCHAR(1000),
    writer  VARCHAR(32) NOT NULL
);

DROP TABLE IF EXISTS comments;
CREATE TABLE comments
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    article_id BIGINT,
    content VARCHAR(1000),
    writer  VARCHAR(32) NOT NULL
);
