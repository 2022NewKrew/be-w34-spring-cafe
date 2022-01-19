package com.kakao.cafe.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
public class ReplyInfoResponse {

    private Long id;
    private Long writerId;
    private String writerUserId;
    private String writerName;
    private String contents;
    private String replyTime;
    private Boolean isVisibleEdit;
}
