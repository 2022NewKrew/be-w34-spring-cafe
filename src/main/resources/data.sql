INSERT INTO USERS (id, userId, email, name, password) VALUES (11, 'javajigi', 'javajigi@slipp.net', '자바지기', 'test');
INSERT INTO USERS (id, userId, email, name, password) VALUES (21, 'sanjigi', 'sanjigi@slipp.net', '산지기', 'test');

INSERT INTO ARTICLE (id, authorId, author, title, content, createdAt, numOfComment)
                VALUES (999, 11, '자바지기', '스프링 안돼요', '그냥 안돼요', current_timestamp, 0);
INSERT INTO ARTICLE (id, authorId, author, title, content, createdAt, numOfComment)
                VALUES (1000, 21, '산지기', '미션 안돼요', '모르겠고 그냥 안됨', current_timestamp, 0);
