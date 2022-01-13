CREATE TABLE IF NOT EXISTS users (
    id bigint not null auto_increment,
    nickname varchar(32),
    email varchar(32),
    password varchar(32),
    created timestamp default current_timestamp
    );

CREATE TABLE IF NOT EXISTS articles (
    id bigint not null auto_increment,
    author varchar(32),
    title varchar(32),
    content text,
    views int default 0,
    created timestamp default current_timestamp
    );

CREATE TABLE IF NOT EXISTS comments (
    id bigint not null auto_increment,
    articleId bigint,
    author varchar(32),
    content text,
    created timestamp default current_timestamp
    );
