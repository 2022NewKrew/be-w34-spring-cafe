package com.kakao.cafe.application.reply.port.in;

public interface DeleteReplyUseCase {

    void delete(int id);

    void deleteAllRepliesInArticle(int articleId);
}
