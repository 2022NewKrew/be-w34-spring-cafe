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
        List<Post> posts = myJdbcTemplate.query(selectQuery("where post.id = ".concat(String.valueOf(id))), postRowMapper);
        if(posts.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(posts.get(0));
    }

    @Override
    public List<Post> getPosts(int start, int size) {
        String condition = String.format("order by timewritten desc limit %d offset %d", size, start);
        return myJdbcTemplate.query(selectQuery(condition), postRowMapper);
    }

    private String selectQuery(String condition){
        String query = "select *, comment.id as comment_id, comment.writerName as comment_writerName, comment.content as comment_content "
                .concat("from post left join comment on post.id = comment.postId ");

        if(condition != null && !condition.isEmpty()){
            return query.concat(condition);
        }

        return query;
    }

    @Override
    public void savePost(Post post) {
        myJdbcTemplate.update("insert into post(id, title, content, writerName, timeWritten) values(?,?,?,?,?)",
                post.getId(), post.getTitle(), post.getContent(), post.getWriterName(), post.getTimeWritten());
    }

    @Override
    public void savePostAll(List<Post> posts) {
        for(Post post : posts){
            savePost(post);
        }
    }

    @Override
    public void saveComment(Long postId, Comment comment) {
        myJdbcTemplate.update("insert into comment(id, postId, writerName, content) values(?,?,?,?)",
            comment.getId(), postId, comment.getWriterName(), comment.getContent());
    }
}
