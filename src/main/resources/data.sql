-- User 데이터 생성
INSERT INTO users(id,password,name,email) VALUES ('1','1','tester','tester@gmail.com');

-- Article 데이터 생성
INSERT INTO articles(user_seq, writer,title,content) VALUES ('1','1','mytitle','I like it!');
INSERT INTO articles(user_seq, writer,title,content) VALUES ('1','2','mytitle2','I like it!');

-- Reply 데이터 생성
INSERT INTO replys(user_seq, article_seq, writer, content) VALUES ('1', '1', '1', 'I like it!');
INSERT INTO replys(user_seq, article_seq, writer, content) VALUES ('1', '1', '2', 'I like it!');
