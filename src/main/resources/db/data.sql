INSERT INTO user(id, pw, name, email)
            values('worldbright', 'asdf', 'shyun', 'shuyn.cream@kakaocorp.com');
INSERT INTO article(authorKey, title, content, postTime, deleted)
            values(1, '첫 글', '제곧내', '2021-01-01 12:34:56', false);
INSERT INTO comment(authorKey, articleKey, content, postTime, deleted)
            values(1, 1, '첫 댓글', '2021-01-01 12:34:56', false);