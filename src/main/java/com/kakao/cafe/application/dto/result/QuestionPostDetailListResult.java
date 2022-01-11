package com.kakao.cafe.application.dto.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class QuestionPostDetailListResult {

    private final List<QuestionPostDetailResult> detailResults;

}
