package com.kakao.cafe.comment.application.dto.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GetRelatedPostCommentResult {

    private final List<GetCommentResult> commentResults;

}
