package com.kakao.cafe.comment.application;

import com.kakao.cafe.comment.application.dto.command.LeaveCommentCommand;
import com.kakao.cafe.comment.application.dto.result.LeaveCommentResult;
import com.kakao.cafe.comment.application.port.in.LeaveCommentUseCase;
import com.kakao.cafe.comment.application.port.out.CreateCommentPort;
import com.kakao.cafe.comment.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class LeaveCommentService implements LeaveCommentUseCase {

    private final CreateCommentPort createCommentPort;

    @Override
    public LeaveCommentResult leave(LeaveCommentCommand command) {
        Comment comment = Comment.builder()
                .content(command.getContent())
                .createdAt(LocalDateTime.now())
                .questionPostId(command.getQuestionPostId())
                .userAccountId(command.getUserAccountId())
                .build();
        Comment result = createCommentPort.create(comment);

        return new LeaveCommentResult(result.getCommentId());
    }

}
