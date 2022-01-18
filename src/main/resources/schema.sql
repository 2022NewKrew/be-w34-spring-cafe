drop table article IF EXISTS;
drop table users IF EXISTS;

create table if not exists users (id BIGINT PRIMARY KEY
                    , userId varchar(15)
                    , email varchar(40)
                    , name varchar(20)
                    , password varchar(30));


create table if not exists article (id BIGINT NOT NULL
                    , authorId BIGINT NOT NULL
                    , author varchar(15)
                    , title varchar(50)
                    , content TEXT
                    , createdAt DATE
                    , numOfComment number
                    , CONSTRAINT authorId FOREIGN KEY (authorId) references users (id));
