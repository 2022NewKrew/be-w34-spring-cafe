drop table QNA if exists;
drop table USER_PROFILE if exists;
drop table COMMENT if exists;

create table QNA
(
    id    int auto_increment
        primary key,
    writer     varchar(30)          not null,
    title      varchar(40)          not null,
    contents   varchar(255)         not null,
    deleted    tinyint(1) default 0 not null,
    created_at datetime             null
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

create table COMMENT
(
    id         int auto_increment
        primary key,
    writer     varchar(30)          not null,
    contents   varchar(255)         not null,
    qna_id  int                  not null,
    created_at datetime             null,
    deleted    tinyint(1) default 0 not null
);

insert into USER_PROFILE(user_id, password, name, email)
VALUES ('lucas', '123', 'lucas', 'test@daum.net'),
       ('lucas2', '123', 'lucas2', 'test@daum.net');

insert into QNA(writer, title, contents)
VALUES ('lucas', 'h2 test', 'h2 test'),
       ('lucas', 'h22 test', 'h22 test');

insert into COMMENT(writer, contents, qna_id, created_at)
VALUES ('lucas', 'test1', '1', PARSEDATETIME('2022-01-19 00:00:00', 'yyyy-MM-dd hh:mm:ss')),
       ('lucas', 'test2', '1', PARSEDATETIME('2022-01-19 00:00:01', 'yyyy-MM-dd hh:mm:ss')),
       ('lucas', 'test3', '2', PARSEDATETIME('2022-01-19 00:00:02', 'yyyy-MM-dd hh:mm:ss'));
