package com.kakao.cafe.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePostDto {
    private final Long postId;
    private final String title;
    private final String contents;
}
