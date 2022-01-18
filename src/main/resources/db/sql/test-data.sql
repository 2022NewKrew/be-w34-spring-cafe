INSERT INTO users (user_id, password, name, email)
VALUES ('testaccount', 'testpass', 'name', 'email@example.com');

INSERT INTO articles (title, content, owner_id, created_at)
VALUES ('title', 'content', SELECT id FROM users WHERE user_id = 'testaccount', NOW());

INSERT INTO articles (title, content, owner_id, created_at)
VALUES ('title', 'content', SELECT id FROM users WHERE user_id = 'testaccount', NOW());

INSERT INTO articles (title, content, owner_id, created_at)
VALUES ('title', 'content', SELECT id FROM users WHERE user_id = 'testaccount', NOW());
