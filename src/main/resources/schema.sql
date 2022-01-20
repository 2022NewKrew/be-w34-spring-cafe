DROP TABLE IF EXISTS article CASCADE;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS reply;

CREATE TABLE article
(
    id integer NOT NULL AUTO_INCREMENT,
    title varchar(255),
    content varchar(255),
    writer varchar(255) not null,
    date timestamp,
    deleted boolean,
    primary key(id)
);

CREATE TABLE `user`
(
    id integer NOT NULL AUTO_INCREMENT,
    userId varchar(255) not null,
    password varchar(255) not null,
    name varchar(255),
    email varchar(255),
    primary key(userId)
);

CREATE TABLE reply
(
    id integer NOT NULL AUTO_INCREMENT,
    writer varchar(255) not null,
    content varchar(255),
    date timestamp,
    articleId integer not null,
    primary key(id),
    foreign key(articleId) references article(id) on delete cascade
);
