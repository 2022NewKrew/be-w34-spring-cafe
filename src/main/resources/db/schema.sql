drop table if exists Reply;
drop table if exists Article;
drop table if exists User;

create table if not exists User
(
    id       bigint      not null primary key auto_increment,
    userId   varchar(20) not null unique,
    password varchar(20) not null,
    email    varchar(50) not null unique,
    time     DATETIME    not null default now()
);

create table if not exists Article
(
    id       bigint    not null primary key auto_increment,
    writerID bigint    not null,
    title    char(100) not null,
    contents TEXT      not null,
    views    bigint    not null default 0,
    isDelete bool      not null default FALSE,
    time     DATETIME  not null default now(),
    foreign key (writerID) references User (id)
);

create table if not exists Reply
(
    id        bigint   not null primary key auto_increment,
    writerID  bigint   not null,
    articleID bigint   not null,
    contents  TEXT     not null,
    isDelete  bool     not null default FALSE,
    time      DATETIME not null default now(),
    foreign key (writerID) references User (id),
    foreign key (articleID) references Article (id)
);
