DROP TABLE IF EXISTS USERS;


CREATE TABLE USERS(
    sequence long PRIMARY KEY AUTO_INCREMENT,
    userid VARCHAR,
    password VARCHAR,
    name VARCHAR,
    email VARCHAR
);