package com.kakao.cafe.article.adapter.out;

import com.kakao.cafe.article.application.port.out.CreateArticleDto;
import com.kakao.cafe.article.application.port.out.LoadArticlePort;
import com.kakao.cafe.article.application.port.out.SaveArticlePort;
import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleId;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryArticleRepository implements SaveArticlePort, LoadArticlePort {

    private static final List<Article> store = new ArrayList<>();
    private static int sequence = 0;

    @Override
    public void save(CreateArticleDto createArticleDto) {
        sequence += 1;
        Article article = new Article(
            new ArticleId(sequence),
            createArticleDto.getTitle(),
            createArticleDto.getContent(),
            Instant.now());

        store.add(article);
    }

    @Override
    public Optional<Article> load(ArticleId findArticleId) {
        return store.stream()
            .filter(article -> Objects.equals(article.getId().getValue(), findArticleId.getValue()))
            .findFirst();
    }

    @Override
    public List<Article> loadAll() {
        return new ArrayList<>(store);
    }
}
