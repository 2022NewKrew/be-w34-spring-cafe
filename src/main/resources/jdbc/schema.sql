CREATE TABLE IF NOT EXISTS USERS (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId VARCHAR(15) NOT NULL UNIQUE,
    password VARCHAR(15) NOT NULL,
    name VARCHAR(15) NOT NULL UNIQUE,
    email VARCHAR(30) NOT NULL,
    createdAt TIMESTAMP DEFAULT NOW(),
    updatedAt TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS ARTICLE (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    author VARCHAR(15) NOT NULL,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP DEFAULT NOW(),
    updatedAt TIMESTAMP DEFAULT NOW(),
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY(author) REFERENCES USERS(userId) ON DELETE CASCADE
);