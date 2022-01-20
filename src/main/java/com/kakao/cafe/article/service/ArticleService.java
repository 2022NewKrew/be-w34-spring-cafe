package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void question(Article article) {
        articleRepository.save(article);
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(()
                -> new NotFoundException("해당 id의 질문은 존재하지 않습니다."));
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public void deleteArticle(Long userFK, Long articleId) throws AccessDeniedException {
        Article article = findById(articleId);
        if (userFK != article.getUserFk()) {
            throw new AccessDeniedException("해당 게시물을 삭제할 수 있는 권한이 없습니다.");
        }
        articleRepository.delete(articleId);
    }
}
