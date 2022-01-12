package com.kakao.cafe.application.article.service;

import com.kakao.cafe.application.article.dto.WriteRequest;
import com.kakao.cafe.application.article.port.in.WriteArticleUseCase;
import com.kakao.cafe.application.article.port.out.RegisterArticlePort;
import com.kakao.cafe.domain.article.Article;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class WriteArticleService implements WriteArticleUseCase {

    private static final int FIRST_INDEX = 1;

    private final RegisterArticlePort registerArticlePort;
    private final AtomicInteger atomicInt = new AtomicInteger(FIRST_INDEX);

    public WriteArticleService(RegisterArticlePort registerArticlePort) {
        this.registerArticlePort = registerArticlePort;
    }

    @Override
    public void writeArticle(WriteRequest writeRequest) {
        registerArticlePort.registerArticle(new Article(
            atomicInt.getAndIncrement(),
            writeRequest.getWriter(),
            writeRequest.getTitle(),
            writeRequest.getContents(),
            LocalDateTime.now()
        ));
    }
}
