create table if not exists USER_TABLE
(
    id          identity,
    userId      varchar(20),
    password    varchar(20),
    name        varchar(20),
    email       varchar(20),
    time        DATETIME,
    primary key (id)
);


create table if not exists ARTICLE_TABLE
(
    id          identity ,
    writer      varchar(20),
    title       varchar(20),
    contents    varchar(255),
    time        DATETIME,
    primary key (id)
);
