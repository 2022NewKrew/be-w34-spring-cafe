package com.kakao.cafe.user.application.dto.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UserAccountDetailListResult {

    private final List<UserAccountDetailResult> userAccountDetailResults;

}
