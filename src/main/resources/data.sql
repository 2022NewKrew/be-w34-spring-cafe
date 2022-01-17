INSERT INTO POST
VALUES (0, '오늘 날씨 어때요?', '집 밖에 안나가서 모르겠네요.');

SELECT ID, TITLE, CONTENT
FROM POST;

INSERT INTO USERS
VALUES (0, '제이지', '힙찔이', 'jayz@kakaocorp.com', '123123');
INSERT INTO USERS
VALUES (1, '벤', 'ben', 'benlee73@naver.com', 'aa');

SELECT ID, USERID, NICKNAME, EMAIL, PASSWORD
FROM USERS;
