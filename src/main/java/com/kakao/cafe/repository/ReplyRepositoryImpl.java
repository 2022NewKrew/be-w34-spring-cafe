package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.entity.Reply;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ReplyRepositoryImpl implements ReplyRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ReplyMapper mapper;

    public ReplyRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new ReplyMapper();
    }

    @Override
    public void save(Reply entity) {
        String sql = "insert into reply (article_id, content, writer_email) values (?, ?, ?)";
        jdbcTemplate.update(sql, entity.getArticle().getArticleId(), entity.getContent(), entity.getWriter().getEmail());
    }

    @Override
    public void update(Reply entity) {
        String sql = "update reply set content=? where article_id=? and reply_id=?";
        jdbcTemplate.update(sql, entity.getContent(), entity.getArticle().getArticleId(), entity.getReplyId());
    }

    @Override
    public List<Reply> findAllByArticleId(Long articleId) {
        String sql = "" +
                "select a.*, u.* " +
                "from reply as a " +
                "left join user as u " +
                "on a.writer_email = u.email " +
                "where article_id = ?";
        return jdbcTemplate.query(sql, mapper, articleId);
    }

    @Override
    public void deleteById(Long replyId) {
        return; // TODO impl
    }

    private static class ReplyMapper implements RowMapper<Reply> {
        @Override
        public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Reply.builder()
                    .article(Article.builder()
                            .articleId(rs.getLong("article_id"))
                            .build())
                    .writer(User.builder()
                            .email(rs.getString("writer_email"))
                            .username(rs.getString("username"))
                            .build())
                    .replyId(rs.getLong("reply_id"))
                    .content(rs.getString("content"))
                    .regDate(rs.getTimestamp("reg_date").toLocalDateTime())
                    .modDate(rs.getTimestamp("mod_date").toLocalDateTime())
                    .build();
        }
    }
}
