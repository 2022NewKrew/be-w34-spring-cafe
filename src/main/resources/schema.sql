
create table if not exists users (
    userid int not null primary key auto_increment,
    password varchar2(256) not null,
    name varchar2(256) not null,
    email varchar2(256) not null
    );


create table if not exists post (
    postid int not null primary key auto_increment,
    userid int not null,
    writer varchar2(256) not null,
    title varchar2(256) not null,
    content varchar2(256) not null,
    createddate date not null,
    isDeleted boolean not null,
    foreign key(userid) references users(userid)
    );


create table if not exists reply(
    replyid int not null primary key auto_increment,
    userid int not null,
    postid int not null,
    writer varchar2(256) not null,
    content varchar2(256) not null,
    createddate date not null,
    foreign key(userid) references users(userid),
    foreign key(postid) references post(postid)
)
