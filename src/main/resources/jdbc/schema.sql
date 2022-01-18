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
    FOREIGN KEY(user_id) REFERENCES userlist(id)
);
