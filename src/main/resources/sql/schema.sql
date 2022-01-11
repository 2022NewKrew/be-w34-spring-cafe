drop table if exists users CASCADE;

create table users
(
    id bigint primary key auto_increment,
    userId varchar(255),
    password varchar(255),
    name varchar(255),
    email varchar(255),
    joinDateTime TIMESTAMP
);

drop table if exists article CASCADE;

create table articles
(
    id bigint primary key auto_increment,
    writer bigint,
    title varchar(255),
    contents CLOB,
    registerDateTime TIMESTAMP,
    foreign key (writer) references users(id)
);