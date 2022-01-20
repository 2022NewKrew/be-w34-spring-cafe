DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id long NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id varchar(20) UNIQUE NOT NULL,
    password varchar(50) NOT NULL,
    user_name varchar(10) NOT NULL,
    email varchar(50) NOT NULL
);

DROP TABLE IF EXISTS articles;
CREATE TABLE articles (
   id long NOT NULL AUTO_INCREMENT PRIMARY KEY,
   user_id varchar(20) NOT NULL,
   title varchar(100) NOT NULL,
   contents varchar(1000) NOT NULL,
   is_deleted boolean DEFAULT false NOT NULl,
   FOREIGN KEY (user_id) REFERENCES users(user_id)
);

DROP TABLE IF EXISTS replies;
CREATE TABLE replies (
    id long NOT NULL AUTO_INCREMENT PRIMARY KEY,
    article_id long NOT NULL,
    writer_id varchar(20) NOT NULL,
    comment varchar(500) NOT NULL,
    created_time DATETIME NOT NULL,
    updated_time DATETIME,
    is_deleted boolean DEFAULT false NOT NULL,
    FOREIGN KEY (article_id) REFERENCES articles(id),
    FOREIGN KEY (writer_id) REFERENCES users(user_id)
);

CREATE INDEX idx_article_id ON replies(article_id);