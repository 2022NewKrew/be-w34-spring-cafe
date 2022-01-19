package com.kakao.cafe.reply.dto.response;

import com.kakao.cafe.user.dto.response.UserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ReplyDetailResponse {

    private final Long id;
    private final UserInfoResponse writer;
    private final String contents;
    private final LocalDateTime createdAt;
}
