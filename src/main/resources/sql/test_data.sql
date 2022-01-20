-- An example user
-- type = 'NORMAL_USER'
INSERT INTO user (username, password, email, display_name, status)
VALUES ('frank', 'password', 'frank@kakao.com', 'frank', 'ACTIVE');

-- An example post
-- type = 'POST'
INSERT INTO thread (parent_id, author_id, title, content, status, type)
VALUES (1, 2, 'Hello World!', 'Bye!', 'VALID', 'POST');
-- An example comment
-- type = 'COMMENT'
INSERT INTO thread (parent_id, author_id, title, content, status, type)
VALUES (2, 2, '', 'I''am Back!', 'VALID', 'COMMENT');
