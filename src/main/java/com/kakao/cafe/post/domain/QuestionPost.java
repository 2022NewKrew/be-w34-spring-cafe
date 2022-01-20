package com.kakao.cafe.post.domain;

import com.kakao.cafe.user.domain.UserAccount;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@EqualsAndHashCode
@Getter
public class QuestionPost {

    private final Long questionPostId;
    private String title;
    private String content;
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

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void viewCountIncrease() {
        this.viewCount++;
    }

    public boolean isSameAuthor(Long userAccountId) {
        return Objects.equals(this.userAccount.getUserAccountId(), userAccountId);
    }

}
