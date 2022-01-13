create table if not exists users (
    user_id varchar2(20) not null primary key,
    password varchar2(20) not null,
    username varchar2(20) not null,
    email varchar2(30) not null
);

create table if not exists articles (
    id bigint not null primary key auto_increment,
    author_id varchar2(20) not null,
    title varchar2(30) not null,
    content clob not null,
    created_at varchar2(20) not null,
    foreign key(author_id) references users(user_id) on delete set null
);
