package com.kakao.cafe.repository.jdbc;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcArticleRepository implements ArticleRepository {

    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE articles IF EXISTS ";
    private static final String ARTICLE_TABLE_DDL = "CREATE TABLE articles(" +
            "id BIGINT GENERATED BY DEFAULT AS IDENTITY, " +
            "writer_name VARCHAR(255), " +
            "title VARCHAR(255), " +
            "contents VARCHAR(255), " +
            "number_of_reply INT, " +
            "created_time TIMESTAMP, " +
            "PRIMARY KEY(id) )";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM articles WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM articles";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcTemplate.execute(DROP_TABLE_IF_EXISTS);
        this.jdbcTemplate.execute(ARTICLE_TABLE_DDL);
    }

    @Override
    public void saveArticle(Article article) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("articles").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = assembleParameters(article);
        jdbcInsert.execute(new MapSqlParameterSource(parameters));
    }

    private Map<String, Object> assembleParameters(Article article) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("writer_name", article.getWriterName());
        parameters.put("title", article.getTitle());
        parameters.put("contents", article.getContents());
        parameters.put("number_of_reply", article.getNumberOfReply());
        parameters.put("created_time", Timestamp.valueOf(article.getCreatedTime()));
        return parameters;
    }

    @Override
    public Optional<Article> findArticleById(Long articleId) {
        List<Article> result = jdbcTemplate.query(FIND_BY_ID_SQL, articleRowMapper(), articleId);
        return result.stream().findAny();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, articleRowMapper());
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article(
                    rs.getString("writer_name"),
                    rs.getString("title"),
                    rs.getString("contents"),
                    rs.getInt("number_of_reply"),
                    rs.getTimestamp("created_time").toLocalDateTime()
            );
            article.setId(rs.getLong("id"));
            return article;
        };
    }
}
