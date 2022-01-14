DROP TABLE IF EXISTS "USER";
CREATE TABLE IF NOT EXISTS "USER"
(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    USERID VARCHAR(32),
    PASSWORD VARCHAR(32),
    NAME VARCHAR(32),
    EMAIL VARCHAR(50),
    JOINED_AT DATE
);


DROP TABLE IF EXISTS "ARTICLE";
CREATE TABLE IF NOT EXISTS "ARTICLE"
(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    AUTHOR VARCHAR(32),
    TITLE VARCHAR(50),
    CONTENT VARCHAR(300),
    VIEWS INT,
    CREATED_AT DATE
);