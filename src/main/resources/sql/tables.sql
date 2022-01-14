DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id          UUID DEFAULT UUID() NOT NULL,
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
    users_id    UUID,
    PRIMARY KEY(id),
    FOREIGN KEY(users_id) REFERENCES users (id)
);
