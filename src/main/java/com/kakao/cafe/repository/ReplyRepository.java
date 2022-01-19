package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Reply;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReplyRepository implements MyRepository<Reply, Long> {

    private final JdbcTemplate jdbcTemplate;
    private final ReplyMapper mapper;

    public ReplyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new ReplyMapper();
    }

    public List<Reply> findAllByArticleId(Long articleId) {
        String sql = "select r.id, r.article_id, r.author_id, u.nickname, r.description " +
                "from reply r join users u " +
                "on r.author_id = u.id " +
                "where r.article_id = ?";

        return jdbcTemplate.query(sql, mapper, articleId);
    }

    @Override
    public void save(Reply entity) {
        String sql = "insert into reply (article_id, author_id, description) values ( ?, ?, ? )";

        jdbcTemplate.update(
                sql,
                entity.getArticleId(),
                entity.getAuthorId(),
                entity.getDescription()
        );
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from reply where id = ?";

        jdbcTemplate.update(sql, id);
    }

    private static class ReplyMapper implements RowMapper<Reply> {
        @Override
        public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Reply(
                    rs.getLong("id"),
                    rs.getLong("article_id"),
                    rs.getLong("author_id"),
                    rs.getString("nickname"),
                    rs.getString("description")
            );
        }
    }
}
