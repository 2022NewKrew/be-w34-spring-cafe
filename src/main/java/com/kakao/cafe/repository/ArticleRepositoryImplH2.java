package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ArticleRepositoryImplH2 implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    public ArticleRepositoryImplH2(DataSource dataSource, UserRepository userRepository) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.userRepository = userRepository;
    }

    @Override
    public void createArticle(Article article) {
        final User writer = article.getWriter();
        jdbcTemplate.update(
            "INSERT INTO ARTICLES (TITLE, WRITER, CONTENTS) VALUES ( ?, ?, ? )",
            article.getTitle(), writer.getUsername(), article.getContents()
        );
    }

    @Override
    public Integer articlesSize(){
        return findAllArticles().size();
    }

    @Override
    public List<Article> findAllArticles() {
        return jdbcTemplate.query(
            "SELECT * FROM ARTICLES",
            articleRowMapper()
        );
    }

    @Override
    public boolean isArticleIdUsed(Integer aid) {
        List<Article> result = jdbcTemplate.query(
            "SELECT * FROM ARTICLES WHERE ID=?",
            articleRowMapper(), aid
        );
        return !result.isEmpty();
    }

    @Override
    public Article findArticleByArticleId(Integer aid) {
        List<Article> result = jdbcTemplate.query(
            "SELECT * FROM ARTICLES WHERE ID=?",
            articleRowMapper(), aid
        );
        return result.get(0);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, count) -> {
            User writer = userRepository.findByUsername(rs.getString("writer"));
            return new Article(
                rs.getInt("id"),
                rs.getString("title"),
                writer,
                rs.getString("contents")
            );
        };
    }
}
