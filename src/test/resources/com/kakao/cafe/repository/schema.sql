DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id long NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userId varchar(20) NOT NULL,
    password varchar(50) NOT NULL,
    userName varchar(10) NOT NULL,
    email varchar(50) NOT NULL
);