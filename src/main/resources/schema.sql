DROP TABLE IF EXISTS account;

CREATE TABLE IF NOT EXISTS account (
    user_id VARCHAR(32),
    name VARCHAR(32),
    password VARCHAR(32),
    email VARCHAR(32),
    PRIMARY KEY (user_id)
    );


DROP TABLE IF EXISTS article;

CREATE TABLE IF NOT EXISTS article (
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(64),
    content TEXT,
    create_time DATETIME,
    PRIMARY KEY (id)
    );
