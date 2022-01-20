package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.persistence.Reply;
import com.kakao.cafe.global.util.JdbcUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepository {

    private final JdbcUtil<Reply> jdbcUtil;

    @Override
    public void save(Reply reply, Long articleId) {
        String sql = "INSERT INTO REPLIES(writer_id, article_id, contents) VALUES(?, ?, ?)";
        jdbcUtil.writeRecord(sql,
                reply.getWriter().getId(),
                articleId,
                reply.getContent()
        );
    }

    @Override
    public List<Reply> findByArticleId(Long articleId) {
        String sql = "SELECT * FROM REPLIES WHERE article_id = ? AND deleted = FALSE";
        return jdbcUtil.readRecords(sql, articleId);
    }

    @Override
    public Optional<Reply> findById(Long id) {
        String sql = "SELECT * FROM REPLIES WHERE id = ? AND deleted = FALSE";
        return jdbcUtil.readRecord(sql, id);
    }

    @Override
    public void deleteByArticleId(Long id) {
        String sql = "UPDATE REPLIES SET deleted = TRUE WHERE id = ?";
        jdbcUtil.writeRecord(sql, id);
    }
}
