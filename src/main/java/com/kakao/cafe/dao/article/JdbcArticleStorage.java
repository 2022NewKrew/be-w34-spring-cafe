package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.model.article.Contents;
import com.kakao.cafe.model.article.Title;
import com.kakao.cafe.model.article.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
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

        String query = "SELECT ID, TITLE, WRITER, CONTENTS, CREATE_DATE FROM ARTICLE ORDER BY ID DESC";
        List<Article> articles = queryToArticles(query);

        if (articles.size() < finishIndex) {
            return new ArrayList<>(articles.subList(startIndex, articles.size()));
        }
        return new ArrayList<>(articles.subList(startIndex, finishIndex));
    }

    @Override
    public void addArticle(Article article) {
        String query = "INSERT INTO ARTICLE(TITLE, WRITER, CONTENTS) VALUES (?, ?, ?)";
        jdbcTemplate.update(
                query,
                article.getTitle().getValue(),
                article.getWriter().getValue(),
                article.getContents().getValue());
    }

    @Override
    public Optional<Article> findArticleById(int id) {
        String query = String.format(
                "SELECT ID, TITLE, WRITER, CONTENTS, CREATE_DATE FROM ARTICLE WHERE ID = %s", id);
        return jdbcTemplate
                .query(query, (rs, rowNum) -> toArticle(rs))
                .stream()
                .findAny();
    }

    @Override
    public int getSize() {
        return jdbcTemplate.queryForObject("select count(*) from ARTICLE", Integer.class);
    }

    private List<Article> queryToArticles(String query) {
        return jdbcTemplate.query(query, (rs, rowNum) -> toArticle(rs));
    }

    private Article toArticle(ResultSet resultSet) throws SQLException {
        return new Article(
                resultSet.getInt("ID"),
                new Title(resultSet.getString("TITLE")),
                new Writer(resultSet.getString("WRITER")),
                new Contents(resultSet.getString("CONTENTS")),
                resultSet.getTimestamp("CREATE_DATE").toLocalDateTime()
        );
    }

    @Override
    public void deleteArticle(int id) {
        String query = "DELETE FROM ARTICLE WHERE ID = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public void updateArticle(Article article) {
        String query = "UPDATE ARTICLE SET TITLE = ?, CONTENTS = ? WHERE ID = ?";
        jdbcTemplate.update(
                query,
                article.getTitle().getValue(),
                article.getContents().getValue(),
                article.getId());
    }
}
