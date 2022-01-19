package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
@Setter
public class ReplyDTO {
    private final Long id;
    private final String writerName;
    @NotNull
    private final Long articleID;
    @NotNull
    @Size(min = 1, max = 1000)
    private final String contents;
    private final Boolean isOwner;
    private final String time;
    private Long writerID;


}
