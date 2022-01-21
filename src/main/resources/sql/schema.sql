SET FOREIGN_KEY_CHECKS = 0;

drop table if exists users CASCADE;

create table users
(
    id bigint primary key auto_increment,
    userId varchar(255),
    password varchar(255),
    name varchar(255),
    email varchar(255),
    joinDateTime TIMESTAMP,
    isDeleted boolean DEFAULT false
);

drop table if exists articles CASCADE;

create table articles
(
    id bigint primary key auto_increment,
    writer bigint,
    title varchar(255),
    contents TEXT,
    registerDateTime TIMESTAMP,
    isDeleted boolean DEFAULT false,
    foreign key (writer) references users(id) on delete CASCADE
) CHARSET='utf8mb4';

drop table if exists comments CASCADE;

create table comments
(
    id bigint primary key auto_increment,
    writerId bigint,
    articleId bigint,
    contents TEXT,
    registerDateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    isDeleted boolean DEFAULT false,
    foreign key (writerId) references users(id) on delete CASCADE,
    foreign key (articleId) references articles(id) on delete CASCADE
);

SET FOREIGN_KEY_CHECKS = 1;