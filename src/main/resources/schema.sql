DROP TABLE IF EXISTS articles;

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    user_id      bigint      NOT NULL AUTO_INCREMENT,
    email        varchar(50) NOT NULL,
    password     varchar(20) NOT NULL,
    username     varchar(10) NOT NULL UNIQUE,
    created_date timestamp   NOT NULL default NOW(),
    PRIMARY KEY (user_id)
);

CREATE TABLE articles
(
    article_id       bigint        NOT NULL AUTO_INCREMENT,
    title            varchar(50)   NOT NULL,
    body             varchar(5000) NOT NULL,
    created_date     timestamp     NOT NULL default NOW(),
    count_of_Comment int           NOT NULL default 0,
    author_id        bigint        NOT NULL,
    PRIMARY KEY (article_id),
    FOREIGN KEY (author_id) references USERS (user_id)
);

