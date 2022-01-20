drop table if exists users, articles;

create table users (
    userId varchar(255) NOT NULL ,
    password varchar(255) NOT NULL ,
    name varchar(255) NOT NULL ,
    email varchar(255) NOT NULL ,
    primary key (userId)
);

create table articles (
    id int NOT NULL AUTO_INCREMENT,
    userId varchar(255) NOT NULL ,
    writer varchar(255) NOT NULL ,
    title varchar(255) NOT NULL ,
    contents varchar(255) NOT NULL ,
    primary key (id)
)
