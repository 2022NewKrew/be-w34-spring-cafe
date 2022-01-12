create table if not exists article_table (
    id          bigint                      primary key     auto_increment,
    writer      varchar2(50)    not null,
    title       varchar2(100)   not null,
    contents    clob            not null,
    created_at  datetime2       not null    default now()
);

create table if not exists user_table (
    id          bigint                      primary key     auto_increment,
    user_id     varchar2(50)    not null    unique,
    password    varchar2(520)   not null,
    name        varchar2(50)    not null,
    email       varchar2(70),
    created_at  datetime2                   default now()
);
