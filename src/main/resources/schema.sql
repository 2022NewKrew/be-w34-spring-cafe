drop table if exists users;
create table if not exists users (
    userid int not null primary key auto_increment,
    password varchar2(256) not null,
    name varchar2(256) not null,
    email varchar2(256) not null
    );

drop table if exists post;
create table if not exists post (
    postid int not null primary key auto_increment,
    writer varchar2(256) not null,
    title varchar2(256) not null,
    content varchar2(256) not null,
    createddate date not null
    );
