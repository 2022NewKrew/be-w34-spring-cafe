package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReplyRepository implements CrudRepository<Reply, Integer> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Reply save(Reply entity) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO reply(article_id, writer, contents, user_pk)");
        query.append(" VALUES (?, ?, ?, ?)");
        int update = jdbcTemplate.update(query.toString(), entity.getArticleId(), entity.getWriter(), entity.getContents(), entity.getUserPk());
        if (update > 0) {
            return entity;
        }

        return null;
    }

    @Override
    public Optional<Reply> findById(Integer id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT *");
        query.append(" FROM reply");
        query.append(" WHERE reply.id = ? AND reply.deleted = FALSE");
        Reply reply = jdbcTemplate.queryForObject(query.toString(), (rs, rowNum) -> new Reply(rs.getInt("id"), rs.getInt("article_id"), rs.getString("writer"), rs.getString("contents"), rs.getInt("user_pk"), rs.getBoolean("deleted")), id);
        return Optional.ofNullable(reply);
    }

    @Override
    public boolean delete(Reply entity) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE reply");
        query.append(" SET deleted = ?");
        query.append(" WHERE reply.id = ?");
        int delete = jdbcTemplate.update(query.toString(),true, entity.getId());
        return delete > 0;
    }

    public List<Reply> findAll(int articleId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT *");
        query.append(" FROM reply");
        query.append(" WHERE reply.article_id = ? AND reply.deleted = FALSE");
        return jdbcTemplate.query(query.toString(), (rs, rowNum) -> new Reply(rs.getInt("id"), rs.getInt("article_id"), rs.getString("writer"), rs.getString("contents"), rs.getInt("user_pk"), rs.getBoolean("deleted")), articleId);
    }
}
