DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    key        BigInt       not null auto_increment,
    userId     varchar(256) not null,
    userName   varchar(256) not null,
    userPw     varchar(256) not null,
    userEmail  varchar(256) not null,
    primary key (key)
);