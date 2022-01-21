package com.kakao.cafe.domain.comment.dao;
import com.kakao.cafe.domain.comment.Comment;
import com.kakao.cafe.domain.comment.dto.CommentTableRowDto;
import com.kakao.cafe.domain.util.TypeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Comment comment) {
        final String sql = "INSERT INTO COMMENTS (USER_ID, ARTICLE_ID, CONTENT) VALUES(?, ?, ?)";

        Object[] parameters = {
                comment.getUserId(),
                comment.getArticleId(),
                comment.getContent()
        };

        jdbcTemplate.update(sql, parameters);
    }

    public void delete(Comment comment) {
        final String sql = "UPDATE COMMENTS SET IS_DELETED = TRUE WHERE ID = ?";
        jdbcTemplate.update(sql, comment.getId());
    }

    public void deletebyArticleId(Long articleId) {
        final String sql = "UPDATE COMMENTS SET IS_DELETED = TRUE WHERE ARTICLE_ID = ?";
        jdbcTemplate.update(sql, articleId);
    }

    public Optional<Comment> findById(Long id) {
        final String sql = "SELECT ID, USER_ID, ARTICLE_ID, CONTENT, CREATED_AT FROM COMMENTS WHERE ID = ? AND IS_DELETED = FALSE";
        List<Comment> result = jdbcTemplate.query(sql, commentRowMapper(), id);
        return result.stream().findAny();
    }

    public List<CommentTableRowDto> retrieveTableRowsByArticleId(Long articleId) {
        final String sql = "SELECT C.ID as c_id, " +
                "U.NICKNAME as u_nickname, " +
                "C.ARTICLE_ID as c_article_id, " +
                "C.CONTENT as c_content, " +
                "C.CREATED_AT as c_created_at " +
                "FROM COMMENTS C " +
                "JOIN USERS U on U.ID = C.USER_ID " +
                "WHERE C.ARTICLE_ID = ? AND C.IS_DELETED IS FALSE";
        return jdbcTemplate.query(sql, commentTableRowDtoRowMapper(), articleId);
    }

    private RowMapper<Comment> commentRowMapper() {
        return (resultSet, rowNum) ->
                (resultSet.wasNull()) ? null : Comment.builder()
                        .id(resultSet.getLong("id"))
                        .userId(resultSet.getLong("user_id"))
                        .articleId(resultSet.getLong("article_id"))
                        .content(resultSet.getString("content"))
                        .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                        .build();
    }

    private RowMapper<CommentTableRowDto> commentTableRowDtoRowMapper() {
        return (resultSet, rowNum) ->
                (resultSet.wasNull()) ? null : CommentTableRowDto.builder()
                        .id(resultSet.getLong("c_id"))
                        .author(resultSet.getString("u_nickname"))
                        .content(resultSet.getString("c_content"))
                        .createdAt(TypeConverter.convertLocalDateTimeToString(resultSet.getTimestamp("c_created_at").toLocalDateTime()))
                        .build();
    }
}
