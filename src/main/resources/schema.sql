DROP TABLE IF EXISTS article;
CREATE TABLE IF NOT EXISTS article(id INTEGER PRIMARY KEY AUTO_INCREMENT, writer VARCHAR(32), title VARCHAR(32), contents VARCHAR(32));