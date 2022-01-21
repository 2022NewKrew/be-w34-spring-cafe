package com.kakao.cafe.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.kakao.cafe.error.ErrorCode;
import com.kakao.cafe.error.exception.ReplyNotFoundException;
import com.kakao.cafe.persistence.model.Reply;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@Sql({"classpath:jdbc/schema.sql", "classpath:jdbc/reply-test-data.sql"})
class ReplyRepositoryTest {

    private ReplyRepository replyRepository;

    @BeforeEach
    void initTest(@Autowired JdbcTemplate jdbcTemplate) {
        replyRepository = new ReplyRepositoryImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("Reply Repository 저장 후 조회 테스트")
    void saveAndFindByArticleId() {
        // Given
        Reply reply1 = Reply.builder().articleId(1L).uid("uid").userName("name").body("body1")
            .createdAt(LocalDateTime.now()).build();
        Reply reply2 = Reply.builder().articleId(1L).uid("uid").userName("name").body("body2")
            .createdAt(LocalDateTime.now()).build();
        Reply reply3 = Reply.builder().articleId(1L).uid("uid").userName("name").body("body3")
            .createdAt(LocalDateTime.now()).build();
        replyRepository.save(reply1);
        replyRepository.save(reply2);
        replyRepository.save(reply3);

        // When
        List<Reply> replies = replyRepository.findByArticleId(1L);

        // Then
        assertThat(replies.size())
            .isEqualTo(3);
    }

    @Test
    @DisplayName("Reply 수정 테스트")
    void update() {
        // Given
        replyRepository.save(Reply.builder().articleId(1L).uid("uid").userName("name").body("body")
            .createdAt(LocalDateTime.now()).build());

        // When
        replyRepository.update(1L, "modified");
        Reply reply = replyRepository.findById(1L)
            .orElseThrow(() -> new ReplyNotFoundException(ErrorCode.NOT_FOUND, 1L));

        // Then
        assertThat(reply.getBody())
            .contains("modified");
    }

    @Test
    @DisplayName("Reply 삭제 테스트")
    void delete() {
        // Given
        replyRepository.save(Reply.builder().articleId(1L).uid("uid").userName("name").body("body")
            .createdAt(LocalDateTime.now()).build());

        // When
        replyRepository.delete(1L);
        ReplyNotFoundException exception = assertThrows(ReplyNotFoundException.class,
            () -> replyRepository.findById(1L)
                .orElseThrow(() -> new ReplyNotFoundException(ErrorCode.NOT_FOUND, 1L)));

        // Then
        assertThat(exception.getMessage())
            .contains("Not Found Reply");
    }

    @Test
    @DisplayName("Reply ID 로 조회 테스트")
    void findById() {
        // Given
        replyRepository.save(Reply.builder().articleId(1L).uid("uid").userName("name").body("body")
            .createdAt(LocalDateTime.now()).build());

        // When
        assertDoesNotThrow(() -> replyRepository.findById(1L)
            .orElseThrow(() -> new ReplyNotFoundException(ErrorCode.NOT_FOUND, 1L)));

        // Then
    }
}