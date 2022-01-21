package com.kakao.cafe.thread.dto;

import com.kakao.cafe.user.dto.UserView;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Builder
@Getter
@ToString
public class PostDetailView {
    private final Long id;
    private final UserView author;
    private final String title;
    private final String content;
    private final Integer commentCount;
    private final List<CommentView> comments;
    private final String createdAt;
    private final String lastModifiedAt;
}
