DROP TABLE IF EXISTS `USER`;
CREATE TABLE IF NOT EXISTS `USER` (
    id INT auto_increment,
    userId VARCHAR(32) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(32) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `POST`;
CREATE TABLE IF NOT EXISTS `POST` (
    id INT auto_increment,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    writer VARCHAR(32) NOT NULL,
    regDate DATE DEFAULT now(),
    PRIMARY KEY (id)
);
