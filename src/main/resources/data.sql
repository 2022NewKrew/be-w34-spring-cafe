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

CREATE TABLE IF NOT EXISTS Answer
(
    id integer NOT NULL auto_increment,
    content varchar(255) NOT NULL,
    postId integer NOT NULL,
    writerId integer NOT NULL,
    createdAt timestamp,
    isRemoved bool NOT NULL DEFAULT false,
    foreign key(writerId) references Member(id),
    foreign key(postId) references Post(id),
    primary key(id)
);