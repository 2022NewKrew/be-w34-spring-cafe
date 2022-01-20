package com.kakao.cafe.article.application.service;

import com.kakao.cafe.article.application.port.in.CommentRegistrationUseCase;
import com.kakao.cafe.article.application.port.out.RegisterCommentPort;

public class CommentRegistrationService implements CommentRegistrationUseCase {
    private final RegisterCommentPort registerCommentPort;

    public CommentRegistrationService(RegisterCommentPort registerCommentPort) {
        this.registerCommentPort = registerCommentPort;
    }

    @Override
    public void registerComment(String comment, String userNickname, Long userId, Long articleId) {
        registerCommentPort.registerComment(comment, userNickname, userId, articleId);
    }
}
