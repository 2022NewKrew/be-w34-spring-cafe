package com.kakao.cafe.thread.dto;

import com.kakao.cafe.user.dto.UserView;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Builder
@Getter
@ToString
public class CommentView {
    private final Long id;
    private final UserView author;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;
}
