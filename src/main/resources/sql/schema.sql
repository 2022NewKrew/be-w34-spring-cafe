DROP TABLE IF EXISTS post;
CREATE TABLE IF NOT EXISTS member (
    userId varchar(20) primary key,
    password varchar(20) not null,
    email varchar(40) not null unique,
    name varchar(12) not null,
    createdAt timestamp default now(),
    tombstone bool default false
);

DROP TABLE IF EXISTS post;
CREATE TABLE IF NOT EXISTS post (
    postId int auto_increment primary key,
    userId varchar(20) not null,
    title varchar(30) not null,
    content varchar(500) not null,
    createdAt timestamp default now(),
    view int default 0,
    tombstone bool default false,
    foreign key(userId) references member(userId)
);

DROP TABLE IF EXISTS reply;
CREATE TABLE IF NOT EXISTS reply (
     replyId int auto_increment primary key,
     postId int not null,
     userId varchar(20) not null,
     content varchar(150) not null,
     createdAt timestamp default now(),
     tombstone bool default false,
     foreign key(postId) references post(postId),
     foreign key(userId) references member(userId)
);