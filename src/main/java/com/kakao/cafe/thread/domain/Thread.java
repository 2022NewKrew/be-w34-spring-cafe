package com.kakao.cafe.thread.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@ToString
public class Thread {
    private final Long id;
    private final Long parentId;
    private final Long authorId;
    private final String title;
    private final String content;
    private final String status;
    private final String type;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;
}
