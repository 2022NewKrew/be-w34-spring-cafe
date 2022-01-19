-- An example user
-- type = 'NORMAL_USER'
INSERT INTO user (email, username, password, status, display_name)
VALUES ('frank@kakao.com', 'frank', 'password', 'ACTIVE', 'frank');

-- An example post
-- type = 'POST'
INSERT INTO thread (parent_id, author_id, title, content, status, type)
VALUES (1, 2, 'Hello World!', 'Bye!', 'VALID', 'POST');
-- An example comment
-- type = 'COMMENT'
INSERT INTO thread (parent_id, author_id, title, content, status, type)
VALUES (2, 2, '', 'I''am Back!', 'VALID', 'COMMENT');
