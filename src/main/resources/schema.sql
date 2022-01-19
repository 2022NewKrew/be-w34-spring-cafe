DROP TABLE IF EXISTS USERLIST;
CREATE TABLE IF NOT EXISTS USERLIST
(
    ID       LONG PRIMARY KEY auto_increment NOT NULL,
    USERID   VARCHAR(255)                    NOT NULL,
    PASSWORD VARCHAR(255)                    NOT NULL,
    NAME     VARCHAR(255)                    NOT NULL,
    EMAIL    VARCHAR(255)                    NOT NULL
);

DROP TABLE IF EXISTS ARTICLE;
CREATE TABLE IF NOT EXISTS ARTICLE
(
    ID        LONG PRIMARY KEY auto_increment NOT NULL,
    WRITETIME VARCHAR(255)                    NOT NULL,
    WRITER    VARCHAR(255)                    NOT NULL,
    TITLE     VARCHAR(255)                    NOT NULL,
    CONTENTS  VARCHAR(255)                    NOT NULL
);
