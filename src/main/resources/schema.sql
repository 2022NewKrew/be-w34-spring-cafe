DROP TABLE IF EXISTS articles, users;

CREATE TABLE ARTICLES (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(64) NOT NULL,
                          contents VARCHAR(1024) NOT NULL,
                          date  DATE NOT NULL
);

CREATE TABLE USERS (
                       userId VARCHAR(16) PRIMARY KEY,
                       password VARCHAR(16) NOT NULL,
                       name VARCHAR(16) NOT NULL,
                       email VARCHAR(32) NOT NULL,
                       signUpDate DATE NOT NULL
);