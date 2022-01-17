DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS POSTS;

CREATE TABLE IF NOT EXISTS Users
(
    ID       VARCHAR(255) NOT NULL PRIMARY KEY,
    PASSWORD VARCHAR(255),
    EMAIL    VARCHAR(255),
    NAME     VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS POSTS
(
    ID       BIGINT
        AUTO_INCREMENT
        PRIMARY KEY,
    TITLE    VARCHAR(255),
    WRITER   VARCHAR(255),
    CONTENTS VARCHAR(255)
);

INSERT INTO USERS (id, password, name, email)
VALUES ('javajigi', 'test', '자바지기', 'javajigi@slipp.net');
INSERT INTO USERS (id, password, name, email)
VALUES ('sanjigi', 'test', '산지기', 'sanjigi@slipp.net');
