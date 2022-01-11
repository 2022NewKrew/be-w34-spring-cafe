package com.kakao.cafe.reply.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Reply {
    private final Long replayId;
    private final Long articleId;
    private final Long authorId;
    private final String contents;
    private final LocalDateTime writeTime;
}
