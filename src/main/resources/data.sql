INSERT INTO "USER"(userid, password, name, email, joined_at)
VALUES
       ('id_001', '123', 'james', 'james1212@gmail.com', '2021-12-13'),
       ('id_002', '123', 'sam', 'samsam@gmail.com', '2022-01-02');



INSERT INTO "ARTICLE"(author, title, content, views, created_at)
VALUES
       ('james', '게시글 예시 제목 1', '이 글은 테스트 입니다. 동해물과 백두산이 마르고 닳도록', 0, '2021-01-11'),
       ('james', '게시글 예시 제목 2', '이 글은 테스트 입니다. 하느님이 보우하사 우리나라 만세', 0, '2021-01-13'),
       ('sam', '게시글 예시 제목 3', '이 글은 테스트 입니다. 무궁화 삼천리 화려강산 대한사람 대한으로 길이 보전하세', 0, '2021-01-14');


select * from article;