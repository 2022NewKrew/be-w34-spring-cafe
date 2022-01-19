insert into users ( username, nickname, email, password ) values ( 'admin', 'admin', 'admin@mail.com', 'admin' ), ( 'pug.gg', 'pug', 'pug.gg@kakaocorp.com', '123' );

insert into article ( author_id, title, description ) values ( 1, '테스트 게시글', '테스트로 생성된 게시글 입니다.' );

insert into reply ( article_id, author_id, description ) values ( 1, 1, '댓글 테스트1' ), ( 1, 2, '댓글 테스트2' );
