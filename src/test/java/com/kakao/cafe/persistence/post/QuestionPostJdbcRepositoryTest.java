package com.kakao.cafe.persistence.post;

import com.kakao.cafe.application.exception.IdNotFoundException;
import com.kakao.cafe.domain.post.QuestionPost;
import com.kakao.cafe.domain.post.QuestionPostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class QuestionPostJdbcRepositoryTest {

    @Autowired
    @Qualifier("jdbc-question-db")
    QuestionPostRepository questionPostRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    QuestionPost testPost1;

    QuestionPost testPost2;

    QuestionPost expected1 = QuestionPost.builder()
            .questionPostId(1L)
            .title("안녕하세요")
            .content("질문1")
            .createdAt(LocalDateTime.of(2022, 1, 12, 6, 54))
            .viewCount(0)
            .userAccountId(0L)
            .build();

    @BeforeEach
    void setUp() {
        testPost1 = QuestionPost.builder()
                .title("안녕하세요")
                .content("질문1")
                .createdAt(LocalDateTime.of(2022, 1, 12, 6, 54))
                .viewCount(0)
                .userAccountId(0L)
                .build();

        testPost2 = QuestionPost.builder()
                .title("반갑습니다")
                .content("질문2")
                .createdAt(LocalDateTime.of(2022, 1, 12, 6, 54))
                .viewCount(0)
                .userAccountId(0L)
                .build();
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.update("alter table question_post alter column question_post_id restart with 1");
    }

    @Test
    @Transactional
    void saveTest() {
        QuestionPost result = questionPostRepository.save(testPost1);

        Assertions.assertThat(result).isEqualTo(expected1);
    }

    @Test
    @Transactional
    void findByIdTest() {
        QuestionPost saved = questionPostRepository.save(testPost1);

        QuestionPost result = questionPostRepository.findById(saved.getQuestionPostId())
                .orElseThrow(() -> new IdNotFoundException("유효하지 않는 값입니다"));

        Assertions.assertThat(result).isEqualTo(saved);
    }

    @Test
    @Transactional
    void findAllTest() {
        questionPostRepository.save(testPost1);
        questionPostRepository.save(testPost2);

        List<QuestionPost> result = questionPostRepository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}