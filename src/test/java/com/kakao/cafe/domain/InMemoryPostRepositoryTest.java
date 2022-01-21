package com.kakao.cafe.domain;

import com.kakao.cafe.exceptions.PostNotFoundException;
import com.kakao.cafe.repository.InMemoryPostRepository;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InMemoryPostRepositoryTest {

    private static final int INITIAL_ID = 0;
    private static final int userId = 1;
    private static final String title = "testTitle";
    private static final String content = "testUserId";

    @Test
    @DisplayName("[성공] InMemoryPostRepository 클래스 생성")
    void InMemoryPostRepository() {
        new InMemoryPostRepository();
    }

    @Test
    @DisplayName("[성공] Post 저장")
    void save() {
        InMemoryPostRepository inMemoryPostRepository = new InMemoryPostRepository();
        Post post = new Post(INITIAL_ID, userId, title, content, new Date());

        inMemoryPostRepository.save(post);
    }

    @Test
    @DisplayName("[성공] Post 저장 - index가 올바르게 증가되어야 한다")
    void save_IndexIncrease() {
        InMemoryPostRepository inMemoryPostRepository = new InMemoryPostRepository();
        Post post = new Post(INITIAL_ID, userId, title, content, new Date());
        Post post2 = new Post(INITIAL_ID, userId, title, content, new Date());

        inMemoryPostRepository.save(post);
        inMemoryPostRepository.save(post2);

        List<Post> postResult = inMemoryPostRepository.findAll();
        Assertions.assertEquals(1, postResult.get(0).getId());
        Assertions.assertEquals(2, postResult.get(1).getId());
    }

    @Test
    @DisplayName("[성공] Post 목록")
    void findAll() {
        InMemoryPostRepository inMemoryPostRepository = new InMemoryPostRepository();
        Post post = new Post(INITIAL_ID, userId, title, content, new Date());
        Post post2 = new Post(INITIAL_ID, userId, title, content, new Date());

        inMemoryPostRepository.save(post);
        inMemoryPostRepository.save(post2);

        List<Post> postResult = inMemoryPostRepository.findAll();
        Assertions.assertEquals(post, postResult.get(0));
        Assertions.assertEquals(post2, postResult.get(1));
    }

    @Test
    @DisplayName("[성공] 게시글 Id로 게시글 찾기")
    void findByPostId() {
        InMemoryPostRepository inMemoryPostRepository = new InMemoryPostRepository();
        Post post = new Post(INITIAL_ID, userId, title, content, new Date());
        inMemoryPostRepository.save(post);

        Post post_Result = inMemoryPostRepository.findByPostId(1);

        Assertions.assertEquals(post, post_Result);
    }

    @DisplayName("[실패] 범위를 벗어난 게시글 Id로 조회")
    @ParameterizedTest(name = "id = {0}")
    @ValueSource(ints = {-1, 0, 2, 999})
    void findByPostId_FailedBy_InvalidPostId(int invalidId) {
        InMemoryPostRepository inMemoryPostRepository = new InMemoryPostRepository();
        Post post = new Post(INITIAL_ID, userId, title, content, new Date());
        inMemoryPostRepository.save(post);

        Assertions.assertThrows(PostNotFoundException.class,
                () -> inMemoryPostRepository.findByPostId(invalidId));
    }
}
