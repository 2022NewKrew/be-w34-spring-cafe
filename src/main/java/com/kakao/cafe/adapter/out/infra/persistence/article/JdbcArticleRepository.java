package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.domain.article.Article;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class JdbcArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Article article) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer", article.getWriter());
        parameters.put("title", article.getTitle());
        parameters.put("contents", article.getContents());
        parameters.put("createdAt", article.getCreatedAt());

        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public List<ArticleVO> getAllArticleList() {
        String sql = "select * from article";
        return jdbcTemplate.query(
            sql,
            (rs, count) -> new ArticleVO(
                rs.getInt("id"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getString("createdAt")
            )
        );
    }

    @Override
    public Optional<ArticleVO> findById(int id) {
        String sql = "select * from article where id = ?";
        ArticleVO articleVO = jdbcTemplate.queryForObject(
            sql,
            (rs, count) -> new ArticleVO(
                rs.getInt("id"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getString("createdAt")
            ), id
        );
        return Optional.ofNullable(articleVO);
    }
}
