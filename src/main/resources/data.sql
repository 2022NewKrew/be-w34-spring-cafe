delete from users;

delete from articles;

delete from comments;

insert into users(user_id, password, username, email)
values('a', 'a', 'james', 'aaa@google.com');

insert into users(user_id, password, username, email)
values('b', 'b', 'jay', 'bbb@google.com');

insert into users(user_id, password, username, email)
values('hong-gil-dong', 'pass', 'gildong', 'gildong1@google.com');

insert into users(user_id, password, username, email)
values('foo', 'pass', 'foo', 'foo2@google.com');

insert into users(user_id, password, username, email)
values('bar', 'pass', 'bar', 'bar2@google.com');

insert into articles(id, author_id, title, content, created_at)
values(10, 'a', 'question1', 'er encapsulate data, in which case only objects which match totally should be equal, or entitbrid', '2016-05-31 23:23');

insert into articles(id, author_id, title, content, created_at)
values(11, 'b', 'notice', 'random content random content random content', '2022-01-01 12:00');

insert into articles(id, author_id, title, content, created_at)
values(12, 'a', 'question2', 'no content', '2016-05-31 23:23');

insert into articles(id, author_id, title, content, created_at)
values(13, 'hong-gil-dong', 'java convention question', 'which is more preferred', '2021-12-12 12:23');

insert into articles(id, author_id, title, content, created_at)
values(14, 'foo', 'foo article title 1', 'foo article content 1', '2021-12-12 12:23');

insert into articles(id, author_id, title, content, created_at)
values(15, 'foo', 'foo article title 2', 'foo article content 2', '2021-12-12 12:23');

insert into articles(id, author_id, title, content, created_at)
values(16, 'bar', 'bar article title 1', 'bar article content 1', '2021-12-12 12:23');

insert into comments(id, article_id, author_id, content, created_at)
values(11, 10, 'foo', 'content1 content1 content1 content1', '2022-01-12 12:23');

insert into comments(id, article_id, author_id, content, created_at)
values(12, 10, 'a', 'waeivnoweiouwrnwbrunwrlfnjwelk', '2022-01-01 12:23');

