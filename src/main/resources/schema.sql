DROP TABLE IF EXISTS replies;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS articles;

CREATE TABLE IF NOT EXISTS articles (
    article_id INT AUTO_INCREMENT,
    writer VARCHAR(32),
    title VARCHAR(100),
    contents VARCHAR(1000),
    createTime TIMESTAMP,
    PRIMARY KEY (article_id)
                                   );

CREATE TABLE IF NOT EXISTS users(
    id INT AUTO_INCREMENT,
    userId VARCHAR(32),
    password VARCHAR(32),
    userName VARCHAR(32),
    email VARCHAR(64),
    PRIMARY KEY (id),
    UNIQUE KEY (userId)
                                );

CREATE TABLE IF NOT EXISTS replies
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    article_id INT,
    writer VARCHAR(32),
    contents VARCHAR(255),
    createTime TIMESTAMP,
    FOREIGN KEY (article_id) REFERENCES articles(article_id) ON DELETE SET NULL,
    FOREIGN KEY (writer) REFERENCES users(userId) ON DELETE SET NULL
);