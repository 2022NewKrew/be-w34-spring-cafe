DROP TABLE IF EXISTS USERS;
CREATE TABLE IF NOT EXISTS USERS (
    USER_ID VARCHAR(20) NOT NULL PRIMARY KEY,
    PASSWORD VARCHAR(20) NOT NULL,
    NAME VARCHAR(20),
    EMAIL VARCHAR(40)
);

DROP TABLE IF EXISTS ARTICLES;
CREATE TABLE IF NOT EXISTS ARTICLES (
    ARTICLE_ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    WRITER VARCHAR(20),
    WRITER_ID VARCHAR(20) NOT NULL,
    TITLE VARCHAR(255),
    CONTENT VARCHAR(5000),
    CREATED_DATE VARCHAR(30),
    DELETED BOOLEAN DEFAULT FALSE
);

DROP TABLE IF EXISTS REPLIES;
CREATE TABLE IF NOT EXISTS REPLIES (
    REPLY_ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    ARTICLE_ID BIGINT NOT NULL,
    WRITER_ID VARCHAR(20) NOT NULL,
    CONTENT VARCHAR(5000),
    CREATED_DATE VARCHAR(30),
    DELETED BOOLEAN DEFAULT FALSE
)