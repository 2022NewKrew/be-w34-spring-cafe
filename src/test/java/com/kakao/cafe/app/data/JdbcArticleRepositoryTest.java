package com.kakao.cafe.app.data;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.Draft;
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
@Sql(scripts={"classpath:/db/sql/schema.sql"})
class JdbcArticleRepositoryTest {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private JdbcArticleRepository subject;
    private Long insertedId;
    private long insertedOwnerId;

    @BeforeEach
    public void setUp() {
        subject = new JdbcArticleRepository(jdbcTemplate);
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                "INSERT INTO users " +
                        "(user_id, name, email, password) " +
                        "VALUES ('foo', 'bar', 'foo@example.com', 'password')",
                new MapSqlParameterSource(Collections.emptyMap()),
                holder
        );
        insertedOwnerId = holder.getKey().longValue();
        jdbcTemplate.update(
                "INSERT INTO articles " +
                        "(owner_id, author, title, content, created_at) " +
                        "VALUES (:owner_id, 'author', 'title', 'content', NOW())",
                new MapSqlParameterSource(Collections.singletonMap("owner_id", insertedOwnerId)),
                holder
        );
        insertedId = holder.getKey().longValue();
        jdbcTemplate.queryForObject(
                "SELECT id FROM articles WHERE author = 'author'",
                Collections.emptyMap(),
                Long.class
        );
    }

    @AfterEach
    public void tearDown() {
        jdbcTemplate.update("DELETE FROM articles", Collections.emptyMap());
        jdbcTemplate.update("DELETE FROM users", Collections.emptyMap());
    }

    @Test
    public void create() {
        User owner = new User.Builder().id(insertedOwnerId).build();
        Draft draft = new Draft(owner, "author", "title", "content");

        Article result = subject.create(draft);

        assertNotNull(result);
    }

    @Test
    public void list() {
        List<Article> result = subject.list();

        assertEquals(1, result.size());
    }

    @Test
    public void getById() {
        Optional<Article> result = subject.getById(insertedId);

        assertTrue(result.isPresent());
    }

    @Test
    public void getById_notFound() {
        Optional<Article> result = subject.getById(insertedId + 999);

        assertTrue(result.isEmpty());
    }
}
