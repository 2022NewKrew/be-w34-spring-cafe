package com.kakao.cafe.reply.repository;

import com.kakao.cafe.reply.domain.Reply;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@RequiredArgsConstructor
public class JdbcReplyRepository implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final String INSERT_QUERY = "insert into replies(writer, article, contents) value(?, ?, ?)";
    private static final String SELECT_QUERY = "select * from replies where id=?";
    private static final String SELECT_BY_ARTICLE_QUERY = "select * from replies where article=?";
    private static final String DELETE_QUERY = "delete from replies where id=?";

    @Override
    public void save(Reply reply) {
        jdbcTemplate.update(INSERT_QUERY,
            reply.getWriter(), reply.getArticle(), reply.getContents());
    }

    @Override
    public Reply findById(int id) {
        return jdbcTemplate.query(SELECT_QUERY, mapper, id).get(0);
    }

    @Override
    public List<Reply> findByArticle(int article) {
        return jdbcTemplate.query(SELECT_BY_ARTICLE_QUERY, mapper, article);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }

    static RowMapper<Reply> mapper = (rs, rowNum) ->
        Reply.builder()
            .id(rs.getInt("id"))
            .writer(rs.getString("writer"))
            .article(rs.getInt("article"))
            .contents(rs.getString("contents"))
            .build();
}
