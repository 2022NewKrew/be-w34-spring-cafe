INSERT INTO
    `USER`(email, nickname, password, createdAt)
VALUES
    ('testdata@test.com', 'testUser1', 'asdf', '20220114'),
    ('testdata2@test.com', 'testUser2', 'asdf', '20220114'),
    ('testdata3@test.com', 'testUser3', 'asdf', '20220114');

INSERT INTO
    `ARTICLE`(userId, title, body, createdAt)
VALUES
    (1, 'testTitle1', 'testContent', '2022-01-14 21:00:00'),
    (2, 'testTitle2', 'testContent2', '2022-01-14 22:00:00'),
    (9999, 'testTitle3', 'testContent3', '2022-01-14 23:00:00');

INSERT INTO
    `REPLY`(userId, articleId, comments, createdAt)
VALUES
    (1, 1, 'testContent', '2022-01-15 21:00:00'),
    (2, 1, 'testContent', '2022-01-15 21:00:00'),
    (2, 2, 'testContent2', '2022-01-15 22:00:00'),
    (9999, 3, 'testContent3', '2022-01-15 23:00:00');
