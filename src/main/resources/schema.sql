DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    email      VARCHAR(100) UNIQUE NOT NULL,
    nickname   VARCHAR(50)         NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE IF NOT EXISTS posts
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    writer_id  BIGINT       NOT NULL,
    title      VARCHAR(100) NOT NULL,
    content    TEXT         NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    deleted    BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (writer_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS comments
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    post_id    BIGINT NOT NULL,
    writer_id  BIGINT NOT NULL,
    content    TEXT   NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    deleted    BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE,
    FOREIGN KEY (writer_id) REFERENCES users (id)
);
