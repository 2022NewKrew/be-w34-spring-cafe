package com.kakao.cafe.domain.post;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode
@Getter
public class QuestionPost {

    private final Long questionPostId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private int viewCount;
    private final Long userAccountId;

    @Builder
    public QuestionPost(Long questionPostId, String title, String content, LocalDateTime createdAt, int viewCount, Long userAccountId) {
        this.questionPostId = questionPostId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
        this.userAccountId = userAccountId;
    }

    public void viewCountIncrease() {
        this.viewCount++;
    }

}
