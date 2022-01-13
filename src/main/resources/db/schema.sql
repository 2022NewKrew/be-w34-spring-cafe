drop table if exists UserTable;
create table if not exists UserTable
(
    id       bigint      not null primary key auto_increment,
    userId   varchar(20) not null unique,
    password varchar(20) not null,
    email    varchar(50) not null,
    time     DATETIME    not null
);

drop table if exists ArticleTable;
create table if not exists ArticleTable
(
    id       bigint     not null primary key auto_increment,
    writerID bigint     not null,
    title    char(100)  not null,
    contents char(1000) not null,
    views    bigint     not null default 0,
    time     DATETIME   not null,
    foreign key (writerID) references UserTable (id)
)
