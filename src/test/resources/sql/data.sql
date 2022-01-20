INSERT INTO USERS(user_id, password, name, email) VALUES ('rain', '1111', '레인', 'rain@rain.com');
INSERT INTO USERS(user_id, password, name, email) VALUES ('cloud', '2222', '클라우드', 'cloud@cloud.com');
INSERT INTO USERS(user_id, password, name, email) VALUES ('snow', '3333', '스노우', 'snow@snow.com');

INSERT INTO ARTICLE(author_id, title, contents, comment_count) VALUES (1, '첫번째 게시물입니다.', '이건 첫번째 게시물이에요.', 4);
INSERT INTO ARTICLE(author_id, title, contents, comment_count) VALUES (2, '테스트 게시물입니다.', '이건 테스트용 게시물이에요. 잘 작성됐나요?', 1);
INSERT INTO ARTICLE(author_id, title, contents, comment_count) VALUES (1, '또 다른 테스트 게시물입니다.', '내용을 뭐라고 작성하면 좋을까요?', 1);
INSERT INTO ARTICLE(author_id, title, contents, comment_count) VALUES (3, '주말에 눈 또는 비가 올지도 모른다고 해요.', '그 날 날씨가 맑아야만 했는데...', 1);
INSERT INTO ARTICLE(author_id, title, contents) VALUES (1, '지금은 노래를 들으며 하고 있어요.', 'A sky full of stars를 듣고 있어요.');
INSERT INTO ARTICLE(author_id, title, contents, status) VALUES (2, '삭제된 글입니다.', '삭제 글 테스트', false);
INSERT INTO ARTICLE(author_id, title, contents, status) VALUES (3, '또 다른 삭제된 글', '삭제 삭제', false);

INSERT INTO REPLY(article_id, author_id, comment) VALUES (1, 1, '내가 단 댓글');
INSERT INTO REPLY(article_id, author_id, comment) VALUES (1, 2, '어쩌구 저쩌구 댓글');
INSERT INTO REPLY(article_id, author_id, comment) VALUES (1, 3, '스노우가 작성한 댓글');
INSERT INTO REPLY(article_id, author_id, comment) VALUES (1, 1, '또 다른 댓글');
INSERT INTO REPLY(article_id, author_id, comment, status) VALUES (1, 3, '스노우가 작성했는데 삭제된 댓글', false);

INSERT INTO REPLY(article_id, author_id, comment) VALUES (2, 1, '다른 사람 글에 단 댓글');

INSERT INTO REPLY(article_id, author_id, comment) VALUES (3, 1, '내가 단 또 다른 댓글');
INSERT INTO REPLY(article_id, author_id, comment, status) VALUES (3, 2, '누군가가 달았는데 삭제된 댓글', false);

INSERT INTO REPLY(article_id, author_id, comment) VALUES (4, 3, '내가 단 댓글');