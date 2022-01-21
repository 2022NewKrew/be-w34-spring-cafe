INSERT INTO USERS (id, userId, email, name, password) VALUES (11, 'javajigi', 'javajigi@slipp.net', '자바지기', 'test');
INSERT INTO USERS (id, userId, email, name, password) VALUES (21, 'sanjigi', 'sanjigi@slipp.net', '산지기', 'test');
INSERT INTO USERS (id, userId, email, name, password) VALUES (31, 'test', 'p@d', '테스터', 'test');

INSERT INTO ARTICLE (id, authorId, author, title, content, createdAt, numOfComment, isDeleted)
                VALUES (999, 11, '자바지기', '스프링 안돼요', '그냥 안돼요', current_timestamp, 0, 'N');
INSERT INTO ARTICLE (id, authorId, author, title, content, createdAt, numOfComment, isDeleted)
                VALUES (1000, 21, '산지기', '미션 안돼요', '모르겠고 그냥 안됨', current_timestamp, 0, 'N');
INSERT INTO ARTICLE (id, authorId, author, title, content, createdAt, numOfComment, isDeleted)
                VALUES (1001, 31, '테스터', '댓글까지~', 'ㅎㅇㅎㅇ', current_timestamp, 0, 'N');

INSERT INTO REPLY (articleId, replyerId, content) VALUES (999, 11, 'hihi');
INSERT INTO REPLY (articleId, replyerId, content) VALUES (999, 11, '나만 작성함 댓글');
INSERT INTO REPLY (articleId, replyerId, content) VALUES (999, 11, '그러니 이글은 삭제 가능');

INSERT INTO REPLY (articleId, replyerId, content) VALUES (1000, 21, '글 작성자의 댓글임');
INSERT INTO REPLY (articleId, replyerId, content) VALUES (1000, 11, '다른 사람의 댓글');
INSERT INTO REPLY (articleId, replyerId, content) VALUES (1000, 11, '삭제 불가능!');
