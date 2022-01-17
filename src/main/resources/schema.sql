-- qna
DROP TABLE IF EXISTS QNA;
CREATE TABLE IF NOT EXISTS QNA(
    qnaId long NOT NULL auto_increment,
    title VARCHAR(100),
    content VARCHAR(1000),
    views long NOT NULL DEFAULT 0,
    createDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY(qnaId)
);
