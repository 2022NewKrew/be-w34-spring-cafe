drop table USER_PROFILE if exists;
drop table QNA if exists;

create table USER_PROFILE
(
    id       INT AUTO_INCREMENT,
    user_id  VARCHAR NOT NULL UNIQUE ,
    password VARCHAR NOT NULL,
    name     VARCHAR NOT NULL,
    email    VARCHAR,
    PRIMARY KEY (id)
);

create table QNA
(
    index    INT AUTO_INCREMENT,
    writer   VARCHAR NOT NULL,
    title    VARCHAR NOT NULL,
    contents VARCHAR NOT NULL,
    PRIMARY KEY (index)
);
