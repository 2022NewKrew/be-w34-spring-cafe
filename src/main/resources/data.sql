insert into post(id, title, content, writerName, timeWritten)
    values(10001,'title1','hello','asdf', CURRENT_TIMESTAMP);
insert into post(id, title, content, writerName, timeWritten)
    values(10002,'title2','content','goodman', CURRENT_TIMESTAMP);
insert into post(id, title, content, writerName, timeWritten)
    values(10003,'title3','LGTM!!','asdf', CURRENT_TIMESTAMP);

insert into comment(id, postId, writerName, content)
values(20001,10001,'writer1','comment!');
insert into comment(id, postId, writerName, content)
values(20002,10001,'writer2','hello!');
insert into comment(id, postId, writerName, content)
values(20003,10001,'writer3','world!');
insert into comment(id, postId, writerName, content)
values(20004,10002,'writer1','LGTM!');

-- delete from COMMENT where ID=20004;
-- delete from COMMENT where ID<0;
