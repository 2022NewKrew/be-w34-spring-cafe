package com.kakao.cafe.persistence.post;

import com.kakao.cafe.domain.post.QuestionPost;
import com.kakao.cafe.domain.post.QuestionPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("jdbc-question-db")
@RequiredArgsConstructor
public class QuestionPostJdbcRepository implements QuestionPostRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public QuestionPost save(QuestionPost questionPost) {
        String sql = "insert into question_post (title, content, created_at, view_count, user_account_id) values(?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"question_post_id"});
            ps.setString(1, questionPost.getTitle());
            ps.setString(2, questionPost.getContent());
            ps.setTimestamp(3, Timestamp.valueOf(questionPost.getCreatedAt()));
            ps.setInt(4, questionPost.getViewCount());
            ps.setLong(5, questionPost.getUserAccountId());
            return ps;
        }, keyHolder);

        return QuestionPost.builder()
                .questionPostId(keyHolder.getKey().longValue())
                .title(questionPost.getTitle())
                .content(questionPost.getContent())
                .createdAt(questionPost.getCreatedAt())
                .viewCount(questionPost.getViewCount())
                .userAccountId(questionPost.getUserAccountId())
                .build();
    }

    @Override
    public Optional<QuestionPost> findById(Long id) {
        String sql = "select question_post_id, title, content, created_at, view_count, user_account_id from question_post where question_post_id = ?";

        QuestionPost questionPost = jdbcTemplate.queryForObject(
                sql,
                (result, row) -> QuestionPost.builder()
                        .questionPostId(result.getLong("question_post_id"))
                        .title(result.getString("title"))
                        .content(result.getString("content"))
                        .createdAt(result.getTimestamp("created_at").toLocalDateTime())
                        .viewCount(result.getInt("view_count"))
                        .userAccountId(result.getLong("user_account_id"))
                        .build(),
                id);

        return Optional.of(questionPost);
    }

    @Override
    public List<QuestionPost> findAll() {
        String sql = "select question_post_id, title, content, created_at, view_count, user_account_id from question_post";

        List<QuestionPost> questionPost = jdbcTemplate.query(
                sql,
                (result, row) -> QuestionPost.builder()
                        .questionPostId(result.getLong("question_post_id"))
                        .title(result.getString("title"))
                        .content(result.getString("content"))
                        .createdAt(result.getTimestamp("created_at").toLocalDateTime())
                        .viewCount(result.getInt("view_count"))
                        .userAccountId(result.getLong("user_account_id"))
                        .build());
        return questionPost;
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from question_post where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteAll() {

    }
}
