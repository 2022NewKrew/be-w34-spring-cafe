package com.kakao.cafe.comment.application.port.in;

import com.kakao.cafe.comment.application.dto.command.GetCommentCommand;
import com.kakao.cafe.comment.application.dto.command.GetRelatedPostCommentCommand;
import com.kakao.cafe.comment.application.dto.result.GetCommentResult;
import com.kakao.cafe.comment.application.dto.result.GetRelatedPostCommentResult;

public interface GetCommentUseCase {

    GetRelatedPostCommentResult getRelatedPost(GetRelatedPostCommentCommand command);

    GetCommentResult get(GetCommentCommand command);
}
