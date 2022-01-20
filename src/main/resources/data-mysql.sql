INSERT INTO USERS (id, user_id, password, name, email) VALUES (1, 'javajigi', 'test', '자바지기', 'javajigi@slipp.net');
INSERT INTO USERS (id, user_id, password, name, email) VALUES (2, 'sanjigi', 'test', '산지기', 'sanjigi@slipp.net');

INSERT INTO ARTICLES (writer, title, contents, is_deleted, created_date, modified_date)
VALUES ('javajigi', 'test', 'test', 'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
