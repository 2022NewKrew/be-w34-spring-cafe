DROP TABLE IF EXISTS replies;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id          int             NOT NULL AUTO_INCREMENT,
    user_id     VARCHAR(100)     NOT NULL UNIQUE,
    password    VARCHAR(100)     NOT NULL,
    name        VARCHAR(100),
    email       VARCHAR(100),
    PRIMARY KEY(id)
);

CREATE TABLE post
(
    id          int     NOT NULL AUTO_INCREMENT,
    title       VARCHAR(100),
    content     TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id     int,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users (id)
);

CREATE TABLE replies
(
    id          int     NOT NULL AUTO_INCREMENT,
    comment     TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id    int,
    post_id    int,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users (id),
    FOREIGN KEY(post_id) REFERENCES post (id)
);
