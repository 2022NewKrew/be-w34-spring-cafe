INSERT INTO users (user_id, password, name, email) VALUES ( 'test1', 'test', 'test1', 'test1@hanmail.net' );

INSERT INTO users (user_id, password, name, email) VALUES ( 'test2', 'test', 'test2', 'test2@hanmail.net' );

INSERT INTO users (user_id, password, name, email) VALUES ( 'test3', 'test', 'test3', 'test3@hanmail.net' );

INSERT INTO articles (TITLE, CONTENTS, WRITER_ID) VALUES ( 'title1', 'contents1', 1 );

INSERT INTO articles (TITLE, CONTENTS, WRITER_ID) VALUES ( 'title1', 'contents1', 2 );

INSERT INTO comments (CONTENTS, WRITER_ID, ARTICLE_ID) VALUES ( 'comment1', 1, 1 );

INSERT INTO comments (CONTENTS, WRITER_ID, ARTICLE_ID) VALUES ( 'comment2', 2, 1 );

INSERT INTO comments (CONTENTS, WRITER_ID, ARTICLE_ID) VALUES ( 'comment3', 3, 1 );
