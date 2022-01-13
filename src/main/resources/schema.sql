drop table article IF EXISTS;
create table article (id BIGINT NOT NULL, writer varchar(15), title varchar(50), content TEXT, createdAt DATE, numOfComment number);

drop table users IF EXISTS;
create table users (id varchar(15) PRIMARY KEY, email varchar(40), name varchar(20), password varchar(30));
