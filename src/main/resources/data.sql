INSERT INTO `USER` (email, password, nick_name, created_at)
VALUES ('gallix@kakao.com', 'abcd1234!', 'gallix', PARSEDATETIME('2021-01-01 00:00:00', 'yyyy-MM-dd hh:mm:ss'));

INSERT INTO `POST` (title, contents, created_at, view_num, user_id)
VALUES ('title', 'good contents by gallix', PARSEDATETIME('2021-01-01 00:00:00', 'yyyy-MM-dd hh:mm:ss'), 123, 1);
