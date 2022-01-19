package com.kakao.cafe.application.article.service;

import com.kakao.cafe.application.article.dto.UpdateRequest;
import com.kakao.cafe.application.article.port.in.UpdateArticleUseCase;
import com.kakao.cafe.application.article.port.out.UpdateArticlePort;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;

public class UpdateArticleService implements UpdateArticleUseCase {

    private final UpdateArticlePort updateArticlePort;

    public UpdateArticleService(UpdateArticlePort updateArticlePort) {
        this.updateArticlePort = updateArticlePort;
    }

    @Override
    public void updateArticle(UpdateRequest updateRequest)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException {
        updateArticlePort.updateArticle(updateRequest);
    }
}
