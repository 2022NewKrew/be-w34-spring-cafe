package com.kakao.cafe.persistence.post;

import com.kakao.cafe.exception.IdNotFoundException;
import com.kakao.cafe.post.adapter.out.persistence.QuestionPostRepository;
import com.kakao.cafe.post.domain.QuestionPost;
import com.kakao.cafe.user.domain.UserAccount;
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

    UserAccount userAccount;

    QuestionPost testPost1;

    QuestionPost testPost2;

    QuestionPost expected1;

    @BeforeEach
    void setUp() {
        userAccount = UserAccount.builder()
                .userAccountId(1L)
                .email("peach@kakao.com")
                .username("peach")
                .password("1234")
                .createdAt(LocalDateTime.of(2022, 1, 12, 6, 54))
                .build();

        testPost1 = QuestionPost.builder()
                .title("안녕하세요")
                .content("질문1")
                .createdAt(LocalDateTime.of(2022, 1, 12, 6, 54))
                .viewCount(0)
                .userAccount(userAccount)
                .build();

        testPost2 = QuestionPost.builder()
                .title("반갑습니다")
                .content("질문2")
                .createdAt(LocalDateTime.of(2022, 1, 12, 6, 54))
                .viewCount(0)
                .userAccount(userAccount)
                .build();

        expected1 = QuestionPost.builder()
                .questionPostId(1L)
                .title("안녕하세요")
                .content("질문1")
                .createdAt(LocalDateTime.of(2022, 1, 12, 6, 54))
                .viewCount(0)
                .userAccount(userAccount)
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
        System.out.println(result);
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

    @Test
    @Transactional
    void updateTest() {
        //given
        QuestionPost questionPost = questionPostRepository.save(testPost1);

        //when
        questionPost.viewCountIncrease();
        questionPostRepository.update(questionPost);

        //then
        QuestionPost result = questionPostRepository.findById(questionPost.getQuestionPostId()).orElseThrow(IllegalAccessError::new);
        Assertions.assertThat(result.getViewCount()).isEqualTo(1);
    }
}