DROP TABLE IF EXISTS articles;

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    user_id    bigint      NOT NULL AUTO_INCREMENT,
    email      varchar(50) NOT NULL UNIQUE,
    password   varchar(20) NOT NULL,
    nickname   varchar(10) NOT NULL UNIQUE,
    created_at timestamp   NOT NULL default NOW(),
    PRIMARY KEY (user_id)
);

CREATE TABLE articles
(
    article_id bigint        NOT NULL AUTO_INCREMENT,
    title      varchar(50)   NOT NULL,
    body       varchar(5000) NOT NULL,
    created_at timestamp     NOT NULL default NOW(),
    view_count int           NOT NULL default 0,
    author_id  bigint        NOT NULL,
    PRIMARY KEY (article_id),
    FOREIGN KEY (author_id) references users (user_id)
);

CREATE TABLE comments
(
    comment_id bigint       NOT NULL AUTO_INCREMENT,
    body       varchar(500) NOT NULL,
    created_at timestamp    NOT NULL default NOW(),
    author_id  bigint       NOT NULL,
    article_id bigint       NOT NULL,
    PRIMARY KEY (comment_id),
    FOREIGN KEY (author_id) references users (user_id),
    FOREIGN KEY (article_id) references articles (article_id)
)

