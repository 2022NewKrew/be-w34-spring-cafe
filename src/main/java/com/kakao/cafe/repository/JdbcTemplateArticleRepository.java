package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.util.DateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateArticleRepository implements RepositoryInterface<Article> {
    private final JdbcTemplate jdbcTemplate;
    private static final String ALL_OF_ARTICLE = "select articleid, title, content, date, u.name as writer," +
            "a.writerid as writerid, view, deleted from articles as a join users as u " +
            "where a.writerid = u.userid AND deleted=false";
    private static final String ORDERED = " order by articleid desc";

    public JdbcTemplateArticleRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Article save(Article article) {
        article.setDate(DateUtils.getLocalDateTimeNow());
        article.setView(0L);

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("articles").usingGeneratedKeyColumns("articleid");

        Map<String, Object> parameters = makeParameters(article);
        Number id = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        article.setArticleId(id.longValue());
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        List<Article> result = jdbcTemplate.query(ALL_OF_ARTICLE + " AND articleid = ?" + ORDERED,
                articleRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Article> findByName(String name) {
        List<Article> result = jdbcTemplate.query(ALL_OF_ARTICLE + " AND title = ?" + ORDERED,
                articleRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(ALL_OF_ARTICLE + ORDERED, articleRowMapper());
    }

    @Override
    public Article update(Article article) {
        article.setDate(DateUtils.getLocalDateTimeNow());
        jdbcTemplate.update("update articles set title = ?, content = ?, date = ? where articleid = ?",
                article.getTitle(), article.getContent(), article.getDate(), article.getArticleId());
        return article;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("update articles set deleted=true where articleid = ?", id);
    }

    private RowMapper<Article> articleRowMapper() {
        return (resultSet, rowNum) -> {
            Article article = new Article();
            article.setArticleId(resultSet.getLong("articleid"));
            article.setTitle(resultSet.getString("title"));
            article.setContent(resultSet.getString("content"));
            article.setDate(resultSet.getString("date"));
            article.setWriter(resultSet.getString("writer"));
            article.setWriterId(resultSet.getLong("writerid"));
            article.setView(resultSet.getLong("view"));
            article.setDeleted(resultSet.getBoolean("deleted"));
            return article;
        };
    }

    private Map<String, Object> makeParameters(Article article) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("articleid", article.getArticleId());
        parameters.put("title", article.getTitle());
        parameters.put("content", article.getContent());
        parameters.put("date", article.getDate());
        parameters.put("writerid", article.getWriterId());
        parameters.put("view", article.getView());
        parameters.put("deleted", article.getDeleted());

        return parameters;
    }
}
