package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.dto.article.ArticleAddRequestDto;
import com.kakao.cafe.dto.article.ArticleResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    ArticleRepository articles = new ArticleRepository();

    public void add(ArticleAddRequestDto artDto) {
        articles.add(artDto);
    }

    public List<Article> findAll() {
        return articles.findAll();
    }

    public ArticleResponseDto findByIndex(int index) {
        return articles.findByIndex(index);
    }
}
