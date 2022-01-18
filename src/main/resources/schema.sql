create table if not exists users (
    user_id varchar(20) not null primary key,
    password varchar(20) not null,
    username varchar(20) not null,
    email varchar(30) not null
);

create table if not exists articles (
    id bigint not null primary key auto_increment,
    author_id varchar(20),
    title varchar(30) not null,
    content BLOB not null,
    created_at varchar(20) not null,
    foreign key(author_id) references users(user_id) on delete set null
);
