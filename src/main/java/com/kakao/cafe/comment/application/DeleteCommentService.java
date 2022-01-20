package com.kakao.cafe.comment.application;

import com.kakao.cafe.comment.application.dto.command.DeleteCommentCommand;
import com.kakao.cafe.comment.application.port.in.DeleteCommentUseCase;
import com.kakao.cafe.comment.application.port.out.DeleteCommentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class DeleteCommentService implements DeleteCommentUseCase {

    private final DeleteCommentPort deleteCommentPort;

    @Override
    public void delete(DeleteCommentCommand command) {
        deleteCommentPort.delete(command.getCommentId());
    }
}
