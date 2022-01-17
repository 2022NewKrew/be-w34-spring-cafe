DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    user_id     VARCHAR(100)     NOT NULL,
    password    VARCHAR(100)     NOT NULL,
    name        VARCHAR(100),
    email       VARCHAR(100),
    PRIMARY KEY(user_id)
);

CREATE TABLE post
(
    id          int     NOT NULL AUTO_INCREMENT,
    title       VARCHAR(100),
    content     TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id     VARCHAR(100),
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users (user_id)
);
