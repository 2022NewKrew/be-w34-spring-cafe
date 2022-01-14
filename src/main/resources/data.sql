insert into users(nickname, email, password) values('test1', 'test1@aaa.com', '1234');
insert into users(nickname, email, password) values('test2', 'test2@aaa.com', '1234');
insert into users(nickname, email, password) values('test3', 'test3@aaa.com', '1234');

insert into articles(title, author, content) values('1번 게시글', 'test1', '안녕하세요, 첫 게시글입니다.');
insert into articles(title, author, content) values('2번 게시글', 'test2', '안녕하세요, 두 번째 게시글입니다.');
insert into articles(title, author, content) values('3번 게시글', 'test3', '안녕하세요, 세 번째 게시글입니다.');

insert into comments(author, content, articleId) values('test2', '첫 게시글에 test2 유저가 댓글 달러 왔습니다.', 1);
insert into comments(author, content, articleId) values('test3', '첫 게시글에 test3 유저가 댓글 달러 왔습니다.', 1);

insert into comments(author, content, articleId) values('test1', '두 번째 게시글에 test1 유저가 댓글 달러 왔습니다.', 2);
insert into comments(author, content, articleId) values('test3', '두 번째 게시글에 test3 유저가 댓글 달러 왔습니다.', 2);

insert into comments(author, content, articleId) values('test1', '세 번째 게시글에 test1 유저가 댓글 달러 왔습니다.', 3);
insert into comments(author, content, articleId) values('test2', '세 번째 게시글에 test2 유저가 댓글 달러 왔습니다.', 3);
