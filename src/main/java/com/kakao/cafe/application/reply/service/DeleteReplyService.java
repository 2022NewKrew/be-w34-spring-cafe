package com.kakao.cafe.application.reply.service;

import com.kakao.cafe.application.reply.port.in.DeleteReplyUseCase;
import com.kakao.cafe.application.reply.port.out.DeleteReplyPort;

public class DeleteReplyService implements DeleteReplyUseCase {

    private final DeleteReplyPort deleteReplyPort;

    public DeleteReplyService(DeleteReplyPort deleteReplyPort) {
        this.deleteReplyPort = deleteReplyPort;
    }

    @Override
    public void delete(int id) {
        deleteReplyPort.delete(id);
    }

    @Override
    public void deleteAllRepliesInArticle(int articleId) {
        deleteReplyPort.deleteAllRepliesInArticle(articleId);
    }
}
