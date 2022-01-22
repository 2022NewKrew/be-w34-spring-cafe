CREATE TABLE IF NOT EXISTS user_info (
    id INTEGER AUTO_INCREMENT,
    email VARCHAR(30) UNIQUE,
    name VARCHAR(30),
    password VARCHAR(30),
    creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS article (
    id INTEGER AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    title VARCHAR(255),
    content TEXT,
    deleted BOOLEAN DEFAULT false,
    creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_info (id)
);

CREATE TABLE IF NOT EXISTS reply (
    id INTEGER AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    article_id INTEGER NOT NULL,
    content TEXT,
    deleted BOOLEAN DEFAULT false,
    creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_info (id),
    FOREIGN KEY (article_id) REFERENCES article (id)
);
