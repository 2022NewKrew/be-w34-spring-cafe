drop table reply IF EXISTS;
drop table article IF EXISTS;
drop table users IF EXISTS;

create table if not exists users (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT
                    , userId varchar(15) NOT NULL
                    , email varchar(40) NOT NULL
                    , name varchar(20) NOT NULL
                    , password varchar(30) NOT NULL);


create table if not exists article (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT
                    , authorId BIGINT NOT NULL
                    , author varchar(15) NOT NULL
                    , title varchar(50) NOT NULL
                    , content TEXT NOT NULL
                    , createdAt DATE NOT NULL default CURRENT_TIMESTAMP
                    , numOfComment number NOT NULL default 0
                    , isDeleted varchar(1) NOT NULL default 'N'
                    , CONSTRAINT authorId FOREIGN KEY (authorId) references users (id));

create table if not exists reply(
                    id BIGINT PRIMARY KEY AUTO_INCREMENT
                    , articleId BIGINT NOT NULL
                    , replyerId BIGINT NOT NULL
                    , content TEXT NOT NULL
                    , createdAt DATE NOT NULL default CURRENT_TIMESTAMP
                    , isDeleted varchar(1) NOT NULL default 'N'
                    , CONSTRAINT articleId FOREIGN KEY (articleId) references article (id)
                    , CONSTRAINT replyerId FOREIGN KEY (replyerId) references users (id));
