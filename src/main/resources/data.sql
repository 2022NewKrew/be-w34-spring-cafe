INSERT INTO `USER`(userid, password, name, email, joined_at)
VALUES ('s000015', '$2a$10$xv9wV0Bul8sbIv7JpmUmlu91WF5iFyAqxXQ9WHfgtv/CtU5zKfIei', 'james', 'james1212@gmail.com',
        '2021-12-13'),
       ('sung2687', '$2a$10$xv9wV0Bul8sbIv7JpmUmlu91WF5iFyAqxXQ9WHfgtv/CtU5zKfIei', 'sam', 'samsam@gmail.com',
        '2022-01-02');



INSERT INTO `ARTICLE`(author_id, title, content, views, created_at)
VALUES (1, '게시글 예시 제목 1', '이 글은 테스트 입니다. 동해물과 백두산이 마르고 닳도록', 0, '2021-01-11'),
       (1, '게시글 예시 제목 2', '이 글은 테스트 입니다. 하느님이 보우하사 우리나라 만세', 0, '2021-01-13'),
       (2, '게시글 예시 제목 3', '이 글은 테스트 입니다. 무궁화 삼천리 화려강산 대한사람 대한으로 길이 보전하세', 0, '2021-01-14');


INSERT INTO `REPLY`(author_id, article_id, content, created_at)
VALUES (1,2, '예시 댓글 작성자 키는 1 게시글 키는 2', '2021-01-14'),
       (2,2, '예시 댓글 작성자 키는 2 게시글 키는 2', '2021-01-15'),
       (1,1, '예시 댓글 작성자 키는 1 게시글 키는 1', '2021-01-12');

select *
from article;

select u.name, a.title, a.CONTENT
from ARTICLE a
         left join `USER` u on a.AUTHOR_ID = u.ID;