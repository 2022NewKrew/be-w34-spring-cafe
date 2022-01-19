drop table if exists reply;
drop table if exists article_content;
drop table if exists article;
drop table if exists user;


create table user
(
    id       bigint       not null auto_increment primary key,
    user_id  varchar(255) not null,
    password varchar(255) not null,
    name     varchar(255) not null,
    email    varchar(255) not null
);

create index user_id_idx on user (user_id);

create table article
(
    id        bigint       not null primary key,
    title     varchar(255) not null,
    writer    varchar(255) not null,
    writer_id bigint       not null,
    foreign key (writer_id) references user (id)
);

create table article_content
(
    id         bigint       not null auto_increment primary key,
    body       varchar(255) not null,
    article_id bigint       not null,
    foreign key (article_id) references article (id)
);

create table reply
(
    id         bigint       not null auto_increment primary key,
    content    varchar(255) not null,
    article_id bigint       not null,
    writer_id  bigint       not null,
    status     boolean      not null,
    foreign key (article_id) references article (id)
);