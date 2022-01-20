package com.kakao.cafe.article.persistence;

import com.kakao.cafe.user.persistence.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class Article {

    private Long id;
    private User writer;
    private String title;
    private String content;
    private List<Reply> replies;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public boolean isDeletable(Long uid) {
        return replies.stream()
                .anyMatch(reply -> !(reply.getWriter().getId().equals(uid)));
    }
}
