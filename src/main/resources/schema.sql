create table tb_user
(
    id       BIGINT PRIMARY KEY,
    userId   VARCHAR(32) UNIQUE,
    password VARCHAR(32),
    name     VARCHAR(16),
    email    VARCHAR(64)
);

CREATE TABLE tb_article
(
    id        BIGINT PRIMARY KEY,
    writer    VARCHAR(32),
    title     VARCHAR(128),
    contents  VARCHAR(2048),
    createdAt TIMESTAMP
);
