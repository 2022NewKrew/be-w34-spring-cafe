package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.article.ArticleCreateDTO;
import com.kakao.cafe.dto.article.ArticleDTO;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ArticleMemoryRepository implements ArticleRepository{

    private static Map<Long, Article> articleMap = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public ArticleDTO save(ArticleCreateDTO articleCreateDTO) {
        Article article = new Article(++sequence, articleCreateDTO);
        articleMap.put(article.getId(), article);
        return new ArticleDTO(article.getId(),article.getWritingTime(), article.getCountOfComment(), article.getAuthorId(), article.getTitle(), article.getContents());
    }

    @Override
    public Optional<ArticleDTO> findById(Long id) {
        Article article = articleMap.get(id);
        ArticleDTO articleDTO = new ArticleDTO(article.getId(),article.getWritingTime(), article.getCountOfComment(), article.getAuthorId(), article.getTitle(), article.getContents());
        return Optional.ofNullable(articleDTO);
    }

    @Override
    public List<ArticleDTO> findAll() {
        return new ArrayList<>(
                articleMap.values().stream()
                        .map(article -> new ArticleDTO(article.getId(),article.getWritingTime(), article.getCountOfComment(), article.getAuthorId(), article.getTitle(), article.getContents()))
                        .collect(Collectors.toList()));
    }
}
