INSERT INTO users(email, password, nickname)
values ('tester1@test.com', 'password', 'tester1'),
       ('tester2@test.com', 'password', 'tester2'),
       ('tester3@test.com', 'password', 'tester3');

INSERT INTO articles(title, body, author_id, view_count)
values ('제목1', '10글자 이상 작성해야 합니다.', 1, 35),
       ('제목2', '10글자 이상 작성해야 합니다.', 2, 57),
       ('제목3', '10글자 이상 작성해야 합니다.', 2, 100),
       ('제목4', '10글자 이상 작성해야 합니다.', 3, 81),
       ('제목5', '10글자 이상 작성해야 합니다.', 3, 17),
       ('제목6', '10글자 이상 작성해야 합니다.', 3, 53);

INSERT INTO comments(body, author_id, article_id)
values ('댓글1', 1, 1),
       ('댓글2', 2, 1),
       ('댓글3', 2, 1),
       ('댓글4', 3, 1),
       ('댓글5', 1, 2),
       ('댓글6', 2, 2),
       ('자신의 댓글1', 2, 3),
       ('자신의 댓글2', 2, 3);