DROP TABLE IF EXISTS REPLY;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ARTICLE;

CREATE TABLE IF NOT EXISTS USERS
(
    id         BIGINT      NOT NULL AUTO_INCREMENT,
    user_id    VARCHAR(50) NOT NULL UNIQUE,
    password   VARCHAR(50) NOT NULL,
    name       VARCHAR(50),
    email      VARCHAR(50),
    created_at DATETIME,
    updated_at DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ARTICLE
(
    id         BIGINT      NOT NULL AUTO_INCREMENT,
    author     VARCHAR(50) NOT NULL,
    title      VARCHAR(50) NOT NULL,
    content    TEXT,
    is_deleted BOOLEAN,
    created_at DATETIME,
    updated_at DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS REPLY
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    article_id BIGINT       NOT NULL,
    author     VARCHAR(50)  NOT NULL,
    comment    VARCHAR(200) NOT NULL,
    is_deleted BOOLEAN,
    created_at DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (article_id) REFERENCES ARTICLE (id)
);
