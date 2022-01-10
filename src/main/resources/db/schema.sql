DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id     BigInt       not null auto_increment,
    user_id     varchar(256) not null,
    user_name   varchar(256) not null,
    user_pw     varchar(256) not null,
    user_email  varchar(256) not null,
    primary key (id)
);