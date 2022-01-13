package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDto;
import com.kakao.cafe.repo.ArticleRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleManager implements ArticleService {
    private final ArticleRepository articleRepository;

    ArticleManager(final ArticleRepository articleRepository) {
        this.articleRepository = Objects.requireNonNull(articleRepository);
    }

    @Override
    public void add(@NonNull final ArticleDto articleDto) {
        boolean result = articleRepository.add(new Article(
                articleDto.getUserId(),
                articleDto.getUserName(),
                articleDto.getTitle(),
                articleDto.getBody()
        ));

        if (!result) {
            throw new RuntimeException("Failed to insert article!");
        }
    }

    @Override
    public List<ArticleDto> getList() {
        final List<ArticleDto> dtos = new ArrayList<>();
        for (Article a: articleRepository.getList()) {
            dtos.add(ArticleDto.from(a));
        }

        return Collections.unmodifiableList(dtos);
    }

    @Override
    public ArticleDto getArticle(final long idx) throws NoSuchElementException {
        final Article article = articleRepository.find(idx);
        if (article.isNone()) {
            throw new NoSuchElementException("Not found article - " + idx);
        }
        return ArticleDto.from(article);
    }
}
