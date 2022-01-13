CREATE TABLE IF NOT EXISTS USERS
(
    id             BIGINT  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_string_id VARCHAR NOT NULL,
    password       VARCHAR NOT NULL,
    name           VARCHAR,
    email          VARCHAR,
    sign_up_date   DATETIME
);

CREATE TABLE IF NOT EXISTS ARTICLE
(
    id         BIGINT  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    author     BIGINT  NOT NULL,
    title      VARCHAR NOT NULL,
    write_date DATETIME,
    content    CLOB,
    hits       INT
);
