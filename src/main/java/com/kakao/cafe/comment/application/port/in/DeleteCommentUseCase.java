package com.kakao.cafe.comment.application.port.in;

import com.kakao.cafe.comment.application.dto.command.DeleteCommentCommand;

public interface DeleteCommentUseCase {

    void delete(DeleteCommentCommand command);
}
