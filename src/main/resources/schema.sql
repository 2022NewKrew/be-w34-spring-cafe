CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(30) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS Qnas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    writer VARCHAR(30) NOT NULL,
    title VARCHAR(255) NOT NULL,
    contents TEXT NOT NULL,
    create_time DATETIME NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO users (user_id, password, name, email) VALUES ('ato', '123', 'ato', 'abc@naver.com');
INSERT INTO qnas (writer, title, contents, create_time) VALUES ('ato', 'title', 'contents', '2022-01-13 18:38:00.0000');
INSERT INTO qnas (writer, title, contents, create_time) VALUES ('ato', 'title2', 'contents2', '2022-01-13 18:38:00.0000');
