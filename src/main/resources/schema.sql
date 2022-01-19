DROP TABLE IF EXISTS users, articles, comments;

CREATE TABLE IF NOT EXISTS users
(
    user_id VARCHAR(255) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    deleted BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS articles
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    writer VARCHAR(255) NOT NULL,
    FOREIGN KEY (writer) REFERENCES users(user_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    title VARCHAR(255) NOT NULL,
    contents CLOB NOT NULL,
    deleted BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS comments
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    article_id BIGINT NOT NULL,
    FOREIGN KEY (article_id) REFERENCES articles(id) ON UPDATE CASCADE ON DELETE CASCADE,
    writer VARCHAR(255),
    FOREIGN KEY (writer) REFERENCES users(user_id) ON UPDATE CASCADE ON DELETE SET NULL,
    contents CLOB NOT NULL,
    deleted BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL
);