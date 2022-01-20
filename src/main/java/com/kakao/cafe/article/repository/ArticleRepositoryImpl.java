package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.persistence.Article;
import com.kakao.cafe.global.util.JdbcUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {

    private final JdbcUtil<Article> jdbcUtil;

    @Override
    public void save(Article article) {
        String sql = "INSERT INTO ARTICLES(writer_id, title, content) VALUES(?, ?, ?)";
        jdbcUtil.readRecord(
                sql,
                article.getWriter().getId(),
                article.getTitle(),
                article.getContent()
        );
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM ARTICLES WHERE deleted = FALSE";

        return jdbcUtil.readRecords(sql);
    }

    @Override
    public Optional<Article> findById(Long id) {
        String sql = "SELECT * FROM ARTICLES WHERE id = ? AND deleted = FALSE";

        return jdbcUtil.readRecord(sql, id);
    }

    @Override
    public void update(Article article) {
        String sql = "UPDATE ARTICLES SET title = ?, content = ? updated_at = ? WHERE id = ?";

        jdbcUtil.writeRecord(sql,
                article.getTitle(),
                article.getContent(),
                LocalDateTime.now(),
                article.getId()
        );
    }

    @Override
    public void delete(Article article) {
        String sql = "UPDATE ARTICLES SET deleted = TRUE WHERE id = ?";
        jdbcUtil.writeRecord(sql, article.getId());
    }
}
