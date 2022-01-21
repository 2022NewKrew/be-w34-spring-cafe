package com.kakao.cafe.web.repository.comment;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.domain.Comment;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateCommentRepository implements CommentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Comment> commentRowMapper = new BeanPropertyRowMapper<>(Comment.class);

    public JdbcTemplateCommentRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void save(Comment comment) {
        String sql = "insert into comments (`article_id`, `content`, `writer`) values(:articleId,:content,:writer)";
        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(comment);
        jdbcTemplate.update(sql, paramSource);
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        String sql = "delete from comments where id = :id";
        jdbcTemplate.update(sql, param);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        List<Comment> result = jdbcTemplate.query("select * from comments where `id` = :id", param, commentRowMapper);
        return Optional.ofNullable(DataAccessUtils.singleResult(result));
    }

    @Override
    public List<Comment> findAllByArticleId(Long articleId) {
        SqlParameterSource param = new MapSqlParameterSource("articleId", articleId);
        return jdbcTemplate.query("select * from comments where `article_id` = :articleId", param, commentRowMapper);
    }

}
