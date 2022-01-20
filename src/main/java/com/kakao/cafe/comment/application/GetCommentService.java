package com.kakao.cafe.comment.application;

import com.kakao.cafe.comment.application.dto.command.GetCommentCommand;
import com.kakao.cafe.comment.application.dto.command.GetRelatedPostCommentCommand;
import com.kakao.cafe.comment.application.dto.result.GetRelatedPostCommentResult;
import com.kakao.cafe.comment.application.dto.result.GetCommentResult;
import com.kakao.cafe.comment.application.port.in.GetCommentUseCase;
import com.kakao.cafe.comment.application.port.out.LoadCommentPort;
import com.kakao.cafe.comment.domain.Comment;
import com.kakao.cafe.user.application.port.out.LoadUserAccountPort;
import com.kakao.cafe.user.domain.UserAccount;
import com.kakao.cafe.user.exception.UserAccountErrorCode;
import com.kakao.cafe.user.exception.UserAccountException;
import com.kakao.cafe.util.DateTimeFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetCommentService implements GetCommentUseCase {

    private final LoadCommentPort loadCommentPort;
    private final LoadUserAccountPort loadUserAccountPort;

    @Override
    public GetRelatedPostCommentResult getRelatedPost(GetRelatedPostCommentCommand command) {
        List<Comment> comments = loadCommentPort.findByQuestionId(command.getQuestionPostId());

        List<GetCommentResult> result = comments.stream()
                .map(comment -> new GetCommentResult(
                        comment.getCommentId(),
                        loadUserAccountPort.findById(comment.getUserAccountId())
                                .orElseThrow(() -> new UserAccountException(UserAccountErrorCode.ID_NOT_FOUND))
                                .getUsername(),
                        DateTimeFormatUtil.format(comment.getCreatedAt()),
                        comment.getContent(),
                        comment.getUserAccountId())
                ).collect(Collectors.toList());

        return new GetRelatedPostCommentResult(result);
    }

    @Override
    public GetCommentResult get(GetCommentCommand command) {
        Comment comment = loadCommentPort.findById(command.getCommandId())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 아이디가 존재하지 않습니다"));
        UserAccount userAccount = loadUserAccountPort.findById(comment.getUserAccountId())
                .orElseThrow(() -> new UserAccountException(UserAccountErrorCode.ID_NOT_FOUND));

        return new GetCommentResult(
                comment.getCommentId(),
                userAccount.getUsername(),
                DateTimeFormatUtil.format(comment.getCreatedAt()),
                comment.getContent(),
                comment.getUserAccountId()
        );
    }
}
