DROP TABLE IF EXISTS post;
CREATE TABLE IF NOT EXISTS member (
    userId varchar(20) primary key,
    password varchar(20) not null,
    email varchar(40) not null unique
);

DROP TABLE IF EXISTS post;
CREATE TABLE IF NOT EXISTS post (
    postId int auto_increment primary key,
    userId varchar(20) not null,
    title varchar(30) not null,
    content varchar(500) not null,
    createdAt timestamp default now(),
    foreign key(userId) references member(userId)
);

