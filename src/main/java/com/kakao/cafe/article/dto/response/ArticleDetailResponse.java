package com.kakao.cafe.article.dto.response;

import com.kakao.cafe.reply.dto.response.ReplyDetailResponse;
import com.kakao.cafe.user.dto.response.UserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class ArticleDetailResponse {

    private final Long id;
    private final UserInfoResponse writer;
    private final List<ReplyDetailResponse> replies;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
}
