-- Admin
-- id = 1
-- type = 'ADMIN'
INSERT INTO user (username, password, email, display_name, status)
VALUES ('admin', 'admin', 'admin@example.com', 'admin', 'ACTIVE');

-- The mother thread or the board
-- id = 1
-- parent_id = null
-- type = 'BOARD'
INSERT INTO thread (author_id, title, content, status, type)
VALUES (1, '', '', 'VALID', 'BOARD');
