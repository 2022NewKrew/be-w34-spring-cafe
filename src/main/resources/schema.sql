DROP TABLE IF EXISTS USERS;

CREATE TABLE IF NOT EXISTS USERS(
    USERID VARCHAR(32),
    PASSWORD VARCHAR(32),
    NAME VARCHAR(32),
    EMAIL VARCHAR(32)
);


DROP TABLE IF EXISTS ARTICLES;

CREATE TABLE IF NOT EXISTS ARTICLES (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    TITLE VARCHAR(64),
    CONTENT VARCHAR(2048)
);