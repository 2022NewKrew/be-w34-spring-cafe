package com.kakao.cafe.post.adapter.in.web.dto.request;

import com.kakao.cafe.post.application.dto.command.QuestionPostSaveCommand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class QuestionPostWriteRequest {

    @NotNull
    private final String title;
    @NotNull
    private final String content;

    public QuestionPostSaveCommand toCommand(Long userAccountId) {
        return new QuestionPostSaveCommand(title, content, userAccountId);
    }
}
