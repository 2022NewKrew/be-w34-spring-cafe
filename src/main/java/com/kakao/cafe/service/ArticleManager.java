package com.kakao.cafe.service;

import com.kakao.cafe.domain.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ArticleManager implements ArticleService {
    private final Articles articles = new Articles();

    @Override
    public void add(@NonNull final ArticleDto articleDto) {
        articles.add(new Article(
                articleDto.getAuthor(),
                articleDto.getTitle(),
                articleDto.getBody()
        ));
    }

    @Override
    public List<ArticleDto> getList() {
        final List<ArticleDto> dtos = new ArrayList<>();
        for (Article a: articles.getList()) {
            dtos.add(ArticleDto.from(a));
        }

        return Collections.unmodifiableList(dtos);
    }

    @Override
    public ArticleDto getArticle(final long idx) throws NoSuchElementException {
        final Article article = articles.find(idx);
        if (article.isNone()) {
            throw new NoSuchElementException("Not found article - " + idx);
        }
        return ArticleDto.from(article);
    }
}
