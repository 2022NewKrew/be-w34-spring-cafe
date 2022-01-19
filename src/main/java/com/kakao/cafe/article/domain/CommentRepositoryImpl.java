package com.kakao.cafe.article.domain;

import com.kakao.cafe.article.dto.SingleComment;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Comment comment) {
        final String sql = "insert into comments(body, author_id, article_id) values(?, ?, ?)";

        jdbcTemplate.update(sql, comment.getBody(), comment.getAuthorId(), comment.getArticleId());
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
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
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .authorId(rs.getLong("author_id"))
                .authorName(rs.getString("nickname"))
                .build(),
            articleId
        );
    }
}
