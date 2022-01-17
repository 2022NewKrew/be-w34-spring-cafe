DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id long NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id varchar(20) UNIQUE NOT NULL,
    password varchar(50) NOT NULL,
    user_name varchar(10) NOT NULL,
    email varchar(50) NOT NULL
);

DROP TABLE IF EXISTS articles;
CREATE TABLE articles (
   id long NOT NULL AUTO_INCREMENT PRIMARY KEY,
   writer varchar(20) NOT NULL,
   title varchar(100) NOT NULL,
   contents varchar(1000) NOT NULL
);