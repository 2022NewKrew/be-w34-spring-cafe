drop table if exists users;
drop table if exists article;

create table users (
    id integer primary key auto_increment,
    username varchar(50) not null,
    nickname varchar(50) not null,
    email varchar(50) not null,
    password varchar(50) not null
);
create table article (
    id integer primary key auto_increment,
    authorId integer references users (id),
    title varchar(100) not null,
    description longtext not null
)
