package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepository {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public void save(Article article) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO Articles (title, content, articleIndex) VALUES (?, ?, ?)");
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setInt(3, Article.createArticleIndex());

            return preparedStatement;
        });
    }

    public List<Article> findAll() {
        String query ="SELECT * FROM Articles";
        List<Article> articles = new ArrayList<>();
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

        for(Map<String, Object> articleInfo : result) {
            articles.add(
                    Article.builder()
                            .title((String) articleInfo.get("title"))
                            .content((String) articleInfo.get("content"))
                            .articleIndex(Integer.parseInt(String.valueOf(articleInfo.get("articleIndex"))))
                            .build());
        }

        return articles;
    }

    public Article findByIndex(Integer articleIndex) {
        String query = String.format("SELECT title, content, articleIndex FROM Articles WHERE articleIndex = %s", articleIndex);
        Map<String, Object> result = jdbcTemplate.queryForMap(query);
        return Article.builder()
                .title((String) result.get("title"))
                .content((String) result.get("content"))
                .articleIndex(Integer.parseInt(String.valueOf(result.get("articleIndex"))))
                .build();
    }
}
