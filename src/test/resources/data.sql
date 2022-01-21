insert into USER_PROFILE(user_id, password, name, email)
VALUES ('lucas', '123', 'lucas', 'test@daum.net'),
       ('lucas2', '123', 'lucas2', 'test@daum.net');

insert into QNA(writer, title, contents, created_at)
VALUES ('lucas', 'h2 test', 'h2 test', PARSEDATETIME('2022-01-19 00:00:00', 'yyyy-MM-dd hh:mm:ss')),
       ('lucas', 'h22 test', 'h22 test', PARSEDATETIME('2022-01-19 00:00:00', 'yyyy-MM-dd hh:mm:ss'));

insert into COMMENT(writer, contents, qna_id, created_at)
VALUES ('lucas', 'test1', '1', PARSEDATETIME('2022-01-19 00:00:00', 'yyyy-MM-dd hh:mm:ss')),
       ('lucas', 'test2', '1', PARSEDATETIME('2022-01-19 00:00:01', 'yyyy-MM-dd hh:mm:ss')),
       ('lucas', 'test3', '2', PARSEDATETIME('2022-01-19 00:00:02', 'yyyy-MM-dd hh:mm:ss'));
