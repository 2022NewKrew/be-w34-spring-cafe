package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticlePage;
import com.kakao.cafe.domain.article.Reply;
import com.kakao.cafe.dto.article.*;
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

    public void saveComment(Long articleId, Long authorId, ReplyRequest request) {
        replyRepository.save(request.toEntity(articleId, authorId));
    }

    public ArticlePageDto getArticlePage(int page) {
        Integer totalSize = articleRepository.getTotalSize();
        ArticlePage articlePage = new ArticlePage(totalSize, page);
        List<Article> articles = articleRepository.findAllByOffset(articlePage.offset(), ArticlePage.DEFAULT_PAGE_SIZE);
        articlePage.setArticles(articles);

        return new ArticlePageDto(articlePage);
    }

    public ArticleDto findArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("존재하지 않는 게시글입니다."));
        return new ArticleDto(article);
    }

    public ReplyDto findReplyById(Long id) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("존재하지 않는 댓글입니다."));
        return new ReplyDto(reply);
    }

    public ArticleDetailDto findArticleDetailById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("존재하지 않는 게시글입니다."));
        List<ReplyDto> replies = replyRepository.findAllByArticleId(id).stream()
                .map(ReplyDto::new)
                .collect(Collectors.toList());
        return new ArticleDetailDto(article, replies);
    }

    public void updateArticle(Long id, ArticleRequest articleRequest) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("존재하지 않는 게시글입니다."));
        article.update(articleRequest.getTitle(), articleRequest.getDescription());
        articleRepository.update(article);
    }

    public void deleteArticle(Long AuthorId, Long articleId) {
        List<Reply> replies = replyRepository.findAllByArticleId(articleId);
        replies.forEach(r -> {
                    if (!r.getAuthorId().equals(AuthorId)) {
                        throw new IllegalStateException("다른 유저의 댓글이 있는 게시글은 삭제할 수 없습니다.");
                    }
                    deleteReply(r.getId());
                });
        articleRepository.delete(articleId);
    }

    public void deleteReply(Long id) {
        replyRepository.delete(id);
    }
}
