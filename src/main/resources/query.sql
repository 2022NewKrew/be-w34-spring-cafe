select *, comment.id as comment_id,
       comment.writerName as comment_writerName,
       comment.content as comment_content
from post left join comment on post.id = comment.postId;

select * from member where userId = 'hello';

select *, comment.id as comment_id, comment.writerName as comment_writerName, comment.content as comment_content
from post left join comment on post.id = comment.postId
where isHidden=false and post.id=10001
order by timewritten desc limit 4 offset 0;

select *, comment.id as comment_id, comment.writerName as comment_writerName, comment.content as comment_content
from post left join comment on post.id = comment.postId
where isHidden=false
order by timewritten desc limit 4 offset 0;

update post set isHidden = 'false' where id = 10003