DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    userId VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS articles;
CREATE TABLE IF NOT EXISTS articles
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    writer VARCHAR(50) NOT NULL,
    title VARCHAR(255) NOT NULL,
    contents TEXT NOT NULL,
    deleted BOOLEAN NOT NULL
);