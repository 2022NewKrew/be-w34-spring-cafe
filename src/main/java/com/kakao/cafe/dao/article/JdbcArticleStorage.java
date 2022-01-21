package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.model.article.Contents;
import com.kakao.cafe.model.article.Title;
import com.kakao.cafe.model.user.UserId;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class JdbcArticleStorage implements ArticleDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcArticleStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Article> getArticles(int pageNumber, int articlesPerPage) {
        int startIndex = (pageNumber - 1) * articlesPerPage;
        int finishIndex = articlesPerPage * (pageNumber);

        List<Article> articles = queryToArticles(ArticleSql.getAllArticleNotDeleted());

        if (articles.size() < finishIndex) {
            return new ArrayList<>(articles.subList(startIndex, articles.size()));
        }
        return new ArrayList<>(articles.subList(startIndex, finishIndex));
    }

    @Override
    public Article addArticle(Article article) {
        String query = ArticleSql.insert(article);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> connection.prepareStatement(query, new String[]{"id"}), keyHolder);

        return findArticleById(keyHolder.getKey().intValue()).orElseThrow(
                () -> new RuntimeException("Article 생성 후 반환시 발생한 예외"));
    }

    @Override
    public Optional<Article> findArticleById(int id) {
        return jdbcTemplate
                .query(ArticleSql.findArticleById(id), (rs, rowNum) -> toArticle(rs))
                .stream()
                .findAny();
    }

    @Override
    public int getSize() {
        return jdbcTemplate.queryForObject(ArticleSql.count(), Integer.class);
    }

    @Override
    public void deleteArticle(int id) {
        jdbcTemplate.update(ArticleSql.delete(id));
    }

    @Override
    public void updateArticle(Article article) {
        jdbcTemplate.update(ArticleSql.update(article));
    }

    private List<Article> queryToArticles(String query) {
        return jdbcTemplate.query(query, (rs, rowNum) -> toArticle(rs));
    }

    private Article toArticle(ResultSet resultSet) throws SQLException {
        return new Article(
                resultSet.getInt("ID"),
                new Title(resultSet.getString("TITLE")),
                new UserId(resultSet.getString("USER_ID")),
                new Contents(resultSet.getString("CONTENTS")),
                resultSet.getTimestamp("CREATE_DATE").toLocalDateTime(),
                resultSet.getBoolean("DELETED")
        );
    }
}
