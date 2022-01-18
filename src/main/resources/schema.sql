DROP TABLE IF EXISTS ARTICLES;
DROP TABLE IF EXISTS USERS;


CREATE TABLE USERS(
    sequence long PRIMARY KEY AUTO_INCREMENT,
    userid VARCHAR(30) UNIQUE,
    password VARCHAR(50),
    name VARCHAR(10),
    email VARCHAR(50)
);


CREATE TABLE ARTICLES(
    sequence long PRIMARY KEY AUTO_INCREMENT,
    userid VARCHAR(30) REFERENCES USERS(userid),
    name VARCHAR(10),
    title VARCHAR(100),
    contents VARCHAR(2000),
    createdAt TIMESTAMP,
    isDeleted boolean DEFAULT false
);