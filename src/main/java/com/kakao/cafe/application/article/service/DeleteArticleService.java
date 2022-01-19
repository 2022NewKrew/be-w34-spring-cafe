package com.kakao.cafe.application.article.service;

import com.kakao.cafe.application.article.port.in.DeleteArticleUseCase;
import com.kakao.cafe.application.article.port.out.DeleteArticlePort;

public class DeleteArticleService implements DeleteArticleUseCase {

    private final DeleteArticlePort deleteArticlePort;

    public DeleteArticleService(DeleteArticlePort deleteArticlePort) {
        this.deleteArticlePort = deleteArticlePort;
    }

    @Override
    public void delete(int id) {
        deleteArticlePort.delete(id);
    }
}
