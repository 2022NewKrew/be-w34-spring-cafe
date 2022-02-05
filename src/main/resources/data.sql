INSERT INTO USERS
VALUES ('abcde', '#asdf1234', '가나다', 'abcd@test.com'),
       ('54321', 'qlalfqjsgh486%', 'bbb', '54321@gmail.com'),
       ('test777', '1234$xptmxm', 'test', 'test777@daum.net');

INSERT INTO ARTICLES (WRITER_ID, WRITE_TIME, TITLE, CONTENTS)
VALUES ('54321', PARSEDATETIME('2016-11-17 12:00:00', 'yyyy-MM-dd hh:mm:ss'), '테스트1', '테스트 게시글1 입니다.'),
       ('abcde', PARSEDATETIME('2016-11-17 12:00:00', 'yyyy-MM-dd hh:mm:ss'), '테스트2', '테스트 게시글2 입니다.'),
       ('test777', PARSEDATETIME('2016-11-17 12:00:00', 'yyyy-MM-dd hh:mm:ss'), '테스트3', '테스트 게시글3 입니다.');

INSERT INTO COMMENTS (ARTICLE_ID, USER_ID, WRITE_TIME, CONTENTS)
VALUES (1, 'abcde', PARSEDATETIME('2016-11-17 12:00:00', 'yyyy-MM-dd hh:mm:ss'), '테스트 댓글 1 입니다.'),
       (1, 'test777', PARSEDATETIME('2016-11-17 12:00:00', 'yyyy-MM-dd hh:mm:ss'), '테스트 댓글 2 입니다.'),
       (2, 'abcde', PARSEDATETIME('2016-11-17 12:00:00', 'yyyy-MM-dd hh:mm:ss'), '테스트 댓글 3 입니다.');