INSERT INTO cafe_user (user_id, password, name, email, image_path, created_time, modified_time)
    VALUES ('mylumiere', 'test1234', '김승찬', 'chance.kk@kakaocorp.com', '/static/images/anonymous-user.png', now(), now()),
           ('chance', 'test1234', '승찬', 'matues00@google.com', '/static/images/anonymous-user.png', now(), now());
INSERT INTO cafe_article (writer, title, contents, created_time, modified_time)
    VALUES ((SELECT user_id FROM cafe_user WHERE user_id = 'mylumiere'), '삽질킹을 아시나요?', '저는 삽질만 합니다.', now(), now()),
           ((SELECT user_id FROM cafe_user WHERE user_id = 'chance'), '이건 무슨 질문일가요?', '과연 이건 무슨 질문일까요?', now(), now()),
           ((SELECT user_id FROM cafe_user WHERE user_id = 'mylumiere'), '과연 세 개는 나올까요?', '세 개도 나오겠지요.', now(), now());
INSERT INTO cafe_reply (article_id, writer, contents, created_time, modified_time)
    VALUES ((SELECT id from cafe_article WHERE id = '1'), (SELECT user_id FROM cafe_user WHERE user_id = 'chance'),
            '아뇨, 저는 잘 모릅니다.', now(), now()),
           ((SELECT id from cafe_article WHERE id = '1'), (SELECT user_id FROM cafe_user WHERE user_id = 'mylumiere'),
            '그렇군요.', now(), now()),
           ((SELECT id from cafe_article WHERE id = '2'), (SELECT user_id FROM cafe_user WHERE user_id = 'mylumiere'),
            '무플방지위원회입니다.', now(), now());
