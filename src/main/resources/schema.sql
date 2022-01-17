DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ATRICLES;
CREATE TABLE IF NOT EXISTS USERS(ID INT AUTO_INCREMENT PRIMARY KEY, USER_ID VARCHAR(255) NOT NULL, PASSWORD VARCHAR(16) NOT NULL, EMAIL VARCHAR(255) NOT NULL, REGISTER_DATE VARCHAR(255) NOT NULL );
CREATE TABLE IF NOT EXISTS ARTICLES(ID INT AUTO_INCREMENT PRIMARY KEY, TITLE VARCHAR(255) NOT NULL, CONTENT VARCHAR(255), CREATE_USER_ID VARCHAR(255), CREATE_DATE VARCHAR(255) NOT NULL, VIEWS INT NOT NULL);