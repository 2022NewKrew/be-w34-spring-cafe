DROP TABLE IF EXISTS users, articles, comments;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(32),
  password VARCHAR(32),
  name VARCHAR(32),
  email VARCHAR(32)
);

CREATE TABLE articles (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100),
  contents TEXT,
  writer_id INT REFERENCES users (id)
);

CREATE TABLE comments (
  id INT AUTO_INCREMENT PRIMARY KEY,
  contents TEXT,
  writer_id INT REFERENCES users(id),
  article_id INT REFERENCES articles(id)
);
