package com.kakao.cafe.domain.article.dao;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.dto.ArticleTableRowDto;
import com.kakao.cafe.domain.util.TypeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(Article article) {
        final String sql = "INSERT INTO ARTICLES (USER_ID, TITLE, CONTENT) VALUES(?, ?, ?)";

        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});

            preparedStatement.setObject(1, article.getUserId());
            preparedStatement.setObject(2, article.getTitle());
            preparedStatement.setObject(3, article.getContent());

            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void update(Article article) {
        final String sql = "UPDATE ARTICLES SET TITLE = ?, CONTENT = ? WHERE ID = ? AND IS_DELETED IS FALSE";
        jdbcTemplate.update(sql, article.getTitle(), article.getContent(), article.getId());
    }

    public void delete(Article article) {
        final String sql = "UPDATE ARTICLES SET IS_DELETED = TRUE WHERE ID = ?";
        jdbcTemplate.update(sql, article.getId());
    }

    public boolean checkIfCanNotDelete(Article article) {
        final String sql =
                "SELECT EXISTS(" +
                        "SELECT * " +
                        "FROM ARTICLES A RIGHT OUTER JOIN COMMENTS C " +
                        "ON A.USER_ID = C.USER_ID " +
                        "WHERE A.USER_ID IS NULL AND C.ARTICLE_ID = ? AND C.IS_DELETED = FALSE)";
        final Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, article.getId());
        Objects.requireNonNull(result);

        return result;
    }

    public Optional<Article> findById(Long id) {
        final String sql =
                "SELECT A.ID as a_id, " +
                        "A.USER_ID as a_user_id, " +
                        "U.NICKNAME as u_nickname, " +
                        "A.TITLE as a_title, " +
                        "A.CONTENT as a_content, " +
                        "A.VIEW_COUNT as a_view_count, " +
                        "A.CREATED_AT as a_created_at " +
                        "FROM ARTICLES A " +
                        "INNER JOIN USERS U on U.ID = A.USER_ID " +
                        "WHERE A.ID = ? AND A.IS_DELETED = FALSE";

        List<Article> result = jdbcTemplate.query(sql, articleRowMapper(), id);
        return result.stream().findAny();
    }

    public void increaseViewCount(Long id) {
        final String sql = "UPDATE ARTICLES SET VIEW_COUNT = VIEW_COUNT + 1 WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<ArticleTableRowDto> retrieveTableRowById(Long id) {
        final String sql =
                "SELECT A.ID as a_id, " +
                        "A.USER_ID as a_user_id, " +
                        "U.NICKNAME as u_nickname, " +
                        "A.TITLE as a_title, " +
                        "A.CONTENT as a_content, " +
                        "A.VIEW_COUNT as a_view_count, " +
                        "A.CREATED_AT as a_created_at " +
                "FROM ARTICLES A " +
                        "INNER JOIN USERS U on U.ID = A.USER_ID " +
                "WHERE A.ID = ? AND A.IS_DELETED = FALSE";

        List<ArticleTableRowDto> result = jdbcTemplate.query(sql, articleTableRowDtoMapper(), id);
        return result.stream().findAny();
    }

    public List<ArticleTableRowDto> retrieveTableRows() {
        final String sql =
                "SELECT A.ID as a_id, " +
                        "A.USER_ID as a_user_id, " +
                        "U.NICKNAME as u_nickname, " +
                        "A.TITLE as a_title, " +
                        "A.CONTENT as a_content, " +
                        "A.VIEW_COUNT as a_view_count, " +
                        "A.CREATED_AT as a_created_at " +
                        "FROM ARTICLES A " +
                        "INNER JOIN USERS U on U.ID = A.USER_ID " +
                        "WHERE A.IS_DELETED = FALSE";

        return jdbcTemplate.query(sql, articleTableRowDtoMapper());
    }

    private RowMapper<Article> articleRowMapper() {
        return (resultSet, rowNum) ->
                (resultSet.wasNull()) ? null : Article.builder()
                        .id(resultSet.getLong("id"))
                        .userId(resultSet.getLong("user_id"))
                        .title(resultSet.getString("title"))
                        .content(resultSet.getString("content"))
                        .viewCount(resultSet.getLong("view_count"))
                        .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                        .build();
    }

    private RowMapper<ArticleTableRowDto> articleTableRowDtoMapper() {
        return (resultSet, rowNum) ->
                (resultSet.wasNull()) ? null : ArticleTableRowDto.builder()
                        .id(resultSet.getLong("a_id"))
                        .userId(resultSet.getLong("a_user_id"))
                        .author(resultSet.getString("u_nickname"))
                        .title(resultSet.getString("a_title"))
                        .content(resultSet.getString("a_content"))
                        .viewCount(resultSet.getLong("a_view_count"))
                        .createdAt(TypeConverter.convertLocalDateTimeToString(resultSet.getTimestamp("created_at").toLocalDateTime()))
                        .build();
    }
}
