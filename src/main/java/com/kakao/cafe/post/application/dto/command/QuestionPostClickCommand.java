package com.kakao.cafe.post.application.dto.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class QuestionPostClickCommand {

    private final Long questionPostId;

}
