package com.kakao.cafe.domain.comment;

import com.kakao.cafe.domain.base.BaseEntity;
import com.kakao.cafe.domain.comment.exception.UserIdNotMatchException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode(callSuper = false)
public class Comment extends BaseEntity {
    private Long userId;
    private Long articleId;
    private String content;

    @Builder
    public Comment(Long id, Long userId, Long articleId, String content, LocalDateTime createdAt, Boolean isDeleted) {
        super(id, createdAt, isDeleted);
        this.userId = userId;
        this.articleId = articleId;
        this.content = content;
    }

    public void validateUserId(Long userId) {
        if(!this.userId.equals(userId)) throw new UserIdNotMatchException();
    }
}
