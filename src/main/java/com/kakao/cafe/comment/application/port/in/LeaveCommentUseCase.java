package com.kakao.cafe.comment.application.port.in;

import com.kakao.cafe.comment.application.dto.command.LeaveCommentCommand;
import com.kakao.cafe.comment.application.dto.result.LeaveCommentResult;

public interface LeaveCommentUseCase {

    LeaveCommentResult leave(LeaveCommentCommand command);
}
