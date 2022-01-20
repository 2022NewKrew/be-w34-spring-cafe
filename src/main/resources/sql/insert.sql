INSERT INTO USERS (user_id, password, user_name, email) VALUES ('javajigi', 'test', '자바지기', 'javajigi@slipp.net');
INSERT INTO USERS (user_id, password, user_name, email) VALUES ('sanjigi', 'test', '산지기', 'sanjigi@slipp.net');
INSERT INTO USERS (user_id, password, user_name, email) VALUES ('jeuslhg', '1234', '이희관', 'jeuslhg@gmail.com');


INSERT INTO articles (user_id, title, contents) VALUES ('jeuslhg', '안녕하세요. 뉴크루', '이희관 입니다.');
INSERT INTO articles (user_id, title, contents) VALUES ('javajigi', '안녕하세요. 자바지기 입니다.', '자바지기 입니다.');

INSERT INTO replies (article_id, writer_id, comment, created_time) VALUES (1, 'jeuslhg', '1빠', NOW());
INSERT INTO replies (article_id, writer_id, comment, created_time) VALUES (1, 'javajigi', '2빠', NOW());
INSERT INTO replies (article_id, writer_id, comment, created_time) VALUES (2, 'javajigi', '1빠', NOW());