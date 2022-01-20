DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ARTICLE;
DROP TABLE IF EXISTS REPLY;

CREATE TABLE IF NOT EXISTS USERS
(
    id           BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    string_id    VARCHAR(32) NOT NULL UNIQUE,
    password     VARCHAR(32) NOT NULL,
    name         VARCHAR(32),
    email        VARCHAR(32),
    sign_up_date DATETIME
);

CREATE TABLE IF NOT EXISTS ARTICLE
(
    id               BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    author           BIGINT      NOT NULL,
    author_string_id VARCHAR(32),
    title            VARCHAR(32) NOT NULL,
    write_date       DATETIME,
    content          TEXT,
    hits             INT,
    is_available     BOOLEAN
);

CREATE TABLE IF NOT EXISTS REPLY
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    article_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    author_string_id VARCHAR(32),
    content TEXT,
    write_date DATETIME,
    is_available BOOLEAN
)
