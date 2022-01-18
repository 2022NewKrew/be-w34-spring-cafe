package com.kakao.cafe.article.dto.response;

import com.kakao.cafe.user.dto.response.UserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ArticleDetailResponse {

    private final Long id;
    private final UserInfoResponse writer;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
}
