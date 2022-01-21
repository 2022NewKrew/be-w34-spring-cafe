INSERT INTO users (userId, password, name, email) VALUES ('admin', '1234', 'admin', 'admin@kakaocorp.com');
INSERT INTO articles (writer, title, contents, deleted) VALUES ('admin', 'adminTest', 'We are testing Now', false);
INSERT INTO replies (articleid, writer, contents, deleted) VALUES (1, 'admin', 'reply testing Now', false);