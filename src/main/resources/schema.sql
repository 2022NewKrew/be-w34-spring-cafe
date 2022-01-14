DROP TABLE IF EXISTS USER;
CREATE TABLE USER
(
    id       VARCHAR(30) PRIMARY KEY,
    password VARCHAR(30),
    name     VARCHAR(30),
    email    VARCHAR(30)
);

DROP TABLE IF EXISTS ARTICLE;
CREATE TABLE ARTICLE
(
    id        BIGINT PRIMARY KEY,
    writer    VARCHAR(30),
    title     VARCHAR(30),
    contents  VARCHAR(1000),
    createdAt VARCHAR(30)
)
