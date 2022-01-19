INSERT INTO USER_TABLE (userId, password, name, email, time) VALUES ('javajigi', 'test', '자바지기', 'javajigi@slipp.net', now());
INSERT INTO USER_TABLE (userId, password, name, email, time) VALUES ('sanjigi', 'test', '산지기', 'sanjigi@slipp.net' , now());

insert into ARTICLE_TABLE (WRITER, TITLE, CONTENTS, TIME) values ('javajigi','제목','내용',now());
insert into REPLY_TABLE (CONTENTS, ARTICLE_ID,USER_ID,TIME) values ('댓글입니다1',1,1,now());
insert into REPLY_TABLE (CONTENTS, ARTICLE_ID,USER_ID,TIME) values ('댓글입니다2',1,2,now());
insert into REPLY_TABLE (CONTENTS, ARTICLE_ID,USER_ID,TIME) values ('댓글입니다3',1,1,now());