package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class JdbcArticleRepository implements ArticleRepository {

    private final static String SELECT_ALL = "select * from article";

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
    public List<Article> getAllArticleList() {
        return jdbcTemplate.query(SELECT_ALL, (rs, rowNum) -> new ArticleMapper().mapRow(rs, rowNum));
    }

    @Override
    public Optional<Article> findById(int id) {
        String sql = SELECT_ALL + " where id = ?";
        Article article = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new ArticleMapper().mapRow(rs, rowNum), id);
        return Optional.ofNullable(article);
    }

    private static final class ArticleMapper implements RowMapper<Article> {

        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                Article article = new Article.Builder().writer(rs.getString("writer"))
                                                       .title(rs.getString("title"))
                                                       .contents(rs.getString("contents"))
                                                       .createdAt(rs.getString("createdAt"))
                                                       .build();
                article.setId(rs.getInt("id"));
                return article;
            } catch (IllegalWriterException | IllegalTitleException | IllegalDateException e) {
                throw new SQLException("DB에서 값을 읽어오지 못했습니다.");
            }
        }
    }
}
