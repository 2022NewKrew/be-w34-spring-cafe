DROP TABLE IF EXISTS USERS;


CREATE TABLE USERS(
    sequence INT PRIMARY KEY AUTO_INCREMENT,
    userid CHAR(30),
    password CHAR(30),
    name CHAR(30),
    email CHAR(50)
);