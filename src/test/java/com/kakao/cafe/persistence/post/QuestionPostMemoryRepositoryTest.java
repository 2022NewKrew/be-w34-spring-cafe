package com.kakao.cafe.persistence.post;

import com.kakao.cafe.domain.post.QuestionPost;
import com.kakao.cafe.domain.post.QuestionPostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class QuestionPostMemoryRepositoryTest {

    @Autowired
    @Qualifier("simple-question-db")
    QuestionPostRepository questionPostRepository;

    QuestionPost testPost1 = QuestionPost.builder()
            .title("안녕하세요")
            .content("질문1")
            .createdAt(LocalDateTime.now())
            .viewCount(0)
            .userAccountId(0L)
            .build();

    QuestionPost testPost2 = QuestionPost.builder()
            .title("반갑습니다")
            .content("질문2")
            .createdAt(LocalDateTime.now())
            .viewCount(0)
            .userAccountId(0L)
            .build();

    @AfterEach
    void tearDown() {
        questionPostRepository.deleteAll();
    }

    @Test
    void saveTest() {
        QuestionPost result = questionPostRepository.save(testPost1);

        Assertions.assertThat(result).isEqualTo(testPost1);
    }

    @Test
    void findByIdTest() {
        QuestionPost saved = questionPostRepository.save(testPost1);

        QuestionPost result = questionPostRepository.findById(saved.getQuestionPostId()).orElseThrow(IllegalAccessError::new);

        Assertions.assertThat(result).isEqualTo(saved);
    }

    @Test
    void findAllTest() {
        questionPostRepository.save(testPost1);
        questionPostRepository.save(testPost2);

        List<QuestionPost> result = questionPostRepository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
