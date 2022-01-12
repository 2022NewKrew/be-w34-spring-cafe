DROP TABLE IF EXISTS ARTICLE;
CREATE TABLE IF NOT EXISTS ARTICLE (
    ARTICLE_ID INT,
    WRITER VARCHAR(255),
    TITLE VARCHAR(255),
    CONTENT VARCHAR(510),
    DATE DATE
);