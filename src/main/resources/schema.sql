DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS articles
(
    id BIGINT,
    writer varchar(255),
    title varchar(255),
    contents CLOB
);
CREATE TABLE IF NOT EXISTS users
(
    id BIGINT,
    userId varchar(255),
    password varchar(255),
    name varchar(255),
    email varchar(255)
);