INSERT INTO USERS(USER_ID, PASSWORD, NAME, EMAIL)
VALUES ('abc', 'ppww', 'sans', 'abc@daum.net');
INSERT INTO USERS(USER_ID, PASSWORD, NAME, EMAIL)
VALUES ('dd13', 'gfdm', 'mutsuhiko izumi', 'dd13@konmai.com');

INSERT INTO ARTICLES(WRITER_ID, TITLE, CONTENT, CREATED_DATE)
VALUES ('dd13', '테스트제목1', '테스트내용1', '2022-01-01 03:55');
INSERT INTO ARTICLES(WRITER_ID, TITLE, CONTENT, CREATED_DATE)
VALUES ('abc', '테스트제목2', '테스트내용2테스트내용2테스트내용2', '2022-01-10 15:20');

INSERT INTO REPLIES(ARTICLE_ID, WRITER_ID, CONTENT, CREATED_DATE)
VALUES (1, 'abc', 'test reply 1', '2022-01-11 11:11');
INSERT INTO REPLIES(ARTICLE_ID, WRITER_ID, CONTENT, CREATED_DATE)
VALUES (1, 'abc', 'test reply 22222', '2022-01-11 22:22');
INSERT INTO REPLIES(ARTICLE_ID, WRITER_ID, CONTENT, CREATED_DATE)
VALUES (2, 'dd13', 'test reply 356663', '2022-01-13 10:55');
INSERT INTO REPLIES(ARTICLE_ID, WRITER_ID, CONTENT, CREATED_DATE)
VALUES (2, 'dd13', 'test reply 4748', '2022-01-18 21:48');