package com.kakao.cafe.user.application.dto.result;

import com.kakao.cafe.user.adapter.in.web.dto.response.UserAccountEnrollResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserAccountEnrollResult {

    private final Long id;

    public UserAccountEnrollResponse toResponse() {
        return new UserAccountEnrollResponse(id);
    }
}
