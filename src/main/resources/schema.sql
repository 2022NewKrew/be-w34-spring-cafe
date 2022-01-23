drop table if exists users;
drop table if exists article;
drop table if exists reply;

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
    user_id bigint,
    primary key(id),
    foreign key(user_id) references users(id)
);

create table reply
(
    id   bigint auto_increment,
    content varchar(255),
    writer varchar(255),
    write_date timestamp,
    created_date timestamp,
    updated_date timestamp,
    user_id bigint,
    article_id bigint,
    primary key(id),
    foreign key(user_id) references users(id),
    foreign key(article_id) references article(id)
);
