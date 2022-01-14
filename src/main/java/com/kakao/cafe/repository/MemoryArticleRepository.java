package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.CreateArticleDto;
import com.kakao.cafe.dto.FindArticleDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryArticleRepository implements ArticleRepository {

    private static final List<Article> store = new ArrayList<>();
    private static int articleId = 0;

    @Override
    public void create(CreateArticleDto createArticleDto) {
        articleId += 1;
        ArticleDto articleDto = new ArticleDto(
            articleId,
            createArticleDto.getTitle(),
            createArticleDto.getContent()
        );

        store.add(new Article(articleDto));
    }

    @Override
    public Optional<Article> findById(FindArticleDto findArticleDto) {
        return store.stream()
            .filter(article -> Objects.equals(article.getId(), findArticleDto.getArticleId()))
            .findFirst();
    }

    @Override
    public List<Article> getAll() {
        return new ArrayList<>(store);
    }
}
