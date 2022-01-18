drop table if exists member;
create table member
(
    id       bigint auto_increment,
    userId   varchar(20) not null,
    password varchar(20) not null,
    name     varchar(20) not null,
    email    varchar(50) not null,
    primary key (id)
);

drop table if exists article;
create table article
(
    id          bigint auto_increment,
    title       varchar(500) not null,
    contents    text,
    writer      varchar(20),
    active      boolean,
    createdTime timestamp    not null default current_timestamp,
    updatedTime timestamp    not null default current_timestamp,
    primary key (id)
)