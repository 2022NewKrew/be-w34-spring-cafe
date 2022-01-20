package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.domain.article.Article;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class JdbcArticleRepository implements ArticleRepository {

    final static String COLUMN_ID = "id";
    final static String COLUMN_USER_ID = "userId";
    final static String COLUMN_WRITER = "writer";
    final static String COLUMN_TITLE = "title";
    final static String COLUMN_CONTENTS = "contents";
    final static String COLUMN_CREATED_AT = "createdAt";
    final static String COLUMN_DELETED = "deleted";
    private final static String ARTICLE_TABLE = "ARTICLE";
    private final static String QUERY_SELECT_ALL = "select * from " + ARTICLE_TABLE;
    private final static String QUERY_CONDITION_NOT_DELETED = " where " + COLUMN_DELETED + "=false";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Article> articleMapper;

    public JdbcArticleRepository(DataSource dataSource, RowMapper<Article> articleMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.articleMapper = articleMapper;
    }

    @Override
    public void save(Article article) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName(ARTICLE_TABLE).usingGeneratedKeyColumns(COLUMN_ID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(COLUMN_USER_ID, article.getUserId());
        parameters.put(COLUMN_WRITER, article.getWriter());
        parameters.put(COLUMN_TITLE, article.getTitle());
        parameters.put(COLUMN_CONTENTS, article.getContents());
        parameters.put(COLUMN_CREATED_AT, article.getCreatedAt());
        parameters.put(COLUMN_DELETED, article.isDeleted());

        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public void update(Article article) {
        String sql = "update " + ARTICLE_TABLE + " set " + COLUMN_TITLE + "=?, " + COLUMN_CONTENTS + "=?, " +
                     COLUMN_CREATED_AT + "=? where " + COLUMN_ID +
                     "=?";
        jdbcTemplate.update(sql, article.getTitle(), article.getContents(), article.getCreatedAt(), article.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "update " + ARTICLE_TABLE + " set " + COLUMN_DELETED + "=true" + " where " + COLUMN_ID + "=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Article> getAllArticleList() {
        return jdbcTemplate.query(QUERY_SELECT_ALL + QUERY_CONDITION_NOT_DELETED, articleMapper);
    }

    @Override
    public Optional<Article> findById(int id) {
        String sql = QUERY_SELECT_ALL + " where " + COLUMN_ID + " = ?";
        try {
            Article article = jdbcTemplate.queryForObject(sql, articleMapper, id);
            return Optional.ofNullable(article);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
