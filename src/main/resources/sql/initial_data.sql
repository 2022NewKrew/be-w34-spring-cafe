-- Admin
-- id = 1
-- type = 'ADMIN'
INSERT INTO user (email, username, password, status, display_name)
VALUES ('admin@example.com', 'admin', 'admin', 'ACTIVE', 'admin');

-- The mother thread or the board
-- id = 1
-- parent_id = null
-- type = 'BOARD'
INSERT INTO thread (author_id, title, content, status, type)
VALUES (1, '', '', 'VALID', 'BOARD');
