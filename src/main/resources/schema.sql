DROP TABLE IF EXISTS MEMBER;
DROP TABLE IF EXISTS ARTICLE;
DROP TABLE IF EXISTS REPLY;

CREATE TABLE MEMBER (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      user_id VARCHAR(64) unique,
                      email VARCHAR(64),
                      name VARCHAR(64),
                      password VARCHAR(64)
);

CREATE TABLE ARTICLE (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         writer VARCHAR(64) references MEMBER(user_id) on delete cascade on update cascade,
                         title VARCHAR(64),
                         contents VARCHAR(256)
);

CREATE TABLE REPLY (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       aid INT references ARTICLE(id) on delete cascade on update cascade,
                       writer VARCHAR(64) references MEMBER(user_id) on delete cascade on update cascade,
                       contents VARCHAR(256)
);