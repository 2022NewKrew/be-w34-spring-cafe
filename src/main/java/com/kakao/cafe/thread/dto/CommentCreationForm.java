package com.kakao.cafe.thread.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
@ToString
public class CommentCreationForm {

    @NotBlank
    private final String content;
}
