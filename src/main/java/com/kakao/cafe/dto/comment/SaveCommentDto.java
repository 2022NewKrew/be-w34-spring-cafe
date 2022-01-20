package com.kakao.cafe.dto.comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveCommentDto {
    private final Long postId;
    private final String contents;
}
