SET FOREIGN_KEY_CHECKS = 0;
drop table if exists USERS;
drop table if exists ARTICLES;
drop table if exists COMMENTS;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE USERS
(
    ID bigint(5) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    USER_ID varchar(10) NOT NULL,
    PASSWORD varchar(10) NOT NULL,
    NAME varchar(100) NOT NULL,
    EMAIL varchar(100) NOT NULL
);

CREATE TABLE ARTICLES
(
    ID bigint(5) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    WRITER varchar(10) NOT NULL,
    TITLE varchar(10) NOT NULL,
    CONTENTS varchar(100) NOT NULL,
    COMMENTS_COUNT bigint(5) DEFAULT 0 NOT NULL,
    IS_DELETED varchar(100) NOT NULL,
    CREATED_DATE varchar(100) NOT NULL,
    MODIFIED_DATE varchar(100) NOT NULL
);

CREATE TABLE COMMENTS
(
    ID bigint(5) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    WRITER_ID bigint(5) NOT NULL,
    WRITER varchar(10) NOT NULL,
    ARTICLE_ID bigint(5) NOT NULL,
    CONTENTS varchar(100) NOT NULL,
    IS_DELETED varchar(100) NOT NULL,
    CREATED_DATE varchar(100) NOT NULL,
    MODIFIED_DATE varchar(100) NOT NULL,
    FOREIGN KEY (WRITER_ID) REFERENCES USERS(ID),
    FOREIGN KEY (ARTICLE_ID) REFERENCES ARTICLES(ID)
);
