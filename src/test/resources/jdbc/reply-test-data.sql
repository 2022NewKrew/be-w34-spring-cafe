INSERT INTO USER (uid, password, name, email, created_at)
VALUES ('uid', 'pwd', 'name', 'email', CURRENT_TIMESTAMP);

INSERT INTO ARTICLE (uid, title, body, created_at)
VALUES ('uid', 'title', 'body', CURRENT_TIMESTAMP);