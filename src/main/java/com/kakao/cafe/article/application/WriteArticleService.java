package com.kakao.cafe.article.application;

import com.kakao.cafe.article.application.port.in.WriteArticleDto;
import com.kakao.cafe.article.application.port.in.WriteArticleUseCase;
import com.kakao.cafe.article.application.port.out.CreateArticleDto;
import com.kakao.cafe.article.application.port.out.SaveArticlePort;
import com.kakao.cafe.article.domain.ArticleId;

public class WriteArticleService implements WriteArticleUseCase {

    private final SaveArticlePort saveArticlePort;

    public WriteArticleService(SaveArticlePort saveArticlePort) {
        this.saveArticlePort = saveArticlePort;
    }


    @Override
    public ArticleId write(WriteArticleDto writeArticleDto) {
        CreateArticleDto createArticleDto = new CreateArticleDto(
            writeArticleDto.getTitle(),
            writeArticleDto.getTitle());

        return saveArticlePort.save(createArticleDto);
    }
}
