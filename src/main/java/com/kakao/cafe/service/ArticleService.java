package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.dto.article.ArticleDetailDto;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ArticleRequest;
import com.kakao.cafe.dto.article.ReplyDto;
import com.kakao.cafe.exception.ArticleNotFoundException;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    public ArticleService(ArticleRepository articleRepository, ReplyRepository replyRepository) {
        this.articleRepository = articleRepository;
        this.replyRepository = replyRepository;
    }

    public void publishArticle(Long id, ArticleRequest request) {
        articleRepository.save(request.toEntity(id));
    }

    public List<ArticleDto> getArticles() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::new)
                .collect(Collectors.toList());
    }

    public ArticleDto findById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("존재하지 않는 게시글입니다."));
        return new ArticleDto(article);
    }

    public ArticleDetailDto findDetailById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("존재하지 않는 게시글입니다."));
        List<ReplyDto> replies = replyRepository.findAllByArticleId(id).stream()
                .map(ReplyDto::new)
                .collect(Collectors.toList());
        return new ArticleDetailDto(article, replies);
    }

    public void update(Long id, ArticleRequest articleRequest) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("존재하지 않는 게시글입니다."));
        article.update(articleRequest.getTitle(), articleRequest.getDescription());
        articleRepository.update(article);
    }

    public void delete(Long id) {
        articleRepository.delete(id);
    }
}
