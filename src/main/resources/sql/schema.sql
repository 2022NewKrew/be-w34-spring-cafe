drop table if exists users;

create table users (
    id integer primary key,
    username varchar(50) not null,
    nickname varchar(50) not null,
    email varchar(50) not null,
    password varchar(50) not null
);
create table article (
    id integer primary key,
    title varchar(100) not null,
    description longtext not null
)
