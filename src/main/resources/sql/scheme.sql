DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    users_id UUID DEFAULT UUID() NOT NULL,
    username VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    PRIMARY KEY(users_id)
);

CREATE TABLE articles
(
    articles_id UUID DEFAULT UUID() NOT NULL,
    title VARCHAR NOT NULL,
    content CLOB NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    view_count INT DEFAULT 0 NOT NULL,
    users_id UUID NOT NULL,
    PRIMARY KEY(articles_id),
    FOREIGN KEY(users_id) REFERENCES users
);
