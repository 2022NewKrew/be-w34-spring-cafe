CREATE TABLE IF NOT EXISTS users
(
    users_id BINARY(16) DEFAULT (UUID_TO_BIN(UUID())) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    PRIMARY KEY(users_id)
);

CREATE TABLE IF NOT EXISTS articles
(
    articles_id BINARY(16) DEFAULT (UUID_TO_BIN(UUID())) NOT NULL,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    view_count INT DEFAULT 0 NOT NULL,
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    users_id BINARY(16) NOT NULL,
    PRIMARY KEY(articles_id),
    FOREIGN KEY(users_id) REFERENCES users (users_id)
);

CREATE TABLE IF NOT EXISTS replies
(
    replies_id BINARY(16) DEFAULT (UUID_TO_BIN(UUID())) NOT NULL,
    comment TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    users_id BINARY(16) NOT NULL,
    articles_id BINARY(16) NOT NULL,
    PRIMARY KEY (replies_id),
    FOREIGN KEY (users_id) REFERENCES users (users_id),
    FOREIGN KEY (articles_id) REFERENCES articles (articles_id)
)
