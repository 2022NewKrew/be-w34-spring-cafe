DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Articles;
DROP TABLE IF EXISTS Reply;

CREATE TABLE IF NOT EXISTS Users (
    userId   varchar(100) PRIMARY KEY,
    userPassword varchar(100) NOT NULL,
    email    varchar(100) NOT NULL,
    userName varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Articles (
    title varchar(300) NOT NULL,
    content varchar(1000) NOT NULL,
    writer varchar(1000) NOT NULL,
    articleIndex int AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS Reply (
    replyIndex int PRIMARY KEY AUTO_INCREMENT,
    articleIndex int NOT NULL,
    writer varchar(1000) NOT NULL,
    content varchar(1000) NOT NULL
);


