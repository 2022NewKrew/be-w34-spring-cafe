create table if not exists member(
    userId varchar(10) primary key,
    password varchar(20) not null,
    name varchar(20) not null,
    email varchar(20) not null
);

