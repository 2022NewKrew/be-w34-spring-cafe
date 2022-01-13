select *, comment.id as comment_id,
       comment.writerName as comment_writerName,
       comment.content as comment_content
from post left join comment on post.id = comment.postId