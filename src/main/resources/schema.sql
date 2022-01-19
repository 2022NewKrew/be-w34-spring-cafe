create table if not exists member(
    userId varchar(10) primary key,
    password varchar(20) not null,
    name varchar(20) not null,
    email varchar(20) not null
);

create table if not exists post(
    id long primary key,
    title varchar(40) not null,
    content varchar(200) not null,
    writerName varchar(10) not null,
    timeWritten datetime not null,
    isHidden boolean default false
);

create table if not exists comment(
    id long primary key,
    postId long references post (id),
    writerName varchar(10) not null,
    content varchar(200) not null
);