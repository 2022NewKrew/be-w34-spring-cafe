DROP TABLE IF EXISTS USERS;
CREATE TABLE IF NOT EXISTS USERS (
                                     id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                     user_id VARCHAR NOT NULL UNIQUE,
                                     password VARCHAR NOT NULL,
                                     name VARCHAR,
                                     email VARCHAR
);

DROP TABLE IF EXISTS ARTICLE;
CREATE TABLE IF NOT EXISTS ARTICLE (
                                       id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                       author VARCHAR NOT NULL,
                                       title VARCHAR NOT NULL,
                                       content CLOB,
                                       created_at DATETIME,
                                       updated_at DATETIME
);
