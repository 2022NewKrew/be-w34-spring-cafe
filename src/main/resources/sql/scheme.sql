DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    user_id UUID DEFAULT UUID() NOT NULL,
    username VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    PRIMARY KEY(user_id)
);

CREATE TABLE articles
(
    article_id UUID DEFAULT UUID() NOT NULL,
    title VARCHAR NOT NULL,
    content CLOB NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    view_count INT DEFAULT 0 NOT NULL,
    user_id UUID NOT NULL,
    PRIMARY KEY(article_id),
    FOREIGN KEY(user_id) REFERENCES users
);
