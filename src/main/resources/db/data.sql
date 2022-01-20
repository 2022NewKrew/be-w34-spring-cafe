INSERT
INTO USER (id, accid, accpw, name, email)
VALUES (1, 'a', 'a', 'testNameA', 'javajigi@slipp.net'),
       (2, 'b', 'b', 'testNameB', 'sanjigi@slipp.net');

INSERT
INTO ARTICLE(id, writer, title, contents, createddate, createdtime)
VALUES (1, 'testNameA', 't1', 'c1', '1일', '1시간'),
       (2, 'testNameB', 't2', 'c2', '아무렇게나', '저장테스트');