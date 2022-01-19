create table qna_user (
    userId varchar(255) NOT NULL ,
    password varchar(255) NOT NULL ,
    name varchar(255) NOT NULL ,
    email varchar(255) NOT NULL ,
    PRIMARY KEY (userId)
);

create table article (
    articleId int NOT NULL AUTO_INCREMENT,
    writer varchar(255) NOT NULL ,
    title varchar(255) NOT NULL ,
    contents varchar(255) NOT NULL ,
    date varchar(255) NOT NULL ,
    userId varchar(255) ,
    PRIMARY KEY (articleId) ,
    FOREIGN KEY (userId) REFERENCES qna_user(userId)
);

create table reply (
    replyId int NOT NULL AUTO_INCREMENT,
    writer varchar(255) NOT Null ,
    comment varchar(255) NOT NULL ,
    date varchar(255) NOT NULL ,
    articleId int NOT NULL ,
    userId varchar(255) NOT NULL ,
    PRIMARY KEY (replyId) ,
    FOREIGN KEY (articleId) REFERENCES article(articleId)
                   ON DELETE CASCADE ,
    FOREIGN KEY (userId) REFERENCES qna_user(userId)
        ON DELETE CASCADE
);
