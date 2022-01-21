INSERT INTO USERS (id, userid, password, name, email)
VALUES (1, 'javajigi', 'test', '자바지기', 'javajigi@slipp.net');
INSERT INTO USERS (id, userid, password, name, email)
VALUES (2, 'sanjigi', 'test', '산지기', 'sanjigi@slipp.net');

INSERT INTO ARTICLES (id, author_id, title, content)
VALUES (1, 1, 'test title', 'test content');

INSERT INTO REPLIES (id, author_id, article_id, content)
VALUES (1, 1, 1, 'test reply');
