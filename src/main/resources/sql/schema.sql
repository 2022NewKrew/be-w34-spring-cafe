drop table if exists users;
drop table if exists article;
drop table if exists reply;

create table users (
    id integer primary key auto_increment,
    username varchar(50) not null,
    nickname varchar(50) not null,
    email varchar(50) not null,
    password varchar(50) not null
);
create table article (
    id integer primary key auto_increment,
    author_id integer references users (id),
    title varchar(100) not null,
    description longtext not null,
    deleted boolean
);
create table reply (
    id integer primary key auto_increment,
    article_id integer references article (id),
    author_id integer references users (id),
    description varchar(100) not null,
    deleted boolean
)
