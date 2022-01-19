CREATE TABLE IF NOT EXISTS USERS
(
    user_seq_id      BIGINT  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS ARTICLE (
    article_seq_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    writer_id VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    contents TEXT NOT NULL
);