DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ARTICLES;

CREATE TABLE USERS
(
    ID       BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    USER_ID   VARCHAR(255),
    PASSWORD VARCHAR(255),
    NAME     VARCHAR(255),
    EMAIL    VARCHAR(255),
    PRIMARY KEY (ID)
);

CREATE TABLE ARTICLES
(
    ID         BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    AUTHOR     VARCHAR(255),
    TITLE      VARCHAR(255),
    CONTENT    VARCHAR(255),
    CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (ID)
);

INSERT INTO USERS (id, user_id, password, name, email) VALUES (1, 'test', 'test', '테스트 계정', 'test@test.com')