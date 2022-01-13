package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Articles;
import com.kakao.cafe.web.dto.article.ArticleAddRequestDto;
import com.kakao.cafe.web.dto.article.ArticleResponseDto;
import com.kakao.cafe.web.dto.article.ArticlesListResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    Articles articles = new Articles();

    public void add(ArticleAddRequestDto artDto) {
        articles.add(artDto);
    }

    public ArticlesListResponseDto findAll() {
        return articles.findAll();
    }

    public ArticleResponseDto findByIndex(int index) {
        return articles.findByIndex(index);
    }
}
