package com.kakao.cafe.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ReplyRestResponseDTO extends RestResponseDTO {
    private final List<ReplyDTO> replyList;

    public ReplyRestResponseDTO(List<ReplyDTO> replyList) {
        super(true);
        this.replyList = replyList;
    }
}
