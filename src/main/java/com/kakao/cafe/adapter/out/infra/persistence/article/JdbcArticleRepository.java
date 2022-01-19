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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class JdbcArticleRepository implements ArticleRepository {

    private final static String ARTICLE_TABLE = "ARTICLE";
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_WRITER = "writer";
    private final static String COLUMN_TITLE = "title";
    private final static String COLUMN_CONTENTS = "contents";
    private final static String COLUMN_CREATED_AT = "createdAt";
    private final static String SELECT_ALL = "select * from " + ARTICLE_TABLE;

    private final JdbcTemplate jdbcTemplate;

    public JdbcArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Article article) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName(ARTICLE_TABLE).usingGeneratedKeyColumns(COLUMN_ID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(COLUMN_WRITER, article.getWriter());
        parameters.put(COLUMN_TITLE, article.getTitle());
        parameters.put(COLUMN_CONTENTS, article.getContents());
        parameters.put(COLUMN_CREATED_AT, article.getCreatedAt());

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
    public List<Article> getAllArticleList() {
        return jdbcTemplate.query(SELECT_ALL, (rs, rowNum) -> new ArticleMapper().mapRow(rs, rowNum));
    }

    @Override
    public Optional<Article> findById(int id) {
        String sql = SELECT_ALL + " where " + COLUMN_ID + " = ?";
        try {
            Article article = jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> new ArticleMapper().mapRow(rs, rowNum),
                id
            );
            return Optional.ofNullable(article);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static final class ArticleMapper implements RowMapper<Article> {

        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                Article article = new Article.Builder().writer(rs.getString(COLUMN_WRITER))
                                                       .title(rs.getString(COLUMN_TITLE))
                                                       .contents(rs.getString(COLUMN_CONTENTS))
                                                       .createdAt(rs.getString(COLUMN_CREATED_AT))
                                                       .build();
                article.setId(rs.getInt(COLUMN_ID));
                return article;
            } catch (IllegalWriterException e) {
                throw new SQLException("DB에 저장된 writer가 잘못되었습니다.");
            } catch (IllegalTitleException e) {
                throw new SQLException("DB에 저장된 title이 잘못되었습니다.");
            } catch (IllegalDateException e) {
                throw new SQLException("DB에 저장된 createdAt 값이 잘못되었습니다.");
            }
        }
    }
}
