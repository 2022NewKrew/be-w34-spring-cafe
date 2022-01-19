DROP TABLE IF EXISTS USER;
CREATE TABLE USER
(
    id       VARCHAR(32) PRIMARY KEY,
    password VARCHAR(32),
    name     VARCHAR(32),
    email    VARCHAR(32)
);

DROP TABLE IF EXISTS ARTICLE;
CREATE TABLE ARTICLE
(
    id        BIGINT PRIMARY KEY auto_increment,
    writer    VARCHAR(32),
    title     VARCHAR(32),
    contents  VARCHAR(1000),
    createdAt VARCHAR(32)
)
