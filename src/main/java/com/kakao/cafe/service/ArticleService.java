package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CommentService commentService;

    public List<Article> findAll() {
        return articleRepository.selectAll();
    }

    public List<ArticleDTO> findAllDTO() {
        List<Article> articleList = findAll();
        return articleList.stream().map(Article::getDTO).collect(Collectors.toList());
    }

    public Optional<Article> findByKey(Long key) {
        Optional<Article> article = articleRepository.selectByKey(key);
        if (article.isEmpty()) return article;
        article.get().setCommentList(commentService.findAllByArticleKey(key));
        return article;
    }

    public Optional<ArticleDTO> findByKeyDTO(Long key) {
        Optional<Article> article = findByKey(key);
        return article.map(Article::getDTO);
    }

    public Long join(ArticleDTO articleDTO) {
        Article article = Article.fromDTOWithoutPostTime(articleDTO);
        article.setPostTime(LocalDateTime.now());
        return articleRepository.insert(article);
    }

    public void update(Long key, ArticleDTO articleDTO) {
        articleRepository.update(key, Article.fromDTOWithoutUser(articleDTO));
    }

    public void delete(Long key) {
        articleRepository.delete(key);
    }
}
