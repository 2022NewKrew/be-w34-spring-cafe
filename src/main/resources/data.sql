INSERT INTO USERS(USERID, NICKNAME, EMAIL, PASSWORD)
VALUES ('제이지', '힙찔이', 'jayz@kakaocorp.com', '123123');
INSERT INTO USERS(USERID, NICKNAME, EMAIL, PASSWORD)
VALUES ('벤', 'ben', 'benlee73@naver.com', 'aa');

SELECT ID, USERID, NICKNAME, EMAIL, PASSWORD
FROM USERS;

INSERT INTO POST(WRITER, TITLE, CONTENT)
VALUES ('제이지', '오늘 날씨 어때요?', '집 밖에 안나가서 모르겠네요.');

SELECT ID, WRITER, TITLE, CONTENT
FROM POST;
