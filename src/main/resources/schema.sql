
CREATE TABLE IF NOT EXISTS USERS
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id   VARCHAR(32),
    password VARCHAR(32),
    name     VARCHAR(16),
    email    VARCHAR(64)
    );

CREATE TABLE IF NOT EXISTS ARTICLES
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    writer    VARCHAR(32),
    title     VARCHAR(128),
    contents  VARCHAR(2048),
    created_at VARCHAR(128)
    );

CREATE TABLE IF NOT EXISTS COMMENTS
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    article_id   BIGINT,
    writer      VARCHAR(32),
    contents    VARCHAR(1024),
    created_at  VARCHAR(128),
    FOREIGN KEY(article_id) REFERENCES ARTICLES(id)
)
