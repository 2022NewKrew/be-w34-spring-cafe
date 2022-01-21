package com.kakao.cafe.app.data;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.Reply;
import com.kakao.cafe.domain.entity.ReplyDraft;
import com.kakao.cafe.domain.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Sql(scripts={"classpath:/db/sql/0_schema.sql"})
class JdbcReplyRepositoryTest {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private JdbcReplyRepository subject;
    private long insertedId;
    private Long insertedArticleId;
    private long insertedAuthorId;

    @BeforeEach
    public void setUp() {
        subject = new JdbcReplyRepository(jdbcTemplate);
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                "INSERT INTO users " +
                        "(user_id, name, email, password) " +
                        "VALUES ('foo', 'bar', 'foo@example.com', 'password')",
                new MapSqlParameterSource(Collections.emptyMap()),
                holder
        );
        insertedAuthorId = holder.getKey().longValue();
        jdbcTemplate.update(
                "INSERT INTO articles " +
                        "(owner_id, title, content, created_at) " +
                        "VALUES (:owner_id, 'title', 'content', NOW())",
                new MapSqlParameterSource(Collections.singletonMap("owner_id", insertedAuthorId)),
                holder
        );
        insertedArticleId = holder.getKey().longValue();
        jdbcTemplate.update(
                "INSERT INTO replies " +
                        "(article_id, author_id, content, created_at) " +
                        "VALUES (:article_id, :author_id, 'content', NOW())",
                new MapSqlParameterSource(Collections.singletonMap("article_id", insertedArticleId))
                        .addValue("author_id", insertedAuthorId),
                holder
        );
        insertedId = holder.getKey().longValue();
    }

    @AfterEach
    public void tearDown() {
        jdbcTemplate.update("DELETE FROM replies", Collections.emptyMap());
        jdbcTemplate.update("DELETE FROM articles", Collections.emptyMap());
        jdbcTemplate.update("DELETE FROM users", Collections.emptyMap());
    }

    @Test
    public void create() {
        Article target = new Article.Builder().id(insertedArticleId).build();
        User author = new User.Builder().id(insertedAuthorId).build();
        ReplyDraft draft = new ReplyDraft(target, author, "content");

        Reply result = subject.create(draft);

        assertNotNull(result);
    }

    @Test
    public void list() {
        List<Reply> result = subject.list(insertedArticleId);

        assertEquals(1, result.size());
    }

    @Test
    public void list_targetNotFound() {
        List<Reply> result = subject.list(-1);

        assertEquals(0, result.size());
    }

    @Test
    void getById() {
        Optional<Reply> result = subject.getById(insertedId);

        assertTrue(result.isPresent());
        assertEquals(insertedId, result.get().getId());
    }

    @Test
    void getById_notFound() {
        Optional<Reply> result = subject.getById(-1);

        assertTrue(result.isEmpty());
    }

    @Test
    void delete() {
        subject.delete(insertedId);

        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM replies WHERE id = :reply_id",
                new MapSqlParameterSource(Collections.singletonMap("reply_id", insertedId)),
                Integer.class
        );
        assertEquals(0, count);
    }
}
