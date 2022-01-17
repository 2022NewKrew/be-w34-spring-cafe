INSERT INTO USERS (id, email, name, password) VALUES ('javajigi', 'javajigi@slipp.net', '자바지기', 'test');
INSERT INTO USERS (id, email, name, password) VALUES ('sanjigi', 'sanjigi@slipp.net', '산지기', 'test');

INSERT INTO ARTICLE (id, writer, title, content, createdAt, numOfComment) VALUES (999, 'javajigi', '스프링 안돼요', '그냥 안돼요', current_timestamp, 0);
INSERT INTO ARTICLE (id, writer, title, content, createdAt, numOfComment) VALUES (1000, 'sanjigi', '미션 안돼요', '모르겠고 그냥 안됨', current_timestamp, 0);
