package com.kakao.cafe.repository;


import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exceptions.PostNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class JdbcPostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(JdbcPostRepository.class);

    public JdbcPostRepository(JdbcTemplate jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public Post save(Post post) {
        logger.debug("[Jdbc] post save: {}", post);
        User user = userRepository.findById(post.getUserId());
        String sql = "insert into post(title, content, user_id) values(?, ?, ?)";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), user.getId());
        logger.debug("[Jdbc] post save success : {}", post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        logger.debug("[Jdbc] post findAll");
        String sql = "select * from post";
        List<Post> postList = jdbcTemplate.query(sql, (rs, rowNum) -> new Post(rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getDate("created_at")));
        logger.debug("[Jdbc] post findAll success: {}", postList);
        return postList;
    }

    @Override
    public Post findByPostId(int id) {
        logger.debug("[Jdbc] post findByPostId id : {}", id);
        String sql = "select * from post where id = ?";

        try {
            Post post = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Post(rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getDate("created_at")),
                    id);
            logger.debug("[Jdbc] post findByPostId success : {}", post);
            return post;
        } catch (EmptyResultDataAccessException e) {
            throw new PostNotFoundException("없는 게시글 입니다");
        }
    }

    @Override
    public void update(Post post) {
        logger.debug("[Jdbc] post update : {}", post);
        String sql = "update post set title = ?, content = ? where id = ?";

        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), post.getId());
        logger.debug("[Jdbc] post update success");
    }

    @Override
    public void delete(int id) {
        logger.debug("[Jdbc] post delete id: {}", id);
        String sql = "delete from post where id = ?";

        jdbcTemplate.update(sql, id);
        logger.debug("[Jdbc] post delete success");
    }
}
