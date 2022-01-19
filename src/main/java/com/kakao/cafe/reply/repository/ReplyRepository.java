package com.kakao.cafe.reply.repository;

import com.kakao.cafe.reply.entity.Reply;
import com.kakao.cafe.user.exception.UserNotFoundException;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    public int save(Reply reply, Long articleId) {
        String sql = "insert into reply_table(writer_id, article_id, contents) values(?, ?, ?)";

        return this.writeQuery(
                sql,
                reply.getWriter().getId(),
                articleId,
                reply.getContents()
        );
    }

    public List<Reply> findByArticleId(Long articleId) {
        String sql = "select * from reply_table where article_id = ? and tombstone = false";

        return this.readListQuery(sql, articleId);
    }

    public Optional<Reply> findById(Long replyId) {
        String sql = "select * from reply_table where id = ? and tombstone = false";

        return this.readOneQuery(
                sql,
                replyId
        );
    }

    public int delete(Reply reply) {
        String sql = "update reply_table set tombstone = true where id = ?";

        return this.writeQuery(
                sql,
                reply.getId()
        );
    }

    public int deleteByArticleId(Long articleId) {
        String sql = "update reply_table set tombstone = true where id in (" +
                "select id from reply_table where article_id = ?" +
                ")";

        return this.writeQuery(
                sql,
                articleId
        );
    }

    /**
     * 단일 행을 반환하는 SELECT 문을 담당하는 메서드
     * @param sql: 실행하고자 하는 SQL
     * @param parameters: SQL 문에 들어갈 매개변수(가변인자)
     * @return Optional<Reply>: Optional 로 감싸진 Reply 인스턴스
     */
    private Optional<Reply> readOneQuery(String sql, Object... parameters) {
        List<Reply> replies = this.readListQuery(sql, parameters);

        return Optional.ofNullable(replies.isEmpty() ? null : replies.get(0));
    }

    /**
     * SELECT 문을 담당하는 메서드
     * @param sql: 실행하고자 하는 SQL
     * @param parameters: SQL 문에 들어갈 매개변수(가변인자)
     * @return List<Reply>: Reply 인스턴스로 이루어진 리스트
     */
    private List<Reply> readListQuery(String sql, Object... parameters) {
        return this.jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Reply(
                        rs.getLong("id"),
                        userRepository.findById(rs.getLong("writer_id")).orElseThrow(UserNotFoundException::new),
                        rs.getString("contents"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ), parameters
        );
    }

    /**
     * INSERT, DELETE, UPDATE 문을 담당하는 메서드
     * @param sql: 실행하고자 하는 SQL
     * @param parameters: SQL 문에 들어갈 매개변수(가변인자)
     * @return int: 영향받은 행의 개수(1)
     */
    private int writeQuery(String sql, Object... parameters) {
        return this.jdbcTemplate.update(
                sql, parameters
        );
    }
}
