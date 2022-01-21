CREATE TABLE IF NOT EXISTS users
(
    users_id UUID DEFAULT UUID() NOT NULL,
    username VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    PRIMARY KEY(users_id)
);

CREATE TABLE IF NOT EXISTS articles
(
    articles_id UUID DEFAULT UUID() NOT NULL,
    title VARCHAR NOT NULL,
    content CLOB NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    view_count INT DEFAULT 0 NOT NULL,
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    users_id UUID NOT NULL,
    PRIMARY KEY(articles_id),
    FOREIGN KEY(users_id) REFERENCES users
);

CREATE TABLE IF NOT EXISTS replies
(
    replies_id UUID DEFAULT UUID() NOT NULL,
    comment VARCHAR NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    users_id UUID NOT NULL,
    articles_id UUID NOT NULL,
    PRIMARY KEY (replies_id),
    FOREIGN KEY (users_id) REFERENCES users,
    FOREIGN KEY (articles_id) REFERENCES articles
)
