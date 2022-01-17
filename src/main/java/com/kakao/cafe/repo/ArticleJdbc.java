package com.kakao.cafe.repo;

import com.kakao.cafe.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
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
                    "INSERT INTO article (user_id, title, body, created_at) " +
                            "values (?,?,?,?)"
            );

            pstmt.setString(1, article.getUserId());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getBody());
            pstmt.setLong(4, article.getCreatedAt());
            return pstmt;
        });

        return (result > 0);
    }

    @Override
    public Article find(final long idx) {
        final List<Article> list = jdbcTemplate.query(
                con -> {
                    final PreparedStatement pstmt = con.prepareStatement(
                            "SELECT * FROM article WHERE idx = ? LIMIT 1"
                    );
                    pstmt.setLong(1, idx);
                    return pstmt;
                },
                (rs, count) -> new Article(rs)
        );

        if (list.size() == 0) {
            return Article.NONE;
        }

        return list.get(0);
    }

    @Override
    public List<Article> getList() {
        return Collections.unmodifiableList(
                jdbcTemplate.query(
                        con -> con.prepareStatement(
                                "SELECT * FROM article ORDER BY created_at DESC"
                        ),
                        (rs, count) -> new Article(rs)
                )
        );
    }
}
