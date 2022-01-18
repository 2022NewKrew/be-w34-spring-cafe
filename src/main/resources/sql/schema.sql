DROP TABLE IF EXISTS USER_TABLE;
DROP TABLE If EXISTS ARTICLE_TABLE;
CREATE TABLE IF NOT EXISTS USER_TABLE (
    USERID VARCHAR (32),
    PASSWORD VARCHAR (32),
    NAME VARCHAR (32),
    EMAIL VARCHAR (32),
    PRIMARY KEY (USERID));
CREATE TABLE If NOT EXISTS ARTICLE_TABLE (
    ID INT AUTO_INCREMENT,
    TITLE VARCHAR(32),
    CONTENT VARCHAR(255),
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    USERID VARCHAR(32),
    PRIMARY KEY (ID),
    FOREIGN KEY(USERID) REFERENCES USER_TABLE(USERID));
