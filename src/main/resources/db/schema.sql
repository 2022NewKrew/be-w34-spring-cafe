DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    user_id     BigInt       not null auto_increment,
    user_name   varchar(256) not null,
    user_pw     varchar(256) not null,
    primary key (user_id)
);