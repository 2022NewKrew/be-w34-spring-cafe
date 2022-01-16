package com.kakao.cafe.post.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class QuestionPostDetailListResult {

    private final List<QuestionPostDetailResult> detailResults;

}
