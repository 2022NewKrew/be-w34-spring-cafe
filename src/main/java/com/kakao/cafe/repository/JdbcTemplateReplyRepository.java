package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.ReplyInfoResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class JdbcTemplateReplyRepository implements ReplyRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateReplyRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(Reply reply) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into REPLY_TABLE (CONTENTS, ARTICLE_ID,USER_ID,TIME) values (?,?,?,now())", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, reply.getContents());
            ps.setLong(2, reply.getArticleId());
            ps.setLong(3, reply.getUserId());
            return ps;
        }, keyHolder);
        return (Long)keyHolder.getKey();
    }
    @Override
    public List<ReplyInfoResponse> findAll(Long articleId) {
        final String sql = "select r.id, "
                + "r.user_Id, "
                + "u.userId, "
                + "u.name, "
                + "r.contents, "
                + "r.time "
                + "from REPLY_TABLE as r "
                + "inner join USER_TABLE u "
                + "on r.user_Id = u.id where article_Id = ? "
                + "order by r.time";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> ReplyInfoResponse.builder()
                        .id(rs.getLong("id"))
                        .writerId(rs.getLong("user_Id"))
                        .writerUserId(rs.getString("userId"))
                        .writerName(rs.getString("name"))
                        .contents(rs.getString("contents"))
                        .replyTime(rs.getString("time"))
                        .build(),
                articleId
        );
    }
}
