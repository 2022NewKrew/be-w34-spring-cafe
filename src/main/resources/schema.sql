DROP TABLE IF EXISTS users, articles;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(32),
  password VARCHAR(32),
  name VARCHAR(32),
  email VARCHAR(32)
);

CREATE TABLE articles (
  id INT AUTO_INCREMENT PRIMARY KEY,
  writer VARCHAR(32),
  title VARCHAR(100),
  contents TEXT
);

