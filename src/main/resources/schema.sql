drop table if exists users;
drop table if exists article;

create table users
(
    id   bigint auto_increment,
    email varchar(255),
    nickname varchar(255),
    password varchar(255),
    registered_date timestamp,
    created_date timestamp,
    updated_date timestamp,
    primary key(id)
);

create table article
(
    id   bigint auto_increment,
    title varchar(255),
    content varchar(255),
    writer varchar(255),
    views int,
    write_date timestamp,
    created_date timestamp,
    updated_date timestamp,
    primary key(id)
);
