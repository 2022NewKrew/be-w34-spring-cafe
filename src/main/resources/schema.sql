DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ARTICLES;


CREATE TABLE USERS(
    sequence long PRIMARY KEY AUTO_INCREMENT,
    userid VARCHAR(30),
    password VARCHAR(50),
    name VARCHAR(10),
    email VARCHAR(50)
);


CREATE TABLE ARTICLES(
    sequence long PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(10),
    title VARCHAR(100),
    contents VARCHAR(2000),
    createdAt TIMESTAMP
);