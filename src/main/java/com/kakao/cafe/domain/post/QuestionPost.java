package com.kakao.cafe.domain.post;

import com.kakao.cafe.application.dto.result.QuestionPostDetailResult;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@ToString
@EqualsAndHashCode
public class QuestionPost {

    private static final AtomicLong idGenerator;

    private final Long questionPostId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private int viewCount;
    private final Long userAccountId;

    static {
        idGenerator = new AtomicLong();
    }

    @Builder
    public QuestionPost(Long questionPostId, String title, String content, LocalDateTime createdAt, int viewCount, Long userAccountId) {
        this.questionPostId = Objects.requireNonNullElseGet(questionPostId, idGenerator::getAndIncrement);
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
        this.userAccountId = userAccountId;
    }

    public Long getQuestionPostId() {
        return this.questionPostId;
    }

    public Long getUserAccountId() {
        return this.userAccountId;
    }

    public void viewCountIncrease() {
        this.viewCount++;
    }

    public QuestionPostDetailResult toResult(String author) {
        return new QuestionPostDetailResult(
                questionPostId,
                title,
                content,
                createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                viewCount,
                author
        );
    }
}
