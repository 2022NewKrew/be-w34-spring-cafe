INSERT INTO users VALUES ('jbjb', 'jbjb', '장주빈', 'sunshine4429@gmail.com', false, now()), ('jbjb2', 'jbjb2', '제이비', 'jb.jang@kakaocorp.com', false, now());
INSERT INTO articles (writer, title, contents, deleted, created_at, modified_at) VALUES ('jbjb', '테스트', '테스트 게시글입니다', false, now(), now()), ('jbjb2', '테스트2', '테스트 게시글입니다2', false, now(), now());
INSERT INTO comments (article_id, commenter, contents, deleted, created_at, modified_at) VALUES (1, 'jbjb', '테스트 댓글입니다', false, now(), now()), (1, 'jbjb2', '테스트 댓글입니다2', false, now(), now());
