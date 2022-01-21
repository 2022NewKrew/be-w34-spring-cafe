package com.kakao.cafe.reply.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplySaveDto {

    @NotBlank(message = "contents is mandatory")
    private String contents;

    @NotNull(message = "article is mandatory")
    private int article;

    private String writer;
}
