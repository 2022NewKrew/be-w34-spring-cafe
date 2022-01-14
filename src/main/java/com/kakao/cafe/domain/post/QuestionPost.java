package com.kakao.cafe.domain.post;

import com.kakao.cafe.domain.user.UserAccount;
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
    private final UserAccount userAccount;

    @Builder
    public QuestionPost(Long questionPostId, String title, String content, LocalDateTime createdAt, int viewCount, UserAccount userAccount) {
        this.questionPostId = questionPostId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
        this.userAccount = userAccount;
    }

    public void viewCountIncrease() {
        this.viewCount++;
    }

}
