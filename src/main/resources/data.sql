INSERT INTO
    `USER`(email, nickname, password, createdAt)
VALUES
    ('testdata@test.com', 'testUser1', 'asdf', '20220114'),
    ('testdata2@test.com', 'testUser2', 'asdf', '20220114'),
    ('testdata3@test.com', 'testUser3', 'asdf', '20220114');

INSERT INTO
    `ARTICLE`(userId, title, body, createdAt)
VALUES
    (1, 'testTitle1', 'testContent', '20220114 21:00:00'),
    (2, 'testTitle2', 'testContent2', '20220114 22:00:00'),
    (9999, 'testTitle3', 'testContent3', '20220114 23:00:00');

SELECT * FROM `USER`;
SELECT * FROM `ARTICLE`;
