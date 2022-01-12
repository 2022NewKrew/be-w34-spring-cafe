create table if not exists UserTable
(
    id       bigint      not null primary key auto_increment,
    userId   varchar(20) not null unique,
    password varchar(20) not null,
    email    varchar(50) not null,
    time     DATETIME    not null
);

create table if not exists ArticleTable
(
    id       bigint     not null primary key auto_increment,
    writerID bigint     not null,
    title    char(100)  not null,
    contents char(1000) not null,
    time     DATETIME   not null,
    foreign key (writerID) references UserTable (id)
)
