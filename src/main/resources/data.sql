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

INSERT INTO REPLY(COMMENT, WRITER, POST_ID)
VALUES ('지금 밖은 엄청 화창해요!! 그런데 아침에 일기예보 보니까 저녁부터 비가 온대요~ 우산 챙기는 게 좋을 것 같습니다^^', '벤', 1);
INSERT INTO REPLY(COMMENT, WRITER, POST_ID)
VALUES ('고마워요 벤 :D', '제이지', 1);

SELECT ID, COMMENT, WRITER, POST_ID
FROM REPLY;
