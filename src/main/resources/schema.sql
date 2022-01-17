DROP TABLE IF EXISTS ARTICLE;
DROP TABLE IF EXISTS MEMBER;

CREATE TABLE IF Not Exists MEMBER
(
    member_id int primary key auto_increment,
    user_id   varchar(50) unique not null,
    user_name varchar(50)        not null,
    password  varchar(50)        not null,
    email     varchar(50)        not null
);

CREATE TABLE IF NOT EXISTS ARTICLE
(
    article_id int primary key auto_increment,
    title      varchar(50) not null,
    text       clob        not null,
    author_id  int         not null,
    time       DATETIME,
    foreign key (author_id) references member (member_id)
)
