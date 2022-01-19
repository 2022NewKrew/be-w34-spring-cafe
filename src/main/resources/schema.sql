DROP TABLE IF EXISTS articles, users, replys;

CREATE TABLE articles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    writer VARCHAR(32) NOT NULL,
    title VARCHAR(64) NOT NULL,
    contents VARCHAR(1024) NOT NULL,
    deleted BOOLEAN
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId VARCHAR(16) NOT NULL,
    password VARCHAR(16) NOT NULL,
    name VARCHAR(16) NOT NULL,
    email VARCHAR(32) NOT NULL
);

CREATE TABLE replys (
    id INT AUTO_INCREMENT PRIMARY KEY,
    writer VARCHAR(32) NOT NULL,
    contents VARCHAR(1024) NOT NULL,
    articleId INT,
    deleted BOOLEAN,
    FOREIGN KEY (articleId) REFERENCES articles (id)
);