INSERT INTO cafe_user (user_id, password, name, email)
    VALUES ('mylumiere', 'test1234', '김승찬', 'chance.kk@kakaocorp.com'),
           ('chance', 'test1234', '승찬', 'matues00@google.com');
INSERT INTO cafe_article (writer, title, contents)
    VALUES ((SELECT user_id FROM cafe_user WHERE user_id = 'mylumiere'), '삽질킹을 아시나요?', '저는 삽질만 합니다.');