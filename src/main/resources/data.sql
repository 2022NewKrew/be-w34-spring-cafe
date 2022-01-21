INSERT INTO USERS(email, password, nickname, created_at, is_deleted) VALUES ('mino.lee@kakaocorp.com', '123', '이민호', parsedatetime('2022-01-12 10:30', 'yyyy-MM-dd hh:mm'), false);

INSERT INTO USERS(email, password, nickname, created_at, is_deleted) VALUES ('austin.22@kakaocorp.com', '123', '이수민', parsedatetime('2022-01-12 10:30', 'yyyy-MM-dd hh:mm'), true);

INSERT INTO ARTICLES(user_id, title, content, view_count, created_at, is_deleted) VALUES ('1', '안녕하세요', '카카오 카페입니다.', 3, parsedatetime('2022-01-12 11:00', 'yyyy-MM-dd hh:mm'), false);

INSERT INTO ARTICLES(user_id, title, content, created_at, is_deleted) VALUES ('1', '좋은 아침입니다', '다들 새해 복 많이 받으세요.', parsedatetime('2022-01-12 12:00', 'yyyy-MM-dd hh:mm'), false);