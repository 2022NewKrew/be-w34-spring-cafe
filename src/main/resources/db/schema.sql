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
create table if not exists REPLY_TABLE
(
    id          identity ,
    contents    varchar(20),
    article_Id   bigint,
    user_Id      bigint,
    time        DATETIME,
    primary key (id),
    FOREIGN KEY (article_Id) references ARTICLE_TABLE (id),
    FOREIGN KEY (user_Id) references USER_TABLE (id)
);