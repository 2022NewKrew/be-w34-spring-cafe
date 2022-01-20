package com.kakao.cafe.article.application.port.in;

public interface CommentRegistrationUseCase {

    void registerComment(String comment, String userNickname, Long userId, Long articleId);
}
