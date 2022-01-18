DROP TABLE IF EXISTS cafe_user, cafe_article, cafe_reply;
CREATE TABLE IF NOT EXISTS cafe_user
(
    user_id VARCHAR(32) NOT NULL PRIMARY KEY,
    password VARCHAR(32) NOT NULL,
    name VARCHAR(32) NOT NULL,
    email VARCHAR(32) NOT NULL,
    image_path VARCHAR(100) NOT NULL,
    created_time TIMESTAMP NOT NULL,
    modified_time TIMESTAMP NOT NULL
);
CREATE TABLE IF NOT EXISTS cafe_article
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    writer VARCHAR(32) NOT NULL,
    FOREIGN KEY (writer) REFERENCES cafe_user(user_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    title VARCHAR(100) NOT NULL,
    contents VARCHAR(1000) NOT NULL,
    created_time TIMESTAMP NOT NULL,
    modified_time TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL
);
CREATE TABLE IF NOT EXISTS cafe_reply
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    article_id BIGINT NOT NULL,
    FOREIGN KEY (article_id) REFERENCES cafe_article(id) ON UPDATE CASCADE ON DELETE CASCADE,
    writer VARCHAR(32),
    FOREIGN KEY (writer) REFERENCES cafe_user(user_id) ON UPDATE CASCADE ON DELETE SET NULL,
    contents VARCHAR(300) NOT NULL,
    created_time TIMESTAMP NOT NULL,
    modified_time TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL
);
