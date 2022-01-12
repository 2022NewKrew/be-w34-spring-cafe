CREATE TABLE IF NOT EXISTS USERS (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    name VARCHAR NOT NULL UNIQUE,
    email VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS ARTICLE (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    author_id BIGINT NOT NULL,
    title VARCHAR NOT NULL,
    contents CLOB,
    created DATETIME NOT NULL,
    view_count INT,
    comment_count INT,
    FOREIGN KEY(author_id) REFERENCES USERS(id) ON DELETE CASCADE
);