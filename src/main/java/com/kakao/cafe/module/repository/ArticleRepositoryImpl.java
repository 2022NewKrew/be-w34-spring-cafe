package com.kakao.cafe.module.repository;

import com.kakao.cafe.module.model.domain.Article;
import com.kakao.cafe.module.repository.mapper.ArticleDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Primary
@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Article> rowMapper = new ArticleDBMapper();

    @Override
    public void addArticle(Article article) {
        String query = "INSERT INTO ARTICLE (author_id, title, contents, created, view_count, comment_count) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"id"});
            preparedStatement.setLong(1, article.getAuthorId());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setString(3, article.getContents());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(article.getCreated()));
            preparedStatement.setInt(5, article.getViewCount());
            preparedStatement.setInt(6, article.getCommentCount());
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
    }

    @Override
    public List<Article> findAllArticles() {
        return jdbcTemplate.query("SELECT * FROM ARTICLE", rowMapper);
    }

    @Override
    public Article findArticleById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ARTICLE WHERE id = ?", rowMapper, id);
    }
}
