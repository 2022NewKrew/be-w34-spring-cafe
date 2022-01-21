package com.kakao.cafe.repo;

import com.kakao.cafe.entity.Comment;
import com.kakao.cafe.dto.CommentDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class CommentJdbc implements CommentRepository {
    private final JdbcTemplate jdbcTemplate;

    CommentJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(Objects.requireNonNull(dataSource));
    }

    @Override
    public boolean add(@NonNull final Comment comment) {
        final int resultInsert = jdbcTemplate.update(con -> {
            final PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO comment (user_id, article_idx, body, created_at, modified_at) " +
                            "VALUES (?,?,?,?,?)"
            );

            pstmt.setString(1, comment.getUserId());
            pstmt.setLong(2, comment.getArticleIdx());
            pstmt.setString(3, comment.getBody());
            pstmt.setLong(4, comment.getCreatedAt());
            pstmt.setLong(5, comment.getModifiedAt());
            return pstmt;
        });

        final int resultUpdate = jdbcTemplate.update(con -> {
            final PreparedStatement pstmt = con.prepareStatement(
                    "UPDATE article SET count_comments = count_comments + 1 " +
                            "WHERE idx = ?"
            );

            pstmt.setLong(1, comment.getArticleIdx());
            return pstmt;
        });

        if (resultInsert != 1 || resultUpdate != 1) {
            throw new IllegalStateException(
                    "Affected record(s) is not 2 for add comment" +
                            "(article: " + comment.getArticleIdx() + ", user: " + comment.getUserId() + ")!" +
                            " insert - " + resultInsert + ", update - " + resultUpdate);
        }

        return true;
    }

    @Override
    public CommentDto getDto(final long idx) {
        final List<CommentDto> list = jdbcTemplate.query(
                con -> {
                    final PreparedStatement pstmt = con.prepareStatement(
                            "SELECT c.idx, c.user_id, u.name AS user_name, c.article_idx, c.body, c.created_at, c.modified_at " +
                                    "FROM userlist AS u " +
                                    "JOIN (SELECT * FROM comment WHERE idx = ? AND deleted = false LIMIT 1) AS c " +
                                    "ON u.id = c.user_id"
                    );
                    pstmt.setLong(1, idx);
                    return pstmt;
                },
                (rs, count) -> CommentDto.from(
                        rs.getLong("idx"),
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getLong("article_idx"),
                        rs.getString("body"),
                        rs.getLong("created_at"),
                        rs.getLong("modified_at")
                )
        );

        if (list.size() != 1) {
            throw new IllegalStateException("Selected record(s) is not 1 for get comment(" + idx + ")! - " + list.size());
        }

        return list.get(0);
    }

    @Override
    public List<CommentDto> getDtoList(final long articleIdx) {
        return Collections.unmodifiableList(
                jdbcTemplate.query(
                        con -> {
                            final PreparedStatement pstmt = con.prepareStatement(
                                    "SELECT c.idx, c.user_id, u.name AS user_name, c.article_idx, c.body, c.created_at, c.modified_at " +
                                            "FROM userlist AS u " +
                                            "JOIN comment AS c " +
                                            "ON u.id = c.user_id " +
                                            "WHERE c.deleted = false AND c.article_idx = ?"
                            );
                            pstmt.setLong(1, articleIdx);
                            return pstmt;
                        },
                        (rs, count) -> CommentDto.from(
                                rs.getLong("idx"),
                                rs.getString("user_id"),
                                rs.getString("user_name"),
                                rs.getLong("article_idx"),
                                rs.getString("body"),
                                rs.getLong("created_at"),
                                rs.getLong("modified_at")
                        )
                )
        );
    }

    @Override
    public boolean update(final long idx, @NonNull final Comment comment) {
        final int result = jdbcTemplate.update(con -> {
            final PreparedStatement pstmt = con.prepareStatement(
                    "UPDATE comment SET body = ?, modified_at = ? " +
                            "WHERE idx = ?"
            );

            pstmt.setString(1, comment.getBody());
            pstmt.setLong(2, Instant.now().getEpochSecond());
            pstmt.setLong(3, idx);
            return pstmt;
        });

        if (result != 1) {
            throw new IllegalStateException("Affected record(s) is not 1 for update comment(" + idx + ")! - " + result);
        }

        return true;
    }

    @Override
    public boolean delete(final long idx) {
        final int result = jdbcTemplate.update(con -> {
            final PreparedStatement pstmt = con.prepareStatement(
                    "UPDATE comment, article " +
                            "SET comment.deleted = true, article.count_comments = GREATEST(0, article.count_comments - 1) " +
                            "WHERE comment.article_idx = article.idx AND comment.idx = ? AND comment.deleted = false"
            );
            pstmt.setLong(1, idx);
            return pstmt;
        });

        if (result != 2) {
            throw new IllegalStateException("Affected record(s) is not 2 for delete comment(" + idx + ")! - " + result);
        }

        return true;
    }
}
