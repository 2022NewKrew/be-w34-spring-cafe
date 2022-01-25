DROP TABLE IF EXISTS replies;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id          int     NOT NULL AUTO_INCREMENT,
    user_id     VARCHAR     NOT NULL UNIQUE,
    password    VARCHAR     NOT NULL,
    name        VARCHAR,
    email       VARCHAR,
    PRIMARY KEY(id)
);

CREATE TABLE post
(
    id          int     NOT NULL AUTO_INCREMENT,
    title       VARCHAR,
    content     CLOB,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id    int,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users (id)
);

CREATE TABLE replies
(
    id          int     NOT NULL AUTO_INCREMENT,
    comment     CLOB,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id    int,
    post_id    int,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users (id),
    FOREIGN KEY(post_id) REFERENCES post (id)
);
