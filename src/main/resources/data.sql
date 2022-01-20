INSERT INTO USERS (userId, password, name, email) VALUES ('jaden.dev', '123', '허홍준', 'jaden.dev@kakaocorp.com')
INSERT INTO USERS (userId, password, name, email) VALUES ('jiwon.lee', '456', '이지원', 'jwjw210@naver.com')

INSERT INTO ARTICLES (userId, writer, title, contents) VALUES ('jaden.dev', '허홍준', 'hi', 'hello world!')
INSERT INTO ARTICLES (userId, writer, title, contents) VALUES ('jiwon.lee', '이지원', 'hello', 'nice to meet you!')

INSERT INTO COMMENTS (articleId, userId, writer, contents) VALUES (1, 'jaden.dev', '허홍준', 'hi')
INSERT INTO COMMENTS (articleId, userId, writer, contents) VALUES (1, 'jiwon.lee', '이지원', 'hello')
