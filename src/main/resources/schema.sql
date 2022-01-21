DROP TABLE IF EXISTS replies;
DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id       int auto_increment primary key,
    userId   varchar(20) not null unique,
    password varchar(20) not null,
    name     varchar(20) not null,
    email    varchar(50) not null
);
CREATE TABLE articles
(
    id       int auto_increment primary key,
    writer   varchar(20),
    title    varchar(50) not null,
    contents longtext    not null,
    deleted  boolean     not null default false,
    foreign key (writer) references users (userId) on update cascade
);
CREATE TABLE replies
(
    id       int auto_increment primary key,
    writer   varchar(20) not null,
    article  int         not null,
    contents varchar(50) not null,
    foreign key (writer) references users (userId) on update cascade,
    foreign key (article) references articles (id) on update cascade
);
