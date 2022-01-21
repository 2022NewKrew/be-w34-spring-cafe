use kakaodb;

DROP TABLE IF EXISTS ARTICLE, USERS, COMMENTS;

CREATE TABLE ARTICLE(
    id bigint AUTO_INCREMENT PRIMARY KEY ,
    author VARCHAR(30) NOT NULL,
    title VARCHAR(30) NOT NULL,
    contents VARCHAR(255) NOT NULL,
    uploadTime VARCHAR(30) NOT NULL,
    deleted BOOLEAN default FALSE
);

CREATE TABLE USERS(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    userId VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE COMMENTS(
    id         bigint AUTO_INCREMENT PRIMARY KEY,
    articleId  bigint       NOT NULL,
    author     VARCHAR(30)  NOT NULL,
    contents   VARCHAR(255) NOT NULL,
    uploadTime VARCHAR(30)  NOT NULL,
    deleted    BOOLEAN default FALSE,
    FOREIGN KEY (articleId) REFERENCES ARTICLE (id)
);

