DROP TABLE IF EXISTS post;
CREATE TABLE IF NOT EXISTS member (
    userId varchar(20) primary key,
    password varchar(20) not null,
    email varchar(40) not null unique,
    tombstone bool default false
);

DROP TABLE IF EXISTS post;
CREATE TABLE IF NOT EXISTS post (
    postId int auto_increment primary key,
    userId varchar(20) not null,
    title varchar(30) not null,
    content varchar(500) not null,
    createdAt timestamp default now(),
    tombstone bool default false,
    foreign key(userId) references member(userId)
);

INSERT INTO member (userId, password, email)
VALUES ('1','1','1@email.com'),
       ('2','2','2@email.com'),
       ('3','3','3@email.com'),
       ('4','4','4@email.com');

INSERT INTO post (userId, title, content)
VALUES ('1', '1번이 작성했어요', '1번이 작성한 내용이예요.'),
       ('2', '2번이 작성했어요', '2번이 작성한 내용이예요.'),
       ('3', '3번이 작성했어요', '3번이 작성한 내용이예요.'),
       ('4', '4번이 작성했어요', '4번이 작성한 내용이예요.'),
       ('1', '1번이 2번째 작성했어요', '1번이 2번째 작성한 내용이예요.'),
       ('1', '1번이 3번째 작성했어요', '1번이 3번째 작성한 내용이예요.'),
       ('2', '2번이 2번째 작성했어요', '2번이 2번째 작성한 내용이예요.');