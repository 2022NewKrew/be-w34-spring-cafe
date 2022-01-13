DROP TABLE IF EXISTS MEMBER;

CREATE TABLE IF NOT EXISTS MEMBER (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    EMAIL VARCHAR NOT NULL,
    NICKNAME VARCHAR NOT NULL,
    PASSWORD VARCHAR NOT NULL,
    CREATE_DATE DATE
);

DROP TABLE IF EXISTS ARTICLE;

CREATE TABLE IF NOT EXISTS ARTICLE (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    TITLE VARCHAR NOT NULL,
    AUTHOR VARCHAR NOT NULL,
    CONTENT VARCHAR NOT NULL,
    CREATE_DATE DATE
);
