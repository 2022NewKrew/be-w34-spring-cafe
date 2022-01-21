package com.kakao.cafe.post.persistence;

import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.domain.repository.PostRepository;
import com.kakao.cafe.post.persistence.mapper.PostRowMapper;
import com.kakao.cafe.util.MyJdbcTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcPostRepository implements PostRepository {
    private final MyJdbcTemplate myJdbcTemplate;
    private final PostRowMapper postRowMapper;

    @Override
    public Optional<Post> getPost(Long id) {
        final String queryString = selectQuery(String.format("and post.id = %d", id));
        List<Post> posts = myJdbcTemplate.query(queryString, postRowMapper);

        return posts.isEmpty() ? Optional.empty()
                : Optional.of(posts.get(0));
    }

    @Override
    public List<Post> getPosts(int start, int size) {
        final String condition = "order by timewritten desc";
        final String queryString = selectQuery(condition);
        return myJdbcTemplate.query(queryString, postRowMapper).subList(start, size);
    }

    private String selectQuery(String condition){
        String queryString = "select *, comment.id as comment_id, "
                + "comment.writerName as comment_writerName, comment.content as comment_content "
                + "from post left join comment on post.id = comment.postId "
                + "where isHidden=false ";

        if(condition != null && !condition.isEmpty()){
            return queryString.concat(condition);
        }

        return queryString;
    }

    @Override
    public void savePost(Post post) {
        final String queryString = "insert into post(id, title, content, writerName, timeWritten, isHidden) values(?,?,?,?,?,?)";
        myJdbcTemplate.update(queryString, post.getId(), post.getTitle(), post.getContent(),
                post.getWriterName(), post.getTimeWritten(), post.getIsHidden());

        post.getComments().forEach(comment -> saveComment(post.getId(), comment));
    }

    @Override
    public void savePostAll(List<Post> posts) {
        for(Post post : posts){
            savePost(post);
        }
    }

    @Override
    public void saveComment(Long postId, Comment comment) {
        final String queryString = "insert into comment(id, postId, writerName, content) values(?,?,?,?)";
        myJdbcTemplate.update(queryString, comment.getId(), postId, comment.getWriterName(), comment.getContent());
    }

    @Override
    public void update(Long postId, String newContent) {
        final String queryString = String.format("update post set content = '%s' where id = %d", newContent, postId);
        myJdbcTemplate.update(queryString);
    }

    @Override
    public void softDelete(Long postId) {
        final String queryString = String.format("update post set isHidden = 'true' where id = %d", postId);
        myJdbcTemplate.update(queryString);
    }

    @Override
    public void deleteComment(Long commentId) {
        final String queryString = String.format("delete from COMMENT where ID=%d", commentId);
        myJdbcTemplate.update(queryString);
    }
}
