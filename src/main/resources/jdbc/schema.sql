CREATE TABLE IF NOT EXISTS userlist (
    idx BIGINT PRIMARY KEY AUTO_INCREMENT,
    id VARCHAR(12) UNIQUE NOT NULL,
    password CHAR(60) NOT NULL,
    name VARCHAR(32) NOT NULL,
    email VARCHAR(127) NOT NULL
);

CREATE TABLE IF NOT EXISTS article (
    idx BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(12) NOT NULL,
    title VARCHAR(255) NOT NULL,
    body VARCHAR(4095) NOT NULL,
    created_at BIGINT NOT NULL,
    modified_at BIGINT NOT NULL,
    deleted BOOL DEFAULT false,
    count_comments MEDIUMINT DEFAULT 0,
    FOREIGN KEY(user_id) REFERENCES userlist(id),
    INDEX deleted(deleted)
);

CREATE TABLE IF NOT EXISTS comment (
    idx BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(12) NOT NULL,
    article_idx BIGINT NOT NULL,
    body VARCHAR(400) NOT NULL,
    created_at BIGINT NOT NULL,
    modified_at BIGINT NOT NULL,
    deleted BOOL DEFAULT false,
    FOREIGN KEY(user_id) REFERENCES userlist(id),
    FOREIGN KEY(article_idx) REFERENCES article(idx),
    INDEX deleted(deleted)
);
