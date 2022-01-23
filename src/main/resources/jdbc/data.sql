INSERT INTO user_info (email, name, password)
VALUES ('dhkdtlswh@gmail.com', 'shinjo', 'asdf'),
       ('creed.wang@kakaocorp.com', 'creed', 'aaa');

INSERT INTO article (user_id, title, content)
VALUES (1, 'title1', 'content1'),
       (1, 'title2', 'content2'),
       (2, 'title3', 'content3');

INSERT INTO reply (user_id, article_id, content)
VALUES (1, 1, 'reply1'),
       (1, 1, 'reply2'),
       (2, 1, 'reply3'),
       (1, 2, 'reply4'),
       (1, 2, 'reply5'),
       (1, 3, 'reply6'),
       (2, 3, 'reply7');