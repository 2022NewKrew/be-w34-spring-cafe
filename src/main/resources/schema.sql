DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_date DATETIME NOT NULL
);

CREATE TABLE articles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    view_num INT NOT NULL,
    created_date DATETIME NOT NULL,

    FOREIGN KEY (user_id)
    REFERENCES users(id) ON UPDATE CASCADE
);

INSERT INTO users (email, nickname, password, created_date) VALUES ('aa@aa.com','멋진삼','aaaa',now());
INSERT INTO articles (user_id, title, content, view_num, created_date) values (1, '오늘의 점심', '짜파게티와 짜장면 중 뭘 먹을까?\n여러분의 선택은?', 2, now());
INSERT INTO articles (user_id, title, content, view_num, created_date) values (1, '오늘의 저녁', '오징어짬뽕과 짬뽕 중 뭘 먹을까?\n여러분의 선택은?', 5, now());
