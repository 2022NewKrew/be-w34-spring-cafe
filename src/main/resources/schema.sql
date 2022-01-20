DROP TABLE IF EXISTS USERS;
CREATE TABLE IF NOT EXISTS USERS
(
    id
        BIGINT
        NOT NULL
        PRIMARY KEY AUTO_INCREMENT,
    user_id
        VARCHAR
        NOT NULL
        UNIQUE,
    password
        VARCHAR
        NOT NULL,
    name
        VARCHAR,
    email
        VARCHAR
);

DROP TABLE IF EXISTS ARTICLE;
CREATE TABLE IF NOT EXISTS ARTICLES
(
    id
        BIGINT
        NOT NULL
        PRIMARY KEY AUTO_INCREMENT,
    writer_id
        BIGINT
        NOT NULL
        REFERENCES USERS (id),
    title
        VARCHAR
        NOT NULL,
    content
        CLOB
        NOT NULL,
    created_at
        DATETIME
        DEFAULT NOW(),
    updated_at
        DATETIME
        DEFAULT NOW(),
    deleted
        BOOLEAN
        NOT NULL
        DEFAULT FALSE
);

DROP TABLE IF EXISTS REPLIES;
CREATE TABLE IF NOT EXISTS REPLIES
(
    id
        BIGINT
        NOT NULL
        PRIMARY KEY AUTO_INCREMENT,
    writer_id
        BIGINT
        NOT NULL
        REFERENCES USERS (id),
    content
        VARCHAR
        NOT NULL,
    created_at
        DATETIME
        DEFAULT NOW(),
    deleted
        BOOLEAN
        NOT NULL
        DEFAULT FALSE
);
