package com.kakao.cafe.repo;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.dto.ArticleDto;
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
public class ArticleJdbc implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    ArticleJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(Objects.requireNonNull(dataSource));
    }

    @Override
    public boolean add(@NonNull final Article article) {
        final int result = jdbcTemplate.update(con -> {
            final PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO article (user_id, title, body, created_at, modified_at) " +
                            "values (?,?,?,?,?)"
            );

            pstmt.setString(1, article.getUserId());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getBody());
            pstmt.setLong(4, article.getCreatedAt());
            pstmt.setLong(5, article.getModifiedAt());
            return pstmt;
        });

        if (result != 1) {
            throw new IllegalStateException("Affected record(s) is not 1 for add article(user: " + article.getUserId() + ")! - " + result);
        }

        return true;
    }

    @Override
    public ArticleDto getDto(final long idx) {
        final List<ArticleDto> list = jdbcTemplate.query(
                con -> {
                    final PreparedStatement pstmt = con.prepareStatement(
                            "SELECT a.idx, a.user_id, u.name AS user_name, a.title, a.body, a.created_at, a.modified_at, a.count_comments " +
                                    "FROM userlist AS u " +
                                    "JOIN (SELECT * FROM article WHERE idx = ? AND deleted = false LIMIT 1) AS a " +
                                    "ON u.id = a.user_id"
                    );
                    pstmt.setLong(1, idx);
                    return pstmt;
                },
                (rs, count) -> ArticleDto.from(
                        rs.getLong("idx"),
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("title"),
                        rs.getString("body"),
                        rs.getLong("created_at"),
                        rs.getLong("modified_at"),
                        rs.getInt("count_comments")
                )
        );

        if (list.size() == 0) {
            return null;
        }
        else if (list.size() > 1) {
            throw new IllegalStateException("Selected record(s) is not 1 for get article(" + idx + ")! - " + list.size());
        }

        return list.get(0);
    }

    @Override
    public List<ArticleDto> getDtoList() {
        return Collections.unmodifiableList(
                jdbcTemplate.query(
                        con -> con.prepareStatement(
                                "SELECT a.idx, a.user_id, u.name AS user_name, a.title, a.body, a.created_at, a.modified_at, a.count_comments " +
                                        "FROM userlist AS u " +
                                        "JOIN article AS a " +
                                        "ON u.id = a.user_id " +
                                        "WHERE a.deleted = false " +
                                        "ORDER BY a.created_at DESC"
                        ),
                        (rs, count) -> ArticleDto.from(
                                rs.getLong("idx"),
                                rs.getString("user_id"),
                                rs.getString("user_name"),
                                rs.getString("title"),
                                rs.getString("body"),
                                rs.getLong("created_at"),
                                rs.getLong("modified_at"),
                                rs.getInt("count_comments")
                        )
                )
        );
    }

    @Override
    public boolean update(final long idx, @NonNull final Article article) {
        final int result = jdbcTemplate.update(con -> {
            final PreparedStatement pstmt = con.prepareStatement(
                    "UPDATE article SET title = ?, body = ?, modified_at = ? " +
                            "WHERE idx = ?"
            );

            pstmt.setString(1, article.getTitle());
            pstmt.setString(2, article.getBody());
            pstmt.setLong(3, Instant.now().getEpochSecond());
            pstmt.setLong(4, idx);
            return pstmt;
        });

        if (result != 1) {
            throw new IllegalStateException("Affected record(s) is not 1 for update article(" + idx + ")! - " + result);
        }

        return true;
    }

    @Override
    public boolean delete(final long idx) {
        final int result = jdbcTemplate.update(con -> {
            final PreparedStatement pstmt = con.prepareStatement(
                    "UPDATE comment co " +
                            "RIGHT JOIN article ao " +
                            "ON ao.idx = ? AND ao.idx = co.article_idx AND co.deleted = false " +
                            "SET ao.deleted = true, ao.count_comments = 0, co.deleted = true " +
                            "WHERE ao.idx = ? AND ao.deleted = false AND 0 = (SELECT t2.cnt FROM (SELECT COUNT(c2.idx) cnt " +
                            "   FROM comment c2 " +
                            "   JOIN (SELECT * FROM article WHERE idx = ? AND deleted = false LIMIT 1) a2 " +
                            "   ON c2.article_idx = a2.idx " +
                            "   WHERE c2.user_id != a2.user_id " +
                            "   LIMIT 1) t2)"
            );
            pstmt.setLong(1, idx);
            pstmt.setLong(2, idx);
            pstmt.setLong(3, idx);
            return pstmt;
        });

        return (result > 0);
    }
}
