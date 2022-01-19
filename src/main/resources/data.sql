INSERT INTO articles (writer, title, contents, deleted) VALUES ('picosw', 'sample', 'sample contexts', false), ('javajigi', 'test', 'test contexts', false);

INSERT INTO users (userid, password, name, email) VALUES ( 'picosw', '1234', '안상욱', 'tony.ahn@kakaocorp.com' ), ('javajigi', '123', '자바지기', 'javajigi@kakaocorp.com');

INSERT INTO replys (writer, contents, articleId, deleted) VALUES ('picosw', 'test reply', 1, false), ('javajigi', 'test reply', 1, false);