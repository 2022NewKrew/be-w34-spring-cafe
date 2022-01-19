DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ARTICLE;

CREATE TABLE IF NOT EXISTS USERS
(
    id           BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    string_id    VARCHAR(20) NOT NULL UNIQUE,
    password     VARCHAR(20) NOT NULL,
    name         VARCHAR(20),
    email        VARCHAR(20),
    sign_up_date DATETIME
);

CREATE TABLE IF NOT EXISTS ARTICLE
(
    id         BIGINT  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    author     BIGINT  NOT NULL,
    title      VARCHAR(50) NOT NULL,
    write_date DATETIME,
    content    TEXT,
    hits       INT
);
