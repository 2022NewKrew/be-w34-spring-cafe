package com.kakao.cafe.post.application.dto.result;

import com.kakao.cafe.post.adapter.in.web.dto.response.QuestionPostWriteResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class QuestionPostSaveResult {

    private final Long questionPostId;

    public QuestionPostWriteResponse toResponse() {
        return new QuestionPostWriteResponse(questionPostId);
    }
}
