DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    users_id UUID DEFAULT UUID() NOT NULL,
    username VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    name VARCHAR,
    email VARCHAR,
    PRIMARY KEY(users_id)
);

CREATE TABLE articles
(
    articles_id UUID DEFAULT UUID() NOT NULL,
    title VARCHAR,
    content CLOB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    view_count INT DEFAULT 0,
    users_id UUID,
    PRIMARY KEY(articles_id),
    FOREIGN KEY(users_id) REFERENCES users
);
