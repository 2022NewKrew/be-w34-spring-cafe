insert into users(userId, password, name, email, joinDateTime) values('testID', '1234', 'testName','123@123', '2022-01-11 19:24:58.991202')
    ON DUPLICATE KEY UPDATE name=name;
insert into users(userId, password, name, email, joinDateTime) values('gcnml10', '1234', '김민수','raon.su@kakaocorp.com', '2022-01-11 19:24:58.991202')
    ON DUPLICATE KEY UPDATE name=name;
INSERT INTO users (userId, password, name, email, joinDateTime) VALUES ('javajigi', 'test', '자바지기', 'javajigi@slipp.net', '2022-01-12 07:24:58.991202')
    ON DUPLICATE KEY UPDATE name=name;;
INSERT INTO users (userId, password, name, email, joinDateTime) VALUES ('sanjigi', 'test', '산지기', 'sanjigi@slipp.net', '2022-01-13 19:24:58.991202')
    ON DUPLICATE KEY UPDATE name=name;;

INSERT INTO articles (writer, title, contents, registerDateTime) VALUES ('1', '저기 질문이 있는데요 이 카페는 왜이리 별로예요?', '안되는게 너무 많아요 ㅜㅜ', '2022-01-13 19:24:58.991202')
    ON DUPLICATE KEY UPDATE title=title;

INSERT INTO comments (writerId, articleId, contents) VALUES ('2', '1', '그러게요 만든 사람 좀 만나보고 싶네요ㅜㅜ')