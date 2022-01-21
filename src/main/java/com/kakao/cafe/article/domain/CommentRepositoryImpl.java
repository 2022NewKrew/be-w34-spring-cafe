package com.kakao.cafe.article.domain;

import com.kakao.cafe.article.dto.SingleComment;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CommentMapper commentMapper;

    @Override
    public void save(Comment comment) {
        final String sql = "insert into comments(body, author_id, article_id) values(?, ?, ?)";

        jdbcTemplate.update(sql, comment.getBody(), comment.getAuthorId(), comment.getArticleId());
    }

    @Override
    public Optional<Comment> findById(Long id) {
        final String sql = "select * from comments where comment_id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, commentMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<SingleComment> findAllByArticleId(Long articleId) {
        final String sql = "select c.comment_id, "
            + "c.body, "
            + "c.created_at, "
            + "c.author_id, "
            + "u.nickname "
            + "from comments as c "
            + "inner join users u "
            + "on c.author_id = u.user_id where article_id = ? "
            + "order by c.created_at";

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> SingleComment.builder()
                .commentId(rs.getLong("comment_id"))
                .body(rs.getString("body"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .authorId(rs.getLong("author_id"))
                .authorName(rs.getString("nickname"))
                .build(),
            articleId
        );
    }

    @Override
    public void delete(Comment comment) {
        final String sql = "delete from comments where comment_id = ?";

        jdbcTemplate.update(sql, comment.getId());
    }
}
