DROP TABLE IF EXISTS MEMBER;
DROP TABLE IF EXISTS ARTICLE;

CREATE TABLE MEMBER (
                      id INT AUTO_INCREMENT,
                      userId VARCHAR(64),
                      email VARCHAR(64),
                      name VARCHAR(64),
                      password VARCHAR(64)
);

CREATE TABLE ARTICLE (
                         id INT AUTO_INCREMENT,
                         writer VARCHAR(64),
                         title VARCHAR(64),
                         contents VARCHAR(256)
);