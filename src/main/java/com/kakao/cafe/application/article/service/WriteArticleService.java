package com.kakao.cafe.application.article.service;

import com.kakao.cafe.application.article.dto.WriteRequest;
import com.kakao.cafe.application.article.port.in.WriteArticleUseCase;
import com.kakao.cafe.application.article.port.out.RegisterArticlePort;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalIdException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import org.springframework.stereotype.Service;

@Service
public class WriteArticleService implements WriteArticleUseCase {

    private final RegisterArticlePort registerArticlePort;

    public WriteArticleService(RegisterArticlePort registerArticlePort) {
        this.registerArticlePort = registerArticlePort;
    }

    @Override
    public void writeArticle(WriteRequest writeRequest)
        throws IllegalIdException, IllegalWriterException, IllegalTitleException, IllegalDateException {
        registerArticlePort.registerArticle(writeRequest);
    }
}
