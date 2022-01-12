CREATE TABLE IF NOT EXISTS Member
(
    id integer NOT NULL auto_increment,
    name varchar(255) NOT NULL,
    userId varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    email varchar(255),
    createdAt timestamp,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Post
(
    id integer NOT NULL auto_increment,
    title varchar(255) not null,
    content varchar(255) not null,
    createdAt timestamp,
    viewCount integer,
    writerId integer NOT NULL,
    foreign key(writerId) references Member(id),
    primary key(id)
);