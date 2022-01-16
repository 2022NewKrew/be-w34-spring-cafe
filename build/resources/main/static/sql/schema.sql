DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Articles;

CREATE TABLE IF NOT EXISTS Users (
    userId   varchar(100) PRIMARY KEY,
    userPassword varchar(100) NOT NULL,
    email    varchar(100) NOT NULL,
    userName varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Articles (
    title varchar(300) NOT NULL,
    content varchar(1000) NOT NULL,
    articleIndex int PRIMARY KEY
);
