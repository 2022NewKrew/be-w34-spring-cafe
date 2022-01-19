package com.kakao.cafe.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PostCommentDto {

    @NotEmpty
    private String text;
}
