drop table QNA if exists;
drop table USER_PROFILE if exists;

create table QNA
(
    `index`  int auto_increment
        primary key,
    writer   varchar(30)  not null,
    title    varchar(40)  not null,
    contents varchar(255) not null
);

create table USER_PROFILE
(
    id       int auto_increment
        primary key,
    user_id  varchar(30) not null,
    password varchar(30) not null,
    name     varchar(30) not null,
    email    varchar(40) null,
    constraint USER_PROFILE_user_id_uindex
        unique (user_id)
);
