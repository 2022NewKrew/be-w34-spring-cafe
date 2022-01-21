package com.kakao.cafe.thread.dto;

import com.kakao.cafe.user.dto.UserView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@ToString
public class PostView {
    private final Long id;
    private final UserView author;
    private final String title;
    private final String content;
    // private final CommentView commentView;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;
}
