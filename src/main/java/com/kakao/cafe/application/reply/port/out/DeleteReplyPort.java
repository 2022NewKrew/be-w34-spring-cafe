package com.kakao.cafe.application.reply.port.out;

public interface DeleteReplyPort {

    void delete(int id);

    void deleteAllRepliesInArticle(int articleId);
}
