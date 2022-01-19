package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.CommentDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CommentRepositoryJdbcTemplate implements CommentRepository {
    private final JdbcTemplate jdbcTemplate;

    public CommentRepositoryJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long insert(Long articleKey, Long authorKey, Comment comment) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("comment").usingGeneratedKeyColumns("key");


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("articleKey", articleKey);
        parameters.put("authorKey", authorKey);
        parameters.put("content", comment.getContent());
        parameters.put("postTime", comment.getPostTime());
        parameters.put("deleted", "false");

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return key.longValue();
    }

    @Override
    public void update(Long key, String content) {
        jdbcTemplate.update("UPDATE comment SET content = ? WHERE `key` = ?",
                content, key);
    }

    @Override
    public void delete(Long key) {
        jdbcTemplate.update("update comment set deleted = true WHERE `key` = ?", key);
    }

    @Override
    public void deleteByArticleKey(Long key) {
        jdbcTemplate.update("update comment set deleted = true WHERE `articleKey` = ?", key);
    }

    @Override
    public List<Comment> selectByArticleKey(Long key) {
        return jdbcTemplate.query(
                "SELECT c.key, c.content, c.postTime, c.articleKey, u.key, u.name " +
                        "from comment as c left join user as u on c.authorKey = u.key " +
                        "where c.articleKey = ? and c.deleted = false", commentRowMapper(), key);
    }

    @Override
    public Optional<Comment> selectByKey(Long key) {
        List<Comment> commentList = jdbcTemplate.query(
                                "SELECT c.key, c.content, c.postTime, c.articleKey, u.key, u.name " +
                                        "from comment as c left join user as u on c.authorKey = u.key " +
                                        "where c.key = ? and c.deleted = false", commentRowMapper(), key);
        return commentList.stream().findAny();
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> {
            Comment comment = Comment.builder()
                    .key(rs.getLong("c.key"))
                    .author(User.builder()
                            .key(rs.getLong("u.key"))
                            .name(rs.getString("u.name"))
                            .build())
                    .article(Article.builder()
                            .key(rs.getLong("c.articleKey"))
                            .build())
                    .content(rs.getString("c.content"))
                    .postTime(rs.getTimestamp("c.postTime").toLocalDateTime())
                    .build();
            return comment;
        };
    }
}
