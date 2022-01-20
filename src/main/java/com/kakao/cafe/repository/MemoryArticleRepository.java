package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.web.dto.ArticleCreateRequestDto;

import java.time.LocalDateTime;
import java.util.*;

public class MemoryArticleRepository implements ArticleRepository {

    private final Map<Long, Article> articleMap = new HashMap<>();
    private Long idNumber = 0L;

    @Override
    public void create(ArticleCreateRequestDto requestDto) {
        Long id = ++idNumber;
        articleMap.put(id, new Article(id, requestDto.getWriter(), requestDto.getTitle(), requestDto.getContents(), LocalDateTime.now(), LocalDateTime.now()));
    }

    @Override
    public List<Article> findNotDeleted() {
        return List.copyOf(articleMap.values());
    }

    @Override
    public Article findById(Long id) {
        Optional<Article> result =  Optional.ofNullable(articleMap.get(id));
        if (result.isPresent()) {
            return result.get();
        }
        throw new RuntimeException("일치하는 게시글이 존재하지 않습니다.");
    }

    @Override
    public void shiftIsDeleted(Long id) {
        articleMap.get(id).shiftIsDeleted();
    }
}
