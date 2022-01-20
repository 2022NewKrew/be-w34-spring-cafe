drop table if exists users, articles, comments;

create table users (
    userId varchar(255) NOT NULL ,
    password varchar(255) NOT NULL ,
    name varchar(255) NOT NULL ,
    email varchar(255) NOT NULL ,

    PRIMARY KEY (userId)
);

create table articles (
    id int NOT NULL AUTO_INCREMENT ,
    userId varchar(255) NOT NULL ,
    writer varchar(255) NOT NULL ,
    title varchar(255) NOT NULL ,
    contents varchar(255) NOT NULL ,

    PRIMARY KEY (id) ,
    FOREIGN KEY (userId) REFERENCES users (userId)
);

create table comments (
    id int NOT NULL AUTO_INCREMENT ,
    articleId int NOT NULL ,
    userId varchar(255) NOT NULL ,
    writer varchar(255) NOT NULL ,
    contents varchar(255) NOT NULL ,

    PRIMARY KEY (id) ,
    FOREIGN KEY (articleId) REFERENCES articles (id) ,
    FOREIGN KEY (userId) REFERENCES users (userId)
)
