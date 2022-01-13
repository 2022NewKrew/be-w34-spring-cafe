package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.repository.mapper.ArticleMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class ArticleDBRepositoryImpl implements ArticleRepository {
    private JdbcTemplate jdbcTemplate;

    public ArticleDBRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Article find(Long id) {
        String SQL = "SELECT * FROM ARTICLE WHERE ID = ?";
        List<Article> articles = jdbcTemplate.query(SQL, new ArticleMapper(), id);
        return articles.get(0);
    }

    public ArrayList<Article> findAll() {
        String SQL = "SELECT * FROM ARTICLE";
        List<Article> articles = jdbcTemplate.query(SQL, new ArticleMapper());
        return new ArrayList<Article>(articles);
    }

    public Long persist(ArticleCreateRequestDTO dto) {
        String SQL = "INSERT INTO ARTICLE (author, title, write_date, content, hits) VALUES (?, ?, ?, ?, ?)";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement statement = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, dto.getAuthorId());
            statement.setString(2, dto.getTitle());
            statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(4, dto.getContents());
            statement.setInt(5, 0);
            return statement;
        }, generatedKeyHolder);

        return generatedKeyHolder.getKey().longValue();
    }

    public void increaseHit(Long id) {
        String SQL = "UPDATE ARTICLE SET hits = hits + 1 WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }
}
