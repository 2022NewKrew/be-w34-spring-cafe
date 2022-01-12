package com.kakao.cafe.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostViewDto {
    private final Long id;
    private final String title;
    private final String writerNickName;
    private final String createdDateTime;
    private final Integer viewNum;
}
