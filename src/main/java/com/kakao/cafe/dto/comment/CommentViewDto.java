package com.kakao.cafe.dto.comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentViewDto {
    private final String contents;
    private final String writerNickName;
    private final String createdDateTime;
}
