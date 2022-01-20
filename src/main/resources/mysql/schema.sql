create table if not exists user(
    user_id varchar(50) not null,
    user_password varchar(30) not null,
    user_name varchar(50) not null,
    user_email varchar(30) not null,
    primary key(user_id)
);

create table if not exists article(
    article_id int auto_increment not null,
    user_id varchar(50) not null,
    article_created_at timestamp not null,
    article_title varchar(100) not null,
    article_contents varchar(1000) not null,
    primary key(article_id),
    foreign key(user_id) references user (user_id)
);