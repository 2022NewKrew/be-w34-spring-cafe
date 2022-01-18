DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id       INT AUTO_INCREMENT,
    user_id  VARCHAR(30) UNIQUE,
    password VARCHAR(30),
    name     VARCHAR(30),
    email    VARCHAR(30),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS article;

CREATE TABLE article
(
    id          INT AUTO_INCREMENT,
    writer      VARCHAR(30),
    title       VARCHAR(30),
    contents    LONGTEXT,
    create_time DATETIME DEFAULT CURRENT_TIME,
    modify_time DATETIME DEFAULT CURRENT_TIME,
    is_deleted  BOOLEAN  DEFAULT FALSE,
    PRIMARY KEY (id)
);
