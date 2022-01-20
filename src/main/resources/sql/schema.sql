create table QUESTION_POST (
    question_post_id bigint auto_increment,
    title varchar(100),
    content varchar(100),
    created_at timestamp,
    view_count int,
    user_account_id bigint,
    primary key (question_post_id)
);

create table USER_ACCOUNT (
    user_account_id bigint auto_increment,
    username varchar(100),
    password varchar(100),
    email varchar(100),
    created_at timestamp,
    primary key (user_account_id)
);

create table COMMENT (
    comment_id bigint auto_increment,
    content varchar(100),
    created_at timestamp,
    question_post_id bigint,
    user_account_id bigint,
    primary key (comment_id)
);