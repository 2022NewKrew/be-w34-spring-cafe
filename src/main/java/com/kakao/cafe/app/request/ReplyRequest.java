// NOTE 얘네도 네트워크 상에서 컨트롤러로 전달되는 DTO가 아닐까?
package com.kakao.cafe.app.request;

import com.kakao.cafe.service.dto.ReplyDraftDto;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

public class ReplyRequest {

    @NonNull
    @Length(min=1, max=245)
    private final String content;

    public ReplyRequest(
            String content
    ) {
        this.content = content;
    }

    public ReplyDraftDto toDraftDto() {
        return new ReplyDraftDto(content);
    }
}
