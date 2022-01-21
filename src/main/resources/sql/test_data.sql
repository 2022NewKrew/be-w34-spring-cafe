-- An example user with id=1
INSERT INTO user (username, password, email, display_name, status)
VALUES ('frank', 'password', 'frank@kakao.com', 'frank', 'ACTIVE');

-- An example post with id=1 written by the example user
INSERT INTO thread (parent_id, author_id, title, content, status, type)
VALUES (NULL, 1, 'Hello World!', 'Bye!', 'VALID', 'POST');

-- An example comment with id=2 on the example post written by the example user
INSERT INTO thread (parent_id, author_id, title, content, status, type)
VALUES (1, 1, '', 'I''m Back!', 'VALID', 'COMMENT');
