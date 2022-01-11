package com.kakao.cafe.service;

import com.kakao.cafe.dto.request.ArticleReqDto;
import com.kakao.cafe.entity.ArticleEntity;
import com.kakao.cafe.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void saveArticle(ArticleReqDto articleDto) {
        ArticleEntity articleEntity = ArticleEntity.builder()
                .writer(articleDto.getWriter())
                .title(articleDto.getTitle())
                .contents(articleDto.getContents())
                .build();

        articleRepository.save(articleEntity);
    }
}
