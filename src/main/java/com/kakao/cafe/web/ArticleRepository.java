package com.kakao.cafe.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArticleRepository {
    private static final String INSERT_VALUES = "INSERT INTO ARTICLES(WRITER, TITLE, CONTENTS) VALUES (?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM ARTICLES WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM ARTICLES";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Article> getArticleList() throws SQLException {
        return jdbcTemplate.query(
                SELECT_ALL,
                new ArticleRepository.ArticleMapper()
        );
    }

    public Article getArticle(int id) throws SQLException {
        return jdbcTemplate.queryForObject(
                SELECT_BY_ID,
                new ArticleMapper(),
                id
        );
    }

    public void addArticle(Article article) throws SQLException {
        jdbcTemplate.update(INSERT_VALUES,
                article.getWriter(), article.getTitle(), article.getContents());
    }

    public static class ArticleMapper implements RowMapper<Article> {
        public Article mapRow(ResultSet rs, int count) throws SQLException {
            Article article = new Article(
                    rs.getString("WRITER"),
                    rs.getString("TITLE"),
                    rs.getString("CONTENTS")
            );
            article.setId(rs.getInt("ID"));
            article.setTime(rs.getString("TIME"));
            System.out.println(article.getId());
            return article;
        }
    }
}
