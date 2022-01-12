package com.kakao.cafe.app.data;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.Draft;
import com.kakao.cafe.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryArticleRepositoryTest {

    private InMemoryArticleRepository subject;

    @BeforeEach
    void setUp() {
        subject = new InMemoryArticleRepository();
        User owner = new User.Builder().build();
        Draft draft = new Draft(owner, "author", "title", "content");
        subject.create(draft);
    }

    @Test
    void create() {
        User owner = new User.Builder().build();
        Draft draft = new Draft(owner, "author", "title", "content");

        Article result = subject.create(draft);

        assertNotNull(result);
    }

    @Test
    void list() {
        List<Article> result = subject.list();

        assertEquals(1, result.size());
    }

    @Test
    void getById() {
        Article result = subject.getById(1);

        assertNotNull(result);
    }

    @Test
    void getById_notFound() {
        Article result = subject.getById(999);

        assertNull(result);
    }
}
