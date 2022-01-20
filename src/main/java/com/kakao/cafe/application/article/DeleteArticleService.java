package com.kakao.cafe.application.article;

import com.kakao.cafe.domain.article.DeleteArticlePort;

public class DeleteArticleService {
    private final DeleteArticlePort deleteArticlePort;

    public DeleteArticleService(DeleteArticlePort deleteArticlePort) {
        this.deleteArticlePort = deleteArticlePort;
    }

    public void delete(int id) {
        deleteArticlePort.deleteById(id);
    }
}
