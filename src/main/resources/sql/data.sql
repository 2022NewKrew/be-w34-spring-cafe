INSERT INTO USERS(USER_ID, PASSWORD, NAME, EMAIL)
VALUES ('admin', 'admin', '관리자', 'admin@kafe.com'),
       ('jynah92', '1111', '나준엽', 'jynah92@kakao.com');

INSERT INTO ARTICLES(TITLE, WRITER_ID, CONTENT, CREATED_DATE, IS_DELETED)
VALUES ('안녕하세요', 'admin', '안녕하세요, 관리자입니다.', NOW(), FALSE);

INSERT INTO COMMENTS(ARTICLE_ID, WRITER_ID, CONTENT, CREATED_DATE, IS_DELETED)
VALUES (1, 'jynah92', '안녕하세요!', NOW(), FALSE);