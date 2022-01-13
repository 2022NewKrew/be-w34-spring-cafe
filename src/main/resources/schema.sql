DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS
(
    ID       INT AUTO_INCREMENT,
    USER_ID  VARCHAR(32) NOT NULL,
    PASSWORD VARCHAR(32) NOT NULL,
    NAME     VARCHAR(32) NOT NULL,
    EMAIL    VARCHAR(32) NOT NULL
);

DROP TABLE IF EXISTS ARTICLES;
CREATE TABLE ARTICLES
(
    ID            INT AUTO_INCREMENT,
    WRITER        VARCHAR(32) NOT NULL,
    TITLE         VARCHAR(32) NOT NULL,
    CONTENTS      VARCHAR(32),
    WRITING_TIME  VARCHAR(32) NOT NULL,
    COUNT_COMMENT INT
);
