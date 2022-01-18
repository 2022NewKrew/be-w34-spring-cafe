package com.kakao.cafe.user.application.dto.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserAccountCheckResult {

    private final boolean isCorrect;
    private final Long userAccountId;
}
