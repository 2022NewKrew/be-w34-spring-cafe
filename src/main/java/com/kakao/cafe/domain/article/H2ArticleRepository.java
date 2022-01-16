package com.kakao.cafe.domain.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class H2ArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public H2ArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(Article article) {
        String title = article.getTitle();
        String content = article.getContent();
        return jdbcTemplate.update(
                "INSERT INTO ARTICLES(TITLE, CONTENT) VALUES (?, ?)", title, content
        );
    }

    @Override
    public Article findById(int articleId) {
        return jdbcTemplate.queryForObject(
                "SELECT ID, TITLE, CONTENT FROM ARTICLES WHERE ID = ?",
                (rs, rowNum) -> {
                    Article article = new Article();
                    article.setId(rs.getInt("ID"));
                    article.setTitle(rs.getString("TITLE"));
                    article.setContent(rs.getString("CONTENT"));
                    return article;
                }
                , articleId
        );
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(
                "SELECT ID, TITLE, CONTENT FROM ARTICLES",
                (rs, rowNum) -> {
                    Article article = new Article();
                    article.setId(rs.getInt("ID"));
                    article.setTitle(rs.getString("TITLE"));
                    article.setContent(rs.getString("CONTENT"));
                    return article;
                }
        );
    }
}
