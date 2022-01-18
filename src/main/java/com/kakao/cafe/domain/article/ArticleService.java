package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dto.ArticleRequestDto;
import com.kakao.cafe.domain.article.dto.ArticleResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Long createArticle(ArticleRequestDto requestDto, String userId) {
        Article article = requestDto.toArticle();
        if(!article.isAuthor(userId)) {
            throw new IllegalArgumentException("사용자는 해당 게시글을 생성할 수 있는 권한이 없습니다.");
        }
        return articleRepository.save(requestDto.toArticle());
    }

    @Transactional
    public Long updateArticle(Long articleId, String userId, ArticleRequestDto requestDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        if(!article.isAuthor(userId)) {
            throw new IllegalArgumentException("사용자는 해당 게시글을 수정할 수 있는 권한이 없습니다.");
        }
        article.update(requestDto.getTitle(), requestDto.getContent());
        return articleRepository.save(article);
    }

    @Transactional
    public Long deleteArticle(Long articleId, String userId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        if(!article.isAuthor(userId)) {
            throw new IllegalArgumentException("사용자는 해당 게시글을 삭제할 수 있는 권한이 없습니다.");
        }
        return articleRepository.delete(article);
    }

    @Transactional(readOnly = true)
    public ArticleResponseDto retrieveArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        return new ArticleResponseDto(article);
    }

    @Transactional(readOnly = true)
    public ArticleResponseDto retrieveArticleForUpdate(Long articleId, String userId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        if(!article.isAuthor(userId)) {
            throw new IllegalArgumentException("사용자는 게시글을 수정할 수 있는 권한이 없습니다.");
        }
        return new ArticleResponseDto(article);
    }

    @Transactional(readOnly = true)
    public List<ArticleResponseDto> retrieveAllArticles() {
        return articleRepository.findAll().stream().map(ArticleResponseDto::new).collect(Collectors.toList());
    }
}
